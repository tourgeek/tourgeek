/**
 * @(#)HotelSearchSession.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.thin.app.booking.entry.search.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.hotel.event.*;
import com.tourapp.tour.product.remote.*;
import org.jbundle.thin.base.remote.*;
import org.jbundle.base.remote.*;
import org.jbundle.thin.opt.location.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.hotel.screen.*;
import com.tourapp.tour.product.base.event.*;
import com.tourapp.tour.product.base.screen.*;

/**
 *  HotelSearchSession - Remote part of the hotel display.
 */
public class HotelSearchSession extends ProductSearchSession
{
    /**
     * Default constructor.
     */
    public HotelSearchSession() throws RemoteException
    {
        super();
    }
    /**
     * HotelSearchSession Method.
     */
    public HotelSearchSession(BaseSession parentSessionObject, Record record, Map<String, Object> objectID) throws RemoteException
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
        Record record = new Hotel(this);
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
    }
    /**
     * Add the screen record to this session.
     */
    public Record addScreenRecord()
    {
        return new HotelScreenRecord(this);
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
        
        ((ReferenceField)this.getScreenRecord().getField(ProductScreenRecord.CLASS_ID)).getReferenceRecord(this);
        
        this.getMainRecord().addListener(new ExtractRangeFilter(Product.DESC_SORT, this.getScreenRecord().getField(ProductScreenRecord.DESCRIPTION)));
        this.getMainRecord().addListener(new CompareFileFilter(Product.CITY_ID, this.getScreenRecord().getField(ProductScreenRecord.CITY_ID), DBConstants.EQUALS, null, true));
    }
    /**
     * AddPriceListeners Method.
     */
    public void addPriceListeners(Product recProduct)
    {
        super.addPriceListeners(recProduct);
        recProduct.getField(Hotel.SINGLE_PRICE_LOCAL).addListener(new AddCommissionHandler(null));
        recProduct.getField(Hotel.DOUBLE_PRICE_LOCAL).addListener(new AddCommissionHandler(null));
        recProduct.getField(Hotel.TRIPLE_PRICE_LOCAL).addListener(new AddCommissionHandler(null));
        recProduct.getField(Hotel.QUAD_PRICE_LOCAL).addListener(new AddCommissionHandler(null));
        recProduct.getField(Hotel.ROOM_PRICE_LOCAL).addListener(new AddCommissionHandler(null));
        recProduct.getField(Hotel.MEAL_PRICE_LOCAL).addListener(new AddCommissionHandler(null));
    }
    /**
     * Select the fields required for the grid screen.
     */
    public void selectGridFields()
    {
        super.selectGridFields();
        Record record = this.getMainRecord();
        
        record.getField(Hotel.SINGLE_COST).setSelected(true);
        record.getField(Hotel.DOUBLE_COST).setSelected(true);
        record.getField(Hotel.TRIPLE_COST).setSelected(true);
        record.getField(Hotel.QUAD_COST).setSelected(true);
        record.getField(Hotel.SINGLE_COST_LOCAL).setSelected(true);
        record.getField(Hotel.DOUBLE_COST_LOCAL).setSelected(true);
        record.getField(Hotel.TRIPLE_COST_LOCAL).setSelected(true);
        record.getField(Hotel.QUAD_COST_LOCAL).setSelected(true);
        record.getField(Hotel.SINGLE_PRICE_LOCAL).setSelected(true);
        record.getField(Hotel.DOUBLE_PRICE_LOCAL).setSelected(true);
        record.getField(Hotel.TRIPLE_PRICE_LOCAL).setSelected(true);
        record.getField(Hotel.QUAD_PRICE_LOCAL).setSelected(true);
    }
    /**
     * Select the fields for the maint screen.
     */
    public void selectMaintFields()
    {
        super.selectMaintFields();
        Record record = this.getMainRecord();
        if (this.getMainRecord().getField(Hotel.SINGLE_COST).getListener(CalcProductAmountHome.class) == null)
        {
            this.getMainRecord().getField(Hotel.SINGLE_COST).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Hotel.SINGLE_COST_LOCAL)));
            this.getMainRecord().getField(Hotel.TRIPLE_COST).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Hotel.TRIPLE_COST_LOCAL)));
            this.getMainRecord().getField(Hotel.QUAD_COST).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Hotel.QUAD_COST_LOCAL)));
            this.getMainRecord().getField(Hotel.ROOM_COST).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Hotel.ROOM_COST_LOCAL)));
            this.getMainRecord().getField(Hotel.MEAL_COST).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Hotel.MEAL_COST_LOCAL)));
        }
    }
    /**
     * Add the listeners and message queues for rate lookups.
     */
    public void addRateMessageListeners(Product recProduct, ProductScreenRecord screenRecord)
    {
        super.addRateMessageListeners(recProduct, screenRecord);
        // Override this to add the listeners and message queues (remember to call super)
        this.getMainRecord().getField(Hotel.DOUBLE_COST).setSelected(true);  // Now you can calc the USD amount (since you have this local amount)
        this.getMainRecord().getField(Hotel.DOUBLE_COST).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Hotel.DOUBLE_COST_LOCAL)));
    }
    /**
     * AddProductRateMessageFilter Method.
     */
    public ProductRateMessageListener addProductRateMessageFilter(Product recProduct, BaseMessageFilter messageFilter)
    {
        return new HotelRateMessageListener(null, recProduct, false, messageFilter);
    }
    /**
     * GetProductCostHandler Method.
     */
    public GetProductCostHandler getProductCostHandler(ProductScreenRecord screenRecord, Integer intRegistryID)
    {
        return new GetHotelCostHandler(screenRecord, intRegistryID);
    }
    /**
     * AddProductAvailabilityMessageFilter Method.
     */
    public ProductAvailabilityMessageListener addProductAvailabilityMessageFilter(Product recProduct, BaseMessageFilter messageFilter)
    {
        return new HotelAvailabilityMessageListener(null, recProduct, false, messageFilter);
    }
    /**
     * GetProductAvailabilityHandler Method.
     */
    public GetProductAvailabilityHandler getProductAvailabilityHandler(ProductScreenRecord screenRecord, Integer intRegistryID)
    {
        return new GetHotelAvailabilityHandler(screenRecord, intRegistryID);
    }
    /**
     * SetScreenFields Method.
     */
    public void setScreenFields(Map<String,Object> properties)
    {
        super.setScreenFields(properties);
        Record recProduct = this.getMainRecord();
        
        String strHotelClass = (String)properties.get(SearchConstants.HOTEL_CLASS);
        this.getScreenRecord().getField(ProductScreenRecord.CLASS_ID).setString(strHotelClass);
        
        Record recProductControl = this.getRecord(ProductControl.PRODUCT_CONTROL_FILE);
        this.getScreenRecord().getField(ProductScreenRecord.RATE_ID).moveFieldToThis(recProductControl.getField(ProductControl.HOTEL_RATE_ID));
    }

}
