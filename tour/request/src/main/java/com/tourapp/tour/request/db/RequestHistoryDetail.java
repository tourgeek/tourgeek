/**
 * @(#)RequestHistoryDetail.
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

/**
 *  RequestHistoryDetail - Brochure Request Detail History.
 */
public class RequestHistoryDetail extends VirtualRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kRequestHistoryID = kVirtualRecordLastField + 1;
    public static final int kProfileID = kRequestHistoryID + 1;
    public static final int kBrochureID = kProfileID + 1;
    public static final int kBrochureQty = kBrochureID + 1;
    public static final int kBrochureDesc = kBrochureQty + 1;
    public static final int kMailedOn = kBrochureDesc + 1;
    public static final int kRequestHistoryDetailLastField = kMailedOn;
    public static final int kRequestHistoryDetailFields = kMailedOn - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kRequestHistoryIDKey = kIDKey + 1;
    public static final int kProfileIDKey = kRequestHistoryIDKey + 1;
    public static final int kRequestHistoryDetailLastKey = kProfileIDKey;
    public static final int kRequestHistoryDetailKeys = kProfileIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public RequestHistoryDetail()
    {
        super();
    }
    /**
     * Constructor.
     */
    public RequestHistoryDetail(RecordOwner screen)
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

    public static final String kRequestHistoryDetailFile = "RequestHistoryDetail";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kRequestHistoryDetailFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Brochure request history";
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
        if (iFieldSeq == kRequestHistoryID)
        {
            field = new ReferenceField(this, "RequestHistoryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kProfileID)
            field = new ReferenceField(this, "ProfileID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBrochureID)
            field = new BrochureField(this, "BrochureID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBrochureQty)
            field = new ShortField(this, "BrochureQty", 3, null, null);
        if (iFieldSeq == kBrochureDesc)
            field = new StringField(this, "BrochureDesc", 30, null, null);
        if (iFieldSeq == kMailedOn)
            field = new RequestHistoryDetail_MailedOn(this, "MailedOn", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kRequestHistoryDetailLastField)
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
        if (iKeyArea == kRequestHistoryIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "RequestHistoryID");
            keyArea.addKeyField(kRequestHistoryID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kProfileIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ProfileID");
            keyArea.addKeyField(kProfileID, DBConstants.DESCENDING);
            keyArea.addKeyField(kMailedOn, DBConstants.DESCENDING);
            keyArea.addKeyField(kBrochureID, DBConstants.DESCENDING);
        }
        if (keyArea == null) if (iKeyArea < kRequestHistoryDetailLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kRequestHistoryDetailLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
