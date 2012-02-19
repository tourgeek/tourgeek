/**
 * @(#)TourMessageData.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.tour.request.data;

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
import com.tourapp.tour.message.base.request.data.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.tour.db.*;

/**
 *  TourMessageData - .
 */
public class TourMessageData extends ProductMessageData
{
    /**
     * Default constructor.
     */
    public TourMessageData()
    {
        super();
    }
    /**
     * TourMessageData Method.
     */
    public TourMessageData(MessageDataParent messageDataParent, String strKey)
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
        this.removeMessageDataDesc(BookingDetail.RATE_ID);
        this.removeMessageDataDesc(BookingDetail.CLASS_ID);
    }
    /**
     * Get/Create the product record.
     * @param bFindFirst If true, try to lookup the record first.
     * @return The product record.
     */
    public Product getProductRecord(RecordOwner recordOwner, boolean bFindFirst)
    {
        if (bFindFirst)
            if (recordOwner != null)
                if (recordOwner.getRecord(TourHeader.TOUR_HEADER_FILE) != null)
                    return (TourHeader)recordOwner.getRecord(TourHeader.TOUR_HEADER_FILE);
        return new TourHeader(recordOwner);
    }

}
