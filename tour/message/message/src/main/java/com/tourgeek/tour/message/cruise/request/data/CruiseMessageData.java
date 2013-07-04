
package com.tourgeek.tour.message.cruise.request.data;

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
import com.tourgeek.tour.message.base.request.data.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.model.message.*;
import com.tourgeek.model.tour.product.base.db.*;
import com.tourgeek.model.tour.booking.detail.db.*;
import com.tourgeek.model.tour.product.cruise.db.*;

/**
 *  CruiseMessageData - .
 */
public class CruiseMessageData extends ProductMessageData
{
    /**
     * Default constructor.
     */
    public CruiseMessageData()
    {
        super();
    }
    /**
     * CruiseMessageData Method.
     */
    public CruiseMessageData(MessageDataParent messageDataParent, String strKey)
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
    }
    /**
     * Check to make sure all the data is present to attempt a cost lookup.
     * Note: You are NOT returning the status, you are returning the status of the params,
     * The calling program will change the status if required.
     * @return DATA_REQUIRED if all the data is not present, DATA_VALID if the data is OKAY.
     */
    public int checkRequestParams(Rec record)
    {
        int iStatus = super.checkRequestParams(record);
        return iStatus;
    }
    /**
     * Initialize the fields in this record to prepare for this message.
     * Also, do any other preparation needed before sending this message.
     * @param record The record to initialize
     * @return An error code if there were any problems.
     */
    public int initForMessage(Rec record)
    {
        return super.initForMessage(record);
    }
    /**
     * Move the fields of this record to this message
     * (a single Booking Detail record).
     */
    public int putRawRecordData(Rec record)
    {
        BookingDetailModel recBookingDetail = (BookingDetailModel)record;
        return super.putRawRecordData(record);
    }
    /**
     * Move the data from this properyowner to this message.
     */
    public void putRawProperties(PropertyOwner propertyOwner)
    {
        this.put(BookingDetailModel.CLASS_ID, propertyOwner.getProperty(BookingDetailModel.CLASS_ID));
        this.put(BookingDetailModel.RATE_ID, propertyOwner.getProperty(BookingDetailModel.RATE_ID));
        this.put(BookingDetailModel.DETAIL_DATE, propertyOwner.getProperty(BookingDetailModel.DETAIL_DATE));
    }
    /**
     * Get/Create the product record.
     * @param bFindFirst If true, try to lookup the record first.
     * @return The product record.
     */
    public ProductModel getProductRecord(RecordOwner recordOwner, boolean bFindFirst)
    {
        if (bFindFirst)
            if (recordOwner != null)
                if (recordOwner.getRecord(CruiseModel.CRUISE_FILE) != null)
                    return (CruiseModel)recordOwner.getRecord(CruiseModel.CRUISE_FILE);
        return (CruiseModel)Record.makeRecordFromClassName(CruiseModel.THICK_CLASS, recordOwner);
    }
    /**
     * GetProductClass Method.
     */
    public BaseClassModel getProductClass(RecordOwner recordOwner)
    {
        return (CruiseClassModel)Record.makeRecordFromClassName(CruiseClassModel.THICK_CLASS, recordOwner);
    }
    /**
     * GetProductRate Method.
     */
    public BaseRateModel getProductRate(RecordOwner recordOwner)
    {
        return (CruiseRateModel)Record.makeRecordFromClassName(CruiseRateModel.THICK_CLASS, recordOwner);
    }

}
