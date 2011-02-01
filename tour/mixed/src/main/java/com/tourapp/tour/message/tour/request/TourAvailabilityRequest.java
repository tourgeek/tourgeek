/**
 *  @(#)TourAvailabilityRequest.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.tour.request;

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
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.tour.request.data.*;
import org.jbundle.main.msg.db.*;

/**
 *  TourAvailabilityRequest - .
 */
public class TourAvailabilityRequest extends ProductAvailabilityRequest
{
    /**
     * Default constructor.
     */
    public TourAvailabilityRequest()
    {
        super();
    }
    /**
     * TourAvailabilityRequest Method.
     */
    public TourAvailabilityRequest(MessageDataParent messageDataParent, String strKey)
    {
        this();
        this.init(messageDataParent, strKey);
    }
    /**
     * Initialize class fields.
     */
    public void init(MessageDataParent messageDataParent, String strKey)
    {
        super.init(messageDataParent, strKey);
    }
    /**
     * Setup sub-Message Data.
     */
    public void setupMessageDataDesc()
    {
        super.setupMessageDataDesc();
        // this.addDataDesc(Product.INVENTORY_PARAM, Boolean.class, String.class, null, OPTIONAL, null);
    }
    /**
     * Check to make sure all the data is present to attempt a cost lookup.
     * Note: You are NOT returning the status, you are returning the status of the params,
     * The calling program will change the status if required.
     * @return DATA_REQUIRED if all the data is not present, DATA_VALID if the data is OKAY.
     */
    public int checkRequestParams(FieldList record)
    {
        int iStatus = super.checkRequestParams(record);
        if (iStatus == BaseDataStatus.DATA_VALID)
        {
            BookingDetail recBookingDetail = (BookingDetail)record;
        //    if (recBookingDetail.getField(BookingDetail.kInventoryManual).getState() == true)
        //        iStatus = BaseMessageStatus.VALID;  // No inventory lookup required
        }
        return iStatus;
    }
    /**
     * Move the correct fields from this record to the map.
     * If this method is used, is must be overidden to move the correct fields.
     * @param record The record to get the data from.
     */
    public int putRawRecordData(FieldList record)
    {
        int iErrorCode = super.putRawRecordData(record);
        BookingDetail recBookingDetail = (BookingDetail)record;
        // this.putRawFieldData(Product.INVENTORY_PARAM, recBookingDetail.getField(BookingDetail.kInventoryManual));
        return iErrorCode;
    }
    /**
     * Move the data from this properyowner to this message.
     */
    public void putRawProperties(PropertyOwner propertyOwner)
    {
        super.putRawProperties(propertyOwner);
        // this.putRaw(Product.INVENTORY_PARAM, propertyOwner.getProperty(Product.INVENTORY_PARAM));
    }
    /**
     * CreateProductMessageData Method.
     */
    public ProductMessageData createProductMessageData()
    {
        return new TourMessageData(this, PRODUCT_MESSAGE);
    }

}
