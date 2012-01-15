/**
 * @(#)RequestHistory.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.request.db;

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
import com.tourapp.tour.base.db.shared.*;
import com.tourapp.tour.request.screen.detail.*;
import com.tourapp.model.tour.request.db.*;

/**
 *  RequestHistory - Brochure Request History.
 */
public class RequestHistory extends Request
     implements RequestHistoryModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kHistReprint = kHistReprint;
    public static final int kHistTimePrinted = kRequestLastField + 1;
    public static final int kRequestHistoryLastField = kHistTimePrinted;
    public static final int kRequestHistoryFields = kHistTimePrinted - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kProfileIDKey = kIDKey + 1;
    public static final int kHistReprintKey = kProfileIDKey + 1;
    public static final int kRequestHistoryLastKey = kHistReprintKey;
    public static final int kRequestHistoryKeys = kHistReprintKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public RequestHistory()
    {
        super();
    }
    /**
     * Constructor.
     */
    public RequestHistory(RecordOwner screen)
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

    public static final String kRequestHistoryFile = "RequestHistory";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kRequestHistoryFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Brochure Request History";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "request";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.REMOTE | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == ScreenConstants.DETAIL_MODE)
            screen = new RequestHistoryDetailGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new RequestHistoryScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new RequestHistoryGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kHistTimePrinted)
            field = new RequestHistory_HistTimePrinted(this, "HistTimePrinted", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kHistReprint)
        //  field = new BooleanField(this, "HistReprint", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kRequestHistoryLastField)
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
        if (iKeyArea == kProfileIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ProfileID");
            keyArea.addKeyField(kProfileID, DBConstants.ASCENDING);
            keyArea.addKeyField(kHistTimePrinted, DBConstants.ASCENDING);
        }
        if (iKeyArea == kHistReprintKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "HistReprint");
            keyArea.addKeyField(kHistReprint, DBConstants.ASCENDING);
            keyArea.addKeyField(kHistTimePrinted, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kRequestHistoryLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kRequestHistoryLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
