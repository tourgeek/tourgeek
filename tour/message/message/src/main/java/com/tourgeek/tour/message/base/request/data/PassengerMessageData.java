/**
  * @(#)PassengerMessageData.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.message.base.request.data;

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
import com.tourgeek.tour.message.base.request.*;
import org.jbundle.model.message.*;
import com.tourgeek.model.tour.booking.db.*;
import com.tourgeek.model.tour.booking.detail.db.*;
import com.tourgeek.model.tour.product.base.db.*;

/**
 *  PassengerMessageData - .
 */
public class PassengerMessageData extends MessageRecordDesc
{
    public static final String PASSENGER_MESSAGE = "passengerMessage";
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
            strKey = PassengerMessageData.PASSENGER_MESSAGE;
        super.init(messageDataParent, strKey);
    }
    /**
     * Setup sub-Message Data.
     */
    public void setupMessageDataDesc()
    {
        super.setupMessageDataDesc();
        
        this.addMessageDataDesc(new PassengerDetailMessageData(this, PassengerDetailMessageData.PASSENGER_DETAIL));
        
        this.addMessageFieldDesc(ProductModel.PAX_PARAM, Short.class, MessageFieldDesc.REQUIRED, null);
        this.addMessageFieldDesc(ProductModel.ROOM_TYPE_PARAM, Short.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.COMPOUND_PARAM | PaxCategoryModel.CHILD_ID, null);
    }
    /**
     * Get the data description for this param.
     */
    public MessageDataDesc getMessageDataDesc(String strParam)
    {
        String strParamOrig = strParam;
        if (strParam.startsWith(ProductModel.ROOM_TYPE_PARAM))
            strParam = ProductModel.ROOM_TYPE_PARAM;
        MessageDataDesc messageDataDesc = super.getMessageDataDesc(strParam);
        if (strParamOrig.startsWith(ProductModel.ROOM_TYPE_PARAM))
            messageDataDesc.setKey(strParamOrig);
        return messageDataDesc;
    }
    /**
     * Move the correct fields from this record to the map.
     * If this method is used, is must be overidden to move the correct fields.
     * @param record The record to get the data from.
     */
    public int putRawRecordData(Rec record)
    {
        BookingModel recBooking = ((BookingDetailModel)record).getBooking(true);
        int iErrorCode = super.putRawRecordData(recBooking);
        for (int iRoomType = PaxCategoryModel.SINGLE_ID; iRoomType <= PaxCategoryModel.CHILD_ID; iRoomType++)
        {
            short shPaxOfType = (short)recBooking.getField(((Record)recBooking).getFieldSeq(BookingModel.SINGLE_PAX) + iRoomType - PaxCategoryModel.SINGLE_ID).getValue();
            if (shPaxOfType > 0)
            {
                this.put(ProductModel.ROOM_TYPE_PARAM + Integer.toString(iRoomType), new Short(shPaxOfType));
            }
        }
        return iErrorCode;
    }
    /**
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(Rec record)
    {
        BookingModel recBooking = (BookingModel)((BookingDetailModel)record).getBooking(!record.getField(BookingDetailModel.BOOKING_ID).isNull());
        int iErrorCode = super.getRawRecordData(record);
        for (int iRoomType = PaxCategoryModel.SINGLE_ID; iRoomType <= PaxCategoryModel.CHILD_ID; iRoomType++)
        {
            short shPaxOfType = this.getPaxInRoom(iRoomType);
            if (shPaxOfType > 0)
            {
                recBooking.getField(((Record)recBooking).getFieldSeq(BookingModel.SINGLE_PAX) + iRoomType - PaxCategoryModel.SINGLE_ID).setValue(shPaxOfType);
            }
        }
        return iErrorCode;
    }
    /**
     * GetTargetPax Method.
     */
    public short getTargetPax()
    {
        Short shortTargetPax = (Short)this.get(ProductModel.PAX_PARAM);
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
        this.put(ProductModel.PAX_PARAM, new Short(sPax));
    }
    /**
     * GetPaxInRoom Method.
     */
    public short getPaxInRoom(int iRoomCategory)
    {
        Short shPaxInRoom = (Short)this.get(ProductModel.ROOM_TYPE_PARAM + Integer.toString(iRoomCategory));
        if (shPaxInRoom == null)
            return (short)0;
        return shPaxInRoom.shortValue();
    }
    /**
     * SetPaxInRoom Method.
     */
    public void setPaxInRoom(int iRoomCategory, short shPaxInRoom)
    {
        this.put(ProductModel.ROOM_TYPE_PARAM + Integer.toString(iRoomCategory), new Short(shPaxInRoom));
    }

}
