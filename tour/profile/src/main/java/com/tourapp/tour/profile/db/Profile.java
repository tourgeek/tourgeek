/**
 * @(#)Profile.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.main.db.*;
import com.tourapp.tour.profile.screen.*;
import com.tourapp.tour.profile.screen.detail.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.base.message.trx.message.*;
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

    //public static final int kID = kID;
    public static final int kProfileCode = kCode;
    //public static final int kDateEntered = kDateEntered;
    //public static final int kChangedID = kChangedID;
    //public static final int kDateChanged = kDateChanged;
    public static final int kGenericName = kName;
    //public static final int kAddressLine1 = kAddressLine1;
    //public static final int kAddressLine2 = kAddressLine2;
    //public static final int kCityOrTown = kCityOrTown;
    //public static final int kStateOrRegion = kStateOrRegion;
    //public static final int kPostalCode = kPostalCode;
    //public static final int kCountry = kCountry;
    //public static final int kTel = kTel;
    //public static final int kFax = kFax;
    //public static final int kEmail = kEmail;
    //public static final int kWeb = kWeb;
    public static final int kNameOrdered = kContact;
    //public static final int kComments = kComments;
    //public static final int kPassword = kPassword;
    //public static final int kNameSort = kNameSort;
    //public static final int kPostalCodeSort = kPostalCodeSort;
    public static final int kProfileTypeID = kCompanyLastField + 1;
    public static final int kEnteredID = kProfileTypeID + 1;
    public static final int kAffiliationID = kEnteredID + 1;
    public static final int kCommissionPlanCode = kAffiliationID + 1;
    public static final int kAltPhone = kCommissionPlanCode + 1;
    public static final int kNamePrefix = kAltPhone + 1;
    public static final int kNameFirst = kNamePrefix + 1;
    public static final int kNameMiddle = kNameFirst + 1;
    public static final int kNameSur = kNameMiddle + 1;
    public static final int kNameSuffix = kNameSur + 1;
    public static final int kNameTitle = kNameSuffix + 1;
    public static final int kDateOfBirth = kNameTitle + 1;
    public static final int kGender = kDateOfBirth + 1;
    public static final int kExpirationDate = kGender + 1;
    public static final int kCorporatePosition = kExpirationDate + 1;
    public static final int kPIN = kCorporatePosition + 1;
    public static final int kCreditLimit = kPIN + 1;
    public static final int kCountryID = kCreditLimit + 1;
    public static final int kPrimaryLanguageID = kCountryID + 1;
    public static final int kCurrencysID = kPrimaryLanguageID + 1;
    public static final int kMessageTransportID = kCurrencysID + 1;
    public static final int kProperties = kMessageTransportID + 1;
    public static final int kProfileStatusID = kProperties + 1;
    public static final int kProfileClassID = kProfileStatusID + 1;
    public static final int kSmoker = kProfileClassID + 1;
    public static final int kSeatChoiceID = kSmoker + 1;
    public static final int kMainProfileID = kSeatChoiceID + 1;
    public static final int kProfileLastField = kMainProfileID;
    public static final int kProfileFields = kMainProfileID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kProfileCodeKey = kIDKey + 1;
    public static final int kNameSortKey = kProfileCodeKey + 1;
    public static final int kMainProfileIDKey = kNameSortKey + 1;
    public static final int kPostalCodeSortKey = kMainProfileIDKey + 1;
    public static final int kNameSurKey = kPostalCodeSortKey + 1;
    public static final int kProfileLastKey = kNameSurKey;
    public static final int kProfileKeys = kNameSurKey - DBConstants.MAIN_KEY_FIELD + 1;
    protected ProfileControl m_recProfileControl = null;
    public static final int MESSAGE_LOG_MODE = ScreenConstants.LAST_MODE * 4;
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

    public static final String kProfileFile = "Profile";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kProfileFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", 8, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kProfileTypeID)
        {
            field = new ProfileTypeFilter(this, "ProfileTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kProfileCode)
            field = new StringField(this, "ProfileCode", 20, null, null);
        if (iFieldSeq == kEnteredID)
            field = new Profile_EnteredID(this, "EnteredID", 6, null, null);
        if (iFieldSeq == kDateEntered)
            field = new Profile_DateEntered(this, "DateEntered", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kChangedID)
            field = new ReferenceField(this, "ChangedID", 6, null, null);
        if (iFieldSeq == kDateChanged)
            field = new DateField(this, "DateChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAffiliationID)
            field = new AffiliationField(this, "AffiliationID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCommissionPlanCode)
            field = new StringField(this, "CommissionPlanCode", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kGenericName)
            field = new StringField(this, "GenericName", 65, null, null);
        //if (iFieldSeq == kAddressLine1)
        //  field = new StringField(this, "AddressLine1", 40, null, null);
        //if (iFieldSeq == kAddressLine2)
        //  field = new StringField(this, "AddressLine2", 40, null, null);
        //if (iFieldSeq == kCityOrTown)
        //  field = new StringField(this, "CityOrTown", 15, null, null);
        //if (iFieldSeq == kStateOrRegion)
        //  field = new StringField(this, "StateOrRegion", 15, null, null);
        //if (iFieldSeq == kPostalCode)
        //  field = new StringField(this, "PostalCode", 10, null, null);
        //if (iFieldSeq == kCountry)
        //  field = new StringField(this, "Country", 15, null, null);
        //if (iFieldSeq == kTel)
        //  field = new PhoneField(this, "Tel", 24, null, null);
        if (iFieldSeq == kAltPhone)
        {
            field = new PhoneField(this, "AltPhone", 24, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kFax)
        //  field = new FaxField(this, "Fax", 24, null, null);
        //if (iFieldSeq == kEmail)
        //  field = new EMailField(this, "Email", 40, null, null);
        //if (iFieldSeq == kWeb)
        //  field = new URLField(this, "Web", 60, null, null);
        if (iFieldSeq == kNamePrefix)
            field = new TitleField(this, "NamePrefix", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNameFirst)
            field = new StringField(this, "NameFirst", 65, null, null);
        if (iFieldSeq == kNameMiddle)
            field = new StringField(this, "NameMiddle", 65, null, null);
        if (iFieldSeq == kNameSur)
            field = new StringField(this, "NameSur", 65, null, null);
        if (iFieldSeq == kNameSuffix)
            field = new StringField(this, "NameSuffix", 10, null, null);
        if (iFieldSeq == kNameTitle)
            field = new StringField(this, "NameTitle", 10, null, null);
        if (iFieldSeq == kNameOrdered)
            field = new NameOrderedField(this, "NameOrdered", 65, null, null);
        if (iFieldSeq == kDateOfBirth)
            field = new DateField(this, "DateOfBirth", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kGender)
            field = new GenderField(this, "Gender", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kExpirationDate)
            field = new DateTimeField(this, "ExpirationDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCorporatePosition)
            field = new StringField(this, "CorporatePosition", 60, null, null);
        if (iFieldSeq == kPIN)
            field = new StringField(this, "PIN", 10, null, null);
        if (iFieldSeq == kCreditLimit)
            field = new CurrencyField(this, "CreditLimit", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCountryID)
            field = new CountryField(this, "CountryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPrimaryLanguageID)
            field = new LanguageField(this, "PrimaryLanguageID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrencysID)
            field = new CurrencysField(this, "CurrencysID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMessageTransportID)
            field = new MessageTransportSelect(this, "MessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kComments)
        //  field = new MemoField(this, "Comments", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProperties)
            field = new PropertiesField(this, "Properties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kPassword)
        //  field = new StringField(this, "Password", 16, null, null);
        if (iFieldSeq == kNameSort)
            field = new StringField(this, "NameSort", 6, null, null);
        if (iFieldSeq == kProfileStatusID)
            field = new ProfileStatusField(this, "ProfileStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kPostalCodeSort)
        //  field = new StringField(this, "PostalCodeSort", 5, null, null);
        if (iFieldSeq == kProfileClassID)
            field = new ProfileClassField(this, "ProfileClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSmoker)
            field = new BooleanField(this, "Smoker", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSeatChoiceID)
            field = new SeatChoiceField(this, "SeatChoiceID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMainProfileID)
            field = new ProfileField(this, "MainProfileID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kProfileLastField)
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
        if (iKeyArea == kProfileCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "ProfileCode");
            keyArea.addKeyField(kProfileCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kNameSortKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "NameSort");
            keyArea.addKeyField(kNameSort, DBConstants.ASCENDING);
        }
        if (iKeyArea == kMainProfileIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "MainProfileID");
            keyArea.addKeyField(kMainProfileID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kPostalCodeSortKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "PostalCodeSort");
            keyArea.addKeyField(kPostalCodeSort, DBConstants.ASCENDING);
            keyArea.addKeyField(kNameSort, DBConstants.ASCENDING);
        }
        if (iKeyArea == kNameSurKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "NameSur");
            keyArea.addKeyField(kNameSur, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kProfileLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kProfileLastKey)
                keyArea = new EmptyKey(this);
        }
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
        this.addListener(new SetUserIDHandler(kEnteredID, true));
        
        this.getField(Company.kContact).removeListener(this.getField(Company.kContact).getListener(CopyLastHandler.class.getName()), true);
        this.getField(Company.kName).removeListener(this.getField(Company.kName).getListener(CopyFieldHandler.class.getName()), true);
        
        Converter converter = this.getField(Company.kName);
        Converter cnvDependent = this.getField(Profile.kNameSur);
        Converter converterAlt = new AltFieldConverter(converter, cnvDependent);
        this.addListener(new RemoveConverterOnCloseHandler(converterAlt));
        
        boolean bClearIfThisNull = false;
        boolean bOnlyIfDestNull = false;
        this.getField(Profile.kName).addListener(new MoveOnChangeHandler(this.getField(Profile.kNameSort), converterAlt, bClearIfThisNull, bOnlyIfDestNull));    // Only if dest is null (ie., company name is null)
        this.getField(Profile.kNameSur).addListener(new MoveOnChangeHandler(this.getField(Profile.kNameSort), converterAlt));    // Only if dest is null (ie., company name is null)
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
            m_recProfileControl = new ProfileControl(Utility.getRecordOwner(this));
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
        return (MessageTransport)((ReferenceField)this.getField(Profile.kMessageTransportID)).getReference();
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
            this.getField(Profile.kWeb).setString(strProperty);
        else
            ((PropertiesField)this.getField(Profile.kProperties)).setProperty(strKey, strProperty);
        return true;
    }
    /**
     * Get this property.
     */
    public String getProperty(String strKey)
    {
        if (TrxMessageHeader.DESTINATION_PARAM.equalsIgnoreCase(strKey))
            return this.getField(Profile.kWeb).toString();
        else
            return ((PropertiesField)this.getField(Profile.kProperties)).getProperty(strKey);
    }

}
