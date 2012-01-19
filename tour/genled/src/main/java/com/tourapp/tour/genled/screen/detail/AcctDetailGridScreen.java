/**
 * @(#)AcctDetailGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.screen.detail;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.genled.db.*;

/**
 *  AcctDetailGridScreen - Account Transaction Detail.
 */
public class AcctDetailGridScreen extends DetailGridScreen
{
    /**
     * Default constructor.
     */
    public AcctDetailGridScreen()
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
    public AcctDetailGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Account Transaction Detail";
    }
    /**
     * Constructor.
     */
    public AcctDetailGridScreen(Record recAccount, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recAccount, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new AcctDetail(this);
    }
    /**
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        return new Account(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new AcctDetailScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getMainRecord().addListener(new ExtractRangeFilter(AcctDetail.kTrxDate, this.getScreenRecord().getField(AcctDetailScreenRecord.kStartDate), this.getScreenRecord().getField(AcctDetailScreenRecord.kEndDate), ExtractRangeFilter.PAD_DEFAULT));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(AcctDetailScreenRecord.kChangeBalance), AcctDetail.kAmountLocal, true, true));    // Init this field override for other value
        
        this.getHeaderRecord().getField(Account.kID).addListener(new FieldReSelectHandler(this));
        this.getScreenRecord().getField(AcctDetailScreenRecord.kStartDate).addListener(new FieldReSelectHandler(this));
        this.getScreenRecord().getField(AcctDetailScreenRecord.kEndDate).addListener(new FieldReSelectHandler(this));
        this.getScreenRecord().getField(AcctDetailScreenRecord.kCalcStart).addListener(new FieldReSelectHandler(this));
        
        this.getMainRecord().addListener(new AcctDetailCalcStartBal(this.getRecord(Account.kAccountFile), this.getScreenRecord()));
        this.getScreenRecord().getField(AcctDetailScreenRecord.kStartBalance).addListener(new AcctDetailCalcEndBal(this.getScreenRecord()));
        this.getScreenRecord().getField(AcctDetailScreenRecord.kChangeBalance).addListener(new AcctDetailCalcEndBal(this.getScreenRecord()));
        
        this.setEditing(false);
    }
    /**
     * Add the navigation button(s) to the left of the grid row.
     */
    public void addNavButtons()
    {
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
        super.addNavButtons();  // Next buttons will be "First!"
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kTrxDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kAmountLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kComments).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kSource).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kTrxTypeID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        if (strCommand.equalsIgnoreCase(MenuConstants.FORMDETAIL))
            return (this.onForm(null, AcctDetail.ACCT_DIST_GRID_SCREEN, true, iCommandOptions, null) != null);
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new AccountHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }

}
