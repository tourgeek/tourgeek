/**
 * @(#)RefundGridScreen.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import com.tourapp.tour.booking.entry.acctrec.*;
import com.tourapp.tour.acctrec.screen.misc.*;
import com.tourapp.tour.acctrec.db.event.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.db.*;

/**
 *  RefundGridScreen - Enter refunds.
 */
public class RefundGridScreen extends BookingArTrxGridScreen
{
    /**
     * Default constructor.
     */
    public RefundGridScreen()
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
    public RefundGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Enter refunds";
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new TrxStatus(this);
        new ArControl(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new CrDrScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.setEditing(false);     // Can't change current rows
        this.getMainRecord().getField(ArTrx.TRX_DATE).setEnabled(true);
        this.getMainRecord().getField(ArTrx.AMOUNT).setEnabled(true);
        this.getMainRecord().getField(ArTrx.COMMENTS).setEnabled(true);
        this.setAppending(true);    // CAN append
        this.getMainRecord().setOpenMode(DBConstants.OPEN_APPEND_ONLY); // This makes it possible
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.AR_TRX_FILE, ArTrx.REFUND_SUBMITTED);
        this.getMainRecord().getField(ArTrx.TRX_STATUS_ID).addListener(new InitFieldHandler(recTrxStatus.getField(TrxStatus.ID)));
        
        this.getMainRecord().getListener(UpdateArTrxAcctDetailHandler.class, true).setEnabledListener(false);    // Since I will be doing the updating
        this.getMainRecord().addListener(new UpdateRefundAcctDetailHandler(this.getRecord(Booking.BOOKING_FILE)));
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
        {
            BasePanel parentScreen = this.getParentScreen();
            if (parentScreen != null)
            {
                parentScreen.popHistory(1, false);
                parentScreen.pushHistory(this.getScreenURL(), false);  // Push this screen onto history stack
            }
            return (this.onForm(null, ArTrx.REFUND_SCREEN, true, iCommandOptions, null) != null);
        }
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
