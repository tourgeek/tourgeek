/**
 * @(#)Location.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.model.tour.base.db.*;

/**
 *  Location - This is the base class for all location records such as:
Country, City, Region, etc..
 */
public class Location extends VirtualRecord
     implements LocationModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kName = kVirtualRecordLastField + 1;
    public static final int kCode = kName + 1;
    public static final int kLocationLastField = kCode;
    public static final int kLocationFields = kCode - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kNameKey = kIDKey + 1;
    public static final int kCodeKey = kNameKey + 1;
    public static final int kLocationLastKey = kCodeKey;
    public static final int kLocationKeys = kCodeKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public Location()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Location(RecordOwner screen)
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

    public static final String kLocationFile = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kName)
            field = new StringField(this, "Name", 40, null, null);
        if (iFieldSeq == kCode)
            field = new StringField(this, "Code", 3, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kLocationLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
