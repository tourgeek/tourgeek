/**
 * @(#)PaymentHistory.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.acctpay.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.genled.db.*;
import com.tourapp.model.tour.acctpay.db.*;

public class PaymentHistory extends LinkTrx
    implements PaymentHistoryModel
{
    private static final long serialVersionUID = 1L;


    public PaymentHistory()
    {
        super();
    }
    public PaymentHistory(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String PAYMENT_HISTORY_FILE = "PaymentHistory";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? PaymentHistory.PAYMENT_HISTORY_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "acctpay";
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
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, TRX_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TRX_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TRX_DATE, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, AMOUNT_LOCAL, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, TRX_ENTRY, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, LINKED_TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, LINKED_TRX_DESC_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AP_TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AMOUNT_APPLIED, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, CURR_LOSS_LOCAL, 18, null, null);
        field.setDataClass(Double.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "LinkedTrxID");
        keyArea.addKeyField("LinkedTrxID", Constants.ASCENDING);
        keyArea.addKeyField("LinkedTrxDescID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "ApTrxID");
        keyArea.addKeyField("ApTrxID", Constants.ASCENDING);
        keyArea.addKeyField("TrxDate", Constants.ASCENDING);
    }

}
