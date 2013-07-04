
package com.tourgeek.tour.booking.report.profile;

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

/**
 *  ProfileAnalysisHeading - .
 */
public class ProfileAnalysisHeading extends HeadingScreen
{
    /**
     * Default constructor.
     */
    public ProfileAnalysisHeading()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public ProfileAnalysisHeading(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(ProfileAnalysisScreenRecord.PROFILE_ANALYSIS_SCREEN_RECORD_FILE).getField(ProfileAnalysisScreenRecord.REPORT_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ProfileAnalysisScreenRecord.PROFILE_ANALYSIS_SCREEN_RECORD_FILE).getField(ProfileAnalysisScreenRecord.REPORT_TIME).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ProfileAnalysisScreenRecord.PROFILE_ANALYSIS_SCREEN_RECORD_FILE).getField(ProfileAnalysisScreenRecord.REPORT_USER_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ProfileAnalysisScreenRecord.PROFILE_ANALYSIS_SCREEN_RECORD_FILE).getField(ProfileAnalysisScreenRecord.REPORT_PAGE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ProfileAnalysisScreenRecord.PROFILE_ANALYSIS_SCREEN_RECORD_FILE).getField(ProfileAnalysisScreenRecord.PRIMARY_ANALYSIS_TYPE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ProfileAnalysisScreenRecord.PROFILE_ANALYSIS_SCREEN_RECORD_FILE).getField(ProfileAnalysisScreenRecord.SECONDARY_ANALYSIS_TYPE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ProfileAnalysisScreenRecord.PROFILE_ANALYSIS_SCREEN_RECORD_FILE).getField(ProfileAnalysisScreenRecord.THIRD_ANALYSIS_TYPE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
