/**
  * @(#)BookingTourInfoMsgReplyInProcessor.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.message.tour.response.in;

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
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  BookingTourInfoMsgReplyInProcessor - .
 */
public class BookingTourInfoMsgReplyInProcessor extends BookingDetailInfoMsgReplyInProcessor
{
    /**
     * Default constructor.
     */
    public BookingTourInfoMsgReplyInProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingTourInfoMsgReplyInProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
        return new BookingTour(this);
    }
    /**
     * SetRecordDataStatus Method.
     */
    public int setRecordDataStatus(Record record, String iFieldSeq, int iStatus, ProductMessageData productRequest)
    {
        if (iStatus == BaseDataStatus.OKAY)
        {
            FieldDataScratchHandler fieldDataScratchHandler = (FieldDataScratchHandler)record.getField(BookingDetail.DETAIL_DATE).getListener(FieldDataScratchHandler.class, false);
            if (fieldDataScratchHandler != null)
            { // Always
                Date dateOriginal = (Date)productRequest.get(ProductMessageData.OLD_DETAIL_DATE);
                fieldDataScratchHandler.setOriginalData(dateOriginal);  // Make sure you know the original date
            }
        }
        return super.setRecordDataStatus(record, iFieldSeq, iStatus, productRequest);
    }

}
