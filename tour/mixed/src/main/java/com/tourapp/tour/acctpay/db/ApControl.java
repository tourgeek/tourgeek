/**
 * @(#)ApControl.
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
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.base.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.model.tour.acctpay.db.*;

/**
 *  ApControl - A/P Control File.
 */
public class ApControl extends ControlRecord
     implements ApControlModel, MessageDetailTarget
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kAutoApCode = kControlRecordLastField + 1;
    public static final int kApBankAcctID = kAutoApCode + 1;
    public static final int kApAccountID = kApBankAcctID + 1;
    public static final int kBrokerVendorID = kApAccountID + 1;
    public static final int kCountryID = kBrokerVendorID + 1;
    public static final int kCurrencysID = kCountryID + 1;
    public static final int kMessageTransportID = kCurrencysID + 1;
    public static final int kVendorStatusID = kMessageTransportID + 1;
    public static final int kPaymentCycleID = kVendorStatusID + 1;
    public static final int kPaymentCodeID = kPaymentCycleID + 1;
    public static final int kPrepayTypeID = kPaymentCodeID + 1;
    public static final int kNonTourApAccountID = kPrepayTypeID + 1;
    public static final int kCostAccountID = kNonTourApAccountID + 1;
    public static final int kCurrOUAccountID = kCostAccountID + 1;
    public static final int kPrepayAccountID = kCurrOUAccountID + 1;
    public static final int kNonTourPrepayAccountID = kPrepayAccountID + 1;
    public static final int kAirAccountID = kNonTourPrepayAccountID + 1;
    public static final int kPPTicAccountID = kAirAccountID + 1;
    public static final int kArcSummaryAccountID = kPPTicAccountID + 1;
    public static final int kOverrideRecAccountID = kArcSummaryAccountID + 1;
    public static final int kOverrideSummAccountID = kOverrideRecAccountID + 1;
    public static final int kOverrideGainLossAccountID = kOverrideSummAccountID + 1;
    public static final int kTen99Template = kOverrideGainLossAccountID + 1;
    public static final int kApControlLastField = kTen99Template;
    public static final int kApControlFields = kTen99Template - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kApControlLastKey = kIDKey;
    public static final int kApControlKeys = kIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public ApControl()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ApControl(RecordOwner screen)
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

    public static final String kApControlFile = "ApControl";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kApControlFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        if (iFieldSeq == kAutoApCode)
            field = new BooleanField(this, "AutoApCode", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kApBankAcctID)
            field = new BankAcctField(this, "ApBankAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kApAccountID)
            field = new AccountField(this, "ApAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBrokerVendorID)
            field = new VendorField(this, "BrokerVendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCountryID)
            field = new CountryField(this, "CountryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrencysID)
            field = new CurrencysField(this, "CurrencysID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMessageTransportID)
            field = new MessageTransportField(this, "MessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVendorStatusID)
            field = new VendorStatusField(this, "VendorStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPaymentCycleID)
            field = new PaymentCycleField(this, "PaymentCycleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPaymentCodeID)
            field = new PaymentCodeField(this, "PaymentCodeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPrepayTypeID)
            field = new PrepayTypeField(this, "PrepayTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNonTourApAccountID)
            field = new AccountField(this, "NonTourApAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCostAccountID)
            field = new AccountField(this, "CostAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCurrOUAccountID)
            field = new AccountField(this, "CurrOUAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPrepayAccountID)
            field = new AccountField(this, "PrepayAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNonTourPrepayAccountID)
            field = new AccountField(this, "NonTourPrepayAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAirAccountID)
            field = new AccountField(this, "AirAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPPTicAccountID)
            field = new AccountField(this, "PPTicAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kArcSummaryAccountID)
            field = new AccountField(this, "ArcSummaryAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kOverrideRecAccountID)
            field = new AccountField(this, "OverrideRecAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kOverrideSummAccountID)
            field = new AccountField(this, "OverrideSummAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kOverrideGainLossAccountID)
            field = new AccountField(this, "OverrideGainLossAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTen99Template)
            field = new StringField(this, "Ten99Template", Constants.DEFAULT_FIELD_LENGTH, null, "ten99");
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kApControlLastField)
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
        if (keyArea == null) if (iKeyArea < kApControlLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kApControlLastKey)
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
     * Get the message properties for this product.
     * @param strMessageName The message name.
     * @return A map with the message properties.
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
        return (MessageTransport)((ReferenceField)this.getField(ApControl.kMessageTransportID)).getReference();
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
     * Get this propery.
     */
    public String getProperty(String strKey)
    {
        return null;
    }

}
