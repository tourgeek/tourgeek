/**
 * @(#)RefundCheckJournal.
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
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.acctrec.screen.cash.*;
import com.tourapp.tour.assetdr.db.*;

/**
 *  RefundCheckJournal - Print refund checks.
 */
public class RefundCheckJournal extends ReportScreen
{
    public static final String CANNED_CHECKS = "Canned Checks";
    public static final String XML_CHECKS = "XML Checks";
    /**
     * Default constructor.
     */
    public RefundCheckJournal()
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
    public RefundCheckJournal(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        
        this.getRecord(RefundScreenRecord.kRefundScreenRecordFile).getField(RefundScreenRecord.kBankAcctID).addListener(new InitFieldHandler(this.getRecord(ArControl.kArControlFile).getField(ArControl.kRefundBankAcctID)));
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.kArTrxFile, ArTrx.REFUND_PAY);
        this.getMainRecord().setKeyArea(ArTrx.kTrxStatusIDKey);
        this.getMainRecord().addListener(new SubFileFilter(recTrxStatus.getField(TrxStatus.kID), ArTrx.kTrxStatusID, null, -1, null, -1));
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(RefundScreenRecord.kReportCount), false, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(RefundScreenRecord.kReportTotal), ArTrx.kAmount, false, true));
        
        Record recBooking = this.getRecord(Booking.kBookingFile);
        Record recBookingLine = this.getRecord(BookingLine.kBookingLineFile);
        recBookingLine.addListener(new SubFileFilter(recBooking));
        recBooking.addListener(new RecountOnValidHandler(recBookingLine));
        recBookingLine.addListener(new SubCountHandler(recBooking.getField(Booking.kGross), BookingLine.kGross, true, true));
        recBookingLine.addListener(new SubCountHandler(recBooking.getField(Booking.kNet), BookingLine.kNet, true, true));
        
        Record recBankAcct = ((ReferenceField)this.getScreenRecord().getField(RefundScreenRecord.kBankAcctID)).getReferenceRecord();
        recBankAcct.addListener(new MoveOnValidHandler(this.getScreenRecord().getField(RefundScreenRecord.kNextCheckNo), recBankAcct.getField(BankAcct.kNextCheck)));
        
        this.getMainRecord().addListener(new BumpCheckNoHandler(this.getScreenRecord().getField(RefundScreenRecord.kCheckNo), this.getScreenRecord().getField(RefundScreenRecord.kNextCheckNo)));
        
        this.getScreenRecord().getField(RefundScreenRecord.kBankAcctID).addListener(new ReadSecondaryHandler(recBankAcct));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new RefundCheckToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.ACCTREC_RESOURCE, true).getString(CANNED_CHECKS), MenuConstants.PRINT, CANNED_CHECKS, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.ACCTREC_RESOURCE, true).getString(XML_CHECKS), MenuConstants.PRINT, XML_CHECKS, null);
        super.addToolbarButtons(toolScreen);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(ArTrx.kArTrxFile).getField(ArTrx.kBookingID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kProfileCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kTourID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kBookingStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kNet).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ArTrx.kArTrxFile).getField(ArTrx.kTrxDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ArTrx.kArTrxFile).getField(ArTrx.kAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(RefundScreenRecord.kRefundScreenRecordFile).getField(RefundScreenRecord.kCheckNo).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(RefundScreenRecord.kRefundScreenRecordFile).getField(RefundScreenRecord.kCheckDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new RefundCheckFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new RefundCheckHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
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
            || (strCommand.equalsIgnoreCase(CANNED_CHECKS))
            || (strCommand.equalsIgnoreCase(XML_CHECKS)))
        {
            Object objBankID = this.getScreenRecord().getField(RefundScreenRecord.kBankAcctID).getData();
            Object objCheckNo = this.getScreenRecord().getField(RefundScreenRecord.kNextCheckNo).getData();
            Object objCheckDate = this.getScreenRecord().getField(RefundScreenRecord.kCheckDate).getData();
            Record record = this.getMainRecord();
            BasePanel parentScreen = this.getParentScreen();
        
            int iScreen = ArTrx.REFUND_CHECK_POST;
            if (strCommand.equalsIgnoreCase(CANNED_CHECKS))
                iScreen = ArTrx.REFUND_CHECK_CANNED_PRINT;
            if (strCommand.equalsIgnoreCase(XML_CHECKS))
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
                        screen.getScreenRecord().getField(RefundScreenRecord.kBankAcctID).setData(objBankID);
                        screen.getScreenRecord().getField(RefundScreenRecord.kNextCheckNo).setData(objCheckNo);
                        screen.getScreenRecord().getField(RefundScreenRecord.kCheckDate).setData(objCheckDate);
                    }
                }
            }
        
            return bSuccess;
        }
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
