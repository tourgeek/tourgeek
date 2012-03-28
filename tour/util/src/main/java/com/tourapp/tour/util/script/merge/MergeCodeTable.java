/**
 * @(#)MergeCodeTable.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.util.script.merge;

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
import org.jbundle.app.program.script.data.importfix.base.*;
import org.jbundle.thin.base.screen.*;
import com.tourapp.tour.product.base.ota.db.*;

/**
 *  MergeCodeTable - .
 */
public class MergeCodeTable extends MergeData
{
    public static final String SOURCE = "sourceField";
    public static final String DEST = "destField";
    /**
     * Default constructor.
     */
    public MergeCodeTable()
    {
        super();
    }
    /**
     * Constructor.
     */
    public MergeCodeTable(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
    {
        this();
        this.init(taskParent, recordMain, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwnerParent taskParent, Record recordMain, Map<String, Object> properties)
    {
        super.init(taskParent, recordMain, properties);
    }
    /**
     * OpenMergeRecord Method.
     */
    public Record openMergeRecord()
    {
        String code = this.getProperty("code");
        if (code != null)
        {    // Always
            Record record = new OTACode();
            record.setTableNames(code);
            record.init(this);
            return record;
        }
        return super.openMergeRecord();
    }
    /**
     * Given this source record, read the destination record.
     * @param recSource The source record
     * @param recDest The destination record
     * @return True if found.
     */
    public boolean readDestRecord(FieldList recSource, Record recDest)
    {
        String sourceKeyField = this.getProperty("sourceKeyField");
        String destKeyField = this.getProperty("destKeyField");
        if ((sourceKeyField != null) && (destKeyField != null))
        {
            FieldInfo fldPrimary = recSource.getField(sourceKeyField);
            BaseField fldSecond = recDest.getField(destKeyField);
            if ((fldSecond == null) || (fldPrimary == null))
                return false;
            recDest.setKeyArea(fldSecond);
            fldSecond.moveFieldToThis(fldPrimary);
            try {
                if (recDest.seek(DBConstants.EQUALS))
                    return true;
            } catch (DBException e) {
                e.printStackTrace();
            }
            return false;   // Must be a new record
        }
        return super.readDestRecord(recSource, recDest);
    }
    /**
     * Merge this source record with the destination record.
     * @param recSource
     * @param recDest.
     */
    public void mergeSourceData(Record recSource, Record recDest, boolean bFound)
    {
        // First, move same fields
        for (int i = 0; i < recDest.getFieldCount(); i++)
        {
            if (recSource.getField(recDest.getField(i).getFieldName()) != null)
                if (recDest.getField(i).isNull())
                    recDest.getField(i).moveFieldToThis(recSource.getField(recDest.getField(i).getFieldName()));
        }
        // Now move explicitly specified fields
        for (int i = 1; ; i++)
        {
            String source = this.getProperty(SOURCE + Integer.toString(i));
            String dest = this.getProperty(DEST + Integer.toString(i));
            if ((source == null) || (dest == null))
                break;  // Done
            if (recDest.getField(dest) != null)
                if (recDest.getField(dest).isNull())
                    if (recSource.getField(source) != null)
                        recDest.getField(dest).moveFieldToThis(recSource.getField(source));
        }
    }

}
