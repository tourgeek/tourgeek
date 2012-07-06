/**
  * @(#)CarSearchSession.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import com.tourapp.tour.product.car.db.*;
import com.tourapp.tour.product.remote.*;
import org.jbundle.thin.base.remote.*;
import org.jbundle.base.remote.*;
import org.jbundle.thin.opt.location.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.event.*;
import com.tourapp.tour.product.car.event.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.car.screen.*;
import com.tourapp.thin.app.booking.entry.search.*;

/**
 *  CarSearchSession - .
 */
public class CarSearchSession extends ProductSearchSession
{
    /**
     * Default constructor.
     */
    public CarSearchSession() throws RemoteException
    {
        super();
    }
    /**
     * CarSearchSession Method.
     */
    public CarSearchSession(BaseSession parentSessionObject, Record record, Map<String, Object> objectID) throws RemoteException
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
        Record record = new Car(this);
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
        return new CarScreenRecord(this);
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners(); 
        
        ((ReferenceField)this.getScreenRecord().getField(ProductScreenRecord.CLASS_ID)).getReferenceRecord(this);
        
        this.getMainRecord().addListener(new ExtractRangeFilter(Product.DESC_SORT, this.getScreenRecord().getField(ProductScreenRecord.DESCRIPTION)));
        this.getMainRecord().addListener(new CompareFileFilter(TransportProduct.CITY_ID, this.getScreenRecord().getField(ProductScreenRecord.CITY_ID), DBConstants.EQUALS, null, true));
        this.getMainRecord().addListener(new CompareFileFilter(TransportProduct.TO_CITY_ID, this.getScreenRecord().getField(ProductScreenRecord.TO_CITY_ID), DBConstants.EQUALS, null, true));
    }
    /**
     * Select the fields required for the grid screen.
     */
    public void selectGridFields()
    {
        super.selectGridFields();
        Record record = this.getMainRecord();
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
     * SetScreenFields Method.
     */
    public void setScreenFields(Map<String,Object> properties)
    {
        super.setScreenFields(properties);
        Record recProduct = this.getMainRecord();
        
        String strCarClass = (String)properties.get(SearchConstants.CAR_CLASS);
        this.getScreenRecord().getField(ProductScreenRecord.CLASS_ID).setString(strCarClass);
        
        Record recProductControl = this.getRecord(ProductControl.PRODUCT_CONTROL_FILE);
        this.getScreenRecord().getField(ProductScreenRecord.RATE_ID).moveFieldToThis(recProductControl.getField(ProductControl.CAR_RATE_ID));
    }

}
