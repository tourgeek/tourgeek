/**
 *  @(#)HotelRateMessageListener.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.hotel.event;

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
import com.tourapp.tour.product.base.event.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.hotel.response.*;
import com.tourapp.tour.message.hotel.response.data.*;
import com.tourapp.tour.message.base.response.*;

/**
 *  HotelRateMessageListener - Listen for the (private) hotel rate messages..
 */
public class HotelRateMessageListener extends ProductRateMessageListener
{
    /**
     * Default constructor.
     */
    public HotelRateMessageListener()
    {
        super();
    }
    /**
     * HotelRateMessageListener Method.
     */
    public HotelRateMessageListener(BaseMessageReceiver messageHandler, Record record, boolean bUpdateRecord, BaseMessageFilter messageFilter)
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
     * Convert this message map to the message map AutoRecordMessageListener
     * is expecting, so the correct fields will be updated.
     */
    public void fixMessageMap(BaseMessage message)
    {
        super.fixMessageMap(message);
        HotelRateResponse productRateResponse = (HotelRateResponse)message.getMessageDataDesc(null);
        HotelRateResponseMessageData messageData = (HotelRateResponseMessageData)productRateResponse.getMessageDataDesc(BaseProductResponse.PRODUCT_RESPONSE_MESSAGE);
        String strDoubleCostParam = m_record.getField(Hotel.kDoubleCost).getFieldName();
        Double dblProductCost = (Double)messageData.get(BookingDetail.TOTAL_COST);
        if (dblProductCost == null)
            dblProductCost = DoubleField.ZERO;
        message.put(strDoubleCostParam, dblProductCost.toString());
    }
    /**
     * Add message description.
     */
    public ProductRateResponse addMessageDesc(BaseMessage message)
    {
        return new HotelRateResponse(message, null);
    }

}
