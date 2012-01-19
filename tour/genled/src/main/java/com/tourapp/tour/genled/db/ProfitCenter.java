/**
 * @(#)ProfitCenter.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.db;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.model.tour.genled.db.*;

/**
 *  ProfitCenter - Profit Centers.
 */
public class ProfitCenter extends VirtualRecord
     implements ProfitCenterModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kProfitCenterNo = kVirtualRecordLastField + 1;
    public static final int kDescription = kProfitCenterNo + 1;
    public static final int kProfitCenterLastField = kDescription;
    public static final int kProfitCenterFields = kDescription - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDescriptionKey = kIDKey + 1;
    public static final int kProfitCenterNoKey = kDescriptionKey + 1;
    public static final int kProfitCenterLastKey = kProfitCenterNoKey;
    public static final int kProfitCenterKeys = kProfitCenterNoKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public ProfitCenter()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProfitCenter(RecordOwner screen)
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

    public static final String kProfitCenterFile = "ProfitCenter";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kProfitCenterFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Profit center";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "genled";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kProfitCenterNo)
            field = new ShortField(this, "ProfitCenterNo", 3, null, null);
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 20, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kProfitCenterLastField)
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
        if (iKeyArea == kProfitCenterNoKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "ProfitCenterNo");
            keyArea.addKeyField(kProfitCenterNo, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kProfitCenterLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kProfitCenterLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
