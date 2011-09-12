/**
 * @(#)ProfileGridScreen.
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
 *  ProfileGridScreen - Passenger and Agency Profile.
 */
public class ProfileGridScreen extends GridScreen
{
    /**
     * Default constructor.
     */
    public ProfileGridScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public ProfileGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new ProfileScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.setDisplayInitialQuery(false);
        Profile recProfile = (Profile)this.getMainRecord();
        Record recScreenQuery = this.getScreenRecord();
        ((NumberField)recScreenQuery.getField(ProfileScreenRecord.kProfileKey)).setValue(0, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        recScreenQuery.getField(ProfileScreenRecord.kProfileKey).addListener(new RegisterValueHandler(null));
        this.setEditing(false);
        SortOrderHandler keyBehavior = new SortOrderHandler(this);
        keyBehavior.setGridTable(Profile.kNameSortKey, recProfile, 0);
        keyBehavior.setGridTable(Profile.kNameSurKey, recProfile, 1);
        keyBehavior.setGridTable(-1, recProfile, 2);
        keyBehavior.setGridTable(Profile.kPostalCodeSortKey, recProfile, 3);
        keyBehavior.setGridTable(-1, recProfile, 4);
        keyBehavior.setGridTable(-1, recProfile, 5);
        keyBehavior.setGridTable(-1, recProfile, 6);
        recScreenQuery.getField(ProfileScreenRecord.kProfileKey).addListener(keyBehavior);
        
        recProfile.addListener(new ExtractRangeFilter(Profile.kPostalCodeSort, recScreenQuery.getField(ProfileScreenRecord.kPostalCodeSort)));
        recProfile.addListener(new ExtractRangeFilter(Profile.kNameSur, recScreenQuery.getField(ProfileScreenRecord.kLastNameSort)));
        recProfile.addListener(new ExtractRangeFilter(Profile.kNameSort, recScreenQuery.getField(ProfileScreenRecord.kNameSort)));
        recProfile.addListener(new CompareFileFilter(Profile.kProfileTypeID, recScreenQuery.getField(ProfileScreenRecord.kProfileTypeID), "=", null, true));
        
        recScreenQuery.getField(ProfileScreenRecord.kNameSort).addListener(new FieldReSelectHandler(this));
        recScreenQuery.getField(ProfileScreenRecord.kLastNameSort).addListener(new FieldReSelectHandler(this));
        recScreenQuery.getField(ProfileScreenRecord.kPostalCodeSort).addListener(new FieldReSelectHandler(this));
        recScreenQuery.getField(ProfileScreenRecord.kProfileTypeID).addListener(new FieldReSelectHandler(this));
    }
    /**
     * Read the current file in the header record given the current detail record.
     */
    public void syncHeaderToMain()
    {
        super.syncHeaderToMain();
        this.restoreScreenParam(ProfileScreenRecord.kProfileTypeID);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
        toolScreen.getScreenRecord().getField(ProfileScreenRecord.kNameSort).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        toolScreen.getScreenRecord().getField(ProfileScreenRecord.kLastNameSort).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        toolScreen.getScreenRecord().getField(ProfileScreenRecord.kPostalCodeSort).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        toolScreen.getScreenRecord().getField(ProfileScreenRecord.kProfileTypeID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Add the navigation button(s) to the left of the grid row.
     */
    public void addNavButtons()
    {
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
        super.addNavButtons();
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Converter converter = this.getRecord(Profile.kProfileFile).getField(Profile.kGenericName);
        converter = new FieldLengthConverter(converter, 25);
        this.addColumn(converter);
        converter = this.getRecord(Profile.kProfileFile).getField(Profile.kNameOrdered);
        converter = new FieldLengthConverter(converter, 25);
        this.addColumn(converter);
        this.getRecord(Profile.kProfileFile).getField(Profile.kCityOrTown).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kPostalCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kTel).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Profile.kProfileFile).getField(Profile.kFax).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
