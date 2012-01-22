/**
 * @(#)CashBatchScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.cash;

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
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.air.db.*;

/**
 *  CashBatchScreenRecord - Cash Batch Variables.
 */
public class CashBatchScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kUserID = kScreenRecordLastField + 1;
    public static final int kTotal = kUserID + 1;
    public static final int kCount = kTotal + 1;
    public static final int kMcoNo = kCount + 1;
    public static final int kBookingID = kMcoNo + 1;
    public static final int kChangeBalance = kBookingID + 1;
    public static final int kEndBalance = kChangeBalance + 1;
    public static final int kAirlineID = kEndBalance + 1;
    public static final int kCashBatchScreenRecordLastField = kAirlineID;
    public static final int kCashBatchScreenRecordFields = kAirlineID - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public CashBatchScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CashBatchScreenRecord(RecordOwner screen)
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

    public static final String kCashBatchScreenRecordFile = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kUserID)
            field = new CashBatchScreenRecord_UserID(this, "UserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotal)
            field = new CurrencyField(this, "Total", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCount)
            field = new ShortField(this, "Count", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMcoNo)
            field = new StringField(this, "McoNo", 16, null, null);
        if (iFieldSeq == kBookingID)
            field = new BookingField(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kChangeBalance)
            field = new CurrencyField(this, "ChangeBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndBalance)
            field = new CurrencyField(this, "EndBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAirlineID)
            field = new AirlineField(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCashBatchScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
