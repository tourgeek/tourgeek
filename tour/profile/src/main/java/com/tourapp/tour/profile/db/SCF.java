/**
 * @(#)SCF.
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
import com.tourapp.model.tour.profile.db.*;

/**
 *  SCF - SCF Control.
 */
public class SCF extends VirtualRecord
     implements SCFModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kScfFrom = kVirtualRecordLastField + 1;
    public static final int kScfTo = kScfFrom + 1;
    public static final int kDescription = kScfTo + 1;
    public static final int kSalespersonID = kDescription + 1;
    public static final int kSalesRegionID = kSalespersonID + 1;
    public static final int kUpsZone = kSalesRegionID + 1;
    public static final int kZipZone = kUpsZone + 1;
    public static final int kSCFLastField = kZipZone;
    public static final int kSCFFields = kZipZone - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kScfToKey = kIDKey + 1;
    public static final int kSCFLastKey = kScfToKey;
    public static final int kSCFKeys = kScfToKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public SCF()
    {
        super();
    }
    /**
     * Constructor.
     */
    public SCF(RecordOwner screen)
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

    public static final String kSCFFile = "SCF";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kSCFFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "SCF";
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
        return DBConstants.LOCAL | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
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
        if (iFieldSeq == kScfFrom)
        {
            field = new StringField(this, "ScfFrom", 3, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kScfTo)
        {
            field = new StringField(this, "ScfTo", 3, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 25, null, null);
        if (iFieldSeq == kSalespersonID)
        {
            field = new SalespersonField(this, "SalespersonID", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSalesRegionID)
        {
            field = new SalesRegionField(this, "SalesRegionID", 4, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kUpsZone)
        {
            field = new StringField(this, "UpsZone", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kZipZone)
        {
            field = new StringField(this, "ZipZone", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kSCFLastField)
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
        if (iKeyArea == kScfToKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ScfTo");
            keyArea.addKeyField(kScfTo, DBConstants.ASCENDING);
            keyArea.addKeyField(kScfFrom, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kSCFLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kSCFLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        this.getField(SCF.kScfFrom).addListener(new ScfToHandler(null));
        this.getField(SCF.kScfFrom).addListener(new ScfFromHandler(null));
        this.getField(SCF.kScfTo).addListener(new ScfToHandler(null));
    }
    /**
     * Get the default key index for grid screens and popup displays.
     * The default key area for grid screens is the first non-unique key that is a string.
     * Override this to supply a different key area.
     * @return The key area to use for screens and popups.
     */
    public int getDefaultScreenKeyArea()
    {
        return SCF.kScfToKey;
    }

}
