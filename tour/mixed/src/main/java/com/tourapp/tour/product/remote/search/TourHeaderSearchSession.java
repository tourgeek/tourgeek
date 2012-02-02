/**
 * @(#)TourHeaderSearchSession.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.remote.search;

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
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.thin.app.booking.entry.search.*;
import com.tourapp.tour.base.db.*;
import org.jbundle.thin.base.remote.*;
import com.tourapp.tour.product.remote.*;
import org.jbundle.base.remote.*;
import org.jbundle.thin.opt.location.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.tour.event.*;
import com.tourapp.tour.product.base.event.*;
import com.tourapp.tour.product.tour.screen.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  TourHeaderSearchSession - Remote side of the tour header search.
 */
public class TourHeaderSearchSession extends ProductSearchSession
{
    /**
     * Default constructor.
     */
    public TourHeaderSearchSession() throws RemoteException
    {
        super();
    }
    /**
     * TourHeaderSearchSession Method.
     */
    public TourHeaderSearchSession(BaseSession parentSessionObject, Record record, Map<String, Object> objectID) throws RemoteException
    {
        this();
        this.init(parentSessionObject, record, objectID);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseSession parentSessionObject, Record record, Map<String, Object> objectID)
    {
        super.init(parentSessionObject, record, objectID);
    }
    /**
     * Override this to open the main file for this session.
     */
    public Record openMainRecord()
    {
        Record record = new TourHeader(this);
        try   {   // Wrap the record in a ProductSessionObject so the default TableSesionObject isn't used.
            ProductSession obj = new ProductSession(this, record, null);
        } catch (RemoteException ex)    {
            ex.printStackTrace();
        }
        return record;
    }
    /**
     * Override this to open other session files.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        if (this.getRecord(BookingControl.BOOKING_CONTROL_FILE) == null)
            new BookingControl(this);
        new TourType(this);
    }
    /**
     * Add the screen record to this session.
     */
    public Record addScreenRecord()
    {
        return new TourHeaderScreenRecord(this);
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
        if (this.getScreenRecord().getField(TourHeaderScreenRecord.TOUR_TYPE).isNull())
            this.getScreenRecord().getField(TourHeaderScreenRecord.TOUR_TYPE).moveFieldToThis(this.getRecord(BookingControl.BOOKING_CONTROL_FILE).getField(BookingControl.THIN_TOUR_TYPE));
        
        this.getMainRecord().addListener(new ExtractRangeFilter(Product.DESC_SORT, this.getScreenRecord().getField(ProductScreenRecord.DESCRIPTION)));
        this.getMainRecord().addListener(new BitFileFilter(TourHeader.TOUR_TYPE, this.getScreenRecord().getField(TourHeaderScreenRecord.TOUR_TYPE)));
        
        this.getMainRecord().addListener(new CompareFileFilter(TourHeader.END_DATE, this.getScreenRecord().getField(TourHeaderScreenRecord.START_DEPARTURE_DATE), FileFilter.GREATER_THAN_EQUAL, null, true));
    }
    /**
     * Select the fields required for the grid screen.
     */
    public void selectGridFields()
    {
        super.selectGridFields();
        Record record = this.getMainRecord();
        
        record.getField(TourHeader.TOUR_TYPE).setSelected(true);
        record.getField(TourHeader.PRODUCT_CAT_ID).setSelected(true);
        record.getField(TourHeader.TOUR_CLASS_ID).setSelected(true);
        
        record.getField(TourHeader.DAYS).setSelected(true);
        
        this.getRecord(TourType.TOUR_TYPE_FILE).setKeyArea(TourType.DESCRIPTION_KEY);
    }
    /**
     * Select the fields for the maint screen.
     */
    public void selectMaintFields()
    {
        super.selectMaintFields();
        Record record = this.getMainRecord();
    }
    /**
     * SetScreenFields Method.
     */
    public void setScreenFields(Map<String,Object> properties)
    {
        super.setScreenFields(properties);
        
        Record recTourHeader = this.getMainRecord();
        // Add the tour type filter
        String strTourType = (String)properties.get(SearchConstants.TOUR_TYPE);
        if (strTourType != null)
        {
            int sBitPosition = Integer.parseInt(strTourType);
            if (sBitPosition > 0)
                this.getScreenRecord().getField(TourHeaderScreenRecord.TOUR_TYPE).setValue(1 << sBitPosition);
            else
                strTourType = null;
        }
        if (strTourType == null)
            this.getScreenRecord().getField(TourHeaderScreenRecord.TOUR_TYPE).moveFieldToThis(this.getRecord(BookingControl.BOOKING_CONTROL_FILE).getField(BookingControl.THIN_TOUR_TYPE));
        
        ((DateTimeField)this.getScreenRecord().getField(TourHeaderScreenRecord.START_DEPARTURE_DATE)).setValue(DateTimeField.todaysDate());
        
        this.addThisRecordFilter(properties, Country.COUNTRY_FILE, JTreePanel.LOCATION, TourHeader.COUNTRY_ID, TourHeaderScreenRecord.COUNTRY_ID);
        this.addThisRecordFilter(properties, Region.REGION_FILE, JTreePanel.LOCATION, TourHeader.REGION_ID, TourHeaderScreenRecord.REGION_ID);
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

}
