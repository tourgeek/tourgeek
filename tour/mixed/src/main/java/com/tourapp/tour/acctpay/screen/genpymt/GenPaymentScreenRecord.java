/**
 *  @(#)GenPaymentScreenRecord.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.assetdr.db.*;

/**
 *  GenPaymentScreenRecord - Screen fields.
 */
public class GenPaymentScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kPaymentCodeID = kScreenRecordLastField + 1;
    public static final int kUseCurrentSelection = kPaymentCodeID + 1;
    public static final int kBankAcctID = kUseCurrentSelection + 1;
    public static final int kReportDate = kBankAcctID + 1;
    public static final int kReportTime = kReportDate + 1;
    public static final int kUserID = kReportTime + 1;
    public static final int kPage = kUserID + 1;
    public static final int kTotal = kPage + 1;
    public static final int kGenPaymentScreenRecordLastField = kTotal;
    public static final int kGenPaymentScreenRecordFields = kTotal - DBConstants.MAIN_FIELD + 1;
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

    public static final String kGenPaymentScreenRecordFile = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kPaymentCodeID)
            field = new PaymentCodeField(this, "PaymentCodeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kUseCurrentSelection)
            field = new BooleanField(this, "UseCurrentSelection", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == kBankAcctID)
            field = new BankAcctField(this, "BankAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kReportDate)
            field = new GenPaymentScreenRecord_ReportDate(this, "ReportDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kReportTime)
            field = new GenPaymentScreenRecord_ReportTime(this, "ReportTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kUserID)
            field = new GenPaymentScreenRecord_UserID(this, "UserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPage)
            field = new ShortField(this, "Page", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotal)
            field = new CurrencyField(this, "Total", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kGenPaymentScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
