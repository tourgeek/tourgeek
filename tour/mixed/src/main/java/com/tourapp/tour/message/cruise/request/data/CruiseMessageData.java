/**
 * @(#)CruiseMessageData.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.cruise.request.data;

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
import com.tourapp.tour.message.base.request.data.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.cruise.db.*;
import org.jbundle.main.msg.db.*;

/**
 *  CruiseMessageData - .
 */
public class CruiseMessageData extends ProductMessageData
{
    /**
     * Default constructor.
     */
    public CruiseMessageData()
    {
        super();
    }
    /**
     * CruiseMessageData Method.
     */
    public CruiseMessageData(MessageDataParent messageDataParent, String strKey)
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
     * Initialize the fields in this record to prepare for this message.
     * Also, do any other preparation needed before sending this message.
     * @param record The record to initialize
     * @return An error code if there were any problems.
     */
    public int initForMessage(Rec record)
    {
        return super.initForMessage(record);
    }
    /**
     * Move the fields of this record to this message
     * (a single Booking Detail record).
     */
    public int putRawRecordData(Rec record)
    {
        BookingDetail recBookingDetail = (BookingDetail)record;
        return super.putRawRecordData(record);
    }
    /**
     * Move the data from this properyowner to this message.
     */
    public void putRawProperties(PropertyOwner propertyOwner)
    {
        this.put(BookingDetail.CLASS_ID, propertyOwner.getProperty(BookingDetail.CLASS_ID));
        this.put(BookingDetail.RATE_ID, propertyOwner.getProperty(BookingDetail.RATE_ID));
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
                if (recordOwner.getRecord(Cruise.CRUISE_FILE) != null)
                    return (Cruise)recordOwner.getRecord(Cruise.CRUISE_FILE);
        return new Cruise(recordOwner);
    }
    /**
     * GetProductClass Method.
     */
    public BaseClass getProductClass(RecordOwner recordOwner)
    {
        return new CruiseClass(recordOwner);
    }
    /**
     * GetProductRate Method.
     */
    public BaseRate getProductRate(RecordOwner recordOwner)
    {
        return new CruiseRate(recordOwner);
    }

}
