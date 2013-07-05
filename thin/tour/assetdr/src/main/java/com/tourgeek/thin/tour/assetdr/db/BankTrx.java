/**
  * @(#)BankTrx.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.assetdr.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.thin.tour.genled.db.*;
import com.tourgeek.model.tour.assetdr.db.*;

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
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, TRX_DATE_KEY);
        keyArea.addKeyField(BANK_ACCT_ID, Constants.ASCENDING);
        keyArea.addKeyField(TRX_DATE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, TRX_CLASS_KEY);
        keyArea.addKeyField(BANK_ACCT_ID, Constants.ASCENDING);
        keyArea.addKeyField(TRX_STATUS_ID, Constants.ASCENDING);
        keyArea.addKeyField(TRX_DATE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, INV_BALANCE_KEY);
        keyArea.addKeyField(BANK_ACCT_ID, Constants.ASCENDING);
        keyArea.addKeyField(INV_SIGN, Constants.ASCENDING);
        keyArea.addKeyField(TRX_DATE, Constants.ASCENDING);
        keyArea.addKeyField(INV_BALANCE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, TRX_NUMBER_KEY);
        keyArea.addKeyField(BANK_ACCT_ID, Constants.ASCENDING);
        keyArea.addKeyField(TRX_NUMBER, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, PAYEE_ID_KEY);
        keyArea.addKeyField(PAYEE_ID, Constants.ASCENDING);
        keyArea.addKeyField(PAYEE_TRX_DESC_ID, Constants.ASCENDING);
    }

}
