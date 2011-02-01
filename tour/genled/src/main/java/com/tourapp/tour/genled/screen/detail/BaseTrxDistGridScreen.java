/**
 *  @(#)BaseTrxDistGridScreen.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import org.jbundle.main.screen.*;
import com.tourapp.tour.genled.db.*;

/**
 *  BaseTrxDistGridScreen - Account Transaction Distribution.
 */
public class BaseTrxDistGridScreen extends DetailGridScreen
{
    protected TrxDescField m_fldTrxDescID = null;
    protected TrxIDField m_fldTrxID = null;
    /**
     * Default constructor.
     */
    public BaseTrxDistGridScreen()
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
    public BaseTrxDistGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        m_fldTrxDescID = null;
        m_fldTrxID = null;
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Account Transaction Distribution";
    }
    /**
     * Constructor - passing BaseTrx.
     */
    public BaseTrxDistGridScreen(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recHeader, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_fldTrxDescID != null)
            m_fldTrxDescID.free();
        m_fldTrxDescID = null;
        if (m_fldTrxID != null)
            m_fldTrxID.free();
        m_fldTrxID = null;
        super.free();
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
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new AcctDetail(this);
        // Remember to add to code in your override to check the m_recBaseTrx
    }
    /**
     * AddSubFileFilter Method.
     */
    public void addSubFileFilter()
    {
        this.getMainRecord().setKeyArea(AcctDetailDist.kTrxDescIDKey);
        
        m_fldTrxDescID = new TrxDescField(null, "TrxDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        m_fldTrxID = new TrxIDField(null, "TrxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        
        if ((this.getHeaderRecord().getEditMode() == DBConstants.EDIT_NONE) || (this.getHeaderRecord().getEditMode() == DBConstants.EDIT_ADD))
            this.syncHeaderToMain();
        
        Record recTrxStatus = ((ReferenceField)this.getHeaderRecord().getField(BaseTrx.kTrxStatusID)).getReference();
        if (recTrxStatus != null)
            m_fldTrxDescID.moveFieldToThis(recTrxStatus.getField(TrxStatus.kTrxDescID));
        m_fldTrxID.moveFieldToThis(this.getHeaderRecord().getField(BaseTrx.kID));
        m_fldTrxID.setReferenceRecord(this.getHeaderRecord());
        this.getMainRecord().addListener(new SubFileFilter(m_fldTrxDescID, AcctDetailDist.kTrxDescID, m_fldTrxID, AcctDetailDist.kTrxID, null, -1));
        m_fldTrxDescID.addListener(new FieldReSelectHandler(this));
        m_fldTrxID.addListener(new FieldReSelectHandler(this));
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getMainRecord().getField(AcctDetailDist.kAcctDetailID).addListener(new ReadSecondaryHandler(this.getRecord(AcctDetail.kAcctDetailFile)));
        
        this.setEditing(false);
    }
    /**
     * Add the navigation button(s) to the left of the grid row.
     */
    public void addNavButtons()
    {
        String strGroup = AcctDetailDist.DIST_GROUP;
        String strTransaction = AcctDetailDist.DIST_TRANSACTION;
        if (this.getTask() != null)
        {
            strGroup = ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.GENLED_RESOURCE, true).getString(strGroup);
            strTransaction = ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.GENLED_RESOURCE, true).getString(strTransaction);
        }
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, AcctDetailDist.DIST_TRANSACTION, AcctDetailDist.DIST_TRANSACTION, strTransaction);
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, AcctDetailDist.DIST_GROUP, AcctDetailDist.DIST_GROUP, strGroup);
        super.addNavButtons();  // Next buttons will be "First!"
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        String strGroup = AcctDetailDist.DIST_GROUP;
        String strTransaction = AcctDetailDist.DIST_TRANSACTION;
        if (this.getTask() != null)
        {
            strGroup = ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.GENLED_RESOURCE, true).getString(strGroup);
            strTransaction = ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.GENLED_RESOURCE, true).getString(strTransaction);
        }
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, strGroup, AcctDetailDist.DIST_GROUP, AcctDetailDist.DIST_GROUP, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, strTransaction, AcctDetailDist.DIST_TRANSACTION, AcctDetailDist.DIST_TRANSACTION, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(AcctDetailDist.kAcctDetailDistFile).getField(AcctDetailDist.kTrxDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kAccountID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetailDist.kAcctDetailDistFile).getField(AcctDetailDist.kAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetailDist.kAcctDetailDistFile).getField(AcctDetailDist.kTrxEntry).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetailDist.kAcctDetailDistFile).getField(AcctDetailDist.kUserID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        if(strCommand.equalsIgnoreCase(AcctDetailDist.DIST_GROUP))
            return (this.onForm(this.getMainRecord(), AcctDetailDist.DIST_GROUP_SCREEN, true, iCommandOptions, null) != null);
        else if(strCommand.equalsIgnoreCase(AcctDetailDist.DIST_TRANSACTION))
        {
            Record recAcctDetail = ((ReferenceField)this.getMainRecord().getField(AcctDetailDist.kAcctDetailID)).getReference();
            if (recAcctDetail != null)
                return (this.onForm(recAcctDetail, ScreenConstants.MAINT_MODE, true, iCommandOptions, null) != null);
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return super.makeSubScreen(); // Override this to display the header info
    }

}
