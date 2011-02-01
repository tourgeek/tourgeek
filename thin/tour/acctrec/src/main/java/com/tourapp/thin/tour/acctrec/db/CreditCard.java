/**
 *  @(#)CreditCard.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.acctrec.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class CreditCard extends FieldList
{

    public CreditCard()
    {
        super();
    }
    public CreditCard(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String CREDIT_CARD_FILE = "CreditCard";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? CreditCard.CREDIT_CARD_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.REMOTE | Constants.USER_DATA;
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
        field = new FieldInfo(this, "AmtApply", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "TrxEntry", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "TrxUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Gross", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "SvcPer", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "SvcAmt", 8, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Net", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Comments", 30, null, null);
        field = new FieldInfo(this, "DateSubmitted", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "DatePaid", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "AmountPaid", 18, null, null);
        field.setDataClass(Double.class);
        //field = new FieldInfo(this, "Paid", 10, null, null);
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "PaymentEntered", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "CardID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CardNo", 20, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "Expiration", 5, null, null);
        field = new FieldInfo(this, "DateApproved", 25, null, null);
        field.setDataClass(Date.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TrxStatusID");
        keyArea.addKeyField("TrxStatusID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TrxDate");
        keyArea.addKeyField("TrxDate", Constants.ASCENDING);
    }

}
