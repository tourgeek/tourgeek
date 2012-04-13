/**
 * @(#)BookingTourScreen.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.tour;

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
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.inventory.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.hotel.db.*;
import java.util.*;
import com.tourapp.tour.product.base.event.*;
import com.tourapp.tour.booking.detail.event.*;
import com.tourapp.tour.booking.db.event.*;

/**
 *  BookingTourScreen - Tour information.
 */
public class BookingTourScreen extends BookingSubScreen
{
    /**
     * Default constructor.
     */
    public BookingTourScreen()
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
    public BookingTourScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Open the files and setup the screen.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Additional properties to pass to the screen.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        parentScreen.setProperty(BookingScreenHandler.SUB_SCREEN_PARAM, Integer.toString(BookingScreenHandler.TOUR_SCREEN));
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Tour information";
    }
    /**
     * Get the main record for this screen.
     * @return The main record.
     */
    public Record getMainRecord()
    {
        return this.getRecord(Booking.BOOKING_FILE);    // Booking file is the main file
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        new Inventory(this);
        Booking recBooking = (Booking)this.getRecord(Booking.BOOKING_FILE);
        Tour recTour = (Tour)((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReferenceRecord(); // These have already been set up in the screen header
        TourHeader recTourHdr = (TourHeader)((ReferenceField)recTour.getField(Tour.TOUR_HEADER_ID)).getReferenceRecord();
        TourClass recTourClass = (TourClass)((ReferenceField)recTourHdr.getField(TourHeader.TOUR_CLASS_ID)).getReferenceRecord(this);
        if (this.getRecord(BookingControl.BOOKING_CONTROL_FILE) == null)
            new BookingControl(this);
        super.openOtherRecords();
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new TourEntryScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        Booking recBooking = (Booking)this.getRecord(Booking.BOOKING_FILE);
        Tour recTour = (Tour)((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReferenceRecord();
        TourHeader recTourHeader = (TourHeader)((ReferenceField)recTour.getField(Tour.TOUR_HEADER_ID)).getReferenceRecord();
        TourClass recTourClass = (TourClass)((ReferenceField)recTourHeader.getField(TourHeader.TOUR_CLASS_ID)).getReferenceRecord(this);
        Inventory recInventory = (Inventory)this.getRecord(Inventory.INVENTORY_FILE);
        try {
            if (((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReference() == null)
                recTour.addNew();
            if (((ReferenceField)recTour.getField(Tour.TOUR_HEADER_ID)).getReference() == null)
                recTourHeader.addNew();
        } catch (DBException ex)    {
        }
        
        BaseField fldTourCode = this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.CODE);
        BaseField fldDepartureDate = this.getRecord(Tour.TOUR_FILE).getField(Tour.DEPARTURE_DATE);
        BaseField fldTourDesc = this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.DESCRIPTION);
        
        FieldListener fieldBehavior = null;
        fieldBehavior = new ChangeTourHeaderHandler(recTourHeader, recTourClass, recTour, recBooking, fldTourCode, fldDepartureDate, null);
        fldTourCode.addListener(fieldBehavior);
        recInventory.addListener(new FileRemoveBOnCloseHandler(fieldBehavior));
        fieldBehavior = new ChangeTourHeaderHandler(recTourHeader, recTourClass, recTour, recBooking, fldTourCode, fldDepartureDate, fldTourDesc);
        fldDepartureDate.addListener(fieldBehavior);
        recInventory.addListener(new FileRemoveBOnCloseHandler(fieldBehavior));
        fieldBehavior = new ChangeTourHeaderHandler(recTourHeader, recTourClass, recTour, recBooking, null, fldDepartureDate, fldTourDesc);
        fldTourDesc.addListener(fieldBehavior);
        recInventory.addListener(new FileRemoveBOnCloseHandler(fieldBehavior));
        
        // Make sure you switch to the options screen if always resolve is set, also change when option button is pressed
        fldTourCode.addListener(fieldBehavior = new CheckTourOptionDisplay(null));
        recInventory.addListener(new FileRemoveBOnCloseHandler(fieldBehavior));
        fldDepartureDate.addListener(fieldBehavior = new CheckTourOptionDisplay(null));
        recInventory.addListener(new FileRemoveBOnCloseHandler(fieldBehavior));
        fldTourDesc.addListener(fieldBehavior = new CheckTourOptionDisplay(null));
        recInventory.addListener(new FileRemoveBOnCloseHandler(fieldBehavior));
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.ASK_FOR_ANSWER).addListener(fieldBehavior = new DisableOnFieldHandler(this.getRecord(Booking.BOOKING_FILE).getField(Booking.ASK_FOR_ANSWER), BooleanField.YES, false));
        recInventory.addListener(new FileRemoveBOnCloseHandler(fieldBehavior));
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.ALWAYS_RESOLVE).addListener(fieldBehavior = new DisableOnFieldHandler(this.getRecord(Booking.BOOKING_FILE).getField(Booking.ALWAYS_RESOLVE), BooleanField.YES, false));
        recInventory.addListener(new FileRemoveBOnCloseHandler(fieldBehavior));
        
        // Make sure the screen record's field is set to the header ID
        ReadSecondaryHandler pBehavior2 = new ReadSecondaryHandler(recTourHeader, DBConstants.MAIN_FIELD, DBConstants.DONT_CLOSE_ON_FREE, false, false);
        this.getScreenRecord().getField(TourEntryScreenRecord.TOUR_HEADER_ID).addListener(pBehavior2);
        recInventory.addListener(new FileRemoveBOnCloseHandler(pBehavior2));
        
        if (recBooking.getField(Booking.TOUR_ID).getListener(CalcBookingDatesHandler.class) == null)
            recBooking.getField(Booking.TOUR_ID).addListener(new CalcBookingDatesHandler(recTour, recTourHeader));
        
        FileListener fileBeh = null;
        recTourHeader.addListener(fileBeh = new DisplayReadHandler(TourHeader.TOUR_CLASS_ID, this.getRecord(TourClass.TOUR_CLASS_FILE), TourClass.ID));
        recInventory.addListener(new FileRemoveBOnCloseHandler(fileBeh));        
        fileBeh = new EnableOnValidHandler(true, false);
        recTour.addListener(fileBeh);
        recInventory.addListener(new FileRemoveBOnCloseHandler(fileBeh));
        
        fileBeh = new EnableOnValidHandler(Tour.DEPARTURE_DATE, EnableOnValidHandler.ENABLE_ON_VALID, EnableOnValidHandler.ENABLE_ON_NEW);
        recTour.addListener(fileBeh); // Make sure date remains enabled
        recInventory.addListener(new FileRemoveBOnCloseHandler(fileBeh));
        
        fileBeh = new EnableOnValidHandler(Tour.SERV_CONF, EnableOnValidHandler.DISABLE_ON_VALID, EnableOnValidHandler.DISABLE_ON_NEW);
        recTour.addListener(fileBeh); // Make sure date remains enabled
        recInventory.addListener(new FileRemoveBOnCloseHandler(fileBeh));
        
        recInventory.addListener(new UpdateOnCloseHandler(recTour, true));
        
        fileBeh = new EnableOnValidHandler(this.getRecord(Tour.TOUR_FILE).getField(Tour.ID), true, false)
        {
            public void setEnabled(boolean bEnableFlag)   // Init this field override for other value
            {
                if (bEnableFlag)
                {
                    Record recTourHeader = this.getOwner();
                    bEnableFlag = recTourHeader.getField(TourHeader.TOUR_SERIES).getState();    // Only for series tours
                }
                super.setEnabled(bEnableFlag);   // Init this field override for other value
            }
        };
        recTourHeader.addListener(fileBeh);
        recInventory.addListener(new FileRemoveBOnCloseHandler(fileBeh));
        
        fileBeh = new TourHeaderSelectHandler(null);
        recTourHeader.addListener(fileBeh);
        recInventory.addListener(new FileRemoveBOnCloseHandler(fileBeh));
        
        ProductType recProductType = new ProductType(this);
        int iProductType = recProductType.getProductTypeID(recTourHeader);
        recProductType.free();
        recProductType = null;
        BaseField fldTourID = this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.ID);
        recTourHeader.addListener(new LookupInventoryHandler(recInventory, iProductType, fldTourID, fldDepartureDate, null, null));
        
        this.getScreenRecord().getField(TourEntryScreenRecord.LAND_CLASS_ID).addListener(new UpdatePMC(recTour.getField(Tour.PMC_CUTOFF)));
        if (recTour.getField(Tour.PMC_CUTOFF).getListener(UpdateLandClass.class) == null)
            recTour.getField(Tour.PMC_CUTOFF).addListener(new UpdateLandClass(this.getScreenRecord().getField(TourEntryScreenRecord.LAND_CLASS_ID)));
        
        recTour.addScreenListeners(this);
        
        fieldBehavior = new ChangeTourHeaderHandler(recTourHeader, recTourClass, recTour, recBooking, null, fldDepartureDate, fldTourDesc);
        this.getScreenRecord().getField(TourEntryScreenRecord.TOUR_HEADER_ID).addListener(fieldBehavior);
        
        this.getRecord(Tour.TOUR_FILE).addListener(fileBeh = new FileListener(null)
        {
            public int doRecordChange(FieldInfo field, int iChangeType, boolean bDisplayOption)
            {
                int iErrorCode = super.doRecordChange(field, iChangeType, bDisplayOption);
                if (iErrorCode == DBConstants.NORMAL_RETURN)
                    if (iChangeType == DBConstants.SELECT_TYPE)
                {
                    BaseField fldDepartureDate = this.getOwner().getField(Tour.DEPARTURE_DATE);
                    FieldListener listener = fldDepartureDate.getListener(ChangeTourHeaderHandler.class);
                    listener.fieldChanged(bDisplayOption, DBConstants.SCREEN_MOVE); // Fake a departure date change
                }
                return iErrorCode;
            }
        });
        recInventory.addListener(new FileRemoveBOnCloseHandler(fileBeh));
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        ScreenField screenField = null;
        TourCodeConverter tourCodeConv = new TourCodeConverter(this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.CODE), this.getRecord(Tour.TOUR_FILE).getField(Tour.DEPARTURE_DATE));
        new SEditText(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, tourCodeConv, ScreenConstants.DISPLAY_FIELD_DESC);
        // Lookup Tour Hdr (right of last)
        screenField = new SSelectBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, this.getRecord(TourHeader.TOUR_HEADER_FILE));
        // Make sure the tour header display displays only the modules
        String strCommand = ((SSelectBox)screenField).getButtonCommand();
        strCommand = Utility.addURLParam(null, DBParams.COMMAND, strCommand);
        Record recBookingControl = this.getRecord(BookingControl.BOOKING_CONTROL_FILE);
        strCommand = Utility.addURLParam(strCommand, TourHeaderScreenRecord.TOUR_TYPE, recBookingControl.getField(BookingControl.TOUR_HEADER_TOUR_TYPE).toString());
        ((SSelectBox)screenField).setButtonCommand(strCommand);
        screenField.setRequestFocusEnabled(false);
        screenField = new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, null, ScreenModel.CLEAR, ScreenModel.CLEAR, application.getResources(ResourceConstants.MENU_RESOURCE, true).getString(ScreenModel.CLEAR), null, null);
        screenField.setRequestFocusEnabled(false);
        // Tour Header Popup (right of last)
        TourHeader recTourHeader = new TourHeader(this);
        this.removeRecord(recTourHeader);
        this.getMainRecord().addListener(new FreeOnFreeHandler(recTourHeader));
        BaseField fldDepartureDate = this.getRecord(Tour.TOUR_FILE).getField(Tour.DEPARTURE_DATE);
        BaseField fldStartDate = this.getScreenRecord().getField(TourEntryScreenRecord.START_TARGET_DATE);
        BaseField fldEndDate = this.getScreenRecord().getField(TourEntryScreenRecord.END_TARGET_DATE);
        
        BitFileFilter filter = new BitFileFilter(TourHeader.TOUR_TYPE, recBookingControl.getField(BookingControl.TOUR_HEADER_TOUR_TYPE));
        filter.setNoFilterIfNone(true);
        recTourHeader.addListener(filter);
        
        this.getScreenRecord().getField(TourEntryScreenRecord.TOUR_HEADER_ID).moveFieldToThis(this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.ID));
        QueryConverter tourHdrConv = new QueryConverter((ReferenceField)this.getScreenRecord().getField(TourEntryScreenRecord.TOUR_HEADER_ID), recTourHeader, TourHeader.DESCRIPTION, true);
        recTourHeader.createTourHeaderPopup(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.ANCHOR_DEFAULT), this, tourHdrConv, ScreenConstants.DONT_DISPLAY_FIELD_DESC, fldDepartureDate, fldStartDate, fldEndDate, recBookingControl.getField(BookingControl.TOUR_HEADER_TOUR_TYPE));
        
        recTourHeader.setKeyArea(TourHeader.ID_KEY);
        recTourHeader.getField(TourHeader.ID).moveFieldToThis(this.getRecord(Tour.TOUR_FILE).getField(Tour.TOUR_HEADER_ID));
        try   {
            if (recTourHeader.getField(TourHeader.ID).getLength() == 0)
                recTourHeader.addNew();
            else
                recTourHeader.seek(DBConstants.EQUALS);
        } catch (DBException e)   {
            e.printStackTrace();
        }
        this.getRecord(Tour.TOUR_FILE).getField(Tour.DEPARTURE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SSelectBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, this.getRecord(Tour.TOUR_FILE).getField(Tour.ID), ScreenConstants.DONT_DISPLAY_FIELD_DESC, this.getRecord(Tour.TOUR_FILE))
        {
           public String getProperties(String strCommand, Map<String,Object> properties)
            {
                strCommand = super.getProperties(strCommand, properties);
                BaseField fldTourHeaderID = BookingTourScreen.this.getScreenRecord().getField(TourEntryScreenRecord.TOUR_HEADER_ID);
                Utility.addFieldParam(properties, fldTourHeaderID);
                return strCommand;
            }
        };
        // Button to switch to the option screen (see doCommand)
        Converter fieldConverter = this.getRecord(Booking.BOOKING_FILE).getField(Booking.ASK_FOR_ANSWER);
        new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST_BUTTON_WITH_GAP, ScreenConstants.DONT_SET_ANCHOR), this, fieldConverter, ScreenConstants.DEFAULT_DISPLAY, null, BookingScreenHandler.OPTIONS, Booking.BUTTON_LOCATION + BookingScreenHandler.OPTIONS, BookingScreenHandler.OPTIONS, null);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.DESCRIPTION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        // Series departure date lookup
        this.getRecord(Tour.TOUR_FILE).getField(Tour.TOUR_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.MANUAL_TOUR_STATUS).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.FILE_NO).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.AIR_RATE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.AIR_CLASS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.HOTEL_RATE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.HOTEL_CLASS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.LAND_RATE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.LAND_CLASS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.PMC_CUTOFF).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.CAR_RATE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.CAR_CLASS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.TRANSPORTATION_RATE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.TRANSPORTATION_CLASS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.CRUISE_RATE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.CRUISE_CLASS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.ITEM_RATE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.ITEM_CLASS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        int iOldEditMode = ((ReferenceField)this.getRecord(Booking.BOOKING_FILE).getField(Booking.TOUR_ID)).getReferenceRecord().getOpenMode();
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.TOUR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        ((ReferenceField)this.getRecord(Booking.BOOKING_FILE).getField(Booking.TOUR_ID)).getReferenceRecord().setOpenMode(iOldEditMode);
        this.getRecord(TourClass.TOUR_CLASS_FILE).getField(TourClass.DEPOSIT_AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(TourClass.TOUR_CLASS_FILE).getField(TourClass.DEPOSIT_DUE_DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.DEPOSIT_DUE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(TourClass.TOUR_CLASS_FILE).getField(TourClass.FINAL_PAY_DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.FINAL_PAYMENT_DUE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.NIGHTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.MIN_TO_OP).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(Inventory.INVENTORY_FILE).getField(Inventory.BLOCKED).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(Inventory.INVENTORY_FILE).getField(Inventory.USED).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(Inventory.INVENTORY_FILE).getField(Inventory.AVAILABLE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.FINALIZED).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourClass.TOUR_CLASS_FILE).getField(TourClass.FINALIZE_DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.FINALIZE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.ORDER_COMPONENTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourClass.TOUR_CLASS_FILE).getField(TourClass.ORDER_COMP_DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.ORDER_COMP_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.CLOSED).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(TourClass.TOUR_CLASS_FILE).getField(TourClass.CLOSED_DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.CLOSED_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.FINAL_DOCS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourClass.TOUR_CLASS_FILE).getField(TourClass.FINAL_DOC_DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.FINAL_DOC_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.TICKETS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourClass.TOUR_CLASS_FILE).getField(TourClass.TICKET_DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.TICKET_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.SP_1).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        ScreenLocation descLocation = m_screenParent.getNextLocation(ScreenConstants.FIELD_DESC, ScreenConstants.DONT_SET_ANCHOR);
        BaseField sp1Desc = this.getRecord(TourClass.TOUR_CLASS_FILE).getField(TourClass.SP_1_DESC);
        new SStaticText(descLocation, this, sp1Desc, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(TourClass.TOUR_CLASS_FILE).getField(TourClass.SP_1_DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.SP_1_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.SP_2).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        ScreenLocation desc2Location = m_screenParent.getNextLocation(ScreenConstants.FIELD_DESC, ScreenConstants.DONT_SET_ANCHOR);
        BaseField sp2Desc = this.getRecord(TourClass.TOUR_CLASS_FILE).getField(TourClass.SP_2_DESC);
        new SStaticText(desc2Location, this, sp2Desc, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(TourClass.TOUR_CLASS_FILE).getField(TourClass.SP_2_DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.SP_2_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.SERV_CONF).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.DEPARTED).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.MARKUP).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.TOUR_PRICING_TYPE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.NON_TOUR_PRICING_TYPE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        boolean bHandled = false;
        if (MenuConstants.LOOKUP.equals(strCommand))
        {
            for (int i = 0; i < this.getSFieldCount(); i++)
            {
                ScreenField screenField = this.getSField(i);
                if (screenField instanceof SSelectBox)
                {
                    if (((SSelectBox)screenField).getButtonCommand().indexOf(MenuConstants.LOOKUP) != -1)
                        return ((SSelectBox)screenField).doCommand(((SSelectBox)screenField).getButtonCommand(), screenField, 0);
                }
            }
        }
        if (ScreenModel.CLEAR.equalsIgnoreCase(strCommand))
        {
            Record recBooking = this.getMainRecord();
            if (!recBooking.getField(Booking.TOUR_ID).isNull())
            {
                Record recTour = ((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReference();
                if ((recTour.getEditMode() != DBConstants.EDIT_CURRENT)
                    && (recTour.getEditMode() != DBConstants.EDIT_IN_PROGRESS))
                        bHandled = true;    // Nothing to clear
                else if ((recTour.getField(Tour.TOUR_STATUS_ID).getValue() == TourStatus.OKAY)
                    || (recBooking.getField(Booking.BOOKING_STATUS_ID).getValue() == BookingStatus.OKAY))
                {
                    String strError = "Can't change a live tour - cancel and rebook";
                    strError = this.getTask().getApplication().getResources(ResourceConstants.BOOKING_RESOURCE, true).getString(strError);
                    this.getTask().setStatusText(strError, DBConstants.WARNING);
                    bHandled = true;
                }
                else
                {
                    recBooking.getField(Booking.CODE).setData(null, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                    recBooking.getField(Booking.DESCRIPTION).setData(null, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                    try {
                        recTour.edit();
                        recTour.getField(Tour.TOUR_STATUS_ID).setValue(TourStatus.NOT_VALID);
                        recTour.set();
                        recTour.addNew();
                    } catch (DBException ex) {
                        ex.printStackTrace();
                    }
                    recBooking.getField(Booking.TOUR_ID).setData(null, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                    bHandled = true;
                }
            }
        }
        else if (BookingScreenHandler.OPTIONS.equalsIgnoreCase(strCommand))
        {
            RecordOwner recordOwner = this.getRecord(Booking.BOOKING_FILE).getRecordOwner();
            recordOwner.getScreenRecord().getField(BookingScreenRecord.BK_SUB_SCREEN).setValue(BookingScreenHandler.OPTIONS_SCREEN); // Switch to the options screen
            bHandled = true;
        }
        if (!bHandled)
            bHandled = super.doCommand(strCommand, sourceSField, iCommandOptions); // This will send the command to my view
        return bHandled;
    }

}
