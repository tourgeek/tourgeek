/**
 * @(#)CreditCardCollPost.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
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
        BaseField fldCardID = this.getScreenRecord().getField(McoScreenRecord.CARD_ID);
        String strCardID = this.getProperty(fldCardID.getFieldName());
        fldCardID.setString(strCardID);
        
        this.getMainRecord().setKeyArea(CreditCard.TRX_STATUS_ID_KEY);
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, CreditCard.CREDIT_CARD_FILE, CreditCard.APPROVED);
        this.getMainRecord().addListener(new SubFileFilter(recTrxStatus));
        
        this.getMainRecord().addListener(new CompareFileFilter(CreditCard.CARD_ID, this.getScreenRecord().getField(McoScreenRecord.CARD_ID), FileListener.EQUALS, null, false));
        this.getScreenRecord().getField(McoScreenRecord.NULL_DATE).setData(null); // Just need a temporary null field to compare
        this.getMainRecord().addListener(new CompareFileFilter(CreditCard.DATE_PAID, this.getScreenRecord().getField(McoScreenRecord.NULL_DATE), FileListener.NOT_EQUAL, null, false));
        
        Record recCreditCard = this.getMainRecord();
        recCreditCard.addListener(new SubCountHandler(this.getScreenRecord().getField(McoScreenRecord.COUNT), false, true));
        recCreditCard.addListener(new SubCountHandler(this.getScreenRecord().getField(McoScreenRecord.TOTAL_GROSS), CreditCard.GROSS, false, true));
        recCreditCard.addListener(new SubCountHandler(this.getScreenRecord().getField(McoScreenRecord.TOTAL_NET), CreditCard.AMOUNT_PAID, false, true));
        
        recCreditCard.close();
        try   {   // Recount totals
            while (recCreditCard.hasNext())
            {
                recCreditCard.next();
                if (recCreditCard.getField(CreditCard.DATE_PAID).isNull())
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
        return (BaseTrx)this.getRecord(CreditCard.CREDIT_CARD_FILE);
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
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        return recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, CreditCard.CREDIT_CARD_FILE, CreditCard.ITEM_PAID);
    }
    /**
     * GetTrxAccountID Method.
     */
    public BaseField getTrxAccountID(Record recBaseArTrx)
    {
        BaseField fldAccountID = null;
        ArControl recArControl = (ArControl)this.getRecord(ArControl.AR_CONTROL_FILE);
        Card recCard = (Card)((ReferenceField)recBaseArTrx.getField(CreditCard.CARD_ID)).getReference();
        if (recCard != null)
            fldAccountID = recCard.getField(Card.CREDIT_CARD_REC_ACCOUNT_ID);
        if ((fldAccountID == null) || (fldAccountID.getValue() == 0))
            fldAccountID = recArControl.getField(ArControl.CREDIT_CARD_REC_ACCOUNT_ID);
        return fldAccountID;
    }
    /**
     * GetDistAccountID Method.
     */
    public BaseField getDistAccountID(Record recBaseArTrx)
    {
        return this.getRecord(ArControl.AR_CONTROL_FILE).getField(ArControl.CREDIT_CARD_SUSPENSE_ACCOUNT_ID);
    }
    /**
     * GetVarAccountID Method.
     */
    public BaseField getVarAccountID(Record recBaseArTrx)
    {
        BaseField fldAccountID = null;
        ArControl recArControl = (ArControl)this.getRecord(ArControl.AR_CONTROL_FILE);
        Card recCard = (Card)((ReferenceField)recBaseArTrx.getField(CreditCard.CARD_ID)).getReference();
        if (recCard != null)
            fldAccountID = recCard.getField(Card.CREDIT_CARD_VAR_ACCOUNT_ID);
        if ((fldAccountID == null) || (fldAccountID.getValue() == 0))
            fldAccountID = recArControl.getField(ArControl.CREDIT_CARD_VAR_ACCOUNT_ID);
        return fldAccountID;
    }

}
