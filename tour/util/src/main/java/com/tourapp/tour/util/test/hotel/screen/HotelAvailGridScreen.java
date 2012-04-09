/**
 * @(#)HotelAvailGridScreen.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.util.test.hotel.screen;

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
import com.tourapp.tour.util.test.hotel.db.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.core.trx.*;

/**
 *  HotelAvailGridScreen - .
 */
public class HotelAvailGridScreen extends GridScreen
{
    /**
     * Default constructor.
     */
    public HotelAvailGridScreen()
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
    public HotelAvailGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return new HotelAvail(this);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Run updater", MenuConstants.FORMDETAIL, "Run updater", null);
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
        if ("Run updater".equalsIgnoreCase(strCommand))
            return this.runUpdater();
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * RunUpdater Method.
     */
    public boolean runUpdater()
    {
        Map<String,Object> properties = new HashMap<String,Object>();
        String strTableName = this.getMainRecord().getTableNames(false);
        properties.put(DBParams.PROCESS, HotelAvailProcess.class.getName());
        properties.put(DBParams.TABLE, strTableName);
        
        Application app = (Application)this.getTask().getApplication();
        String strQueueName = MessageConstants.TRX_SEND_QUEUE;
        String strQueueType = MessageConstants.INTRANET_QUEUE;
        BaseMessage message = new MapMessage(new TrxMessageHeader(strQueueName, strQueueType, properties), properties);
        
        app.getMessageManager().sendMessage(message);
        
        return true;
    }

}
