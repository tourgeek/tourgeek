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
    private static final long serialVersionUID = 1L;


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
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, TOUR_HEADER_OPTION_ID, 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MODIFY_CODE, 1, null, null);
        field = new FieldInfo(this, MODIFY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AIRLINE_CODE, 2, null, null);
        field = new FieldInfo(this, AIRLINE_IATA, 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, AIRLINE_DESC, 16, null, null);
        field = new FieldInfo(this, CONJUNCTION, 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ENDORSEMENTS, 29, null, null);
        field = new FieldInfo(this, ORIGIN_DEST, 13, null, null);
        field = new FieldInfo(this, BOOKING_REFERENCE, 13, null, null);
        field = new FieldInfo(this, TOUR_CODE, 14, null, null);
        field = new FieldInfo(this, TOTAL_FARE_BASIS, 1, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, FARE, 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, EQUIVALENT, 8, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, CURRENCY_CODE, 3, null, null);
        field = new FieldInfo(this, TAX_PERCENT, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TAX_1, 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TAX_1_DESC, 2, null, null);
        field = new FieldInfo(this, TAX_2, 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TAX_2_DESC, 2, null, null);
        field = new FieldInfo(this, TOTAL, 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, COMMISSION, 10, null, "      10   ");
        field = new FieldInfo(this, TAX, 10, null, "      8   ");
        field = new FieldInfo(this, COMMISSION_RATE, 5, null, "  10 ");
        field = new FieldInfo(this, AGENT, 10, null, " AGENT");
        field = new FieldInfo(this, INTERNATIONAL, 3, null, "X/");
        field = new FieldInfo(this, COMM_PERCENT, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, COMM_AMOUNT, 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, TICKET_BY, 1, null, "U");
        field = new FieldInfo(this, NET_FARE, 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, OVERRIDE_PERCENT, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, OVERRIDE_AMOUNT, 9, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, NET_COST, 9, null, null);
        field.setDataClass(Float.class);
        //field = new FieldInfo(this, TK_OR_COLL, 9, null, null);
        //field.setDataClass(Float.class);
        //field = new FieldInfo(this, ARC_COST, 9, null, null);
        //field.setDataClass(Float.class);
        field = new FieldInfo(this, PNR, 15, null, null);
        field = new FieldInfo(this, VOID, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, VOID_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, EXCH_TICKET, 20, null, null);
        field = new FieldInfo(this, DEP_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, CREDIT, 1, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, COMMENT_1, 20, null, null);
        field = new FieldInfo(this, COMMENT_2, 20, null, null);
        field = new FieldInfo(this, COMMENT_3, 20, null, null);
        field = new FieldInfo(this, CRS_CONF, 20, null, null);
        field = new FieldInfo(this, CRS_STATUS, 2, null, null);
        field = new FieldInfo(this, FREQ_FLIER, 20, null, null);
        field = new FieldInfo(this, FARE_1, 60, null, null);
        field = new FieldInfo(this, FARE_2, 60, null, null);
        field = new FieldInfo(this, FARE_3, 60, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TourHeaderOptionID");
        keyArea.addKeyField("TourHeaderOptionID", Constants.ASCENDING);
    }

}
