/**
 * @(#)TourBookingRequest.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.tour.request.data.*;
import org.jbundle.main.msg.db.*;

/**
 *  TourBookingRequest - .
 */
public class TourBookingRequest extends ProductBookingRequest
{
    /**
     * Default constructor.
     */
    public TourBookingRequest()
    {
        super();
    }
    /**
     * TourBookingRequest Method.
     */
    public TourBookingRequest(MessageDataParent messageDataParent, String strKey)
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
     * Check to make sure all the data is present to attempt a cost lookup.
     * Note: You are NOT returning the status, you are returning the status of the params,
     * The calling program will change the status if required.
     * @return DATA_REQUIRED if all the data is not present, DATA_VALID if the data is OKAY.
     */
    public int checkRequestParams(Rec record)
    {
        int iStatus = super.checkRequestParams(record);
        if (iStatus == BaseDataStatus.DATA_VALID)
            iStatus = this.checkBookingRequestParams(record);
        return iStatus;
    }
    /**
     * Initialize the fields in this record to prepare for this message.
     * Also, do any other preparation needed before sending this message.
     * @param record The record to initialize
     * @return An error code if there were any problems.
     */
    public int initForMessage(Record record)
    {
        int iErrorCode = super.initForMessage(record);
        if (iErrorCode == DBConstants.NORMAL_RETURN)
            iErrorCode = this.initBookingApTrx(record);
        if (iErrorCode == DBConstants.NORMAL_RETURN)
            iErrorCode = this.initBookingInventory(record);
        return iErrorCode;
    }
    /**
     * GetRequestType Method.
     */
    public String getRequestType()
    {
        return RequestType.BOOKING_ADD;
    }
    /**
     * CreateProductMessageData Method.
     */
    public ProductMessageData createProductMessageData()
    {
        return new TourMessageData(this, PRODUCT_MESSAGE);
    }
    /**
     * IsOrderComponents Method.
     */
    public boolean isOrderComponents(BookingDetail recBookingDetail)
    {
        MessageTransport recMessageTransport = (MessageTransport)((ReferenceField)recBookingDetail.getField(BookingDetail.kProductMessageTransportID)).getReference();
        if (recMessageTransport != null)
            if (recMessageTransport.isDirectTransport())
                return true;    // Special case - For direct tours, add the tour detail immediately
        return super.isOrderComponents(recBookingDetail);
    }
    /**
     * Make sure this BookingDetail is linked to an ApTrx.
     */
    public int initBookingApTrx(Rec record)
    {
        BookingDetail recBookingDetail = (BookingDetail)record; 
        if (recBookingDetail.getField(BookingDetail.kVendorID).isNull())
            return DBConstants.NORMAL_RETURN;   // Vendor not required for tour detail
        return super.initBookingApTrx(record);
    }

}
