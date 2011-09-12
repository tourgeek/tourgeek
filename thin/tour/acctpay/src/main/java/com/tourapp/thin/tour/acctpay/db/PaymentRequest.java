/**
 * @(#)PaymentRequest.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.acctpay.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class PaymentRequest extends FieldList
{

    public PaymentRequest()
    {
        super();
    }
    public PaymentRequest(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String PAYMENT_REQUEST_FILE = "PaymentRequest";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? PaymentRequest.PAYMENT_REQUEST_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.LOCAL;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "BankAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "VendorID", 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Amount", 12, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "TrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "AccountID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CheckNo", 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Comments", 30, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "BankAcctID");
        keyArea.addKeyField("BankAcctID", Constants.ASCENDING);
        keyArea.addKeyField("VendorID", Constants.ASCENDING);
    }

}
