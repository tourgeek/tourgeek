/**
  * @(#)CarMessageData.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.message.car.request.data;

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
import com.tourgeek.model.tour.product.car.db.*;
import com.tourgeek.model.tour.booking.detail.db.*;

/**
 *  CarMessageData - .
 */
public class CarMessageData extends ProductMessageData
{
    public static final Short QUANTITY_DEFAULT = new Short((short)1);
    /**
     * Default constructor.
     */
    public CarMessageData()
    {
        super();
    }
    /**
     * CarMessageData Method.
     */
    public CarMessageData(MessageDataParent messageDataParent, String strKey)
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
        this.addMessageFieldDesc(BookingCarModel.DAYS, Integer.class, MessageFieldDesc.REQUIRED, null);
        this.addMessageFieldDesc(BookingCarModel.QUANTITY, Short.class, MessageFieldDesc.REQUIRED, null);
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
     * Move the data from this properyowner to this message.
     */
    public void putRawProperties(PropertyOwner propertyOwner)
    {
        this.put(BookingCarModel.DAYS, propertyOwner.getProperty(BookingCarModel.DAYS));
        this.put(BookingCarModel.QUANTITY, propertyOwner.getProperty(BookingCarModel.QUANTITY));
        this.put(BookingDetailModel.CLASS_ID, propertyOwner.getProperty(BookingDetailModel.CLASS_ID));
        this.put(BookingDetailModel.RATE_ID, propertyOwner.getProperty(BookingDetailModel.RATE_ID));
        this.put(BookingDetailModel.DETAIL_DATE, propertyOwner.getProperty(BookingDetailModel.DETAIL_DATE));
    }
    /**
     * Move the fields of this record to this message
     * (a single Booking Detail record).
     */
    public int putRawRecordData(Rec record)
    {
        int iErrorCode = super.putRawRecordData(record);
        Rec recBookingDetail = (BookingDetailModel)record;
        this.putRawFieldData(recBookingDetail.getField(BookingCarModel.QUANTITY));
        this.putRawFieldData(recBookingDetail.getField(BookingCarModel.DAYS));
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
                if (recordOwner.getRecord(CarModel.CAR_FILE) != null)
                    return (CarModel)recordOwner.getRecord(CarModel.CAR_FILE);
        return (CarModel)Record.makeRecordFromClassName(CarModel.THICK_CLASS, recordOwner);
    }
    /**
     * GetProductRate Method.
     */
    public BaseRateModel getProductRate(RecordOwner recordOwner)
    {
        return (CarRateModel)Record.makeRecordFromClassName(CarRateModel.THICK_CLASS, recordOwner);
    }
    /**
     * GetProductClass Method.
     */
    public BaseClassModel getProductClass(RecordOwner recordOwner)
    {
        return (CarClassModel)Record.makeRecordFromClassName(CarClassModel.THICK_CLASS, recordOwner);
    }
    /**
     * GetQuantity Method.
     */
    public short getQuantity()
    {
        Short shQuantity = (Short)this.get(BookingCarModel.QUANTITY);
        if (shQuantity == null)
            shQuantity = QUANTITY_DEFAULT;
        return shQuantity.shortValue();
    }
    /**
     * Get the number of days this car is rented for.
     */
    public int getDays()
    {
        Integer intDays = (Integer)this.get(BookingCarModel.DAYS);
        if (intDays == null)
            intDays = 1;
        return intDays.intValue();
    }

}
