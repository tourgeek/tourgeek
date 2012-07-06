/**
  * @(#)ArcReport.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctpay.air.arc;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;
import org.jbundle.main.db.*;

/**
 *  ArcReport - ARC Report.
 */
public class ArcReport extends ReportScreen
{
    /**
     * Default constructor.
     */
    public ArcReport()
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
    public ArcReport(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "ARC Report";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new TicketTrx(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ApControl(this);
        new TrxStatus(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new ArcReportScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        recTrxStatus.getTrxStatusID(TransactionType.AIR, TicketTrx.TICKET_TRX_FILE, TicketTrx.TICKETED);
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.TRX_STATUS_ID), recTrxStatus.getField(TrxStatus.ID), "="));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.ISSUE_DATE), this.getScreenRecord().getField(ArcReportScreenRecord.LAST_ARC_DATE), "<="));
        
        this.getScreenRecord().getField(ArcReportScreenRecord.LAST_ARC_DATE).setSFieldToProperty();
        this.getScreenRecord().getField(ArcReportScreenRecord.SUMMARY_ACCOUNT_ID).setSFieldToProperty();
        
        if (this.getScreenRecord().getField(ArcReportScreenRecord.LAST_ARC_DATE).isNull())
            this.getScreenRecord().getField(ArcReportScreenRecord.LAST_ARC_DATE).setValue(DateField.todaysDate());
        if (this.getScreenRecord().getField(ArcReportScreenRecord.SUMMARY_ACCOUNT_ID).isNull())
            this.getScreenRecord().getField(ArcReportScreenRecord.SUMMARY_ACCOUNT_ID).addListener(new InitFieldHandler(this.getRecord(ApControl.AP_CONTROL_FILE).getField(ApControl.ARC_SUMMARY_ACCOUNT_ID)));
        // Report totals
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(ArcReportScreenRecord.REPORT_TOTAL), TicketTrx.NET_FARE, true, true));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new ArcReportToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(TicketTrx.TICKET_TRX_FILE).getField(TicketTrx.TICKET_NUMBER).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.TICKET_TRX_FILE).getField(TicketTrx.AIRLINE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.TICKET_TRX_FILE).getField(TicketTrx.ISSUE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.TICKET_TRX_FILE).getField(TicketTrx.FARE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.TICKET_TRX_FILE).getField(TicketTrx.TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.TICKET_TRX_FILE).getField(TicketTrx.COMM_AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.TICKET_TRX_FILE).getField(TicketTrx.NET_FARE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new ArcReportHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new ArcReportFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
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
        {
            String strScreen = ArcReportPost.class.getName();
        
            strCommand = this.getScreenURL();
            strCommand = this.addScreenParams(this, strCommand);
            this.getParentScreen().popHistory(1, false);
            this.getParentScreen().pushHistory(strCommand, false); // Repush this command with the current values
        
            strCommand = Utility.addURLParam(null, DBParams.SCREEN, strScreen);
            strCommand = this.addScreenParams(this, strCommand);
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
