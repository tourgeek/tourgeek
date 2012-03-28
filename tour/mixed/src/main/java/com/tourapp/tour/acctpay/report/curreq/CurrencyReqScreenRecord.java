/**
 * @(#)CurrencyReqScreenRecord.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.base.db.event.*;
import com.tourapp.tour.acctpay.report.*;
import org.jbundle.base.screen.model.util.*;

/**
 *  CurrencyReqScreenRecord - .
 */
public class CurrencyReqScreenRecord extends ApReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final String REPORT_DATE = REPORT_DATE;
    //public static final String REPORT_TIME = REPORT_TIME;
    //public static final String REPORT_USER_ID = REPORT_USER_ID;
    //public static final String REPORT_PAGE = REPORT_PAGE;
    //public static final String REPORT_COUNT = REPORT_COUNT;
    //public static final String REPORT_TOTAL = REPORT_TOTAL;
    //public static final String REPORT_KEY_AREA = REPORT_KEY_AREA;
    //public static final String VENDOR_ID = VENDOR_ID;
    //public static final String CURRENCYS_ID = CURRENCYS_ID;
    //public static final String VOUCHERS = VOUCHERS;
    //public static final String DEP_ESTIMATES = DEP_ESTIMATES;
    //public static final String OPEN_ITEMS = OPEN_ITEMS;
    //public static final String SHOW_PAID = SHOW_PAID;
    //public static final String TOUR_ID = TOUR_ID;
    //public static final String TOUR_TYPE_ID = TOUR_TYPE_ID;
    //public static final String TOUR_HEADER_ID = TOUR_HEADER_ID;
    //public static final String TOUR_CLASS_ID = TOUR_CLASS_ID;
    //public static final String PRODUCT_CATEGORY_ID = PRODUCT_CATEGORY_ID;
    //public static final String TOUR_STATUS_ID = TOUR_STATUS_ID;
    //public static final String START_DEPARTURE = START_DEPARTURE;
    //public static final String END_DEPARTURE = END_DEPARTURE;
    //public static final String DETAIL = DETAIL;
    //public static final String START_DATE = START_DATE;
    //public static final String END_DATE = END_DATE;
    //public static final String CUTOFF_DATE = CUTOFF_DATE;
    //public static final String PAYMENTS = PAYMENTS;
    //public static final String BALANCE = BALANCE;
    //public static final String TOTAL_ESTIMATE = TOTAL_ESTIMATE;
    //public static final String TOTAL_INVOICE = TOTAL_INVOICE;
    //public static final String TOTAL_BALANCE = TOTAL_BALANCE;
    //public static final String TOTAL_USD_BAL = TOTAL_USD_BAL;
    //public static final String TOTAL_VENDORS = TOTAL_VENDORS;
    //public static final String GRAND_USD_BAL = GRAND_USD_BAL;
    //public static final String COUNT = COUNT;
    //public static final String TOTAL_COST = TOTAL_COST;
    //public static final String TOTAL = TOTAL;
    //public static final String EXCLUDE_AMOUNT = EXCLUDE_AMOUNT;
    //public static final String TEMPLATE = TEMPLATE;
    //public static final String TRUE_FIELD = TRUE_FIELD;
    public static final String PERIOD_TYPE = "PeriodType";
    public static final String PERIOD_LENGTH = "PeriodLength";
    public static final String DEPARTURE_TOTAL = "DepartureTotal";
    public static final String BALANCE_TOTAL = "BalanceTotal";
    public static final String TOTAL_TOTAL = "TotalTotal";
    public static final String TOTAL_USD = "TotalUSD";
    public static final String USD_TOTAL = "USDTotal";
    public static final String DISPLAY_ACTIVE = "DisplayActive";
    public static final String SUMMARY_CURRENCY_DESC = "SummaryCurrencyDesc";
    public static final String FROM_DATE = "FromDate";
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

    public static final String CURRENCY_REQ_SCREEN_RECORD_FILE = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //  field = new CurrencyReqScreenRecord_ReportDate(this, REPORT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 1)
        //  field = new CurrencyReqScreenRecord_ReportTime(this, REPORT_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 2)
        //  field = new CurrencyReqScreenRecord_ReportUserID(this, REPORT_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 3)
        //  field = new ShortField(this, REPORT_PAGE, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        //if (iFieldSeq == 4)
        //  field = new IntegerField(this, REPORT_COUNT, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        //if (iFieldSeq == 5)
        //  field = new CurrencyField(this, REPORT_TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        //if (iFieldSeq == 6)
        //  field = new IntegerField(this, REPORT_KEY_AREA, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 7)
        //  field = new VendorField(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 8)
        //  field = new CurrencysField(this, CURRENCYS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new BooleanField(this, VOUCHERS, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == 10)
            field = new BooleanField(this, DEP_ESTIMATES, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == 11)
            field = new BooleanField(this, OPEN_ITEMS, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == 12)
            field = new BooleanField(this, SHOW_PAID, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //if (iFieldSeq == 13)
        //  field = new TourField(this, TOUR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 14)
        //  field = new TourTypeSelect(this, TOUR_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 15)
        //  field = new TourHeaderSelect(this, TOUR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 16)
        //  field = new TourClassSelect(this, TOUR_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 17)
        //  field = new ProductCategoryField(this, PRODUCT_CATEGORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 18)
        //  field = new TourStatusSelect(this, TOUR_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 19)
        //  field = new DateField(this, START_DEPARTURE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 20)
        //  field = new DateField(this, END_DEPARTURE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 21)
            field = new BooleanField(this, DETAIL, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == 22)
            field = new CurrencyReqScreenRecord_StartDate(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 23)
        //  field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 24)
        //  field = new CurrencyReqScreenRecord_CutoffDate(this, CUTOFF_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 25)
        //  field = new CurrencyField(this, PAYMENTS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 26)
        //  field = new CurrencyField(this, BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 27)
        //  field = new FullCurrencyField(this, TOTAL_ESTIMATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 28)
        //  field = new FullCurrencyField(this, TOTAL_INVOICE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 29)
        //  field = new FullCurrencyField(this, TOTAL_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 30)
        //  field = new CurrencyField(this, TOTAL_USD_BAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 31)
        //  field = new ShortField(this, TOTAL_VENDORS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 32)
        //  field = new CurrencyField(this, GRAND_USD_BAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 33)
        //  field = new ShortField(this, COUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 34)
        //  field = new CurrencyField(this, TOTAL_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 35)
        //  field = new CurrencyField(this, TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 36)
        //  field = new CurrencyField(this, EXCLUDE_AMOUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 37)
        //  field = new StringField(this, TEMPLATE, 60, null, null);
        //if (iFieldSeq == 38)
        //  field = new BooleanField(this, TRUE_FIELD, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == 39)
            field = new PeriodTypeField(this, PERIOD_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, "PeriodTypeField.WEEKLY");
        if (iFieldSeq == 40)
            field = new ShortField(this, PERIOD_LENGTH, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        if (iFieldSeq == 41)
            field = new CurrencyField(this, DEPARTURE_TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 42)
            field = new CurrencyField(this, BALANCE_TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 43)
            field = new CurrencyField(this, TOTAL_TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 44)
            field = new CurrencyField(this, TOTAL_USD, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 45)
            field = new CurrencyField(this, USD_TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 46)
            field = new BooleanField(this, DISPLAY_ACTIVE, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == 47)
            field = new StringField(this, SUMMARY_CURRENCY_DESC, 30, null, null);
        if (iFieldSeq == 48)
            field = new DateField(this, FROM_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
