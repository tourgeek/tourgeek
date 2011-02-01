/**
 *  @(#)PassengerMessageData.
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
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.message.base.request.*;

/**
 *  PassengerMessageData - .
 */
public class PassengerMessageData extends MessageRecordDesc
{
    /**
     * Default constructor.
     */
    public PassengerMessageData()
    {
        super();
    }
    /**
     * PassengerMessageData Method.
     */
    public PassengerMessageData(MessageDataParent messageDataParent, String strKey)
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
            strKey = ProductRequest.PASSENGER_MESSAGE;
        super.init(messageDataParent, strKey);
    }
    /**
     * Setup sub-Message Data.
     */
    public void setupMessageDataDesc()
    {
        super.setupMessageDataDesc();
        
        this.addMessageDataDesc(new PassengerDetailMessageData(this, ProductRequest.PASSENGER_DETAIL));
        
        this.addMessageFieldDesc(Product.PAX_PARAM, Short.class, MessageFieldDesc.REQUIRED, null);
        this.addMessageFieldDesc(Product.ROOM_TYPE_PARAM, Short.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.COMPOUND_PARAM | PaxCategory.CHILD_ID, null);
    }
    /**
     * Get the data description for this param.
     */
    public MessageDataDesc getMessageDataDesc(String strParam)
    {
        String strParamOrig = strParam;
        if (strParam.startsWith(Product.ROOM_TYPE_PARAM))
            strParam = Product.ROOM_TYPE_PARAM;
        MessageDataDesc messageDataDesc = super.getMessageDataDesc(strParam);
        if (strParamOrig.startsWith(Product.ROOM_TYPE_PARAM))
            messageDataDesc.setKey(strParamOrig);
        return messageDataDesc;
    }
    /**
     * Move the correct fields from this record to the map.
     * If this method is used, is must be overidden to move the correct fields.
     * @param record The record to get the data from.
     */
    public int putRawRecordData(FieldList record)
    {
        Booking recBooking = ((BookingDetail)record).getBooking(true);
        int iErrorCode = super.putRawRecordData(recBooking);
        for (int iRoomType = PaxCategory.SINGLE_ID; iRoomType <= PaxCategory.CHILD_ID; iRoomType++)
        {
            short shPaxOfType = (short)recBooking.getField(Booking.kSinglePax + iRoomType - PaxCategory.SINGLE_ID).getValue();
            if (shPaxOfType > 0)
            {
                this.put(Product.ROOM_TYPE_PARAM + Integer.toString(iRoomType), new Short(shPaxOfType));
            }
        }
        return iErrorCode;
    }
    /**
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(FieldList record)
    {
        Booking recBooking = ((BookingDetail)record).getBooking(!record.getField(BookingDetail.kBookingID).isNull());
        int iErrorCode = super.getRawRecordData(record);
        for (int iRoomType = PaxCategory.SINGLE_ID; iRoomType <= PaxCategory.CHILD_ID; iRoomType++)
        {
            short shPaxOfType = this.getPaxInRoom(iRoomType);
            if (shPaxOfType > 0)
            {
                recBooking.getField(Booking.kSinglePax + iRoomType - PaxCategory.SINGLE_ID).setValue(shPaxOfType);
            }
        }
        return iErrorCode;
    }
    /**
     * GetTargetPax Method.
     */
    public short getTargetPax()
    {
        Short shortTargetPax = (Short)this.get(Product.PAX_PARAM);
        short sTargetPax = 0;
        if (shortTargetPax != null)
            sTargetPax = shortTargetPax.shortValue();
        if (sTargetPax == 0)
            sTargetPax = 1;
        return sTargetPax;
    }
    /**
     * Set the pax count.
     */
    public void setTargetPax(short sPax)
    {
        this.put(Product.PAX_PARAM, new Short(sPax));
    }
    /**
     * GetPaxInRoom Method.
     */
    public short getPaxInRoom(int iRoomCategory)
    {
        Short shPaxInRoom = (Short)this.get(Product.ROOM_TYPE_PARAM + Integer.toString(iRoomCategory));
        if (shPaxInRoom == null)
            return (short)0;
        return shPaxInRoom.shortValue();
    }
    /**
     * SetPaxInRoom Method.
     */
    public void setPaxInRoom(int iRoomCategory, short shPaxInRoom)
    {
        this.put(Product.ROOM_TYPE_PARAM + Integer.toString(iRoomCategory), new Short(shPaxInRoom));
    }

}
