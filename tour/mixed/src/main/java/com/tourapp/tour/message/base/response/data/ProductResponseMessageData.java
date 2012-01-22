/**
 * @(#)ProductResponseMessageData.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.base.response.data;

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
import com.tourapp.tour.message.base.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.base.response.*;
import org.jbundle.thin.base.message.*;

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
        this.addMessageFieldDesc(BookingDetail.PRODUCT_ID, Integer.class, MessageFieldDesc.OPTIONAL, null);
        this.addMessageFieldDesc(BaseDataStatus.DATA_STATUS, Integer.class, MessageFieldDesc.OPTIONAL, null);
        this.addMessageFieldDesc(BaseDataStatus.DATA_ERROR_MESSAGE, String.class, MessageFieldDesc.OPTIONAL, null);
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
        String strErrorMessage = (String)this.get(BaseDataStatus.DATA_ERROR_MESSAGE);
        if (strErrorMessage == null)
            strErrorMessage = DBConstants.BLANK;
        return strErrorMessage;
    }
    /**
     * GetMessageDataStatus Method.
     */
    public int getMessageDataStatus()
    {
        Integer intMessageStatus = (Integer)this.get(BaseDataStatus.DATA_STATUS);
        if (intMessageStatus == null)
            intMessageStatus = new Integer(BaseDataStatus.NO_STATUS);
        return intMessageStatus.intValue();
    }
    /**
     * SetMessageDataError Method.
     */
    public void setMessageDataError(String strErrorMessage)
    {
        this.put(BaseDataStatus.DATA_ERROR_MESSAGE, strErrorMessage);
    }
    /**
     * SetMessageDataStatus Method.
     */
    public void setMessageDataStatus(int iMessageStatus)
    {
        this.put(BaseDataStatus.DATA_STATUS, new Integer(iMessageStatus));
    }
    /**
     * SetAvailability Method.
     */
    public void setAvailability(int iAvailability)
    {
        this.put(Product.AVAILABILITY_PARAM, new Integer(iAvailability));
    }
    /**
     * SetProductCost Method.
     */
    public void setProductCost(double dCost)
    {
        this.put(BookingDetail.TOTAL_COST, new Double(dCost));
    }
    /**
     * Set the remote booking number.
     */
    public void setRemoteBookingNo(String strRemoteBookingNo)
    {
        this.put(BookingDetail.REMOTE_BOOKING_NO, strRemoteBookingNo);
    }
    /**
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(Rec record)
    {
        int iInfoStatus = super.getRawRecordData(record);
        BookingDetail recBookingDetail = (BookingDetail)record;
        
        String strConfirmedBy = (String)this.get(Product.CONFIRMED_BY_PARAM);
        ((PropertiesField)recBookingDetail.getField(BookingDetail.kProperties)).setProperty(((BaseProductResponse)this.getMessageDataParent()).getKeyPrefix() + Product.CONFIRMED_BY_PARAM, strConfirmedBy);
        String strConfirmationNo = (String)this.get(Product.CONFIRMATION_NO_PARAM);
        ((PropertiesField)recBookingDetail.getField(BookingDetail.kProperties)).setProperty(((BaseProductResponse)this.getMessageDataParent()).getKeyPrefix() + Product.CONFIRMATION_NO_PARAM, strConfirmationNo);
        if (this.get(BookingDetail.REMOTE_BOOKING_NO) != null)
            recBookingDetail.getField(BookingDetail.kRemoteBookingNo).setString((String)this.get(BookingDetail.REMOTE_BOOKING_NO).toString());
        if (this.get(BookingDetail.REMOTE_REFERENCE_NO) != null)
            recBookingDetail.getField(BookingDetail.kRemoteReferenceNo).setString((String)this.get(BookingDetail.REMOTE_REFERENCE_NO).toString());
        
        return iInfoStatus;
    }

}
