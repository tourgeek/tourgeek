/**
  * @(#)BookingHotelScreen.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.entry.detail.hotel;

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
import com.tourapp.tour.booking.entry.detail.base.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.thin.app.booking.entry.*;
import com.tourapp.tour.booking.entry.base.*;

/**
 *  BookingHotelScreen - Booking hotel information.
 */
public class BookingHotelScreen extends BookingDetailSubScreen
{
    /**
     * Default constructor.
     */
    public BookingHotelScreen()
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
    public BookingHotelScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        parentScreen.setProperty(BookingScreenHandler.SUB_SCREEN_PARAM, Integer.toString(BookingScreenHandler.HOTEL_SCREEN));
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Booking hotel information";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new BookingHotel(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new HotelPricing(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new BookingHotelScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        BookingHotel recBookingHotel = (BookingHotel)this.getMainRecord();
        
        recBookingHotel.getField(BookingHotel.NIGHTS).setEnabled(true);
        for (int iFieldSeq = recBookingHotel.getFieldSeq(BookingHotel.MEAL_PLAN_1ID); iFieldSeq <= recBookingHotel.getFieldSeq(BookingHotel.MEAL_PLAN_4_DAYS); iFieldSeq++)
        {
            recBookingHotel.getField(iFieldSeq).setEnabled(true);
        }
        
        recBookingHotel.getField(BookingHotel.MEAL_PLAN_1ID).addListener(new MoveOnChangeHandler(recBookingHotel.getField(BookingHotel.MEAL_PLAN_1_QTY), recBookingHotel.getField(BookingHotel.NIGHTS), true, false));
        recBookingHotel.getField(BookingHotel.MEAL_PLAN_2ID).addListener(new MoveOnChangeHandler(recBookingHotel.getField(BookingHotel.MEAL_PLAN_2_QTY), recBookingHotel.getField(BookingHotel.NIGHTS), true, false));
        recBookingHotel.getField(BookingHotel.MEAL_PLAN_3ID).addListener(new MoveOnChangeHandler(recBookingHotel.getField(BookingHotel.MEAL_PLAN_3_QTY), recBookingHotel.getField(BookingHotel.NIGHTS), true, false));
        recBookingHotel.getField(BookingHotel.MEAL_PLAN_4ID).addListener(new MoveOnChangeHandler(recBookingHotel.getField(BookingHotel.MEAL_PLAN_4_QTY), recBookingHotel.getField(BookingHotel.NIGHTS), true, false));
        
        // Display the USD equivalents
        BaseField fldExchange = recBookingHotel.getField(BookingHotel.EXCHANGE);
        Record recBookingHotelScreenRecord = this.getScreenRecord();
        FieldListener fieldListener = null;
        recBookingHotel.getField(BookingHotel.SINGLE_COST).addListener(fieldListener = new CalcBalanceHandler(recBookingHotelScreenRecord.getField(BookingHotelScreenRecord.SINGLE_COST_LOCAL), recBookingHotel.getField(BookingHotel.SINGLE_COST), fldExchange, CalcBalanceHandler.MULTIPLY, false));
        fieldListener.setRespondsToMode(DBConstants.READ_MOVE, true);
        recBookingHotel.getField(BookingHotel.DOUBLE_COST).addListener(fieldListener = new CalcBalanceHandler(recBookingHotelScreenRecord.getField(BookingHotelScreenRecord.DOUBLE_COST_LOCAL), recBookingHotel.getField(BookingHotel.DOUBLE_COST), fldExchange, CalcBalanceHandler.MULTIPLY, false));
        fieldListener.setRespondsToMode(DBConstants.READ_MOVE, true);
        recBookingHotel.getField(BookingHotel.TRIPLE_COST).addListener(fieldListener = new CalcBalanceHandler(recBookingHotelScreenRecord.getField(BookingHotelScreenRecord.TRIPLE_COST_LOCAL), recBookingHotel.getField(BookingHotel.TRIPLE_COST), fldExchange, CalcBalanceHandler.MULTIPLY, false));
        fieldListener.setRespondsToMode(DBConstants.READ_MOVE, true);
        recBookingHotel.getField(BookingHotel.QUAD_COST).addListener(fieldListener = new CalcBalanceHandler(recBookingHotelScreenRecord.getField(BookingHotelScreenRecord.QUAD_COST_LOCAL), recBookingHotel.getField(BookingHotel.QUAD_COST), fldExchange, CalcBalanceHandler.MULTIPLY, false));
        fieldListener.setRespondsToMode(DBConstants.READ_MOVE, true);
        
        for (int i = 0; i < this.getSFieldCount(); i++)
        {
            ScreenField sField = this.getSField(i);
            if (sField instanceof SCannedBox)
            {
                String strCommand = ((SCannedBox)sField).getButtonCommand();
                if (Hotel.MEAL_DETAIL.equals(strCommand))
                    this.getRecord(Hotel.HOTEL_FILE).addListener(new EnableOnValidHandler(sField, true, false));
            }
        }
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.PRODUCT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.DETAIL_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.NIGHTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.RATE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.CLASS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        // Make sure these all have this recordowner
        Record recProduct = ((ReferenceField)this.getMainRecord().getField(BookingDetail.PRODUCT_ID)).getReferenceRecord(this);    // Reference same recordowner
        Record recVendor = ((ReferenceField)recProduct.getField(Product.VENDOR_ID)).getReferenceRecord(this);
        Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReferenceRecord(this);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.VENDOR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.CURRENCYS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.COSTING_RATE).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.TOTAL_COST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.TOTAL_COST_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.addStandardScreenFields(false);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.MARKUP_FROM_LAST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.PRICING_DETAIL), BookingConstants.BUTTON_LOCATION + Product.PRICING_DETAIL, Product.PRICING_DETAIL, null);
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Hotel.MEAL_DETAIL), BookingConstants.BUTTON_LOCATION + Hotel.MEAL_DETAIL, Hotel.MEAL_DETAIL, null);
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.INVENTORY_DETAIL), BookingConstants.BUTTON_LOCATION + Product.INVENTORY_DETAIL, Product.INVENTORY_DETAIL, null);
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.MESSAGE_LOG), BookingConstants.BUTTON_LOCATION + Product.MESSAGE_LOG, Product.MESSAGE_LOG, null);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.MEAL_PLAN_1ID).setupDefaultView(this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.MEAL_PLAN_1_QTY).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.MEAL_PLAN_1_DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.MEAL_PLAN_2ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.MEAL_PLAN_2_QTY).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.MEAL_PLAN_2_DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.MEAL_PLAN_3ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.MEAL_PLAN_3_QTY).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.MEAL_PLAN_3_DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.MEAL_PLAN_4ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.MEAL_PLAN_4_QTY).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.MEAL_PLAN_4_DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(HotelPricing.HOTEL_PRICING_FILE).getField(HotelPricing.MEAL_PLAN_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.SINGLE_COST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingHotelScreenRecord.BOOKING_HOTEL_SCREEN_RECORD_FILE).getField(BookingHotelScreenRecord.SINGLE_COST_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.DOUBLE_COST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingHotelScreenRecord.BOOKING_HOTEL_SCREEN_RECORD_FILE).getField(BookingHotelScreenRecord.DOUBLE_COST_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.TRIPLE_COST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingHotelScreenRecord.BOOKING_HOTEL_SCREEN_RECORD_FILE).getField(BookingHotelScreenRecord.TRIPLE_COST_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.QUAD_COST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingHotelScreenRecord.BOOKING_HOTEL_SCREEN_RECORD_FILE).getField(BookingHotelScreenRecord.QUAD_COST_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.ROOM_COST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.ROOM_COST_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.MEAL_COST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingHotel.BOOKING_HOTEL_FILE).getField(BookingHotel.MEAL_COST_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.COMMENTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.ONE_FREE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.FREE_TYPE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        Record recHotel = this.getRecord(Hotel.HOTEL_FILE);
        if (strCommand.equalsIgnoreCase(Hotel.MEAL_DETAIL))
            return (this.onForm(recHotel, Hotel.MEAL_PRICING_GRID_SCREEN, true, ScreenConstants.USE_NEW_WINDOW | ScreenConstants.DONT_PUSH_TO_BROSWER, null) != null);
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
