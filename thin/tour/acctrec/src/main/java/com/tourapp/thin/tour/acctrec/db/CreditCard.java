/**
 * @(#)CreditCard.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.acctrec.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.acctrec.db.*;
import com.tourapp.model.tour.acctrec.db.*;

public class CreditCard extends BaseArPay
    implements CreditCardModel
{
    private static final long serialVersionUID = 1L;


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
        field = new FieldInfo(this, AMT_APPLY, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, TRX_ENTRY, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, BOOKING_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, GROSS, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, SVC_PER, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, SVC_AMT, 8, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, NET, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, COMMENTS, 30, null, null);
        field = new FieldInfo(this, DATE_SUBMITTED, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, DATE_PAID, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, AMOUNT_PAID, 18, null, null);
        field.setDataClass(Double.class);
        //field = new FieldInfo(this, PAID, 10, null, null);
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, PAYMENT_ENTERED, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, CARD_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CARD_NO, 20, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, EXPIRATION, 5, null, null);
        field = new FieldInfo(this, DATE_APPROVED, 25, null, null);
        field.setDataClass(Date.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TrxStatusID");
        keyArea.addKeyField("TrxStatusID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TrxDate");
        keyArea.addKeyField("TrxDate", Constants.ASCENDING);
    }

}
