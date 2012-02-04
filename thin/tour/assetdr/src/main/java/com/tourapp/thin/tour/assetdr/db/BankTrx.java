/**
 * @(#)BankTrx.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.assetdr.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.genled.db.*;
import com.tourapp.model.tour.assetdr.db.*;

public class BankTrx extends BaseTrx
    implements BankTrxModel
{
    private static final long serialVersionUID = 1L;


    public BankTrx()
    {
        super();
    }
    public BankTrx(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BANK_TRX_FILE = "BankTrx";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? BankTrx.BANK_TRX_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, BANK_ACCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TRX_NUMBER, 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, EFT_TRX_NO, 20, null, null);
        field = new FieldInfo(this, PAYEE_TRX_DESC_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PAYEE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PAYEE_NAME, 30, null, null);
        field = new FieldInfo(this, AMOUNT, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, EXCHANGE, 10, null, null);
        field.setDataClass(Double.class);
        field.setScale(-1);
        field = new FieldInfo(this, INV_SIGN, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, INV_BALANCE, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, INV_BALANCE_LOCAL, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, COMMENTS, 30, null, null);
        field = new FieldInfo(this, DATE_RECONCILED, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, MANUAL, 10, null, null);
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
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TrxDate");
        keyArea.addKeyField("BankAcctID", Constants.ASCENDING);
        keyArea.addKeyField("TrxDate", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TrxClass");
        keyArea.addKeyField("BankAcctID", Constants.ASCENDING);
        keyArea.addKeyField("TrxStatusID", Constants.ASCENDING);
        keyArea.addKeyField("TrxDate", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "InvBalance");
        keyArea.addKeyField("BankAcctID", Constants.ASCENDING);
        keyArea.addKeyField("InvSign", Constants.ASCENDING);
        keyArea.addKeyField("TrxDate", Constants.ASCENDING);
        keyArea.addKeyField("InvBalance", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TrxNumber");
        keyArea.addKeyField("BankAcctID", Constants.ASCENDING);
        keyArea.addKeyField("TrxNumber", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "PayeeID");
        keyArea.addKeyField("PayeeID", Constants.ASCENDING);
        keyArea.addKeyField("PayeeTrxDescID", Constants.ASCENDING);
    }

}
