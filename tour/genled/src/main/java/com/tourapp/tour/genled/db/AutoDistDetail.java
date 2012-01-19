/**
 * @(#)AutoDistDetail.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.genled.screen.autodist.*;
import com.tourapp.model.tour.genled.db.*;

/**
 *  AutoDistDetail - Automatic Distribution Entry.
 */
public class AutoDistDetail extends VirtualRecord
     implements AutoDistDetailModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kAutoDistID = kVirtualRecordLastField + 1;
    public static final int kDistAccountID = kAutoDistID + 1;
    public static final int kDistPercent = kDistAccountID + 1;
    public static final int kAutoDistDetailLastField = kDistPercent;
    public static final int kAutoDistDetailFields = kDistPercent - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kAutoDistIDKey = kIDKey + 1;
    public static final int kAutoDistDetailLastKey = kAutoDistIDKey;
    public static final int kAutoDistDetailKeys = kAutoDistIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public AutoDistDetail()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AutoDistDetail(RecordOwner screen)
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

    public static final String kAutoDistDetailFile = "AutoDistDetail";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kAutoDistDetailFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Auto Distribution Detail";
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
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * Make a default screen.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = BaseScreen.makeNewScreen(AUTO_DIST_DETAIL_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = BaseScreen.makeNewScreen(AUTO_DIST_DETAIL_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == kAutoDistID)
        {
            field = new ReferenceField(this, "AutoDistID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kDistAccountID)
            field = new AccountField(this, "DistAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDistPercent)
            field = new PercentField(this, "DistPercent", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kAutoDistDetailLastField)
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
        if (iKeyArea == kAutoDistIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "AutoDistID");
            keyArea.addKeyField(kAutoDistID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kAutoDistDetailLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kAutoDistDetailLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
