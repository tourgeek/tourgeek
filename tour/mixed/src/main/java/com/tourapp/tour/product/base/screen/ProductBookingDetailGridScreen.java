/**
 * @(#)ProductBookingDetailGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.screen;

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
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.db.*;

/**
 *  ProductBookingDetailGridScreen - .
 */
public class ProductBookingDetailGridScreen extends ProductDetailGridScreen
{
    /**
     * Default constructor.
     */
    public ProductBookingDetailGridScreen()
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
    public ProductBookingDetailGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * ProductBookingDetailGridScreen Method.
     */
    public ProductBookingDetailGridScreen(Record recProduct, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recProduct, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new BookingDetail(this);   // Never - always called from product screen
    }
    /**
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        String strProductType = this.getProperty(Product.kProductFile);
        return Product.getProductRecord(strProductType, this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ProductType(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new ProductScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        SubFileFilter listener = null;
        this.getMainRecord().addListener(listener = new SubFileFilter(this.getRecord(ProductScreenRecord.kProductScreenRecordFile).getField(ProductScreenRecord.kStartDate), BookingDetail.kDetailDate, null, -1, null, -1));
        listener.setEndKey(false);
        
        if (this.getScreenRecord().getField(ProductScreenRecord.kStartDate).isNull())
            this.getScreenRecord().getField(ProductScreenRecord.kStartDate).setValue(DateTimeField.currentTime(), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        
        this.getRecord(ProductScreenRecord.kProductScreenRecordFile).getField(ProductScreenRecord.kStartDate).addListener(new FieldReSelectHandler(this));
        this.setAppending(false);
        this.setEditing(false);
    }
    /**
     * AddSubFileFilter Method.
     */
    public void addSubFileFilter()
    {
        // Override this if it is not correct.
        this.getMainRecord().setKeyArea(BookingDetail.kProductIDKey);
        Product recProduct = (Product)this.getHeaderRecord();
        ProductType recProductType = (ProductType)this.getRecord(ProductType.kProductTypeFile);
        recProductType.getProductTypeID(recProduct);
        this.getMainRecord().addListener(new SubFileFilter(recProductType.getField(ProductType.kID), BookingDetail.kProductTypeID, this.getHeaderRecord().getField(Product.kID), BookingDetail.kProductID, null, -1));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        ToolScreen screen = super.addToolbars();
        this.getScreenRecord().getField(ProductScreenRecord.kStartDate).setupDefaultView(screen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), screen, ScreenConstants.DEFAULT_DISPLAY);
        return screen;
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(BookingDetail.kBookingDetailFile).getField(BookingDetail.kStatusSummary).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingDetail.kBookingDetailFile).getField(BookingDetail.kDetailDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingDetail.kBookingDetailFile).getField(BookingDetail.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingDetail.kBookingDetailFile).getField(BookingDetail.kBookingID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        ((ReferenceField)this.getRecord(BookingDetail.kBookingDetailFile).getField(BookingDetail.kBookingID)).getReferenceRecord().getField(Booking.kBookingStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new ProductHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the command string to restore screen.
     */
    public String getScreenURL()
    {
        String strURL = super.getScreenURL();
        if (this.getRecord(ProductType.kProductTypeFile).getEditMode() == DBConstants.EDIT_CURRENT)
        {   // Always
            strURL = Utility.addURLParam(strURL, Product.kProductFile, this.getHeaderRecord().getTableNames(false));
        }
        return strURL;
    }

}
