/**
 * @(#)BankAcct.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.assetdr.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class BankAcct extends FieldList
{

    public BankAcct()
    {
        super();
    }
    public BankAcct(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BANK_ACCT_FILE = "BankAcct";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? BankAcct.BANK_ACCT_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "assetdr";
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
        field = new FieldInfo(this, "ID", 2, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Description", 30, null, null);
        field = new FieldInfo(this, "CurrencyID", 3, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "EFTRouting", 10, null, null);
        field = new FieldInfo(this, "BankABA", 10, null, null);
        field = new FieldInfo(this, "BankAcctNo", 20, null, null);
        field = new FieldInfo(this, "AccountID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "NextCheck", 8, null, new Integer(0));
        field.setDataClass(Integer.class);
        //field = new FieldInfo(this, "Balance", 18, null, null);
        //field.setDataClass(Double.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "Description");
        keyArea.addKeyField("Description", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "CurrencyID");
        keyArea.addKeyField("CurrencyID", Constants.ASCENDING);
    }

}
