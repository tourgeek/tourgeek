
package com.tourgeek.tour.acctpay.air.oride;

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
import com.tourgeek.tour.acctpay.db.*;
import org.jbundle.main.db.*;
import org.jbundle.base.screen.model.util.*;
import com.tourgeek.tour.base.field.*;

/**
 *  OverrideScreenRecord - .
 */
public class OverrideScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final String REPORT_DATE = REPORT_DATE;
    //public static final String REPORT_TIME = REPORT_TIME;
    //public static final String REPORT_USER_ID = REPORT_USER_ID;
    //public static final String REPORT_PAGE = REPORT_PAGE;
    //public static final String REPORT_COUNT = REPORT_COUNT;
    //public static final String REPORT_TOTAL = REPORT_TOTAL;
    //public static final String REPORT_KEY_AREA = REPORT_KEY_AREA;
    public static final String COUNT = "Count";
    public static final String AIRLINE_ID = "AirlineID";
    public static final String VENDOR_ID = "VendorID";
    public static final String TOTAL = "Total";
    public static final String START_DEPARTURE = "StartDeparture";
    public static final String END_DEPARTURE = "EndDeparture";
    public static final String INCLUDE_PAID = "IncludePaid";
    public static final String OVERRIDE_PAID_DATE = "OverridePaidDate";
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

    public static final String OVERRIDE_SCREEN_RECORD_FILE = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //  field = new OverrideScreenRecord_ReportDate(this, REPORT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 1)
        //  field = new OverrideScreenRecord_ReportTime(this, REPORT_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 2)
        //  field = new OverrideScreenRecord_ReportUserID(this, REPORT_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 3)
        //  field = new ShortField(this, REPORT_PAGE, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        //if (iFieldSeq == 4)
        //  field = new IntegerField(this, REPORT_COUNT, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        //if (iFieldSeq == 5)
        //  field = new CurrencyField(this, REPORT_TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        //if (iFieldSeq == 6)
        //  field = new IntegerField(this, REPORT_KEY_AREA, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new ShortField(this, COUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new AirlineField(this, AIRLINE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new VendorField(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
            field = new CurrencyField(this, TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 11)
            field = new DateField(this, START_DEPARTURE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
            field = new DateField(this, END_DEPARTURE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 13)
            field = new BooleanField(this, INCLUDE_PAID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 14)
            field = new DateField(this, OVERRIDE_PAID_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
