/**
 * @(#)PaxCategory.
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
import com.tourapp.model.tour.product.base.db.*;

/**
 *  PaxCategory - Passenger price category.
 */
public class PaxCategory extends VirtualRecord
     implements PaxCategoryModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public PaxCategory()
    {
        super();
    }
    /**
     * Constructor.
     */
    public PaxCategory(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(PAX_CATEGORY_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Passenger category";
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
        if (iFieldSeq == 0)
        {
            field = new CounterField(this, ID, 1, null, null);
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
            field = new StringField(this, DESCRIPTION, 20, null, null);
        if (iFieldSeq == 4)
        {
            field = new RoomTypeField(this, ROOM_TYPE, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 5)
            field = new PaxCategorySelect(this, BASED_PAX_CAT_ID, 2, null, null);
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
    /**
     * GetPaxInRoom Method.
     */
    public int getPaxInRoom()
    {
        int iRoomType = (int)this.getField(PaxCategory.ROOM_TYPE).getValue();
        if ((iRoomType >= PaxCategory.SINGLE_ID)
            && (iRoomType <= PaxCategory.QUAD_ID))
                return iRoomType;
        return 1;
    }
    /**
     * Convert this description to an ID.
     */
    public String convertNameToID(String strPaxCategory)
    {
        int iOldKeyOrder = this.getDefaultOrder();
        this.setKeyArea(PaxCategory.DESCRIPTION_KEY);
        this.getField(PaxCategory.DESCRIPTION).setString(strPaxCategory);
        try {
            if (this.seek(null))
                return this.getCounterField().toString();
        } catch (DBException ex) {
            ex.printStackTrace();
        } finally {
            this.setKeyArea(iOldKeyOrder);
        }
        return null;
    }

}
