/**
 * @(#)Mco.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.acctrec.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.acctrec.db.*;
import com.tourapp.model.tour.acctrec.db.*;

public class Mco extends BaseArPay
    implements McoModel
{

    public Mco()
    {
        super();
    }
    public Mco(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String MCO_FILE = "Mco";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Mco.MCO_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, "McoNo", 16, null, null);
        field = new FieldInfo(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CommPer", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "CommAmt", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "TaxPer", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "TaxAmt", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "CarrierSvcPer", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Float.class);
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
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "McoNo");
        keyArea.addKeyField("McoNo", Constants.ASCENDING);
    }

}
