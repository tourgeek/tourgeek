/**
 *  @(#)CurrencyReqScreenRecord.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report.curreq;

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
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.base.db.event.*;
import com.tourapp.tour.acctpay.report.*;

/**
 *  CurrencyReqScreenRecord - .
 */
public class CurrencyReqScreenRecord extends ApReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kReportDate = kReportDate;
    //public static final int kReportTime = kReportTime;
    //public static final int kReportUserID = kReportUserID;
    //public static final int kReportPage = kReportPage;
    //public static final int kCurrencysID = kCurrencysID;
    //public static final int kStartDate = kStartDate;
    //public static final int kEndDate = kEndDate;
    //public static final int kDepEstimates = kDepEstimates;
    //public static final int kOpenItems = kOpenItems;
    //public static final int kShowPaid = kShowPaid;
    //public static final int kVouchers = kVouchers;
    //public static final int kDetail = kDetail;
    public static final int kPeriodType = kApReportScreenRecordLastField + 1;
    public static final int kPeriodLength = kPeriodType + 1;
    public static final int kDepartureTotal = kPeriodLength + 1;
    public static final int kBalanceTotal = kDepartureTotal + 1;
    public static final int kTotalTotal = kBalanceTotal + 1;
    public static final int kTotalUSD = kTotalTotal + 1;
    public static final int kUSDTotal = kTotalUSD + 1;
    public static final int kDisplayActive = kUSDTotal + 1;
    public static final int kSummaryCurrencyDesc = kDisplayActive + 1;
    public static final int kFromDate = kSummaryCurrencyDesc + 1;
    public static final int kCurrencyReqScreenRecordLastField = kFromDate;
    public static final int kCurrencyReqScreenRecordFields = kFromDate - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public CurrencyReqScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CurrencyReqScreenRecord(RecordOwner screen)
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

    public static final String kCurrencyReqScreenRecordFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kReportDate)
        //  field = new CurrencyReqScreenRecord_ReportDate(this, "ReportDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportTime)
        //  field = new CurrencyReqScreenRecord_ReportTime(this, "ReportTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportUserID)
        //  field = new CurrencyReqScreenRecord_ReportUserID(this, "ReportUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportPage)
        //  field = new ShortField(this, "ReportPage", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        //if (iFieldSeq == kCurrencysID)
        //  field = new CurrencysField(this, "CurrencysID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartDate)
            field = new CurrencyReqScreenRecord_StartDate(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kEndDate)
        //  field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDepEstimates)
            field = new BooleanField(this, "DepEstimates", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == kOpenItems)
            field = new BooleanField(this, "OpenItems", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == kShowPaid)
            field = new BooleanField(this, "ShowPaid", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == kVouchers)
            field = new BooleanField(this, "Vouchers", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == kPeriodType)
            field = new PeriodTypeField(this, "PeriodType", Constants.DEFAULT_FIELD_LENGTH, null, "PeriodTypeField.WEEKLY");
        if (iFieldSeq == kPeriodLength)
            field = new ShortField(this, "PeriodLength", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        if (iFieldSeq == kDetail)
            field = new BooleanField(this, "Detail", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == kDepartureTotal)
            field = new CurrencyField(this, "DepartureTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBalanceTotal)
            field = new CurrencyField(this, "BalanceTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalTotal)
            field = new CurrencyField(this, "TotalTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalUSD)
            field = new CurrencyField(this, "TotalUSD", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kUSDTotal)
            field = new CurrencyField(this, "USDTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDisplayActive)
            field = new BooleanField(this, "DisplayActive", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == kSummaryCurrencyDesc)
            field = new StringField(this, "SummaryCurrencyDesc", 30, null, null);
        if (iFieldSeq == kFromDate)
            field = new DateField(this, "FromDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCurrencyReqScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
