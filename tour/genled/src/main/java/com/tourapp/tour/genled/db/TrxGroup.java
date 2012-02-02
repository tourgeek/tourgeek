/**
 * @(#)TrxGroup.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.genled.screen.trx.*;
import com.tourapp.model.tour.genled.db.*;

/**
 *  TrxGroup - Transaction type.
 */
public class TrxGroup extends VirtualRecord
     implements TrxGroupModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kGroupCode = kVirtualRecordLastField + 1;
    public static final int kGroupDesc = kGroupCode + 1;
    public static final int kTrxDescID = kGroupDesc + 1;
    public static final int kDescCode = kTrxDescID + 1;
    public static final int kTrxSystemID = kDescCode + 1;
    public static final int kSystemCode = kTrxSystemID + 1;
    public static final int kTrxGroupLastField = kSystemCode;
    public static final int kTrxGroupFields = kSystemCode - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kTrxDescIDKey = kIDKey + 1;
    public static final int kSystemCodeKey = kTrxDescIDKey + 1;
    public static final int kTrxGroupLastKey = kSystemCodeKey;
    public static final int kTrxGroupKeys = kSystemCodeKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public TrxGroup()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TrxGroup(RecordOwner screen)
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

    public static final String kTrxGroupFile = "TrxGroup";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTrxGroupFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Transaction Header";
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
        return DBConstants.LOCAL | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
    }
    /**
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == ScreenConstants.DETAIL_MODE)
            screen = new TrxTypeGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new TrxGroupScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new TrxGroupGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        return screen;
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
        if (iFieldSeq == kGroupCode)
            field = new StringField(this, "GroupCode", 20, null, null);
        if (iFieldSeq == kGroupDesc)
            field = new StringField(this, "GroupDesc", 30, null, null);
        if (iFieldSeq == kTrxDescID)
        {
            field = new TrxDescField(this, "TrxDescID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kDescCode)
            field = new StringField(this, "DescCode", 20, null, null);
        if (iFieldSeq == kTrxSystemID)
            field = new TrxSystemField(this, "TrxSystemID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSystemCode)
            field = new StringField(this, "SystemCode", 20, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTrxGroupLastField)
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
        if (iKeyArea == kTrxDescIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxDescID");
            keyArea.addKeyField(kTrxDescID, DBConstants.ASCENDING);
            keyArea.addKeyField(kGroupDesc, DBConstants.ASCENDING);
        }
        if (iKeyArea == kSystemCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "SystemCode");
            keyArea.addKeyField(kSystemCode, DBConstants.ASCENDING);
            keyArea.addKeyField(kDescCode, DBConstants.ASCENDING);
            keyArea.addKeyField(kGroupCode, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kTrxGroupLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTrxGroupLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Get the group that matches this description.
     * @param strSystemCode The system.
     * @param strDescCode The File.
     * @param strGroupCode The transaction code.
     * @return The group id (or -1 if not found).
     */
    public int getTrxGroupID(String strSystemCode, String strDescCode, String strGroupCode)
    {
        int iTrxGroupID = -1;
        this.setKeyArea(TrxGroup.SYSTEM_CODE_KEY);
        this.getField(TrxGroup.SYSTEM_CODE).setString(strSystemCode);
        this.getField(TrxGroup.DESC_CODE).setString(strDescCode);
        this.getField(TrxGroup.GROUP_CODE).setString(strGroupCode);
        try   {
            boolean bSuccess = this.seek("=");
            if (bSuccess)
                iTrxGroupID = (int)this.getField(TrxGroup.ID).getValue();
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return iTrxGroupID;
    }

}
