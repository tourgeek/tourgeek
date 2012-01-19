/**
 * @(#)State.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.base.db;

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
import com.tourapp.tour.base.screen.*;
import com.tourapp.model.tour.base.db.*;

/**
 *  State - States.
 */
public class State extends Location
     implements StateModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kName = kName;
    public static final int kStatePostalCode = kCode;
    public static final int kCountryID = kLocationLastField + 1;
    public static final int kDescription = kCountryID + 1;
    public static final int kPicture = kDescription + 1;
    public static final int kPolygon = kPicture + 1;
    public static final int kGMTOffset = kPolygon + 1;
    public static final int kStateLastField = kGMTOffset;
    public static final int kStateFields = kGMTOffset - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kNameKey = kIDKey + 1;
    public static final int kStatePostalCodeKey = kNameKey + 1;
    public static final int kCountryIDKey = kStatePostalCodeKey + 1;
    public static final int kStateLastKey = kCountryIDKey;
    public static final int kStateKeys = kCountryIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public State()
    {
        super();
    }
    /**
     * Constructor.
     */
    public State(RecordOwner screen)
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

    public static final String kStateFile = "State";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kStateFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "State";
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
        return DBConstants.LOCAL | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
    }
    /**
     * Make a default screen.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = BaseScreen.makeNewScreen(STATE_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = BaseScreen.makeNewScreen(STATE_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == kName)
            field = new StringField(this, "Name", 40, null, null);
        if (iFieldSeq == kStatePostalCode)
            field = new StringField(this, "StatePostalCode", 20, null, null);
        if (iFieldSeq == kCountryID)
            field = new CountryField(this, "CountryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDescription)
            field = new MemoField(this, "Description", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPicture)
            field = new ObjectField(this, "Picture", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPolygon)
            field = new ObjectField(this, "Polygon", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kGMTOffset)
            field = new FloatField(this, "GMTOffset", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kStateLastField)
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
        if (iKeyArea == kNameKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Name");
            keyArea.addKeyField(kName, DBConstants.ASCENDING);
        }
        if (iKeyArea == kStatePostalCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "StatePostalCode");
            keyArea.addKeyField(kStatePostalCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kCountryIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "CountryID");
            keyArea.addKeyField(kCountryID, DBConstants.ASCENDING);
            keyArea.addKeyField(kName, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kStateLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kStateLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
