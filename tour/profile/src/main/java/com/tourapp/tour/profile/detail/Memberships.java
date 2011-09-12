/**
 * @(#)Memberships.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.profile.detail;

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

/**
 *  Memberships - Membership detail.
 */
public class Memberships extends VirtualRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kProfileID = kVirtualRecordLastField + 1;
    public static final int kProgramCode = kProfileID + 1;
    public static final int kAccountID = kProgramCode + 1;
    public static final int kLevelCode = kAccountID + 1;
    public static final int kMembershipCategory = kLevelCode + 1;
    public static final int kExpireDate = kMembershipCategory + 1;
    public static final int kSignupDate = kExpireDate + 1;
    public static final int kStartDate = kSignupDate + 1;
    public static final int kMembershipsLastField = kStartDate;
    public static final int kMembershipsFields = kStartDate - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kProfileIDKey = kIDKey + 1;
    public static final int kMembershipsLastKey = kProfileIDKey;
    public static final int kMembershipsKeys = kProfileIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public Memberships()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Memberships(RecordOwner screen)
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

    public static final String kMembershipsFile = "Memberships";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kMembershipsFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Membership";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "profile";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.REMOTE | DBConstants.USER_DATA;
    }
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
        if (iFieldSeq == kProfileID)
            field = new ProfileField(this, "ProfileID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProgramCode)
            field = new StringField(this, "ProgramCode", 10, null, null);
        if (iFieldSeq == kAccountID)
            field = new StringField(this, "AccountID", 20, null, null);
        if (iFieldSeq == kLevelCode)
            field = new StringField(this, "LevelCode", 20, null, null);
        if (iFieldSeq == kMembershipCategory)
            field = new StringField(this, "MembershipCategory", 6, null, null);
        if (iFieldSeq == kExpireDate)
            field = new DateField(this, "ExpireDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSignupDate)
            field = new DateField(this, "SignupDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartDate)
            field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kMembershipsLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == kIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PrimaryKey");
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kProfileIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ProfileID");
            keyArea.addKeyField(kProfileID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kMembershipsLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kMembershipsLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
