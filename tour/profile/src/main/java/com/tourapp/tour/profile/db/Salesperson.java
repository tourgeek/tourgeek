/**
 * @(#)Salesperson.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.profile.db;

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
import org.jbundle.main.db.base.*;
import com.tourapp.model.tour.profile.db.*;

/**
 *  Salesperson - Salesperson.
 */
public class Salesperson extends VirtualRecord
     implements SalespersonModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kSalespersonName = kVirtualRecordLastField + 1;
    public static final int kVendorID = kSalespersonName + 1;
    public static final int kSalespersonLastField = kVendorID;
    public static final int kSalespersonFields = kVendorID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kSalespersonNameKey = kIDKey + 1;
    public static final int kSalespersonLastKey = kSalespersonNameKey;
    public static final int kSalespersonKeys = kSalespersonNameKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public Salesperson()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Salesperson(RecordOwner screen)
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

    public static final String kSalespersonFile = "Salesperson";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kSalespersonFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Salesperson";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "profile";
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
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", 8, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kSalespersonName)
            field = new StringField(this, "SalespersonName", 30, null, null);
        if (iFieldSeq == kVendorID)
            field = new ContactField(this, "VendorID", 8, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kSalespersonLastField)
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
        if (iKeyArea == kSalespersonNameKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "SalespersonName");
            keyArea.addKeyField(kSalespersonName, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kSalespersonLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kSalespersonLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
