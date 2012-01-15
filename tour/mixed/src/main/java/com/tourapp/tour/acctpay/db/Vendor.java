/**
 * @(#)Vendor.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.db;

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
import org.jbundle.main.db.*;
import com.tourapp.tour.acctpay.screen.broker.*;
import com.tourapp.tour.acctpay.screen.vendor.*;
import com.tourapp.tour.acctpay.screen.trx.*;
import org.jbundle.main.msg.screen.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.base.message.trx.message.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.model.tour.acctpay.db.*;

/**
 *  Vendor - Vendors.
 */
public class Vendor extends Company
     implements VendorModel, MessageDetailTarget
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kCode = kCode;
    //public static final int kContact = kContact;
    public static final int kVendorName = kName;
    //public static final int kAddressLine1 = kAddressLine1;
    //public static final int kAddressLine2 = kAddressLine2;
    //public static final int kCityOrTown = kCityOrTown;
    //public static final int kStateOrRegion = kStateOrRegion;
    //public static final int kPostalCode = kPostalCode;
    //public static final int kCountry = kCountry;
    //public static final int kTel = kTel;
    //public static final int kDateEntered = kDateEntered;
    //public static final int kDateChanged = kDateChanged;
    //public static final int kChangedID = kChangedID;
    //public static final int kFax = kFax;
    //public static final int kEmail = kEmail;
    //public static final int kWeb = kWeb;
    //public static final int kComments = kComments;
    //public static final int kPassword = kPassword;
    //public static final int kPostalCodeSort = kPostalCodeSort;
    //public static final int kNameSort = kNameSort;
    public static final int kContactTitle = kCompanyLastField + 1;
    public static final int kCountryID = kContactTitle + 1;
    public static final int kCurrencysID = kCountryID + 1;
    public static final int kMessageTransportID = kCurrencysID + 1;
    public static final int kMessageServer = kMessageTransportID + 1;
    public static final int kWSDLPath = kMessageServer + 1;
    public static final int kProperties = kWSDLPath + 1;
    public static final int kVendorStatusID = kProperties + 1;
    public static final int kPaymentCycleID = kVendorStatusID + 1;
    public static final int kPaymentCodeID = kPaymentCycleID + 1;
    public static final int kPrepayTypeID = kPaymentCodeID + 1;
    public static final int kDepositID = kPrepayTypeID + 1;
    public static final int kOperationTypeCode = kDepositID + 1;
    public static final int kDefaultAccountID = kOperationTypeCode + 1;
    public static final int kSend1099 = kDefaultAccountID + 1;
    public static final int kTaxId = kSend1099 + 1;
    public static final int kVendorsCustNo = kTaxId + 1;
    public static final int kAmountSelected = kVendorsCustNo + 1;
    public static final int kVendorBalance = kAmountSelected + 1;
    public static final int kVendorLastField = kVendorBalance;
    public static final int kVendorFields = kVendorBalance - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCodeKey = kIDKey + 1;
    public static final int kNameSortKey = kCodeKey + 1;
    public static final int kCurrencysIDKey = kNameSortKey + 1;
    public static final int kVendorLastKey = kCurrencysIDKey;
    public static final int kVendorKeys = kCurrencysIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    protected ApControl m_recApControl = null;
    public static final int VENDOR_DETAIL_MODE = ScreenConstants.DISPLAY_MODE | 64;
    public static final int BROKER_DETAIL_MODE = ScreenConstants.DISPLAY_MODE | 128;
    public static final int MESSAGE_DETAIL_MODE = ScreenConstants.LAST_MODE * 2;
    public static final int MESSAGE_LOG_MODE = ScreenConstants.LAST_MODE * 4;
    /**
     * Default constructor.
     */
    public Vendor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Vendor(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        m_recApControl = null;
        super.init(screen);
    }

    public static final String kVendorFile = "Vendor";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kVendorFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Vendor";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "acctpay";
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
        if ((iDocMode & Vendor.BROKER_DETAIL_MODE) == Vendor.BROKER_DETAIL_MODE)
            screen = new BrokerDistGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Vendor.MESSAGE_DETAIL_MODE) == Vendor.MESSAGE_DETAIL_MODE)
            screen = new MessageDetailGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & Vendor.MESSAGE_LOG_MODE) == Vendor.MESSAGE_LOG_MODE)
            screen = new MessageLogGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == Vendor.VENDOR_DETAIL_MODE)
            screen = new VendorApTrxGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new VendorScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new VendorGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", 8, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kCode)
            field = new StringField(this, "Code", 16, null, null);
        //if (iFieldSeq == kContact)
        //  field = new StringField(this, "Contact", 30, null, null);
        if (iFieldSeq == kContactTitle)
            field = new StringField(this, "ContactTitle", 30, null, null);
        if (iFieldSeq == kVendorName)
            field = new StringField(this, "VendorName", 30, null, null);
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
        if (iFieldSeq == kCountryID)
        {
            field = new CountryField(this, "CountryID", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCurrencysID)
        {
            field = new CurrencysField(this, "CurrencysID", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kTel)
        //  field = new PhoneField(this, "Tel", 24, null, null);
        //if (iFieldSeq == kDateEntered)
        //  field = new Vendor_DateEntered(this, "DateEntered", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kDateChanged)
        //  field = new DateField(this, "DateChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kChangedID)
        //  field = new ReferenceField(this, "ChangedID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMessageTransportID)
            field = new MessageTransportSelect(this, "MessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kFax)
        //  field = new FaxField(this, "Fax", 24, null, null);
        //if (iFieldSeq == kEmail)
        //  field = new EMailField(this, "Email", 40, null, null);
        //if (iFieldSeq == kWeb)
        //  field = new URLField(this, "Web", 60, null, null);
        if (iFieldSeq == kMessageServer)
        {
            field = new StringField(this, "MessageServer", 255, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kWSDLPath)
        {
            field = new StringField(this, "WSDLPath", 255, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kProperties)
            field = new PropertiesField(this, "Properties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVendorStatusID)
            field = new VendorStatusField(this, "VendorStatusID", 1, null, null);
        if (iFieldSeq == kPaymentCycleID)
            field = new PaymentCycleField(this, "PaymentCycleID", 1, null, null);
        if (iFieldSeq == kPaymentCodeID)
        {
            field = new PaymentCodeField(this, "PaymentCodeID", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kPrepayTypeID)
        {
            field = new PrepayTypeField(this, "PrepayTypeID", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDepositID)
            field = new DepositField(this, "DepositID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kOperationTypeCode)
            field = new OperationTypeField(this, "OperationTypeCode", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kComments)
        //  field = new MemoField(this, "Comments", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDefaultAccountID)
        {
            field = new AccountField(this, "DefaultAccountID", 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
            field.setMinimumLength(3);
        }
        if (iFieldSeq == kSend1099)
        {
            field = new BooleanField(this, "Send1099", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTaxId)
        {
            field = new StringField(this, "TaxId", 11, null, "");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kVendorsCustNo)
        {
            field = new StringField(this, "VendorsCustNo", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kPassword)
        //  field = new StringField(this, "Password", 16, null, null);
        if (iFieldSeq == kAmountSelected)
        {
            field = new FullCurrencyField(this, "AmountSelected", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kVendorBalance)
        {
            field = new FullCurrencyField(this, "VendorBalance", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == kPostalCodeSort)
        //  field = new StringField(this, "PostalCodeSort", 5, null, null);
        //if (iFieldSeq == kNameSort)
        //  field = new StringField(this, "NameSort", 6, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kVendorLastField)
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
        if (iKeyArea == kCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Code");
            keyArea.addKeyField(kCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kNameSortKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "NameSort");
            keyArea.addKeyField(kNameSort, DBConstants.ASCENDING);
        }
        if (iKeyArea == kCurrencysIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "CurrencysID");
            keyArea.addKeyField(kCurrencysID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kVendorLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kVendorLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Free Method.
     */
    public void free()
    {
        m_recApControl = null;
        super.free();
    }
    /**
     * AddPropertyListeners Method.
     */
    public void addPropertyListeners()
    {
        BaseField fldProperties = this.getField(Vendor.kProperties);
        BaseField fldDisplay = this.getField(Vendor.kMessageServer);
        FieldListener listener = new CopyConvertersHandler(new PropertiesConverter(fldProperties, TrxMessageHeader.DESTINATION_MESSAGE_PARAM));
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        fldDisplay.addListener(listener);
        listener = new CopyConvertersHandler(fldDisplay, new PropertiesConverter(fldProperties, TrxMessageHeader.DESTINATION_MESSAGE_PARAM));
        fldProperties.addListener(listener);
        
        fldDisplay = this.getField(Vendor.kWSDLPath);
        listener = new CopyConvertersHandler(new PropertiesConverter(fldProperties, TrxMessageHeader.WSDL_PATH));
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        fldDisplay.addListener(listener);
        listener = new CopyConvertersHandler(fldDisplay, new PropertiesConverter(fldProperties, TrxMessageHeader.WSDL_PATH));
        fldProperties.addListener(listener);
    }
    /**
     * Add the behaviors to calculate the "Amount Selected" and Balance field.
     */
    public ApTrx addSelectBehaviors()
    {
        RecordOwner recordOwner = Utility.getRecordOwner(this);
        ApTrx recApTrx2 = new ApTrx(recordOwner);      // Don't add second copy to screen
        if (recordOwner != null)
            recordOwner.removeRecord(recApTrx2);
        this.addListener(new FreeOnFreeHandler(recApTrx2));  // ...but be sure to free it
        recApTrx2.addListener(new SubFileFilter(this));
        this.addListener(new RecountOnValidHandler(recApTrx2));
        
        BooleanField fldTrue = new BooleanField(null, "True", 1, null, new Boolean(true));
        recApTrx2.addListener(new FreeOnFreeHandler(fldTrue));
        recApTrx2.addListener(new CompareFileFilter(ApTrx.kActiveTrx, fldTrue, "=", fldTrue, true));
        
        recApTrx2.addListener(new SubCountHandler(this.getField(Vendor.kAmountSelected), ApTrx.kAmountSelected, true, true));
        recApTrx2.addListener(new SubCountHandler(this.getField(Vendor.kVendorBalance), ApTrx.kInvoiceBalance, true, true));
        
        return recApTrx2;
    }
    /**
     * Convert the command to the screen document type.
     * @param strCommand The command text.
     * @param The standard document type (MAINT/DISPLAY/SELECT/MENU/etc).
     */
    public int commandToDocType(String strCommand)
    {
        if (Vendor.MESSAGE_DETAIL_SCREEN.equalsIgnoreCase(strCommand))
            return Vendor.MESSAGE_DETAIL_MODE;
        if (Vendor.MESSAGE_LOG_SCREEN.equalsIgnoreCase(strCommand))
            return Vendor.MESSAGE_LOG_MODE;
        return super.commandToDocType(strCommand);
    }
    /**
     * GetNextMessageDetailTarget Method.
     */
    public MessageDetailTarget getNextMessageDetailTarget()
    {
        if (m_recApControl == null)
        {
            RecordOwner recordOwner = Utility.getRecordOwner(this);
            m_recApControl = new ApControl(recordOwner);
            if (recordOwner != null)
                recordOwner.removeRecord(m_recApControl);
            this.addListener(new FreeOnFreeHandler(m_recApControl));
        }
        return m_recApControl;
    }
    /**
     * Get the message properties for this vendor.
     * @param strMessageName The message name.
     * @return A map with the message properties.
     */
    public TrxMessageHeader addMessageProperties(TrxMessageHeader trxMessageHeader)
    {
        return trxMessageHeader;
    }
    /**
     * Add the information from this record to this message header.
     */
    public MessageTransport getMessageTransport(TrxMessageHeader trxMessageHeader)
    {
        return (MessageTransport)((ReferenceField)this.getField(Vendor.kMessageTransportID)).getReference();
    }
    /**
     * Add the destination information of this person to the message.
     */
    public TrxMessageHeader addDestInfo(TrxMessageHeader trxMessageHeader)
    {
        String strMessageTransport = (String)trxMessageHeader.get(MessageTransport.SEND_MESSAGE_BY_PARAM);
        
        if (((MessageTransport.SOAP.equalsIgnoreCase(strMessageTransport)) || (MessageTransport.XML.equalsIgnoreCase(strMessageTransport)))
            && (trxMessageHeader.get(TrxMessageHeader.DESTINATION_PARAM) == null))
        {
            String strDestination = ((PropertiesField)this.getField(Vendor.kProperties)).getProperty(TrxMessageHeader.DESTINATION_MESSAGE_PARAM);
            if (strDestination != null)
            {
                if (strDestination.startsWith("/"))
                    if (!this.getField(Person.kWeb).isNull())
                        strDestination = this.getField(Person.kWeb).toString() + strDestination;
                trxMessageHeader.put(TrxMessageHeader.DESTINATION_PARAM, strDestination);
            }
            else
                trxMessageHeader.put(TrxMessageHeader.DESTINATION_PARAM, this.getField(Person.kWeb).toString());
        }
        
        trxMessageHeader = super.addDestInfo(trxMessageHeader);
        
        return trxMessageHeader;
    }
    /**
     * SetProperty Method.
     */
    public boolean setProperty(String strKey, String strProperty)
    {
        if (TrxMessageHeader.DESTINATION_PARAM.equalsIgnoreCase(strKey))
            this.getField(Vendor.kWeb).setString(strProperty);
        else
            ((PropertiesField)this.getField(Vendor.kProperties)).setProperty(strKey, strProperty);
        return true;
    }
    /**
     * Get this property.
     */
    public String getProperty(String strKey)
    {
        if (TrxMessageHeader.DESTINATION_PARAM.equalsIgnoreCase(strKey))
            return this.getField(Vendor.kWeb).toString();
        else
            return ((PropertiesField)this.getField(Vendor.kProperties)).getProperty(strKey);
    }

}
