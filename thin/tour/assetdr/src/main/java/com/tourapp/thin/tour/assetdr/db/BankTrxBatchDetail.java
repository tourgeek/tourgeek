/**
 *  @(#)BankTrxBatchDetail.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.assetdr.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class BankTrxBatchDetail extends com.tourapp.thin.tour.assetdr.db.BankTrx
{

    public BankTrxBatchDetail()
    {
        super();
    }
    public BankTrxBatchDetail(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BANK_TRX_BATCH_DETAIL_FILE = "BankTrxBatchDetail";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? BankTrxBatchDetail.BANK_TRX_BATCH_DETAIL_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.LOCAL | Constants.USER_DATA;
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
        field = new FieldInfo(this, "TrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TrxDate", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "AmountLocal", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "TrxEntry", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "UserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "BankAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TrxNumber", 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "EFTTrxNo", 20, null, null);
        field = new FieldInfo(this, "PayeeTrxDescID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PayeeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PayeeName", 30, null, null);
        field = new FieldInfo(this, "Amount", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Exchange", 10, null, null);
        field.setDataClass(Double.class);
        field.setScale(-1);
        field = new FieldInfo(this, "InvSign", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, "InvBalance", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "InvBalanceLocal", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Comments", 30, null, null);
        field = new FieldInfo(this, "DateReconciled", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "Manual", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "BankTrxBatchID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "DistributionDisplay", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TrxDate");
        keyArea.addKeyField("BankAcctID", Constants.ASCENDING);
        keyArea.addKeyField("TrxDate", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TrxStatus");
        keyArea.addKeyField("BankAcctID", Constants.ASCENDING);
        keyArea.addKeyField("TrxStatusID", Constants.ASCENDING);
        keyArea.addKeyField("TrxDate", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "InvBalance");
        keyArea.addKeyField("BankAcctID", Constants.ASCENDING);
        keyArea.addKeyField("InvSign", Constants.ASCENDING);
        keyArea.addKeyField("TrxDate", Constants.ASCENDING);
        keyArea.addKeyField("InvBalance", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "BankTrxBatchID");
        keyArea.addKeyField("BankTrxBatchID", Constants.ASCENDING);
    }

}
