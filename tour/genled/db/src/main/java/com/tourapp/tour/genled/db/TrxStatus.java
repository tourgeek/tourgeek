/**
  * @(#)TrxStatus.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.model.tour.genled.db.*;

/**
 *  TrxStatus - Transaction type.
 */
public class TrxStatus extends VirtualRecord
     implements TrxStatusModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public TrxStatus()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TrxStatus(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(TRX_STATUS_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Transaction status";
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
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(TRX_STATUS_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = Record.makeNewScreen(TRX_STATUS_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
            field = new StringField(this, STATUS_CODE, 20, null, null);
        if (iFieldSeq == 4)
            field = new StringField(this, STATUS_DESC, 30, null, null);
        if (iFieldSeq == 5)
            field = new PreferredSignField(this, PREFERRED_SIGN, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
        {
            field = new TrxDescField(this, TRX_DESC_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == 7)
            field = new StringField(this, DESC_CODE, 20, null, null);
        if (iFieldSeq == 8)
            field = new TrxSystemField(this, TRX_SYSTEM_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new StringField(this, SYSTEM_CODE, 20, null, null);
        if (iFieldSeq == 10)
            field = new BooleanField(this, ACTIVE_TRX, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, TRX_DESC_ID_KEY);
            keyArea.addKeyField(TRX_DESC_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(STATUS_DESC, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, SYSTEM_CODE_KEY);
            keyArea.addKeyField(SYSTEM_CODE, DBConstants.ASCENDING);
            keyArea.addKeyField(DESC_CODE, DBConstants.ASCENDING);
            keyArea.addKeyField(STATUS_CODE, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Get the status record that matches this template.
     */
    public int getTrxStatusID(String strSystemCode, String strDescCode, String strStatusCode)
    {
        int iTrxStatusID = 0;
        this.setKeyArea(TrxStatus.SYSTEM_CODE_KEY);
        this.getField(TrxStatus.SYSTEM_CODE).setString(strSystemCode);
        this.getField(TrxStatus.DESC_CODE).setString(strDescCode);
        this.getField(TrxStatus.STATUS_CODE).setString(strStatusCode);
        try   {
            boolean bSuccess = this.seek("=");
            if (bSuccess)
                iTrxStatusID = (int)this.getField(TrxStatus.ID).getValue();
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return iTrxStatusID;
    }

}
