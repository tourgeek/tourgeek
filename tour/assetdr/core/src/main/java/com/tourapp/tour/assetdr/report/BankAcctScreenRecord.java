/**
 * @(#)BankAcctScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.report;

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
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.assetdr.db.*;
import org.jbundle.main.db.*;

/**
 *  BankAcctScreenRecord - Bank Account Report Fields.
 */
public class BankAcctScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kReportDate = kReportDate;
    //public static final int kReportTime = kReportTime;
    //public static final int kReportUserID = kReportUserID;
    //public static final int kReportPage = kReportPage;
    public static final String START_DATE = "StartDate";
    public static final int kStartDate = kReportScreenRecordLastField + 1;
    public static final String END_DATE = "EndDate";
    public static final int kEndDate = kStartDate + 1;
    public static final String BANK_ACCT_ID = "BankAcctID";
    public static final int kBankAcctID = kEndDate + 1;
    public static final String COUNT = "Count";
    public static final int kCount = kBankAcctID + 1;
    public static final String BALANCE = "Balance";
    public static final int kBalance = kCount + 1;
    public static final String START_BALANCE = "StartBalance";
    public static final int kStartBalance = kBalance + 1;
    public static final String END_BALANCE = "EndBalance";
    public static final int kEndBalance = kStartBalance + 1;
    public static final int kBankAcctScreenRecordLastField = kEndBalance;
    public static final int kBankAcctScreenRecordFields = kEndBalance - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public BankAcctScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BankAcctScreenRecord(RecordOwner screen)
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

    public static final String kBankAcctScreenRecordFile = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kReportDate)
        //  field = new BankAcctScreenRecord_ReportDate(this, "ReportDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportTime)
        //  field = new BankAcctScreenRecord_ReportTime(this, "ReportTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kReportUserID)
            field = new BankAcctScreenRecord_ReportUserID(this, "ReportUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kReportPage)
            field = new ShortField(this, "ReportPage", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        if (iFieldSeq == kStartDate)
        {
            field = new BankAcctScreenRecord_StartDate(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kEndDate)
        {
            field = new BankAcctScreenRecord_EndDate(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kBankAcctID)
            field = new BankAcctField(this, "BankAcctID", 2, null, null);
        if (iFieldSeq == kCount)
            field = new IntegerField(this, "Count", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBalance)
            field = new CurrencyField(this, "Balance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartBalance)
            field = new CurrencyField(this, "StartBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndBalance)
            field = new CurrencyField(this, "EndBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBankAcctScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
