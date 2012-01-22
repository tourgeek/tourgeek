/**
 * @(#)RequestDetail.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.base.db.shared.*;
import com.tourapp.model.tour.request.db.*;

/**
 *  RequestDetail - Brochure request detail.
 */
public class RequestDetail extends VirtualRecord
     implements RequestDetailModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kRequestID = kVirtualRecordLastField + 1;
    public static final int kBrochureID = kRequestID + 1;
    public static final int kBrochureQty = kBrochureID + 1;
    public static final int kBrochureDesc = kBrochureQty + 1;
    public static final int kRequestDetailLastField = kBrochureDesc;
    public static final int kRequestDetailFields = kBrochureDesc - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kRequestIDKey = kIDKey + 1;
    public static final int kRequestDetailLastKey = kRequestIDKey;
    public static final int kRequestDetailKeys = kRequestIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public RequestDetail()
    {
        super();
    }
    /**
     * Constructor.
     */
    public RequestDetail(RecordOwner screen)
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

    public static final String kRequestDetailFile = "RequestDetail";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kRequestDetailFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Brochure request detail";
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
        if (iFieldSeq == kRequestID)
            field = new ReferenceField(this, "RequestID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBrochureID)
            field = new BrochureField(this, "BrochureID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBrochureQty)
            field = new ShortField(this, "BrochureQty", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBrochureDesc)
            field = new StringField(this, "BrochureDesc", 30, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kRequestDetailLastField)
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
        if (iKeyArea == kRequestIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "RequestID");
            keyArea.addKeyField(kRequestID, DBConstants.ASCENDING);
            keyArea.addKeyField(kBrochureID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kRequestDetailLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kRequestDetailLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
