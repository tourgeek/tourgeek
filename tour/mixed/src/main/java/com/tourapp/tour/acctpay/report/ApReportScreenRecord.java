/**
 * @(#)ApReportScreenRecord.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report;

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
import org.jbundle.main.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.base.field.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  ApReportScreenRecord - .
 */
public class ApReportScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final String REPORT_DATE = REPORT_DATE;
    //public static final String REPORT_TIME = REPORT_TIME;
    //public static final String REPORT_USER_ID = REPORT_USER_ID;
    //public static final String REPORT_PAGE = REPORT_PAGE;
    //public static final String REPORT_COUNT = REPORT_COUNT;
    //public static final String REPORT_TOTAL = REPORT_TOTAL;
    //public static final String REPORT_KEY_AREA = REPORT_KEY_AREA;
    public static final String VENDOR_ID = "VendorID";
    public static final String CURRENCYS_ID = "CurrencysID";
    public static final String VOUCHERS = "Vouchers";
    public static final String DEP_ESTIMATES = "DepEstimates";
    public static final String OPEN_ITEMS = "OpenItems";
    public static final String SHOW_PAID = "ShowPaid";
    public static final String TOUR_ID = "TourID";
    public static final String TOUR_TYPE_ID = "TourTypeID";
    public static final String TOUR_HEADER_ID = "TourHeaderID";
    public static final String TOUR_CLASS_ID = "TourClassID";
    public static final String PRODUCT_CATEGORY_ID = "ProductCategoryID";
    public static final String TOUR_STATUS_ID = "TourStatusID";
    public static final String START_DEPARTURE = "StartDeparture";
    public static final String END_DEPARTURE = "EndDeparture";
    public static final String DETAIL = "Detail";
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String CUTOFF_DATE = "CutoffDate";
    public static final String PAYMENTS = "Payments";
    public static final String BALANCE = "Balance";
    public static final String TOTAL_ESTIMATE = "TotalEstimate";
    public static final String TOTAL_INVOICE = "TotalInvoice";
    public static final String TOTAL_BALANCE = "TotalBalance";
    public static final String TOTAL_USD_BAL = "TotalUSDBal";
    public static final String TOTAL_VENDORS = "TotalVendors";
    public static final String GRAND_USD_BAL = "GrandUSDBal";
    public static final String COUNT = "Count";
    public static final String TOTAL_COST = "TotalCost";
    public static final String TOTAL = "Total";
    public static final String EXCLUDE_AMOUNT = "ExcludeAmount";
    public static final String TEMPLATE = "template";
    public static final String TRUE_FIELD = "TrueField";
    /**
     * Default constructor.
     */
    public ApReportScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ApReportScreenRecord(RecordOwner screen)
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

    public static final String AP_REPORT_SCREEN_RECORD_FILE = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //  field = new ApReportScreenRecord_ReportDate(this, REPORT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 1)
        //  field = new ApReportScreenRecord_ReportTime(this, REPORT_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 2)
        //  field = new ApReportScreenRecord_ReportUserID(this, REPORT_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 3)
        //  field = new ShortField(this, REPORT_PAGE, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        //if (iFieldSeq == 4)
        //  field = new IntegerField(this, REPORT_COUNT, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        //if (iFieldSeq == 5)
        //  field = new CurrencyField(this, REPORT_TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        //if (iFieldSeq == 6)
        //  field = new IntegerField(this, REPORT_KEY_AREA, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new VendorField(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new CurrencysField(this, CURRENCYS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new BooleanField(this, VOUCHERS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
            field = new BooleanField(this, DEP_ESTIMATES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 11)
            field = new BooleanField(this, OPEN_ITEMS, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == 12)
            field = new BooleanField(this, SHOW_PAID, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == 13)
            field = new TourField(this, TOUR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 14)
            field = new TourTypeSelect(this, TOUR_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 15)
            field = new TourHeaderSelect(this, TOUR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
            field = new TourClassSelect(this, TOUR_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new ProductCategoryField(this, PRODUCT_CATEGORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 18)
            field = new TourStatusSelect(this, TOUR_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 19)
            field = new DateField(this, START_DEPARTURE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 20)
            field = new DateField(this, END_DEPARTURE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 21)
            field = new BooleanField(this, DETAIL, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == 22)
            field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 23)
            field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 24)
            field = new ApReportScreenRecord_CutoffDate(this, CUTOFF_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 25)
            field = new CurrencyField(this, PAYMENTS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 26)
            field = new CurrencyField(this, BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 27)
            field = new FullCurrencyField(this, TOTAL_ESTIMATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 28)
            field = new FullCurrencyField(this, TOTAL_INVOICE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 29)
            field = new FullCurrencyField(this, TOTAL_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 30)
            field = new CurrencyField(this, TOTAL_USD_BAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 31)
            field = new ShortField(this, TOTAL_VENDORS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 32)
            field = new CurrencyField(this, GRAND_USD_BAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 33)
            field = new ShortField(this, COUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 34)
            field = new CurrencyField(this, TOTAL_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 35)
            field = new CurrencyField(this, TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 36)
            field = new CurrencyField(this, EXCLUDE_AMOUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 37)
            field = new StringField(this, TEMPLATE, 60, null, null);
        if (iFieldSeq == 38)
            field = new BooleanField(this, TRUE_FIELD, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
