/**
 * @(#)Profile.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.main.db.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.base.message.core.trx.*;
import org.jbundle.main.msg.screen.*;
import com.tourapp.tour.base.field.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.base.db.shared.*;
import com.tourapp.model.tour.profile.db.*;

/**
 *  Profile - Passenger and Agency Profile.
 */
public class Profile extends Company
     implements ProfileModel, MessageDetailTarget
{
    private static final long serialVersionUID = 1L;

    protected ProfileControl m_recProfileControl = null;
    public static final int MESSAGE_LOG_MODE = ScreenConstants.DETAIL_MODE | ScreenConstants.LAST_MODE * 4;
    /**
     * Default constructor.
     */
    public Profile()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Profile(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        m_recProfileControl = null;
        super.init(screen);
    }
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(PROFILE_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Profile";
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
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == ScreenConstants.DETAIL_MODE)
            screen = Record.makeNewScreen(REQUEST_HISTORY_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & Profile.MESSAGE_LOG_MODE) == Profile.MESSAGE_LOG_MODE)
            screen = Record.makeNewScreen(MESSAGE_LOG_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(MAINT_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = Record.makeNewScreen(GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, 8, null, null);
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
            field = new StringField(this, PROFILE_CODE, 20, null, null);
        if (iFieldSeq == 4)
            field = new StringField(this, GENERIC_NAME, 65, null, null);
        //if (iFieldSeq == 5)
        //  field = new StringField(this, ADDRESS_LINE_1, 40, null, null);
        //if (iFieldSeq == 6)
        //  field = new StringField(this, ADDRESS_LINE_2, 40, null, null);
        //if (iFieldSeq == 7)
        //  field = new StringField(this, CITY_OR_TOWN, 15, null, null);
        //if (iFieldSeq == 8)
        //  field = new StringField(this, STATE_OR_REGION, 15, null, null);
        //if (iFieldSeq == 9)
        //  field = new StringField(this, POSTAL_CODE, 10, null, null);
        //if (iFieldSeq == 10)
        //  field = new StringField(this, COUNTRY, 15, null, null);
        //if (iFieldSeq == 11)
        //  field = new PhoneField(this, TEL, 24, null, null);
        //if (iFieldSeq == 12)
        //  field = new FaxField(this, FAX, 24, null, null);
        //if (iFieldSeq == 13)
        //  field = new EMailField(this, EMAIL, 40, null, null);
        //if (iFieldSeq == 14)
        //  field = new URLField(this, WEB, 60, null, null);
        if (iFieldSeq == 15)
            field = new Profile_DateEntered(this, DATE_ENTERED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
            field = new DateField(this, DATE_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new ReferenceField(this, CHANGED_ID, 6, null, null);
        //if (iFieldSeq == 18)
        //  field = new MemoField(this, COMMENTS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 19)
        //  field = new UserField(this, USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 20)
        //  field = new StringField(this, PASSWORD, 16, null, null);
        if (iFieldSeq == 21)
            field = new StringField(this, NAME_SORT, 6, null, null);
        //if (iFieldSeq == 22)
        //  field = new StringField(this, POSTAL_CODE_SORT, 5, null, null);
        if (iFieldSeq == 23)
            field = new NameOrderedField(this, NAME_ORDERED, 65, null, null);
        if (iFieldSeq == 24)
        {
            field = new ProfileTypeFilter(this, PROFILE_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 25)
            field = new Profile_EnteredID(this, ENTERED_ID, 6, null, null);
        if (iFieldSeq == 26)
            field = new AffiliationField(this, AFFILIATION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 27)
            field = new StringField(this, COMMISSION_PLAN_CODE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 28)
        {
            field = new PhoneField(this, ALT_PHONE, 24, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 29)
            field = new TitleField(this, NAME_PREFIX, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 30)
            field = new StringField(this, NAME_FIRST, 65, null, null);
        if (iFieldSeq == 31)
            field = new StringField(this, NAME_MIDDLE, 65, null, null);
        if (iFieldSeq == 32)
            field = new StringField(this, NAME_SUR, 65, null, null);
        if (iFieldSeq == 33)
            field = new StringField(this, NAME_SUFFIX, 10, null, null);
        if (iFieldSeq == 34)
            field = new StringField(this, NAME_TITLE, 10, null, null);
        if (iFieldSeq == 35)
            field = new DateField(this, DATE_OF_BIRTH, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 36)
            field = new GenderField(this, GENDER, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 37)
            field = new DateTimeField(this, EXPIRATION_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 38)
            field = new StringField(this, CORPORATE_POSITION, 60, null, null);
        if (iFieldSeq == 39)
            field = new StringField(this, PIN, 10, null, null);
        if (iFieldSeq == 40)
            field = new CurrencyField(this, CREDIT_LIMIT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 41)
            field = new CountryField(this, COUNTRY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 42)
            field = new LanguageField(this, PRIMARY_LANGUAGE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 43)
            field = new CurrencysField(this, CURRENCYS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 44)
            field = new MessageTransportSelect(this, MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 45)
            field = new PropertiesField(this, PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 46)
            field = new ProfileStatusField(this, PROFILE_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 47)
            field = new ProfileClassField(this, PROFILE_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 48)
            field = new BooleanField(this, SMOKER, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 49)
            field = new SeatChoiceField(this, SEAT_CHOICE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 50)
            field = new ProfileField(this, MAIN_PROFILE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ID");
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "ProfileCode");
            keyArea.addKeyField(PROFILE_CODE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "NameSort");
            keyArea.addKeyField(NAME_SORT, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "MainProfileID");
            keyArea.addKeyField(MAIN_PROFILE_ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 4)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "PostalCodeSort");
            keyArea.addKeyField(POSTAL_CODE_SORT, DBConstants.ASCENDING);
            keyArea.addKeyField(NAME_SORT, DBConstants.ASCENDING);
        }
        if (iKeyArea == 5)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "NameSur");
            keyArea.addKeyField(NAME_SUR, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recProfileControl != null)
            m_recProfileControl.free();
        m_recProfileControl = null;
        super.free();
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        this.addListener(new SetUserIDHandler(Profile.ENTERED_ID, true));
        
        this.getField(Company.CONTACT).removeListener(this.getField(Company.CONTACT).getListener(CopyLastHandler.class.getName()), true);
        this.getField(Company.NAME).removeListener(this.getField(Company.NAME).getListener(CopyFieldHandler.class.getName()), true);
        
        Converter converter = this.getField(Company.NAME);
        Converter cnvDependent = this.getField(Profile.NAME_SUR);
        Converter converterAlt = new AltFieldConverter(converter, cnvDependent);
        this.addListener(new RemoveConverterOnCloseHandler(converterAlt));
        
        boolean bClearIfThisNull = false;
        boolean bOnlyIfDestNull = false;
        this.getField(Profile.NAME).addListener(new MoveOnChangeHandler(this.getField(Profile.NAME_SORT), converterAlt, bClearIfThisNull, bOnlyIfDestNull));    // Only if dest is null (ie., company name is null)
        this.getField(Profile.NAME_SUR).addListener(new MoveOnChangeHandler(this.getField(Profile.NAME_SORT), converterAlt));    // Only if dest is null (ie., company name is null)
    }
    /**
     * Convert the command to the screen document type.
     * @param strCommand The command text.
     * @param The standard document type (MAINT/DISPLAY/SELECT/MENU/etc).
     */
    public int commandToDocType(String strCommand)
    {
        if (Profile.MESSAGE_LOG_SCREEN.equalsIgnoreCase(strCommand))
            return Profile.MESSAGE_LOG_MODE;
        return super.commandToDocType(strCommand);
    }
    /**
     * GetNextMessageDetailTarget Method.
     */
    public MessageDetailTarget getNextMessageDetailTarget()
    {
        if (m_recProfileControl == null)
        {
            m_recProfileControl = new ProfileControl(this.findRecordOwner());
            if (m_recProfileControl.getRecordOwner() != null)
                m_recProfileControl.getRecordOwner().removeRecord(m_recProfileControl);
            this.addListener(new FreeOnFreeHandler(m_recProfileControl));
        }
        return m_recProfileControl;
    }
    /**
     * Get the properties for this kind of message
     * @param strMessageName The message name.
     * @return The properties for this message.
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
        return (MessageTransport)((ReferenceField)this.getField(Profile.MESSAGE_TRANSPORT_ID)).getReference();
    }
    /**
     * Add the destination information of this person to the message.
     */
    public TrxMessageHeader addDestInfo(TrxMessageHeader trxMessageHeader)
    {
        // Add SOAP handling!
        
        return super.addDestInfo(trxMessageHeader);
    }
    /**
     * SetProperty Method.
     */
    public boolean setProperty(String strKey, String strProperty)
    {
        if (TrxMessageHeader.DESTINATION_PARAM.equalsIgnoreCase(strKey))
            this.getField(Profile.WEB).setString(strProperty);
        else
            ((PropertiesField)this.getField(Profile.PROPERTIES)).setProperty(strKey, strProperty);
        return true;
    }
    /**
     * Get this property.
     */
    public String getProperty(String strKey)
    {
        if (TrxMessageHeader.DESTINATION_PARAM.equalsIgnoreCase(strKey))
            return this.getField(Profile.WEB).toString();
        else
            return ((PropertiesField)this.getField(Profile.PROPERTIES)).getProperty(strKey);
    }

}
