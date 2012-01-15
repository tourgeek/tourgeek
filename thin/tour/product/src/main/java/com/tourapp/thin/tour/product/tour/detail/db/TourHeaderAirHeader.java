/**
 * @(#)TourHeaderAirHeader.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.tour.detail.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.product.tour.detail.db.*;
import com.tourapp.model.tour.product.tour.detail.db.*;

public class TourHeaderAirHeader extends TourSub
    implements TourHeaderAirHeaderModel
{

    public TourHeaderAirHeader()
    {
        super();
    }
    public TourHeaderAirHeader(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TOUR_HEADER_AIR_HEADER_FILE = "TourHeaderAirHeader";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "product";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.LOCAL | Constants.USER_DATA;
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
        field = new FieldInfo(this, "TourHeaderOptionID", 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ModifyCode", 1, null, null);
        field = new FieldInfo(this, "ModifyID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "AirlineCode", 2, null, null);
        field = new FieldInfo(this, "AirlineIATA", 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "AirlineDesc", 16, null, null);
        field = new FieldInfo(this, "Conjunction", 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Endorsements", 29, null, null);
        field = new FieldInfo(this, "OriginDest", 13, null, null);
        field = new FieldInfo(this, "BookingReference", 13, null, null);
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
        //field = new FieldInfo(this, "TkOrColl", 9, null, null);
        //field.setDataClass(Float.class);
        //field = new FieldInfo(this, "ARCCost", 9, null, null);
        //field.setDataClass(Float.class);
        field = new FieldInfo(this, "PNR", 15, null, null);
        field = new FieldInfo(this, "Void", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "VoidDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "ExchTicket", 20, null, null);
        field = new FieldInfo(this, "DepDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "Credit", 1, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Comment1", 20, null, null);
        field = new FieldInfo(this, "Comment2", 20, null, null);
        field = new FieldInfo(this, "Comment3", 20, null, null);
        field = new FieldInfo(this, "CRSConf", 20, null, null);
        field = new FieldInfo(this, "CRSStatus", 2, null, null);
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
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TourHeaderOptionID");
        keyArea.addKeyField("TourHeaderOptionID", Constants.ASCENDING);
    }

}
