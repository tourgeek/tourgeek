/**
 * @(#)OverrideScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.air.oride;

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
import org.jbundle.main.db.*;
import com.tourapp.tour.product.air.db.*;

/**
 *  OverrideScreenRecord - .
 */
public class OverrideScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kReportDate = kReportDate;
    //public static final int kReportTime = kReportTime;
    //public static final int kReportUserID = kReportUserID;
    //public static final int kReportPage = kReportPage;
    public static final String COUNT = "Count";
    public static final int kCount = kReportScreenRecordLastField + 1;
    public static final String AIRLINE_ID = "AirlineID";
    public static final int kAirlineID = kCount + 1;
    public static final String VENDOR_ID = "VendorID";
    public static final int kVendorID = kAirlineID + 1;
    public static final String TOTAL = "Total";
    public static final int kTotal = kVendorID + 1;
    public static final String START_DEPARTURE = "StartDeparture";
    public static final int kStartDeparture = kTotal + 1;
    public static final String END_DEPARTURE = "EndDeparture";
    public static final int kEndDeparture = kStartDeparture + 1;
    public static final String INCLUDE_PAID = "IncludePaid";
    public static final int kIncludePaid = kEndDeparture + 1;
    public static final String OVERRIDE_PAID_DATE = "OverridePaidDate";
    public static final int kOverridePaidDate = kIncludePaid + 1;
    public static final int kOverrideScreenRecordLastField = kOverridePaidDate;
    public static final int kOverrideScreenRecordFields = kOverridePaidDate - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public OverrideScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public OverrideScreenRecord(RecordOwner screen)
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

    public static final String kOverrideScreenRecordFile = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kReportDate)
        //  field = new OverrideScreenRecord_ReportDate(this, "ReportDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportTime)
        //  field = new OverrideScreenRecord_ReportTime(this, "ReportTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportUserID)
        //  field = new OverrideScreenRecord_ReportUserID(this, "ReportUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportPage)
        //  field = new ShortField(this, "ReportPage", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        if (iFieldSeq == kCount)
            field = new ShortField(this, "Count", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAirlineID)
            field = new AirlineField(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVendorID)
            field = new VendorField(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotal)
            field = new CurrencyField(this, "Total", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartDeparture)
            field = new DateField(this, "StartDeparture", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndDeparture)
            field = new DateField(this, "EndDeparture", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kIncludePaid)
            field = new BooleanField(this, "IncludePaid", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kOverridePaidDate)
            field = new DateField(this, "OverridePaidDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kOverrideScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
