/**
  * @(#)PastDueScreenRecord.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctrec.report.pastdue;

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
import com.tourgeek.tour.genled.db.*;
import org.jbundle.base.screen.model.report.*;
import org.bson.*;
import com.tourgeek.tour.acctrec.db.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.tour.booking.detail.db.*;
import org.jbundle.main.db.*;
import org.jbundle.base.screen.model.util.*;

/**
 *  PastDueScreenRecord - Screen variables.
 */
public class PastDueScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final String REPORT_DATE = REPORT_DATE;
    //public static final String REPORT_TIME = REPORT_TIME;
    //public static final String REPORT_USER_ID = REPORT_USER_ID;
    //public static final String REPORT_PAGE = REPORT_PAGE;
    //public static final String REPORT_COUNT = REPORT_COUNT;
    //public static final String REPORT_TOTAL = REPORT_TOTAL;
    //public static final String REPORT_KEY_AREA = REPORT_KEY_AREA;
    public static final String AS_OF_DATE = "AsOfDate";
    public static final String CHECK_DEP = "CheckDep";
    public static final String MCO_PER = "McoPer";
    public static final String CHECK_FINAL = "CheckFinal";
    public static final String PRINT_RPT = "PrintRpt";
    public static final String PRT_NOTICE = "PrtNotice";
    public static final String MCO_AMOUNT_PAID = "McoAmountPaid";
    public static final String DUE_DATE = "DueDate";
    public static final String AMT_DUE = "AmtDue";
    public static final String DEP_OR_PYMT = "DepOrPymt";
    public static final String TEMPLATE = "template";
    /**
     * Default constructor.
     */
    public PastDueScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public PastDueScreenRecord(RecordOwner screen)
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

    public static final String PAST_DUE_SCREEN_RECORD_FILE = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //  field = new PastDueScreenRecord_ReportDate(this, REPORT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 1)
        //  field = new PastDueScreenRecord_ReportTime(this, REPORT_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 2)
        //  field = new PastDueScreenRecord_ReportUserID(this, REPORT_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 3)
        //  field = new ShortField(this, REPORT_PAGE, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        //if (iFieldSeq == 4)
        //  field = new IntegerField(this, REPORT_COUNT, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        //if (iFieldSeq == 5)
        //  field = new CurrencyField(this, REPORT_TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        //if (iFieldSeq == 6)
        //  field = new IntegerField(this, REPORT_KEY_AREA, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new PastDueScreenRecord_AsOfDate(this, AS_OF_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new BooleanField(this, CHECK_DEP, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == 9)
            field = new PercentField(this, MCO_PER, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
            field = new BooleanField(this, CHECK_FINAL, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == 11)
            field = new BooleanField(this, PRINT_RPT, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == 12)
            field = new BooleanField(this, PRT_NOTICE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 13)
            field = new CurrencyField(this, MCO_AMOUNT_PAID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 14)
            field = new DateField(this, DUE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 15)
            field = new CurrencyField(this, AMT_DUE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
            field = new StringField(this, DEP_OR_PYMT, 7, null, null);
        if (iFieldSeq == 17)
            field = new StringField(this, TEMPLATE, 60, null, "report");
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
