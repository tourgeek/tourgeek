/**
 * @(#)HotelRateAvailResponse.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.hotel.response;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.message.base.response.data.*;
import com.tourapp.tour.message.hotel.response.data.*;

/**
 *  HotelRateAvailResponse - Combined Rate and Availability response message.
 */
public class HotelRateAvailResponse extends HotelRateResponse
{
    /**
     * Default constructor.
     */
    public HotelRateAvailResponse()
    {
        super();
    }
    /**
     * HotelRateAvailResponse Method.
     */
    public HotelRateAvailResponse(MessageDataParent messageDataParent, String strKey)
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
     * CreateProductResponseMessageData Method.
     */
    public ProductResponseMessageData createProductResponseMessageData()
    {
        return new HotelRateAvailResponseMessageData(this, PRODUCT_RESPONSE_MESSAGE);
    }

}
