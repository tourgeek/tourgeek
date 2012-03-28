/**
 * @(#)HotelRateAvailRequest.
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
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.base.request.*;

/**
 *  HotelRateAvailRequest - Combined rate and availability request.
 */
public class HotelRateAvailRequest extends HotelRateRequest
{
    /**
     * Default constructor.
     */
    public HotelRateAvailRequest()
    {
        super();
    }
    /**
     * HotelRateAvailRequest Method.
     */
    public HotelRateAvailRequest(MessageDataParent messageDataParent, String strKey)
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
        ((ProductMessageData)this.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE)).setSyncFields(BookingDetail.INVENTORY_STATUS_ID, BookingDetail.COST_STATUS_ID);
    }

}
