/**
 * @(#)TourHeaderLine.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.tour.detail.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.product.tour.detail.db.*;
import com.tourapp.model.tour.product.tour.detail.db.*;

public class TourHeaderLine extends TourSub
    implements TourHeaderLineModel
{
    private static final long serialVersionUID = 1L;


    public TourHeaderLine()
    {
        super();
    }
    public TourHeaderLine(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TOUR_HEADER_LINE_FILE = "TourHeaderLine";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? TourHeaderLine.TOUR_HEADER_LINE_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, SEQUENCE, 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, PAX_CATEGORY_ID, 1, null, new Integer(6) /* PaxCategory.ALL_ID */);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DESCRIPTION, 45, null, null);
        field = new FieldInfo(this, PRICE, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, COMMISSIONABLE, 1, null, new Boolean(true));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, COMMISSION_RATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, PAY_AT, 1, null, null);
        field = new FieldInfo(this, COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, PRODUCT_TERMS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
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
        keyArea.addKeyField("PaxCategoryID", Constants.ASCENDING);
        keyArea.addKeyField("Sequence", Constants.ASCENDING);
    }

}
