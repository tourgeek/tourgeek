/**
  * @(#)GenPaymentScreenRecord.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctpay.screen.genpymt;

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
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.assetdr.db.*;
import org.jbundle.base.screen.model.util.*;

/**
 *  GenPaymentScreenRecord - Screen fields.
 */
public class GenPaymentScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String PAYMENT_CODE_ID = "PaymentCodeID";
    public static final String USE_CURRENT_SELECTION = "UseCurrentSelection";
    public static final String BANK_ACCT_ID = "BankAcctID";
    public static final String REPORT_DATE = "ReportDate";
    public static final String REPORT_TIME = "ReportTime";
    public static final String USER_ID = "UserID";
    public static final String PAGE = "Page";
    public static final String TOTAL = "Total";
    /**
     * Default constructor.
     */
    public GenPaymentScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public GenPaymentScreenRecord(RecordOwner screen)
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

    public static final String GEN_PAYMENT_SCREEN_RECORD_FILE = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new PaymentCodeField(this, PAYMENT_CODE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new BooleanField(this, USE_CURRENT_SELECTION, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == 2)
            field = new BankAcctField(this, BANK_ACCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 3)
            field = new GenPaymentScreenRecord_ReportDate(this, REPORT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new GenPaymentScreenRecord_ReportTime(this, REPORT_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new GenPaymentScreenRecord_UserID(this, USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new ShortField(this, PAGE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new CurrencyField(this, TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
