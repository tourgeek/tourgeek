/**
 * @(#)CostAnalysisScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report.tourcost;

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
import com.tourapp.tour.acctpay.report.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;

/**
 *  CostAnalysisScreenRecord - .
 */
public class CostAnalysisScreenRecord extends ApReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kTourID = kApReportScreenRecordLastField + 1;
    public static final int kTourTypeID = kTourID + 1;
    public static final int kSubTotalDepEstUSD = kTourTypeID + 1;
    public static final int kSubTotalInvoiceUSD = kSubTotalDepEstUSD + 1;
    public static final int kSubTotalPaymentsUSD = kSubTotalInvoiceUSD + 1;
    public static final int kSubTotalBalUSD = kSubTotalPaymentsUSD + 1;
    public static final int kTotalDepEstUSD = kSubTotalBalUSD + 1;
    public static final int kTotalInvoiceUSD = kTotalDepEstUSD + 1;
    public static final int kTotalPaymentsUSD = kTotalInvoiceUSD + 1;
    public static final int kCostAnalysisScreenRecordLastField = kTotalPaymentsUSD;
    public static final int kCostAnalysisScreenRecordFields = kTotalPaymentsUSD - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public CostAnalysisScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CostAnalysisScreenRecord(RecordOwner screen)
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

    public static final String kCostAnalysisScreenRecordFile = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kTourID)
            field = new TourField(this, "TourID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourTypeID)
            field = new TourTypeSelect(this, "TourTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSubTotalDepEstUSD)
            field = new CurrencyField(this, "SubTotalDepEstUSD", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSubTotalInvoiceUSD)
            field = new CurrencyField(this, "SubTotalInvoiceUSD", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSubTotalPaymentsUSD)
            field = new CurrencyField(this, "SubTotalPaymentsUSD", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSubTotalBalUSD)
            field = new CurrencyField(this, "SubTotalBalUSD", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalDepEstUSD)
            field = new CurrencyField(this, "TotalDepEstUSD", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalInvoiceUSD)
            field = new CurrencyField(this, "TotalInvoiceUSD", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalPaymentsUSD)
            field = new CurrencyField(this, "TotalPaymentsUSD", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCostAnalysisScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
