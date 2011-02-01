/**
 *  @(#)AcctBatch.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.genled.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class AcctBatch extends FieldList
{

    public AcctBatch()
    {
        super();
    }
    public AcctBatch(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String ACCT_BATCH_FILE = "AcctBatch";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? AcctBatch.ACCT_BATCH_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, "ID", 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "UserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Recurring", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "TrxDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "Comments", 30, null, null);
        field = new FieldInfo(this, "Source", 10, null, null);
        field = new FieldInfo(this, "Balance", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "NextSequence", 5, null, new Short((short)1));
        field.setDataClass(Short.class);
        //field = new FieldInfo(this, "AutoReversal", 10, null, null);
        //field.setDataClass(Boolean.class);
        //field = new FieldInfo(this, "AutoReversalDate", 12, null, null);
        //field.setDataClass(Date.class);
        //field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "AutoClosing", 10, null, null);
        field.setDataClass(Boolean.class);
        //field = new FieldInfo(this, "TrxEntry", 25, null, null);
        //field.setDataClass(Date.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "UserID");
        keyArea.addKeyField("UserID", Constants.ASCENDING);
        keyArea.addKeyField("Recurring", Constants.ASCENDING);
    }

}
