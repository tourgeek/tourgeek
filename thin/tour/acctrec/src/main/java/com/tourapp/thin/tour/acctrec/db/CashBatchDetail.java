/**
 * @(#)CashBatchDetail.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.acctrec.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.acctrec.db.*;

public class CashBatchDetail extends FieldList
    implements CashBatchDetailModel
{
    private static final long serialVersionUID = 1L;


    public CashBatchDetail()
    {
        super();
    }
    public CashBatchDetail(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String CASH_BATCH_DETAIL_FILE = "CashBatchDetail";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? CashBatchDetail.CASH_BATCH_DETAIL_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, CASH_BATCH_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, BOOKING_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CHECK_NO, 8, null, null);
        field = new FieldInfo(this, CHECK_ABA, 10, null, null);
        field = new FieldInfo(this, AMOUNT, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, COMMENTS, 30, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "CashBatchID");
        keyArea.addKeyField("CashBatchID", Constants.ASCENDING);
        keyArea.addKeyField("ID", Constants.ASCENDING);
    }

}
