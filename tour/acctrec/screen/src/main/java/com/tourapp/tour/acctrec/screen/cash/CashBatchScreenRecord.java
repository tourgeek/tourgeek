/**
  * @(#)CashBatchScreenRecord.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.screen.model.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.base.field.*;

/**
 *  CashBatchScreenRecord - Cash Batch Variables.
 */
public class CashBatchScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String USER_ID = "UserID";
    public static final String TOTAL = "Total";
    public static final String COUNT = "Count";
    public static final String MCO_NO = "McoNo";
    public static final String BOOKING_ID = "BookingID";
    public static final String CHANGE_BALANCE = "ChangeBalance";
    public static final String END_BALANCE = "EndBalance";
    public static final String AIRLINE_ID = "AirlineID";
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

    public static final String CASH_BATCH_SCREEN_RECORD_FILE = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new CashBatchScreenRecord_UserID(this, USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new CurrencyField(this, TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 2)
            field = new ShortField(this, COUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 3)
            field = new StringField(this, MCO_NO, 16, null, null);
        if (iFieldSeq == 4)
            field = new BookingField(this, BOOKING_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new CurrencyField(this, CHANGE_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new CurrencyField(this, END_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new AirlineField(this, AIRLINE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
