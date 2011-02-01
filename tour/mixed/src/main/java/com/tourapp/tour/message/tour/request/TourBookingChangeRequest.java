/**
 *  @(#)TourBookingChangeRequest.
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
 *  TourBookingChangeRequest - .
 */
public class TourBookingChangeRequest extends TourBookingRequest
{
    /**
     * Default constructor.
     */
    public TourBookingChangeRequest()
    {
        super();
    }
    /**
     * TourBookingChangeRequest Method.
     */
    public TourBookingChangeRequest(MessageDataParent messageDataParent, String strKey)
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
        ((ProductMessageData)this.getMessageDataDesc(PRODUCT_MESSAGE)).addMessageFieldDesc(BookingDetail.REMOTE_BOOKING_NO, String.class, MessageFieldDesc.REQUIRED, MessageFieldDesc.NOT_UNIQUE | MessageFieldDesc.DONT_INIT, null);
    }
    /**
     * GetRequestType Method.
     */
    public String getRequestType()
    {
        return RequestType.BOOKING_CHANGE;
    }
    /**
     * Make sure this BookingDetail is linked to an ApTrx.
     */
    public int initBookingApTrx(FieldList record)
    {
        BookingDetail recBookingDetail = (BookingDetail)record; 
        if (recBookingDetail.getField(BookingDetail.kVendorID).isNull())
            return DBConstants.NORMAL_RETURN;   // Vendor not required for tour detail
        return super.initBookingApTrx(record);
    }

}
