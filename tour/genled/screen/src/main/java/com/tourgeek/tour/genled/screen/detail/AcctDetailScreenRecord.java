/**
  * @(#)AcctDetailScreenRecord.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.genled.screen.detail;

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
import com.tourgeek.tour.genled.db.*;

/**
 *  AcctDetailScreenRecord - Screen Field for Transaction Inquiry.
 */
public class AcctDetailScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String ACCOUNT_ID = "AccountID";
    public static final String CALC_START = "CalcStart";
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String START_BALANCE = "StartBalance";
    public static final String END_BALANCE = "EndBalance";
    public static final String CHANGE_BALANCE = "ChangeBalance";
    /**
     * Default constructor.
     */
    public AcctDetailScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AcctDetailScreenRecord(RecordOwner screen)
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

    public static final String ACCT_DETAIL_SCREEN_RECORD_FILE = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new AccountField(this, ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new BooleanField(this, CALC_START, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 2)
            field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 3)
            field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new CurrencyField(this, START_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new CurrencyField(this, END_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new CurrencyField(this, CHANGE_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
