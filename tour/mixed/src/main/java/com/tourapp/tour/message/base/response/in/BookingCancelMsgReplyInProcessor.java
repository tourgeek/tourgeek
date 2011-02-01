/**
 *  @(#)BookingCancelMsgReplyInProcessor.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.base.response.in;

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
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.message.base.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.booking.detail.db.*;

/**
 *  BookingCancelMsgReplyInProcessor - Process cancel booking message.
 */
public class BookingCancelMsgReplyInProcessor extends ProductResponseMsgReplyInProcessor
{
    /**
     * Default constructor.
     */
    public BookingCancelMsgReplyInProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingCancelMsgReplyInProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
    {
        this();
        this.init(taskParent, recordMain, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwnerParent taskParent, Record recordMain, Map<String, Object> properties)
    {
        super.init(taskParent, recordMain, properties);
    }
    /**
     * Process this internal message.
     * @param internalMessage The message to process.
     * @return (optional) The return message if applicable.
     */
    public BaseMessage processMessage(BaseMessage internalMessage)
    {
        BaseMessage messageOrig = this.getOriginalMessage(internalMessage);
        BaseProductMessageDesc message = (BaseProductMessageDesc)internalMessage.getMessageDataDesc(null);
        if (messageOrig != null)
        {   // Open the process message
            if (messageOrig.getMessageDataDesc(null) != null)
                if (messageOrig.getMessageDataDesc(null).getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE) instanceof ProductMessageData)
            {   // Always
                ProductMessageData productRequest = (ProductMessageData)messageOrig.getMessageDataDesc(null).getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
                String strProductType = (String)productRequest.get(BookingDetail.PRODUCT_TYPE);
                this.openProductFile(strProductType);
            }
        }
        return super.processMessage(internalMessage);
    }
    /**
     * OpenProductFile Method.
     */
    public Record openProductFile(String strProductType)
    {
        Record record = BookingDetail.createSharedDetailRecord(strProductType, this);
        if (record != this.getMainRecord())
        {
            this.removeRecord(record);
            this.addRecord(record, true);
        }
        return record;
    }
    /**
     * GetStatusFieldSeq Method.
     */
    public int getStatusFieldSeq()
    {
        return BookingDetail.kProductStatusID;
    }

}
