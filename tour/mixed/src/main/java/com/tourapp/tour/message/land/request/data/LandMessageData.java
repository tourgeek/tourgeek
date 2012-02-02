/**
 * @(#)LandMessageData.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.land.request.data;

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
import com.tourapp.tour.message.base.request.data.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.main.msg.db.*;

/**
 *  LandMessageData - .
 */
public class LandMessageData extends ProductMessageData
{
    /**
     * Default constructor.
     */
    public LandMessageData()
    {
        super();
    }
    /**
     * LandMessageData Method.
     */
    public LandMessageData(MessageDataParent messageDataParent, String strKey)
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
        this.addMessageFieldDesc(Product.ROOM_TYPE_PARAM, Short.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.COMPOUND_PARAM | PaxCategory.CHILD_ID, null);
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
        int iErrorCode = super.initForMessage(record);
        ((Record)record).getField(BookingLand.PP_COST).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingLand.VARIES_CODE).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingLand.VARIES_QTY).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingLand.VARIES_COST).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingLand.PMC_CUTOFF).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingLand.PMC_COST).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingLand.SIC_COST).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        return iErrorCode;
    }
    /**
     * Move the fields of this record to this message
     * (a single Booking Detail record).
     */
    public int putRawRecordData(Rec record)
    {
        int iErrorCode = super.putRawRecordData(record);
        BookingLand recBookingLand = (BookingLand)record;
        return iErrorCode;
    }
    /**
     * Move the data from this properyowner to this message.
     */
    public void putRawProperties(PropertyOwner propertyOwner)
    {
        this.put(BookingDetail.RATE_ID, propertyOwner.getProperty(BookingDetail.RATE_ID));
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
                if (recordOwner.getRecord(Land.LAND_FILE) != null)
                    return (Land)recordOwner.getRecord(Land.LAND_FILE);
        return new Land(recordOwner);
    }
    /**
     * GetProductClass Method.
     */
    public BaseClass getProductClass(RecordOwner recordOwner)
    {
        return new LandClass(recordOwner);
    }
    /**
     * GetProductRate Method.
     */
    public BaseRate getProductRate(RecordOwner recordOwner)
    {
        return new LandRate(recordOwner);
    }

}
