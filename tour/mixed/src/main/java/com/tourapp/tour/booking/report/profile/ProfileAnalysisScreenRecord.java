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

    public static final String PRIMARY_ANALYSIS_TYPE = "PrimaryAnalysisType";
    public static final int kPrimaryAnalysisType = kReportScreenRecordLastField + 1;
    public static final String SECONDARY_ANALYSIS_TYPE = "SecondaryAnalysisType";
    public static final int kSecondaryAnalysisType = kPrimaryAnalysisType + 1;
    public static final String THIRD_ANALYSIS_TYPE = "ThirdAnalysisType";
    public static final int kThirdAnalysisType = kSecondaryAnalysisType + 1;
    public static final String GROSS_SALES_START_DATE = "GrossSalesStartDate";
    public static final int kGrossSalesStartDate = kThirdAnalysisType + 1;
    public static final String GROSS_SALES_END_DATE = "GrossSalesEndDate";
    public static final int kGrossSalesEndDate = kGrossSalesStartDate + 1;
    public static final String GROSS_SALES = "GrossSales";
    public static final int kGrossSales = kGrossSalesEndDate + 1;
    public static final String TEMPLATE = "template";
    public static final int ktemplate = kGrossSales + 1;
    public static final int kProfileAnalysisScreenRecordLastField = ktemplate;
    public static final int kProfileAnalysisScreenRecordFields = ktemplate - DBConstants.MAIN_FIELD + 1;
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

    public static final String kProfileAnalysisScreenRecordFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kPrimaryAnalysisType)
            field = new ProfileAnalysisField(this, "PrimaryAnalysisType", Constants.DEFAULT_FIELD_LENGTH, null, "ProfileAnalysisField.PROFILE_STATUS");
        if (iFieldSeq == kSecondaryAnalysisType)
            field = new ProfileAnalysisField(this, "SecondaryAnalysisType", Constants.DEFAULT_FIELD_LENGTH, null, "ProfileAnalysisField.STATE_REGION");
        if (iFieldSeq == kThirdAnalysisType)
            field = new ProfileAnalysisField(this, "ThirdAnalysisType", Constants.DEFAULT_FIELD_LENGTH, null, "ProfileAnalysisField.NONE");
        if (iFieldSeq == kGrossSalesStartDate)
            field = new DateField(this, "GrossSalesStartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kGrossSalesEndDate)
            field = new DateField(this, "GrossSalesEndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kGrossSales)
            field = new CurrencyField(this, "GrossSales", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == ktemplate)
            field = new StringField(this, "template", 60, null, "report");
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kProfileAnalysisScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
