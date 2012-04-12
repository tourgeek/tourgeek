/**
 * @(#)HotelRateRequest.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.hotel.request;

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
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.hotel.request.data.*;
import org.jbundle.model.message.*;
import com.tourapp.model.tour.product.hotel.db.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  HotelRateRequest - .
 */
public class HotelRateRequest extends ProductRateRequest
{
    /**
     * Default constructor.
     */
    public HotelRateRequest()
    {
        super();
    }
    /**
     * HotelRateRequest Method.
     */
    public HotelRateRequest(MessageDataParent messageDataParent, String strKey)
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
     * Initialize the fields in this record to prepare for this message.
     * Also, do any other preparation needed before sending this message.
     * @param record The record to initialize
     * @return An error code if there were any problems.
     */
    public int initForMessage(Record record)
    {
        record.getField(BookingDetailModel.TOTAL_COST).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        record.getField(BookingDetailModel.TOTAL_COST_LOCAL).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        return super.initForMessage(record);
    }
    /**
     * CreateProductMessageData Method.
     */
    public ProductMessageData createProductMessageData()
    {
        return new HotelMessageData(this, PRODUCT_MESSAGE);
    }

}
