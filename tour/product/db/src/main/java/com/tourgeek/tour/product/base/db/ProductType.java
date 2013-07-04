
package com.tourgeek.tour.product.base.db;

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
import com.tourgeek.model.tour.product.base.db.*;

/**
 *  ProductType - Product Type.
 */
public class ProductType extends VirtualRecord
     implements ProductTypeModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public ProductType()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductType(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(PRODUCT_TYPE_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Product Type";
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
        return DBConstants.TABLE | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
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
            field = new StringField(this, CODE, 1, null, null);
        if (iFieldSeq == 5)
            field = new ImageField(this, ICON, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, CODE_KEY);
            keyArea.addKeyField(CODE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, DESCRIPTION_KEY);
            keyArea.addKeyField(DESCRIPTION, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Get the product type of this product class.
     */
    public int getProductTypeID(Product product)
    {
        this.close();
        String string = product.getTableNames(false);
        if (string.equals("TourHeader"))
            string = "Tour";
        return this.getProductTypeIDFromName(string);
    }
    /**
     * GetProductTypeIDFromName Method.
     */
    public int getProductTypeIDFromName(String string)
    {
        boolean bSuccess = false;
        if (this.getEditMode() == DBConstants.EDIT_CURRENT)
            if (string.equals(this.getField(ProductType.DESCRIPTION).toString()))
                bSuccess = true;    // Already current
        this.getField(ProductType.DESCRIPTION).setString(string);
        this.setKeyArea(ProductType.DESCRIPTION_KEY);
        try   {
            if (bSuccess == false)
                bSuccess = this.seek("=");
        } catch (DBException ex)    {
            bSuccess = false;
        }
        if (bSuccess)
            return (int)this.getField(ProductType.ID).getValue();
        else
            return -1;  // Not found???
    }
    /**
     * SetupProductTypeCheckboxes Method.
     */
    public void setupProductTypeCheckboxes(ScreenParent screen, Record record, String strFieldSuffix)
    {
        try {
            this.close();
            while (this.hasNext())
            {
                this.next();
                BaseField field = record.getField(this.getField(ProductType.DESCRIPTION).toString() + strFieldSuffix);
                if (field != null)
                    BaseField.createScreenComponent(ScreenModel.CHECK_BOX, screen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), screen, field, ScreenConstants.DEFAULT_DISPLAY, null);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

}
