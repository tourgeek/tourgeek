/**
 * @(#)Account.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.genled.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.genled.db.*;

public class Account extends FieldList
    implements AccountModel
{
    private static final long serialVersionUID = 1L;


    public Account()
    {
        super();
    }
    public Account(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String ACCOUNT_FILE = "Account";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Account.ACCOUNT_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, DESCRIPTION, 30, null, null);
        field = new FieldInfo(this, ACCOUNT_NO, 10, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TYPICAL_BALANCE, 1, null, "D");
        field = new FieldInfo(this, SECTION_SUB_TOTAL, 1, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, COUNTER_ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AUTO_DIST_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CLOSE_YEAR_END, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, DISCONTINUED, 1, null, null);
        field.setDataClass(Boolean.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, "AccountNo");
        keyArea.addKeyField("AccountNo", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "Description");
        keyArea.addKeyField("Description", Constants.ASCENDING);
    }

}
