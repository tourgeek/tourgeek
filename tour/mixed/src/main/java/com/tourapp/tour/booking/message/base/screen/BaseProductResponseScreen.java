/**
 * @(#)BaseProductResponseScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.message.base.screen;

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
import org.jbundle.main.msg.screen.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.message.base.response.data.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.profile.db.*;

/**
 *  BaseProductResponseScreen - .
 */
public class BaseProductResponseScreen extends MessageScreen
{
    /**
     * Default constructor.
     */
    public BaseProductResponseScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public BaseProductResponseScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new ProductInfoScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getScreenRecord().getField(ProductInfoScreenRecord.kTotalCost).setEnabled(true);
        this.getScreenRecord().getField(ProductInfoScreenRecord.kAvailability).setEnabled(true);
        this.getScreenRecord().getField(ProductInfoScreenRecord.kConfirmationNo).setEnabled(true);
        this.getScreenRecord().getField(ProductInfoScreenRecord.kConfirmedBy).setEnabled(true);
    }
    /**
     * Does the current user have permission to access this screen.
     * @return NORMAL_RETURN if access is allowed, ACCESS_DENIED or LOGIN_REQUIRED otherwise.
     */
    public int checkSecurity()
    {
        int iErrorCode = super.checkSecurity();
        if (iErrorCode == DBConstants.NORMAL_RETURN)
        {   // Okay, their group can access this screen, but can this user access this data?
            BaseMessage message = this.getMessage();
            String strContactID = (String)message.getMessageHeader().get(TrxMessageHeader.CONTACT_ID);
            String strContactType = (String)message.getMessageHeader().get(TrxMessageHeader.CONTACT_TYPE);
            iErrorCode = this.checkContactSecurity(strContactType, strContactID);
        }
        return iErrorCode;
    }
    /**
     * Move the original(sent) message params to this screen.
     */
    public void moveMessageParamsToScreen(BaseMessage message)
    {
        Record screenRecord = this.getScreenRecord();
        ProductMessageData messageData = (ProductMessageData)message.getMessageDataDesc(null).getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        messageData.getRawFieldData(screenRecord.getField(ProductInfoScreenRecord.kProductID));
        messageData.getRawFieldData(screenRecord.getField(ProductInfoScreenRecord.kRateID));
        messageData.getRawFieldData(screenRecord.getField(ProductInfoScreenRecord.kClassID));
        messageData.getRawFieldData(screenRecord.getField(ProductInfoScreenRecord.kDetailDate));
    }
    /**
     * Move to entered fields to the return message.
     */
    public void moveScreenParamsToMessage(BaseMessage message)
    {
        super.moveScreenParamsToMessage(message);
        
        ProductResponseMessageData messageData = (ProductResponseMessageData)message.getMessageDataDesc(BaseProductResponse.PRODUCT_RESPONSE_MESSAGE);
        Double dblCost = (Double)this.getRecord(ProductInfoScreenRecord.kProductInfoScreenRecordFile).getField(ProductInfoScreenRecord.kTotalCost).getData();
        messageData.put(BookingDetail.TOTAL_COST, dblCost);
        Integer intAvailability = (Integer)this.getRecord(ProductInfoScreenRecord.kProductInfoScreenRecordFile).getField(ProductInfoScreenRecord.kAvailability).getData();
        messageData.put(Product.AVAILABILITY_PARAM, intAvailability);
        String strConfirmationNo = this.getRecord(ProductInfoScreenRecord.kProductInfoScreenRecordFile).getField(ProductInfoScreenRecord.kConfirmationNo).getString();
        messageData.put(Product.CONFIRMATION_NO_PARAM, strConfirmationNo);
        String strConfirmedBy = this.getRecord(ProductInfoScreenRecord.kProductInfoScreenRecordFile).getField(ProductInfoScreenRecord.kConfirmedBy).getString();
        messageData.put(Product.CONFIRMED_BY_PARAM, strConfirmedBy);
        
        BaseProductResponse productResponseData = (BaseProductResponse)message.getMessageDataDesc(null);
        productResponseData.setMessageDataStatus(MessageDataDesc.VALID);  // Change this in the overriding class if not valid      
    }

}
