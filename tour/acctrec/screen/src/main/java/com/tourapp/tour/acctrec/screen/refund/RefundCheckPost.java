/**
  * @(#)RefundCheckPost.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctrec.screen.refund;

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
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.acctrec.screen.cash.*;
import com.tourapp.tour.assetdr.db.*;

/**
 *  RefundCheckPost - Refund check posting.
 */
public class RefundCheckPost extends BaseArTrxPostScreen
{
    /**
     * Default constructor.
     */
    public RefundCheckPost()
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
    public RefundCheckPost(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Refund check posting";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new ArTrx(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new BankTrx(this);
        new BankAcct(this);
        
        new TrxStatus(this);
        
        new ArControl(this);
        new Booking(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new RefundScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.AR_TRX_FILE, ArTrx.REFUND_PAY);
        this.getMainRecord().setKeyArea(ArTrx.TRX_STATUS_ID_KEY);
        this.getMainRecord().addListener(new SubFileFilter(recTrxStatus.getField(TrxStatus.ID), ArTrx.TRX_STATUS_ID, null, null, null, null));
        this.getMainRecord().addListener(new BumpCheckNoHandler(this.getScreenRecord().getField(RefundScreenRecord.CHECK_NO), this.getScreenRecord().getField(RefundScreenRecord.NEXT_CHECK_NO)));
        this.getMainRecord().addListener(new UpdateRefundPaidAcctDetailHandler(null));
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(RefundScreenRecord.REFUND_SCREEN_RECORD_FILE).getField(RefundScreenRecord.BANK_ACCT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(RefundScreenRecord.REFUND_SCREEN_RECORD_FILE).getField(RefundScreenRecord.CHECK_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(RefundScreenRecord.REFUND_SCREEN_RECORD_FILE).getField(RefundScreenRecord.NEXT_CHECK_NO).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
    }
    /**
     * Process the command.
     * <br />Step 1 - Process the command if possible and return true if processed.
     * <br />Step 2 - If I can't process, pass to all children (with me as the source).
     * <br />Step 3 - If children didn't process, pass to parent (with me as the source).
     * <br />Note: Never pass to a parent or child that matches the source (to avoid an endless loop).
     * @param strCommand The command to process.
     * @param sourceSField The source screen field (to avoid echos).
     * @param iCommandOptions If this command creates a new screen, create in a new window?
     * @return true if success.
     */
    public boolean doCommand(String strCommand, ScreenField sourceSField, int iCommandOptions)
    {
        if (strCommand.equalsIgnoreCase(MenuConstants.POST))
            return this.onPost();
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Post all the transactions in this batch.
     * @return true if successful.
     */
    public boolean onPost()
    {
        try   {
            TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
            Object bookmark = recTrxStatus.getHandle(DBConstants.DATA_SOURCE_HANDLE);
            int iTrxPaidClassID = recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.AR_TRX_FILE, ArTrx.REFUND_PAID);
            recTrxStatus.setHandle(bookmark, DBConstants.DATA_SOURCE_HANDLE);
            Record recArTrx = this.getMainRecord();
            recArTrx.close();
            while (recArTrx.hasNext())
            {
                recArTrx.next();
                recArTrx.edit();
                recArTrx.getField(ArTrx.TRX_STATUS_ID).setValue(iTrxPaidClassID);
                recArTrx.set();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return true;
    }

}
