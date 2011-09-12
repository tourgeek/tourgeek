/**
 * @(#)BookingDetailRateMsgReplyInProcessor.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import com.tourapp.tour.booking.detail.db.*;

/**
 *  BookingDetailRateMsgReplyInProcessor - .
 */
public class BookingDetailRateMsgReplyInProcessor extends ProductResponseMsgReplyInProcessor
{
    /**
     * Default constructor.
     */
    public BookingDetailRateMsgReplyInProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingDetailRateMsgReplyInProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
     * GetStatusFieldSeq Method.
     */
    public int getStatusFieldSeq()
    {
        return BookingDetail.kCostStatusID;
    }

}
