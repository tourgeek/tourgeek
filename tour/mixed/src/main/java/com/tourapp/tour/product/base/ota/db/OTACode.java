/**
 * @(#)OTACode.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.model.tour.product.base.ota.db.*;

/**
 *  OTACode - This is the virtual table that maps to an actual OTA code table.
 */
public class OTACode extends VirtualRecord
     implements OTACodeModel
{
    private static final long serialVersionUID = 1L;

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
            field = new StringField(this, NAME, 60, null, null);
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
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Name");
            keyArea.addKeyField(NAME, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Create the filter and apply it to this/a record and record the record to traverse.
     * @return The record to traverse (with filters applied).
     */
    public Record applyMappedFilter()
    {
        Record recOTACodes = new OTACodes(this.getRecordOwner());
        Record recOTACodeTable = ((ReferenceField)recOTACodes.getField(OTACodes.OTA_CODE_TABLE_ID)).getReferenceRecord();
        recOTACodeTable.setKeyArea(OTACodeTable.NAME_CODE_KEY);
        recOTACodes.addListener(new SubFileFilter(recOTACodeTable));
        recOTACodes.addListener(new CompareFileFilter(OTACodes.DELETION_DATE, (String)null, FileListener.EQUALS, null, false));
        recOTACodeTable.getField(OTACodeTable.NAME_CODE).setString(this.getTableNames(false));
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
        if (!record.getField(OTACodes.VALUE).isNull())
            fieldList.getField(record.getField(OTACode.ID).getFieldName()).setString(record.getField(OTACodes.VALUE).toString());
        this.moveFieldToThin(fieldList.getField(record.getField(OTACodes.NAME).getFieldName()), null, record);
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
