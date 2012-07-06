/**
  * @(#)BankTrxScreenRecord.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.assetdr.screen.trx;

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
import com.tourapp.tour.assetdr.db.*;

/**
 *  BankTrxScreenRecord - Params for Bank Trx display.
 */
public class BankTrxScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String USER_ID = "UserID";
    public static final String BANK_ACCT_ID = "BankAcctID";
    public static final String START_DATE = "StartDate";
    public static final String DISPLAY_BALANCE = "DisplayBalance";
    public static final String START_BALANCE = "StartBalance";
    public static final String END_BALANCE = "EndBalance";
    public static final String CHANGE_BALANCE = "ChangeBalance";
    public static final String TRX_COUNT = "TrxCount";
    /**
     * Default constructor.
     */
    public BankTrxScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BankTrxScreenRecord(RecordOwner screen)
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

    public static final String BANK_TRX_SCREEN_RECORD_FILE = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new BankTrxScreenRecord_UserID(this, USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new BankAcctField(this, BANK_ACCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 2)
            field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 3)
            field = new BooleanField(this, DISPLAY_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new CurrencyField(this, START_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new CurrencyField(this, END_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new CurrencyField(this, CHANGE_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new IntegerField(this, TRX_COUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
