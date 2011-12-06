/**
 * @(#)ProductSession.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.remote;

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
import org.jbundle.base.remote.opt.*;
import org.jbundle.thin.base.remote.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.base.remote.*;

/**
 *  ProductSession - Product session.
 */
public class ProductSession extends TableModelSession
{
    /**
     * Default constructor.
     */
    public ProductSession() throws RemoteException
    {
        super();
    }
    /**
     * ProductSession Method.
     */
    public ProductSession(BaseSession parentSessionObject, Record record, Object objectID) throws RemoteException
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
     * Get the item description.
     */
    public String getDescription() throws RemoteException
    {
        Product recProduct = (Product)this.setRecordCurrent();
        return recProduct.getField(Product.kDescription).toString();
    }
    /**
     * Get the ID of this object. For example, if object was an employee you might return 'EmployeeID=43332'.
     * This is a server defined string, so supply whatever it takes for you to find this object.
     * <P><B>NOTE: Make sure you return an object that implements Serializable (like String).</B></P>.
     */
    public Object getID() throws RemoteException
    {
        return null;    // Add code
    }
    /**
     * Get the product type.
     * This violates OO rules, but it is convenient to have.
     */
    public String getProductType() throws RemoteException
    {
        Product recProduct = (Product)this.setRecordCurrent();
        String strClass = null;
        if (recProduct != null)
        {
            strClass = recProduct.getClass().getName();
            if (strClass.lastIndexOf('.') != -1)
            {
                strClass = strClass.substring(strClass.lastIndexOf('.') + 1);
            }
        }
        return strClass;
    }
    /**
     * Set the description URL.
     */
    public void setDescriptionURL(String strDescriptionURL, int iType) throws RemoteException
    {
        // Add code
    }
    /**
     * The the object ID. This method is usually only supported locally.
     */
    public void setID(Object objectID) throws RemoteException
    {
        // Add code
    }

}
