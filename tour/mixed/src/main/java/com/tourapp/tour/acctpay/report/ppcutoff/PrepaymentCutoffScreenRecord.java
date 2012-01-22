/**
 * @(#)PrepaymentCutoffScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report.ppcutoff;

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
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctpay.report.cutoff.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.report.*;

/**
 *  PrepaymentCutoffScreenRecord - .
 */
public class PrepaymentCutoffScreenRecord extends ApReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kCurrencysID = kCurrencysID;
    //public static final int kCutoffDate = kCutoffDate;
    //public static final int kReportDate = kReportDate;
    //public static final int kReportTime = kReportTime;
    //public static final int kReportUserID = kReportUserID;
    //public static final int kReportPage = kReportPage;
    public static final int kVendorTotal = kApReportScreenRecordLastField + 1;
    public static final int kVendorTotalUSD = kVendorTotal + 1;
    public static final int kVendorBalanceTotal = kVendorTotalUSD + 1;
    public static final int kVendorBalanceTotalUSD = kVendorBalanceTotal + 1;
    public static final int kCurrTotal = kVendorBalanceTotalUSD + 1;
    public static final int kCurrTotalUSD = kCurrTotal + 1;
    public static final int kCurrBalanceTotal = kCurrTotalUSD + 1;
    public static final int kCurrBalanceTotalUSD = kCurrBalanceTotal + 1;
    public static final int kTotalUSD = kCurrBalanceTotalUSD + 1;
    public static final int kBalanceTotalUSD = kTotalUSD + 1;
    public static final int kPrepaymentCutoffScreenRecordLastField = kBalanceTotalUSD;
    public static final int kPrepaymentCutoffScreenRecordFields = kBalanceTotalUSD - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public PrepaymentCutoffScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public PrepaymentCutoffScreenRecord(RecordOwner screen)
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

    public static final String kPrepaymentCutoffScreenRecordFile = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kCurrencysID)
        //  field = new CurrencysField(this, "CurrencysID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kCutoffDate)
        //  field = new PrepaymentCutoffScreenRecord_CutoffDate(this, "CutoffDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportDate)
        //  field = new PrepaymentCutoffScreenRecord_ReportDate(this, "ReportDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportTime)
        //  field = new PrepaymentCutoffScreenRecord_ReportTime(this, "ReportTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportUserID)
        //  field = new PrepaymentCutoffScreenRecord_ReportUserI(this, "ReportUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportPage)
        //  field = new ShortField(this, "ReportPage", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        if (iFieldSeq == kVendorTotal)
            field = new CurrencyField(this, "VendorTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVendorTotalUSD)
            field = new CurrencyField(this, "VendorTotalUSD", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVendorBalanceTotal)
            field = new CurrencyField(this, "VendorBalanceTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVendorBalanceTotalUSD)
            field = new CurrencyField(this, "VendorBalanceTotalUSD", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrTotal)
            field = new CurrencyField(this, "CurrTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrTotalUSD)
            field = new CurrencyField(this, "CurrTotalUSD", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrBalanceTotal)
            field = new CurrencyField(this, "CurrBalanceTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrBalanceTotalUSD)
            field = new CurrencyField(this, "CurrBalanceTotalUSD", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalUSD)
            field = new CurrencyField(this, "TotalUSD", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBalanceTotalUSD)
            field = new CurrencyField(this, "BalanceTotalUSD", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kPrepaymentCutoffScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
