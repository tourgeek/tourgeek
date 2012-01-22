/**
 * @(#)BookingHotelRateAvailMsgReplyInProcessor.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.base.request.data.*;

/**
 *  BookingHotelRateAvailMsgReplyInProcessor - Processor for a combined Rate AND Availability message.
 */
public class BookingHotelRateAvailMsgReplyInProcessor extends BookingHotelAvailMsgReplyInProcessor
{
    /**
     * Default constructor.
     */
    public BookingHotelRateAvailMsgReplyInProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingHotelRateAvailMsgReplyInProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
     * SetRecordDataStatus Method.
     */
    public int setRecordDataStatus(Record record, int iFieldSeq, int iStatus, ProductMessageData productRequest)
    {
        int iErrorCode = super.setRecordDataStatus(record, iFieldSeq, iStatus, productRequest);
        if (iErrorCode == DBConstants.NORMAL_RETURN)
        if (iFieldSeq == BookingDetail.kInventoryStatusID)
            iErrorCode = productRequest.setRecordDataStatus(record, BookingDetail.kCostStatusID, iStatus);  // Make sure all the detail has this status
        return iErrorCode;
    }

}
