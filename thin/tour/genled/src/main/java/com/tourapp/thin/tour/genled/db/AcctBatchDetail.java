/**
  * @(#)AcctBatchDetail.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.genled.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.genled.db.*;

public class AcctBatchDetail extends FieldList
    implements AcctBatchDetailModel
{
    private static final long serialVersionUID = 1L;


    public AcctBatchDetail()
    {
        super();
    }
    public AcctBatchDetail(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String ACCT_BATCH_DETAIL_FILE = "AcctBatchDetail";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? AcctBatchDetail.ACCT_BATCH_DETAIL_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, ACCT_BATCH_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SEQUENCE, 5, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AMOUNT, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, COUNTER_BALANCE, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, AUTO_DIST, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, AUTO_ACCRUAL, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, AUTO_REVERSAL, 10, null, null);
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
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "AcctBatchID");
        keyArea.addKeyField("AcctBatchID", Constants.ASCENDING);
        keyArea.addKeyField("Sequence", Constants.ASCENDING);
        keyArea.addKeyField("ID", Constants.ASCENDING);
    }

}
