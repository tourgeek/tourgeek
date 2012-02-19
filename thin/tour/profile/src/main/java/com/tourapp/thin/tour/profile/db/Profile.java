/**
 * @(#)Profile.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
    private static final long serialVersionUID = 1L;


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
        field = new FieldInfo(this, ID, 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, PROFILE_CODE, 20, null, null);
        field = new FieldInfo(this, GENERIC_NAME, 65, null, null);
        field = new FieldInfo(this, ADDRESS_LINE_1, 40, null, null);
        field = new FieldInfo(this, ADDRESS_LINE_2, 40, null, null);
        field = new FieldInfo(this, CITY_OR_TOWN, 15, null, null);
        field = new FieldInfo(this, STATE_OR_REGION, 15, null, null);
        field = new FieldInfo(this, POSTAL_CODE, 10, null, null);
        field = new FieldInfo(this, COUNTRY, 15, null, null);
        field = new FieldInfo(this, TEL, 24, null, null);
        field = new FieldInfo(this, FAX, 24, null, null);
        field = new FieldInfo(this, EMAIL, 40, null, null);
        field = new FieldInfo(this, WEB, 60, null, null);
        field = new FieldInfo(this, DATE_ENTERED, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, DATE_CHANGED, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, CHANGED_ID, 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COMMENTS, 32000, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PASSWORD, 16, null, null);
        field = new FieldInfo(this, NAME_SORT, 6, null, null);
        field = new FieldInfo(this, POSTAL_CODE_SORT, 5, null, null);
        field = new FieldInfo(this, NAME_ORDERED, 65, null, null);
        field = new FieldInfo(this, PROFILE_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ENTERED_ID, 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AFFILIATION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COMMISSION_PLAN_CODE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, ALT_PHONE, 24, null, null);
        field = new FieldInfo(this, NAME_PREFIX, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, NAME_FIRST, 65, null, null);
        field = new FieldInfo(this, NAME_MIDDLE, 65, null, null);
        field = new FieldInfo(this, NAME_SUR, 65, null, null);
        field = new FieldInfo(this, NAME_SUFFIX, 10, null, null);
        field = new FieldInfo(this, NAME_TITLE, 10, null, null);
        field = new FieldInfo(this, DATE_OF_BIRTH, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, GENDER, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, EXPIRATION_DATE, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, CORPORATE_POSITION, 60, null, null);
        field = new FieldInfo(this, PIN, 10, null, null);
        field = new FieldInfo(this, CREDIT_LIMIT, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, COUNTRY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PRIMARY_LANGUAGE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CURRENCYS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, PROFILE_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PROFILE_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SMOKER, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, SEAT_CHOICE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MAIN_PROFILE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
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
