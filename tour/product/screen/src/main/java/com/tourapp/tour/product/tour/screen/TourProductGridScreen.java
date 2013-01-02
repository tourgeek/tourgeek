/**
  * @(#)TourProductGridScreen.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.product.tour.screen;

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
import com.tourapp.tour.product.base.screen.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.db.event.*;
import com.tourapp.tour.product.tour.event.*;
import com.tourapp.tour.product.base.event.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.booking.entry.tour.*;

/**
 *  TourProductGridScreen - Tour Header.
 */
public class TourProductGridScreen extends ProductGridScreen
{
    /**
     * Default constructor.
     */
    public TourProductGridScreen()
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
    public TourProductGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Tour Header";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new TourHeader(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new TourHeaderScreenRecord(this);
    }
    /**
     * Read the current file in the header record given the current detail record.
     */
    public void syncHeaderToMain()
    {
        super.syncHeaderToMain();
        
        this.restoreScreenParam(TourHeaderScreenRecord.KEY_ORDER);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        TourHeader recTourHeader = (TourHeader)this.getRecord(TourHeader.TOUR_HEADER_FILE);
        Record screenRecord = this.getScreenRecord();
        
        if (((NumberField)screenRecord.getField(TourHeaderScreenRecord.KEY_ORDER)).getValue() > 3)
            ((NumberField)screenRecord.getField(TourHeaderScreenRecord.KEY_ORDER)).setValue(0);
        FieldListener behCheckRange = new CheckRangeHandler(0, 2);
        screenRecord.getField(TourHeaderScreenRecord.KEY_ORDER).addListener(behCheckRange);
        recTourHeader.addListener(new FileRemoveBOnCloseHandler(behCheckRange));
        
        KeyArea tempKeyStart = recTourHeader.makeIndex(DBConstants.NOT_UNIQUE, null);  // Add temp key
        tempKeyStart.addKeyField(TourHeader.START_DATE, DBConstants.ASCENDING);
        KeyArea tempKeyEnd = recTourHeader.makeIndex(DBConstants.NOT_UNIQUE, null);
        tempKeyEnd.addKeyField(TourHeader.END_DATE, DBConstants.ASCENDING);
        SortOrderHandler behQueryKeyHandler = new SortOrderHandler(this);
        behQueryKeyHandler.setGridTable(TourHeader.DESC_SORT, recTourHeader, 0);
        behQueryKeyHandler.setGridTable(tempKeyStart.getKeyName(), recTourHeader, 1);
        behQueryKeyHandler.setGridTable(tempKeyEnd.getKeyName(), recTourHeader, 2);
        //behQueryKeyHandler.setGridTable(TourHeader.ID, recTourHeader, 3);
        screenRecord.getField(TourHeaderScreenRecord.KEY_ORDER).addListener(behQueryKeyHandler);
        
        recTourHeader.addListener(new CompareFileFilter(TourHeader.BROCHURE_ID, screenRecord.getField(TourHeaderScreenRecord.BROCHURE_ID), "=", null, true));
        screenRecord.getField(TourHeaderScreenRecord.BROCHURE_ID).addListener(new FieldReSelectHandler(this));
        BitFileFilter filter = new BitFileFilter(TourHeader.TOUR_TYPE, screenRecord.getField(TourHeaderScreenRecord.TOUR_TYPE));
        filter.setNoFilterIfNone(true);
        recTourHeader.addListener(filter);
        screenRecord.getField(TourHeaderScreenRecord.TOUR_TYPE).addListener(new FieldReSelectHandler(this));
        recTourHeader.addListener(new CompareFileFilter(TourHeader.AIRLINE_ID, screenRecord.getField(TourHeaderScreenRecord.AIRLINE_ID), "=", null, true));
        screenRecord.getField(TourHeaderScreenRecord.AIRLINE_ID).addListener(new FieldReSelectHandler(this));
        recTourHeader.addListener(new CompareFileFilter(TourHeader.END_DATE, screenRecord.getField(TourHeaderScreenRecord.START_DEPARTURE_DATE), ">=", null, true));
        screenRecord.getField(TourHeaderScreenRecord.START_DEPARTURE_DATE).addListener(new FieldReSelectHandler(this));
        recTourHeader.addListener(new CompareFileFilter(TourHeader.START_DATE, screenRecord.getField(TourHeaderScreenRecord.END_DEPARTURE_DATE), "<=", null, true));
        screenRecord.getField(TourHeaderScreenRecord.END_DEPARTURE_DATE).addListener(new FieldReSelectHandler(this));
        recTourHeader.addListener(new CompareFileFilter(TourHeader.REGION_ID, screenRecord.getField(TourHeaderScreenRecord.REGION_ID), "=", null, true));
        screenRecord.getField(TourHeaderScreenRecord.REGION_ID).addListener(new FieldReSelectHandler(this));
        recTourHeader.addListener(new CompareFileFilter(TourHeader.VENDOR_ID, screenRecord.getField(TourHeaderScreenRecord.VENDOR_ID), "=", null, true));
        screenRecord.getField(TourHeaderScreenRecord.VENDOR_ID).addListener(new FieldReSelectHandler(this));
        
        this.getMainRecord().addListener(new ExtractRangeFilter(Product.DESC_SORT, this.getScreenRecord().getField(ProductScreenRecord.DESCRIPTION)));
        screenRecord.getField(TourHeaderScreenRecord.DESCRIPTION).addListener(new FieldReSelectHandler(this));
        FieldListener behInitOnChange = new InitOnChangeHandler(screenRecord.getField(TourHeaderScreenRecord.DESCRIPTION));
        screenRecord.getField(TourHeaderScreenRecord.KEY_ORDER).addListener(behInitOnChange);
        recTourHeader.addListener(new FileRemoveBOnCloseHandler(behInitOnChange));
        
        this.getScreenRecord().getField(TourHeaderScreenRecord.DISPLAY_TYPE).setValue(DisplayTypeField.TOUR_HEADER_DISPLAY, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
    }
    /**
     * Add the listeners and message queues for rate lookups.
     * (todo - Don't set this up until they are required).
     */
    public void addRateMessageListeners(Product recProduct, ProductScreenRecord screenRecord)
    {
        super.addRateMessageListeners(recProduct, screenRecord);
    }
    /**
     * AddProductRateMessageFilter Method.
     */
    public ProductRateMessageListener addProductRateMessageFilter(Product recProduct, BaseMessageFilter messageFilter)
    {
        return new TourHeaderRateMessageListener(null, recProduct, false, messageFilter);
    }
    /**
     * GetProductCostHandler Method.
     */
    public GetProductCostHandler getProductCostHandler(ProductScreenRecord screenRecord, Integer intRegistryID)
    {
        return new GetTourHeaderCostHandler(screenRecord, intRegistryID);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        // Don't call super
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(BookingScreenHandler.OPTIONS), Booking.BUTTON_LOCATION + BookingScreenHandler.OPTIONS, Product.PRICING_DETAIL, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.INVENTORY_DETAIL), Booking.BUTTON_LOCATION + BookingScreenHandler.INVENTORY, Product.INVENTORY_DETAIL, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(BookingScreenHandler.TOUR_SERIES), Booking.BUTTON_LOCATION + BookingScreenHandler.TOUR, TourHeader.TOUR_DETAIL, null);
        
        this.getScreenRecord().getField(TourHeaderScreenRecord.DISPLAY_TYPE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(TourHeaderScreenRecord.DESCRIPTION).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(TourHeaderScreenRecord.START_DEPARTURE_DATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        
        this.getScreenRecord().getField(TourHeaderScreenRecord.BROCHURE_ID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(TourHeaderScreenRecord.AIRLINE_ID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        
        this.getScreenRecord().getField(TourHeaderScreenRecord.TOUR_TYPE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(TourHeaderScreenRecord.END_DEPARTURE_DATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        
        this.getScreenRecord().getField(ProductScreenRecord.VENDOR_ID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(TourHeaderScreenRecord.REGION_ID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        
        this.getScreenRecord().getField(ProductScreenRecord.DETAIL_DATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(ProductScreenRecord.REMOTE_QUERY_ENABLED).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Add the navigation button(s) to the left of the grid row.
     */
    public void addNavButtons()
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, Booking.BUTTON_LOCATION + "Tour", TourHeader.TOUR_DETAIL, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString("(Series) Tours"));
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, Booking.BUTTON_LOCATION + "Inventory", Product.INVENTORY_DETAIL, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.INVENTORY_DETAIL));
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, Booking.BUTTON_LOCATION + "Options", Product.PRICING_DETAIL, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString("Options"));
        // Don't call super
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, MenuConstants.FORM, MenuConstants.FORMLINK, null);
        if ((m_iDisplayFieldDesc & ScreenConstants.SELECT_MODE) == ScreenConstants.SELECT_MODE)
            new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, MenuConstants.SELECT, MenuConstants.SELECT, null);
    }
    /**
     * Get the command string to restore screen.
     */
    public String getScreenURL()
    {
        String strCommand = super.getScreenURL();
        
        strCommand = this.saveProductParam(strCommand, TourHeaderScreenRecord.KEY_ORDER);
        
        return strCommand;
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Converter converter = new FieldLengthConverter(this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.DESCRIPTION), 30);
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.START_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.END_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.NIGHTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.DISPLAY_COST_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.PRODUCT_COST_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
