/**
  * @(#)BookingTourDetailScreen.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.booking.entry.detail.tour;

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
import com.tourgeek.tour.booking.entry.detail.base.*;
import com.tourgeek.tour.booking.detail.db.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.thin.app.booking.entry.*;
import com.tourgeek.tour.booking.entry.base.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.booking.entry.tour.*;

/**
 *  BookingTourDetailScreen - Booking tour header detail.
 */
public class BookingTourDetailScreen extends BookingDetailSubScreen
{
    /**
     * Default constructor.
     */
    public BookingTourDetailScreen()
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
    public BookingTourDetailScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        parentScreen.setProperty(BookingScreenHandler.SUB_SCREEN_PARAM, Integer.toString(BookingScreenHandler.TOUR_DETAIL_SCREEN));
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Booking tour header detail";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new BookingTour(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
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
        return new BookingDetailScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        FieldListener fieldBehavior = null;
        Record record = this.getMainRecord();
        record.getField(BookingTour.PRICING_TYPE_ID).setEnabled(true);
        record.getField(BookingTour.ASK_FOR_ANSWER).addListener(fieldBehavior = new DisableOnFieldHandler(record.getField(BookingTour.ASK_FOR_ANSWER), BooleanField.YES, false));
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.ALWAYS_RESOLVE).addListener(fieldBehavior = new DisableOnFieldHandler(this.getRecord(Booking.BOOKING_FILE).getField(Booking.ALWAYS_RESOLVE), BooleanField.YES, false));
        record.addListener(new FileRemoveBOnCloseHandler(fieldBehavior));
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(BookingTour.BOOKING_TOUR_FILE).getField(BookingTour.PRODUCT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        for (int i = 0; i < this.getSFieldCount(); i++)
        {
            if (this.getSField(i) instanceof SSelectBox)
            {   // Make sure the tour header display displays only the modules
                String strCommand = ((SSelectBox)this.getSField(i)).getButtonCommand();
                strCommand = Utility.addURLParam(null, DBParams.COMMAND, strCommand);
                Record recBookingControl = this.getRecord(BookingControl.BOOKING_CONTROL_FILE);
                strCommand = Utility.addURLParam(strCommand, TourHeaderScreenRecord.TOUR_TYPE, recBookingControl.getField(BookingControl.MODULE_TOUR_TYPE).toString());
                ((SSelectBox)this.getSField(i)).setButtonCommand(strCommand);
            }
        }
        this.getRecord(BookingTour.BOOKING_TOUR_FILE).getField(BookingTour.DETAIL_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        // Button to switch to the option screen (see doCommand)
        if ((this.getMainRecord().getEditMode() == DBConstants.EDIT_NONE) || (this.getMainRecord().getEditMode() == DBConstants.EDIT_ADD))
        {
            this.getRecord(BookingTour.BOOKING_TOUR_FILE).getField(BookingTour.ASK_FOR_ANSWER).setState(false);
            this.getRecord(Booking.BOOKING_FILE).getField(Booking.ALWAYS_RESOLVE).setState(false);
        }
        Converter fieldConverter = this.getRecord(BookingTour.BOOKING_TOUR_FILE).getField(BookingTour.ASK_FOR_ANSWER);
        new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST_BUTTON_WITH_GAP, ScreenConstants.DONT_SET_ANCHOR), this, fieldConverter, ScreenConstants.DEFAULT_DISPLAY, null, BookingScreenHandler.OPTIONS, Booking.BUTTON_LOCATION + BookingScreenHandler.OPTIONS, BookingScreenHandler.OPTIONS, null);
        // Make sure these all have this recordowner
        Record recProduct = ((ReferenceField)this.getMainRecord().getField(BookingDetail.PRODUCT_ID)).getReferenceRecord(this);    // Reference same recordowner
        Record recVendor = ((ReferenceField)recProduct.getField(Product.VENDOR_ID)).getReferenceRecord(this);
        Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReferenceRecord(this);
        this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.VENDOR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.CURRENCYS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.COSTING_RATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingTour.BOOKING_TOUR_FILE).getField(BookingTour.TOTAL_COST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingTour.BOOKING_TOUR_FILE).getField(BookingTour.TOTAL_COST_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.addStandardScreenFields(false);
        this.getRecord(BookingDetail.BOOKING_DETAIL_FILE).getField(BookingDetail.MARKUP_FROM_LAST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingTour.BOOKING_TOUR_FILE).getField(BookingTour.PRICING_TYPE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.PRICING_DETAIL), BookingConstants.BUTTON_LOCATION + Product.PRICING_DETAIL, Product.PRICING_DETAIL, null);
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.INVENTORY_DETAIL), BookingConstants.BUTTON_LOCATION + Product.INVENTORY_DETAIL, Product.INVENTORY_DETAIL, null);
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.MESSAGE_LOG), BookingConstants.BUTTON_LOCATION + Product.MESSAGE_LOG, Product.MESSAGE_LOG, null);
        this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.NIGHTS).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingTour.BOOKING_TOUR_FILE).getField(BookingTour.PP_COST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingDetailScreenRecord.BOOKING_DETAIL_SCREEN_RECORD_FILE).getField(BookingDetailScreenRecord.PP_COST_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.COMMENTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        if (BookingScreenHandler.OPTIONS.equalsIgnoreCase(strCommand))
        {
            BasePanel parentScreen = this.getParentScreen();
            ScreenLocation screenLocation = parentScreen.getNextLocation(ScreenConstants.FLUSH_LEFT, ScreenConstants.FILL_REMAINDER);
            Map<String,Object> properties = new Hashtable<String,Object>();
            if (!this.getMainRecord().getField(BookingDetail.PRODUCT_ID).isNull())
            {
                properties.put(BookingAnswerGridScreen.MODULE_ID, this.getMainRecord().getField(BookingDetail.PRODUCT_ID).toString());
                properties.put(BookingAnswerGridScreen.MODULE_START_DATE, this.getMainRecord().getField(BookingDetail.DETAIL_DATE).toString());
            }
            Task task = parentScreen.getTask();
        
            this.free();
            task.setProperties(properties);
            new BookingAnswerGridScreen(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
            bHandled = true;
        }
        if (!bHandled)
            bHandled = super.doCommand(strCommand, sourceSField, iCommandOptions); // This will send the command to my view
        return bHandled;
    }

}
