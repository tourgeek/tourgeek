/**
 * @(#)TrxDesc.
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
 *  TrxDesc - Transaction type.
 */
public class TrxDesc extends VirtualRecord
     implements TrxDescModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDescCode = kVirtualRecordLastField + 1;
    public static final int kDescription = kDescCode + 1;
    public static final int kSourceFile = kDescription + 1;
    public static final int kTrxSystemID = kSourceFile + 1;
    public static final int kTrxDescLastField = kTrxSystemID;
    public static final int kTrxDescFields = kTrxSystemID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDescCodeKey = kIDKey + 1;
    public static final int kDescriptionKey = kDescCodeKey + 1;
    public static final int kTrxSystemIDKey = kDescriptionKey + 1;
    public static final int kSourceFileKey = kTrxSystemIDKey + 1;
    public static final int kTrxDescLastKey = kSourceFileKey;
    public static final int kTrxDescKeys = kSourceFileKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public TrxDesc()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TrxDesc(RecordOwner screen)
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

    public static final String kTrxDescFile = "TrxDesc";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTrxDescFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Transaction Description";
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
            screen = new TrxGroupGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == (ScreenConstants.DISPLAY_MODE | 4096))
            screen = new TrxStatusGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new TrxDescScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new TrxDescGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kDescCode)
            field = new StringField(this, "DescCode", 20, null, null);
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 30, null, null);
        if (iFieldSeq == kSourceFile)
            field = new StringField(this, "SourceFile", 50, null, null);
        if (iFieldSeq == kTrxSystemID)
        {
            field = new TrxSystemField(this, "TrxSystemID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTrxDescLastField)
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
        if (iKeyArea == kDescCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "DescCode");
            keyArea.addKeyField(kDescCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kDescriptionKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(kDescription, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTrxSystemIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxSystemID");
            keyArea.addKeyField(kTrxSystemID, DBConstants.ASCENDING);
            keyArea.addKeyField(kDescCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kSourceFileKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "SourceFile");
            keyArea.addKeyField(kSourceFile, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kTrxDescLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTrxDescLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Convert the command to the screen document type.
     * @param strCommand The command text.
     * @param The standard document type (MAINT/DISPLAY/SELECT/MENU/etc).
     */
    public int commandToDocType(String strCommand)
    {
        if ("Status".equalsIgnoreCase(strCommand))
            return ScreenConstants.DISPLAY_MODE | 4096;
        else
            return super.commandToDocType(strCommand);
    }
    /**
     * GetTrxDesc Method.
     */
    public TrxDesc getTrxDesc(String strSystemCode, String strDescCode)
    {
        TrxSystem recTrxSystem = new TrxSystem(this.findRecordOwner());
        // TODO - Move the SystemCode to this file.
        try {
            recTrxSystem.setKeyArea(TrxSystem.SYSTEM_CODE_KEY);
            recTrxSystem.getField(TrxSystem.SYSTEM_CODE).setString(strSystemCode);
            if (!recTrxSystem.seek("="))
                return null;    // System code not found;
            this.setKeyArea(TrxDesc.TRX_SYSTEM_ID_KEY);
            this.getField(TrxDesc.TRX_SYSTEM_ID).moveFieldToThis(recTrxSystem.getField(TrxSystem.ID));
            this.getField(TrxDesc.DESC_CODE).setString(strDescCode);
            if (!this.seek("="))
                return null;
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally {
            recTrxSystem.free();
        }
        return this;
    }
    /**
     * MakeSourceRecord Method.
     */
    public Record makeSourceRecord(RecordOwner recordOwner)
    {
        String strClassName = this.getField(TrxDesc.SOURCE_FILE).toString();
        return Record.makeRecordFromClassName(strClassName, recordOwner);
    }
    /**
     * MakeLinkTrxScreen Method.
     */
    public BaseScreen makeLinkTrxScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        Record record = this.makeSourceRecord(this.findRecordOwner());
        
        BaseScreen screen = (BaseScreen)record.makeScreen(itsLocation, parentScreen, iDocMode, properties);
        
        return screen;
    }

}
