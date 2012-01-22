/**
 * @(#)RefundPendGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.assetdr.db.*;

/**
 *  RefundPendGridScreen - Review the pending refunds.
 */
public class RefundPendGridScreen extends GridScreen
{
    /**
     * Default constructor.
     */
    public RefundPendGridScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public RefundPendGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Review the pending refunds";
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
        new TrxStatus(this);
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
        this.getMainRecord().setKeyArea(ArTrx.kTrxStatusIDKey);
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile);
        int iSubmitted = recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.kArTrxFile, ArTrx.REFUND_SUBMITTED);
        int iHeld = recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.kArTrxFile, ArTrx.REFUND_HELD);
        int iPay = recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.kArTrxFile, ArTrx.REFUND_PAY);
        this.getScreenRecord().getField(RefundScreenRecord.kStartTrxStatusID).setValue(Math.min(iSubmitted, Math.min(iHeld, iPay)));
        this.getScreenRecord().getField(RefundScreenRecord.kEndTrxStatusID).setValue(Math.max(iSubmitted, Math.max(iHeld, iPay)));
        
        this.getMainRecord().addListener(new ExtractRangeFilter(ArTrx.kTrxStatusID, this.getScreenRecord().getField(RefundScreenRecord.kStartTrxStatusID), this.getScreenRecord().getField(RefundScreenRecord.kEndTrxStatusID), ExtractRangeFilter.PAD_END_FIELD));
        this.getMainRecord().addListener(new CompareRefundHandler(this.getMainRecord().getField(ArTrx.kTrxStatusID), iSubmitted, iHeld, iPay));
        
        Record recBooking = ((ReferenceField)this.getMainRecord().getField(ArTrx.kBookingID)).getReferenceRecord();
        if (recBooking.getRecordOwner() == null)
            this.addRecord(recBooking, false);
        this.setEnabled(false);
        this.getMainRecord().getField(ArTrx.kTrxStatusID).setEnabled(true);
        this.getMainRecord().getField(ArTrx.kComments).setEnabled(true);
        this.setAppending(false);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(ArTrx.kArTrxFile).getField(ArTrx.kBookingID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ArTrx.kArTrxFile).getField(ArTrx.kComments).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ArTrx.kArTrxFile).getField(ArTrx.kAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ArTrx.kArTrxFile).getField(ArTrx.kTrxStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.kArTrxFile, ArTrx.REFUND_PAY);
        new SRadioButton(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, this.getRecord(ArTrx.kArTrxFile).getField(ArTrx.kTrxStatusID), ScreenConstants.DEFAULT_DISPLAY, recTrxStatus.getField(TrxStatus.kID).toString(), application.getResources(ResourceConstants.ACCTREC_RESOURCE, true).getString(ArTrx.REFUND_PAY));
        recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.kArTrxFile, ArTrx.REFUND_HELD);
        new SRadioButton(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, this.getRecord(ArTrx.kArTrxFile).getField(ArTrx.kTrxStatusID), ScreenConstants.DEFAULT_DISPLAY, recTrxStatus.getField(TrxStatus.kID).toString(), application.getResources(ResourceConstants.ACCTREC_RESOURCE, true).getString(ArTrx.REFUND_HELD));
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
        if ((strCommand.equalsIgnoreCase(MenuConstants.FORM)) || (strCommand.equalsIgnoreCase(MenuConstants.FORMLINK)))
            return (this.onForm(null, ArTrx.REFUND_PEND_SCREEN, true, iCommandOptions, null) != null);
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
