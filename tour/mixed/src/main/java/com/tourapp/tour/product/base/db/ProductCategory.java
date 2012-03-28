/**
 * @(#)ProductCategory.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.db;

import java.awt.*;
import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.model.tour.product.base.db.*;

/**
 *  ProductCategory - Tour Product Category.
 */
public class ProductCategory extends VirtualRecord
     implements ProductCategoryModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public ProductCategory()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductCategory(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(PRODUCT_CATEGORY_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Product Category";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "product";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
        {
            field = new CounterField(this, ID, 8, null, null);
            field.setHidden(true);
        }
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (iFieldSeq == 3)
            field = new StringField(this, DESCRIPTION, 30, null, null);
        if (iFieldSeq == 4)
            field = new AccountField(this, INCOME_ACCOUNT_ID, 7, null, null);
        if (iFieldSeq == 5)
            field = new AccountField(this, AR_ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new AccountField(this, PP_ACCOUNT_ID, 7, null, null);
        if (iFieldSeq == 7)
            field = new AccountField(this, XL_CHG_ACCOUNT_ID, 7, null, null);
        if (iFieldSeq == 8)
            field = new AccountField(this, LAND_ACCOUNT_ID, 7, null, null);
        if (iFieldSeq == 9)
            field = new AccountField(this, UNINV_ACCOUNT_ID, 7, null, null);
        if (iFieldSeq == 10)
            field = new AccountField(this, COST_OU_ACCOUNT_ID, 7, null, null);
        if (iFieldSeq == 11)
            field = new AccountField(this, AP_ACCOUNT_ID, 7, null, null);
        if (iFieldSeq == 12)
            field = new AccountField(this, CURR_OU_ACCOUNT_ID, 7, null, null);
        if (iFieldSeq == 13)
            field = new AccountField(this, AIR_ACCOUNT_ID, 7, null, null);
        if (iFieldSeq == 14)
            field = new AccountField(this, PP_TIC_ACCOUNT_ID, 7, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ID");
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(DESCRIPTION, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }

}
