/**
  * @(#)ProductResponseMessageData.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.message.base.response.data;

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
import com.tourapp.tour.message.base.*;
import com.tourapp.tour.message.base.response.*;
import org.jbundle.model.message.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.model.tour.product.base.db.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  ProductResponseMessageData - .
 */
public class ProductResponseMessageData extends BaseProductMessageData
{
    /**
     * Default constructor.
     */
    public ProductResponseMessageData()
    {
        super();
    }
    /**
     * ProductResponseMessageData Method.
     */
    public ProductResponseMessageData(MessageDataParent messageDataParent, String strKey)
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
        this.addMessageFieldDesc(BookingDetailModel.PRODUCT_ID, Integer.class, MessageFieldDesc.OPTIONAL, null);
        this.addMessageFieldDesc(BaseDataStatusModel.DATA_STATUS, Integer.class, MessageFieldDesc.OPTIONAL, null);
        this.addMessageFieldDesc(BaseDataStatusModel.DATA_ERROR_MESSAGE, String.class, MessageFieldDesc.OPTIONAL, null);
    }
    /**
     * Initialize the fields in this record to prepare for this message.
     * Also, do any other preparation needed before sending this message.
     * @param record The record to initialize
     * @return An error code if there were any problems.
     */
    public int initForMessage(Rec record)
    {
        int iErrorCode = Constants.NORMAL_RETURN;
        // Do not clear the confirmation fields!
        return iErrorCode;
    }
    /**
     * GetMessageDataError Method.
     */
    public String getMessageDataError()
    {
        String strErrorMessage = (String)this.get(BaseDataStatusModel.DATA_ERROR_MESSAGE);
        if (strErrorMessage == null)
            strErrorMessage = DBConstants.BLANK;
        return strErrorMessage;
    }
    /**
     * GetMessageDataStatus Method.
     */
    public int getMessageDataStatus()
    {
        Integer intMessageStatus = (Integer)this.get(BaseDataStatusModel.DATA_STATUS);
        if (intMessageStatus == null)
            intMessageStatus = new Integer(BaseDataStatusModel.NO_STATUS);
        return intMessageStatus.intValue();
    }
    /**
     * SetMessageDataError Method.
     */
    public void setMessageDataError(String strErrorMessage)
    {
        this.put(BaseDataStatusModel.DATA_ERROR_MESSAGE, strErrorMessage);
    }
    /**
     * SetMessageDataStatus Method.
     */
    public void setMessageDataStatus(int iMessageStatus)
    {
        this.put(BaseDataStatusModel.DATA_STATUS, new Integer(iMessageStatus));
    }
    /**
     * SetAvailability Method.
     */
    public void setAvailability(int iAvailability)
    {
        this.put(ProductModel.AVAILABILITY_PARAM, new Integer(iAvailability));
    }
    /**
     * SetProductCost Method.
     */
    public void setProductCost(double dCost)
    {
        this.put(BookingDetailModel.TOTAL_COST, new Double(dCost));
    }
    /**
     * Set the remote booking number.
     */
    public void setRemoteBookingNo(String strRemoteBookingNo)
    {
        this.put(BookingDetailModel.REMOTE_BOOKING_NO, strRemoteBookingNo);
    }
    /**
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(Rec record)
    {
        int iInfoStatus = super.getRawRecordData(record);
        BookingDetailModel recBookingDetail = (BookingDetailModel)record;
        
        String strConfirmedBy = (String)this.get(ProductModel.CONFIRMED_BY_PARAM);
        ((PropertiesField)recBookingDetail.getField(BookingDetailModel.PROPERTIES)).setProperty(((BaseProductResponse)this.getMessageDataParent()).getKeyPrefix() + ProductModel.CONFIRMED_BY_PARAM, strConfirmedBy);
        String strConfirmationNo = (String)this.get(ProductModel.CONFIRMATION_NO_PARAM);
        ((PropertiesField)recBookingDetail.getField(BookingDetailModel.PROPERTIES)).setProperty(((BaseProductResponse)this.getMessageDataParent()).getKeyPrefix() + ProductModel.CONFIRMATION_NO_PARAM, strConfirmationNo);
        if (this.get(BookingDetailModel.REMOTE_BOOKING_NO) != null)
            recBookingDetail.getField(BookingDetailModel.REMOTE_BOOKING_NO).setString((String)this.get(BookingDetailModel.REMOTE_BOOKING_NO).toString());
        if (this.get(BookingDetailModel.REMOTE_REFERENCE_NO) != null)
            recBookingDetail.getField(BookingDetailModel.REMOTE_REFERENCE_NO).setString((String)this.get(BookingDetailModel.REMOTE_REFERENCE_NO).toString());
        
        return iInfoStatus;
    }

}
