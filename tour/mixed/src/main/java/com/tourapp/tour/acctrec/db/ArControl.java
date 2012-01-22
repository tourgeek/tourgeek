/**
 * @(#)ArControl.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.db;

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
import com.tourapp.tour.acctrec.screen.misc.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.product.air.db.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.model.tour.acctrec.db.*;

/**
 *  ArControl - Control File.
 */
public class ArControl extends ControlRecord
     implements ArControlModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kArBankAcctID = kControlRecordLastField + 1;
    public static final int kArAccountID = kArBankAcctID + 1;
    public static final int kMcoRecAccountID = kArAccountID + 1;
    public static final int kMcoVarAccountID = kMcoRecAccountID + 1;
    public static final int kMcoSuspenseAccountID = kMcoVarAccountID + 1;
    public static final int kMcoCommPer = kMcoSuspenseAccountID + 1;
    public static final int kMcoSvcPer = kMcoCommPer + 1;
    public static final int kMcoTaxPer = kMcoSvcPer + 1;
    public static final int kNonTourAccountID = kMcoTaxPer + 1;
    public static final int kRefundBankAcctID = kNonTourAccountID + 1;
    public static final int kRefundSuspenseAccountID = kRefundBankAcctID + 1;
    public static final int kAirlineID = kRefundSuspenseAccountID + 1;
    public static final int kCardID = kAirlineID + 1;
    public static final int kCreditCardRecAccountID = kCardID + 1;
    public static final int kCreditCardSuspenseAccountID = kCreditCardRecAccountID + 1;
    public static final int kCreditCardVarAccountID = kCreditCardSuspenseAccountID + 1;
    public static final int kCreditCardSvcPer = kCreditCardVarAccountID + 1;
    public static final int kCreditDebitAccountID = kCreditCardSvcPer + 1;
    public static final int kArControlLastField = kCreditDebitAccountID;
    public static final int kArControlFields = kCreditDebitAccountID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kArControlLastKey = kIDKey;
    public static final int kArControlKeys = kIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public ArControl()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ArControl(RecordOwner screen)
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

    public static final String kArControlFile = "ArControl";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kArControlFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Control";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "acctrec";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(AR_CONTROL_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(AR_CONTROL_SCREEN_2_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
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
        if (iFieldSeq == kArBankAcctID)
            field = new BankAcctField(this, "ArBankAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kArAccountID)
            field = new AccountField(this, "ArAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMcoRecAccountID)
            field = new AccountField(this, "McoRecAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMcoVarAccountID)
            field = new AccountField(this, "McoVarAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMcoSuspenseAccountID)
            field = new AccountField(this, "McoSuspenseAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMcoCommPer)
            field = new PercentField(this, "McoCommPer", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMcoSvcPer)
            field = new PercentField(this, "McoSvcPer", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMcoTaxPer)
            field = new PercentField(this, "McoTaxPer", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNonTourAccountID)
            field = new AccountField(this, "NonTourAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRefundBankAcctID)
            field = new BankAcctField(this, "RefundBankAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRefundSuspenseAccountID)
            field = new AccountField(this, "RefundSuspenseAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAirlineID)
            field = new AirlineField(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCardID)
            field = new CardField(this, "CardID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCreditCardRecAccountID)
            field = new AccountField(this, "CreditCardRecAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCreditCardSuspenseAccountID)
            field = new AccountField(this, "CreditCardSuspenseAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCreditCardVarAccountID)
            field = new AccountField(this, "CreditCardVarAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCreditCardSvcPer)
            field = new PercentField(this, "CreditCardSvcPer", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCreditDebitAccountID)
            field = new AccountField(this, "CreditDebitAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kArControlLastField)
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
        if (keyArea == null) if (iKeyArea < kArControlLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kArControlLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}
