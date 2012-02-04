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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(TRX_GROUP_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
            field = new StringField(this, GROUP_CODE, 20, null, null);
        if (iFieldSeq == 4)
            field = new StringField(this, GROUP_DESC, 30, null, null);
        if (iFieldSeq == 5)
        {
            field = new TrxDescField(this, TRX_DESC_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == 6)
            field = new StringField(this, DESC_CODE, 20, null, null);
        if (iFieldSeq == 7)
            field = new TrxSystemField(this, TRX_SYSTEM_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new StringField(this, SYSTEM_CODE, 20, null, null);
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
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxDescID");
            keyArea.addKeyField(TRX_DESC_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(GROUP_DESC, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "SystemCode");
            keyArea.addKeyField(SYSTEM_CODE, DBConstants.ASCENDING);
            keyArea.addKeyField(DESC_CODE, DBConstants.ASCENDING);
            keyArea.addKeyField(GROUP_CODE, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
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
