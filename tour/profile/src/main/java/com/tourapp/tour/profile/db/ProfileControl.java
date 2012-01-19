/**
 * @(#)ProfileControl.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.profile.db;

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
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.base.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.model.tour.profile.db.*;

/**
 *  ProfileControl - Control File.
 */
public class ProfileControl extends ControlRecord
     implements ProfileControlModel, MessageDetailTarget
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDefaultProfileStatusID = kControlRecordLastField + 1;
    public static final int kDefaultProfileClassID = kDefaultProfileStatusID + 1;
    public static final int kDefaultProfileTypeID = kDefaultProfileClassID + 1;
    public static final int kCountryID = kDefaultProfileTypeID + 1;
    public static final int kLanguageID = kCountryID + 1;
    public static final int kCurrencysID = kLanguageID + 1;
    public static final int kMessageTransportID = kCurrencysID + 1;
    public static final int kProfileControlLastField = kMessageTransportID;
    public static final int kProfileControlFields = kMessageTransportID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kProfileControlLastKey = kIDKey;
    public static final int kProfileControlKeys = kIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public ProfileControl()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProfileControl(RecordOwner screen)
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

    public static final String kProfileControlFile = "ProfileControl";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kProfileControlFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        if (iFieldSeq == kDefaultProfileStatusID)
            field = new ProfileStatusField(this, "DefaultProfileStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDefaultProfileClassID)
            field = new ProfileClassField(this, "DefaultProfileClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDefaultProfileTypeID)
            field = new ProfileTypeFilter(this, "DefaultProfileTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCountryID)
            field = new CountryField(this, "CountryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLanguageID)
            field = new LanguageField(this, "LanguageID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrencysID)
            field = new CurrencysField(this, "CurrencysID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMessageTransportID)
            field = new MessageTransportField(this, "MessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kProfileControlLastField)
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
        if (keyArea == null) if (iKeyArea < kProfileControlLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kProfileControlLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * GetNextMessageDetailTarget Method.
     */
    public MessageDetailTarget getNextMessageDetailTarget()
    {
        return null;    // End of the chain
    }
    /**
     * AddMessageProperties Method.
     */
    public TrxMessageHeader addMessageProperties(TrxMessageHeader trxMessageHeader)
    {
        return trxMessageHeader;
    }
    /**
     * GetMessageTransport Method.
     */
    public MessageTransport getMessageTransport(TrxMessageHeader trxMessageHeader)
    {
        return (MessageTransport)((ReferenceField)this.getField(ProfileControl.kMessageTransportID)).getReference();
    }
    /**
     * AddDestInfo Method.
     */
    public TrxMessageHeader addDestInfo(TrxMessageHeader trxMessageHeader)
    {
        return trxMessageHeader;
    }
    /**
     * SetProperty Method.
     */
    public boolean setProperty(String strKey, String strProperty)
    {
        return false; // Not supported for control files
    }
    /**
     * Get this property.
     */
    public String getProperty(String strKey)
    {
        return null;
    }

}
