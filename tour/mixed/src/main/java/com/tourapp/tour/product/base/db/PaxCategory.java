/**
 * @(#)PaxCategory.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.model.tour.product.base.db.*;

/**
 *  PaxCategory - Passenger price category.
 */
public class PaxCategory extends VirtualRecord
     implements PaxCategoryModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDescription = kVirtualRecordLastField + 1;
    public static final int kRoomType = kDescription + 1;
    public static final int kBasedPaxCatID = kRoomType + 1;
    public static final int kPaxCategoryLastField = kBasedPaxCatID;
    public static final int kPaxCategoryFields = kBasedPaxCatID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDescriptionKey = kIDKey + 1;
    public static final int kPaxCategoryLastKey = kDescriptionKey;
    public static final int kPaxCategoryKeys = kDescriptionKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kPaxCategoryFile = "PaxCategory";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kPaxCategoryFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", 1, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 20, null, null);
        if (iFieldSeq == kRoomType)
        {
            field = new RoomTypeField(this, "RoomType", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kBasedPaxCatID)
            field = new PaxCategorySelect(this, "BasedPaxCatID", 2, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kPaxCategoryLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == kIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PrimaryKey");
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kDescriptionKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(kDescription, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kPaxCategoryLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kPaxCategoryLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * GetPaxInRoom Method.
     */
    public int getPaxInRoom()
    {
        int iRoomType = (int)this.getField(PaxCategory.kRoomType).getValue();
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
        this.setKeyArea(PaxCategory.kDescriptionKey);
        this.getField(PaxCategory.kDescription).setString(strPaxCategory);
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
