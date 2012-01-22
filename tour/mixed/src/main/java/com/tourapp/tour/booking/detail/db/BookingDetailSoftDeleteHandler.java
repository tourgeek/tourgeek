/**
 * @(#)BookingDetailSoftDeleteHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.detail.db;

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
import java.util.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.product.tour.detail.db.*;
import com.tourapp.tour.booking.db.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.thin.app.booking.entry.*;
import com.tourapp.tour.booking.detail.event.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.booking.entry.detail.land.*;
import com.tourapp.tour.booking.entry.detail.hotel.*;
import com.tourapp.tour.booking.entry.detail.detail.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.*;
import org.jbundle.base.message.trx.transport.local.*;
import org.jbundle.base.message.trx.processor.*;
import org.jbundle.base.message.trx.transport.*;
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.db.event.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.booking.history.db.*;
import java.text.*;
import org.jbundle.thin.base.db.buff.*;

/**
 *  BookingDetailSoftDeleteHandler - .
 */
public class BookingDetailSoftDeleteHandler extends SoftDeleteHandler
{
    /**
     * Default constructor.
     */
    public BookingDetailSoftDeleteHandler()
    {
        super();
    }
    /**
     * BookingDetailSoftDeleteHandler Method.
     */
    public BookingDetailSoftDeleteHandler(BaseField fldDeleteFlag)
    {
        this();
        this.init(fldDeleteFlag);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(record);
    }
    /**
     * Soft delete this record?
     * Override this to decide whether to soft delete or physically delete the record.
     */
    public boolean isSoftDeleteThisRecord()
    {
        if (this.getOwner().getField(BookingDetail.kApTrxID).isNull())
            return false;   // If there is no active voucher, it is okay to delete this record.
        return super.isSoftDeleteThisRecord();
    }

}
