
package com.tourgeek.tour.booking.entry.detail.car;

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
import com.tourgeek.tour.product.car.db.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.thin.app.booking.entry.*;
import com.tourgeek.tour.booking.entry.base.*;

/**
 *  BookingCarScreen - Rental car detail.
 */
public class BookingCarScreen extends BookingDetailSubScreen
{
    /**
     * Default constructor.
     */
    public BookingCarScreen()
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
    public BookingCarScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        parentScreen.setProperty(BookingScreenHandler.SUB_SCREEN_PARAM, Integer.toString(BookingScreenHandler.CAR_SCREEN));
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Rental car detail";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new BookingCar(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
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
        BookingCar recBookingCar = (BookingCar)this.getMainRecord();
        
        recBookingCar.getField(BookingCar.DAYS).setEnabled(true);
        recBookingCar.getField(BookingCar.QUANTITY).setEnabled(true);
        recBookingCar.getField(BookingCar.DETAIL_END_DATE).setEnabled(true);
        
        this.getRecord(BookingDetail.BOOKING_DETAIL_FILE).getField(BookingDetail.MARKUP_FROM_LAST).setEnabled(true);
        
        // Display the USD equivalents
        BaseField fldExchange = recBookingCar.getField(BookingCar.EXCHANGE);
        Record recBookingDetailScreenRecord = this.getScreenRecord();
        FieldListener fieldListener = null;

    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(BookingCar.BOOKING_CAR_FILE).getField(BookingCar.PRODUCT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingCar.BOOKING_CAR_FILE).getField(BookingCar.DETAIL_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingCar.BOOKING_CAR_FILE).getField(BookingCar.DETAIL_END_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingCar.BOOKING_CAR_FILE).getField(BookingCar.DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingCar.BOOKING_CAR_FILE).getField(BookingCar.QUANTITY).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingCar.BOOKING_CAR_FILE).getField(BookingCar.RATE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingCar.BOOKING_CAR_FILE).getField(BookingCar.CLASS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        // Make sure these all have this recordowner
        Record recProduct = ((ReferenceField)this.getMainRecord().getField(BookingDetail.PRODUCT_ID)).getReferenceRecord(this);    // Reference same recordowner
        Record recVendor = ((ReferenceField)recProduct.getField(Product.VENDOR_ID)).getReferenceRecord(this);
        Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReferenceRecord(this);
        this.getRecord(Car.CAR_FILE).getField(Car.VENDOR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.CURRENCYS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.COSTING_RATE).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(BookingCar.BOOKING_CAR_FILE).getField(BookingCar.TOTAL_COST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingCar.BOOKING_CAR_FILE).getField(BookingCar.TOTAL_COST_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.addStandardScreenFields(false);
        this.getRecord(BookingDetail.BOOKING_DETAIL_FILE).getField(BookingDetail.MARKUP_FROM_LAST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.PRICING_DETAIL), BookingConstants.BUTTON_LOCATION + Product.PRICING_DETAIL, Product.PRICING_DETAIL, null);
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.INVENTORY_DETAIL), BookingConstants.BUTTON_LOCATION + Product.INVENTORY_DETAIL, Product.INVENTORY_DETAIL, null);
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.MESSAGE_LOG), BookingConstants.BUTTON_LOCATION + Product.MESSAGE_LOG, Product.MESSAGE_LOG, null);
        this.getRecord(Car.CAR_FILE).getField(Car.COMMENTS).setupDefaultView(this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
