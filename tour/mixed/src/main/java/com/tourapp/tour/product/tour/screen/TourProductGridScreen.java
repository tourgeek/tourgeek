/**
 * @(#)TourProductGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.screen;

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
import com.tourapp.tour.product.base.screen.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.lookup.*;
import com.tourapp.tour.product.tour.event.*;
import com.tourapp.tour.product.base.event.*;
import org.jbundle.thin.base.message.*;

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
        
        this.restoreScreenParam(TourHeaderScreenRecord.kKeyOrder);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        TourHeader recTourHeader = (TourHeader)this.getRecord(TourHeader.kTourHeaderFile);
        Record screenRecord = this.getScreenRecord();
        
        if (((NumberField)screenRecord.getField(TourHeaderScreenRecord.kKeyOrder)).getValue() > 3)
            ((NumberField)screenRecord.getField(TourHeaderScreenRecord.kKeyOrder)).setValue(0);
        FieldListener behCheckRange = new CheckRangeHandler(0, 2);
        screenRecord.getField(TourHeaderScreenRecord.kKeyOrder).addListener(behCheckRange);
        recTourHeader.addListener(new FileRemoveBOnCloseHandler(behCheckRange));
        
        KeyArea tempKeyStart = recTourHeader.makeIndex(DBConstants.NOT_UNIQUE, null);  // Add temp key
        tempKeyStart.addKeyField(TourHeader.kStartDate, DBConstants.ASCENDING);
        KeyArea tempKeyEnd = recTourHeader.makeIndex(DBConstants.NOT_UNIQUE, null);
        tempKeyEnd.addKeyField(TourHeader.kEndDate, DBConstants.ASCENDING);
        SortOrderHandler behQueryKeyHandler = new SortOrderHandler(this);
        behQueryKeyHandler.setGridTable(TourHeader.kDescSort, recTourHeader, 0);
        behQueryKeyHandler.setGridTable(tempKeyStart.getKeySeq(), recTourHeader, 1);
        behQueryKeyHandler.setGridTable(tempKeyEnd.getKeySeq(), recTourHeader, 2);
        //behQueryKeyHandler.setGridTable(TourHeader.kID, recTourHeader, 3);
        screenRecord.getField(TourHeaderScreenRecord.kKeyOrder).addListener(behQueryKeyHandler);
        
        recTourHeader.addListener(new CompareFileFilter(TourHeader.kBrochureID, screenRecord.getField(TourHeaderScreenRecord.kBrochureID), "=", null, true));
        screenRecord.getField(TourHeaderScreenRecord.kBrochureID).addListener(new FieldReSelectHandler(this));
        BitFileFilter filter = new BitFileFilter(TourHeader.kTourType, screenRecord.getField(TourHeaderScreenRecord.kTourType));
        filter.setNoFilterIfNone(true);
        recTourHeader.addListener(filter);
        screenRecord.getField(TourHeaderScreenRecord.kTourType).addListener(new FieldReSelectHandler(this));
        recTourHeader.addListener(new CompareFileFilter(TourHeader.kAirlineID, screenRecord.getField(TourHeaderScreenRecord.kAirlineID), "=", null, true));
        screenRecord.getField(TourHeaderScreenRecord.kAirlineID).addListener(new FieldReSelectHandler(this));
        recTourHeader.addListener(new CompareFileFilter(TourHeader.kEndDate, screenRecord.getField(TourHeaderScreenRecord.kStartDepartureDate), ">=", null, true));
        screenRecord.getField(TourHeaderScreenRecord.kStartDepartureDate).addListener(new FieldReSelectHandler(this));
        recTourHeader.addListener(new CompareFileFilter(TourHeader.kStartDate, screenRecord.getField(TourHeaderScreenRecord.kEndDepartureDate), "<=", null, true));
        screenRecord.getField(TourHeaderScreenRecord.kEndDepartureDate).addListener(new FieldReSelectHandler(this));
        recTourHeader.addListener(new CompareFileFilter(TourHeader.kRegionID, screenRecord.getField(TourHeaderScreenRecord.kRegionID), "=", null, true));
        screenRecord.getField(TourHeaderScreenRecord.kRegionID).addListener(new FieldReSelectHandler(this));
        recTourHeader.addListener(new CompareFileFilter(TourHeader.kVendorID, screenRecord.getField(TourHeaderScreenRecord.kVendorID), "=", null, true));
        screenRecord.getField(TourHeaderScreenRecord.kVendorID).addListener(new FieldReSelectHandler(this));
        
        this.getMainRecord().addListener(new ExtractRangeFilter(Product.kDescSort, this.getScreenRecord().getField(ProductScreenRecord.kDescription)));
        screenRecord.getField(TourHeaderScreenRecord.kDescription).addListener(new FieldReSelectHandler(this));
        FieldListener behInitOnChange = new InitOnChangeHandler(screenRecord.getField(TourHeaderScreenRecord.kDescription));
        screenRecord.getField(TourHeaderScreenRecord.kKeyOrder).addListener(behInitOnChange);
        recTourHeader.addListener(new FileRemoveBOnCloseHandler(behInitOnChange));
        
        this.getScreenRecord().getField(TourHeaderScreenRecord.kDisplayType).setValue(DisplayTypeField.TOUR_HEADER_DISPLAY, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
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
        
        this.getScreenRecord().getField(TourHeaderScreenRecord.kDisplayType).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(TourHeaderScreenRecord.kDescription).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(TourHeaderScreenRecord.kStartDepartureDate).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        
        this.getScreenRecord().getField(TourHeaderScreenRecord.kBrochureID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(TourHeaderScreenRecord.kAirlineID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        
        this.getScreenRecord().getField(TourHeaderScreenRecord.kTourType).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(TourHeaderScreenRecord.kEndDepartureDate).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        
        this.getScreenRecord().getField(ProductScreenRecord.kVendorID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(TourHeaderScreenRecord.kRegionID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        
        this.getScreenRecord().getField(ProductScreenRecord.kDetailDate).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(ProductScreenRecord.kRemoteQueryEnabled).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
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
        
        strCommand = this.saveProductParam(strCommand, TourHeaderScreenRecord.kKeyOrder);
        
        return strCommand;
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Converter converter = new FieldLengthConverter(this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kDescription), 30);
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kStartDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kEndDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kDays).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kNights).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kDisplayCostStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeader.kTourHeaderFile).getField(TourHeader.kProductCostLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
