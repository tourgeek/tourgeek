/**
  * @(#)BookingLandRateMsgReplyInProcessor.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.message.land.response.in;

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
import com.tourgeek.tour.message.base.response.in.*;
import com.tourgeek.tour.booking.detail.db.*;
import org.jbundle.thin.base.message.*;
import com.tourgeek.tour.product.base.db.*;

/**
 *  BookingLandRateMsgReplyInProcessor - Process the incoming land rates..
 */
public class BookingLandRateMsgReplyInProcessor extends BookingDetailRateMsgReplyInProcessor
{
    /**
     * Default constructor.
     */
    public BookingLandRateMsgReplyInProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingLandRateMsgReplyInProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
        return new BookingLand(this);
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
