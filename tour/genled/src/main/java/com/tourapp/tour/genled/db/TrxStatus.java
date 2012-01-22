/**
 * @(#)TrxStatus.
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
 *  TrxStatus - Transaction type.
 */
public class TrxStatus extends VirtualRecord
     implements TrxStatusModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kStatusCode = kVirtualRecordLastField + 1;
    public static final int kStatusDesc = kStatusCode + 1;
    public static final int kPreferredSign = kStatusDesc + 1;
    public static final int kTrxDescID = kPreferredSign + 1;
    public static final int kDescCode = kTrxDescID + 1;
    public static final int kTrxSystemID = kDescCode + 1;
    public static final int kSystemCode = kTrxSystemID + 1;
    public static final int kActiveTrx = kSystemCode + 1;
    public static final int kTrxStatusLastField = kActiveTrx;
    public static final int kTrxStatusFields = kActiveTrx - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kTrxDescIDKey = kIDKey + 1;
    public static final int kSystemCodeKey = kTrxDescIDKey + 1;
    public static final int kTrxStatusLastKey = kSystemCodeKey;
    public static final int kTrxStatusKeys = kSystemCodeKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kTrxStatusFile = "TrxStatus";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTrxStatusFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
            screen = new TrxStatusScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new TrxStatusGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kStatusCode)
            field = new StringField(this, "StatusCode", 20, null, null);
        if (iFieldSeq == kStatusDesc)
            field = new StringField(this, "StatusDesc", 30, null, null);
        if (iFieldSeq == kPreferredSign)
            field = new PreferredSignField(this, "PreferredSign", Constants.DEFAULT_FIELD_LENGTH, null, null);
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
        if (iFieldSeq == kActiveTrx)
            field = new BooleanField(this, "ActiveTrx", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTrxStatusLastField)
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
            keyArea.addKeyField(kStatusDesc, DBConstants.ASCENDING);
        }
        if (iKeyArea == kSystemCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "SystemCode");
            keyArea.addKeyField(kSystemCode, DBConstants.ASCENDING);
            keyArea.addKeyField(kDescCode, DBConstants.ASCENDING);
            keyArea.addKeyField(kStatusCode, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kTrxStatusLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTrxStatusLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Get the status record that matches this template.
     */
    public int getTrxStatusID(String strSystemCode, String strDescCode, String strStatusCode)
    {
        int iTrxStatusID = 0;
        this.setKeyArea(TrxStatus.kSystemCodeKey);
        this.getField(TrxStatus.kSystemCode).setString(strSystemCode);
        this.getField(TrxStatus.kDescCode).setString(strDescCode);
        this.getField(TrxStatus.kStatusCode).setString(strStatusCode);
        try   {
            boolean bSuccess = this.seek("=");
            if (bSuccess)
                iTrxStatusID = (int)this.getField(TrxStatus.kID).getValue();
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return iTrxStatusID;
    }

}
