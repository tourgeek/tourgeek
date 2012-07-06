/**
  * @(#)ProfileAnalysisScreen.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
 *  ProfileAnalysisScreen - Profile analysis.
 */
public class ProfileAnalysisScreen extends AnalysisScreen
{
    /**
     * Default constructor.
     */
    public ProfileAnalysisScreen()
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
    public ProfileAnalysisScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Profile analysis";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Profile(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new ProfileAnalysisScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
    }
    /**
     * Get the source field to analyze at this position (From the selection).
     * @param iSeq location of the source field.
     * @return The field.
     */
    public BaseField getSourceField(int iSeq)
    {
        BaseField field = super.getSourceField(iSeq);
        if (field.getFieldName().equals(this.getScreenRecord().getField(ProfileAnalysisScreenRecord.GROSS_SALES).getFieldName()))
        {
            if ((this.getScreenRecord().getField(ProfileAnalysisScreenRecord.GROSS_SALES_START_DATE).isNull()) && (this.getScreenRecord().getField(ProfileAnalysisScreenRecord.GROSS_SALES_END_DATE).isNull()))
                return null;    // Don't include gross sales
            Record recProfile = this.getBasisRecord();
            if (this.getRecord(Booking.BOOKING_FILE) == null)
            {
                Booking recBooking = new Booking(this);
                BookingLine recBookingLine = new BookingLine(this);
        
                recProfile.addListener(new RecountOnValidHandler(recBooking));
                recBooking.addListener(new SubFileFilter(recProfile));
        //+        recBooking.addListener(new ExtractRangeFilter(Booking.DEP_DATE, this.getScreenRecord().getField(ProfileAnalysisScreenRecord.GROSS_SALES_START_DATE), this.getScreenRecord().getField(ProfileAnalysisScreenRecord.GROSS_SALES_END_DATE), true));
        
                recBooking.addListener(new RecountOnValidHandler(recBookingLine));
                recBookingLine.addDetailBehaviors(recBooking, null);
        
                recBooking.addListener(new SubCountHandler(this.getScreenRecord().getField(ProfileAnalysisScreenRecord.GROSS_SALES), Booking.GROSS, true, true));
            }
        }
        if ((iSeq == 0) || (iSeq == 1) || (iSeq == 2))
        {
            String iScreenRecordSeq = ProfileAnalysisScreenRecord.PRIMARY_ANALYSIS_TYPE;
            if (iSeq == 1)
                iScreenRecordSeq = ProfileAnalysisScreenRecord.SECONDARY_ANALYSIS_TYPE;
            if (iSeq == 2)
                iScreenRecordSeq = ProfileAnalysisScreenRecord.THIRD_ANALYSIS_TYPE;
        
            String strPrimary = this.getScreenRecord().getField(iScreenRecordSeq).toString();
            String iPrimaryField = Profile.PROFILE_TYPE_ID;
            if (ProfileAnalysisField.NONE.equalsIgnoreCase(strPrimary))
                return null;
            else if (ProfileAnalysisField.AFFILIATION.equalsIgnoreCase(strPrimary))
                iPrimaryField = Profile.AFFILIATION_ID;
            else if (ProfileAnalysisField.PROFILE_CLASS.equalsIgnoreCase(strPrimary))
                iPrimaryField = Profile.PROFILE_CLASS_ID;
            else if (ProfileAnalysisField.PROFILE_STATUS.equalsIgnoreCase(strPrimary))
                iPrimaryField = Profile.PROFILE_STATUS_ID;
            else if (ProfileAnalysisField.STATE_REGION.equalsIgnoreCase(strPrimary))
                iPrimaryField = Profile.STATE_OR_REGION;
            else if (ProfileAnalysisField.CITY_TOWN.equalsIgnoreCase(strPrimary))
                iPrimaryField = Profile.CITY_OR_TOWN;
            else if (ProfileAnalysisField.COUNTRY.equalsIgnoreCase(strPrimary))
                iPrimaryField = Profile.COUNTRY;
            else if (ProfileAnalysisField.PROFILE_TYPE.equalsIgnoreCase(strPrimary))
                iPrimaryField = Profile.PROFILE_TYPE_ID;
            else if ((ProfileAnalysisField.SALES_REGION.equalsIgnoreCase(strPrimary))
                || (ProfileAnalysisField.SALESPERSON.equalsIgnoreCase(strPrimary)))
            {
                SCF recSCF = new SCF(this);
                this.getRecord(Profile.PROFILE_FILE).addListener(new MoveOnValidHandler(recSCF.getField(SCF.SCF_FROM), this.getRecord(Profile.PROFILE_FILE).getField(Profile.POSTAL_CODE), null, true, true));
                MainFieldHandler behavior = (MainFieldHandler)recSCF.getField(SCF.SCF_FROM).getListener(ScfFromHandler.class.getName());
                behavior.setReadOnly(true);
                behavior.setRespondsToMode(DBConstants.INIT_MOVE, true);
                if (ProfileAnalysisField.SALESPERSON.equalsIgnoreCase(strPrimary))
                    return recSCF.getField(SCF.SALESPERSON_ID);
                return recSCF.getField(SCF.SALES_REGION_ID);
            }
            field = this.getRecord(Profile.PROFILE_FILE).getField(iPrimaryField);
        }
        return field;
    }
    /**
     * This is field a potential index for analysis.
     * (Override this to allow number fields to be keys).
     * @param field The field to check.
     * @param iSourceFieldSeq The source field sequence of this field
     * @return True if this is a potential key field.
     */
    public boolean isKeyField(BaseField field, int iSourceFieldSeq)
    {
        int iKeyCount = 0;
        for (int iFieldSeq = this.getScreenRecord().getFieldSeq(ProfileAnalysisScreenRecord.PRIMARY_ANALYSIS_TYPE); iFieldSeq <= this.getScreenRecord().getFieldSeq(ProfileAnalysisScreenRecord.THIRD_ANALYSIS_TYPE); iFieldSeq++)
        {
            if (!ProfileAnalysisField.NONE.equals(this.getScreenRecord().getField(iFieldSeq)))
                iKeyCount++;
        }
        if (iSourceFieldSeq < iKeyCount)
            return true;
        return false;
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new ProfileAnalysisHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(Profile.PROFILE_FILE).getField(Profile.PROFILE_TYPE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.PROFILE_FILE).getField(Profile.STATE_OR_REGION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.PROFILE_FILE).getField(Profile.COUNTRY).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.PROFILE_FILE).getField(Profile.ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ProfileAnalysisScreenRecord.PROFILE_ANALYSIS_SCREEN_RECORD_FILE).getField(ProfileAnalysisScreenRecord.GROSS_SALES).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        toolScreen.getScreenRecord().getField(ProfileAnalysisScreenRecord.PRIMARY_ANALYSIS_TYPE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        toolScreen.getScreenRecord().getField(ProfileAnalysisScreenRecord.SECONDARY_ANALYSIS_TYPE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        toolScreen.getScreenRecord().getField(ProfileAnalysisScreenRecord.THIRD_ANALYSIS_TYPE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        toolScreen.getScreenRecord().getField(ProfileAnalysisScreenRecord.GROSS_SALES_START_DATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        toolScreen.getScreenRecord().getField(ProfileAnalysisScreenRecord.GROSS_SALES_END_DATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        toolScreen.getScreenRecord().getField(ProfileAnalysisScreenRecord.TEMPLATE).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        super.addToolbarButtons(toolScreen);
    }

}
