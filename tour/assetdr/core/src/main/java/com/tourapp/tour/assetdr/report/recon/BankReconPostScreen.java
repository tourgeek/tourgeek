/**
 * @(#)BankReconPostScreen.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.report.recon;

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
import com.tourapp.tour.assetdr.db.*;

/**
 *  BankReconPostScreen - Bank Reconciliation.
 */
public class BankReconPostScreen extends Screen
{
    /**
     * Default constructor.
     */
    public BankReconPostScreen()
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
    public BankReconPostScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Bank Reconciliation";
    }
    /**
     * Free Method.
     */
    public void free()
    {
        this.setProperty("swingResizeHack", null);
        super.free();
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new AssetDrControl(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.setProperty("swingResizeHack", DBConstants.TRUE); // Swing doesn't layout this screen correctly
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.setScreenRecord(this.getMainRecord());
        this.getRecord(BankReconScreenRecord.BANK_RECON_SCREEN_RECORD_FILE).getField(BankReconScreenRecord.START_CLEARED).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankReconScreenRecord.BANK_RECON_SCREEN_RECORD_FILE).getField(BankReconScreenRecord.DEPOSITS_CLEARED).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankReconScreenRecord.BANK_RECON_SCREEN_RECORD_FILE).getField(BankReconScreenRecord.CHECKS_CLEARED).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankReconScreenRecord.BANK_RECON_SCREEN_RECORD_FILE).getField(BankReconScreenRecord.NEW_CLEARED).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.setEnabled(false);
        SCannedBox box = new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
        box.setEnabled(true);
        box.setRequestFocusEnabled(true);
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
            try {
                Record recAssetDrControl = this.getRecord(AssetDrControl.ASSET_DR_CONTROL_FILE);
                recAssetDrControl.edit();
                ((DateTimeField)recAssetDrControl.getField(AssetDrControl.DATE_RECONCILED)).setDateTime(new Date(), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                recAssetDrControl.set();
            } catch (DBException ex) {
                ex.printStackTrace();
            }
            strCommand = DBConstants.BACK;  // Go to previous screen
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
