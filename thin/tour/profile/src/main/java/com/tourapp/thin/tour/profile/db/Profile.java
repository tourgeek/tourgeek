/**
 * @(#)Profile.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.profile.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import org.jbundle.thin.main.db.*;
import com.tourapp.model.tour.profile.db.*;

public class Profile extends Company
    implements ProfileModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String PROFILE_CODE = CODE;
    public static final String GENERIC_NAME = NAME;
    //public static final String ADDRESS_LINE_1 = ADDRESS_LINE_1;
    //public static final String ADDRESS_LINE_2 = ADDRESS_LINE_2;
    //public static final String CITY_OR_TOWN = CITY_OR_TOWN;
    //public static final String STATE_OR_REGION = STATE_OR_REGION;
    //public static final String POSTAL_CODE = POSTAL_CODE;
    //public static final String COUNTRY = COUNTRY;
    //public static final String TEL = TEL;
    //public static final String FAX = FAX;
    //public static final String EMAIL = EMAIL;
    //public static final String WEB = WEB;
    //public static final String DATE_ENTERED = DATE_ENTERED;
    //public static final String DATE_CHANGED = DATE_CHANGED;
    //public static final String CHANGED_ID = CHANGED_ID;
    //public static final String COMMENTS = COMMENTS;
    //public static final String USER_ID = USER_ID;
    //public static final String PASSWORD = PASSWORD;
    //public static final String NAME_SORT = NAME_SORT;
    //public static final String POSTAL_CODE_SORT = POSTAL_CODE_SORT;
    public static final String NAME_ORDERED = CONTACT;

    public Profile()
    {
        super();
    }
    public Profile(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String PROFILE_FILE = "Profile";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Profile.PROFILE_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "profile";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.REMOTE | Constants.USER_DATA;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, "ID", 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "ProfileCode", 20, null, null);
        field = new FieldInfo(this, "GenericName", 65, null, null);
        field = new FieldInfo(this, "AddressLine1", 40, null, null);
        field = new FieldInfo(this, "AddressLine2", 40, null, null);
        field = new FieldInfo(this, "CityOrTown", 15, null, null);
        field = new FieldInfo(this, "StateOrRegion", 15, null, null);
        field = new FieldInfo(this, "PostalCode", 10, null, null);
        field = new FieldInfo(this, "Country", 15, null, null);
        field = new FieldInfo(this, "Tel", 24, null, null);
        field = new FieldInfo(this, "Fax", 24, null, null);
        field = new FieldInfo(this, "Email", 40, null, null);
        field = new FieldInfo(this, "Web", 60, null, null);
        field = new FieldInfo(this, "DateEntered", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "DateChanged", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "ChangedID", 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Comments", 32000, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "UserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Password", 16, null, null);
        field = new FieldInfo(this, "NameSort", 6, null, null);
        field = new FieldInfo(this, "PostalCodeSort", 5, null, null);
        field = new FieldInfo(this, "NameOrdered", 65, null, null);
        field = new FieldInfo(this, "ProfileTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "EnteredID", 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "AffiliationID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CommissionPlanCode", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, "AltPhone", 24, null, null);
        field = new FieldInfo(this, "NamePrefix", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, "NameFirst", 65, null, null);
        field = new FieldInfo(this, "NameMiddle", 65, null, null);
        field = new FieldInfo(this, "NameSur", 65, null, null);
        field = new FieldInfo(this, "NameSuffix", 10, null, null);
        field = new FieldInfo(this, "NameTitle", 10, null, null);
        field = new FieldInfo(this, "DateOfBirth", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "Gender", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, "ExpirationDate", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "CorporatePosition", 60, null, null);
        field = new FieldInfo(this, "PIN", 10, null, null);
        field = new FieldInfo(this, "CreditLimit", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "CountryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PrimaryLanguageID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CurrencysID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "MessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Properties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "ProfileStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ProfileClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Smoker", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "SeatChoiceID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "MainProfileID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, "ProfileCode");
        keyArea.addKeyField("ProfileCode", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "NameSort");
        keyArea.addKeyField("NameSort", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "MainProfileID");
        keyArea.addKeyField("MainProfileID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "PostalCodeSort");
        keyArea.addKeyField("PostalCodeSort", Constants.ASCENDING);
        keyArea.addKeyField("NameSort", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "NameSur");
        keyArea.addKeyField("NameSur", Constants.ASCENDING);
    }

}
