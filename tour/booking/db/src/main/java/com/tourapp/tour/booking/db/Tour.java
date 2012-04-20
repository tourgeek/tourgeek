/**
 * @(#)Tour.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.db;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.product.tour.db.*;
import java.util.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.booking.detail.event.*;
import com.tourapp.tour.product.tour.schedule.db.*;
import com.tourapp.tour.booking.db.event.*;
import com.tourapp.tour.acctpay.db.event.*;
import com.tourapp.model.tour.product.tour.db.*;
import com.tourapp.model.tour.product.tour.schedule.db.*;
import com.tourapp.tour.base.field.*;
import org.jbundle.main.db.base.*;
import com.tourapp.tour.product.air.db.*;
import com.tourapp.tour.product.car.db.*;
import com.tourapp.tour.product.trans.db.*;
import com.tourapp.tour.product.cruise.db.*;
import com.tourapp.tour.product.item.db.*;
import com.tourapp.model.tour.booking.db.*;

/**
 *  Tour - Tour information.
 */
public class Tour extends Job
     implements TourModel
{
    private static final long serialVersionUID = 1L;

    protected TourEventSchedule m_recTourEventSchedule = null;
    /**
     * Default constructor.
     */
    public Tour()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Tour(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        m_recTourEventSchedule = null;
        super.init(screen);
    }
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(TOUR_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Tour";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "booking";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.REMOTE | DBConstants.USER_DATA;
    }
    /**
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(TOUR_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(TOUR_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
        {
            field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setHidden(true);
        }
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (iFieldSeq == 3)
            field = new StringField(this, DESCRIPTION, 50, null, null);
        if (iFieldSeq == 4)
            field = new StringField(this, CODE, 20, null, null);
        if (iFieldSeq == 5)
            field = new TourHeaderField(this, TOUR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new DateField(this, DEPARTURE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new TourStatusSummaryField(this, TOUR_STATUS_SUMMARY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new TourStatusField(this, TOUR_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new BooleanField(this, MANUAL_TOUR_STATUS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
        {
            field = new ShortField(this, MIN_TO_OP, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 11)
        {
            field = new AirRateField(this, AIR_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 12)
        {
            field = new AirClassField(this, AIR_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 13)
        {
            field = new HotelRateSelect(this, HOTEL_RATE_ID, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 14)
        {
            field = new HotelClassSelect(this, HOTEL_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 15)
        {
            field = new LandRateSelect(this, LAND_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 16)
            field = new LandClassSelect(this, LAND_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new ShortField(this, PMC_CUTOFF, 3, null, null);
        if (iFieldSeq == 18)
        {
            field = new CarRateSelect(this, CAR_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 19)
        {
            field = new CarClassSelect(this, CAR_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 20)
        {
            field = new TransportationRateSelect(this, TRANSPORTATION_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 21)
        {
            field = new TransportationClassSelect(this, TRANSPORTATION_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 22)
        {
            field = new CruiseRateSelect(this, CRUISE_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 23)
        {
            field = new CruiseClassSelect(this, CRUISE_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 24)
            field = new ItemRateSelect(this, ITEM_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 25)
            field = new ItemClassSelect(this, ITEM_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 26)
            field = new ShortField(this, FOCS, 2, null, null);
        if (iFieldSeq == 27)
        {
            field = new StringField(this, FILE_NO, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 28)
            field = new DateField(this, FINALIZE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 29)
            field = new DateField(this, ORDER_COMP_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 30)
            field = new DateField(this, CLOSED_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 31)
            field = new DateField(this, FINAL_DOC_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 32)
            field = new DateField(this, TICKET_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 33)
            field = new DateField(this, SP_1_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 34)
            field = new DateField(this, SP_2_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 35)
        {
            field = new BooleanField(this, FINALIZED, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 36)
            field = new BooleanField(this, ORDER_COMPONENTS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 37)
            field = new BooleanField(this, CLOSED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 38)
        {
            field = new BooleanField(this, FINAL_DOCS, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 39)
        {
            field = new BooleanField(this, TICKETS, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 40)
            field = new BooleanField(this, SP_1, 1, null, null);
        if (iFieldSeq == 41)
            field = new BooleanField(this, SP_2, 1, null, null);
        if (iFieldSeq == 42)
            field = new BooleanField(this, SERV_CONF, 1, null, null);
        if (iFieldSeq == 43)
            field = new BooleanField(this, DEPARTED, 1, null, null);
        if (iFieldSeq == 44)
            field = new BooleanField(this, CANCELLED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 45)
            field = new DateField(this, NEXT_EVENT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 46)
            field = new TourEventField(this, TOUR_EVENT_ID, 1, null, null);
        if (iFieldSeq == 47)
            field = new PropertiesField(this, PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ID");
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Code");
            keyArea.addKeyField(CODE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TourHeaderID");
            keyArea.addKeyField(TOUR_HEADER_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(DEPARTURE_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DepartureDate");
            keyArea.addKeyField(DEPARTURE_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 4)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "NextEventDate");
            keyArea.addKeyField(NEXT_EVENT_DATE, DBConstants.ASCENDING);
            keyArea.addKeyField(TOUR_EVENT_ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 5)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(DESCRIPTION, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        
        this.addListener(new NoDeleteModifyHandler(true, false));
        
        this.getField(Tour.FINALIZED).addListener(new FinalizeHandler(null));
        this.getField(Tour.ORDER_COMPONENTS).addListener(new TourOrderHandler(null));
        this.getField(Tour.CANCELLED).addListener(new TourOrderHandler(null));
        
        this.getField(Tour.TOUR_STATUS_SUMMARY).addListener(new UpdateTourStatusSummaryHandler(null));
        ((TourStatusField)this.getField(Tour.TOUR_STATUS_ID)).getIconField(null).addListener(new TourStatusUpdateHandler(null));
        
        this.addActionListeners();
    }
    /**
     * Override this to add record listeners and filters to every screen where this is the main record.
     * @param screen The screen these listeners will be in.
     */
    public void addScreenListeners(RecordOwner screen)
    {
        super.addScreenListeners(screen);
        this.getField(Tour.SERV_CONF).setEnabled(false);
        if (this.getField(Tour.MANUAL_TOUR_STATUS).getListener(DisableOnFieldHandler.class) == null)
            this.getField(Tour.MANUAL_TOUR_STATUS).addListener(new DisableOnFieldHandler(this.getField(Tour.TOUR_STATUS_ID), BooleanField.YES, false));
    }
    /**
     * AddActionListeners Method.
     */
    public void addActionListeners()
    {
        this.getField(Tour.DEPARTURE_DATE).addListener(new FieldDataScratchHandler(null));
        
        this.getField(Tour.ORDER_COMP_DATE).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.FINALIZE_DATE).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.CLOSED_DATE).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.FINAL_DOC_DATE).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.TICKET_DATE).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.SP_1_DATE).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.SP_2_DATE).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.DEPARTURE_DATE).addListener(new CalcActionDateHandler(null));
        
        this.getField(Tour.ORDER_COMPONENTS).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.FINALIZED).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.CLOSED).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.FINAL_DOCS).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.TICKETS).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.SP_1).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.SP_2).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.DEPARTED).addListener(new CalcActionDateHandler(null));
        
        this.getField(Tour.TOUR_STATUS_ID).addListener(new TourStatusEventHandler(null));
        
        this.getField(Tour.ORDER_COMPONENTS).addListener(new TourEventHandler(TourEvent.ORDER_COMPONENTS));
        this.getField(Tour.FINALIZED).addListener(new TourEventHandler(TourEvent.FINALIZATION));
        this.getField(Tour.CLOSED).addListener(new TourEventHandler(TourEvent.TOUR_CLOSED));
        this.getField(Tour.FINAL_DOCS).addListener(new TourEventHandler(TourEvent.FINAL_DOCS));
        this.getField(Tour.TICKETS).addListener(new TourEventHandler(TourEvent.TICKETING));
        this.getField(Tour.SP_1).addListener(new TourEventHandler(TourEvent.SPECIAL_1));
        this.getField(Tour.SP_2).addListener(new TourEventHandler(TourEvent.SPECIAL_2));
        this.getField(Tour.DEPARTED).addListener(new TourEventHandler(TourEvent.DEPARTURE));
        
        this.getField(Tour.CANCELLED).addListener(new TourEventHandler(TourEvent.TOUR_CANCELLED));
        
        this.getField(Tour.SERV_CONF).addListener(new TourEventHandler(TourEvent.SERVICES_CONFIRMED));
    }
    /**
     * Given the tour header and the departure date, setup or locate
     * the correct tour.
     */
    public int setupTourFromHeader(TourHeaderModel recTourHeader, Field fldDepDate, String strCode, String strDescription)
    {
        if ((recTourHeader.getEditMode() != DBConstants.EDIT_CURRENT)
            && (recTourHeader.getEditMode() != DBConstants.EDIT_IN_PROGRESS))
                return DBConstants.ERROR_RETURN;    // Must pass in a valid tour header!
        boolean bNewTour = false;
        if (recTourHeader.getField(TourHeader.TOUR_SERIES).getState() == false)
            bNewTour = true;    // New tour
        if (TOUR_SCREEN_CLASS.equalsIgnoreCase(this.getRecordOwner().getClass().toString()))
        { // Special case - series entry
            if (bNewTour)
                return DBConstants.ERROR_RETURN;    // Can't enter series tour for a non-series header
            bNewTour = true;    // Special case - Tour entry for a tour series header
        }
        if (bNewTour)
        { // Tour Header - Create this booking's tour from the tour header
            return this.setupNewTour((Record)recTourHeader, (BaseField)fldDepDate, strCode, strDescription);
        }
        else
        { // Series - Get the booking's tour from the tour header
            return this.lookupTour((Record)recTourHeader, (BaseField)fldDepDate);
        }
    }
    /**
     * LookupTour Method.
     */
    public int lookupTour(Record recTourHeader, BaseField fldDepartureDate)
    {
        FileListener subBehavior = null;
        BaseField fldTemp = null;
        boolean[] rgbRecordListeners = null;
        Object[] rgobjEnabledFields = null;
        int iOldOrder = this.getDefaultOrder();
        try {
            // There are probably a million listeners on this record, so disable them first.
            rgbRecordListeners = this.setEnableListeners(false);
            rgobjEnabledFields = this.setEnableFieldListeners(false);
        
            if (fldDepartureDate == null)
                fldDepartureDate = this.getField(Tour.DEPARTURE_DATE);
            if (fldDepartureDate.getRecord() == this)
            {
                fldTemp = new DateField(null, DBConstants.BLANK, DBConstants.DEFAULT_FIELD_LENGTH, DBConstants.BLANK, null);
                fldTemp.moveFieldToThis(fldDepartureDate, DBConstants.DONT_DISPLAY, DBConstants.INIT_MOVE);
                fldDepartureDate = fldTemp;
            }
        
            this.addNew();
            this.setKeyArea(Tour.TOUR_HEADER_ID_KEY);
            subBehavior = new SubFileFilter(recTourHeader.getField(TourHeader.ID), Tour.TOUR_HEADER_ID, fldDepartureDate, Tour.DEPARTURE_DATE, null, null);
            this.addListener(subBehavior);
        
            this.close();
            if (this.hasNext())
            {
                this.next();
                // Restore the state before I started
                this.removeListener(subBehavior, true);
                subBehavior = null;
                if (rgbRecordListeners != null)
                    this.setEnableListeners(rgbRecordListeners);
                rgbRecordListeners = null;
                if (rgobjEnabledFields != null)
                    this.setEnableFieldListeners(rgobjEnabledFields);
                rgobjEnabledFields = null;
        
                this.setKeyArea(Tour.ID_KEY);   // Re-read and do all listeners.
                this.seek(DBConstants.EQUALS);
                this.edit();
                this.calcTourDates(recTourHeader); // If change, resched all
            }
            else
            {
                if (this.getTask() != null)
                    return this.getTask().setLastError("A Series tour does not exist for this departure date");
                else
                    return DBConstants.ERROR_RETURN;
            }
        } catch (DBException ex)   {
            ex.printStackTrace();
            return DBConstants.ERROR_RETURN;
        } finally { // Restore the state
            this.setKeyArea(iOldOrder);
            if (subBehavior != null)
                this.removeListener(subBehavior, true);
            subBehavior = null;
            if (fldTemp != null)
                fldTemp.free();
            fldTemp = null;
            if (rgbRecordListeners != null)
                this.setEnableListeners(rgbRecordListeners);
            rgbRecordListeners = null;
            if (rgobjEnabledFields != null)
                this.setEnableFieldListeners(rgobjEnabledFields);
            rgobjEnabledFields = null;
        }
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * From this tour header and departure date, create a new tour.
     */
    public int setupNewTour(Record recTourHeader, BaseField fldDepDate, String strCode, String strDescription)
    {
        boolean[] rgbRecordListeners = null;
        Object[] rgobjEnabledFields = null;
        int iOldOrder = this.getDefaultOrder();
        try {
            // There are probably a million listeners on this record, so disable them first.
            rgbRecordListeners = this.setEnableListeners(false);
            rgobjEnabledFields = this.setEnableFieldListeners(false);
        
            Calendar calDepDate = null;
            if (fldDepDate instanceof DateTimeField)
                calDepDate = ((DateTimeField)fldDepDate).getCalendar();
            if (calDepDate == null)
                calDepDate = ((DateTimeField)this.getField(Tour.DEPARTURE_DATE)).getCalendar();
        
            if ((this.getOpenMode() & DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY) != DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY)
                this.setOpenMode(this.getOpenMode() | DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
            this.addNew();
            ((DateTimeField)this.getField(Tour.DEPARTURE_DATE)).setCalendar(calDepDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            if (strCode == null)
                strCode = DBConstants.BLANK;
            this.getField(Tour.CODE).setString(strCode, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            if ((strDescription == null) || (strDescription.length() == 0))
                strDescription = recTourHeader.getField(TourHeader.DESCRIPTION).toString() + " - " + fldDepDate.toString();
            this.getField(Tour.DESCRIPTION).setString(strDescription, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            this.getField(Tour.TOUR_HEADER_ID).moveFieldToThis(recTourHeader.getField(TourHeader.ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            TourClass recTourClass = (TourClass)((ReferenceField)recTourHeader.getField(TourHeader.TOUR_CLASS_ID)).getReference();
            recTourClass.fixBasedFields();
            this.moveTourClassInfo(recTourClass);
        
            if (rgbRecordListeners != null)
                this.setEnableListeners(rgbRecordListeners);
            rgbRecordListeners = null;
            if (rgobjEnabledFields != null)
                this.setEnableFieldListeners(rgobjEnabledFields);
            rgobjEnabledFields = null;
        
            this.calcTourDates(recTourHeader);
        
            this.add();
            Object bookmark = this.getLastModified(DBConstants.BOOKMARK_HANDLE);
            this.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE);
            this.edit();
        } catch (DBException ex)    {
            ex.printStackTrace();
            return DBConstants.ERROR_RETURN;
        } finally { // Restore the state
            this.setKeyArea(iOldOrder);
            if (rgbRecordListeners != null)
                this.setEnableListeners(rgbRecordListeners);
            rgbRecordListeners = null;
            if (rgobjEnabledFields != null)
                this.setEnableFieldListeners(rgobjEnabledFields);
            rgobjEnabledFields = null;
        }   
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * Move this tour class information to this tour.
     */
    public void moveTourClassInfo(TourClass recTourClass)
    {
        this.getField(Tour.TOUR_STATUS_ID).moveFieldToThis(recTourClass.getField(TourClass.INITIAL_TOUR_STATUS_ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.AIR_RATE_ID).moveFieldToThis(recTourClass.getField(TourClass.AIR_RATE_ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.AIR_CLASS_ID).moveFieldToThis(recTourClass.getField(TourClass.AIR_CLASS_ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.HOTEL_RATE_ID).moveFieldToThis(recTourClass.getField(TourClass.HOTEL_RATE_ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.HOTEL_CLASS_ID).moveFieldToThis(recTourClass.getField(TourClass.HOTEL_CLASS_ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.LAND_RATE_ID).moveFieldToThis(recTourClass.getField(TourClass.LAND_RATE_ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.LAND_CLASS_ID).moveFieldToThis(recTourClass.getField(TourClass.LAND_CLASS_ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.PMC_CUTOFF).moveFieldToThis(recTourClass.getField(TourClass.PMC_CUTOFF), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.CAR_RATE_ID).moveFieldToThis(recTourClass.getField(TourClass.CAR_RATE_ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.CAR_CLASS_ID).moveFieldToThis(recTourClass.getField(TourClass.CAR_CLASS_ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.TRANSPORTATION_RATE_ID).moveFieldToThis(recTourClass.getField(TourClass.TRANSPORTATION_RATE_ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.TRANSPORTATION_CLASS_ID).moveFieldToThis(recTourClass.getField(TourClass.TRANSPORTATION_CLASS_ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.CRUISE_RATE_ID).moveFieldToThis(recTourClass.getField(TourClass.CRUISE_RATE_ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.CRUISE_CLASS_ID).moveFieldToThis(recTourClass.getField(TourClass.CRUISE_CLASS_ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.ITEM_RATE_ID).moveFieldToThis(recTourClass.getField(TourClass.ITEM_RATE_ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.ITEM_CLASS_ID).moveFieldToThis(recTourClass.getField(TourClass.ITEM_CLASS_ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
    }
    /**
     * CalcTourDates Method.
     */
    public void calcTourDates(Rec recTourHeader)
    {
        if (recTourHeader == null)
            recTourHeader = ((ReferenceField)this.getField(Tour.TOUR_HEADER_ID)).getReference();
        Record recTourClass = ((ReferenceField)recTourHeader.getField(TourHeader.TOUR_CLASS_ID)).getReference();
        Calendar calDepartureDate = ((DateTimeField)this.getField(Tour.DEPARTURE_DATE)).getCalendar();
        
        Calendar calTargetDate = (Calendar)calDepartureDate.clone();
        calTargetDate.add(Calendar.DATE, -(int)recTourClass.getField(TourClass.TICKET_DAYS).getValue());
        ((DateTimeField)this.getField(Tour.TICKET_DATE)).setCalendar(calTargetDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        calTargetDate = (Calendar)calDepartureDate.clone();
        calTargetDate.add(Calendar.DATE, -(int)recTourClass.getField(TourClass.SP_1_DAYS).getValue());
        ((DateTimeField)this.getField(Tour.SP_1_DATE)).setCalendar(calTargetDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        calTargetDate = (Calendar)calDepartureDate.clone();
        calTargetDate.add(Calendar.DATE, -(int)recTourClass.getField(TourClass.SP_2_DAYS).getValue());
        ((DateTimeField)this.getField(Tour.SP_2_DATE)).setCalendar(calTargetDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        calTargetDate = (Calendar)calDepartureDate.clone();
        calTargetDate.add(Calendar.DATE, -(int)recTourClass.getField(TourClass.FINAL_DOC_DAYS).getValue());
        ((DateTimeField)this.getField(Tour.FINAL_DOC_DATE)).setCalendar(calTargetDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        calTargetDate = (Calendar)calDepartureDate.clone();
        calTargetDate.add(Calendar.DATE, -(int)recTourClass.getField(TourClass.ORDER_COMP_DAYS).getValue());
        ((DateTimeField)this.getField(Tour.ORDER_COMP_DATE)).setCalendar(calTargetDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        calTargetDate = (Calendar)calDepartureDate.clone();
        calTargetDate.add(Calendar.DATE, -(int)recTourClass.getField(TourClass.FINALIZE_DAYS).getValue());
        ((DateTimeField)this.getField(Tour.FINALIZE_DATE)).setCalendar(calTargetDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        calTargetDate = (Calendar)calDepartureDate.clone();
        calTargetDate.add(Calendar.DATE, -(int)recTourClass.getField(TourClass.CLOSED_DAYS).getValue());
        ((DateTimeField)this.getField(Tour.CLOSED_DATE)).setCalendar(calTargetDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        this.getField(Tour.CLOSED).setState(false);
        this.getField(Tour.FINALIZED).setState(false);
        this.getField(Tour.ORDER_COMPONENTS).setState(false);
        this.getField(Tour.FINAL_DOCS).setState(false);
        this.getField(Tour.TICKETS).setState(false);
        this.getField(Tour.SP_1).setState(false);
        this.getField(Tour.SP_2).setState(false);
        this.getField(Tour.SERV_CONF).setState(false);
        this.getField(Tour.DEPARTED).setState(false);
    }
    /**
     * Update all the vouchers for this tour.
     */
    public int updateTourApTrx(BookingDetail recBookingDetail, ApTrx recApTrx)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        BookingDetail recBookingDetailNew = null;
        ApTrx recApTrxNew = null;
        RecordOwner recordOwner = this.findRecordOwner();
        if (recBookingDetail == null)
        {
            if (recordOwner != null)
                if (recordOwner.getRecord(Booking.BOOKING_FILE) != null)
                {
                }
            recBookingDetail = new BookingDetail(recordOwner);
            recBookingDetailNew = recBookingDetail;
        }
        recBookingDetail.setKeyArea(BookingDetail.TOUR_ID_KEY);   
        recBookingDetail.addListener(new SubFileFilter(this));
        
        if (recApTrx == null)
        {
            recApTrx = new ApTrx(recordOwner);
            recApTrxNew = recApTrx;
        }
        recApTrx.setKeyArea(ApTrx.TOUR_ID_KEY);
        recApTrx.addListener(new SubFileFilter(this));
        recApTrx.addListener(new UpdateDepEstHandler(null));
        
        ApTrx recApTrxAdd = new ApTrx(recordOwner);    // This one I use for new ApTrxs.
        recApTrxAdd.addListener(new UpdateDepEstHandler(null));
        
        int iOldOpenMode = this.getOpenMode();
        try {
            this.setOpenMode(iOldOpenMode & ~DBConstants.OPEN_READ_ONLY);   // Allow write (I don't know where this file has been)
            this.writeAndRefresh();
        
            recBookingDetail.close();
            recApTrx.close();
            BaseTable tblBookingDetail = recBookingDetail.getTable();
            BaseTable tblApTrx = recApTrx.getTable();
            recBookingDetail = (BookingDetail)tblBookingDetail.next();
            recApTrx = (ApTrx)tblApTrx.next();
        
            while ((recApTrx != null) || (recBookingDetail != null))
            {
                boolean bAddNewVoucher = false;
                int intProductTypeID = 0;
                Vendor recVendor = null;
                if ((recApTrx == null)
                    || ((recBookingDetail != null) && (recBookingDetail.getField(BookingDetail.VENDOR_ID).compareTo(recApTrx.getField(ApTrx.VENDOR_ID)) > 0)))
                        bAddNewVoucher = true;
                else
                {    // (recApTrx != null)
                    if ((recBookingDetail == null)
                        || ((recApTrx != null) && (recBookingDetail.getField(BookingDetail.VENDOR_ID).compareTo(recApTrx.getField(ApTrx.VENDOR_ID)) < 0)))
                            bAddNewVoucher = false;
                    else
                    {
                        recVendor = (Vendor)((ReferenceField)recApTrx.getField(ApTrx.VENDOR_ID)).getReference();
                        if (OperationTypeField.ALL_TOGETHER_CODE.equalsIgnoreCase(recVendor.getField(Vendor.OPERATION_TYPE_CODE).toString()))
                            bAddNewVoucher = false;
                        else if (OperationTypeField.INDIVIDUALLY_CODE.equalsIgnoreCase(recVendor.getField(Vendor.OPERATION_TYPE_CODE).toString()))
                        {
                            if (recBookingDetail.getField(BookingDetail.AP_TRX_ID).compareTo(recApTrx.getField(ApTrx.ID)) != 0)
                                bAddNewVoucher = true;
                            else
                                bAddNewVoucher = false;
                        }
                        else // if (OperationTypeField.LIKE_TOGETHER_CODE.equalsIgnoreCase(recVendor.getField(Vendor.OPERATION_TYPE_CODE).toString()))
                        {
                            if (recBookingDetail.getField(BookingDetail.PRODUCT_TYPE_ID).compareTo(recApTrx.getField(ApTrx.PRODUCT_TYPE_ID)) > 0)
                                bAddNewVoucher = true;
                            else
                                bAddNewVoucher = false;
                        }
                    }
                }
                if (recBookingDetail != null)
                    if (recVendor == null)
                        recVendor = (Vendor)((ReferenceField)recBookingDetail.getField(BookingDetail.VENDOR_ID)).getReference();
                if (recVendor != null)
                    if ((OperationTypeField.LIKE_TOGETHER_CODE.equalsIgnoreCase(recVendor.getField(Vendor.OPERATION_TYPE_CODE).toString()))
                        || (OperationTypeField.INDIVIDUALLY_CODE.equalsIgnoreCase(recVendor.getField(Vendor.OPERATION_TYPE_CODE).toString())))
                            intProductTypeID = (int)recBookingDetail.getField(BookingDetail.PRODUCT_TYPE_ID).getValue();
                if (bAddNewVoucher)
                { // Branch 1 - No A/P detail to match product detail (Create A/P detail & U/D all product detail)
                    ApTrx recNewApTrx = recApTrxAdd.addNewApTrx(this.getField(Tour.ID), recVendor, intProductTypeID);
                    iErrorCode = recNewApTrx.updateThisApTrx(tblBookingDetail, this, recVendor, intProductTypeID);
                }
                else
                { // Branch 2 - First product detail matches A/P detail (Scan product detail to make sure all are here)
                    iErrorCode = recApTrx.updateThisApTrx(tblBookingDetail, this, recVendor, intProductTypeID);
                    recApTrx = (ApTrx)tblApTrx.next();
                }
                // No need to call tblCustSaleDetail.next(), because xxxNewVoucher has already
                recBookingDetail = null;
                if ((tblBookingDetail.getCurrentTable() != null)
                    && (tblBookingDetail.getCurrentTable().getRecord().getEditMode() != DBConstants.EDIT_ADD)
                    && (tblBookingDetail.getCurrentTable().getRecord().getEditMode() != DBConstants.EDIT_NONE))
                        recBookingDetail = (BookingDetail)tblBookingDetail.getCurrentTable().getRecord();
            }
        } catch (DBException ex)   {
            ex.printStackTrace();
        } finally {
            this.setOpenMode(iOldOpenMode);   // Set it back
            if (recBookingDetailNew != null)
                recBookingDetailNew.free();
            if (recApTrxNew != null)
                recApTrxNew.free();
            if (recApTrxAdd != null)
                recApTrxAdd.free();
            recBookingDetailNew = null;
            recApTrxNew = null;
            recApTrxAdd = null;
        }
        return iErrorCode;
    }
    /**
     * Order all the components for this tour.
     */
    public void orderAllComponents()
    {
        if (this.getField(Tour.ORDER_COMPONENTS).getState() == false)
            return;
        BookingDetail recBookingDetail = new BookingDetail(this.findRecordOwner());
        try {
            recBookingDetail.addListener(new SubFileFilter(this));
            while (recBookingDetail.hasNext())
            {
                recBookingDetail.next();
                Record recBookingDetailCurrent = recBookingDetail.getTable().getCurrentTable().getRecord();
                recBookingDetailCurrent.edit();
                recBookingDetailCurrent.getField(BookingDetail.PRODUCT_STATUS_REQUEST).setData(Boolean.TRUE);
                if (recBookingDetailCurrent.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
                    recBookingDetailCurrent.set();   // Possible that the listeners re-wrote this record already.
            }
        } catch (DBException ex) {
            recBookingDetail.free();
        }
    }
    /**
     * Convenience method.
     */
    public TourEventScheduleModel getTourEventSchedule()
    {
        if (m_recTourEventSchedule == null)
        {
            m_recTourEventSchedule = new TourEventSchedule(this.findRecordOwner());
            this.addListener(new FreeOnFreeHandler(m_recTourEventSchedule));
        }
        return m_recTourEventSchedule;
    }

}
