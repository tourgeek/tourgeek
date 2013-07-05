/**
  * @(#)BookingDetailSoftDeleteHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.booking.detail.db;

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
import java.util.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.product.tour.detail.db.*;
import com.tourgeek.tour.booking.db.*;
import org.jbundle.thin.base.message.*;
import com.tourgeek.thin.app.booking.entry.*;
import com.tourgeek.tour.booking.detail.event.*;
import org.jbundle.base.message.core.trx.*;
import org.jbundle.main.msg.db.*;
import com.tourgeek.tour.message.base.*;
import org.jbundle.base.message.trx.transport.local.*;
import org.jbundle.base.message.trx.processor.*;
import org.jbundle.base.message.trx.transport.*;
import com.tourgeek.tour.booking.db.event.*;
import com.tourgeek.tour.message.base.response.*;
import com.tourgeek.tour.message.base.request.*;
import com.tourgeek.tour.message.base.request.data.*;
import com.tourgeek.tour.booking.history.db.*;
import java.text.*;
import org.jbundle.thin.base.db.buff.*;
import com.tourgeek.model.tour.booking.db.*;
import org.jbundle.model.message.*;
import com.tourgeek.model.tour.product.base.db.*;
import com.tourgeek.model.tour.product.tour.detail.db.*;

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
        if (this.getOwner().getField(BookingDetail.AP_TRX_ID).isNull())
            return false;   // If there is no active voucher, it is okay to delete this record.
        return super.isSoftDeleteThisRecord();
    }

}
