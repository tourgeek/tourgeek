/**
 * @(#)BankTrxScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
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

    public static final int kUserID = kScreenRecordLastField + 1;
    public static final int kBankAcctID = kUserID + 1;
    public static final int kStartDate = kBankAcctID + 1;
    public static final int kDisplayBalance = kStartDate + 1;
    public static final int kStartBalance = kDisplayBalance + 1;
    public static final int kEndBalance = kStartBalance + 1;
    public static final int kChangeBalance = kEndBalance + 1;
    public static final int kTrxCount = kChangeBalance + 1;
    public static final int kBankTrxScreenRecordLastField = kTrxCount;
    public static final int kBankTrxScreenRecordFields = kTrxCount - DBConstants.MAIN_FIELD + 1;
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

    public static final String kBankTrxScreenRecordFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kUserID)
            field = new BankTrxScreenRecord_UserID(this, "UserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBankAcctID)
            field = new BankAcctField(this, "BankAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartDate)
            field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDisplayBalance)
            field = new BooleanField(this, "DisplayBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartBalance)
            field = new CurrencyField(this, "StartBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndBalance)
            field = new CurrencyField(this, "EndBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kChangeBalance)
            field = new CurrencyField(this, "ChangeBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxCount)
            field = new IntegerField(this, "TrxCount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBankTrxScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
