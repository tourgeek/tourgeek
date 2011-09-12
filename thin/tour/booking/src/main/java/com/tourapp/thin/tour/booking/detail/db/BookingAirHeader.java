/**
 * @(#)BookingAirHeader.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.detail.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class BookingAirHeader extends FieldList
{

    public BookingAirHeader()
    {
        super();
    }
    public BookingAirHeader(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BOOKING_AIR_HEADER_FILE = "BookingAirHeader";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? BookingAirHeader.BOOKING_AIR_HEADER_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "booking";
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
        field = new FieldInfo(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "BookingPaxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ModuleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TourHeaderDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TourHeaderOptionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ModuleStartDate", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "Description", 60, null, null);
        //field = new FieldInfo(this, "ProductType", 15, null, null);
        field = new FieldInfo(this, "RemoteReferenceNo", 60, null, null);
        field = new FieldInfo(this, "ProductDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "QuesAnswID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "TicketTrxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TicketNumber", 28, null, null);
        field = new FieldInfo(this, "AirlineCode", 2, null, null);
        field = new FieldInfo(this, "AirlineIATA", 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "AirlineDesc", 16, null, null);
        field = new FieldInfo(this, "Conjunction", 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Endorsements", 29, null, null);
        field = new FieldInfo(this, "OriginDest", 13, null, null);
        field = new FieldInfo(this, "BookingReference", 13, null, null);
        field = new FieldInfo(this, "IssueDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "PaxName", 29, null, null);
        field = new FieldInfo(this, "FormOfPayment", 41, null, null);
        field = new FieldInfo(this, "TourCode", 14, null, null);
        field = new FieldInfo(this, "TotalFareBasis", 1, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Fare", 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Equivalent", 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "CurrencyCode", 3, null, null);
        field = new FieldInfo(this, "TaxPercent", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Tax1", 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Tax1Desc", 2, null, null);
        field = new FieldInfo(this, "Tax2", 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Tax2Desc", 2, null, null);
        field = new FieldInfo(this, "Total", 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Commission", 10, null, "      10   ");
        field = new FieldInfo(this, "Tax", 10, null, "      8   ");
        field = new FieldInfo(this, "CommissionRate", 5, null, "  10 ");
        field = new FieldInfo(this, "Agent", 10, null, " AGENT");
        field = new FieldInfo(this, "International", 3, null, "X/");
        field = new FieldInfo(this, "CommPercent", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "CommAmount", 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "TicketBy", 1, null, "U");
        field = new FieldInfo(this, "NetFare", 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "OverridePercent", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "OverrideAmount", 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "NetCost", 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Void", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "VoidDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "ExchTicket", 20, null, null);
        field = new FieldInfo(this, "DepDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "Smoker", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Credit", 1, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Comment1", 20, null, null);
        field = new FieldInfo(this, "Comment2", 20, null, null);
        field = new FieldInfo(this, "Comment3", 20, null, null);
        field = new FieldInfo(this, "FreqFlier", 20, null, null);
        field = new FieldInfo(this, "Fare1", 60, null, null);
        field = new FieldInfo(this, "Fare2", 60, null, null);
        field = new FieldInfo(this, "Fare3", 60, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "DetailAccess");
        keyArea.addKeyField("BookingID", Constants.ASCENDING);
        keyArea.addKeyField("BookingPaxID", Constants.ASCENDING);
        keyArea.addKeyField("ModuleID", Constants.ASCENDING);
        keyArea.addKeyField("TourHeaderDetailID", Constants.ASCENDING);
        keyArea.addKeyField("ModuleStartDate", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "BookingID");
        keyArea.addKeyField("BookingID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TicketNumber");
        keyArea.addKeyField("TicketNumber", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "DepDate");
        keyArea.addKeyField("DepDate", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "IssueDate");
        keyArea.addKeyField("IssueDate", Constants.ASCENDING);
    }

}
