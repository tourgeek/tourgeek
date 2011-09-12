/**
 * @(#)BookingTourScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.inventory.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.hotel.db.*;
import java.util.*;
import com.tourapp.tour.booking.entry.event.*;
import com.tourapp.tour.booking.detail.event.*;
import com.tourapp.tour.product.tour.screen.*;

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
        return this.getRecord(Booking.kBookingFile);    // Booking file is the main file
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        new Inventory(this);
        Booking recBooking = (Booking)this.getRecord(Booking.kBookingFile);
        Tour recTour = (Tour)((ReferenceField)recBooking.getField(Booking.kTourID)).getReferenceRecord(); // These have already been set up in the screen header
        TourHeader recTourHdr = (TourHeader)((ReferenceField)recTour.getField(Tour.kTourHeaderID)).getReferenceRecord();
        TourClass recTourClass = (TourClass)((ReferenceField)recTourHdr.getField(TourHeader.kTourClassID)).getReferenceRecord(this);
        if (this.getRecord(BookingControl.kBookingControlFile) == null)
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
        
        Booking recBooking = (Booking)this.getRecord(Booking.kBookingFile);
        Tour recTour = (Tour)((ReferenceField)recBooking.getField(Booking.kTourID)).getReferenceRecord();
        TourHeader recTourHeader = (TourHeader)((ReferenceField)recTour.getField(Tour.kTourHeaderID)).getReferenceRecord();
        TourClass recTourClass = (TourClass)((ReferenceField)recTourHeader.getField(TourHeader.kTourClassID)).getReferenceRecord(this);
        Inventory recInventory = (Inventory)this.getRecord(Inventory.kInventoryFile);
        try {
            if (((ReferenceField)recBooking.getField(Booking.kTourID)).getReference() == null)
                recTour.addNew();
            if (((ReferenceField)recTour.getField(Tour.kTourHeaderID)).getReference() == null)
                recTourHeader.addNew();
        } catch (DBException ex)    {
        }
        
        BaseField fldTourCode = this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kCode);
        BaseField fldDepartureDate = this.getRecord(Tour.kTourFile).getField(Tour.kDepartureDate);
        BaseField fldTourDesc = this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kDescription);
        
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
        this.getRecord(Booking.kBookingFile).getField(Booking.kAskForAnswer).addListener(fieldBehavior = new DisableOnFieldHandler(this.getRecord(Booking.kBookingFile).getField(Booking.kAskForAnswer), BooleanField.YES, false));
        recInventory.addListener(new FileRemoveBOnCloseHandler(fieldBehavior));
        this.getRecord(Booking.kBookingFile).getField(Booking.kAlwaysResolve).addListener(fieldBehavior = new DisableOnFieldHandler(this.getRecord(Booking.kBookingFile).getField(Booking.kAlwaysResolve), BooleanField.YES, false));
        recInventory.addListener(new FileRemoveBOnCloseHandler(fieldBehavior));
        
        // Make sure the screen record's field is set to the header ID
        ReadSecondaryHandler pBehavior2 = new ReadSecondaryHandler(recTourHeader, DBConstants.MAIN_FIELD, DBConstants.DONT_CLOSE_ON_FREE, false, false);
        this.getRecord(TourEntryScreenRecord.kTourEntryScreenRecordFile).getField(TourEntryScreenRecord.kTourHeaderID).addListener(pBehavior2);
        recInventory.addListener(new FileRemoveBOnCloseHandler(pBehavior2));
        
        if (recBooking.getField(Booking.kTourID).getListener(CalcBookingDatesHandler.class) == null)
            recBooking.getField(Booking.kTourID).addListener(new CalcBookingDatesHandler(recTour, recTourHeader));
        
        FileListener fileBeh = null;
        recTourHeader.addListener(fileBeh = new DisplayReadHandler(TourHeader.kTourClassID, this.getRecord(TourClass.kTourClassFile), TourClass.kID));
        recInventory.addListener(new FileRemoveBOnCloseHandler(fileBeh));        
        fileBeh = new EnableOnValidHandler(true, false);
        recTour.addListener(fileBeh);
        recInventory.addListener(new FileRemoveBOnCloseHandler(fileBeh));
        
        fileBeh = new EnableOnValidHandler(Tour.kDepartureDate, EnableOnValidHandler.ENABLE_ON_VALID, EnableOnValidHandler.ENABLE_ON_NEW);
        recTour.addListener(fileBeh); // Make sure date remains enabled
        recInventory.addListener(new FileRemoveBOnCloseHandler(fileBeh));
        
        fileBeh = new EnableOnValidHandler(Tour.kServConf, EnableOnValidHandler.DISABLE_ON_VALID, EnableOnValidHandler.DISABLE_ON_NEW);
        recTour.addListener(fileBeh); // Make sure date remains enabled
        recInventory.addListener(new FileRemoveBOnCloseHandler(fileBeh));
        
        recInventory.addListener(new UpdateOnCloseHandler(recTour, true));
        
        fileBeh = new EnableOnValidHandler(this.getRecord(Tour.kTourFile).getField(Tour.kID), true, false)
        {
            public void setEnabled(boolean bEnableFlag)   // Init this field override for other value
            {
                if (bEnableFlag)
                {
                    Record recTourHeader = this.getOwner();
                    bEnableFlag = recTourHeader.getField(TourHeader.kTourSeries).getState();    // Only for series tours
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
        BaseField fldTourID = this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kID);
        recTourHeader.addListener(new LookupInventoryHandler(recInventory, iProductType, fldTourID, fldDepartureDate, null, null));
        
        this.getScreenRecord().getField(TourEntryScreenRecord.kLandClassID).addListener(new UpdatePMC(recTour.getField(Tour.kPMCCutoff)));
        if (recTour.getField(Tour.kPMCCutoff).getListener(UpdateLandClass.class) == null)
            recTour.getField(Tour.kPMCCutoff).addListener(new UpdateLandClass(this.getScreenRecord().getField(TourEntryScreenRecord.kLandClassID)));
        
        recTour.addScreenListeners(this);
        
        fieldBehavior = new ChangeTourHeaderHandler(recTourHeader, recTourClass, recTour, recBooking, null, fldDepartureDate, fldTourDesc);
        this.getRecord(TourEntryScreenRecord.kTourEntryScreenRecordFile).getField(TourEntryScreenRecord.kTourHeaderID).addListener(fieldBehavior);
        
        this.getRecord(Tour.kTourFile).addListener(fileBeh = new FileListener(null)
        {
            public int doRecordChange(FieldInfo field, int iChangeType, boolean bDisplayOption)
            {
                int iErrorCode = super.doRecordChange(field, iChangeType, bDisplayOption);
                if (iErrorCode == DBConstants.NORMAL_RETURN)
                    if (iChangeType == DBConstants.SELECT_TYPE)
                {
                    BaseField fldDepartureDate = this.getOwner().getField(Tour.kDepartureDate);
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
        TourCodeConverter tourCodeConv = new TourCodeConverter(this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kCode), this.getRecord(Tour.kTourFile).getField(Tour.kDepartureDate));
        new SEditText(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, tourCodeConv, ScreenConstants.DISPLAY_FIELD_DESC);
        // Lookup Tour Hdr (right of last)
        screenField = new SSelectBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, this.getRecord(TourHeader.kTourHeaderFile));
        // Make sure the tour header display displays only the modules
        String strCommand = ((SSelectBox)screenField).getButtonCommand();
        strCommand = Utility.addURLParam(null, DBParams.COMMAND, strCommand);
        Record recBookingControl = this.getRecord(BookingControl.kBookingControlFile);
        strCommand = Utility.addURLParam(strCommand, TourHeaderScreenRecord.TOUR_TYPE, recBookingControl.getField(BookingControl.kTourHeaderTourType).toString());
        ((SSelectBox)screenField).setButtonCommand(strCommand);
        screenField.setRequestFocusEnabled(false);
        screenField = new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, null, SCannedBox.CLEAR, SCannedBox.CLEAR, application.getResources(ResourceConstants.MENU_RESOURCE, true).getString(SCannedBox.CLEAR), null, null);
        screenField.setRequestFocusEnabled(false);
        // Tour Header Popup (right of last)
        TourHeader recTourHeader = new TourHeader(this);
        this.removeRecord(recTourHeader);
        this.getMainRecord().addListener(new FreeOnFreeHandler(recTourHeader));
        BaseField fldDepartureDate = this.getRecord(Tour.kTourFile).getField(Tour.kDepartureDate);
        BaseField fldStartDate = this.getRecord(TourEntryScreenRecord.kTourEntryScreenRecordFile).getField(TourEntryScreenRecord.kStartTargetDate);
        BaseField fldEndDate = this.getRecord(TourEntryScreenRecord.kTourEntryScreenRecordFile).getField(TourEntryScreenRecord.kEndTargetDate);
        
        BitFileFilter filter = new BitFileFilter(TourHeader.kTourType, recBookingControl.getField(BookingControl.kTourHeaderTourType));
        filter.setNoFilterIfNone(true);
        recTourHeader.addListener(filter);
        
        this.getRecord(TourEntryScreenRecord.kTourEntryScreenRecordFile).getField(TourEntryScreenRecord.kTourHeaderID).moveFieldToThis(this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kID));
        QueryConverter tourHdrConv = new QueryConverter((ReferenceField)this.getRecord(TourEntryScreenRecord.kTourEntryScreenRecordFile).getField(TourEntryScreenRecord.kTourHeaderID), recTourHeader, TourHeader.kDescription, true);
        recTourHeader.createTourHeaderPopup(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.ANCHOR_DEFAULT), this, tourHdrConv, ScreenConstants.DONT_DISPLAY_FIELD_DESC, fldDepartureDate, fldStartDate, fldEndDate, recBookingControl.getField(BookingControl.kTourHeaderTourType));
        
        recTourHeader.setKeyArea(TourHeader.kIDKey);
        recTourHeader.getField(TourHeader.kID).moveFieldToThis(this.getRecord(Tour.kTourFile).getField(Tour.kTourHeaderID));
        try   {
            if (recTourHeader.getField(TourHeader.kID).getLength() == 0)
                recTourHeader.addNew();
            else
                recTourHeader.seek(DBConstants.EQUALS);
        } catch (DBException e)   {
            e.printStackTrace();
        }
        this.getRecord(Tour.kTourFile).getField(Tour.kDepartureDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SSelectBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, this.getRecord(Tour.kTourFile).getField(Tour.kID), ScreenConstants.DONT_DISPLAY_FIELD_DESC, this.getRecord(Tour.kTourFile))
        {
           public String getProperties(String strCommand, Map<String,Object> properties)
            {
                strCommand = super.getProperties(strCommand, properties);
                BaseField fldTourHeaderID = BookingTourScreen.this.getRecord(TourEntryScreenRecord.kTourEntryScreenRecordFile).getField(TourEntryScreenRecord.kTourHeaderID);
                Utility.addFieldParam(properties, fldTourHeaderID);
                return strCommand;
            }
        };
        // Button to switch to the option screen (see doCommand)
        Converter fieldConverter = this.getRecord(Booking.kBookingFile).getField(Booking.kAskForAnswer);
        new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST_BUTTON_WITH_GAP, ScreenConstants.DONT_SET_ANCHOR), this, fieldConverter, ScreenConstants.DEFAULT_DISPLAY, null, BookingScreenHandler.OPTIONS, Booking.BUTTON_LOCATION + BookingScreenHandler.OPTIONS, BookingScreenHandler.OPTIONS, null);
        this.getRecord(Booking.kBookingFile).getField(Booking.kCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        // Series departure date lookup
        this.getRecord(Tour.kTourFile).getField(Tour.kTourStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kManualTourStatus).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kFileNo).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kAirRateID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kAirClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kHotelRateID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kHotelClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kLandRateID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kLandClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kPMCCutoff).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kCarRateID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kCarClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kTransportationRateID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kTransportationClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kCruiseRateID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kCruiseClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kItemRateID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kItemClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        int iOldEditMode = ((ReferenceField)this.getRecord(Booking.kBookingFile).getField(Booking.kTourID)).getReferenceRecord().getOpenMode();
        this.getRecord(Booking.kBookingFile).getField(Booking.kTourID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        ((ReferenceField)this.getRecord(Booking.kBookingFile).getField(Booking.kTourID)).getReferenceRecord().setOpenMode(iOldEditMode);
        this.getRecord(TourClass.kTourClassFile).getField(TourClass.kDepositAmount).setupDefaultView(this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(TourClass.kTourClassFile).getField(TourClass.kDepositDueDays).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(Booking.kBookingFile).getField(Booking.kDepositDueDate).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(TourClass.kTourClassFile).getField(TourClass.kFinalPayDays).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(Booking.kBookingFile).getField(Booking.kFinalPaymentDueDate).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kDays).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kNights).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(Tour.kTourFile).getField(Tour.kMinToOp).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(Inventory.kInventoryFile).getField(Inventory.kBlocked).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(Inventory.kInventoryFile).getField(Inventory.kUsed).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(Inventory.kInventoryFile).getField(Inventory.kAvailable).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(Tour.kTourFile).getField(Tour.kFinalized).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourClass.kTourClassFile).getField(TourClass.kFinalizeDays).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.kTourFile).getField(Tour.kFinalizeDate).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.kTourFile).getField(Tour.kOrderComponents).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourClass.kTourClassFile).getField(TourClass.kOrderCompDays).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.kTourFile).getField(Tour.kOrderCompDate).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(Tour.kTourFile).getField(Tour.kClosed).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY).setEnabled(false);
        this.getRecord(TourClass.kTourClassFile).getField(TourClass.kClosedDays).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.kTourFile).getField(Tour.kClosedDate).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.kTourFile).getField(Tour.kFinalDocs).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourClass.kTourClassFile).getField(TourClass.kFinalDocDays).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.kTourFile).getField(Tour.kFinalDocDate).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.kTourFile).getField(Tour.kTickets).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourClass.kTourClassFile).getField(TourClass.kTicketDays).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.kTourFile).getField(Tour.kTicketDate).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.kTourFile).getField(Tour.kSp1).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        ScreenLocation descLocation = m_screenParent.getNextLocation(ScreenConstants.FIELD_DESC, ScreenConstants.DONT_SET_ANCHOR);
        BaseField sp1Desc = this.getRecord(TourClass.kTourClassFile).getField(TourClass.kSp1Desc);
        new SStaticText(descLocation, this, sp1Desc, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(TourClass.kTourClassFile).getField(TourClass.kSp1Days).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.kTourFile).getField(Tour.kSp1Date).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.kTourFile).getField(Tour.kSp2).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        ScreenLocation desc2Location = m_screenParent.getNextLocation(ScreenConstants.FIELD_DESC, ScreenConstants.DONT_SET_ANCHOR);
        BaseField sp2Desc = this.getRecord(TourClass.kTourClassFile).getField(TourClass.kSp2Desc);
        new SStaticText(desc2Location, this, sp2Desc, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(TourClass.kTourClassFile).getField(TourClass.kSp2Days).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.kTourFile).getField(Tour.kSp2Date).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC).setEnabled(false);
        this.getRecord(Tour.kTourFile).getField(Tour.kServConf).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kDeparted).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kMarkup).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kTourPricingTypeID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.kBookingFile).getField(Booking.kNonTourPricingTypeID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        if (SCannedBox.CLEAR.equalsIgnoreCase(strCommand))
        {
            Record recBooking = this.getMainRecord();
            if (!recBooking.getField(Booking.kTourID).isNull())
            {
                Record recTour = ((ReferenceField)recBooking.getField(Booking.kTourID)).getReference();
                if ((recTour.getEditMode() != DBConstants.EDIT_CURRENT)
                    && (recTour.getEditMode() != DBConstants.EDIT_IN_PROGRESS))
                        bHandled = true;    // Nothing to clear
                else if ((recTour.getField(Tour.kTourStatusID).getValue() == TourStatus.OKAY)
                    || (recBooking.getField(Booking.kBookingStatusID).getValue() == BookingStatus.OKAY))
                {
                    String strError = "Can't change a live tour - cancel and rebook";
                    strError = this.getTask().getApplication().getResources(ResourceConstants.BOOKING_RESOURCE, true).getString(strError);
                    this.getTask().setStatusText(strError, DBConstants.WARNING);
                    bHandled = true;
                }
                else
                {
                    recBooking.getField(Booking.kCode).setData(null, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                    recBooking.getField(Booking.kDescription).setData(null, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                    try {
                        recTour.edit();
                        recTour.getField(Tour.kTourStatusID).setValue(TourStatus.NOT_VALID);
                        recTour.set();
                        recTour.addNew();
                    } catch (DBException ex) {
                        ex.printStackTrace();
                    }
                    recBooking.getField(Booking.kTourID).setData(null, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                    bHandled = true;
                }
            }
        }
        else if (BookingScreenHandler.OPTIONS.equalsIgnoreCase(strCommand))
        {
            RecordOwner recordOwner = this.getRecord(Booking.kBookingFile).getRecordOwner();
            recordOwner.getScreenRecord().getField(BookingScreenRecord.kBkSubScreen).setValue(BookingScreenHandler.OPTIONS_SCREEN); // Switch to the options screen
            bHandled = true;
        }
        if (!bHandled)
            bHandled = super.doCommand(strCommand, sourceSField, iCommandOptions); // This will send the command to my view
        return bHandled;
    }

}
