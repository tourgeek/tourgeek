/**
 * @(#)TourHeaderOptionGridScreen.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.main.screen.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.record.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.base.screen.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.db.*;

/**
 *  TourHeaderOptionGridScreen - Tour product options.
 */
public class TourHeaderOptionGridScreen extends BaseFolderGridScreen
{
    /**
     * Default constructor.
     */
    public TourHeaderOptionGridScreen()
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
    public TourHeaderOptionGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Tour product options";
    }
    /**
     * TourHeaderOptionGridScreen Method.
     */
    public TourHeaderOptionGridScreen(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
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
        return new TourHeaderOption(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        if (m_recHeader != null)
            this.addRecord(m_recHeader, false);
        else
        {
            String strType = this.getProperty(TourHeaderOptionScreen.TOUR_OR_OPTION);
            if (TourHeaderOption.OPTION.equals(strType))
                m_recHeader = new TourHeaderOption(this);
            else
                m_recHeader = new TourHeader(this);
        }
        ((ReferenceField)this.getMainRecord().getField(TourHeaderOption.TOUR_OR_OPTION_ID)).setReferenceRecord(m_recHeader);
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
     * AddSubFileFilter Method.
     */
    public void addSubFileFilter()
    {
        this.getMainRecord().setKeyArea(TourHeaderOption.TOUR_OR_OPTION_KEY);
        StringField fldTourOrOption = new StringField(null, TourHeaderOptionScreen.TOUR_OR_OPTION, 1, null, null);
        if (this.getHeaderRecord() instanceof TourHeader)
            fldTourOrOption.setString(TourHeaderOption.TOUR);
        else
        {
            fldTourOrOption.setString(TourHeaderOption.OPTION);
            this.getMainRecord().addListener(new SubCountHandler(this.getHeaderRecord().getField(TourHeaderOption.DETAIL_OPTION_COUNT), false, true));
        }
        this.getMainRecord().addListener(new FreeOnFreeHandler(fldTourOrOption));
        this.getMainRecord().addListener(new SubFileFilter(fldTourOrOption, TourHeaderOption.TOUR_OR_OPTION, (BaseField)this.getHeaderRecord().getCounterField(), TourHeaderOption.TOUR_OR_OPTION_ID, null, null));
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        ((TourHeaderOption)this.getMainRecord()).addSubFileIntegrityHandlers();
    }
    /**
     * Add the navigation button(s) to the left of the grid row.
     */
    public void addNavButtons()
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, Booking.BUTTON_LOCATION + ProductType.ITEM, ProductType.ITEM, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.ITEM));
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, Booking.BUTTON_LOCATION + ProductType.CRUISE, ProductType.CRUISE, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.CRUISE));
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, Booking.BUTTON_LOCATION + ProductType.TRANSPORTATION, ProductType.TRANSPORTATION, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.TRANSPORTATION));
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, Booking.BUTTON_LOCATION + ProductType.CAR, ProductType.CAR, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.CAR));
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, Booking.BUTTON_LOCATION + ProductType.LAND, ProductType.LAND, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.LAND));
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, Booking.BUTTON_LOCATION + ProductType.HOTEL, ProductType.HOTEL, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.HOTEL));
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, Booking.BUTTON_LOCATION + ProductType.AIR, ProductType.AIR, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.AIR));
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, Booking.BUTTON_LOCATION + ProductType.AIR + "Header", ProductType.AIR + "Header", application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.AIR + "Header"));
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, Booking.BUTTON_LOCATION + ProductType.TOUR, ProductType.TOUR, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.TOUR)); 
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, Booking.BUTTON_LOCATION + "Price", Product.PRICING_DETAIL, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.PRICING_DETAIL));
        super.addNavButtons();  // Next buttons will be "First!"
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        super.addToolbarButtons(toolScreen);
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.FLUSH_LEFT, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.PRICING_DETAIL), Booking.BUTTON_LOCATION + "Price", Product.PRICING_DETAIL, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.TOUR), Booking.BUTTON_LOCATION + ProductType.TOUR, ProductType.TOUR, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.AIR + "Header"), Booking.BUTTON_LOCATION + ProductType.AIR + "Header", ProductType.AIR + "Header", null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.AIR), Booking.BUTTON_LOCATION + ProductType.AIR, ProductType.AIR, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.HOTEL), Booking.BUTTON_LOCATION + ProductType.HOTEL, ProductType.HOTEL, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.LAND), Booking.BUTTON_LOCATION + ProductType.LAND, ProductType.LAND, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.CAR), Booking.BUTTON_LOCATION + ProductType.CAR, ProductType.CAR, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.TRANSPORTATION), Booking.BUTTON_LOCATION + ProductType.TRANSPORTATION, ProductType.TRANSPORTATION, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.CRUISE), Booking.BUTTON_LOCATION + ProductType.CRUISE, ProductType.CRUISE, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(ProductType.ITEM), Booking.BUTTON_LOCATION + ProductType.ITEM, ProductType.ITEM, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(TourHeaderOption.kTourHeaderOptionFile).getField(TourHeaderOption.kSequence).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderOption.kTourHeaderOptionFile).getField(TourHeaderOption.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        if (this.getHeaderRecord() instanceof TourHeader)
            return new ProductHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
        else
            return new TourHeaderOptionHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Create a data entry screen of this type.
     */
    public BasePanel onForm(Record recordMain, int iDocMode, boolean bReadCurrentRecord, int iCommandOptions, Map<String,Object> properties)
    {
        String strMessage = this.getProperty(MessageConstants.QUEUE_NAME);
        if (strMessage != null)
        {
            if (properties == null)
                properties = new Hashtable<String,Object>();
            properties.put(MessageConstants.QUEUE_NAME, strMessage);
            if (this.getProperty(RecordMessageConstants.TABLE_NAME) != null)
                properties.put(RecordMessageConstants.TABLE_NAME, this.getProperty(RecordMessageConstants.TABLE_NAME));
        }
        
        return super.onForm(recordMain, iDocMode, bReadCurrentRecord, iCommandOptions, properties);
    }
    /**
     * Get the command string to restore screen.
     */
    public String getScreenURL()
    {
        String strCommand = super.getScreenURL();
        
        String strValue = TourHeaderOption.OPTION;
        if (this.getHeaderRecord() instanceof TourHeader)
            strValue = TourHeaderOption.TOUR;
        strCommand = Utility.addURLParam(strCommand, TourHeaderOptionScreen.TOUR_OR_OPTION, strValue);
        
        return strCommand;
    }

}
