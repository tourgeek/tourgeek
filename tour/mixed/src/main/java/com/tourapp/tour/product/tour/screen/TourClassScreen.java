/**
 *  @(#)TourClassScreen.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
        this.getMainRecord().getField(TourClass.kAirRateID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kAirRateID)));
        this.getMainRecord().getField(TourClass.kAirClassID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kAirClassID)));
        this.getMainRecord().getField(TourClass.kHotelRateID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kHotelRateID)));
        this.getMainRecord().getField(TourClass.kHotelClassID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kHotelClassID)));
        this.getMainRecord().getField(TourClass.kLandRateID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kLandRateID)));
        this.getMainRecord().getField(TourClass.kLandClassID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kLandClassID)));
        this.getMainRecord().getField(TourClass.kPMCCutoff).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kPMCCutoff)));
        this.getMainRecord().getField(TourClass.kCarRateID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kCarRateID)));
        this.getMainRecord().getField(TourClass.kCarClassID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kCarClassID)));
        this.getMainRecord().getField(TourClass.kTransportationRateID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kTransportationRateID)));
        this.getMainRecord().getField(TourClass.kTransportationClassID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kTransportationClassID)));
        this.getMainRecord().getField(TourClass.kCruiseRateID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kCruiseRateID)));
        this.getMainRecord().getField(TourClass.kCruiseClassID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kCruiseClassID)));
        this.getMainRecord().getField(TourClass.kItemRateID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kItemRateID)));
        this.getMainRecord().getField(TourClass.kItemClassID).addListener(new InitFieldHandler(this.getRecord(ProductControl.kProductControlFile).getField(ProductControl.kItemClassID)));
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
