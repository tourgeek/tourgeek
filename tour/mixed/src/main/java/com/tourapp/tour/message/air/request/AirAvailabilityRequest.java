/**
 * @(#)AirAvailabilityRequest.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.air.request;

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
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.air.request.data.*;
import com.tourapp.tour.message.base.response.*;
import org.jbundle.model.message.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.model.tour.booking.detail.db.*;
import com.tourapp.tour.message.air.response.*;

/**
 *  AirAvailabilityRequest - .
 */
public class AirAvailabilityRequest extends ProductAvailabilityRequest
{
    /**
     * Default constructor.
     */
    public AirAvailabilityRequest()
    {
        super();
    }
    /**
     * AirAvailabilityRequest Method.
     */
    public AirAvailabilityRequest(MessageDataParent messageDataParent, String strKey)
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
    }
    /**
     * Check to make sure all the data is present to attempt a cost lookup.
     * Note: You are NOT returning the status, you are returning the status of the params,
     * The calling program will change the status if required.
     * @return DATA_REQUIRED if all the data is not present, DATA_VALID if the data is OKAY.
     */
    public int checkRequestParams(Rec record)
    {
        int iStatus = super.checkRequestParams(record);
        return iStatus;
    }
    /**
     * Move the correct fields from this record to the map.
     * If this method is used, is must be overidden to move the correct fields.
     * @param record The record to get the data from.
     */
    public int putRawRecordData(Rec record)
    {
        return super.putRawRecordData(record);
    }
    /**
     * Move the data from this properyowner to this message.
     */
    public void putRawProperties(PropertyOwner propertyOwner)
    {
        super.putRawProperties(propertyOwner);
    }
    /**
     * CreateProductMessageData Method.
     */
    public ProductMessageData createProductMessageData()
    {
        return new AirMessageData(this, PRODUCT_MESSAGE);
    }

}
