/**
 * @(#)RequestPrintXMLLabels.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.request.report;

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
import com.tourapp.tour.request.db.*;
import com.tourapp.tour.request.screen.*;
import com.tourapp.tour.base.db.shared.*;

/**
 *  RequestPrintXMLLabels - Brochure request XML labels..
 */
public class RequestPrintXMLLabels extends ReportScreen
{
    /**
     * Default constructor.
     */
    public RequestPrintXMLLabels()
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
    public RequestPrintXMLLabels(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Request(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new RequestDetail(this); 
        new Brochure(this);
        new RequestControl(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new RequestLabelsScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getScreenRecord().getField(RequestLabelsScreenRecord.kSendViaID).addListener(new InitFieldHandler(this.getRecord(RequestControl.kRequestControlFile).getField(RequestControl.kSendViaCode)));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(Request.kSendViaCode), this.getScreenRecord().getField(RequestLabelsScreenRecord.kSendViaID), "="));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(Request.kPrintNow), this.getScreenRecord().getField(RequestLabelsScreenRecord.kTrueField), "="));
        this.getRecord(RequestDetail.kRequestDetailFile).addListener(new SubFileFilter(this.getMainRecord()));
        this.getMainRecord().addListener(new SetupLabelTextHandler(null)); 
        this.getRecord(RequestDetail.kRequestDetailFile).getField(RequestDetail.kBrochureID).addListener(new ReadSecondaryHandler(this.getRecord(Brochure.kBrochureFile)));
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Request History", MenuConstants.LOOKUP, "Request History", null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Canned Labels", MenuConstants.PRINT, "Canned Labels", null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(Request.kRequestFile).getField(Request.kProfileCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kGenericName).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kAddressLine1).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kAddressLine2).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kCityOrTown).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kStateOrRegion).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kPostalCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kCountry).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kAttention).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(RequestLabelsScreenRecord.kRequestLabelsScreenRecordFile).getField(RequestLabelsScreenRecord.kFullAddress).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(RequestLabelsScreenRecord.kRequestLabelsScreenRecordFile).getField(RequestLabelsScreenRecord.kRequestText).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new RequestLabelsXMLToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
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
        if ("Request History".equalsIgnoreCase(strCommand))
        {
            BasePanel parentScreen = Screen.makeWindow(this.getTask().getApplication());
            new RequestHistoryDisplayScreen(null, null, parentScreen, null, 0, null);
            return true;
        }
        Map properties = new HashMap();
        properties.put("sendvia", this.getScreenRecord().getField(RequestLabelsScreenRecord.kSendViaID).getData());
        if (strCommand.equalsIgnoreCase(MenuConstants.PRINT))
        {
            // Step 1 - Move any history to the Request file before starting.
            RequestLabelsRestore update = new RequestLabelsRestore(this.getTask(), null, properties);
            update.run();
            update.free();
            // Step 2 - Mark all the records to print (In case someone submits a request between print and update).
            MarkLabelsToPrint markProcess = new MarkLabelsToPrint(this.getTask(), null, properties);
            markProcess.run();
            markProcess.free();
            // DO NOT RETURN, continue "printing".
        }
        if ("Canned Labels".equalsIgnoreCase(strCommand))
        {
            Record record = null;//this.getMainRecord();
            BasePanel parentScreen = this.getParentScreen();
            ScreenLocation itsLocation = this.getScreenLocation();
            FieldConverter fieldConverter = null;
            int iDisplayFieldDesc = ScreenConstants.DEFAULT_DISPLAY;
            int iSendViaID = (int)this.getScreenRecord().getField(RequestLabelsScreenRecord.kSendViaID).getValue();
            this.free();        // Remove this screen
            parentScreen.popHistory(1, false);  // Back should skip this screen
            BaseScreen screen = new RequestPrintLabels(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
            screen.getScreenRecord().getField(RequestLabelsScreenRecord.kSendViaID).setValue(iSendViaID);   // Sync
        
            strCommand = MenuConstants.PRINT;
            return screen.doCommand(strCommand, sourceSField, iCommandOptions);
        }
        boolean bSuccess = super.doCommand(strCommand, sourceSField, iCommandOptions);
        if (bSuccess)
            if (strCommand.equalsIgnoreCase(MenuConstants.PRINT))
        {       // Move the printed labels to history
            RequestLabelsUpdate update = new RequestLabelsUpdate(this.getTask(), null, properties);
            update.run();
            update.free();
        }
        return bSuccess;
    }
    /**
     * Get the path to the target servlet.
     * @param The servlet type (regular html or xhtml)
     * @return the servlet path.
     */
    public String getServletPath(String strServletParam)
    {
        return super.getServletPath(DBParams.XHTMLSERVLET); // Use cocoon
    }

}
