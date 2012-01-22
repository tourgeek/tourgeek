/**
 * @(#)Mco.
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
import com.tourapp.tour.acctrec.screen.mco.*;
import com.tourapp.tour.acctrec.screen.mco.trx.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.product.air.db.*;
import com.tourapp.model.tour.acctrec.db.*;

/**
 *  Mco - MCOs.
 */
public class Mco extends BaseArPay
     implements McoModel
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
    public static final int kMcoNo = kBaseArPayLastField + 1;
    public static final int kAirlineID = kMcoNo + 1;
    public static final int kCommPer = kAirlineID + 1;
    public static final int kCommAmt = kCommPer + 1;
    public static final int kTaxPer = kCommAmt + 1;
    public static final int kTaxAmt = kTaxPer + 1;
    public static final int kCarrierSvcPer = kTaxAmt + 1;
    public static final int kMcoLastField = kCarrierSvcPer;
    public static final int kMcoFields = kCarrierSvcPer - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kTrxStatusIDKey = kIDKey + 1;
    public static final int kMcoNoKey = kTrxStatusIDKey + 1;
    public static final int kMcoLastKey = kMcoNoKey;
    public static final int kMcoKeys = kMcoNoKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public Mco()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Mco(RecordOwner screen)
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

    public static final String kMcoFile = "Mco";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kMcoFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "MCO";
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
            screen = new McoDistGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == ScreenConstants.POST_MODE)
            screen = new McoPost(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == Mco.ENTRY_SCREEN)
            screen = new McoEntryScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == Mco.ENTRY_GRID_SCREEN)
            screen = new McoEntryGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == Mco.COLL_POST)
            screen = new McoCollPost(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == Mco.COLL_SCREEN)
            screen = new McoCollScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == ScreenConstants.DETAIL_MODE)
            screen = new McoBatchDistGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new McoScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new McoGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kMcoNo)
            field = new StringField(this, "McoNo", 16, null, null);
        //if (iFieldSeq == kTrxStatusID)
        //  field = new TrxStatusField(this, "TrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAirlineID)
        {
            field = new AirlineField(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kTrxDate)
        //  field = new Mco_TrxDate(this, "TrxDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kGross)
            field = new CurrencyField(this, "Gross", 10, null, null);
        if (iFieldSeq == kCommPer)
            field = new PercentField(this, "CommPer", 5, null, new Float(0.10));
        if (iFieldSeq == kCommAmt)
        {
            field = new CurrencyField(this, "CommAmt", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kSvcPer)
        //{
        //  field = new PercentField(this, "SvcPer", 5, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kSvcAmt)
        //  field = new CurrencyField(this, "SvcAmt", 8, null, null);
        if (iFieldSeq == kTaxPer)
        {
            field = new PercentField(this, "TaxPer", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTaxAmt)
        {
            field = new CurrencyField(this, "TaxAmt", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kNet)
            field = new CurrencyField(this, "Net", 10, null, null);
        //if (iFieldSeq == kAmtApply)
        //  field = new CurrencyField(this, "AmtApply", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kComments)
        //  field = new StringField(this, "Comments", 30, null, null);
        //if (iFieldSeq == kTrxEntry)
        //  field = new Mco_TrxEntry(this, "TrxEntry", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kDateSubmitted)
        //  field = new DateTimeField(this, "DateSubmitted", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kDatePaid)
        //  field = new DateTimeField(this, "DatePaid", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kAmountPaid)
        //  field = new CurrencyField(this, "AmountPaid", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kPaymentEntered)
        //  field = new DateTimeField(this, "PaymentEntered", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCarrierSvcPer)
            field = new PercentField(this, "CarrierSvcPer", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kMcoLastField)
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
        if (iKeyArea == kMcoNoKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "McoNo");
            keyArea.addKeyField(kMcoNo, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kMcoLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kMcoLastKey)
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
        
        this.getField(Mco.kGross).addListener(new CalcBalanceHandler(this.getField(Mco.kCommAmt), this.getField(Mco.kGross), this.getField(Mco.kCommPer), "*", false));
        this.getField(Mco.kCommPer).addListener(new CalcBalanceHandler(this.getField(Mco.kCommAmt), this.getField(Mco.kGross), this.getField(Mco.kCommPer), "*", false));
        this.getField(Mco.kGross).addListener(new CalcBalanceHandler(this.getField(Mco.kSvcAmt), this.getField(Mco.kGross), this.getField(Mco.kSvcPer), "*", false));
        this.getField(Mco.kSvcPer).addListener(new CalcBalanceHandler(this.getField(Mco.kSvcAmt), this.getField(Mco.kGross), this.getField(Mco.kSvcPer), "*", false));
        this.getField(Mco.kGross).addListener(new CalcBalanceHandler(this.getField(Mco.kTaxAmt), this.getField(Mco.kGross), this.getField(Mco.kTaxPer), "*", false));
        this.getField(Mco.kTaxPer).addListener(new CalcBalanceHandler(this.getField(Mco.kTaxAmt), this.getField(Mco.kGross), this.getField(Mco.kTaxPer), "*", false));
        this.getField(Mco.kGross).addListener(new CalcMcoHandler(null));
        this.getField(Mco.kCommAmt).addListener(new CalcMcoHandler(null));
        this.getField(Mco.kSvcAmt).addListener(new CalcMcoHandler(null));
        this.getField(Mco.kTaxAmt).addListener(new CalcMcoHandler(null));
        this.getField(Mco.kNet).addListener(new MoveOnChangeHandler(this.getField(Mco.kAmtApply), this.getField(Mco.kNet)));
    }

}
