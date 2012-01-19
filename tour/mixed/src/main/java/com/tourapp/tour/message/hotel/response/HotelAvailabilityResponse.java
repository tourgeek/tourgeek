/**
 * @(#)HotelAvailabilityResponse.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.message.base.response.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.booking.detail.db.*;

/**
 *  HotelAvailabilityResponse - .
 */
public class HotelAvailabilityResponse extends ProductAvailabilityResponse
{
    /**
     * Default constructor.
     */
    public HotelAvailabilityResponse()
    {
        super();
    }
    /**
     * HotelAvailabilityResponse Method.
     */
    public HotelAvailabilityResponse(MessageDataParent messageDataParent, String strKey)
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
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(Rec record)
    {
        int iInfoStatus = super.getRawRecordData(record);
        BookingHotel recBookingHotel = (BookingHotel)record;
        return iInfoStatus;
    }

}
