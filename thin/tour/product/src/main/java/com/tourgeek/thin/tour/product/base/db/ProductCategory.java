
package com.tourgeek.thin.tour.product.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.product.base.db.*;

public class ProductCategory extends FieldList
    implements ProductCategoryModel
{
    private static final long serialVersionUID = 1L;


    public ProductCategory()
    {
        super();
    }
    public ProductCategory(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String PRODUCT_CATEGORY_FILE = "ProductCategory";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? ProductCategory.PRODUCT_CATEGORY_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.LOCAL | Constants.SHARED_DATA | Constants.LOCALIZABLE;
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
        field = new FieldInfo(this, DESCRIPTION, 30, null, null);
        field = new FieldInfo(this, INCOME_ACCOUNT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AR_ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PP_ACCOUNT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, XL_CHG_ACCOUNT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, LAND_ACCOUNT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, UNINV_ACCOUNT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COST_OU_ACCOUNT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AP_ACCOUNT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CURR_OU_ACCOUNT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AIR_ACCOUNT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PP_TIC_ACCOUNT_ID, 7, null, null);
        field.setDataClass(Integer.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, DESCRIPTION_KEY);
        keyArea.addKeyField(DESCRIPTION, Constants.ASCENDING);
    }

}
