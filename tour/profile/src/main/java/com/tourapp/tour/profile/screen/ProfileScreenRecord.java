/**
 * @(#)ProfileScreenRecord.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.profile.db.*;

/**
 *  ProfileScreenRecord - Screen Fields.
 */
public class ProfileScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String PROFILE_KEY = "ProfileKey";
    public static final String NAME_SORT = "NameSort";
    public static final String POSTAL_CODE_SORT = "PostalCodeSort";
    public static final String PROFILE_TYPE_ID = "ProfileTypeID";
    public static final String LAST_NAME_SORT = "LastNameSort";
    /**
     * Default constructor.
     */
    public ProfileScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProfileScreenRecord(RecordOwner screen)
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

    public static final String PROFILE_SCREEN_RECORD_FILE = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new ShortField(this, PROFILE_KEY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new StringField(this, NAME_SORT, 15, null, null);
        if (iFieldSeq == 2)
            field = new StringField(this, POSTAL_CODE_SORT, 10, null, null);
        if (iFieldSeq == 3)
            field = new ProfileTypeFilter(this, PROFILE_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new StringField(this, LAST_NAME_SORT, 15, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add all standard file & field behaviors.
     * Override this to add record listeners and filters.
     */
    public void addListeners()
    {
        super.addListeners();
        FieldToUpperHandler upper = new FieldToUpperHandler(null);
        this.getField(ProfileScreenRecord.NAME_SORT).addListener(upper);
        CheckForTheHandler the = new CheckForTheHandler(null);
        this.getField(ProfileScreenRecord.NAME_SORT).addListener(the);
    }

}
