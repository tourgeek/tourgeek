/**
 * @(#)Document.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.profile.detail;

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
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.model.tour.profile.detail.*;

/**
 *  Document - Documents detail.
 */
public class Document extends VirtualRecord
     implements DocumentModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kProfileID = kVirtualRecordLastField + 1;
    public static final int kDocName = kProfileID + 1;
    public static final int kDocNumber = kDocName + 1;
    public static final int kCountryCodeID = kDocNumber + 1;
    public static final int kNationalityID = kCountryCodeID + 1;
    public static final int kEffectiveDate = kNationalityID + 1;
    public static final int kExpirationDate = kEffectiveDate + 1;
    public static final int kDocumentCode = kExpirationDate + 1;
    public static final int kPlaceOfIssue = kDocumentCode + 1;
    public static final int kDocumentLastField = kPlaceOfIssue;
    public static final int kDocumentFields = kPlaceOfIssue - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kProfileIDKey = kIDKey + 1;
    public static final int kDocumentLastKey = kProfileIDKey;
    public static final int kDocumentKeys = kProfileIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public Document()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Document(RecordOwner screen)
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

    public static final String kDocumentFile = "Document";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kDocumentFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Document";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "profile";
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
        if (iFieldSeq == kProfileID)
            field = new ProfileField(this, "ProfileID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDocName)
            field = new StringField(this, "DocName", 65, null, null);
        if (iFieldSeq == kDocNumber)
            field = new StringField(this, "DocNumber", 20, null, null);
        if (iFieldSeq == kCountryCodeID)
            field = new CountryField(this, "CountryCodeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNationalityID)
            field = new CountryField(this, "NationalityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEffectiveDate)
            field = new DateField(this, "EffectiveDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kExpirationDate)
            field = new DateField(this, "ExpirationDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDocumentCode)
            field = new StringField(this, "DocumentCode", 3, null, "PP");
        if (iFieldSeq == kPlaceOfIssue)
            field = new StringField(this, "PlaceOfIssue", 3, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kDocumentLastField)
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
        }
        if (keyArea == null) if (iKeyArea < kDocumentLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kDocumentLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
