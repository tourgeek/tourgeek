/*
 * ProductTransferable.java
 *
 * Created on August 17, 2004, 1:36 AM
 */

package com.tourapp.thin.app.booking.entry.search.base;

import java.awt.Component;
import java.awt.Container;
import java.util.Date;
import java.util.Map;

import org.jbundle.thin.base.db.FieldList;
import org.jbundle.util.calendarpanel.dnd.CalendarItemProperties;

import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.tour.product.base.db.Product;

/**
 * Object used to transfer data from here to there.
 */
public class ProductItemProperties extends CalendarItemProperties
{
	private static final long serialVersionUID = 1L;

	/**
     * Constructor.
     */
    public ProductItemProperties()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductItemProperties(Map<String,Object> m)
    {
        this();
        this.init(m);
    }
    /**
     * Constructor.
     */
    public void init(Map<String, Object> m)
    {
        super.init(m);
    }
    /**
     * This was dropped on a calendar pane, change the date to match.
     */
    public boolean setTargetDate(Object source, Date dateTarget)
    {
        Container container = ((Component)source).getParent();
        while (container != null)
        {
            if (container instanceof TourAppScreen)
            {
                if (this.get(Product.PRODUCT_TYPE) != null)
                {
                    String strProductType = (String)this.get(Product.PRODUCT_TYPE);
                    Integer intID = (Integer)this.get(Product.ID);
                    String strID = intID.toString();
                    ((TourAppScreen)container).addProductToSession(strProductType, strID, dateTarget);
                    return true;
                }
            }
            container = container.getParent();
        }
        return false;
    }
    /**
     *
     */
    public void setProductInfo(FieldList recProduct)
    {
        this.put(Product.PRODUCT_TYPE, recProduct.getField(Product.PRODUCT_TYPE).getData());
        this.put(Product.ID, recProduct.getField(Product.ID).getData());
    }
}
