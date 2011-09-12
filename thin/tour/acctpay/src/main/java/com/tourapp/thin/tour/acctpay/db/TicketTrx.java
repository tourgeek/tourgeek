/**
 * @(#)TicketTrx.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.acctpay.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class TicketTrx extends com.tourapp.thin.tour.acctpay.db.ApTrx
{

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
