/**
 * @(#)OTACode.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.ota.db;

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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.model.tour.product.base.ota.db.*;

/**
 *  OTACode - This is the virtual table that maps to an actual OTA code table.
 */
public class OTACode extends Record
     implements OTACodeModel
{
    private static final long serialVersionUID = 1L;

    public static final int kID = kRecordLastField + 1;
    public static final int kName = kID + 1;
    public static final int kOTACodeLastField = kName;
    public static final int kOTACodeFields = kName - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kNameKey = kIDKey + 1;
    public static final int kOTACodeLastKey = kNameKey;
    public static final int kOTACodeKeys = kNameKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public OTACode()
    {
        super();
    }
    /**
     * Constructor.
     */
    public OTACode(RecordOwner screen)
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
        return DBConstants.TABLE | DBConstants.MAPPED;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kID)
            field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kName)
            field = new StringField(this, "Name", 60, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kOTACodeLastField)
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
        if (iKeyArea == kNameKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Name");
            keyArea.addKeyField(kName, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kOTACodeLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kOTACodeLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Create the filter and apply it to this/a record and record the record to traverse.
     * @return The record to traverse (with filters applied).
     */
    public Record applyMappedFilter()
    {
        Record recOTACodes = new OTACodes(this.getRecordOwner());
        Record recOTACodeTable = ((ReferenceField)recOTACodes.getField(OTACodes.kOTACodeTableID)).getReferenceRecord();
        recOTACodeTable.setKeyArea(OTACodeTable.kNameCodeKey);
        recOTACodes.addListener(new SubFileFilter(recOTACodeTable));
        recOTACodes.addListener(new CompareFileFilter(OTACodes.kDeletionDate, (String)null, FileListener.EQUALS, null, false));
        recOTACodeTable.getField(OTACodeTable.kNameCode).setString(this.getTableNames(false));
        try {
            if (!recOTACodeTable.seek(DBConstants.EQUALS))
                return null;    // Error!
        } catch (DBException e) {
            recOTACodes.free();      // Frees both      
            e.printStackTrace();
            return null;
        }
        return recOTACodes;
    }
    /**
     * Move the data in this record to the thin version.
     * @param fieldList.
     */
    public void moveDataToThin(Record record, FieldList fieldList)
    {
        if (!record.getField(OTACodes.kValue).isNull())
            fieldList.getField(record.getField(OTACode.kID).getFieldName()).setString(record.getField(OTACodes.kValue).toString());
        this.moveFieldToThin(fieldList.getField(record.getField(OTACodes.kName).getFieldName()), null, record);
    }
    /**
     * Free this mapped record.
     * @param record The record to free.
     */
    public void freeMappedRecord(Record record)
    {
        record.free();      // Frees both
    }

}
