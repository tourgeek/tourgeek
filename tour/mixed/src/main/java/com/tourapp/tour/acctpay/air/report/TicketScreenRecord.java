/**
 * @(#)TicketScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.air.report;

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
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.main.db.*;
import com.tourapp.tour.product.air.db.*;

/**
 *  TicketScreenRecord - .
 */
public class TicketScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kReportDate = kReportDate;
    //public static final int kReportTime = kReportTime;
    //public static final int kReportUserID = kReportUserID;
    //public static final int kReportPage = kReportPage;
    public static final String COUNT = "Count";
    public static final int kCount = kReportScreenRecordLastField + 1;
    public static final String TOTAL = "Total";
    public static final int kTotal = kCount + 1;
    public static final String REPORT_ORDER = "ReportOrder";
    public static final int kReportOrder = kTotal + 1;
    public static final String START_DEPARTURE = "StartDeparture";
    public static final int kStartDeparture = kReportOrder + 1;
    public static final String END_DEPARTURE = "EndDeparture";
    public static final int kEndDeparture = kStartDeparture + 1;
    public static final String START_ISSUE = "StartIssue";
    public static final int kStartIssue = kEndDeparture + 1;
    public static final String END_ISSUE = "EndIssue";
    public static final int kEndIssue = kStartIssue + 1;
    public static final String INCLUDE_VOID = "IncludeVoid";
    public static final int kIncludeVoid = kEndIssue + 1;
    public static final String AIRLINE_1ID = "Airline1ID";
    public static final int kAirline1ID = kIncludeVoid + 1;
    public static final String AIRLINE_2ID = "Airline2ID";
    public static final int kAirline2ID = kAirline1ID + 1;
    public static final String AIRLINE_3ID = "Airline3ID";
    public static final int kAirline3ID = kAirline2ID + 1;
    public static final String AIRLINE_4ID = "Airline4ID";
    public static final int kAirline4ID = kAirline3ID + 1;
    public static final String START_TICKET = "StartTicket";
    public static final int kStartTicket = kAirline4ID + 1;
    public static final String END_TICKET = "EndTicket";
    public static final int kEndTicket = kStartTicket + 1;
    public static final int kTicketScreenRecordLastField = kEndTicket;
    public static final int kTicketScreenRecordFields = kEndTicket - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public TicketScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TicketScreenRecord(RecordOwner screen)
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

    public static final String kTicketScreenRecordFile = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kReportDate)
        //  field = new TicketScreenRecord_ReportDate(this, "ReportDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportTime)
        //  field = new TicketScreenRecord_ReportTime(this, "ReportTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportUserID)
        //  field = new TicketScreenRecord_ReportUserID(this, "ReportUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportPage)
        //  field = new ShortField(this, "ReportPage", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        if (iFieldSeq == kCount)
            field = new ShortField(this, "Count", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotal)
            field = new CurrencyField(this, "Total", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kReportOrder)
            field = new TicketReportOrderField(this, "ReportOrder", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartDeparture)
            field = new DateField(this, "StartDeparture", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndDeparture)
            field = new DateField(this, "EndDeparture", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartIssue)
            field = new DateField(this, "StartIssue", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndIssue)
            field = new DateField(this, "EndIssue", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kIncludeVoid)
            field = new BooleanField(this, "IncludeVoid", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAirline1ID)
            field = new AirlineField(this, "Airline1ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAirline2ID)
            field = new AirlineField(this, "Airline2ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAirline3ID)
            field = new AirlineField(this, "Airline3ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAirline4ID)
            field = new AirlineField(this, "Airline4ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartTicket)
            field = new StringField(this, "StartTicket", 20, null, null);
        if (iFieldSeq == kEndTicket)
            field = new StringField(this, "EndTicket", 20, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTicketScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
