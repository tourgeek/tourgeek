
package com.tourgeek.tour.product.hotel.screen;

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
import com.tourgeek.tour.product.base.screen.*;
import com.tourgeek.tour.product.hotel.event.*;
import com.tourgeek.tour.product.base.db.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.thin.app.booking.entry.*;
import com.tourgeek.tour.product.base.event.*;
import com.tourgeek.tour.product.hotel.db.*;
import com.tourgeek.tour.booking.detail.db.*;
import com.tourgeek.tour.base.db.*;
import java.util.*;
import com.tourgeek.tour.acctpay.db.*;

/**
 *  HotelScreen - Hotel.
 */
public class HotelScreen extends ProductScreen
{
    /**
     * Default constructor.
     */
    public HotelScreen()
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
    public HotelScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Hotel";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Hotel(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new Vendor(this);
        new Currencys(this);
        // new BkCountry(this);
        // new BkCity(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.addMainKeyBehavior();
        Hotel bkHotel = (Hotel)this.getMainRecord();
        Vendor apVendor = (Vendor)this.getRecord(Vendor.VENDOR_FILE);
        ReadSecondaryHandler secondaryStuff = new ReadSecondaryHandler(apVendor, Vendor.ID_KEY);
        bkHotel.getField(Hotel.VENDOR_ID).addListener(secondaryStuff);
        ReadSecondaryHandler currSecond = new ReadSecondaryHandler(this.getRecord(Currencys.CURRENCYS_FILE), Currencys.CURRENCY_CODE_KEY);
        apVendor.getField(Vendor.CURRENCYS_ID).addListener(currSecond);
        
        BaseField checkMark = bkHotel.getField(Hotel.SAME_AS_VENDOR);
        secondaryStuff.addFieldSeqPair(Hotel.DESCRIPTION, Vendor.VENDOR_NAME, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK, checkMark, null);
        secondaryStuff.addFieldSeqPair(Hotel.ADDRESS_LINE_1, Vendor.ADDRESS_LINE_1, DBConstants.MOVE_TO_DEPENDENT,DBConstants.DONT_MOVE_DEPENDENT_BACK, checkMark, null);
        secondaryStuff.addFieldSeqPair(Hotel.ADDRESS_LINE_2, Vendor.ADDRESS_LINE_2, DBConstants.MOVE_TO_DEPENDENT,DBConstants.DONT_MOVE_DEPENDENT_BACK, checkMark, null);
        secondaryStuff.addFieldSeqPair(Hotel.CITY_OR_TOWN, Vendor.CITY_OR_TOWN, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK, checkMark, null);
        secondaryStuff.addFieldSeqPair(Hotel.STATE_OR_REGION, Vendor.STATE_OR_REGION, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK, checkMark, null);
        secondaryStuff.addFieldSeqPair(Hotel.POSTAL_CODE, Vendor.POSTAL_CODE, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK, checkMark, null);
        secondaryStuff.addFieldSeqPair(Hotel.COUNTRY, Vendor.COUNTRY, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK, checkMark, null);
        secondaryStuff.addFieldSeqPair(Hotel.TEL, Vendor.TEL, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK, checkMark, null);
        secondaryStuff.addFieldSeqPair(Hotel.FAX, Vendor.FAX, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK, checkMark, null);
        secondaryStuff.addFieldSeqPair(Hotel.EMAIL, Vendor.EMAIL, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK, checkMark, null);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        super.addToolbarButtons(toolScreen);
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Hotel.MEAL_DETAIL), BookingConstants.BUTTON_LOCATION + Hotel.MEAL_DETAIL, Hotel.MEAL_DETAIL, null);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.DESCRIPTION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.VENDOR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.DESCRIPTION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.DESCRIPTION).setEnabled(false);
        this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.COSTING_RATE).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.COSTING_RATE).setEnabled(false);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.SAME_AS_VENDOR).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.CITY_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.ADDRESS_LINE_1).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.ADDRESS_LINE_2).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.CITY_OR_TOWN).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.STATE_OR_REGION).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.POSTAL_CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.COUNTRY).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.TEL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.EMAIL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.FAX).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.ETD).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.CHECK_OUT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.ONE_FREE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.FREE_TYPE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.CHILD_AGE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.GENERAL_MANAGER).setupDefaultView(this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.SALES_MANAGER).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.LOCAL_CONTACT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.LOCAL_PHONE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.TOLL_FREE_PHONE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.ALT_PHONE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.ROOMS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.OPERATORS_CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.PRODUCT_CHAIN_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.COMMENTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Hotel.HOTEL_FILE).getField(Hotel.ITINERARY_DESC).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.FILL_REMAINDER), this, ScreenConstants.DEFAULT_DISPLAY);
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
        if (strCommand.equalsIgnoreCase(Hotel.MEAL_DETAIL))
            return (this.onForm(null, Hotel.MEAL_PRICING_GRID_SCREEN, true, iCommandOptions, null) != null);
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
