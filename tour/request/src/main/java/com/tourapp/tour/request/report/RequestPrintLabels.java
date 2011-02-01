/**
 *  @(#)RequestPrintLabels.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.request.db.*;
import javax.swing.*;
import com.tourapp.tour.request.screen.*;
import com.tourapp.tour.base.db.shared.*;

/**
 *  RequestPrintLabels - Brochure request labels..
 */
public class RequestPrintLabels extends CustomReportScreen
{
    /**
     * Default constructor.
     */
    public RequestPrintLabels()
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
    public RequestPrintLabels(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Brochure request labels.";
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
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new RequestLabelsToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(RequestLabelsScreenRecord.kRequestLabelsScreenRecordFile).getField(RequestLabelsScreenRecord.kFullAddress).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(RequestLabelsScreenRecord.kRequestLabelsScreenRecordFile).getField(RequestLabelsScreenRecord.kRequestText).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
     * Layout the special print control (usually a JPanel).
     */
    public void layoutPrintControl(Component control)
    {
        JPanel panel = (JPanel)control;
        int x = 0;
        int y = 0;
        int width = 3 * 72;
        int height = 2 * 72;
        panel.setBounds(x, y, width, height);
        
        JTextArea label = (JTextArea)((STEView)this.getScreenRecord().getField(RequestLabelsScreenRecord.kFullAddress).getComponent(0)).getScreenFieldView().getControl();
        JScrollPane compTop = (JScrollPane)((STEView)this.getScreenRecord().getField(RequestLabelsScreenRecord.kFullAddress).getComponent(0)).getScreenFieldView().getControl(DBConstants.CONTROL_TOP);
        this.setupComponent(compTop, x, y, width, height);
        
        label = (JTextArea)((STEView)this.getScreenRecord().getField(RequestLabelsScreenRecord.kRequestText).getComponent(0)).getScreenFieldView().getControl();
        compTop = (JScrollPane)((STEView)this.getScreenRecord().getField(RequestLabelsScreenRecord.kRequestText).getComponent(0)).getScreenFieldView().getControl(DBConstants.CONTROL_TOP);
        compTop.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        compTop.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        y = (int)(1.5 * 72);
        height = (int)(0.5 * 72);
        this.setupComponent(compTop, x, y, width, height);
        label.setFont(new Font("SansSerif", Font.PLAIN, 8));
    }

}
