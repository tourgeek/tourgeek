/**
  * @(#)ProductRateMessageListener.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.product.base.event;

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
import org.jbundle.base.message.opt.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.base.remote.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.message.base.response.data.*;
import org.jbundle.base.message.core.trx.*;
import org.jbundle.main.db.base.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  ProductRateMessageListener - Handle incoming rate messages.
 */
public class ProductRateMessageListener extends AutoRecordMessageListener
{
    /**
     * Default constructor.
     */
    public ProductRateMessageListener()
    {
        super();
    }
    /**
     * ProductRateMessageListener Method.
     */
    public ProductRateMessageListener(BaseMessageReceiver messageHandler, Record record, boolean bUpdateRecord, BaseMessageFilter messageFilter)
    {
        this();
        this.init(messageHandler, record, bUpdateRecord, messageFilter);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseMessageReceiver messageHandler, Record record, boolean bUpdateRecord, BaseMessageFilter messageFilter)
    {
        super.init(messageHandler, record, bUpdateRecord, messageFilter);
    }
    /**
     * Handle the incoming message.
     */
    public int handleMessage(BaseMessage message)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        if (m_record != null)
        {
            RecordOwner recordOwner = m_record.getRecordOwner();
            if (recordOwner != null)
            {
                Record screenRecord = (Record)recordOwner.getScreenRecord();
                if (screenRecord instanceof ProductScreenRecord)
                {   // Always
                    if (!((ProductScreenRecord)screenRecord).checkPriceProperties(message, (Product)m_record))
                        return DBConstants.NORMAL_RETURN;   // The user is not looking for these prices anymore, don't display
                }
            }
            this.fixMessageMap(message);
            iErrorCode = super.handleMessage(message);  // Owner is a GridScreen, so the AutoRecord logic
        }
        return iErrorCode;
    }
    /**
     * Convert this message map to the message map AutoRecordMessageListener
     * is expecting, so the correct fields will be updated.
     */
    public void fixMessageMap(BaseMessage message)
    {
        ProductRateResponse productRateResponse = this.addMessageDesc(message);
        ProductRateResponseMessageData messageData = (ProductRateResponseMessageData)productRateResponse.getMessageDataDesc(BaseProductResponse.PRODUCT_RESPONSE_MESSAGE);
        
        message.put(DBParams.FIELD, MULTIPLE_FIELDS);
        String strProductCostParam = m_record.getField(Product.PRODUCT_COST).getFieldName();
        Double dblProductCost = (Double)messageData.get(BookingDetailModel.TOTAL_COST);
        if (dblProductCost == null)
            dblProductCost = DoubleField.ZERO;
        message.put(strProductCostParam, dblProductCost.toString());
        String strCostStatusParam = m_record.getField(Product.DISPLAY_COST_STATUS_ID).getFieldName();
        Integer intProductStatus = (Integer)productRateResponse.get(BaseDataStatus.DATA_STATUS);
        if (intProductStatus == null)
            intProductStatus = BaseStatus.VALID;
        message.put(strCostStatusParam, intProductStatus.toString());
    }
    /**
     * Add message description.
     */
    public ProductRateResponse addMessageDesc(BaseMessage message)
    {
        return new ProductRateResponse(message, null);  // Override this if you can
    }

}
