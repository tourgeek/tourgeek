/**
 *  @(#)TourHeaderOption.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.tour.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

public class TourHeaderOption extends FieldList
{

    public TourHeaderOption()
    {
        super();
    }
    public TourHeaderOption(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TOUR_HEADER_OPTION_FILE = "TourHeaderOption";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? TourHeaderOption.TOUR_HEADER_OPTION_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, "ID", 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "TourOrOption", 1, null, null);
        field = new FieldInfo(this, "TourOrOptionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Sequence", 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Description", 60, null, null);
        field = new FieldInfo(this, "Comments", 32000, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "AskForAnswer", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "AlwaysResolve", 1, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "StartDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "EndDate", 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, "DaysOfWeek", 5, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Gateway", 3, null, null);
        field = new FieldInfo(this, "UseTourHeaderOptionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PaxCategoryID", 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "DetailOptionCount", 10, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "DetailPriceCount", 10, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "DetailAirHeaderCount", 10, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "DetailTourDetailCount", 10, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TourClassID", 4, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "InvoiceText", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "ItineraryDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TourOrOption");
        keyArea.addKeyField("TourOrOption", Constants.ASCENDING);
        keyArea.addKeyField("TourOrOptionID", Constants.ASCENDING);
        keyArea.addKeyField("Sequence", Constants.ASCENDING);
    }

}
