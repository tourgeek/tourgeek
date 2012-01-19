/**
 * @(#)ProductTypeHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.detail.event;

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
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.db.*;

/**
 *  ProductTypeHandler - Get the product type of this detail record and return it as a string.
This is used to fill in the virtual ProductType field in BookingDetail..
 */
public class ProductTypeHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public ProductTypeHandler()
    {
        super();
    }
    /**
     * ProductTypeHandler Method.
     */
    public ProductTypeHandler(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        super.init(field);
    }
    /**
     * Get the product type of this detail record.
     * This is used to fill in the virtual ProductType field in BookingDetail.
     */
    public Object doGetData()
    {
        String strDesc = DBConstants.BLANK;
        Record recBookingDetail = (BookingSub)this.getOwner().getRecord();
        Record recProductType = ((ReferenceField)recBookingDetail.getField(BookingDetail.kProductTypeID)).getReference();
        if ((recProductType == null)
            || (recProductType.getEditMode() == DBConstants.EDIT_ADD)
            || (recProductType.getEditMode() == DBConstants.EDIT_NONE))
        {
            strDesc = recBookingDetail.getRecordName();
            if (strDesc.indexOf(Booking.kBookingFile) == 0)
                strDesc = strDesc.substring(Booking.kBookingFile.length());
        }
        else
            strDesc = recProductType.getField(ProductType.kDescription).toString();
        if (this.getOwner().getRecord().isModified())
            this.getOwner().setData(strDesc);
        else
        { // Make sure you don't trigger any field or file changes by setting this here
            boolean[] listenersState = this.getOwner().setEnableListeners(false);
            this.getOwner().setData(strDesc, Constants.DISPLAY, Constants.INIT_MOVE);
            this.getOwner().setModified(false);
            this.getOwner().setEnableListeners(listenersState);
        }
        return super.doGetData();
    }

}
