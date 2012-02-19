/**
 * @(#)TourHeaderOption.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.tour.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.product.tour.db.*;

public class TourHeaderOption extends FieldList
    implements TourHeaderOptionModel
{
    private static final long serialVersionUID = 1L;


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
        field = new FieldInfo(this, ID, 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, TOUR_OR_OPTION, 1, null, null);
        field = new FieldInfo(this, TOUR_OR_OPTION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SEQUENCE, 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, DESCRIPTION, 60, null, null);
        field = new FieldInfo(this, COMMENTS, 32000, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, ASK_FOR_ANSWER, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, ALWAYS_RESOLVE, 1, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, START_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, END_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, DAYS_OF_WEEK, 5, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, GATEWAY, 3, null, null);
        field = new FieldInfo(this, USE_TOUR_HEADER_OPTION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PAX_CATEGORY_ID, 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DETAIL_OPTION_COUNT, 10, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DETAIL_PRICE_COUNT, 10, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DETAIL_AIR_HEADER_COUNT, 10, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DETAIL_TOUR_DETAIL_COUNT, 10, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TOUR_CLASS_ID, 4, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, INVOICE_TEXT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, ITINERARY_DESC, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "ID");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "TourOrOption");
        keyArea.addKeyField("TourOrOption", Constants.ASCENDING);
        keyArea.addKeyField("TourOrOptionID", Constants.ASCENDING);
        keyArea.addKeyField("Sequence", Constants.ASCENDING);
    }

}
