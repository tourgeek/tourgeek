/**
 * @(#)HotelSearchSession.
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
    public HotelSearchSession(BaseSession parentSessionObject, Record record, Object objectID) throws RemoteException
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
        
        ((ReferenceField)this.getScreenRecord().getField(ProductScreenRecord.kClassID)).getReferenceRecord(this);
        
        this.getMainRecord().addListener(new ExtractRangeFilter(Product.kDescSort, this.getScreenRecord().getField(ProductScreenRecord.kDescription)));
        this.getMainRecord().addListener(new CompareFileFilter(Product.kCityID, this.getScreenRecord().getField(ProductScreenRecord.kCityID), DBConstants.EQUALS, null, true));
    }
    /**
     * AddPriceListeners Method.
     */
    public void addPriceListeners(Product recProduct)
    {
        super.addPriceListeners(recProduct);
        recProduct.getField(Hotel.kSinglePriceLocal).addListener(new AddCommissionHandler(null));
        recProduct.getField(Hotel.kDoublePriceLocal).addListener(new AddCommissionHandler(null));
        recProduct.getField(Hotel.kTriplePriceLocal).addListener(new AddCommissionHandler(null));
        recProduct.getField(Hotel.kQuadPriceLocal).addListener(new AddCommissionHandler(null));
        recProduct.getField(Hotel.kRoomPriceLocal).addListener(new AddCommissionHandler(null));
        recProduct.getField(Hotel.kMealPriceLocal).addListener(new AddCommissionHandler(null));
    }
    /**
     * Select the fields required for the grid screen.
     */
    public void selectGridFields()
    {
        super.selectGridFields();
        Record record = this.getMainRecord();
        
        record.getField(Hotel.kSingleCost).setSelected(true);
        record.getField(Hotel.kDoubleCost).setSelected(true);
        record.getField(Hotel.kTripleCost).setSelected(true);
        record.getField(Hotel.kQuadCost).setSelected(true);
        record.getField(Hotel.kSingleCostLocal).setSelected(true);
        record.getField(Hotel.kDoubleCostLocal).setSelected(true);
        record.getField(Hotel.kTripleCostLocal).setSelected(true);
        record.getField(Hotel.kQuadCostLocal).setSelected(true);
        record.getField(Hotel.kSinglePriceLocal).setSelected(true);
        record.getField(Hotel.kDoublePriceLocal).setSelected(true);
        record.getField(Hotel.kTriplePriceLocal).setSelected(true);
        record.getField(Hotel.kQuadPriceLocal).setSelected(true);
    }
    /**
     * Select the fields for the maint screen.
     */
    public void selectMaintFields()
    {
        super.selectMaintFields();
        Record record = this.getMainRecord();
        if (this.getMainRecord().getField(Hotel.kSingleCost).getListener(CalcProductAmountHome.class) == null)
        {
            this.getMainRecord().getField(Hotel.kSingleCost).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Hotel.kSingleCostLocal)));
            this.getMainRecord().getField(Hotel.kTripleCost).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Hotel.kTripleCostLocal)));
            this.getMainRecord().getField(Hotel.kQuadCost).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Hotel.kQuadCostLocal)));
            this.getMainRecord().getField(Hotel.kRoomCost).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Hotel.kRoomCostLocal)));
            this.getMainRecord().getField(Hotel.kMealCost).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Hotel.kMealCostLocal)));
        }
    }
    /**
     * Add the listeners and message queues for rate lookups.
     */
    public void addRateMessageListeners(Product recProduct, ProductScreenRecord screenRecord)
    {
        super.addRateMessageListeners(recProduct, screenRecord);
        // Override this to add the listeners and message queues (remember to call super)
        this.getMainRecord().getField(Hotel.kDoubleCost).setSelected(true);  // Now you can calc the USD amount (since you have this local amount)
        this.getMainRecord().getField(Hotel.kDoubleCost).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Hotel.kDoubleCostLocal)));
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
        this.getScreenRecord().getField(ProductScreenRecord.kClassID).setString(strHotelClass);
        
        Record recProductControl = this.getRecord(ProductControl.kProductControlFile);
        this.getScreenRecord().getField(ProductScreenRecord.kRateID).moveFieldToThis(recProductControl.getField(ProductControl.kHotelRateID));
    }

}
