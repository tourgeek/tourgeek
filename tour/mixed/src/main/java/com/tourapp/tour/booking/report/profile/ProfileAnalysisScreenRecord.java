/**
 * @(#)ProfileAnalysisScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.report.profile;

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
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.main.db.*;

/**
 *  ProfileAnalysisScreenRecord - Screen record for profile analysis.
 */
public class ProfileAnalysisScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final String REPORT_DATE = REPORT_DATE;
    //public static final String REPORT_TIME = REPORT_TIME;
    //public static final String REPORT_USER_ID = REPORT_USER_ID;
    //public static final String REPORT_PAGE = REPORT_PAGE;
    //public static final String REPORT_COUNT = REPORT_COUNT;
    //public static final String REPORT_TOTAL = REPORT_TOTAL;
    //public static final String REPORT_KEY_AREA = REPORT_KEY_AREA;
    public static final String PRIMARY_ANALYSIS_TYPE = "PrimaryAnalysisType";
    public static final String SECONDARY_ANALYSIS_TYPE = "SecondaryAnalysisType";
    public static final String THIRD_ANALYSIS_TYPE = "ThirdAnalysisType";
    public static final String GROSS_SALES_START_DATE = "GrossSalesStartDate";
    public static final String GROSS_SALES_END_DATE = "GrossSalesEndDate";
    public static final String GROSS_SALES = "GrossSales";
    public static final String TEMPLATE = "template";
    /**
     * Default constructor.
     */
    public ProfileAnalysisScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProfileAnalysisScreenRecord(RecordOwner screen)
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

    public static final String PROFILE_ANALYSIS_SCREEN_RECORD_FILE = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //  field = new ProfileAnalysisScreenRecord_ReportDate(this, REPORT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 1)
        //  field = new ProfileAnalysisScreenRecord_ReportTime(this, REPORT_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 2)
        //  field = new ProfileAnalysisScreenRecord_ReportUserID(this, REPORT_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 3)
        //  field = new ShortField(this, REPORT_PAGE, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        //if (iFieldSeq == 4)
        //  field = new IntegerField(this, REPORT_COUNT, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        //if (iFieldSeq == 5)
        //  field = new CurrencyField(this, REPORT_TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        //if (iFieldSeq == 6)
        //  field = new IntegerField(this, REPORT_KEY_AREA, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new ProfileAnalysisField(this, PRIMARY_ANALYSIS_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, "ProfileAnalysisField.PROFILE_STATUS");
        if (iFieldSeq == 8)
            field = new ProfileAnalysisField(this, SECONDARY_ANALYSIS_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, "ProfileAnalysisField.STATE_REGION");
        if (iFieldSeq == 9)
            field = new ProfileAnalysisField(this, THIRD_ANALYSIS_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, "ProfileAnalysisField.NONE");
        if (iFieldSeq == 10)
            field = new DateField(this, GROSS_SALES_START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 11)
            field = new DateField(this, GROSS_SALES_END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
            field = new CurrencyField(this, GROSS_SALES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 13)
            field = new StringField(this, TEMPLATE, 60, null, "report");
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
