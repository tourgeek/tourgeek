/**
  * @(#)TourHeaderTourGridScreen.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.tour.other.screen;

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
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.tour.booking.entry.tour.*;
import com.tourgeek.tour.booking.db.event.*;

/**
 *  TourHeaderTourGridScreen - Tour tour detail.
 */
public class TourHeaderTourGridScreen extends ProductDetailGridScreen
{
    /**
     * Default constructor.
     */
    public TourHeaderTourGridScreen()
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
    public TourHeaderTourGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Tour tour detail";
    }
    /**
     * TourHeaderTourGridScreen Method.
     */
    public TourHeaderTourGridScreen(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recHeader, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Tour(this);
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
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        return new TourHeader(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        // Note: Use the booking tour header display is most cases.
        // Eventually merge with some of this code.
        Tour recTour = (Tour)this.getRecord(Tour.TOUR_FILE);
        Record screenQuery = this.getScreenRecord();
        
        if (((NumberField)screenQuery.getField(TourHeaderScreenRecord.KEY_ORDER)).getValue() > 2)
            ((NumberField)screenQuery.getField(TourHeaderScreenRecord.KEY_ORDER)).setValue(0);
        FieldListener behCheckRange = new CheckRangeHandler(0, 2);
        screenQuery.getField(TourHeaderScreenRecord.KEY_ORDER).addListener(behCheckRange);
        recTour.addListener(new FileRemoveBOnCloseHandler(behCheckRange));
        
        SortOrderHandler behQueryKeyHandler = new SortOrderHandler(this);
        behQueryKeyHandler.setGridTable(Tour.DESCRIPTION_KEY, recTour, -1);
        behQueryKeyHandler.setGridTable(Tour.DEPARTURE_DATE_KEY, recTour, -1);
        screenQuery.getField(TourHeaderScreenRecord.KEY_ORDER).addListener(behQueryKeyHandler);
        
        this.getMainRecord().addListener(new CompareFileFilter(recTour.getField(Tour.DEPARTURE_DATE), screenQuery.getField(TourHeaderScreenRecord.START_DEPARTURE_DATE), ">=", null, true));
        screenQuery.getField(TourHeaderScreenRecord.START_DEPARTURE_DATE).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new CompareFileFilter(recTour.getField(Tour.DEPARTURE_DATE), screenQuery.getField(TourHeaderScreenRecord.END_DEPARTURE_DATE), "<=", null, true));
        screenQuery.getField(TourHeaderScreenRecord.END_DEPARTURE_DATE).addListener(new FieldReSelectHandler(this));
        
        TourHeader recTourHeader = (TourHeader)this.getRecord(TourHeader.TOUR_HEADER_FILE);
        this.getMainRecord().addListener(new BitFileFilter(recTourHeader.getField(TourHeader.TOUR_TYPE), screenQuery.getField(TourHeaderScreenRecord.TOUR_TYPE)));
        screenQuery.getField(TourHeaderScreenRecord.TOUR_TYPE).addListener(new FieldReSelectHandler(this));
        
        this.getMainRecord().addListener(new CompareFileFilter(TourHeader.DESCRIPTION, screenQuery.getField(TourHeaderScreenRecord.DESCRIPTION), ">=", null, true));
        screenQuery.getField(TourHeaderScreenRecord.DESCRIPTION).addListener(new FieldReSelectHandler(this));
        FieldListener behInitOnChange = new InitOnChangeHandler(screenQuery.getField(TourHeaderScreenRecord.DESCRIPTION));
        screenQuery.getField(TourHeaderScreenRecord.KEY_ORDER).addListener(behInitOnChange);
        recTour.addListener(new FileRemoveBOnCloseHandler(behInitOnChange));
        
        this.getScreenRecord().getField(TourHeaderScreenRecord.DISPLAY_TYPE).setValue(DisplayTypeField.TOUR_DISPLAY, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        
        screenQuery.getField(TourHeaderScreenRecord.PRODUCT_ID).addListener(new FieldReSelectHandler(this));
        
        this.setEditing(false);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        // Don't call super
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        
        this.getScreenRecord().getField(TourHeaderScreenRecord.DISPLAY_TYPE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(TourHeaderScreenRecord.DESCRIPTION).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(TourHeaderScreenRecord.START_DEPARTURE_DATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        
        this.getScreenRecord().getField(TourHeaderScreenRecord.TOUR_TYPE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(TourHeaderScreenRecord.END_DEPARTURE_DATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        
        //this.getScreenRecord().getField(ProductScreenRecord.DETAIL_DATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        //this.getScreenRecord().getField(ProductScreenRecord.REMOTE_QUERY_ENABLED).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Converter converter = this.getMainRecord().getField(Tour.TOUR_FILE, Tour.DESCRIPTION);
        converter = new FieldLengthConverter(converter, 30);
        this.addColumn(converter);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.DEPARTURE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        //converter = this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.TOUR_HEADER_FILE, TourHeader.DESCRIPTION);
        //converter = new FieldLengthConverter(converter, 30);
        //converter = new FieldDescConverter(converter, "Header Tour Desc");
        //this.addColumn(converter);
        //this.getRecord(Inventory.INVENTORY_FILE).getField(Inventory.BLOCKED).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        //this.getRecord(Inventory.INVENTORY_FILE).getField(Inventory.AVAILABLE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.TOUR_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new ProductHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}
