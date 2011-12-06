/**
 * @(#)LandSearchSession.
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
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.product.remote.*;
import org.jbundle.thin.base.remote.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.thin.app.booking.entry.search.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.land.event.*;
import org.jbundle.base.remote.*;
import org.jbundle.thin.opt.location.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.event.*;
import com.tourapp.tour.product.land.screen.*;
import com.tourapp.tour.product.base.screen.*;

/**
 *  LandSearchSession - The remote side of the thin land search.
 */
public class LandSearchSession extends ProductSearchSession
{
    /**
     * Default constructor.
     */
    public LandSearchSession() throws RemoteException
    {
        super();
    }
    /**
     * LandSearchSession Method.
     */
    public LandSearchSession(BaseSession parentSessionObject, Record record, Object objectID) throws RemoteException
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
        Record record = new Land(this);
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
        new City(this);   // Thin display screen uses this for depart and arrive city.
    }
    /**
     * Add the screen record to this session.
     */
    public Record addScreenRecord()
    {
        return new LandScreenRecord(this);
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getMainRecord().addListener(new ExtractRangeFilter(Product.kDescSort, this.getScreenRecord().getField(ProductScreenRecord.kDescription)));
        this.getMainRecord().addListener(new CompareFileFilter(Product.kCityID, this.getScreenRecord().getField(ProductScreenRecord.kCityID), DBConstants.EQUALS, null, true));
    }
    /**
     * AddPriceListeners Method.
     */
    public void addPriceListeners(Product recProduct)
    {
        super.addPriceListeners(recProduct);
        recProduct.getField(Land.kPMCPriceHome).addListener(new AddCommissionHandler(null));
        recProduct.getField(Land.kSICPriceHome).addListener(new AddCommissionHandler(null));
    }
    /**
     * Select the fields required for the grid screen.
     */
    public void selectGridFields()
    {
        super.selectGridFields();
        Record record = this.getMainRecord();
        
        record.getField(Land.kPMCCost).setSelected(true);
        record.getField(Land.kSICCost).setSelected(true);
        record.getField(Land.kPMCCostHome).setSelected(true);
        record.getField(Land.kSICCostHome).setSelected(true);
        record.getField(Land.kPMCPriceHome).setSelected(true);
        record.getField(Land.kSICPriceHome).setSelected(true);
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
     * Add the listeners and message queues for rate lookups.
     */
    public void addRateMessageListeners(Product recProduct, ProductScreenRecord screenRecord)
    {
        super.addRateMessageListeners(recProduct, screenRecord);
        // Override this to add the listeners and message queues (remember to call super)
        this.getMainRecord().getField(Land.kPMCCost).setSelected(true);  // Now you can calc the USD amount (since you have this local amount)
        this.getMainRecord().getField(Land.kPMCCost).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Land.kPMCCostHome)));
        this.getMainRecord().getField(Land.kSICCost).setSelected(true);  // Now you can calc the USD amount (since you have this local amount)
        this.getMainRecord().getField(Land.kSICCost).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Land.kSICCostHome)));
    }
    /**
     * AddProductRateMessageFilter Method.
     */
    public ProductRateMessageListener addProductRateMessageFilter(Product recProduct, BaseMessageFilter messageFilter)
    {
        return new LandRateMessageListener(null, recProduct, false, messageFilter);
    }
    /**
     * GetProductCostHandler Method.
     */
    public GetProductCostHandler getProductCostHandler(ProductScreenRecord screenRecord, Integer intRegistryID)
    {
        return new GetLandCostHandler(screenRecord, intRegistryID);
    }
    /**
     * SetScreenFields Method.
     */
    public void setScreenFields(Map<String,Object> properties)
    {
        super.setScreenFields(properties);
        Record recProduct = this.getMainRecord();
        String strPax = (String)properties.get(SearchConstants.PAX);
        this.getScreenRecord().getField(ProductScreenRecord.kPax).setString(strPax);
        
        Record recProductControl = this.getRecord(ProductControl.kProductControlFile);
        this.getScreenRecord().getField(ProductScreenRecord.kRateID).moveFieldToThis(recProductControl.getField(ProductControl.kLandRateID));
    }

}
