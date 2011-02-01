/*
 * ProductTransferable.java
 *
 * Created on August 17, 2004, 1:36 AM
 */

package com.tourapp.thin.app.booking.entry.search.base;

import java.awt.Container;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import org.jbundle.thin.base.db.FieldList;
import org.jbundle.util.calendarpanel.dnd.CalendarItemProperties;
import org.jbundle.util.calendarpanel.dnd.CalendarItemTransferable;
import org.jbundle.thin.base.screen.grid.JGridScreen;

import com.tourapp.thin.tour.product.base.db.Product;

/**
 * This class packages up a product into a ProductTransferable object.
 */
public class ProductTransferHandler extends TransferHandler
{
	private static final long serialVersionUID = 1L;

    /**
     * Standard constructor.
     */
    public ProductTransferHandler(String strType)
    {
        super(strType);
    }
    /**
     * Actions that I support.
     */
    public int getSourceActions(JComponent c)
    {
        return COPY_OR_MOVE;
    }
    /**
     * Create the transferable object using this source object.
     */
    protected Transferable createTransferable(JComponent c)
    {
        return new CalendarItemTransferable(this.exportProduct(c));
    }
    /**
     * Transfer (paste) this data to this component.
     */
    public boolean importData(JComponent c, Transferable t)
    {
        return false;   // A calendar item is never a dnd destination.
    }
    /**
     * Figure out the product that is connected to this component and create a ProductProperties object to transfer it.
     */
    protected CalendarItemProperties exportProduct(JComponent c)
    {
        Container container = c.getParent();
        while (container != null)
        {
            if (container instanceof JGridScreen)
            {
                FieldList fieldList = ((JGridScreen)container).makeSelectedRowCurrent();
                if (fieldList != null)
                    if (fieldList.getField(Product.PRODUCT_TYPE) != null)
                    {
                        ProductItemProperties calendarItemProperties = new ProductItemProperties(null);
                        calendarItemProperties.setProductInfo(fieldList);
                        return calendarItemProperties;
                    }
            }
            container = container.getParent();
        }
        return null;
    }
}
