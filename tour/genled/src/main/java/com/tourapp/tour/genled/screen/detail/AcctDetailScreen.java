/**
 * @(#)AcctDetailScreen.
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
 *  AcctDetailScreen - Account Transaction Detail.
 */
public class AcctDetailScreen extends Screen
{
    /**
     * Default constructor.
     */
    public AcctDetailScreen()
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
    public AcctDetailScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new AcctDetail(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.setEditing(false);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kAccountID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kTrxDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        BaseField field = this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kAmountLocal);
        Converter converter = new DrCrConverter(field, true);
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        converter = new DrCrConverter(field, false);
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kComments).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kSource).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kTrxTypeID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kTrxEntry).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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

}
