/**
 * @(#)Vendor.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.acctpay.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import org.jbundle.thin.main.db.*;
import com.tourapp.model.tour.acctpay.db.*;

public class Vendor extends Company
    implements VendorModel
{

    public Vendor()
    {
        super();
    }
    public Vendor(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String VENDOR_FILE = "Vendor";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Vendor.VENDOR_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, "ID", 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Code", 16, null, null);
        field = new FieldInfo(this, "VendorName", 30, null, null);
        field = new FieldInfo(this, "AddressLine1", 40, null, null);
        field = new FieldInfo(this, "AddressLine2", 40, null, null);
        field = new FieldInfo(this, "CityOrTown", 15, null, null);
        field = new FieldInfo(this, "StateOrRegion", 15, null, null);
        field = new FieldInfo(this, "PostalCode", 10, null, null);
        field = new FieldInfo(this, "Country", 15, null, null);
        field = new FieldInfo(this, "Tel", 24, null, null);
        field = new FieldInfo(this, "Fax", 24, null, null);
        field = new FieldInfo(this, "Email", 40, null, null);
        field = new FieldInfo(this, "Web", 60, null, null);
        field = new FieldInfo(this, "DateEntered", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "DateChanged", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "ChangedID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Comments", 32000, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "UserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Password", 16, null, null);
        field = new FieldInfo(this, "NameSort", 6, null, null);
        field = new FieldInfo(this, "PostalCodeSort", 5, null, null);
        field = new FieldInfo(this, "Contact", 30, null, null);
        field = new FieldInfo(this, "ContactTitle", 30, null, null);
        field = new FieldInfo(this, "CountryID", 2, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CurrencysID", 3, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "MessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        //field = new FieldInfo(this, "MessageServer", 255, null, null);
        //field = new FieldInfo(this, "WSDLPath", 255, null, null);
        field = new FieldInfo(this, "Properties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "VendorStatusID", 1, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PaymentCycleID", 1, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PaymentCodeID", 1, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PrepayTypeID", 1, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "DepositID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "OperationTypeCode", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, "DefaultAccountID", 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Send1099", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "TaxId", 11, null, "");
        field = new FieldInfo(this, "VendorsCustNo", 20, null, null);
        //field = new FieldInfo(this, "AmountSelected", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "VendorBalance", 18, null, null);
        //field.setDataClass(Double.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, "Code");
        keyArea.addKeyField("Code", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "NameSort");
        keyArea.addKeyField("NameSort", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "CurrencysID");
        keyArea.addKeyField("CurrencysID", Constants.ASCENDING);
    }

}
