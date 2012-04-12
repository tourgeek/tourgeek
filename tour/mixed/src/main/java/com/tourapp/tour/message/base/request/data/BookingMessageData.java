/**
 * @(#)BookingMessageData.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.message.base.request.*;
import org.jbundle.model.message.*;
import com.tourapp.model.tour.booking.db.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  BookingMessageData - .
 */
public class BookingMessageData extends MessageRecordDesc
{
    public static final String BOOKING_MESSAGE = "bookingMessage";
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
            strKey = BookingMessageData.BOOKING_MESSAGE;
        super.init(messageDataParent, strKey);
    }
    /**
     * Move the correct fields from this record to the map.
     * If this method is used, is must be overidden to move the correct fields.
     * @param record The record to get the data from.
     */
    public int putRawRecordData(Rec record)
    {
        Record recBooking = (Record)((BookingDetailModel)record).getBooking(true);
        int iErrorCode = DBConstants.NORMAL_RETURN;
        for (int iFieldSeq = recBooking.getFieldSeq(BookingModel.GENERIC_NAME); iFieldSeq <= recBooking.getFieldSeq(BookingModel.CONTACT); iFieldSeq++)
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
        Record recBooking = (Record)((BookingDetailModel)record).getBooking(!record.getField(BookingDetailModel.BOOKING_ID).isNull());
        for (int iFieldSeq = recBooking.getFieldSeq(BookingModel.GENERIC_NAME); iFieldSeq <= recBooking.getFieldSeq(BookingModel.CONTACT); iFieldSeq++)
        {
            this.getRawFieldData(recBooking.getField(iFieldSeq));
        }
        return super.getRawRecordData(record);
    }

}
