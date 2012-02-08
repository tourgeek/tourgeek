/**
 * @(#)HotelRateRequestMessageOutProcessor.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.hotel.request.out;

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
import org.jbundle.base.message.trx.processor.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.trx.transport.*;
import org.jbundle.base.message.core.trx.*;
import org.jbundle.base.message.trx.transport.soap.*;
import javax.xml.bind.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.message.hotel.request.*;

/**
 *  HotelRateRequestMessageOutProcessor - Send a rate request directly to a hotel..
 */
public class HotelRateRequestMessageOutProcessor extends BaseMessageOutProcessor
{
    /**
     * Default constructor.
     */
    public HotelRateRequestMessageOutProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public HotelRateRequestMessageOutProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
        TrxMessageHeader messageHeader = (TrxMessageHeader)internalMessage.getMessageHeader();
        //if (messageHeader.get(TrxMessageHeader.MESSAGE_CODE) == null)
        //    messageHeader.put(TrxMessageHeader.MESSAGE_CODE, HotelRateRequestMessageOutProcessor.MESSAGE_CODE);    // External operation name
        //if (messageHeader.get(TrxMessageHeader.MESSAGE_RESPONSE_CODE) == null)  // For email transport, need to know who processes the returned message
        //    messageHeader.put(TrxMessageHeader.MESSAGE_RESPONSE_CODE, com.tourapp.tour.product.hotel.message.in.HotelRateResponseSOAPMsgReplyOut.MESSAGE_CODE);    // External operation name
        return null;    // No return from a message out
    }

}
