/**
 *  @(#)TransportationMessageData.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.trans.request.data;

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
import com.tourapp.tour.message.base.request.data.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.trans.db.*;
import org.jbundle.main.msg.db.*;

/**
 *  TransportationMessageData - .
 */
public class TransportationMessageData extends ProductMessageData
{
    /**
     * Default constructor.
     */
    public TransportationMessageData()
    {
        super();
    }
    /**
     * TransportationMessageData Method.
     */
    public TransportationMessageData(MessageDataParent messageDataParent, String strKey)
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
        this.removeMessageDataDesc(BookingDetail.RATE_ID);
        this.removeMessageDataDesc(BookingDetail.CLASS_ID);
        this.addMessageFieldDesc(BookingDetail.RATE_ID, Integer.class, MessageFieldDesc.OPTIONAL, null);
        this.addMessageFieldDesc(BookingDetail.CLASS_ID, Integer.class, MessageFieldDesc.OPTIONAL, null);
    }
    /**
     * Check to make sure all the data is present to attempt a cost lookup.
     * Note: You are NOT returning the status, you are returning the status of the params,
     * The calling program will change the status if required.
     * @return DATA_REQUIRED if all the data is not present, DATA_VALID if the data is OKAY.
     */
    public int checkRequestParams(FieldList record)
    {
        return super.checkRequestParams(record);
    }
    /**
     * Initialize the fields in this record to prepare for this message.
     * Also, do any other preparation needed before sending this message.
     * @param record The record to initialize
     * @return An error code if there were any problems.
     */
    public int initForMessage(FieldList record)
    {
        int iErrorCode = super.initForMessage(record);
        ((Record)record).getField(BookingTransportation.kPPCost).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        return iErrorCode;
    }
    /**
     * Move the fields of this record to this message
     * (a single Booking Detail record).
     */
    public int putRawRecordData(FieldList record)
    {
        int iErrorCode = super.putRawRecordData(record);
        BookingTransportation recBookingTransportation = (BookingTransportation)record;
        return iErrorCode;
    }
    /**
     * Move the data from this properyowner to this message.
     */
    public void putRawProperties(PropertyOwner propertyOwner)
    {
        this.put(BookingDetail.CLASS_ID, propertyOwner.getProperty(BookingDetail.CLASS_ID));
        this.put(BookingDetail.DETAIL_DATE, propertyOwner.getProperty(BookingDetail.DETAIL_DATE));
    }
    /**
     * Get/Create the product record.
     * @param bFindFirst If true, try to lookup the record first.
     * @return The product record.
     */
    public Product getProductRecord(RecordOwner recordOwner, boolean bFindFirst)
    {
        if (bFindFirst)
            if (recordOwner != null)
                if (recordOwner.getRecord(Transportation.kTransportationFile) != null)
                    return (Transportation)recordOwner.getRecord(Transportation.kTransportationFile);
        return new Transportation(recordOwner);
    }
    /**
     * GetProductClass Method.
     */
    public BaseClass getProductClass(RecordOwner recordOwner)
    {
        return new TransportationClass(recordOwner);
    }

}
