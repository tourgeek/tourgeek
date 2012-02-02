/**
 * @(#)ProfileField.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.profile.db;

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

/**
 *  ProfileField - Profile field.
 */
public class ProfileField extends CustSaleCustNo
{
    /**
     * Default constructor.
     */
    public ProfileField()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The parent record.
     * @param strName The field name.
     * @param iDataLength The maximum string length (pass -1 for default).
     * @param strDesc The string description (usually pass null, to use the resource file desc).
     * @param strDefault The default value (if object, this value is the default value, if string, the string is the default).
     */
    public ProfileField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        super.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Get (or make) the current record for this reference.
     */
    public Record makeReferenceRecord(RecordOwner recordOwner)
    {
        return new Profile(recordOwner);
    }
    /**
     * Set up the default screen control for this field.
     * @param itsLocation Location of this component on screen (ie., GridBagConstraint).
     * @param targetScreen Where to place this component (ie., Parent screen or GridBagLayout).
     * @param converter The converter to set the screenfield to.
     * @param iDisplayFieldDesc Display the label? (optional).
     * @param properties Extra properties
     * @return Return the component or ScreenField that is created for this field.
     */
    public ScreenComponent setupDefaultView(ScreenLoc itsLocation, ComponentParent targetScreen, Convert converter, int iDisplayFieldDesc, Map<String, Object> properties)
    {
        Profile recProfile = (Profile)this.makeReferenceRecord();
        Converter paConverter = new FirstMLastConverter(recProfile, Profile.NAME_PREFIX, Profile.NAME_FIRST, Profile.NAME_MIDDLE, Profile.NAME_SUR, Profile.NAME_SUFFIX, Profile.NAME_TITLE);
        Converter altConverter = new AltFieldConverter(recProfile.getField(Profile.NAME), paConverter);
        altConverter = new FieldLengthConverter(altConverter, 25);
        altConverter = new FieldDescConverter((Converter)altConverter, (Converter)converter);
        ScreenField sField = (ScreenField)altConverter.setupDefaultView(itsLocation, targetScreen, altConverter, iDisplayFieldDesc, properties);
        sField.setEnabled(false);
        return this.setupTableLookup(itsLocation, targetScreen, converter, iDisplayFieldDesc, recProfile, -1, -2, true, true);
    }

}
