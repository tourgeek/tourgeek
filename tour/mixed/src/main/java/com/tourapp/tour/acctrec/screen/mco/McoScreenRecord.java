/**
 * @(#)McoScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.mco;

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
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.product.air.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.profile.db.*;

/**
 *  McoScreenRecord - Screen Fields for MCO reports.
 */
public class McoScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kNet = kScreenRecordLastField + 1;
    public static final int kAirlineID = kNet + 1;
    public static final int kServiceCharge = kAirlineID + 1;
    public static final int kReportDate = kServiceCharge + 1;
    public static final int kTotalGross = kReportDate + 1;
    public static final int kTotalNet = kTotalGross + 1;
    public static final int kCount = kTotalNet + 1;
    public static final int kFlag = kCount + 1;
    public static final int kToday = kFlag + 1;
    public static final int kBookingID = kToday + 1;
    public static final int kCardNo = kBookingID + 1;
    public static final int kCardID = kCardNo + 1;
    public static final int kCardFilterID = kCardID + 1;
    public static final int kNullDate = kCardFilterID + 1;
    public static final int kMcoScreenRecordLastField = kNullDate;
    public static final int kMcoScreenRecordFields = kNullDate - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public McoScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public McoScreenRecord(RecordOwner screen)
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

    public static final String kMcoScreenRecordFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kNet)
            field = new CurrencyField(this, "Net", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAirlineID)
            field = new AirlineField(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kServiceCharge)
            field = new PercentField(this, "ServiceCharge", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kReportDate)
            field = new McoScreenRecord_ReportDate(this, "ReportDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalGross)
            field = new CurrencyField(this, "TotalGross", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalNet)
            field = new CurrencyField(this, "TotalNet", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCount)
            field = new ShortField(this, "Count", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFlag)
            field = new BooleanField(this, "Flag", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kToday)
            field = new McoScreenRecord_Today(this, "Today", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBookingID)
            field = new BookingField(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCardNo)
            field = new StringField(this, "CardNo", 20, null, null);
        if (iFieldSeq == kCardID)
            field = new CardField(this, "CardID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCardFilterID)
            field = new CardFilter(this, "CardFilterID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNullDate)
            field = new DateField(this, "NullDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kMcoScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
