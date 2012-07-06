/**
  * @(#)Vendor.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
    private static final long serialVersionUID = 1L;


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
        field = new FieldInfo(this, ID, 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, CODE, 16, null, null);
        field = new FieldInfo(this, VENDOR_NAME, 30, null, null);
        field = new FieldInfo(this, ADDRESS_LINE_1, 40, null, null);
        field = new FieldInfo(this, ADDRESS_LINE_2, 40, null, null);
        field = new FieldInfo(this, CITY_OR_TOWN, 15, null, null);
        field = new FieldInfo(this, STATE_OR_REGION, 15, null, null);
        field = new FieldInfo(this, POSTAL_CODE, 10, null, null);
        field = new FieldInfo(this, COUNTRY, 15, null, null);
        field = new FieldInfo(this, TEL, 24, null, null);
        field = new FieldInfo(this, FAX, 24, null, null);
        field = new FieldInfo(this, EMAIL, 40, null, null);
        field = new FieldInfo(this, WEB, 60, null, null);
        field = new FieldInfo(this, DATE_ENTERED, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, DATE_CHANGED, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, CHANGED_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COMMENTS, 32000, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PASSWORD, 16, null, null);
        field = new FieldInfo(this, NAME_SORT, 6, null, null);
        field = new FieldInfo(this, POSTAL_CODE_SORT, 5, null, null);
        field = new FieldInfo(this, CONTACT, 30, null, null);
        field = new FieldInfo(this, CONTACT_TITLE, 30, null, null);
        field = new FieldInfo(this, COUNTRY_ID, 2, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CURRENCYS_ID, 3, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        //field = new FieldInfo(this, MESSAGE_SERVER, 255, null, null);
        //field = new FieldInfo(this, WSDL_PATH, 255, null, null);
        field = new FieldInfo(this, PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, VENDOR_STATUS_ID, 1, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PAYMENT_CYCLE_ID, 1, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PAYMENT_CODE_ID, 1, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PREPAY_TYPE_ID, 1, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DEPOSIT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, OPERATION_TYPE_CODE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, DEFAULT_ACCOUNT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SEND_1099, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, TAX_ID, 11, null, "");
        field = new FieldInfo(this, VENDORS_CUST_NO, 20, null, null);
        //field = new FieldInfo(this, AMOUNT_SELECTED, 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, VENDOR_BALANCE, 18, null, null);
        //field.setDataClass(Double.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, "Code");
        keyArea.addKeyField("Code", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "NameSort");
        keyArea.addKeyField("NameSort", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "CurrencysID");
        keyArea.addKeyField("CurrencysID", Constants.ASCENDING);
    }

}
