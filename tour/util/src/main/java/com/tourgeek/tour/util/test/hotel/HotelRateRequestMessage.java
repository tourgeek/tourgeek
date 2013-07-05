/**
  * @(#)HotelRateRequestMessage.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.util.test.hotel;

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
import com.tourgeek.tour.message.hotel.request.out.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.core.trx.*;

/**
 *  HotelRateRequestMessage - .
 */
public class HotelRateRequestMessage extends RecordMapTrxMessage
{
    /**
     * Default constructor.
     */
    public HotelRateRequestMessage()
    {
        super();
    }
    /**
     * HotelRateRequestMessage Method.
     */
    public HotelRateRequestMessage(BaseMessageHeader messageHeader, Object mapMessage)
    {
        this();
        this.init(messageHeader, mapMessage);
    }
    /**
     * Init Method.
     */
    public void init(BaseMessageHeader messageHeader, Object data)
    {
        super.init(messageHeader, data);
        if (messageHeader != null)
            ((TrxMessageHeader)messageHeader).put(TrxMessageHeader.MESSAGE_PROCESSOR_CLASS, HotelRateRequestMessageOutProcessor.class.getName());    // From the hotel file
        //this.getMap().put(TrxMessageHeader.MESSAGE_CODE, HotelRateRequestMessageOutProcessor.MESSAGE_CODE);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        super.free();
    }

}
