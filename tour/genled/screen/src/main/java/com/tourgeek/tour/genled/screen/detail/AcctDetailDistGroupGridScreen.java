/**
  * @(#)AcctDetailDistGroupGridScreen.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.genled.screen.detail;

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
import com.tourgeek.tour.genled.db.*;

/**
 *  AcctDetailDistGroupGridScreen - Transaction distribution group display.
 */
public class AcctDetailDistGroupGridScreen extends DetailGridScreen
{
    /**
     * Default constructor.
     */
    public AcctDetailDistGroupGridScreen()
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
    public AcctDetailDistGroupGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Transaction distribution group display";
    }
    /**
     * AcctDetailDistGroupGridScreen Method.
     */
    public AcctDetailDistGroupGridScreen(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recHeader, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new AcctDetailDist(this);
    }
    /**
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        return new AcctDetailDist(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new AcctDetail(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        Record recAcctDetailDist = this.getHeaderRecord();
        this.removeRecord(recAcctDetailDist); // Do not add this to the screen (because it may get mixed up with the detail record).
        this.getMainRecord().addListener(new FreeOnFreeHandler(recAcctDetailDist));
        
        this.getMainRecord().getField(AcctDetailDist.ACCT_DETAIL_ID).addListener(new ReadSecondaryHandler(this.getRecord(AcctDetail.ACCT_DETAIL_FILE)));
        
        this.setEditing(false);
        this.setAppending(false);
    }
    /**
     * AddSubFileFilter Method.
     */
    public void addSubFileFilter()
    {
        this.getMainRecord().setKeyArea(AcctDetailDist.ACCT_DETAIL_DIST_GROUP_ID_KEY);
        this.getMainRecord().addListener(new SubFileFilter(this.getHeaderRecord().getField(AcctDetailDist.ACCT_DETAIL_DIST_GROUP_ID), AcctDetailDist.ACCT_DETAIL_DIST_GROUP_ID, null, null, null, null));
        this.getHeaderRecord().getField(AcctDetailDist.ID).addListener(new FieldReSelectHandler(this));
    }
    /**
     * Add the navigation button(s) to the left of the grid row.
     */
    public void addNavButtons()
    {
        String strTransaction = AcctDetailDist.DIST_TRANSACTION;
        if (this.getTask() != null)
            strTransaction = ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.GENLED_RESOURCE, true).getString(strTransaction);
        BaseField.createScreenComponent(TransactionType.TRX_ID_SFIELD_CLASS, this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, this.getMainRecord().getField(AcctDetailDist.TRX_ID), ScreenConstants.DEFAULT_DISPLAY, null);
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, AcctDetailDist.DIST_TRANSACTION, AcctDetailDist.DIST_TRANSACTION, strTransaction);
        super.addNavButtons();  // Next buttons will be "First!"
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        String strTransaction = AcctDetailDist.DIST_TRANSACTION;
        if (this.getTask() != null)
            strTransaction = ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.GENLED_RESOURCE, true).getString(strTransaction);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, strTransaction, AcctDetailDist.DIST_TRANSACTION, AcctDetailDist.DIST_TRANSACTION, null);
        BaseField.createScreenComponent(TransactionType.TRX_ID_SFIELD_CLASS, toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, this.getMainRecord().getField(AcctDetailDist.TRX_ID), ScreenConstants.DEFAULT_DISPLAY, null);
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
        if(strCommand.equalsIgnoreCase(AcctDetailDist.DIST_TRANSACTION))
        {
            Record recAcctDetail = ((ReferenceField)this.getMainRecord().getField(AcctDetailDist.ACCT_DETAIL_ID)).getReference();
            if (recAcctDetail != null)
                return (this.onForm(recAcctDetail, ScreenConstants.MAINT_MODE, true, iCommandOptions, null) != null);
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(AcctDetailDist.ACCT_DETAIL_DIST_FILE).getField(AcctDetailDist.TRX_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.ACCT_DETAIL_FILE).getField(AcctDetail.ACCOUNT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetailDist.ACCT_DETAIL_DIST_FILE).getField(AcctDetailDist.AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetailDist.ACCT_DETAIL_DIST_FILE).getField(AcctDetailDist.TRX_DESC_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetailDist.ACCT_DETAIL_DIST_FILE).getField(AcctDetailDist.TRX_ENTRY).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetailDist.ACCT_DETAIL_DIST_FILE).getField(AcctDetailDist.TRX_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetailDist.ACCT_DETAIL_DIST_FILE).getField(AcctDetailDist.USER_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
