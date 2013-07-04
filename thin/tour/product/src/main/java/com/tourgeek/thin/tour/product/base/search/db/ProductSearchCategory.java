
package com.tourgeek.thin.tour.product.base.search.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.product.base.search.db.*;

public class ProductSearchCategory extends FieldList
    implements ProductSearchCategoryModel
{
    private static final long serialVersionUID = 1L;


    public ProductSearchCategory()
    {
        super();
    }
    public ProductSearchCategory(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String PRODUCT_SEARCH_CATEGORY_FILE = "ProductSearchCategory";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? ProductSearchCategory.PRODUCT_SEARCH_CATEGORY_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.LOCAL;
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
        field = new FieldInfo(this, PRODUCT_SEARCH_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DESCRIPTION, 30, null, null);
        field = new FieldInfo(this, VALUE, 10, null, null);
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
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, PRODUCT_SEARCH_TYPE_ID_KEY);
        keyArea.addKeyField(PRODUCT_SEARCH_TYPE_ID, Constants.ASCENDING);
        keyArea.addKeyField(DESCRIPTION, Constants.ASCENDING);
    }

}
