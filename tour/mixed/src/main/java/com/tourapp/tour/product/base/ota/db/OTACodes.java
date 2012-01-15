/**
 * @(#)OTACodes.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.product.base.ota.screen.*;
import com.tourapp.model.tour.product.base.ota.db.*;

/**
 *  OTACodes - .
 */
public class OTACodes extends VirtualRecord
     implements OTACodesModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kOTACodeTableID = kVirtualRecordLastField + 1;
    public static final int kValue = kOTACodeTableID + 1;
    public static final int kName = kValue + 1;
    public static final int kCreationDate = kName + 1;
    public static final int kDeletionDate = kCreationDate + 1;
    public static final int kProperties = kDeletionDate + 1;
    public static final int kOTACodesLastField = kProperties;
    public static final int kOTACodesFields = kProperties - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kOTACodeTableIDKey = kIDKey + 1;
    public static final int kOTACodesLastKey = kOTACodeTableIDKey;
    public static final int kOTACodesKeys = kOTACodeTableIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public OTACodes()
    {
        super();
    }
    /**
     * Constructor.
     */
    public OTACodes(RecordOwner screen)
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

    public static final String kOTACodesFile = "OTACodes";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kOTACodesFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        return DBConstants.REMOTE | DBConstants.USER_DATA;
    }
    /**
     * Make a default screen.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = BaseScreen.makeNewScreen(OTA_CODES_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = BaseScreen.makeNewScreen(OTA_CODES_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
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
        if (iFieldSeq == kOTACodeTableID)
        {
            field = new OTACodeTableField(this, "OTACodeTableID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setHidden(true);
            field.setNullable(false);
        }
        if (iFieldSeq == kValue)
            field = new StringField(this, "Value", 10, null, null);
        if (iFieldSeq == kName)
            field = new StringField(this, "Name", 128, null, null);
        if (iFieldSeq == kCreationDate)
            field = new DateField(this, "CreationDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDeletionDate)
            field = new DateField(this, "DeletionDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProperties)
            field = new PropertiesField(this, "Properties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kOTACodesLastField)
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
        if (iKeyArea == kOTACodeTableIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "OTACodeTableID");
            keyArea.addKeyField(kOTACodeTableID, DBConstants.ASCENDING);
            keyArea.addKeyField(kName, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kOTACodesLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kOTACodesLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
