/**
  * @(#)McoScreenRecord.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctrec.screen.mco;

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
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.genled.db.*;
import org.jbundle.base.screen.model.util.*;
import com.tourapp.tour.base.field.*;
import com.tourapp.tour.profile.db.*;

/**
 *  McoScreenRecord - Screen Fields for MCO reports.
 */
public class McoScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String NET = "Net";
    public static final String AIRLINE_ID = "AirlineID";
    public static final String SERVICE_CHARGE = "ServiceCharge";
    public static final String REPORT_DATE = "ReportDate";
    public static final String TOTAL_GROSS = "TotalGross";
    public static final String TOTAL_NET = "TotalNet";
    public static final String COUNT = "Count";
    public static final String FLAG = "Flag";
    public static final String TODAY = "Today";
    public static final String BOOKING_ID = "BookingID";
    public static final String CARD_NO = "CardNo";
    public static final String CARD_ID = "CardID";
    public static final String CARD_FILTER_ID = "CardFilterID";
    public static final String NULL_DATE = "NullDate";
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

    public static final String MCO_SCREEN_RECORD_FILE = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new CurrencyField(this, NET, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new AirlineField(this, AIRLINE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 2)
            field = new PercentField(this, SERVICE_CHARGE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 3)
            field = new McoScreenRecord_ReportDate(this, REPORT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new CurrencyField(this, TOTAL_GROSS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new CurrencyField(this, TOTAL_NET, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new ShortField(this, COUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new BooleanField(this, FLAG, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new McoScreenRecord_Today(this, TODAY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new BookingField(this, BOOKING_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
            field = new StringField(this, CARD_NO, 20, null, null);
        if (iFieldSeq == 11)
            field = new CardField(this, CARD_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
            field = new CardFilter(this, CARD_FILTER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 13)
            field = new DateField(this, NULL_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
