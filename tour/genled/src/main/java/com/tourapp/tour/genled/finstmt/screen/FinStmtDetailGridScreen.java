/**
 * @(#)FinStmtDetailGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.finstmt.screen;

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
import com.tourapp.tour.genled.db.*;

/**
 *  FinStmtDetailGridScreen - Financial Statement Detail.
 */
public class FinStmtDetailGridScreen extends DetailGridScreen
{
    /**
     * Default constructor.
     */
    public FinStmtDetailGridScreen()
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
    public FinStmtDetailGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Financial Statement Detail";
    }
    /**
     * FinStmtDetailGridScreen Method.
     */
    public FinStmtDetailGridScreen(Record recFinStmt, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recFinStmt, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new FinStmtDetail(this);
    }
    /**
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        return new FinStmt(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, FinStmtDetail.RENUMBER, "Go", FinStmtDetail.RENUMBER, null);
        this.getHeaderRecord().getField(FinStmt.kStatementDesc).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DONT_DISPLAY_DESC);
        this.getHeaderRecord().getField(FinStmt.kStatementDesc).setEnabled(false);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kSequence).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kAccountID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        Converter converter = new AccountDescConverter(this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kAccountDesc), this.getRecord(Account.kAccountFile).getField(Account.kDescription));
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kIndent).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kInvisible).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kTypicalBalance).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kSubTotalLevel).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kDataColumn).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kSpecialFormat).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kNumberFormat).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kSpecialFunction).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        Record recFinStmt = this.getHeaderRecord();
        FinStmt recFinStmtPopup = new FinStmt(this);
        this.removeRecord(recFinStmtPopup);    // The queryconverter will free this.
        recFinStmtPopup.setKeyArea(FinStmt.kFinStmtHeaderIDKey);
        recFinStmtPopup.addListener(new StringSubFileFilter(recFinStmt.getField(FinStmt.kFinStmtHeaderID).toString(), FinStmt.kFinStmtHeaderID, null, -1, null, -1));
        this.getMainRecord().getField(FinStmtDetail.kFinStmtID).setupTablePopup(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, null, ScreenConstants.DEFAULT_DISPLAY, recFinStmtPopup, FinStmt.kFinStmtHeaderIDKey, FinStmt.kStatementDesc, false, false);
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
        if (strCommand.equalsIgnoreCase(FinStmtDetail.RENUMBER))
        {
            FinStmtDetail recFinStmtDetail = new FinStmtDetail(this);
            recFinStmtDetail.renumber(this.getRecord(FinStmt.kFinStmtFile));
            recFinStmtDetail.free();
            recFinStmtDetail = null;
            this.reSelectRecords();
            return true;
        }
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
