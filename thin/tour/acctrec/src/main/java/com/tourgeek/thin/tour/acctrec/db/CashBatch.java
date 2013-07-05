/**
  * @(#)CashBatch.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.acctrec.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.thin.tour.assetdr.db.*;
import com.tourgeek.model.tour.acctrec.db.*;

public class CashBatch extends BankTrxBatch
    implements CashBatchModel
{
    private static final long serialVersionUID = 1L;


    public CashBatch()
    {
        super();
    }
    public CashBatch(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String CASH_BATCH_FILE = "CashBatch";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? CashBatch.CASH_BATCH_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "acctrec";
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
        field = new FieldInfo(this, USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, BANK_ACCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DETAIL_DATE, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, BATCH_CHECKS, 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, BATCH_CHECKS_ACTUAL, 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, BATCH_TOTAL, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, BATCH_TOTAL_ACTUAL, 10, null, null);
        field.setDataClass(Double.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, USER_ID_KEY);
        keyArea.addKeyField(USER_ID, Constants.ASCENDING);
    }

}
