/**
 * @(#)ProfileAnalysisScreen.
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
        if (field.getFieldName().equals(this.getScreenRecord().getField(ProfileAnalysisScreenRecord.kGrossSales).getFieldName()))
        {
            if ((this.getScreenRecord().getField(ProfileAnalysisScreenRecord.kGrossSalesStartDate).isNull()) && (this.getScreenRecord().getField(ProfileAnalysisScreenRecord.kGrossSalesEndDate).isNull()))
                return null;    // Don't include gross sales
            Record recProfile = this.getBasisRecord();
            if (this.getRecord(Booking.kBookingFile) == null)
            {
                Booking recBooking = new Booking(this);
                BookingLine recBookingLine = new BookingLine(this);
        
                recProfile.addListener(new RecountOnValidHandler(recBooking));
                recBooking.addListener(new SubFileFilter(recProfile));
        //+        recBooking.addListener(new ExtractRangeFilter(Booking.kDepDate, this.getScreenRecord().getField(ProfileAnalysisScreenRecord.kGrossSalesStartDate), this.getScreenRecord().getField(ProfileAnalysisScreenRecord.kGrossSalesEndDate), true));
        
                recBooking.addListener(new RecountOnValidHandler(recBookingLine));
                recBookingLine.addDetailBehaviors(recBooking, null);
        
                recBooking.addListener(new SubCountHandler(this.getScreenRecord().getField(ProfileAnalysisScreenRecord.kGrossSales), Booking.kGross, true, true));
            }
        }
        if ((iSeq == 0) || (iSeq == 1) || (iSeq == 2))
        {
            int iScreenRecordSeq = ProfileAnalysisScreenRecord.kPrimaryAnalysisType;
            if (iSeq == 1)
                iScreenRecordSeq = ProfileAnalysisScreenRecord.kSecondaryAnalysisType;
            if (iSeq == 2)
                iScreenRecordSeq = ProfileAnalysisScreenRecord.kThirdAnalysisType;
        
            String strPrimary = this.getScreenRecord().getField(iScreenRecordSeq).toString();
            int iPrimaryField = Profile.kProfileTypeID;
            if (ProfileAnalysisField.NONE.equalsIgnoreCase(strPrimary))
                return null;
            else if (ProfileAnalysisField.AFFILIATION.equalsIgnoreCase(strPrimary))
                iPrimaryField = Profile.kAffiliationID;
            else if (ProfileAnalysisField.PROFILE_CLASS.equalsIgnoreCase(strPrimary))
                iPrimaryField = Profile.kProfileClassID;
            else if (ProfileAnalysisField.PROFILE_STATUS.equalsIgnoreCase(strPrimary))
                iPrimaryField = Profile.kProfileStatusID;
            else if (ProfileAnalysisField.STATE_REGION.equalsIgnoreCase(strPrimary))
                iPrimaryField = Profile.kStateOrRegion;
            else if (ProfileAnalysisField.CITY_TOWN.equalsIgnoreCase(strPrimary))
                iPrimaryField = Profile.kCityOrTown;
            else if (ProfileAnalysisField.COUNTRY.equalsIgnoreCase(strPrimary))
                iPrimaryField = Profile.kCountry;
            else if (ProfileAnalysisField.PROFILE_TYPE.equalsIgnoreCase(strPrimary))
                iPrimaryField = Profile.kProfileTypeID;
            else if ((ProfileAnalysisField.SALES_REGION.equalsIgnoreCase(strPrimary))
                || (ProfileAnalysisField.SALESPERSON.equalsIgnoreCase(strPrimary)))
            {
                SCF recSCF = new SCF(this);
                this.getRecord(Profile.kProfileFile).addListener(new MoveOnValidHandler(recSCF.getField(SCF.kScfFrom), this.getRecord(Profile.kProfileFile).getField(Profile.kPostalCode), null, true, true));
                MainFieldHandler behavior = (MainFieldHandler)recSCF.getField(SCF.kScfFrom).getListener(ScfFromHandler.class.getName());
                behavior.setReadOnly(true);
                behavior.setRespondsToMode(DBConstants.INIT_MOVE, true);
                if (ProfileAnalysisField.SALESPERSON.equalsIgnoreCase(strPrimary))
                    return recSCF.getField(SCF.kSalespersonID);
                return recSCF.getField(SCF.kSalesRegionID);
            }
            field = this.getRecord(Profile.kProfileFile).getField(iPrimaryField);
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
        for (int iFieldSeq = ProfileAnalysisScreenRecord.kPrimaryAnalysisType; iFieldSeq <= ProfileAnalysisScreenRecord.kThirdAnalysisType; iFieldSeq++)
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
        this.getRecord(Profile.kProfileFile).getField(Profile.kProfileTypeID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kStateOrRegion).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kCountry).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ProfileAnalysisScreenRecord.kProfileAnalysisScreenRecordFile).getField(ProfileAnalysisScreenRecord.kGrossSales).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        toolScreen.getScreenRecord().getField(ProfileAnalysisScreenRecord.kPrimaryAnalysisType).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        toolScreen.getScreenRecord().getField(ProfileAnalysisScreenRecord.kSecondaryAnalysisType).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        toolScreen.getScreenRecord().getField(ProfileAnalysisScreenRecord.kThirdAnalysisType).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        toolScreen.getScreenRecord().getField(ProfileAnalysisScreenRecord.kGrossSalesStartDate).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        toolScreen.getScreenRecord().getField(ProfileAnalysisScreenRecord.kGrossSalesEndDate).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        toolScreen.getScreenRecord().getField(ProfileAnalysisScreenRecord.ktemplate).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        super.addToolbarButtons(toolScreen);
    }

}
