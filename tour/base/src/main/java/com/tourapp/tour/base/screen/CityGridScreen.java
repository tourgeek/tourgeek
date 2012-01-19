/**
 * @(#)CityGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.base.screen;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.base.db.*;

/**
 *  CityGridScreen - Airports.
 */
public class CityGridScreen extends GridScreen
{
    /**
     * Default constructor.
     */
    public CityGridScreen()
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
    public CityGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Airports";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new City(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new CityScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getMainRecord().addListener(new ExtractRangeFilter(City.kDescription, this.getScreenRecord().getField(CityScreenRecord.kDescription)));
        this.getMainRecord().addListener(new CompareFileFilter(City.kStateID, this.getScreenRecord().getField(CityScreenRecord.kStateID), "=", null, true));
        this.getMainRecord().addListener(new CompareFileFilter(City.kCountryID, this.getScreenRecord().getField(CityScreenRecord.kCountryID), "=", null, true));
        
        this.getScreenRecord().getField(CityScreenRecord.kDescription).addListener(new FieldReSelectHandler(this));
        this.getScreenRecord().getField(CityScreenRecord.kStateID).addListener(new FieldReSelectHandler(this));
        this.getScreenRecord().getField(CityScreenRecord.kCountryID).addListener(new FieldReSelectHandler(this));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        ToolScreen toolScreen = super.addToolbars();
        
        this.getScreenRecord().getField(CityScreenRecord.kDescription).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(CityScreenRecord.kCountryID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(CityScreenRecord.kStateID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        
        return toolScreen;
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(City.kCityFile).getField(City.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(City.kCityFile).getField(City.kCityCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
