/**
 *  @(#)BookingMessageData.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.base.request.data;

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
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.base.request.*;
import org.jbundle.model.db.*;

/**
 *  BookingMessageData - .
 */
public class BookingMessageData extends MessageRecordDesc
{
    /**
     * Default constructor.
     */
    public BookingMessageData()
    {
        super();
    }
    /**
     * BookingMessageData Method.
     */
    public BookingMessageData(MessageDataParent messageDataParent, String strKey)
    {
        this();
        this.init(messageDataParent, strKey);
    }
    /**
     * Initialize class fields.
     */
    public void init(MessageDataParent messageDataParent, String strKey)
    {
        if (strKey == null)
            strKey = ProductRequest.BOOKING_MESSAGE;
        super.init(messageDataParent, strKey);
    }
    /**
     * Move the correct fields from this record to the map.
     * If this method is used, is must be overidden to move the correct fields.
     * @param record The record to get the data from.
     */
    public int putRawRecordData(Rec record)
    {
        Booking recBooking = ((BookingDetail)record).getBooking(true);
        int iErrorCode = DBConstants.NORMAL_RETURN;
        for (int iFieldSeq = Booking.kGenericName; iFieldSeq <= Booking.kContact; iFieldSeq++)
        {
            iErrorCode = this.putRawFieldData(recBooking.getField(iFieldSeq));
            if (iErrorCode != DBConstants.NORMAL_RETURN)
                break;
        }
        return iErrorCode;
    }
    /**
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(Rec record)
    {
        Booking recBooking = ((BookingDetail)record).getBooking(!record.getField(BookingDetail.kBookingID).isNull());
        for (int iFieldSeq = Booking.kGenericName; iFieldSeq <= Booking.kContact; iFieldSeq++)
        {
            this.getRawFieldData(recBooking.getField(iFieldSeq));
        }
        return super.getRawRecordData(record);
    }

}
