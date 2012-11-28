/**
  * @(#)CompanyInfo.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.genled.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import org.jbundle.thin.main.db.*;
import com.tourapp.model.tour.genled.db.*;

public class CompanyInfo extends Company
    implements CompanyInfoModel
{
    private static final long serialVersionUID = 1L;


    public CompanyInfo()
    {
        super();
    }
    public CompanyInfo(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String COMPANY_INFO_FILE = "CompanyInfo";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? CompanyInfo.COMPANY_INFO_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "genled";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.LOCAL | Constants.USER_DATA;
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
        field = new FieldInfo(this, CODE, 16, null, null);
        field = new FieldInfo(this, COMPANY_NAME, 30, null, null);
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
        field = new FieldInfo(this, CHANGED_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COMMENTS, 32000, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PASSWORD, 16, null, null);
        field = new FieldInfo(this, NAME_SORT, 6, null, null);
        field = new FieldInfo(this, POSTAL_CODE_SORT, 5, null, null);
        field = new FieldInfo(this, CONTACT, 30, null, null);
        field = new FieldInfo(this, LOGO, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
    }

}
