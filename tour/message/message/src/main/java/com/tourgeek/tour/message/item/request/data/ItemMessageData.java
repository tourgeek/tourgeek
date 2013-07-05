/**
  * @(#)ItemMessageData.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.message.item.request.data;

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
import com.tourgeek.tour.message.base.request.data.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.model.message.*;
import com.tourgeek.model.tour.product.base.db.*;
import com.tourgeek.model.tour.product.item.db.*;
import com.tourgeek.model.tour.booking.detail.db.*;
import com.tourgeek.model.tour.booking.db.*;

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
        this.removeMessageDataDesc(BookingDetailModel.RATE_ID);
        this.removeMessageDataDesc(BookingDetailModel.CLASS_ID);
        this.addMessageFieldDesc(BookingDetailModel.RATE_ID, Integer.class, MessageFieldDesc.OPTIONAL, null);
        this.addMessageFieldDesc(BookingDetailModel.CLASS_ID, Integer.class, MessageFieldDesc.OPTIONAL, null);
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
        //xthis.put(ProductModel.RATE_TYPE_ID_PARAM, propertyOwner.getProperty(ProductModel.RATE_TYPE_ID_PARAM));
        //xthis.put(ProductModel.RATE_CLASS_ID_PARAM, propertyOwner.getProperty(ProductModel.RATE_CLASS_ID_PARAM));
        this.put(BookingDetailModel.DETAIL_DATE, propertyOwner.getProperty(BookingDetailModel.DETAIL_DATE));
    }
    /**
     * Move the fields of this record to this message
     * (a single Booking Detail record).
     */
    public int putRawRecordData(Rec record)
    {
        int iErrorCode = super.putRawRecordData(record);
        BookingItemModel recBookingItem = (BookingItemModel)record;
        Record recBooking = ((ReferenceField)recBookingItem.getField(BookingDetailModel.BOOKING_ID)).getReference();
        if (recBooking != null)
        { // Since there is no product date, use the departure date
            Record recTour = ((ReferenceField)recBooking.getField(BookingModel.TOUR_ID)).getReference();
            if (recTour != null)
                this.putRawFieldData(recTour.getField(TourModel.DEPARTURE_DATE));
        }
        return iErrorCode;
    }
    /**
     * Get/Create the product record.
     * @param bFindFirst If true, try to lookup the record first.
     * @return The product record.
     */
    public ProductModel getProductRecord(RecordOwner recordOwner, boolean bFindFirst)
    {
        if (bFindFirst)
            if (recordOwner != null)
                if (recordOwner.getRecord(ItemModel.ITEM_FILE) != null)
                    return (ItemModel)recordOwner.getRecord(ItemModel.ITEM_FILE);
        return (ItemModel)Record.makeRecordFromClassName(ItemModel.THICK_CLASS, recordOwner);
    }

}
