/**
 * @(#)BookingDetailScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.detail.base;

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
import com.tourapp.tour.booking.db.*;

/**
 *  BookingDetailScreenRecord - .
 */
public class BookingDetailScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kPPCostLocal = kScreenRecordLastField + 1;
    public static final int kBookingDetailScreenRecordLastField = kPPCostLocal;
    public static final int kBookingDetailScreenRecordFields = kPPCostLocal - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public BookingDetailScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingDetailScreenRecord(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }

    public static final String kBookingDetailScreenRecordFile = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kPPCostLocal)
            field = new CurrencyField(this, "PPCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBookingDetailScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
