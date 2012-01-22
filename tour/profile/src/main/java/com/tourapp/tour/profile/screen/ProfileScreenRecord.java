/**
 * @(#)ProfileScreenRecord.
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

    public static final int kProfileKey = kScreenRecordLastField + 1;
    public static final int kNameSort = kProfileKey + 1;
    public static final int kPostalCodeSort = kNameSort + 1;
    public static final int kProfileTypeID = kPostalCodeSort + 1;
    public static final int kLastNameSort = kProfileTypeID + 1;
    public static final int kProfileScreenRecordLastField = kLastNameSort;
    public static final int kProfileScreenRecordFields = kLastNameSort - DBConstants.MAIN_FIELD + 1;
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

    public static final String kProfileScreenRecordFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kProfileKey)
            field = new ShortField(this, "ProfileKey", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNameSort)
            field = new StringField(this, "NameSort", 15, null, null);
        if (iFieldSeq == kPostalCodeSort)
            field = new StringField(this, "PostalCodeSort", 10, null, null);
        if (iFieldSeq == kProfileTypeID)
            field = new ProfileTypeFilter(this, "ProfileTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLastNameSort)
            field = new StringField(this, "LastNameSort", 15, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kProfileScreenRecordLastField)
                field = new EmptyField(this);
        }
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
        this.getField(ProfileScreenRecord.kNameSort).addListener(upper);
        CheckForTheHandler the = new CheckForTheHandler(null);
        this.getField(ProfileScreenRecord.kNameSort).addListener(the);
    }

}
