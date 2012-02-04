/**
 * @(#)AcctBatch.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.genled.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.genled.db.*;

public class AcctBatch extends FieldList
    implements AcctBatchModel
{
    private static final long serialVersionUID = 1L;


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
        field = new FieldInfo(this, ID, 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, RECURRING, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, TRX_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, COMMENTS, 30, null, null);
        field = new FieldInfo(this, SOURCE, 10, null, null);
        field = new FieldInfo(this, BALANCE, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, NEXT_SEQUENCE, 5, null, new Short((short)1));
        field.setDataClass(Short.class);
        //field = new FieldInfo(this, AUTO_REVERSAL, 10, null, null);
        //field.setDataClass(Boolean.class);
        //field = new FieldInfo(this, AUTO_REVERSAL_DATE, 12, null, null);
        //field.setDataClass(Date.class);
        //field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, AUTO_CLOSING, 10, null, null);
        field.setDataClass(Boolean.class);
        //field = new FieldInfo(this, TRX_ENTRY, 25, null, null);
        //field.setDataClass(Date.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "UserID");
        keyArea.addKeyField("UserID", Constants.ASCENDING);
        keyArea.addKeyField("Recurring", Constants.ASCENDING);
    }

}
