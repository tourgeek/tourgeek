/**
  * @(#)ProfileControl.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.message.core.trx.*;
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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(PROFILE_CONTROL_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (iFieldSeq == 3)
            field = new ProfileStatusField(this, DEFAULT_PROFILE_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new ProfileClassField(this, DEFAULT_PROFILE_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new ProfileTypeFilter(this, DEFAULT_PROFILE_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new CountryField(this, COUNTRY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new LanguageField(this, LANGUAGE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new CurrencysField(this, CURRENCYS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new MessageTransportField(this, MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
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
        return (MessageTransport)((ReferenceField)this.getField(ProfileControl.MESSAGE_TRANSPORT_ID)).getReference();
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
