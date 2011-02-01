/**
 *  @(#)TourHeaderSearchSession.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.thin.app.booking.entry.search.*;
import com.tourapp.tour.base.db.*;
import java.rmi.*;
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
    public TourHeaderSearchSession(BaseSession parentSessionObject, Record record, Object objectID) throws RemoteException
    {
        this();
        this.init(parentSessionObject, record, objectID);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseSession parentSessionObject, Record record, Object objectID)
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
        if (this.getRecord(BookingControl.kBookingControlFile) == null)
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
        if (this.getScreenRecord().getField(TourHeaderScreenRecord.kTourType).isNull())
            this.getScreenRecord().getField(TourHeaderScreenRecord.kTourType).moveFieldToThis(this.getRecord(BookingControl.kBookingControlFile).getField(BookingControl.kThinTourType));
        
        this.getMainRecord().addListener(new ExtractRangeFilter(Product.kDescSort, this.getScreenRecord().getField(ProductScreenRecord.kDescription)));
        this.getMainRecord().addListener(new BitFileFilter(TourHeader.kTourType, this.getScreenRecord().getField(TourHeaderScreenRecord.kTourType)));
        
        this.getMainRecord().addListener(new CompareFileFilter(TourHeader.kEndDate, this.getScreenRecord().getField(TourHeaderScreenRecord.kStartDepartureDate), FileFilter.GREATER_THAN_EQUAL, null, true));
    }
    /**
     * Select the fields required for the grid screen.
     */
    public void selectGridFields()
    {
        super.selectGridFields();
        Record record = this.getMainRecord();
        
        record.getField(TourHeader.kTourType).setSelected(true);
        record.getField(TourHeader.kProductCatID).setSelected(true);
        record.getField(TourHeader.kTourClassID).setSelected(true);
        
        record.getField(TourHeader.kDays).setSelected(true);
        
        this.getRecord(TourType.kTourTypeFile).setKeyArea(TourType.kDescriptionKey);
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
                this.getScreenRecord().getField(TourHeaderScreenRecord.kTourType).setValue(1 << sBitPosition);
            else
                strTourType = null;
        }
        if (strTourType == null)
            this.getScreenRecord().getField(TourHeaderScreenRecord.kTourType).moveFieldToThis(this.getRecord(BookingControl.kBookingControlFile).getField(BookingControl.kThinTourType));
        
        ((DateTimeField)this.getScreenRecord().getField(TourHeaderScreenRecord.kStartDepartureDate)).setValue(DateTimeField.todaysDate());
        
        this.addThisRecordFilter(properties, Country.kCountryFile, JTreePanel.LOCATION, TourHeader.kCountryID, TourHeaderScreenRecord.kCountryID);
        this.addThisRecordFilter(properties, Region.kRegionFile, JTreePanel.LOCATION, TourHeader.kRegionID, TourHeaderScreenRecord.kRegionID);
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
