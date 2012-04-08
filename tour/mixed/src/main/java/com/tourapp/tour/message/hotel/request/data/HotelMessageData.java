/**
 * @(#)HotelMessageData.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.hotel.request.data;

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
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.detail.db.*;
import java.text.*;
import com.tourapp.tour.message.base.request.*;
import org.jbundle.model.message.*;

/**
 *  HotelMessageData - .
 */
public class HotelMessageData extends ProductMessageData
{
    public static final Short DURATION_DEFAULT = new Short((short)1);
    /**
     * Default constructor.
     */
    public HotelMessageData()
    {
        super();
    }
    /**
     * HotelMessageData Method.
     */
    public HotelMessageData(MessageDataParent messageDataParent, String strKey)
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
        this.addMessageFieldDesc(BookingHotel.NIGHTS, Short.class, MessageFieldDesc.REQUIRED, null);
        this.addMessageFieldDesc(Product.ROOM_TYPE_PARAM, Short.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.COMPOUND_PARAM | PaxCategory.CHILD_ID, null);
        this.addMessageFieldDesc(Hotel.MEAL_PLAN_ID_PARAM, Integer.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.COMPOUND_PARAM | 4, null);
        this.addMessageFieldDesc(Hotel.MEAL_PLAN_QTY_PARAM, Short.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.COMPOUND_PARAM | 4, null);
        this.addMessageFieldDesc(Hotel.MEAL_PLAN_DAYS_PARAM, Short.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.COMPOUND_PARAM | 4, null);
    }
    /**
     * Get the data description for this param.
     */
    public MessageDataDesc getMessageDataDesc(String strParam)
    {
        String strParamOrig = strParam;
        if (strParam.startsWith(Hotel.MEAL_PLAN_ID_PARAM))
            strParam = Hotel.MEAL_PLAN_ID_PARAM;
        if (strParam.startsWith(Hotel.MEAL_PLAN_QTY_PARAM))
            strParam = Hotel.MEAL_PLAN_QTY_PARAM;
        if (strParam.startsWith(Hotel.MEAL_PLAN_DAYS_PARAM))
            strParam = Hotel.MEAL_PLAN_DAYS_PARAM;
        MessageDataDesc messageDataDesc = super.getMessageDataDesc(strParam);
        if (messageDataDesc != null)
            if ((strParamOrig.startsWith(Hotel.MEAL_PLAN_ID_PARAM))
                    || (strParamOrig.startsWith(Hotel.MEAL_PLAN_QTY_PARAM))
                    || (strParamOrig.startsWith(Hotel.MEAL_PLAN_DAYS_PARAM)))
                messageDataDesc.setKey(strParamOrig);
        return messageDataDesc;
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
        if (record.getField(BookingHotel.NIGHTS).isNull())
            if (this.getMessageFieldDesc(BookingHotel.NIGHTS) != null)
        {
            iStatus = CostStatus.DATA_REQUIRED;        // The information must be valid to lookup the price
            String strError = "Data required in the {0} field";
            if (record.getTask() != null)
                strError = record.getTask().getApplication().getResources(ThinResourceConstants.ERROR_RESOURCE, true).getString(strError);
            strError = MessageFormat.format(strError, record.getField(BookingHotel.NIGHTS).getFieldDesc());
            ((BookingDetail)record).setErrorMessage((ProductRequest)this.getMessageDataParent(), strError);
        }
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
        ((Record)record).getField(BookingHotel.SINGLE_COST).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingHotel.DOUBLE_COST).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingHotel.TRIPLE_COST).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingHotel.QUAD_COST).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingHotel.ROOM_COST).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingHotel.MEAL_COST).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        return super.initForMessage(record);
    }
    /**
     * Move the fields of this record to this message
     * (a single Booking Detail record).
     */
    public int putRawRecordData(Rec record)
    {
        int iErrorCode = super.putRawRecordData(record);
        BookingDetail recBookingDetail = (BookingDetail)record;
        this.putRawFieldData(recBookingDetail.getField(BookingHotel.NIGHTS));
        for (int iMealNo = 1; iMealNo <= 4; iMealNo++)
        {
            int iFieldSeq = recBookingDetail.getFieldSeq(BookingHotel.MEAL_PLAN_1ID) + ((iMealNo - 1) * (recBookingDetail.getFieldSeq(BookingHotel.MEAL_PLAN_2ID) - recBookingDetail.getFieldSeq(BookingHotel.MEAL_PLAN_1ID)));
            int iMealPlanID = (int)recBookingDetail.getField(iFieldSeq).getValue();
            if (iMealPlanID > 0)
            {
                this.put(Hotel.MEAL_PLAN_ID_PARAM + Integer.toString(iMealNo), recBookingDetail.getField(iFieldSeq).getData());
                BaseField fieldMeals = recBookingDetail.getField(iFieldSeq + (recBookingDetail.getFieldSeq(BookingHotel.MEAL_PLAN_1_QTY) - recBookingDetail.getFieldSeq(BookingHotel.MEAL_PLAN_1ID)));
                this.put(Hotel.MEAL_PLAN_QTY_PARAM + Integer.toString(iMealNo), fieldMeals.getData());
                BaseField fieldDays = recBookingDetail.getField(iFieldSeq + (recBookingDetail.getFieldSeq(BookingHotel.MEAL_PLAN_1_DAYS) - recBookingDetail.getFieldSeq(BookingHotel.MEAL_PLAN_1ID)));
                this.put(Hotel.MEAL_PLAN_DAYS_PARAM + Integer.toString(iMealNo), fieldDays.getData());
            }
        }
        return iErrorCode;
    }
    /**
     * Move the data from this properyowner to this message.
     */
    public void putRawProperties(PropertyOwner propertyOwner)
    {
        this.put(BookingHotel.NIGHTS, propertyOwner.getProperty(BookingHotel.NIGHTS));
        this.put(BookingHotel.CLASS_ID, propertyOwner.getProperty(BookingHotel.CLASS_ID));
        this.put(BookingHotel.RATE_ID, propertyOwner.getProperty(BookingHotel.RATE_ID));
        this.put(Product.ROOM_TYPE_PARAM, propertyOwner.getProperty(Product.ROOM_TYPE_PARAM));
        this.put(BookingHotel.DETAIL_DATE, propertyOwner.getProperty(BookingHotel.DETAIL_DATE));
    }
    /**
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(Rec record)
    {
        this.getRawFieldData(((Record)record).getField(BookingHotel.NIGHTS));
        
        return super.getRawRecordData(record);
    }
    /**
     * GetNights Method.
     */
    public short getNights()
    {
        Short shNights = (Short)this.get(BookingHotel.NIGHTS);
        if (shNights == null)
            shNights = DURATION_DEFAULT;
        return shNights.shortValue();
    }
    /**
     * GetMealPlanID Method.
     */
    public int getMealPlanID(int iMealSeq)
    {
        Integer intMealPlanID = (Integer)this.get(Hotel.MEAL_PLAN_ID_PARAM + Integer.toString(iMealSeq));
        if (intMealPlanID == null)
            intMealPlanID = IntegerField.ZERO; 
        return intMealPlanID.intValue();
    }
    /**
     * GetMealQuantity Method.
     */
    public short getMealQuantity(int iMealSeq)
    {
        Short shMealPlanQty = (Short)this.get(Hotel.MEAL_PLAN_QTY_PARAM + Integer.toString(iMealSeq));
        if (shMealPlanQty == null)
            shMealPlanQty = ShortField.ZERO; 
        return shMealPlanQty.shortValue();
    }
    /**
     * GetMealDays Method.
     */
    public int getMealDays(int iMealSeq)
    {
        Short shMealDays = (Short)this.get(Hotel.MEAL_PLAN_DAYS_PARAM + Integer.toString(iMealSeq));
        if (shMealDays == null)
            shMealDays = ShortField.ZERO; 
        return shMealDays.shortValue();
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
    /**
     * GetProductClass Method.
     */
    public BaseClass getProductClass(RecordOwner recordOwner)
    {
        return new HotelClass(recordOwner);
    }
    /**
     * GetProductRate Method.
     */
    public BaseRate getProductRate(RecordOwner recordOwner)
    {
        return new HotelRate(recordOwner);
    }

}
