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
        field = new FieldInfo(this, "Sequence", 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "PaxCategoryID", 1, null, new Integer(6) /* PaxCategory.ALL_ID */);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Description", 45, null, null);
        field = new FieldInfo(this, "Price", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Commissionable", 1, null, new Boolean(true));
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "CommissionRate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "PayAt", 1, null, null);
        field = new FieldInfo(this, "Cost", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "ProductTermsID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
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
        keyArea.addKeyField("PaxCategoryID", Constants.ASCENDING);
        keyArea.addKeyField("Sequence", Constants.ASCENDING);
    }

}
