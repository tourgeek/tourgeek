/**
 * @(#)ItemMessageData.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.item.request.data;

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
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.item.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.booking.db.*;
import org.jbundle.main.msg.db.*;

/**
 *  ItemMessageData - .
 */
public class ItemMessageData extends ProductMessageData
{
    /**
     * Default constructor.
     */
    public ItemMessageData()
    {
        super();
    }
    /**
     * ItemMessageData Method.
     */
    public ItemMessageData(MessageDataParent messageDataParent, String strKey)
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
    public int checkRequestParams(Rec record)
    {
        return super.checkRequestParams(record);
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
     * Move the data from this properyowner to this message.
     */
    public void putRawProperties(PropertyOwner propertyOwner)
    {
        //xthis.put(Product.RATE_TYPE_ID_PARAM, propertyOwner.getProperty(Product.RATE_TYPE_ID_PARAM));
        //xthis.put(Product.RATE_CLASS_ID_PARAM, propertyOwner.getProperty(Product.RATE_CLASS_ID_PARAM));
        this.put(BookingDetail.DETAIL_DATE, propertyOwner.getProperty(BookingDetail.DETAIL_DATE));
    }
    /**
     * Move the fields of this record to this message
     * (a single Booking Detail record).
     */
    public int putRawRecordData(Rec record)
    {
        int iErrorCode = super.putRawRecordData(record);
        BookingItem recBookingItem = (BookingItem)record;
        Record recBooking = ((ReferenceField)recBookingItem.getField(BookingDetail.kBookingID)).getReference();
        if (recBooking != null)
        { // Since there is no product date, use the departure date
            Record recTour = ((ReferenceField)recBooking.getField(Booking.kTourID)).getReference();
            if (recTour != null)
                this.putRawFieldData(recTour.getField(Tour.kDepartureDate));
        }
        return iErrorCode;
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
                if (recordOwner.getRecord(Item.kItemFile) != null)
                    return (Item)recordOwner.getRecord(Item.kItemFile);
        return new Item(recordOwner);
    }

}
