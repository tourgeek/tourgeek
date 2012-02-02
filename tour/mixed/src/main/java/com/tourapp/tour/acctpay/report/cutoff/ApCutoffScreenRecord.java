/**
 * @(#)ApCutoffScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report.cutoff;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctpay.report.*;
import com.tourapp.tour.genled.db.*;

/**
 *  ApCutoffScreenRecord - .
 */
public class ApCutoffScreenRecord extends ApReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kReportTime = kReportTime;
    //public static final int kReportUserID = kReportUserID;
    //public static final int kReportPage = kReportPage;
    //public static final int kReportDate = kReportDate;
    //public static final int kCurrencysID = kCurrencysID;
    //public static final int kCutoffDate = kCutoffDate;
    public static final String INV_CUTOFF_BALANCE = "InvCutoffBalance";
    public static final int kInvCutoffBalance = kApReportScreenRecordLastField + 1;
    public static final String CUTOFF_BALANCE_USD = "CutoffBalanceUSD";
    public static final int kCutoffBalanceUSD = kInvCutoffBalance + 1;
    public static final String VEN_EST_TOTAL = "VenEstTotal";
    public static final int kVenEstTotal = kCutoffBalanceUSD + 1;
    public static final String VEN_EST_USD_TOTAL = "VenEstUSDTotal";
    public static final int kVenEstUSDTotal = kVenEstTotal + 1;
    public static final String VEN_INV_TOTAL = "VenInvTotal";
    public static final int kVenInvTotal = kVenEstUSDTotal + 1;
    public static final String VEN_INV_USD_TOTAL = "VenInvUSDTotal";
    public static final int kVenInvUSDTotal = kVenInvTotal + 1;
    public static final String VEN_INV_BAL_TOTAL = "VenInvBalTotal";
    public static final int kVenInvBalTotal = kVenInvUSDTotal + 1;
    public static final String VEN_INV_BAL_USD_TOTAL = "VenInvBalUSDTotal";
    public static final int kVenInvBalUSDTotal = kVenInvBalTotal + 1;
    public static final String CURR_EST_TOTAL = "CurrEstTotal";
    public static final int kCurrEstTotal = kVenInvBalUSDTotal + 1;
    public static final String CURR_EST_USD_TOTAL = "CurrEstUSDTotal";
    public static final int kCurrEstUSDTotal = kCurrEstTotal + 1;
    public static final String CURR_INVOICE_TOTAL = "CurrInvoiceTotal";
    public static final int kCurrInvoiceTotal = kCurrEstUSDTotal + 1;
    public static final String CURR_INVOICE_USD_TOTAL = "CurrInvoiceUSDTotal";
    public static final int kCurrInvoiceUSDTotal = kCurrInvoiceTotal + 1;
    public static final String CURR_INV_BAL_TOTAL = "CurrInvBalTotal";
    public static final int kCurrInvBalTotal = kCurrInvoiceUSDTotal + 1;
    public static final String CURR_INV_BAL_USD_TOTAL = "CurrInvBalUSDTotal";
    public static final int kCurrInvBalUSDTotal = kCurrInvBalTotal + 1;
    public static final String EST_USD_TOTAL = "EstUSDTotal";
    public static final int kEstUSDTotal = kCurrInvBalUSDTotal + 1;
    public static final String INV_USD_TOTAL = "InvUSDTotal";
    public static final int kInvUSDTotal = kEstUSDTotal + 1;
    public static final String INV_BAL_USD_TOTAL = "InvBalUSDTotal";
    public static final int kInvBalUSDTotal = kInvUSDTotal + 1;
    public static final int kApCutoffScreenRecordLastField = kInvBalUSDTotal;
    public static final int kApCutoffScreenRecordFields = kInvBalUSDTotal - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public ApCutoffScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ApCutoffScreenRecord(RecordOwner screen)
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

    public static final String kApCutoffScreenRecordFile = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kReportTime)
        //  field = new ApCutoffScreenRecord_ReportTime(this, "ReportTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportUserID)
        //  field = new ApCutoffScreenRecord_ReportUserID(this, "ReportUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportPage)
        //  field = new ShortField(this, "ReportPage", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        //if (iFieldSeq == kReportDate)
        //  field = new ApCutoffScreenRecord_ReportDate(this, "ReportDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kCurrencysID)
        //  field = new CurrencysField(this, "CurrencysID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kCutoffDate)
        //  field = new ApCutoffScreenRecord_CutoffDate(this, "CutoffDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInvCutoffBalance)
            field = new FullCurrencyField(this, "InvCutoffBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCutoffBalanceUSD)
            field = new CurrencyField(this, "CutoffBalanceUSD", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVenEstTotal)
            field = new CurrencyField(this, "VenEstTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVenEstUSDTotal)
            field = new CurrencyField(this, "VenEstUSDTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVenInvTotal)
            field = new CurrencyField(this, "VenInvTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVenInvUSDTotal)
            field = new CurrencyField(this, "VenInvUSDTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVenInvBalTotal)
            field = new CurrencyField(this, "VenInvBalTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVenInvBalUSDTotal)
            field = new CurrencyField(this, "VenInvBalUSDTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrEstTotal)
            field = new CurrencyField(this, "CurrEstTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrEstUSDTotal)
            field = new CurrencyField(this, "CurrEstUSDTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrInvoiceTotal)
            field = new CurrencyField(this, "CurrInvoiceTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrInvoiceUSDTotal)
            field = new CurrencyField(this, "CurrInvoiceUSDTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrInvBalTotal)
            field = new CurrencyField(this, "CurrInvBalTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrInvBalUSDTotal)
            field = new CurrencyField(this, "CurrInvBalUSDTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEstUSDTotal)
            field = new CurrencyField(this, "EstUSDTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInvUSDTotal)
            field = new CurrencyField(this, "InvUSDTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInvBalUSDTotal)
            field = new CurrencyField(this, "InvBalUSDTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kApCutoffScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
