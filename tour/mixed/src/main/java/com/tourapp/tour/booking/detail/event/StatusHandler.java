/**
 * @(#)StatusHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.detail.db.*;

/**
 *  StatusHandler - Create the overall status from the product, price, and inventory status..
 */
public class StatusHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public StatusHandler()
    {
        super();
    }
    /**
     * StatusHandler Method.
     */
    public StatusHandler(BaseField field)
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
     * Set the object that owns this listener.
     * @owner The object that this listener is being added to (if null, this listener is being removed).
     */
    public void setOwner(ListenerOwner owner)
    {
        super.setOwner(owner);
        if (this.getOwner() != null)
        {
            Record recCustSaleDetail = this.getOwner().getRecord();
            recCustSaleDetail.getField(BookingDetail.COST_STATUS_ID).addListener(new InitOnChangeHandler(this.getOwner()));
            recCustSaleDetail.getField(BookingDetail.INVENTORY_STATUS_ID).addListener(new InitOnChangeHandler(this.getOwner()));
            recCustSaleDetail.getField(BookingDetail.INFO_STATUS_ID).addListener(new InitOnChangeHandler(this.getOwner()));
            recCustSaleDetail.getField(BookingDetail.PRODUCT_STATUS_ID).addListener(new InitOnChangeHandler(this.getOwner()));
        }
    }
    /**
     * Get the status by combining the other status.
     */
    public Object doGetData()
    {
        this.getOwner().setData(this.getStatus());
        return super.doGetData();
    }
    /**
     * DoSetData Method.
     */
    public int doSetData(Object objData, boolean bDisplayOption, int iMoveMode)
    {
        if (iMoveMode == DBConstants.INIT_MOVE)
            objData = this.getStatus();
        return super.doSetData(objData, bDisplayOption, iMoveMode);
    }
    /**
     * GetStatus Method.
     */
    public Object getStatus()
    {
        int iStatus = ((BookingDetail)this.getOwner().getRecord()).getStatus();
        return new Integer(iStatus);
    }

}
