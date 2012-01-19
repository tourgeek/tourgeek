/**
 * @(#)RequestControl.
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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.model.tour.request.db.*;

/**
 *  RequestControl - Control File.
 */
public class RequestControl extends ControlRecord
     implements RequestControlModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kSendViaCode = kControlRecordLastField + 1;
    public static final int kBrochureQty = kSendViaCode + 1;
    public static final int kBulkPermitCode = kBrochureQty + 1;
    public static final int kBulkPermitText = kBulkPermitCode + 1;
    public static final int kBundleID = kBulkPermitText + 1;
    public static final int kThinBundleID = kBundleID + 1;
    public static final int kHtmlBundleID = kThinBundleID + 1;
    public static final int kProfileTypeID = kHtmlBundleID + 1;
    public static final int kRequestControlLastField = kProfileTypeID;
    public static final int kRequestControlFields = kProfileTypeID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kRequestControlLastKey = kIDKey;
    public static final int kRequestControlKeys = kIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public RequestControl()
    {
        super();
    }
    /**
     * Constructor.
     */
    public RequestControl(RecordOwner screen)
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

    public static final String kRequestControlFile = "RequestControl";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kRequestControlFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        return DBConstants.LOCAL | DBConstants.USER_DATA;
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
        if (iFieldSeq == kSendViaCode)
            field = new SendViaField(this, "SendViaCode", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBrochureQty)
            field = new ShortField(this, "BrochureQty", 3, null, new Short((short)5));
        if (iFieldSeq == kBulkPermitCode)
            field = new SendViaField(this, "BulkPermitCode", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBulkPermitText)
            field = new StringField(this, "BulkPermitText", 255, null, null);
        if (iFieldSeq == kBundleID)
            field = new BundleFilter(this, "BundleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kThinBundleID)
            field = new BundleFilter(this, "ThinBundleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kHtmlBundleID)
            field = new BundleFilter(this, "HtmlBundleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProfileTypeID)
            field = new ProfileTypeFilter(this, "ProfileTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kRequestControlLastField)
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
        if (keyArea == null) if (iKeyArea < kRequestControlLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kRequestControlLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
