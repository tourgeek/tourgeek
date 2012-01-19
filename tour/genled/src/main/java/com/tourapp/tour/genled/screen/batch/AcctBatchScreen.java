/**
 * @(#)AcctBatchScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.screen.batch;

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
import org.jbundle.main.screen.*;
import java.util.*;
import org.jbundle.thin.base.screen.*;

/**
 *  AcctBatchScreen - Transaction Entry.
 */
public class AcctBatchScreen extends Screen
{
    /**
     * Default constructor.
     */
    public AcctBatchScreen()
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
    public AcctBatchScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Transaction Entry";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new AcctBatch(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new AcctBatchScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getScreenRecord().getField(AcctBatchScreenRecord.kUserID).moveFieldToThis(this.getMainRecord().getField(AcctBatch.kUserID));
        this.getScreenRecord().getField(AcctBatchScreenRecord.kRecurring).moveFieldToThis(this.getMainRecord().getField(AcctBatch.kRecurring));
        
        this.getMainRecord().getField(AcctBatch.kRecurring).addListener(new DisableOnFieldHandler(this.getMainRecord().getField(AcctBatch.kTrxDate), BooleanField.YES, true));
        FieldListener listener = new CopyStringHandler(this.getMainRecord().getField(AcctBatch.kTrxDate), DBConstants.BLANK, this.getMainRecord().getField(AcctBatch.kRecurring));
        listener.setRespondsToMode(DBConstants.INIT_MOVE, true);
        this.getMainRecord().getField(AcctBatch.kTrxDate).addListener(listener);
        
        this.getMainRecord().setKeyArea(AcctBatch.kUserIDKey);
        this.getMainRecord().addListener(new SubFileFilter(this.getScreenRecord().getField(AcctBatchScreenRecord.kUserID), AcctBatch.kUserID, this.getScreenRecord().getField(AcctBatchScreenRecord.kRecurring), AcctBatch.kRecurring, null, -1));
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(AcctBatch.kAcctBatchFile).getField(AcctBatch.kTrxDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctBatch.kAcctBatchFile).getField(AcctBatch.kComments).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctBatch.kAcctBatchFile).getField(AcctBatch.kSource).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, AcctBatch.AUTO_CLOSING, "Go", AcctBatch.AUTO_CLOSING, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
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
            return (this.onForm(null, AcctBatch.ACCT_BATCH_DETAIL_SCREEN, true, iCommandOptions, null) != null);
        else if (strCommand.equalsIgnoreCase(MenuConstants.POST))
            return (this.onForm(null, AcctBatch.ACCT_BATCH_POST, true, iCommandOptions, null) != null);
        else if (strCommand.equalsIgnoreCase(AcctBatch.AUTO_CLOSING))
        {
            AcctBatch recAcctBatch = (AcctBatch)this.getMainRecord();
            return recAcctBatch.onAutoClosing();
        }
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
