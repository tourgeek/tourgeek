/**
 * @(#)CarGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.car.screen;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.product.base.screen.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.event.*;
import com.tourapp.tour.product.car.event.*;
import com.tourapp.tour.product.car.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctpay.db.*;

/**
 *  CarGridScreen - Rental Car.
 */
public class CarGridScreen extends ProductGridScreen
{
    /**
     * Default constructor.
     */
    public CarGridScreen()
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
    public CarGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Rental Car";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Car(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new CarScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getMainRecord().addListener(new ExtractRangeFilter(Product.kDescSort, this.getScreenRecord().getField(ProductScreenRecord.kDescription)));
        this.getMainRecord().addListener(new CompareFileFilter(Product.kCityID, this.getScreenRecord().getField(ProductScreenRecord.kCityID), "=", null, true));
        this.getMainRecord().addListener(new CompareFileFilter(Product.kVendorID, this.getScreenRecord().getField(ProductScreenRecord.kVendorID), "=", null, true));
        
        this.getScreenRecord().getField(ProductScreenRecord.kVendorID).addListener(new FieldReSelectHandler(this));
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
        return new CarRateMessageListener(null, recProduct, false, messageFilter);
    }
    /**
     * GetProductCostHandler Method.
     */
    public GetProductCostHandler getProductCostHandler(ProductScreenRecord screenRecord, Integer intRegistryID)
    {
        return new GetCarCostHandler(screenRecord, intRegistryID);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        super.addToolbarButtons(toolScreen);
        this.getScreenRecord().getField(ProductScreenRecord.kDescription).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(ProductScreenRecord.kCityID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(ProductScreenRecord.kVendorID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        
        this.getScreenRecord().getField(ProductScreenRecord.kRateID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(ProductScreenRecord.kClassID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(ProductScreenRecord.kDetailDate).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(ProductScreenRecord.kRemoteQueryEnabled).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(Car.kCarFile).getField(Car.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Car.kCarFile).getField(Car.kDisplayCostStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Car.kCarFile).getField(Car.kProductCostLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Car.kCarFile).getField(Car.kCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Car.kCarFile).getField(Car.kVendorID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
