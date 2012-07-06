/**
  * @(#)TourClassScreen.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.schedule.screen.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.tour.schedule.db.*;

/**
 *  TourClassScreen - .
 */
public class TourClassScreen extends Screen
{
    /**
     * Default constructor.
     */
    public TourClassScreen()
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
    public TourClassScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new TourClass(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ProductControl(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getMainRecord().getField(TourClass.AIR_RATE_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.AIR_RATE_ID)));
        this.getMainRecord().getField(TourClass.AIR_CLASS_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.AIR_CLASS_ID)));
        this.getMainRecord().getField(TourClass.HOTEL_RATE_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.HOTEL_RATE_ID)));
        this.getMainRecord().getField(TourClass.HOTEL_CLASS_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.HOTEL_CLASS_ID)));
        this.getMainRecord().getField(TourClass.LAND_RATE_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.LAND_RATE_ID)));
        this.getMainRecord().getField(TourClass.LAND_CLASS_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.LAND_CLASS_ID)));
        this.getMainRecord().getField(TourClass.PMC_CUTOFF).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.PMC_CUTOFF)));
        this.getMainRecord().getField(TourClass.CAR_RATE_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.CAR_RATE_ID)));
        this.getMainRecord().getField(TourClass.CAR_CLASS_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.CAR_CLASS_ID)));
        this.getMainRecord().getField(TourClass.TRANSPORTATION_RATE_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.TRANSPORTATION_RATE_ID)));
        this.getMainRecord().getField(TourClass.TRANSPORTATION_CLASS_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.TRANSPORTATION_CLASS_ID)));
        this.getMainRecord().getField(TourClass.CRUISE_RATE_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.CRUISE_RATE_ID)));
        this.getMainRecord().getField(TourClass.CRUISE_CLASS_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.CRUISE_CLASS_ID)));
        this.getMainRecord().getField(TourClass.ITEM_RATE_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.ITEM_RATE_ID)));
        this.getMainRecord().getField(TourClass.ITEM_CLASS_ID).addListener(new InitFieldHandler(this.getRecord(ProductControl.PRODUCT_CONTROL_FILE).getField(ProductControl.ITEM_CLASS_ID)));
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(TourEventSchedule.EVENTS), Booking.BUTTON_LOCATION + "Document", MenuConstants.FORMDETAIL, null);
    }

}
