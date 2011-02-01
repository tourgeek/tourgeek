/**
 *  @(#)McoCollPost.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.mco;

import java.awt.*;
import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctrec.screen.cash.*;
import com.tourapp.tour.product.air.db.*;

/**
 *  McoCollPost - Post the collections to A/R.
 */
public class McoCollPost extends BaseArTrxPostScreen
{
    /**
     * Default constructor.
     */
    public McoCollPost()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public McoCollPost(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Post the collections to A/R";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Mco(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ArControl(this);
        
        new TrxStatus(this);
        new TransactionType(this);
        
        new Airline(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new McoScreenRecord(this);
    }
    /**
     * AddTrxSpecificListeners Method.
     */
    public void addTrxSpecificListeners()
    {
        super.addTrxSpecificListeners();
        
        BaseField fldAirlineID = this.getScreenRecord().getField(McoScreenRecord.kAirlineID);
        String strAirlineID = this.getProperty(fldAirlineID.getFieldName());
        fldAirlineID.setString(strAirlineID);
        
        this.getMainRecord().setKeyArea(Mco.kTrxStatusIDKey);
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, Mco.kMcoFile, Mco.SUBMITTED);
        this.getMainRecord().addListener(new SubFileFilter(recTrxStatus));
        
        this.getMainRecord().addListener(new McoCollCalcNetBeh(null));
        
        this.getMainRecord().addListener(new CompareFileFilter(Mco.kAirlineID, this.getScreenRecord().getField(McoScreenRecord.kAirlineID), FileListener.EQUALS, null, false));
        this.getScreenRecord().getField(McoScreenRecord.kNullDate).setData(null); // Just need a temporary null field to compare
        this.getMainRecord().addListener(new CompareFileFilter(Mco.kDatePaid, this.getScreenRecord().getField(McoScreenRecord.kNullDate), FileListener.NOT_EQUAL, null, false));
        
        Record recMco = this.getMainRecord();
        recMco.addListener(new SubCountHandler(this.getScreenRecord().getField(McoScreenRecord.kCount), false, true));
        recMco.addListener(new SubCountHandler(this.getScreenRecord().getField(McoScreenRecord.kTotalGross), Mco.kGross, false, true));
        recMco.addListener(new SubCountHandler(this.getScreenRecord().getField(McoScreenRecord.kTotalNet), Mco.kAmountPaid, false, true));
        
        recMco.close();
        try   {   // Recount totals
            while (recMco.hasNext())
            {
                recMco.next();
                if (recMco.getField(Mco.kDatePaid).isNull())
                    continue;
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(McoScreenRecord.kMcoScreenRecordFile).getField(McoScreenRecord.kTotalGross).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(McoScreenRecord.kMcoScreenRecordFile).getField(McoScreenRecord.kTotalNet).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(McoScreenRecord.kMcoScreenRecordFile).getField(McoScreenRecord.kCount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
    }
    /**
     * Post all the transactions in this batch.
     * @return true if successful.
     */
    public boolean onPost()
    {
        // Step 1 - make sure batch is valid
        BaseArPay recBaseArTrx = (BaseArPay)this.getBaseTrx();
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile);
        TransactionType recTransactionType = (TransactionType)this.getRecord(TransactionType.kTransactionTypeFile);
        
        // Step 2 - Post it to the G/L
        try   {
            Object bookmark = recTrxStatus.getHandle(DBConstants.DATA_SOURCE_HANDLE);
            int iEnteredTrxClass = this.getNewTrxClass();
            recTrxStatus.setHandle(bookmark, DBConstants.DATA_SOURCE_HANDLE);
            recBaseArTrx.addListener(new SyncArTrxStatusHandler(null));   // Make sure A/R matches this
            recBaseArTrx.close();
            while (recBaseArTrx.hasNext())
            {
                recBaseArTrx.next();
                if (recBaseArTrx.getField(BaseArPay.kDatePaid).isNull())
                    continue;
        
                recBaseArTrx.startDistTrx();
                // Step 2a - Create and write the Mco transaction.
                bookmark = recBaseArTrx.getHandle(DBConstants.DATA_SOURCE_HANDLE);
                recBaseArTrx.edit();
                recBaseArTrx.getField(BaseArPay.kTrxStatusID).setValue(iEnteredTrxClass);
                recBaseArTrx.getField(BaseArPay.kPaymentEntered).setValue(DateTimeField.todaysDate());
                recBaseArTrx.set();
                recBaseArTrx.setHandle(bookmark, DBConstants.DATA_SOURCE_HANDLE);
                // Step 2 - Post it to the G/L
                BaseField fldAccountID = this.getTrxAccountID(recBaseArTrx);
                double dTrxAmount = recBaseArTrx.getField(BaseArPay.kAmtApply).getValue();
                recBaseArTrx.onPostTrxDist(fldAccountID, -dTrxAmount, PostingType.TRX_POST);
                // Step 2c - Post the distribution side of the transaction.
                fldAccountID = this.getDistAccountID(recBaseArTrx);
                double dPaid = recBaseArTrx.getField(BaseArPay.kAmountPaid).getValue();
                recBaseArTrx.onPostTrxDist(fldAccountID, dPaid, PostingType.DIST_POST);
        
                // Step 2d - Post the variance transaction.
                fldAccountID = this.getVarAccountID(recBaseArTrx);
                dTrxAmount = dTrxAmount - dPaid;
                if (dTrxAmount != 0)
                    recBaseArTrx.onPostTrxDist(fldAccountID, dTrxAmount, PostingType.OPTIONAL_POST);
                recBaseArTrx.endDistTrx();
        
                recBaseArTrx.close();
            } // End of loop
        } catch (DBException ex)    {
            ex.printStackTrace();
            return false;
        } finally {
            recBaseArTrx.removeListener(recBaseArTrx.getListener(SyncArTrxStatusHandler.class), true);   // Make sure A/R matches this
        }
        // Step 3 - Delete the batch (if not recurring)
        recBaseArTrx.close();
        this.getRecord(McoScreenRecord.kMcoScreenRecordFile).getField(McoScreenRecord.kCount).initField(DBConstants.DISPLAY);
        this.getRecord(McoScreenRecord.kMcoScreenRecordFile).getField(McoScreenRecord.kTotalGross).initField(DBConstants.DISPLAY);
        this.getRecord(McoScreenRecord.kMcoScreenRecordFile).getField(McoScreenRecord.kTotalNet).initField(DBConstants.DISPLAY);
        return true;
    }
    /**
     * Get the base trx record.
     * @return The record.
     */
    public BaseTrx getBaseTrx()
    {
        return (BaseTrx)this.getRecord(Mco.kMcoFile);
    }
    /**
     * Get the batch detail record.
     */
    public Record getDetailRecord()
    {
        return this.getMainRecord();
    }
    /**
     * GetTrxAccountID Method.
     */
    public BaseField getTrxAccountID(Record recBaseArTrx)
    {
        ArControl recArControl = (ArControl)this.getRecord(ArControl.kArControlFile);
        BaseField fldAccountID = null;
        Airline recAirline = (Airline)((ReferenceField)recBaseArTrx.getField(Mco.kAirlineID)).getReference();
        if (recAirline != null)
            fldAccountID = recAirline.getField(Airline.kMcoRecAccountID);
        if ((fldAccountID == null) || (fldAccountID.getValue() == 0))
            fldAccountID = recArControl.getField(ArControl.kMcoRecAccountID);
        return fldAccountID;
    }
    /**
     * GetDistAccountID Method.
     */
    public BaseField getDistAccountID(Record recBaseArTrx)
    {
        return this.getRecord(ArControl.kArControlFile).getField(ArControl.kMcoSuspenseAccountID);
    }
    /**
     * GetVarAccountID Method.
     */
    public BaseField getVarAccountID(Record recBaseArTrx)
    {
        BaseField fldAccountID = null;
        ArControl recArControl = (ArControl)this.getRecord(ArControl.kArControlFile);
        Airline recAirline = (Airline)((ReferenceField)recBaseArTrx.getField(Mco.kAirlineID)).getReference();
        if (recAirline != null)
            fldAccountID = recAirline.getField(Airline.kMcoVarAccountID);
        if ((fldAccountID == null) || (fldAccountID.getValue() == 0))
            fldAccountID = recArControl.getField(ArControl.kMcoVarAccountID);
        return fldAccountID;
    }
    /**
     * GetNewTrxClass Method.
     */
    public int getNewTrxClass()
    {
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile);
        int iEnteredTrxClass = recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, Mco.kMcoFile, Mco.PAID);
        return iEnteredTrxClass;
    }

}
