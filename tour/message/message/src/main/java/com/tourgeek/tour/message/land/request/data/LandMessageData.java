/**
  * @(#)LandMessageData.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.message.land.request.data;

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
import com.tourgeek.model.tour.product.land.db.*;
import com.tourgeek.model.tour.booking.detail.db.*;
import com.tourgeek.model.tour.product.base.db.*;

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
        this.removeMessageDataDesc(BookingDetailModel.RATE_ID);
        this.removeMessageDataDesc(BookingDetailModel.CLASS_ID);
        this.addMessageFieldDesc(BookingDetailModel.RATE_ID, Integer.class, MessageFieldDesc.OPTIONAL, null);
        this.addMessageFieldDesc(BookingDetailModel.CLASS_ID, Integer.class, MessageFieldDesc.OPTIONAL, null);
        this.addMessageFieldDesc(ProductModel.ROOM_TYPE_PARAM, Short.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.COMPOUND_PARAM | PaxCategoryModel.CHILD_ID, null);
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
        ((Record)record).getField(BookingLandModel.PP_COST).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingLandModel.VARIES_CODE).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingLandModel.VARIES_QTY).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingLandModel.VARIES_COST).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingLandModel.PMC_CUTOFF).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingLandModel.PMC_COST).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((Record)record).getField(BookingLandModel.SIC_COST).setData(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        return iErrorCode;
    }
    /**
     * Move the fields of this record to this message
     * (a single Booking Detail record).
     */
    public int putRawRecordData(Rec record)
    {
        int iErrorCode = super.putRawRecordData(record);
        BookingLandModel recBookingLand = (BookingLandModel)record;
        return iErrorCode;
    }
    /**
     * Move the data from this properyowner to this message.
     */
    public void putRawProperties(PropertyOwner propertyOwner)
    {
        this.put(BookingDetailModel.RATE_ID, propertyOwner.getProperty(BookingDetailModel.RATE_ID));
        this.put(BookingDetailModel.CLASS_ID, propertyOwner.getProperty(BookingDetailModel.CLASS_ID));
        this.put(BookingDetailModel.DETAIL_DATE, propertyOwner.getProperty(BookingDetailModel.DETAIL_DATE));
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
                if (recordOwner.getRecord(LandModel.LAND_FILE) != null)
                    return (LandModel)recordOwner.getRecord(LandModel.LAND_FILE);
        return (LandModel)Record.makeRecordFromClassName(LandModel.THICK_CLASS, recordOwner);
    }
    /**
     * GetProductClass Method.
     */
    public BaseClassModel getProductClass(RecordOwner recordOwner)
    {
        return (LandClassModel)Record.makeRecordFromClassName(LandClassModel.THICK_CLASS, recordOwner);
    }
    /**
     * GetProductRate Method.
     */
    public BaseRateModel getProductRate(RecordOwner recordOwner)
    {
        return (LandRateModel)Record.makeRecordFromClassName(LandRateModel.THICK_CLASS, recordOwner);
    }

}
