/**
 * @(#)PaymentRequest.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.acctpay.screen.check.*;
import com.tourapp.tour.acctpay.screen.pymtreq.*;
import com.tourapp.tour.acctpay.screen.genpymt.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.model.tour.acctpay.db.*;

/**
 *  PaymentRequest - Payment Request.
 */
public class PaymentRequest extends VirtualRecord
     implements PaymentRequestModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kBankAcctID = kVirtualRecordLastField + 1;
    public static final int kVendorID = kBankAcctID + 1;
    public static final int kAmount = kVendorID + 1;
    public static final int kTrxStatusID = kAmount + 1;
    public static final int kAccountID = kTrxStatusID + 1;
    public static final int kCheckNo = kAccountID + 1;
    public static final int kComments = kCheckNo + 1;
    public static final int kPaymentRequestLastField = kComments;
    public static final int kPaymentRequestFields = kComments - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kBankAcctIDKey = kIDKey + 1;
    public static final int kPaymentRequestLastKey = kBankAcctIDKey;
    public static final int kPaymentRequestKeys = kBankAcctIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final int PRINT_CHECK_SCREEN = ScreenConstants.DETAIL_MODE;
    public static final int CHECK_POST = ScreenConstants.POST_MODE;
    /**
     * Default constructor.
     */
    public PaymentRequest()
    {
        super();
    }
    /**
     * Constructor.
     */
    public PaymentRequest(RecordOwner screen)
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

    public static final String kPaymentRequestFile = "PaymentRequest";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kPaymentRequestFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Check request";
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
        return DBConstants.LOCAL;
    }
    /**
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == PaymentRequest.PRINT_CHECK_SCREEN)
            screen = new PrintCheckJournal(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == PaymentRequest.CHECK_POST)
            screen = new PrintCheckPost(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new PaymentRequestScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new PaymentRequestGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kBankAcctID)
        {
            field = new BankAcctField(this, "BankAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kVendorID)
            field = new VendorField(this, "VendorID", 6, null, null);
        if (iFieldSeq == kAmount)
            field = new CurrencyField(this, "Amount", 12, null, null);
        if (iFieldSeq == kTrxStatusID)
            field = new TrxStatusField(this, "TrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAccountID)
        {
            field = new AccountField(this, "AccountID", 7, null, null);
            field.addListener(new InitOnceFieldHandler(null));
            field.setMinimumLength(3);
        }
        if (iFieldSeq == kCheckNo)
            field = new IntegerField(this, "CheckNo", 8, null, null);
        if (iFieldSeq == kComments)
            field = new StringField(this, "Comments", 30, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kPaymentRequestLastField)
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
        if (iKeyArea == kBankAcctIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BankAcctID");
            keyArea.addKeyField(kBankAcctID, DBConstants.ASCENDING);
            keyArea.addKeyField(kVendorID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kPaymentRequestLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kPaymentRequestLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
