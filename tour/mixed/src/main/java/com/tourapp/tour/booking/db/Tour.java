/**
 * @(#)Tour.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
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
import com.tourapp.tour.booking.lookup.*;
import com.tourapp.tour.booking.detail.event.*;
import com.tourapp.tour.product.tour.other.screen.*;
import com.tourapp.tour.product.tour.schedule.db.*;
import com.tourapp.tour.booking.db.event.*;
import com.tourapp.tour.acctpay.screen.findepest.*;
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

    //public static final int kID = kID;
    //public static final int kDescription = kDescription;
    public static final int kCode = kJobLastField + 1;
    public static final int kTourHeaderID = kCode + 1;
    public static final int kDepartureDate = kTourHeaderID + 1;
    public static final int kTourStatusSummary = kDepartureDate + 1;
    public static final int kTourStatusID = kTourStatusSummary + 1;
    public static final int kManualTourStatus = kTourStatusID + 1;
    public static final int kMinToOp = kManualTourStatus + 1;
    public static final int kAirRateID = kMinToOp + 1;
    public static final int kAirClassID = kAirRateID + 1;
    public static final int kHotelRateID = kAirClassID + 1;
    public static final int kHotelClassID = kHotelRateID + 1;
    public static final int kLandRateID = kHotelClassID + 1;
    public static final int kLandClassID = kLandRateID + 1;
    public static final int kPMCCutoff = kLandClassID + 1;
    public static final int kCarRateID = kPMCCutoff + 1;
    public static final int kCarClassID = kCarRateID + 1;
    public static final int kTransportationRateID = kCarClassID + 1;
    public static final int kTransportationClassID = kTransportationRateID + 1;
    public static final int kCruiseRateID = kTransportationClassID + 1;
    public static final int kCruiseClassID = kCruiseRateID + 1;
    public static final int kItemRateID = kCruiseClassID + 1;
    public static final int kItemClassID = kItemRateID + 1;
    public static final int kFocs = kItemClassID + 1;
    public static final int kFileNo = kFocs + 1;
    public static final int kFinalizeDate = kFileNo + 1;
    public static final int kOrderCompDate = kFinalizeDate + 1;
    public static final int kClosedDate = kOrderCompDate + 1;
    public static final int kFinalDocDate = kClosedDate + 1;
    public static final int kTicketDate = kFinalDocDate + 1;
    public static final int kSp1Date = kTicketDate + 1;
    public static final int kSp2Date = kSp1Date + 1;
    public static final int kFinalized = kSp2Date + 1;
    public static final int kOrderComponents = kFinalized + 1;
    public static final int kClosed = kOrderComponents + 1;
    public static final int kFinalDocs = kClosed + 1;
    public static final int kTickets = kFinalDocs + 1;
    public static final int kSp1 = kTickets + 1;
    public static final int kSp2 = kSp1 + 1;
    public static final int kServConf = kSp2 + 1;
    public static final int kDeparted = kServConf + 1;
    public static final int kCancelled = kDeparted + 1;
    public static final int kNextEventDate = kCancelled + 1;
    public static final int kTourEventID = kNextEventDate + 1;
    public static final int kProperties = kTourEventID + 1;
    public static final int kTourLastField = kProperties;
    public static final int kTourFields = kProperties - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCodeKey = kIDKey + 1;
    public static final int kTourHeaderIDKey = kCodeKey + 1;
    public static final int kDepartureDateKey = kTourHeaderIDKey + 1;
    public static final int kNextEventDateKey = kDepartureDateKey + 1;
    public static final int kDescriptionKey = kNextEventDateKey + 1;
    public static final int kTourLastKey = kDescriptionKey;
    public static final int kTourKeys = kDescriptionKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kTourFile = "Tour";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTourFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = BaseScreen.makeNewScreen(TOUR_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = BaseScreen.makeNewScreen(TOUR_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kCode)
            field = new StringField(this, "Code", 20, null, null);
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 50, null, null);
        if (iFieldSeq == kTourHeaderID)
            field = new TourHeaderField(this, "TourHeaderID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDepartureDate)
            field = new DateField(this, "DepartureDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourStatusSummary)
            field = new TourStatusSummaryField(this, "TourStatusSummary", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourStatusID)
            field = new TourStatusField(this, "TourStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kManualTourStatus)
            field = new BooleanField(this, "ManualTourStatus", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMinToOp)
        {
            field = new ShortField(this, "MinToOp", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAirRateID)
        {
            field = new AirRateField(this, "AirRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAirClassID)
        {
            field = new AirClassField(this, "AirClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kHotelRateID)
        {
            field = new HotelRateSelect(this, "HotelRateID", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kHotelClassID)
        {
            field = new HotelClassSelect(this, "HotelClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kLandRateID)
        {
            field = new LandRateSelect(this, "LandRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kLandClassID)
            field = new LandClassSelect(this, "LandClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPMCCutoff)
            field = new ShortField(this, "PMCCutoff", 3, null, null);
        if (iFieldSeq == kCarRateID)
        {
            field = new CarRateSelect(this, "CarRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCarClassID)
        {
            field = new CarClassSelect(this, "CarClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTransportationRateID)
        {
            field = new TransportationRateSelect(this, "TransportationRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTransportationClassID)
        {
            field = new TransportationClassSelect(this, "TransportationClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCruiseRateID)
        {
            field = new CruiseRateSelect(this, "CruiseRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCruiseClassID)
        {
            field = new CruiseClassSelect(this, "CruiseClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kItemRateID)
            field = new ItemRateSelect(this, "ItemRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kItemClassID)
            field = new ItemClassSelect(this, "ItemClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFocs)
            field = new ShortField(this, "Focs", 2, null, null);
        if (iFieldSeq == kFileNo)
        {
            field = new StringField(this, "FileNo", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFinalizeDate)
            field = new DateField(this, "FinalizeDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kOrderCompDate)
            field = new DateField(this, "OrderCompDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kClosedDate)
            field = new DateField(this, "ClosedDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFinalDocDate)
            field = new DateField(this, "FinalDocDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTicketDate)
            field = new DateField(this, "TicketDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSp1Date)
            field = new DateField(this, "Sp1Date", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSp2Date)
            field = new DateField(this, "Sp2Date", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFinalized)
        {
            field = new BooleanField(this, "Finalized", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kOrderComponents)
            field = new BooleanField(this, "OrderComponents", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kClosed)
            field = new BooleanField(this, "Closed", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFinalDocs)
        {
            field = new BooleanField(this, "FinalDocs", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTickets)
        {
            field = new BooleanField(this, "Tickets", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSp1)
            field = new BooleanField(this, "Sp1", 1, null, null);
        if (iFieldSeq == kSp2)
            field = new BooleanField(this, "Sp2", 1, null, null);
        if (iFieldSeq == kServConf)
            field = new BooleanField(this, "ServConf", 1, null, null);
        if (iFieldSeq == kDeparted)
            field = new BooleanField(this, "Departed", 1, null, null);
        if (iFieldSeq == kCancelled)
            field = new BooleanField(this, "Cancelled", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNextEventDate)
            field = new DateField(this, "NextEventDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourEventID)
            field = new TourEventField(this, "TourEventID", 1, null, null);
        if (iFieldSeq == kProperties)
            field = new PropertiesField(this, "Properties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTourLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == kIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PrimaryKey");
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Code");
            keyArea.addKeyField(kCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTourHeaderIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TourHeaderID");
            keyArea.addKeyField(kTourHeaderID, DBConstants.ASCENDING);
            keyArea.addKeyField(kDepartureDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kDepartureDateKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "DepartureDate");
            keyArea.addKeyField(kDepartureDate, DBConstants.ASCENDING);
        }
        if (iKeyArea == kNextEventDateKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "NextEventDate");
            keyArea.addKeyField(kNextEventDate, DBConstants.ASCENDING);
            keyArea.addKeyField(kTourEventID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kDescriptionKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(kDescription, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kTourLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTourLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        
        this.addListener(new NoDeleteModifyHandler(true, false));
        
        this.getField(Tour.kFinalized).addListener(new FinalizeHandler(null));
        this.getField(Tour.kOrderComponents).addListener(new TourOrderHandler(null));
        this.getField(Tour.kCancelled).addListener(new TourOrderHandler(null));
        
        this.getField(Tour.kTourStatusSummary).addListener(new UpdateTourStatusSummaryHandler(null));
        ((TourStatusField)this.getField(Tour.kTourStatusID)).getIconField(null).addListener(new TourStatusUpdateHandler(null));
        
        this.addActionListeners();
    }
    /**
     * Override this to add record listeners and filters to every screen where this is the main record.
     * @param screen The screen these listeners will be in.
     */
    public void addScreenListeners(RecordOwner screen)
    {
        super.addScreenListeners(screen);
        this.getField(Tour.kServConf).setEnabled(false);
        if (this.getField(Tour.kManualTourStatus).getListener(DisableOnFieldHandler.class) == null)
            this.getField(Tour.kManualTourStatus).addListener(new DisableOnFieldHandler(this.getField(Tour.kTourStatusID), BooleanField.YES, false));
    }
    /**
     * AddActionListeners Method.
     */
    public void addActionListeners()
    {
        this.getField(Tour.kDepartureDate).addListener(new FieldDataScratchHandler(null));
        
        this.getField(Tour.kOrderCompDate).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.kFinalizeDate).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.kClosedDate).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.kFinalDocDate).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.kTicketDate).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.kSp1Date).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.kSp2Date).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.kDepartureDate).addListener(new CalcActionDateHandler(null));
        
        this.getField(Tour.kOrderComponents).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.kFinalized).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.kClosed).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.kFinalDocs).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.kTickets).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.kSp1).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.kSp2).addListener(new CalcActionDateHandler(null));
        this.getField(Tour.kDeparted).addListener(new CalcActionDateHandler(null));
        
        this.getField(Tour.kTourStatusID).addListener(new TourStatusEventHandler(null));
        
        this.getField(Tour.kOrderComponents).addListener(new TourEventHandler(TourEvent.ORDER_COMPONENTS));
        this.getField(Tour.kFinalized).addListener(new TourEventHandler(TourEvent.FINALIZATION));
        this.getField(Tour.kClosed).addListener(new TourEventHandler(TourEvent.TOUR_CLOSED));
        this.getField(Tour.kFinalDocs).addListener(new TourEventHandler(TourEvent.FINAL_DOCS));
        this.getField(Tour.kTickets).addListener(new TourEventHandler(TourEvent.TICKETING));
        this.getField(Tour.kSp1).addListener(new TourEventHandler(TourEvent.SPECIAL_1));
        this.getField(Tour.kSp2).addListener(new TourEventHandler(TourEvent.SPECIAL_2));
        this.getField(Tour.kDeparted).addListener(new TourEventHandler(TourEvent.DEPARTURE));
        
        this.getField(Tour.kCancelled).addListener(new TourEventHandler(TourEvent.TOUR_CANCELLED));
        
        this.getField(Tour.kServConf).addListener(new TourEventHandler(TourEvent.SERVICES_CONFIRMED));
    }
    /**
     * Given the tour header and the departure date, setup or locate
     * the correct tour.
     */
    public int setupTourFromHeader(TourHeader recTourHeader, BaseField fldDepDate, String strCode, String strDescription)
    {
        if ((recTourHeader.getEditMode() != DBConstants.EDIT_CURRENT)
            && (recTourHeader.getEditMode() != DBConstants.EDIT_IN_PROGRESS))
                return DBConstants.ERROR_RETURN;    // Must pass in a valid tour header!
        boolean bNewTour = false;
        if (recTourHeader.getField(TourHeader.kTourSeries).getState() == false)
            bNewTour = true;    // New tour
        if (this.getRecordOwner() instanceof TourScreen)
        { // Special case - series entry
            if (bNewTour)
                return DBConstants.ERROR_RETURN;    // Can't enter series tour for a non-series header
            bNewTour = true;    // Special case - Tour entry for a tour series header
        }
        if (bNewTour)
        { // Tour Header - Create this booking's tour from the tour header
            return this.setupNewTour(recTourHeader, fldDepDate, strCode, strDescription);
        }
        else
        { // Series - Get the booking's tour from the tour header
            return this.lookupTour(recTourHeader, fldDepDate);
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
                fldDepartureDate = this.getField(Tour.kDepartureDate);
            if (fldDepartureDate.getRecord() == this)
            {
                fldTemp = new DateField(null, DBConstants.BLANK, DBConstants.DEFAULT_FIELD_LENGTH, DBConstants.BLANK, null);
                fldTemp.moveFieldToThis(fldDepartureDate, DBConstants.DONT_DISPLAY, DBConstants.INIT_MOVE);
                fldDepartureDate = fldTemp;
            }
        
            this.addNew();
            this.setKeyArea(Tour.kTourHeaderIDKey);
            subBehavior = new SubFileFilter(recTourHeader.getField(TourHeader.kID), Tour.kTourHeaderID, fldDepartureDate, Tour.kDepartureDate, null, -1);
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
        
                this.setKeyArea(Tour.kIDKey);   // Re-read and do all listeners.
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
                calDepDate = ((DateTimeField)this.getField(Tour.kDepartureDate)).getCalendar();
        
            if ((this.getOpenMode() & DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY) != DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY)
                this.setOpenMode(this.getOpenMode() | DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
            this.addNew();
            ((DateTimeField)this.getField(Tour.kDepartureDate)).setCalendar(calDepDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            if (strCode == null)
                strCode = DBConstants.BLANK;
            this.getField(Tour.kCode).setString(strCode, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            if ((strDescription == null) || (strDescription.length() == 0))
                strDescription = recTourHeader.getField(TourHeader.kDescription).toString() + " - " + fldDepDate.toString();
            this.getField(Tour.kDescription).setString(strDescription, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            this.getField(Tour.kTourHeaderID).moveFieldToThis(recTourHeader.getField(TourHeader.kID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            TourClass recTourClass = (TourClass)((ReferenceField)recTourHeader.getField(TourHeader.kTourClassID)).getReference();
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
        this.getField(Tour.kTourStatusID).moveFieldToThis(recTourClass.getField(TourClass.kInitialTourStatusID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.kAirRateID).moveFieldToThis(recTourClass.getField(TourClass.kAirRateID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.kAirClassID).moveFieldToThis(recTourClass.getField(TourClass.kAirClassID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.kHotelRateID).moveFieldToThis(recTourClass.getField(TourClass.kHotelRateID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.kHotelClassID).moveFieldToThis(recTourClass.getField(TourClass.kHotelClassID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.kLandRateID).moveFieldToThis(recTourClass.getField(TourClass.kLandRateID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.kLandClassID).moveFieldToThis(recTourClass.getField(TourClass.kLandClassID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.kPMCCutoff).moveFieldToThis(recTourClass.getField(TourClass.kPMCCutoff), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.kCarRateID).moveFieldToThis(recTourClass.getField(TourClass.kCarRateID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.kCarClassID).moveFieldToThis(recTourClass.getField(TourClass.kCarClassID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.kTransportationRateID).moveFieldToThis(recTourClass.getField(TourClass.kTransportationRateID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.kTransportationClassID).moveFieldToThis(recTourClass.getField(TourClass.kTransportationClassID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.kCruiseRateID).moveFieldToThis(recTourClass.getField(TourClass.kCruiseRateID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.kCruiseClassID).moveFieldToThis(recTourClass.getField(TourClass.kCruiseClassID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.kItemRateID).moveFieldToThis(recTourClass.getField(TourClass.kItemRateID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(Tour.kItemClassID).moveFieldToThis(recTourClass.getField(TourClass.kItemClassID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
    }
    /**
     * CalcTourDates Method.
     */
    public void calcTourDates(Record recTourHeader)
    {
        if (recTourHeader == null)
            recTourHeader = ((ReferenceField)this.getField(Tour.kTourHeaderID)).getReference();
        Record recTourClass = ((ReferenceField)recTourHeader.getField(TourHeader.kTourClassID)).getReference();
        Calendar calDepartureDate = ((DateTimeField)this.getField(kDepartureDate)).getCalendar();
        
        Calendar calTargetDate = (Calendar)calDepartureDate.clone();
        calTargetDate.add(Calendar.DATE, -(int)recTourClass.getField(TourClass.kTicketDays).getValue());
        ((DateTimeField)this.getField(Tour.kTicketDate)).setCalendar(calTargetDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        calTargetDate = (Calendar)calDepartureDate.clone();
        calTargetDate.add(Calendar.DATE, -(int)recTourClass.getField(TourClass.kSp1Days).getValue());
        ((DateTimeField)this.getField(Tour.kSp1Date)).setCalendar(calTargetDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        calTargetDate = (Calendar)calDepartureDate.clone();
        calTargetDate.add(Calendar.DATE, -(int)recTourClass.getField(TourClass.kSp2Days).getValue());
        ((DateTimeField)this.getField(Tour.kSp2Date)).setCalendar(calTargetDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        calTargetDate = (Calendar)calDepartureDate.clone();
        calTargetDate.add(Calendar.DATE, -(int)recTourClass.getField(TourClass.kFinalDocDays).getValue());
        ((DateTimeField)this.getField(Tour.kFinalDocDate)).setCalendar(calTargetDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        calTargetDate = (Calendar)calDepartureDate.clone();
        calTargetDate.add(Calendar.DATE, -(int)recTourClass.getField(TourClass.kOrderCompDays).getValue());
        ((DateTimeField)this.getField(Tour.kOrderCompDate)).setCalendar(calTargetDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        calTargetDate = (Calendar)calDepartureDate.clone();
        calTargetDate.add(Calendar.DATE, -(int)recTourClass.getField(TourClass.kFinalizeDays).getValue());
        ((DateTimeField)this.getField(Tour.kFinalizeDate)).setCalendar(calTargetDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        calTargetDate = (Calendar)calDepartureDate.clone();
        calTargetDate.add(Calendar.DATE, -(int)recTourClass.getField(TourClass.kClosedDays).getValue());
        ((DateTimeField)this.getField(Tour.kClosedDate)).setCalendar(calTargetDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        this.getField(Tour.kClosed).setState(false);
        this.getField(Tour.kFinalized).setState(false);
        this.getField(Tour.kOrderComponents).setState(false);
        this.getField(Tour.kFinalDocs).setState(false);
        this.getField(Tour.kTickets).setState(false);
        this.getField(Tour.kSp1).setState(false);
        this.getField(Tour.kSp2).setState(false);
        this.getField(Tour.kServConf).setState(false);
        this.getField(Tour.kDeparted).setState(false);
    }
    /**
     * Update all the vouchers for this tour.
     */
    public int updateTourApTrx(BookingDetail recBookingDetail, ApTrx recApTrx)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        BookingDetail recBookingDetailNew = null;
        ApTrx recApTrxNew = null;
        RecordOwner recordOwner = Utility.getRecordOwner(this);
        if (recBookingDetail == null)
        {
            if (recordOwner != null)
                if (recordOwner.getRecord(Booking.kBookingFile) != null)
                {
                }
            recBookingDetail = new BookingDetail(recordOwner);
            recBookingDetailNew = recBookingDetail;
        }
        recBookingDetail.setKeyArea(BookingDetail.kTourIDKey);   
        recBookingDetail.addListener(new SubFileFilter(this));
        
        if (recApTrx == null)
        {
            recApTrx = new ApTrx(recordOwner);
            recApTrxNew = recApTrx;
        }
        recApTrx.setKeyArea(ApTrx.kTourIDKey);
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
                    || ((recBookingDetail != null) && (recBookingDetail.getField(BookingDetail.kVendorID).compareTo(recApTrx.getField(ApTrx.kVendorID)) > 0)))
                        bAddNewVoucher = true;
                else
                {    // (recApTrx != null)
                    if ((recBookingDetail == null)
                        || ((recApTrx != null) && (recBookingDetail.getField(BookingDetail.kVendorID).compareTo(recApTrx.getField(ApTrx.kVendorID)) < 0)))
                            bAddNewVoucher = false;
                    else
                    {
                        recVendor = (Vendor)((ReferenceField)recApTrx.getField(ApTrx.kVendorID)).getReference();
                        if (OperationTypeField.ALL_TOGETHER_CODE.equalsIgnoreCase(recVendor.getField(Vendor.kOperationTypeCode).toString()))
                            bAddNewVoucher = false;
                        else if (OperationTypeField.INDIVIDUALLY_CODE.equalsIgnoreCase(recVendor.getField(Vendor.kOperationTypeCode).toString()))
                        {
                            if (recBookingDetail.getField(BookingDetail.kApTrxID).compareTo(recApTrx.getField(ApTrx.kID)) != 0)
                                bAddNewVoucher = true;
                            else
                                bAddNewVoucher = false;
                        }
                        else // if (OperationTypeField.LIKE_TOGETHER_CODE.equalsIgnoreCase(recVendor.getField(Vendor.kOperationTypeCode).toString()))
                        {
                            if (recBookingDetail.getField(BookingDetail.kProductTypeID).compareTo(recApTrx.getField(ApTrx.kProductTypeID)) > 0)
                                bAddNewVoucher = true;
                            else
                                bAddNewVoucher = false;
                        }
                    }
                }
                if (recBookingDetail != null)
                    if (recVendor == null)
                        recVendor = (Vendor)((ReferenceField)recBookingDetail.getField(BookingDetail.kVendorID)).getReference();
                if (recVendor != null)
                    if ((OperationTypeField.LIKE_TOGETHER_CODE.equalsIgnoreCase(recVendor.getField(Vendor.kOperationTypeCode).toString()))
                        || (OperationTypeField.INDIVIDUALLY_CODE.equalsIgnoreCase(recVendor.getField(Vendor.kOperationTypeCode).toString())))
                            intProductTypeID = (int)recBookingDetail.getField(BookingDetail.kProductTypeID).getValue();
                if (bAddNewVoucher)
                { // Branch 1 - No A/P detail to match product detail (Create A/P detail & U/D all product detail)
                    ApTrx recNewApTrx = recApTrxAdd.addNewApTrx(this.getField(Tour.kID), recVendor, intProductTypeID);
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
        if (this.getField(Tour.kOrderComponents).getState() == false)
            return;
        BookingDetail recBookingDetail = new BookingDetail(Utility.getRecordOwner(this));
        try {
            recBookingDetail.addListener(new SubFileFilter(this));
            while (recBookingDetail.hasNext())
            {
                recBookingDetail.next();
                Record recBookingDetailCurrent = recBookingDetail.getTable().getCurrentTable().getRecord();
                recBookingDetailCurrent.edit();
                recBookingDetailCurrent.getField(BookingDetail.kProductStatusRequest).setData(Boolean.TRUE);
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
    public TourEventSchedule getTourEventSchedule()
    {
        if (m_recTourEventSchedule == null)
        {
            m_recTourEventSchedule = new TourEventSchedule(Utility.getRecordOwner(this));
            this.addListener(new FreeOnFreeHandler(m_recTourEventSchedule));
        }
        return m_recTourEventSchedule;
    }

}
