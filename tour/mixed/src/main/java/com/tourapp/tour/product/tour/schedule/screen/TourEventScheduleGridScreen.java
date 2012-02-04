/**
 * @(#)TourEventScheduleGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.schedule.screen;

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
import com.tourapp.tour.product.base.db.*;
import org.jbundle.main.screen.*;
import com.tourapp.tour.product.tour.schedule.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.tour.screen.*;
import org.jbundle.main.msg.db.*;

/**
 *  TourEventScheduleGridScreen - .
 */
public class TourEventScheduleGridScreen extends DetailGridScreen
{
    /**
     * Default constructor.
     */
    public TourEventScheduleGridScreen()
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
    public TourEventScheduleGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * TourEventScheduleGridScreen Method.
     */
    public TourEventScheduleGridScreen(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
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
        return new TourEventSchedule(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new BookingControl(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new TourClassScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getScreenRecord().getField(TourClassScreenRecord.TOUR_CLASS_ID).moveFieldToThis(this.getHeaderRecord().getField(TourClass.ID));
        ((ReferenceField)this.getScreenRecord().getField(TourClassScreenRecord.TOUR_CLASS_ID)).setReferenceRecord(this.getHeaderRecord());
        this.getScreenRecord().getField(TourClassScreenRecord.TOUR_CLASS_ID).addListener(new ReadSecondaryHandler(((ReferenceField)this.getScreenRecord().getField(TourClassScreenRecord.TOUR_CLASS_ID)).getReferenceRecord()));
        if (this.getHeaderRecord().getField(TourClass.ID).isNull())
            this.getScreenRecord().getField(TourClassScreenRecord.TOUR_CLASS_ID).moveFieldToThis(this.getRecord(BookingControl.BOOKING_CONTROL_FILE).getField(BookingControl.TOUR_CLASS_ID));
        
        this.getScreenRecord().getField(TourClassScreenRecord.TOUR_CLASS_ID).addListener(new FieldReSelectHandler(this));
    }
    /**
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        return new TourClass(this);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(TourEventSchedule.TOUR_EVENT_SCHEDULE_FILE).getField(TourEventSchedule.TOUR_EVENT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourEventSchedule.TOUR_EVENT_SCHEDULE_FILE).getField(TourEventSchedule.TOUR_ACTION_TYPE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourEventSchedule.TOUR_EVENT_SCHEDULE_FILE).getField(TourEventSchedule.ACTION_TOUR_EVENT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourEventSchedule.TOUR_EVENT_SCHEDULE_FILE).getField(TourEventSchedule.ACTION_MESSAGE_PROCESS_INFO_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new TourClassHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
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
        if (MessageProcessInfoManualField.LOOKUP_WITH_PARAMS.equalsIgnoreCase(strCommand))
        {
            if (this.getTask() != null)
                if (this.getTask().getApplication() != null)
            {
                BasePanel parentScreen = Screen.makeWindow(this.getTask().getApplication());
                Map<String,Object> properties = new Hashtable<String,Object>();
                RequestType recRequestType = new RequestType(this);
                properties.put("RequestTypeID", Integer.toString(recRequestType.getIDFromCode(RequestType.MANUAL)));
                recRequestType.free();
                Record record = ((ReferenceField)this.getMainRecord().getField(TourEventSchedule.ACTION_MESSAGE_PROCESS_INFO_ID)).getReferenceRecord();
                GridScreen screen = (GridScreen)record.makeScreen(null, parentScreen, ScreenConstants.SELECT_MODE, true, true, true, true, properties);
                //x if (record.getScreen() == null)
                    screen.setSelectQuery(record, false); // Since this record isn't linked to the screen, manually link it.
                return true;    // Handled
            }
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
