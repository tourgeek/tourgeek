/**
  * @(#)RefundCheckPrint.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctrec.screen.refund;

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
import com.tourgeek.tour.acctrec.db.*;
import com.tourgeek.tour.booking.detail.db.*;
import com.tourgeek.tour.genled.db.*;
import com.tourgeek.tour.assetdr.db.*;
import com.tourgeek.tour.booking.db.*;

/**
 *  RefundCheckPrint - Print refund checks.
 */
public class RefundCheckPrint extends CheckPrintScreen
{
    /**
     * Default constructor.
     */
    public RefundCheckPrint()
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
    public RefundCheckPrint(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Print refund checks";
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
        new ArControl(this);
        new TrxStatus(this);
        new BookingLine(this);
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
        
        this.getScreenRecord().getField(RefundScreenRecord.BANK_ACCT_ID).addListener(new InitFieldHandler(this.getRecord(ArControl.AR_CONTROL_FILE).getField(ArControl.REFUND_BANK_ACCT_ID)));
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.AR_TRX_FILE, ArTrx.REFUND_PAY);
        this.getMainRecord().setKeyArea(ArTrx.TRX_STATUS_ID_KEY);
        this.getMainRecord().addListener(new SubFileFilter(recTrxStatus.getField(TrxStatus.ID), ArTrx.TRX_STATUS_ID, null, null, null, null));
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(RefundScreenRecord.REPORT_COUNT), false, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(RefundScreenRecord.REPORT_TOTAL), ArTrx.AMOUNT, false, true));
        
        Record recBooking = this.getRecord(Booking.BOOKING_FILE);
        Record recBookingLine = this.getRecord(BookingLine.BOOKING_LINE_FILE);
        recBookingLine.addListener(new SubFileFilter(recBooking));
        recBooking.addListener(new RecountOnValidHandler(recBookingLine));
        recBookingLine.addListener(new SubCountHandler(recBooking.getField(Booking.GROSS), BookingLine.GROSS, true, true));
        recBookingLine.addListener(new SubCountHandler(recBooking.getField(Booking.NET), BookingLine.NET, true, true));
        
        Record recBankAcct = ((ReferenceField)this.getScreenRecord().getField(RefundScreenRecord.BANK_ACCT_ID)).getReferenceRecord();
        recBankAcct.addListener(new MoveOnValidHandler(this.getScreenRecord().getField(RefundScreenRecord.NEXT_CHECK_NO), recBankAcct.getField(BankAcct.NEXT_CHECK)));
        
        this.getMainRecord().addListener(new BumpCheckNoHandler(this.getScreenRecord().getField(RefundScreenRecord.CHECK_NO), this.getScreenRecord().getField(RefundScreenRecord.NEXT_CHECK_NO)));
        
        this.getScreenRecord().getField(RefundScreenRecord.BANK_ACCT_ID).addListener(new ReadSecondaryHandler(recBankAcct));
        
        recBooking.addListener(new MoveOnValidHandler(this.getScreenRecord().getField(RefundScreenRecord.PAYEE), recBooking.getField(Booking.GENERIC_NAME)));
        this.getMainRecord().addListener(new MoveOnValidHandler(this.getScreenRecord().getField(RefundScreenRecord.CHECK_AMOUNT), this.getMainRecord().getField(ArTrx.AMOUNT)));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new RefundCheckToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
        super.addToolbarButtons(toolScreen);
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
        if ((strCommand.equalsIgnoreCase(MenuConstants.POST))
            || (strCommand.equalsIgnoreCase(RefundCheckJournal.CANNED_CHECKS))
            || (strCommand.equalsIgnoreCase(RefundCheckJournal.XML_CHECKS)))
        {
            Object objBankID = this.getScreenRecord().getField(RefundScreenRecord.BANK_ACCT_ID).getData();
            Object objCheckNo = this.getScreenRecord().getField(RefundScreenRecord.NEXT_CHECK_NO).getData();
            Object objCheckDate = this.getScreenRecord().getField(RefundScreenRecord.CHECK_DATE).getData();
            Record record = this.getMainRecord();
            BasePanel parentScreen = this.getParentScreen();
        
            int iScreen = ArTrx.REFUND_CHECK_POST;
            if (strCommand.equalsIgnoreCase(RefundCheckJournal.CANNED_CHECKS))
                iScreen = ArTrx.REFUND_CHECK_CANNED_PRINT;
            if (strCommand.equalsIgnoreCase(RefundCheckJournal.XML_CHECKS))
                iScreen = ArTrx.REFUND_CHECK_XML_PRINT;
            boolean bSuccess = (this.onForm(record, iScreen, true, iCommandOptions, null) != null);
        
            if (bSuccess)
            {
                for (int i = 0; i < parentScreen.getSFieldCount(); i++)
                {
                    BasePanel screen = (BasePanel)parentScreen.getSField(i);
                    if (screen instanceof BaseScreen)
                        if (screen.getScreenRecord() instanceof RefundScreenRecord)
                    {
                        screen.getScreenRecord().getField(RefundScreenRecord.BANK_ACCT_ID).setData(objBankID);
                        screen.getScreenRecord().getField(RefundScreenRecord.NEXT_CHECK_NO).setData(objCheckNo);
                        screen.getScreenRecord().getField(RefundScreenRecord.CHECK_DATE).setData(objCheckDate);
                    }
                }
            }
        
            return bSuccess;
        }
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Layout the special print control (usually a JPanel).
     * ***** NOTE: This method is obsolete *****
     * The base code is now in the swing implementation
     * (VCustomReportScreen) since I can't reference awt from here.
     */
    public void layoutPrintControl(Object control)
    {
        super.layoutPrintControl(control);
    }

}
