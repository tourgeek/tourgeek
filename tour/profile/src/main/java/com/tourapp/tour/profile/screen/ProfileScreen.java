/**
 * @(#)ProfileScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.profile.screen;

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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.profile.screen.detail.*;

/**
 *  ProfileScreen - Passenger and Agency Profile.
 */
public class ProfileScreen extends Screen
{
    /**
     * Default constructor.
     */
    public ProfileScreen()
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
    public ProfileScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Passenger and Agency Profile";
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
     * Open the Other Files.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ProfileControl(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.addMainKeyBehavior();
        Profile recProfile = (Profile)this.getRecord(Profile.kProfileFile);
        ProfileControl recProfileControl = (ProfileControl)this.getRecord(ProfileControl.kProfileControlFile);
        try   {
            recProfileControl.open();   // Read the control file
            recProfile.getField(Profile.kProfileStatusID).addListener(new InitFieldHandler(recProfileControl.getField(ProfileControl.kDefaultProfileStatusID)));
            recProfile.getField(Profile.kProfileClassID).addListener(new InitFieldHandler(recProfileControl.getField(ProfileControl.kDefaultProfileClassID)));
            recProfile.getField(Profile.kProfileTypeID).addListener(new InitFieldHandler(recProfileControl.getField(ProfileControl.kDefaultProfileTypeID)));
            recProfile.getField(Profile.kCountryID).addListener(new InitFieldHandler(recProfileControl.getField(ProfileControl.kCountryID)));
            recProfile.getField(Profile.kPrimaryLanguageID).addListener(new InitFieldHandler(recProfileControl.getField(ProfileControl.kLanguageID)));
            recProfile.getField(Profile.kCurrencysID).addListener(new InitFieldHandler(recProfileControl.getField(ProfileControl.kCurrencysID)));
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        Record recCountry = ((ReferenceField)recProfile.getField(Profile.kCountryID)).getReferenceRecord();
        recProfile.getField(Profile.kCountryID).addListener(new MoveOnChangeHandler(recProfile.getField(Profile.kCountry), recCountry.getField(Country.kName)));
        recProfile.getField(Profile.kCountryID).addListener(new MoveOnChangeHandler(recProfile.getField(Profile.kPrimaryLanguageID), recCountry.getField(Country.kLanguageID)));
        recProfile.getField(Profile.kCountryID).addListener(new MoveOnChangeHandler(recProfile.getField(Profile.kCurrencysID), recCountry.getField(Country.kCurrencysID)));
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, Profile.MESSAGE_LOG_SCREEN, MenuConstants.FORMDETAIL, Profile.MESSAGE_LOG_SCREEN, null);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(Profile.kProfileFile).getField(Profile.kProfileCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kProfileTypeID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kGenericName).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kNameOrdered).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kAddressLine1).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kAddressLine2).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kCityOrTown).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kStateOrRegion).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kPostalCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kCountry).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kEmail).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kComments).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kTel).setupDefaultView(this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kFax).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kAffiliationID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kProfileStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kProfileClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kCountryID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kPrimaryLanguageID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kCurrencysID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kDateEntered).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kMainProfileID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Process the command.
     * <br />Step 1 - Process the command if possible and return true if processed.
     * <br />Step 2 - If I can't process, pass to all children (with me as the source).
     * <br />Step 3 - If children didn't process, pass to parent (with me as the source).
     * <br />Note: Never pass to a parent or child that matches the source (to avoid an endless loop).
     * @param strCommand The command to process.
     * @param sourceSField The source screen field (to avoid echos).
     * @param iCommandOptions If this command creates a new screen, create in a new window?
     * @return true if success.
     */
    public boolean doCommand(String strCommand, ScreenField sourceSField, int iCommandOptions)
    {
        if (strCommand.equalsIgnoreCase(MenuConstants.FORMDETAIL))
            return (this.onForm(null, ScreenConstants.DETAIL_MODE, true, iCommandOptions, null) != null);
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
