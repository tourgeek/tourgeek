/**
 * @(#)TourHeaderOptionScreen.
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
 *  TourHeaderOptionScreen - Tour product options.
 */
public class TourHeaderOptionScreen extends BaseFolderScreen
{
    public static final String TOUR_OR_OPTION = "TourOrOption";
    /**
     * Default constructor.
     */
    public TourHeaderOptionScreen()
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
    public TourHeaderOptionScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        String strOption = this.getMainRecord().getField(TourHeaderOption.kTourOrOption).toString();
        StringField fldTourOrOption = new StringField(null, "TourOrOption", 1, null, null);
        fldTourOrOption.setString(strOption);
        if (TourHeaderOption.TOUR.equals(strOption))
            m_recHeader = new TourHeader(this);
        else
        {
            String strType = this.getProperty(TourHeaderOptionScreen.TOUR_OR_OPTION);
            if (strType == null)
                strType = strOption;
            if (TourHeaderOption.OPTION.equals(strType))
                m_recHeader = new TourHeaderOption(this);
            else
                m_recHeader = new TourHeader(this);
        }
        Object objHandle = this.getMainRecord().getField(TourHeaderOption.kTourOrOptionID).getData();
        try {
            m_recHeader.setHandle(objHandle, DBConstants.BOOKMARK_HANDLE);
        } catch (DBException ex) {
            ex.printStackTrace();
        }
        this.getMainRecord().addListener(new FreeOnFreeHandler(fldTourOrOption));
        this.getMainRecord().addListener(new SubFileFilter(fldTourOrOption, TourHeaderOption.kTourOrOption, (BaseField)this.getHeaderRecord().getCounterField(), TourHeaderOption.kTourOrOptionID, null, -1));
        super.openOtherRecords(); // Added
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
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getMainRecord().setKeyArea(TourHeaderOption.kTourOrOptionKey);
        StringField fldTourOrOption = new StringField(null, TourHeaderOptionScreen.TOUR_OR_OPTION, 1, null, null);
        if (this.getHeaderRecord() instanceof TourHeader)
            fldTourOrOption.setString(TourHeaderOption.TOUR);
        else
        {
            fldTourOrOption.setString(TourHeaderOption.OPTION);
            this.getMainRecord().addListener(new SubCountHandler(this.getHeaderRecord().getField(TourHeaderOption.kDetailOptionCount), false, true));
        }
        ((ReferenceField)this.getMainRecord().getField(TourHeaderOption.kTourOrOptionID)).setReferenceRecord(m_recHeader);
        
        this.getMainRecord().addListener(new FreeOnFreeHandler(fldTourOrOption));
        this.getMainRecord().addListener(new SubFileFilter(fldTourOrOption, TourHeaderOption.kTourOrOption, (BaseField)this.getHeaderRecord().getCounterField(), TourHeaderOption.kTourOrOptionID, null, -1));
        
        ((TourHeaderOption)this.getMainRecord()).addSubFileIntegrityHandlers();
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
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(TourHeaderOption.kTourHeaderOptionFile).getField(TourHeaderOption.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderOption.kTourHeaderOptionFile).getField(TourHeaderOption.kSequence).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderOption.kTourHeaderOptionFile).getField(TourHeaderOption.kAskForAnswer).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderOption.kTourHeaderOptionFile).getField(TourHeaderOption.kAlwaysResolve).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderOption.kTourHeaderOptionFile).getField(TourHeaderOption.kStartDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderOption.kTourHeaderOptionFile).getField(TourHeaderOption.kEndDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderOption.kTourHeaderOptionFile).getField(TourHeaderOption.kDaysOfWeek).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderOption.kTourHeaderOptionFile).getField(TourHeaderOption.kGateway).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderOption.kTourHeaderOptionFile).getField(TourHeaderOption.kUseTourHeaderOptionID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderOption.kTourHeaderOptionFile).getField(TourHeaderOption.kPaxCategoryID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderOption.kTourHeaderOptionFile).getField(TourHeaderOption.kTourClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderOption.kTourHeaderOptionFile).getField(TourHeaderOption.kComments).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderOption.kTourHeaderOptionFile).getField(TourHeaderOption.kInvoiceText).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderOption.kTourHeaderOptionFile).getField(TourHeaderOption.kItineraryDesc).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
