/**
 * @(#)BookingHistory.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.history.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class BookingHistory extends com.tourapp.thin.tour.booking.db.Booking
{

    public BookingHistory()
    {
        super();
    }
    public BookingHistory(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BOOKING_HISTORY_FILE = "BookingHistory";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? BookingHistory.BOOKING_HISTORY_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "history";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.REMOTE | Constants.USER_DATA | Constants.SERVER_REWRITES | Constants.DONT_LOG_TRX;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, "ID", 6, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "BookingDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "EmployeeID", 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ProfileID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ProfileCode", 16, null, null);
        field = new FieldInfo(this, "Code", 20, null, null);
        field = new FieldInfo(this, "Description", 50, null, null);
        field = new FieldInfo(this, "EmployeeModID", 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ModDate", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "BookingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "GenericName", 30, null, null);
        field = new FieldInfo(this, "AddressLine1", 40, null, null);
        field = new FieldInfo(this, "AddressLine2", 40, null, null);
        field = new FieldInfo(this, "CityOrTown", 15, null, null);
        field = new FieldInfo(this, "StateOrRegion", 15, null, null);
        field = new FieldInfo(this, "PostalCode", 10, null, null);
        field = new FieldInfo(this, "Country", 15, null, null);
        field = new FieldInfo(this, "Tel", 24, null, null);
        field = new FieldInfo(this, "Fax", 24, null, null);
        field = new FieldInfo(this, "Email", 40, null, null);
        field = new FieldInfo(this, "Contact", 25, null, null);
        field = new FieldInfo(this, "LanguageID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CurrencysID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Double.class);
        //field = new FieldInfo(this, "CurrencyCode", 3, null, null);
        field = new FieldInfo(this, "Exchange", 15, null, null);
        field.setDataClass(Double.class);
        field.setScale(-1);
        field = new FieldInfo(this, "Pax", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Focs", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "SinglePax", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "DoublePax", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "TriplePax", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "QuadPax", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Children", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Gateway", 3, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TourID", 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Markup", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "StdCommission", 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "AcceptDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "DepositRecDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "FinalPayRecDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "Booked", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "DepositReceived", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "DepositDue", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "FinalPaymentReceived", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "FinalPaymentDue", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Cancelled", 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Properties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "Deposit", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "DepositDueDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "FinalPaymentDueDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "NextEventDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "TourEventID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Gross", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Commission", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Net", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Balance", 18, null, null);
        field.setDataClass(Double.class);
        //field = new FieldInfo(this, "PricingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(PricingStatus.OKAY));
        //field.setDataClass(Integer.class);
        field = new FieldInfo(this, "AskForAnswer", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        //field = new FieldInfo(this, "AlwaysResolve", 10, null, new Boolean(false));
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "TourPricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "NonTourPricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "NextLineSequence", 5, null, new Short((short)1));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "CustSaleDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "CustSaleAgent", 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CustSaleCustID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CustSaleCustNo", 16, null, null);
        field = new FieldInfo(this, "HistoryDate", 25, null, null);
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
        keyArea.addKeyField("HistoryDate", Constants.ASCENDING);
    }
    /**
    * This is not an auto-counter record.
    */
    public boolean isAutoSequence()
    {
        return false;
    }

}
