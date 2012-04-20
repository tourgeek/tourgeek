/**
 * @(#)ItemInventory.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.inventory.db;

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
import org.jbundle.base.db.shared.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.item.db.*;
import com.tourapp.model.tour.booking.inventory.db.*;

/**
 *  ItemInventory - Inventory file.
 */
public class ItemInventory extends Inventory
     implements ItemInventoryModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public ItemInventory()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ItemInventory(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(ITEM_INVENTORY_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "booking";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.REMOTE | DBConstants.SHARED_TABLE | DBConstants.USER_DATA;
    }
    /**
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(ITEM_INVENTORY_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(ITEM_INVENTORY_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(ITEM_INVENTORY_RANGE_ADJUST_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, 10, null, null);
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
        {
            field = new ProductTypeField(this, PRODUCT_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == 4)
        {
            field = new ItemField(this, PRODUCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == 5)
        {
            field = new ReferenceField(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(Inventory.NO_RATE));
            field.setNullable(false);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 6)
        {
            field = new ReferenceField(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(Inventory.NO_CLASS));
            field.setNullable(false);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 7)
        {
            field = new ReferenceField(this, OTHER_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(Inventory.NO_OTHER));
            field.setNullable(false);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == 8)
        //  field = new DateField(this, INV_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 9)
        //{
        //  field = new ShortField(this, BLOCKED, 3, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 10)
        //  field = new ShortField(this, USED, 3, null, new Short((short)0));
        //if (iFieldSeq == 11)
        //  field = new ShortField(this, AVAILABLE, 3, null, null);
        //if (iFieldSeq == 12)
        //  field = new ShortField(this, OVERSELL, 3, null, null);
        //if (iFieldSeq == 13)
        //  field = new BooleanField(this, CLOSED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add all standard file & field behaviors.
     * Override this to add record listeners and filters.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.addListener(new SharedFileHandler(Inventory.PRODUCT_TYPE_ID, ProductType.ITEM_ID));
    }

}
