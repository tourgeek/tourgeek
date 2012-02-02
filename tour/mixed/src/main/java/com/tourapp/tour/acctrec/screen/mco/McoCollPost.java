/**
 * @(#)McoCollPost.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
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
        
        BaseField fldAirlineID = this.getScreenRecord().getField(McoScreenRecord.AIRLINE_ID);
        String strAirlineID = this.getProperty(fldAirlineID.getFieldName());
        fldAirlineID.setString(strAirlineID);
        
        this.getMainRecord().setKeyArea(Mco.TRX_STATUS_ID_KEY);
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, Mco.MCO_FILE, Mco.SUBMITTED);
        this.getMainRecord().addListener(new SubFileFilter(recTrxStatus));
        
        this.getMainRecord().addListener(new McoCollCalcNetBeh(null));
        
        this.getMainRecord().addListener(new CompareFileFilter(Mco.AIRLINE_ID, this.getScreenRecord().getField(McoScreenRecord.AIRLINE_ID), FileListener.EQUALS, null, false));
        this.getScreenRecord().getField(McoScreenRecord.NULL_DATE).setData(null); // Just need a temporary null field to compare
        this.getMainRecord().addListener(new CompareFileFilter(Mco.DATE_PAID, this.getScreenRecord().getField(McoScreenRecord.NULL_DATE), FileListener.NOT_EQUAL, null, false));
        
        Record recMco = this.getMainRecord();
        recMco.addListener(new SubCountHandler(this.getScreenRecord().getField(McoScreenRecord.COUNT), false, true));
        recMco.addListener(new SubCountHandler(this.getScreenRecord().getField(McoScreenRecord.TOTAL_GROSS), Mco.GROSS, false, true));
        recMco.addListener(new SubCountHandler(this.getScreenRecord().getField(McoScreenRecord.TOTAL_NET), Mco.AMOUNT_PAID, false, true));
        
        recMco.close();
        try   {   // Recount totals
            while (recMco.hasNext())
            {
                recMco.next();
                if (recMco.getField(Mco.DATE_PAID).isNull())
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
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        TransactionType recTransactionType = (TransactionType)this.getRecord(TransactionType.TRANSACTION_TYPE_FILE);
        
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
                if (recBaseArTrx.getField(BaseArPay.DATE_PAID).isNull())
                    continue;
        
                recBaseArTrx.startDistTrx();
                // Step 2a - Create and write the Mco transaction.
                bookmark = recBaseArTrx.getHandle(DBConstants.DATA_SOURCE_HANDLE);
                recBaseArTrx.edit();
                recBaseArTrx.getField(BaseArPay.TRX_STATUS_ID).setValue(iEnteredTrxClass);
                recBaseArTrx.getField(BaseArPay.PAYMENT_ENTERED).setValue(DateTimeField.todaysDate());
                recBaseArTrx.set();
                recBaseArTrx.setHandle(bookmark, DBConstants.DATA_SOURCE_HANDLE);
                // Step 2 - Post it to the G/L
                BaseField fldAccountID = this.getTrxAccountID(recBaseArTrx);
                double dTrxAmount = recBaseArTrx.getField(BaseArPay.AMT_APPLY).getValue();
                recBaseArTrx.onPostTrxDist(fldAccountID, -dTrxAmount, PostingType.TRX_POST);
                // Step 2c - Post the distribution side of the transaction.
                fldAccountID = this.getDistAccountID(recBaseArTrx);
                double dPaid = recBaseArTrx.getField(BaseArPay.AMOUNT_PAID).getValue();
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
        this.getScreenRecord().getField(McoScreenRecord.COUNT).initField(DBConstants.DISPLAY);
        this.getScreenRecord().getField(McoScreenRecord.TOTAL_GROSS).initField(DBConstants.DISPLAY);
        this.getScreenRecord().getField(McoScreenRecord.TOTAL_NET).initField(DBConstants.DISPLAY);
        return true;
    }
    /**
     * Get the base trx record.
     * @return The record.
     */
    public BaseTrx getBaseTrx()
    {
        return (BaseTrx)this.getRecord(Mco.MCO_FILE);
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
        ArControl recArControl = (ArControl)this.getRecord(ArControl.AR_CONTROL_FILE);
        BaseField fldAccountID = null;
        Airline recAirline = (Airline)((ReferenceField)recBaseArTrx.getField(Mco.AIRLINE_ID)).getReference();
        if (recAirline != null)
            fldAccountID = recAirline.getField(Airline.MCO_REC_ACCOUNT_ID);
        if ((fldAccountID == null) || (fldAccountID.getValue() == 0))
            fldAccountID = recArControl.getField(ArControl.MCO_REC_ACCOUNT_ID);
        return fldAccountID;
    }
    /**
     * GetDistAccountID Method.
     */
    public BaseField getDistAccountID(Record recBaseArTrx)
    {
        return this.getRecord(ArControl.AR_CONTROL_FILE).getField(ArControl.MCO_SUSPENSE_ACCOUNT_ID);
    }
    /**
     * GetVarAccountID Method.
     */
    public BaseField getVarAccountID(Record recBaseArTrx)
    {
        BaseField fldAccountID = null;
        ArControl recArControl = (ArControl)this.getRecord(ArControl.AR_CONTROL_FILE);
        Airline recAirline = (Airline)((ReferenceField)recBaseArTrx.getField(Mco.AIRLINE_ID)).getReference();
        if (recAirline != null)
            fldAccountID = recAirline.getField(Airline.MCO_VAR_ACCOUNT_ID);
        if ((fldAccountID == null) || (fldAccountID.getValue() == 0))
            fldAccountID = recArControl.getField(ArControl.MCO_VAR_ACCOUNT_ID);
        return fldAccountID;
    }
    /**
     * GetNewTrxClass Method.
     */
    public int getNewTrxClass()
    {
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        int iEnteredTrxClass = recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, Mco.MCO_FILE, Mco.ITEM_PAID);
        return iEnteredTrxClass;
    }

}
