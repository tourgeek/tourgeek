/**
 * @(#)LandVaries.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.land.db;

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
import com.tourapp.tour.product.land.screen.*;
import com.tourapp.model.tour.product.land.db.*;

/**
 *  LandVaries - Land varies codes.
 */
public class LandVaries extends VirtualRecord
     implements LandVariesModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kCode = kVirtualRecordLastField + 1;
    public static final int kDescription = kCode + 1;
    public static final int kVariesBy = kDescription + 1;
    public static final int kLandVariesLastField = kVariesBy;
    public static final int kLandVariesFields = kVariesBy - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kVariesByKey = kIDKey + 1;
    public static final int kDescriptionKey = kVariesByKey + 1;
    public static final int kLandVariesLastKey = kDescriptionKey;
    public static final int kLandVariesKeys = kDescriptionKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public LandVaries()
    {
        super();
    }
    /**
     * Constructor.
     */
    public LandVaries(RecordOwner screen)
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

    public static final String kLandVariesFile = "LandVaries";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kLandVariesFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Varies Codes";
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
        return DBConstants.TABLE | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
    }
    /**
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(LAND_VARIES_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(LAND_VARIES_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == kCode)
            field = new StringField(this, "Code", 1, null, null);
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 16, null, null);
        if (iFieldSeq == kVariesBy)
        {
            field = new VariesByField(this, "VariesBy", Constants.DEFAULT_FIELD_LENGTH, null, "VariesByField.AUTO_PER_PERSON");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kLandVariesLastField)
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
        if (iKeyArea == kVariesByKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "VariesBy");
            keyArea.addKeyField(kVariesBy, DBConstants.ASCENDING);
        }
        if (iKeyArea == kDescriptionKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(kDescription, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kLandVariesLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kLandVariesLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Get the param name for this param.
     * @param strVariesDesc The (optional) param name (see LandPricing)
     * @returns The param name.
     */
    public String getVariesParam(String strVariesDesc)
    {
        String strParam = this.getField(LandVaries.kDescription).toString();
        if ((strParam == null) || (strParam.length() == 0))
            strParam = this.getCounterField().toString();
        strParam = VARIES_PARAM + '.' + strParam;
        if ((strVariesDesc != null) && (strVariesDesc.length() != 0))
            strParam = strParam + '.' + strVariesDesc;
        strParam = Utility.replace(strParam, " ", DBConstants.BLANK);
        return strParam;
    }

}
