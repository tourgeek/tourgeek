/**
 * @(#)CityScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.base.db;

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
 *  CityScreenRecord - .
 */
public class CityScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String DESCRIPTION = "Description";
    public static final int kDescription = kScreenRecordLastField + 1;
    public static final String COUNTRY_ID = "CountryID";
    public static final int kCountryID = kDescription + 1;
    public static final String STATE_ID = "StateID";
    public static final int kStateID = kCountryID + 1;
    public static final int kCityScreenRecordLastField = kStateID;
    public static final int kCityScreenRecordFields = kStateID - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public CityScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CityScreenRecord(RecordOwner screen)
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

    public static final String kCityScreenRecordFile = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 10, null, null);
        if (iFieldSeq == kCountryID)
            field = new CountryField(this, "CountryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStateID)
            field = new StateField(this, "StateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCityScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
