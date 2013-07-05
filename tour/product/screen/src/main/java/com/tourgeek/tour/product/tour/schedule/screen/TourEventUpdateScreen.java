/**
  * @(#)TourEventUpdateScreen.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.tour.schedule.screen;

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
import org.jbundle.base.thread.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.base.message.core.trx.*;

/**
 *  TourEventUpdateScreen - Manually run the booking and tour actions.
 */
public class TourEventUpdateScreen extends Screen
{
    /**
     * Default constructor.
     */
    public TourEventUpdateScreen()
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
    public TourEventUpdateScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Manually run the booking and tour actions";
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new TourEventScreenRecord(this);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(TourEventScreenRecord.TOUR_EVENT_SCREEN_RECORD_FILE).getField(TourEventScreenRecord.ACTION_CUTOFF_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourEventScreenRecord.TOUR_EVENT_SCREEN_RECORD_FILE).getField(TourEventScreenRecord.TOUR_UPDATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourEventScreenRecord.TOUR_EVENT_SCREEN_RECORD_FILE).getField(TourEventScreenRecord.BOOKING_UPDATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourEventScreenRecord.TOUR_EVENT_SCREEN_RECORD_FILE).getField(TourEventScreenRecord.RUN_PROCESS_IN).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.RUN, MenuConstants.RUN, MenuConstants.RUN, null);
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
        if (MenuConstants.RUN.equals(strCommand))
        {
            Map<String,Object> properties = new HashMap<String,Object>();
            properties.put(DBParams.PROCESS, TourEventUpdateProcess.class.getName());
            for (int iIndex = 0; iIndex < this.getSFieldCount(); iIndex++)
            {
                ScreenField sField = this.getSField(iIndex);
                if (sField.getConverter() != null)
                {
                    properties.put(sField.getSFieldParam(null, false), sField.getSFieldValue(false, false));
                }
            }
            Application app = (Application)this.getTask().getApplication();
            String strQueueName = MessageConstants.TRX_SEND_QUEUE;
            String strQueueType = MessageConstants.INTRANET_QUEUE;
            BaseMessage message = new MapMessage(new TrxMessageHeader(strQueueName, strQueueType, properties), properties);
            String strProcess = Utility.propertiesToURL(null, properties);
        
            if (RunProcessInField.REMOTE_PROCESS.equalsIgnoreCase(this.getScreenRecord().getField(TourEventScreenRecord.RUN_PROCESS_IN).toString()))
            {
                app.getMessageManager().sendMessage(message);
            }
            else if (RunProcessInField.LOCAL_PROCESS.equalsIgnoreCase(this.getScreenRecord().getField(TourEventScreenRecord.RUN_PROCESS_IN).toString()))
            {
                app.getTaskScheduler().addTask(new ProcessRunnerTask(app, strProcess, null));
            }
            else // LOCAL
            {
                new ProcessRunnerTask(app, strProcess, null).run();
            }
            
            return true;
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
