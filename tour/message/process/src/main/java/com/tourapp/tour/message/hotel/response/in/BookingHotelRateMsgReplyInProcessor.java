/**
  * @(#)BookingHotelRateMsgReplyInProcessor.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.message.hotel.response.in;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.message.base.response.in.*;
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.base.message.core.trx.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.hotel.response.*;

/**
 *  BookingHotelRateMsgReplyInProcessor - This task process the incoming replies from rate requests.
Then it uses the information to update the booking hotel detail..
 */
public class BookingHotelRateMsgReplyInProcessor extends BookingDetailRateMsgReplyInProcessor
{
    /**
     * Default constructor.
     */
    public BookingHotelRateMsgReplyInProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingHotelRateMsgReplyInProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
     * Open the main file.
     */
    public Record openMainRecord()
    {
        return new BookingHotel(this);
    }
    /**
     * Process this internal message.
     * @param internalMessage The message to process.
     * @return (optional) The return message if applicable.
     */
    public BaseMessage processMessage(BaseMessage internalMessage)
    {
        return super.processMessage(internalMessage);
    }

}
