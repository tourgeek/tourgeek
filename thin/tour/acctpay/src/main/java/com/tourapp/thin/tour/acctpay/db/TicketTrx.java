/**
 * @(#)TicketTrx.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.acctpay.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.acctpay.db.*;
import com.tourapp.model.tour.acctpay.db.*;

public class TicketTrx extends ApTrx
    implements TicketTrxModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String TRX_STATUS_ID = TRX_STATUS_ID;
    //public static final String CODE = CODE;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String AP_TRX_TYPE_ID = AP_TRX_TYPE_ID;
    //public static final String TRX_USER_ID = TRX_USER_ID;
    //public static final String ACTIVE_TRX = ACTIVE_TRX;
    //public static final String VENDOR_ID = VENDOR_ID;
    //public static final String TOUR_ID = TOUR_ID;
    //public static final String PRODUCT_TYPE_ID = PRODUCT_TYPE_ID;
    //public static final String START_SERVICE_DATE = START_SERVICE_DATE;
    //public static final String END_SERVICE_DATE = END_SERVICE_DATE;
    //public static final String FINALIZATION_DATE = FINALIZATION_DATE;
    //public static final String ORDER_DATE = ORDER_DATE;
    //public static final String ACKNOWLEDGE_DATE = ACKNOWLEDGE_DATE;
    //public static final String ACKNOWLEDGED_ON = ACKNOWLEDGED_ON;
    //public static final String ACKNOWLEDGED_BY = ACKNOWLEDGED_BY;
    //public static final String VENDOR_CONFIRMATION_NO = VENDOR_CONFIRMATION_NO;
    //public static final String VENDOR_CONF_STATUS = VENDOR_CONF_STATUS;
    //public static final String DEPARTURE_ESTIMATE = DEPARTURE_ESTIMATE;
    //public static final String DEPARTURE_EXCHANGE = DEPARTURE_EXCHANGE;
    //public static final String DEPARTURE_ESTIMATE_LOCAL = DEPARTURE_ESTIMATE_LOCAL;
    //public static final String DEPARTURE_DATE = DEPARTURE_DATE;
    //public static final String INVOICE_NO = INVOICE_NO;
    //public static final String INVOICE_DATE = INVOICE_DATE;
    //public static final String INVOICE_AMOUNT = INVOICE_AMOUNT;
    //public static final String INVOICE_LOCAL = INVOICE_LOCAL;
    //public static final String INVOICE_BALANCE = INVOICE_BALANCE;
    //public static final String INVOICE_BALANCE_LOCAL = INVOICE_BALANCE_LOCAL;
    //public static final String AMOUNT_SELECTED = AMOUNT_SELECTED;
    //public static final String DRAFT_VENDOR_ID = DRAFT_VENDOR_ID;
    //public static final String PREPAYMENT_AP_TRX_ID = PREPAYMENT_AP_TRX_ID;
    //public static final String VOUCHER_BASED_DATE = VOUCHER_BASED_DATE;
    //public static final String TRX_ENTRY = TRX_ENTRY;
    //public static final String ACCOUNT_ID = ACCOUNT_ID;
    //public static final String TICKET_NUMBER = TICKET_NUMBER;
    //public static final String ISSUE_DATE = ISSUE_DATE;
    //public static final String AIRLINE_ID = AIRLINE_ID;
    //public static final String ARC_DATE = ARC_DATE;
    //public static final String ARC_PAY = ARC_PAY;
    //public static final String INTL = INTL;
    //public static final String CREDIT_CARD = CREDIT_CARD;
    //public static final String TOTAL = TOTAL;
    //public static final String FARE = FARE;
    //public static final String COMM_AMOUNT = COMM_AMOUNT;
    //public static final String COMM_PERCENT = COMM_PERCENT;
    //public static final String TAX_AMOUNT = TAX_AMOUNT;
    //public static final String TAX_PERCENT = TAX_PERCENT;
    //public static final String NET_FARE = NET_FARE;
    //public static final String COST_AMOUNT = COST_AMOUNT;
    //public static final String OVERRIDE_PERCENT = OVERRIDE_PERCENT;
    //public static final String OVERRIDE_AMOUNT = OVERRIDE_AMOUNT;
    //public static final String OVERRIDE_PAID_DATE = OVERRIDE_PAID_DATE;
    //public static final String OVERRIDE_PAID = OVERRIDE_PAID;
    //public static final String VOID_DATE = VOID_DATE;

    public TicketTrx()
    {
        super();
    }
    public TicketTrx(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TICKET_TRX_FILE = "ApTrx";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? TicketTrx.TICKET_TRX_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.REMOTE | Constants.SHARED_TABLE | Constants.USER_DATA;
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
        field = new FieldInfo(this, "Code", 28, null, null);
        field = new FieldInfo(this, "Description", 50, null, null);
        field = new FieldInfo(this, "ApTrxTypeID", 10, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TrxUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "ActiveTrx", 10, null, new Boolean(true));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TourID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ProductTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "StartServiceDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "EndServiceDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "FinalizationDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "OrderDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "AcknowledgeDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "AcknowledgedOn", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "AcknowledgedBy", 16, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "VendorConfirmationNo", 20, null, null);
        field = new FieldInfo(this, "VendorConfStatus", 2, null, null);
        field = new FieldInfo(this, "DepartureEstimate", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "DepartureExchange", 10, null, null);
        field.setDataClass(Double.class);
        field.setScale(-1);
        field = new FieldInfo(this, "DepartureEstimateLocal", 10, null, null);
        field.setDataClass(Double.class);
        //field = new FieldInfo(this, "DepartureDate", 12, null, null);
        //field.setDataClass(Date.class);
        //field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "InvoiceNo", 28, null, null);
        field = new FieldInfo(this, "InvoiceDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "InvoiceAmount", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "InvoiceLocal", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "InvoiceBalance", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "InvoiceBalanceLocal", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "AmountSelected", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "DraftVendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PrepaymentApTrxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "VoucherBasedDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "TrxEntry", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "AccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TicketNumber", 28, null, null);
        field = new FieldInfo(this, "IssueDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ArcDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "ArcPay", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "Intl", 1, null, null);
        field = new FieldInfo(this, "CreditCard", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Total", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Fare", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "CommAmount", 9, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "CommPercent", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "TaxAmount", 9, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "TaxPercent", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "NetFare", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "CostAmount", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "OverridePercent", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "OverrideAmount", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "OverridePaidDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "OverridePaid", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "VoidDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
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
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "VendorID");
        keyArea.addKeyField("VendorID", Constants.ASCENDING);
        keyArea.addKeyField("ActiveTrx", Constants.ASCENDING);
        keyArea.addKeyField("StartServiceDate", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TourID");
        keyArea.addKeyField("TourID", Constants.ASCENDING);
        keyArea.addKeyField("VendorID", Constants.ASCENDING);
        keyArea.addKeyField("ProductTypeID", Constants.ASCENDING);
    }

}
