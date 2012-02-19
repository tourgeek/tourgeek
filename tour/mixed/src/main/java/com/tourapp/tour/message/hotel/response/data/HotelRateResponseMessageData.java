/**
 * @(#)HotelRateResponseMessageData.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.hotel.response.data;

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
import com.tourapp.tour.message.base.response.data.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  HotelRateResponseMessageData - .
 */
public class HotelRateResponseMessageData extends ProductRateResponseMessageData
{
    /**
     * Default constructor.
     */
    public HotelRateResponseMessageData()
    {
        super();
    }
    /**
     * HotelRateResponseMessageData Method.
     */
    public HotelRateResponseMessageData(MessageDataParent messageDataParent, String strKey)
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
        this.addMessageFieldDesc(BookingDetail.TOTAL_COST, Double.class, MessageFieldDesc.OPTIONAL, null);
        this.addMessageFieldDesc(BookingHotel.ROOM_COST, Double.class, MessageFieldDesc.OPTIONAL, null);
        this.addMessageFieldDesc(BookingHotel.MEAL_COST, Double.class, MessageFieldDesc.OPTIONAL, null);
    }
    /**
     * Get the data description for this param.
     */
    public MessageDataDesc getMessageDataDesc(String strParam)
    {
        String strParamOrig = strParam;
        if (strParam.startsWith(BookingDetail.TOTAL_COST))
            strParam = BookingDetail.TOTAL_COST;
        MessageDataDesc messageDataDesc = super.getMessageDataDesc(strParam);
        if (strParamOrig.startsWith(BookingDetail.TOTAL_COST))
            messageDataDesc.setKey(strParamOrig);
        return messageDataDesc;
    }
    /**
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(Rec record)
    {
        int iInfoStatus = super.getRawRecordData(record);
        BookingHotel recBookingHotel = (BookingHotel)record;
        for (int iFieldSeq = recBookingHotel.getFieldSeq(BookingHotel.SINGLE_COST), iRoomCategory = PaxCategory.SINGLE_ID; iFieldSeq <= recBookingHotel.getFieldSeq(BookingHotel.QUAD_COST); iFieldSeq++, iRoomCategory++)
        {
            double dRoomCost = this.getRoomCost(iRoomCategory);
            recBookingHotel.getField(iFieldSeq).setValue(dRoomCost);
        }
        this.getRawFieldData(recBookingHotel.getField(BookingHotel.ROOM_COST));
        this.getRawFieldData(recBookingHotel.getField(BookingHotel.MEAL_COST));
        return iInfoStatus;
    }
    /**
     * SetRoomCost Method.
     */
    public void setRoomCost(int iRoomCategory, double dCost)
    {
        this.put(BookingDetail.TOTAL_COST + Integer.toString(iRoomCategory - PaxCategory.SINGLE_ID + 1), new Double(dCost));
    }
    /**
     * SetTotalRoomCost Method.
     */
    public void setTotalRoomCost(double dCost)
    {
        this.put(BookingHotel.ROOM_COST, new Double(dCost));
    }
    /**
     * SetTotalMealCost Method.
     */
    public void setTotalMealCost(double dCost)
    {
        this.put(BookingHotel.MEAL_COST, new Double(dCost));
    }
    /**
     * GetRoomCost Method.
     */
    public double getRoomCost(int iRoomCategory)
    {
        Double dblRoomCost = (Double)this.get(BookingDetail.TOTAL_COST + Integer.toString(iRoomCategory - PaxCategory.SINGLE_ID + 1));
        if (dblRoomCost == null)
            return 0;
        return dblRoomCost.doubleValue();
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
                if (recordOwner.getRecord(Hotel.HOTEL_FILE) != null)
                    return (Hotel)recordOwner.getRecord(Hotel.HOTEL_FILE);
        return new Hotel(recordOwner);
    }

}
