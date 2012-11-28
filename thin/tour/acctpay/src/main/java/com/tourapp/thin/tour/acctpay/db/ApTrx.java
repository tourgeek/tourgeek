/**
  * @(#)ApTrx.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.acctpay.db;

import com.tourapp.model.tour.product.base.db.*;
import com.tourapp.model.tour.booking.detail.db.*;
import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.genled.db.*;
import com.tourapp.model.tour.acctpay.db.*;

public class ApTrx extends Trx
    implements ApTrxModel
{
    private static final long serialVersionUID = 1L;


    public ApTrx()
    {
        super();
    }
    public ApTrx(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String AP_TRX_FILE = "ApTrx";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? ApTrx.AP_TRX_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.REMOTE | Constants.BASE_TABLE_CLASS | Constants.SHARED_TABLE | Constants.USER_DATA;
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
        field = new FieldInfo(this, CODE, 28, null, null);
        field = new FieldInfo(this, DESCRIPTION, 50, null, null);
        field = new FieldInfo(this, AP_TRX_TYPE_ID, 10, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ACTIVE_TRX, 10, null, new Boolean(true));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TOUR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PRODUCT_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, START_SERVICE_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, END_SERVICE_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, FINALIZATION_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, ORDER_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, ACKNOWLEDGE_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, ACKNOWLEDGED_ON, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, ACKNOWLEDGED_BY, 16, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, VENDOR_CONFIRMATION_NO, 20, null, null);
        field = new FieldInfo(this, VENDOR_CONF_STATUS, 2, null, null);
        field = new FieldInfo(this, DEPARTURE_ESTIMATE, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, DEPARTURE_EXCHANGE, 10, null, null);
        field.setDataClass(Double.class);
        field.setScale(-1);
        field = new FieldInfo(this, DEPARTURE_ESTIMATE_LOCAL, 10, null, null);
        field.setDataClass(Double.class);
        //field = new FieldInfo(this, DEPARTURE_DATE, 12, null, null);
        //field.setDataClass(Date.class);
        //field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, INVOICE_NO, 28, null, null);
        field = new FieldInfo(this, INVOICE_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, INVOICE_AMOUNT, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, INVOICE_LOCAL, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, INVOICE_BALANCE, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, INVOICE_BALANCE_LOCAL, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, AMOUNT_SELECTED, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, DRAFT_VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PREPAYMENT_AP_TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, VOUCHER_BASED_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, TRX_ENTRY, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TICKET_NUMBER, 28, null, null);
        field = new FieldInfo(this, ISSUE_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, AIRLINE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ARC_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, ARC_PAY, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, INTL, 1, null, null);
        field = new FieldInfo(this, CREDIT_CARD, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, TOTAL, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, FARE, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, COMM_AMOUNT, 9, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, COMM_PERCENT, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TAX_AMOUNT, 9, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, TAX_PERCENT, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, NET_FARE, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, COST_AMOUNT, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, OVERRIDE_PERCENT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, OVERRIDE_AMOUNT, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, OVERRIDE_PAID_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, OVERRIDE_PAID, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, VOID_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, CODE_KEY);
        keyArea.addKeyField(CODE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, VENDOR_ID_KEY);
        keyArea.addKeyField(VENDOR_ID, Constants.ASCENDING);
        keyArea.addKeyField(ACTIVE_TRX, Constants.ASCENDING);
        keyArea.addKeyField(START_SERVICE_DATE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, TOUR_ID_KEY);
        keyArea.addKeyField(TOUR_ID, Constants.ASCENDING);
        keyArea.addKeyField(VENDOR_ID, Constants.ASCENDING);
        keyArea.addKeyField(PRODUCT_TYPE_ID, Constants.ASCENDING);
    }

}
