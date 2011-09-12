/**
 * @(#)ApReportScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.main.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  ApReportScreenRecord - .
 */
public class ApReportScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kReportDate = kReportDate;
    //public static final int kReportTime = kReportTime;
    //public static final int kReportUserID = kReportUserID;
    //public static final int kReportPage = kReportPage;
    //public static final int kReportCount = kReportCount;
    public static final int kVendorID = kReportScreenRecordLastField + 1;
    public static final int kCurrencysID = kVendorID + 1;
    public static final int kVouchers = kCurrencysID + 1;
    public static final int kDepEstimates = kVouchers + 1;
    public static final int kOpenItems = kDepEstimates + 1;
    public static final int kShowPaid = kOpenItems + 1;
    public static final int kTourID = kShowPaid + 1;
    public static final int kTourTypeID = kTourID + 1;
    public static final int kTourHeaderID = kTourTypeID + 1;
    public static final int kTourClassID = kTourHeaderID + 1;
    public static final int kProductCategoryID = kTourClassID + 1;
    public static final int kTourStatusID = kProductCategoryID + 1;
    public static final int kStartDeparture = kTourStatusID + 1;
    public static final int kEndDeparture = kStartDeparture + 1;
    public static final int kDetail = kEndDeparture + 1;
    public static final int kStartDate = kDetail + 1;
    public static final int kEndDate = kStartDate + 1;
    public static final int kCutoffDate = kEndDate + 1;
    public static final int kPayments = kCutoffDate + 1;
    public static final int kBalance = kPayments + 1;
    public static final int kTotalEstimate = kBalance + 1;
    public static final int kTotalInvoice = kTotalEstimate + 1;
    public static final int kTotalBalance = kTotalInvoice + 1;
    public static final int kTotalUSDBal = kTotalBalance + 1;
    public static final int kTotalVendors = kTotalUSDBal + 1;
    public static final int kGrandUSDBal = kTotalVendors + 1;
    public static final int kCount = kGrandUSDBal + 1;
    public static final int kTotalCost = kCount + 1;
    public static final int kTotal = kTotalCost + 1;
    public static final int kExcludeAmount = kTotal + 1;
    public static final int ktemplate = kExcludeAmount + 1;
    public static final int kTrueField = ktemplate + 1;
    public static final int kApReportScreenRecordLastField = kTrueField;
    public static final int kApReportScreenRecordFields = kTrueField - DBConstants.MAIN_FIELD + 1;
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

    public static final String kApReportScreenRecordFile = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kReportDate)
        //  field = new ApReportScreenRecord_ReportDate(this, "ReportDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportTime)
        //  field = new ApReportScreenRecord_ReportTime(this, "ReportTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportUserID)
        //  field = new ApReportScreenRecord_ReportUserID(this, "ReportUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportPage)
        //  field = new ShortField(this, "ReportPage", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        //if (iFieldSeq == kReportCount)
        //  field = new IntegerField(this, "ReportCount", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        if (iFieldSeq == kVendorID)
            field = new VendorField(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrencysID)
            field = new CurrencysField(this, "CurrencysID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVouchers)
            field = new BooleanField(this, "Vouchers", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDepEstimates)
            field = new BooleanField(this, "DepEstimates", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kOpenItems)
            field = new BooleanField(this, "OpenItems", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == kShowPaid)
            field = new BooleanField(this, "ShowPaid", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == kTourID)
            field = new TourField(this, "TourID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourTypeID)
            field = new TourTypeSelect(this, "TourTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourHeaderID)
            field = new TourHeaderSelect(this, "TourHeaderID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourClassID)
            field = new TourClassSelect(this, "TourClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductCategoryID)
            field = new ProductCategoryField(this, "ProductCategoryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourStatusID)
            field = new TourStatusSelect(this, "TourStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartDeparture)
            field = new DateField(this, "StartDeparture", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndDeparture)
            field = new DateField(this, "EndDeparture", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDetail)
            field = new BooleanField(this, "Detail", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == kStartDate)
            field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndDate)
            field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCutoffDate)
            field = new ApReportScreenRecord_CutoffDate(this, "CutoffDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPayments)
            field = new CurrencyField(this, "Payments", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBalance)
            field = new CurrencyField(this, "Balance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalEstimate)
            field = new FullCurrencyField(this, "TotalEstimate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalInvoice)
            field = new FullCurrencyField(this, "TotalInvoice", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalBalance)
            field = new FullCurrencyField(this, "TotalBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalUSDBal)
            field = new CurrencyField(this, "TotalUSDBal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalVendors)
            field = new ShortField(this, "TotalVendors", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kGrandUSDBal)
            field = new CurrencyField(this, "GrandUSDBal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCount)
            field = new ShortField(this, "Count", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalCost)
            field = new CurrencyField(this, "TotalCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotal)
            field = new CurrencyField(this, "Total", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kExcludeAmount)
            field = new CurrencyField(this, "ExcludeAmount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == ktemplate)
            field = new StringField(this, "template", 60, null, null);
        if (iFieldSeq == kTrueField)
            field = new BooleanField(this, "TrueField", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kApReportScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
