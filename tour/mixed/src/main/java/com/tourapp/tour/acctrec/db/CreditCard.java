/**
 * @(#)CreditCard.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.acctrec.screen.credit.*;
import com.tourapp.tour.acctrec.screen.credit.trx.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.model.tour.acctrec.db.*;

/**
 *  CreditCard - Credit Cards.
 */
public class CreditCard extends BaseArPay
     implements CreditCardModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kBookingID = kBookingID;
    //public static final int kTrxStatusID = kTrxStatusID;
    //public static final int kTrxDate = kTrxDate;
    //public static final int kGross = kGross;
    //public static final int kSvcPer = kSvcPer;
    //public static final int kSvcAmt = kSvcAmt;
    //public static final int kNet = kNet;
    //public static final int kAmtApply = kAmtApply;
    //public static final int kComments = kComments;
    //public static final int kTrxEntry = kTrxEntry;
    //public static final int kDateSubmitted = kDateSubmitted;
    //public static final int kDatePaid = kDatePaid;
    //public static final int kAmountPaid = kAmountPaid;
    //public static final int kPaymentEntered = kPaymentEntered;
    public static final int kCardID = kBaseArPayLastField + 1;
    public static final int kCardNo = kCardID + 1;
    public static final int kExpiration = kCardNo + 1;
    public static final int kDateApproved = kExpiration + 1;
    public static final int kCreditCardLastField = kDateApproved;
    public static final int kCreditCardFields = kDateApproved - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kTrxStatusIDKey = kIDKey + 1;
    public static final int kTrxDateKey = kTrxStatusIDKey + 1;
    public static final int kCreditCardLastKey = kTrxDateKey;
    public static final int kCreditCardKeys = kTrxDateKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public CreditCard()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CreditCard(RecordOwner screen)
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

    public static final String kCreditCardFile = "CreditCard";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kCreditCardFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Credit transaction";
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
        return DBConstants.REMOTE | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & BaseArPay.DISTRIBUTION_SCREEN) == BaseArPay.DISTRIBUTION_SCREEN)
            screen = new CreditCardDistGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == ScreenConstants.POST_MODE)
            screen = new CreditCardPost(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == CreditCard.ENTRY_SCREEN)
            screen = new CreditCardEntryScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == CreditCard.ENTRY_GRID_SCREEN)
            screen = new CreditCardEntryGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == CreditCard.COLL_POST)
            screen = new CreditCardCollPost(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == CreditCard.SUBMIT_SCREEN)
            screen = new CreditSubmitGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == CreditCard.COLL_SCREEN)
            screen = new CreditCollectScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == CreditCard.APPROVE_SCREEN)
            screen = new CreditApproveGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == ScreenConstants.DETAIL_MODE)
            screen = new CreditCardBatchDistGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new CreditCardScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new CreditCardGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        //if (iFieldSeq == kBookingID)
        //  field = new BookingField(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCardID)
            field = new CardField(this, "CardID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCardNo)
            field = new CreditCardField(this, "CardNo", 20, null, null);
        if (iFieldSeq == kExpiration)
            field = new StringField(this, "Expiration", 5, null, null);
        //if (iFieldSeq == kTrxStatusID)
        //  field = new TrxStatusField(this, "TrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTrxDate)
        //  field = new CreditCard_TrxDate(this, "TrxDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kGross)
        //  field = new CurrencyField(this, "Gross", 10, null, null);
        //if (iFieldSeq == kSvcPer)
        //{
        //  field = new PercentField(this, "SvcPer", 5, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kSvcAmt)
        //  field = new CurrencyField(this, "SvcAmt", 8, null, null);
        //if (iFieldSeq == kNet)
        //  field = new CurrencyField(this, "Net", 10, null, null);
        //if (iFieldSeq == kAmtApply)
        //  field = new CurrencyField(this, "AmtApply", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kComments)
        //  field = new StringField(this, "Comments", 30, null, null);
        //if (iFieldSeq == kTrxEntry)
        //  field = new CreditCard_TrxEntry(this, "TrxEntry", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kDateSubmitted)
        //  field = new DateTimeField(this, "DateSubmitted", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kDatePaid)
        //  field = new DateTimeField(this, "DatePaid", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kAmountPaid)
        //  field = new CurrencyField(this, "AmountPaid", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kPaymentEntered)
        //  field = new DateTimeField(this, "PaymentEntered", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDateApproved)
            field = new DateTimeField(this, "DateApproved", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCreditCardLastField)
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
        if (iKeyArea == kTrxStatusIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxStatusID");
            keyArea.addKeyField(kTrxStatusID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTrxDateKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxDate");
            keyArea.addKeyField(kTrxDate, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kCreditCardLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kCreditCardLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        
        this.getField(CreditCard.kGross).addListener(new CalcBalanceHandler(this.getField(CreditCard.kSvcAmt), this.getField(CreditCard.kGross), this.getField(CreditCard.kSvcPer), "*", false));
        this.getField(CreditCard.kSvcPer).addListener(new CalcBalanceHandler(this.getField(CreditCard.kSvcAmt), this.getField(CreditCard.kGross), this.getField(CreditCard.kSvcPer), "*", false));
        this.getField(CreditCard.kGross).addListener(new CalcBalanceHandler(this.getField(CreditCard.kNet), this.getField(CreditCard.kGross), this.getField(CreditCard.kSvcAmt), "-", false));
        this.getField(CreditCard.kSvcAmt).addListener(new CalcBalanceHandler(this.getField(CreditCard.kNet), this.getField(CreditCard.kGross), this.getField(CreditCard.kSvcAmt), "-", false));
        this.getField(CreditCard.kNet).addListener(new MoveOnChangeHandler(this.getField(CreditCard.kAmtApply), this.getField(CreditCard.kNet)));
    }

}
