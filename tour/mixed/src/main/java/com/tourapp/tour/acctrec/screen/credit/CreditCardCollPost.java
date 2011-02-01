/**
 *  @(#)CreditCardCollPost.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.credit;

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
import com.tourapp.tour.acctrec.screen.mco.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.profile.db.*;

/**
 *  CreditCardCollPost - Enter Credit payments collected from credit card company.
 */
public class CreditCardCollPost extends McoCollPost
{
    /**
     * Default constructor.
     */
    public CreditCardCollPost()
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
    public CreditCardCollPost(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Enter Credit payments collected from credit card company";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new CreditCard(this);
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
        
        new Card(this);
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
        // Don't call inherrited
        BaseField fldCardID = this.getScreenRecord().getField(McoScreenRecord.kCardID);
        String strCardID = this.getProperty(fldCardID.getFieldName());
        fldCardID.setString(strCardID);
        
        this.getMainRecord().setKeyArea(CreditCard.kTrxStatusIDKey);
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, CreditCard.kCreditCardFile, CreditCard.APPROVED);
        this.getMainRecord().addListener(new SubFileFilter(recTrxStatus));
        
        this.getMainRecord().addListener(new CompareFileFilter(CreditCard.kCardID, this.getScreenRecord().getField(McoScreenRecord.kCardID), FileListener.EQUALS, null, false));
        this.getScreenRecord().getField(McoScreenRecord.kNullDate).setData(null); // Just need a temporary null field to compare
        this.getMainRecord().addListener(new CompareFileFilter(CreditCard.kDatePaid, this.getScreenRecord().getField(McoScreenRecord.kNullDate), FileListener.NOT_EQUAL, null, false));
        
        Record recCreditCard = this.getMainRecord();
        recCreditCard.addListener(new SubCountHandler(this.getScreenRecord().getField(McoScreenRecord.kCount), false, true));
        recCreditCard.addListener(new SubCountHandler(this.getScreenRecord().getField(McoScreenRecord.kTotalGross), CreditCard.kGross, false, true));
        recCreditCard.addListener(new SubCountHandler(this.getScreenRecord().getField(McoScreenRecord.kTotalNet), CreditCard.kAmountPaid, false, true));
        
        recCreditCard.close();
        try   {   // Recount totals
            while (recCreditCard.hasNext())
            {
                recCreditCard.next();
                if (recCreditCard.getField(CreditCard.kDatePaid).isNull())
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
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, "Post", "Post", "Post", null);
    }
    /**
     * Post all the transactions in this batch.
     * @return true if successful.
     */
    public boolean onPost()
    {
        return super.onPost();
    }
    /**
     * Get the base trx record.
     * @return The record.
     */
    public BaseTrx getBaseTrx()
    {
        return (BaseTrx)this.getRecord(CreditCard.kCreditCardFile);
    }
    /**
     * Get the batch detail record.
     */
    public Record getDetailRecord()
    {
        return this.getMainRecord();
    }
    /**
     * GetNewTrxClass Method.
     */
    public int getNewTrxClass()
    {
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile);
        return recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, CreditCard.kCreditCardFile, CreditCard.PAID);
    }
    /**
     * GetTrxAccountID Method.
     */
    public BaseField getTrxAccountID(Record recBaseArTrx)
    {
        BaseField fldAccountID = null;
        ArControl recArControl = (ArControl)this.getRecord(ArControl.kArControlFile);
        Card recCard = (Card)((ReferenceField)recBaseArTrx.getField(CreditCard.kCardID)).getReference();
        if (recCard != null)
            fldAccountID = recCard.getField(Card.kCreditCardRecAccountID);
        if ((fldAccountID == null) || (fldAccountID.getValue() == 0))
            fldAccountID = recArControl.getField(ArControl.kCreditCardRecAccountID);
        return fldAccountID;
    }
    /**
     * GetDistAccountID Method.
     */
    public BaseField getDistAccountID(Record recBaseArTrx)
    {
        return this.getRecord(ArControl.kArControlFile).getField(ArControl.kCreditCardSuspenseAccountID);
    }
    /**
     * GetVarAccountID Method.
     */
    public BaseField getVarAccountID(Record recBaseArTrx)
    {
        BaseField fldAccountID = null;
        ArControl recArControl = (ArControl)this.getRecord(ArControl.kArControlFile);
        Card recCard = (Card)((ReferenceField)recBaseArTrx.getField(CreditCard.kCardID)).getReference();
        if (recCard != null)
            fldAccountID = recCard.getField(Card.kCreditCardVarAccountID);
        if ((fldAccountID == null) || (fldAccountID.getValue() == 0))
            fldAccountID = recArControl.getField(ArControl.kCreditCardVarAccountID);
        return fldAccountID;
    }

}
