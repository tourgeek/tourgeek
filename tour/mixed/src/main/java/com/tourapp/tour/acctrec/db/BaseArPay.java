/**
 * @(#)BaseArPay.
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
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.model.tour.acctrec.db.*;

/**
 *  BaseArPay - Base payment instrument for A/R (Mcos, Credit Cards, etc.).
 */
public class BaseArPay extends BaseTrx
     implements BaseArPayModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kTrxStatusID = kTrxStatusID;
    //public static final int kTrxDate = kTrxDate;
    public static final int kAmtApply = kAmountLocal;
    //public static final int kTrxEntry = kTrxEntry;
    //public static final int kTrxUserID = kTrxUserID;
    public static final int kBookingID = kBaseTrxLastField + 1;
    public static final int kGross = kBookingID + 1;
    public static final int kSvcPer = kGross + 1;
    public static final int kSvcAmt = kSvcPer + 1;
    public static final int kNet = kSvcAmt + 1;
    public static final int kComments = kNet + 1;
    public static final int kDateSubmitted = kComments + 1;
    public static final int kDatePaid = kDateSubmitted + 1;
    public static final int kAmountPaid = kDatePaid + 1;
    public static final int kPaid = kAmountPaid + 1;
    public static final int kPaymentEntered = kPaid + 1;
    public static final int kBaseArPayLastField = kPaymentEntered;
    public static final int kBaseArPayFields = kPaymentEntered - DBConstants.MAIN_FIELD + 1;
    public static final int POST = ScreenConstants.POST_MODE;
    public static final int ENTRY_SCREEN = ScreenConstants.DISPLAY_MODE | ScreenConstants.LAST_MODE;
    public static final int ENTRY_GRID_SCREEN = ScreenConstants.DISPLAY_MODE | ScreenConstants.LAST_MODE * 2;
    public static final int COLL_POST = ScreenConstants.DISPLAY_MODE | ScreenConstants.LAST_MODE * 4;
    public static final int COLL_SCREEN = ScreenConstants.DISPLAY_MODE | ScreenConstants.LAST_MODE * 8;
    public static final int APPROVE_SCREEN = ScreenConstants.DISPLAY_MODE | ScreenConstants.LAST_MODE * 16;
    public static final int SUBMIT_SCREEN = ScreenConstants.DISPLAY_MODE | ScreenConstants.LAST_MODE * 32;
    public static final int DISTRIBUTION_SCREEN = ScreenConstants.DETAIL_MODE | ScreenConstants.LAST_MODE * 64;
    /**
     * Default constructor.
     */
    public BaseArPay()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BaseArPay(RecordOwner screen)
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

    public static final String kBaseArPayFile = null; // Screen field
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
        if (iFieldSeq == kBookingID)
            field = new BookingField(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTrxStatusID)
        //  field = new TrxStatusField(this, "TrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxDate)
            field = new BaseArPay_TrxDate(this, "TrxDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kGross)
            field = new CurrencyField(this, "Gross", 10, null, null);
        if (iFieldSeq == kSvcPer)
        {
            field = new PercentField(this, "SvcPer", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSvcAmt)
            field = new CurrencyField(this, "SvcAmt", 8, null, null);
        if (iFieldSeq == kNet)
            field = new CurrencyField(this, "Net", 10, null, null);
        if (iFieldSeq == kAmtApply)
            field = new CurrencyField(this, "AmtApply", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kComments)
            field = new StringField(this, "Comments", 30, null, null);
        //if (iFieldSeq == kTrxEntry)
        //  field = new BaseArPay_TrxEntry(this, "TrxEntry", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTrxUserID)
        //  field = new BaseArPay_TrxUserID(this, "TrxUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDateSubmitted)
            field = new DateTimeField(this, "DateSubmitted", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDatePaid)
            field = new DateTimeField(this, "DatePaid", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAmountPaid)
            field = new CurrencyField(this, "AmountPaid", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPaid)
        {
            field = new BooleanField(this, "Paid", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kPaymentEntered)
            field = new DateTimeField(this, "PaymentEntered", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBaseArPayLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Convert the command to the screen document type.
     * @param strCommand The command text.
     * @param The standard document type (MAINT/DISPLAY/SELECT/MENU/etc).
     */
    public int commandToDocType(String strCommand)
    {
        if (AcctDetailDist.DIST_DISTRIBUTION.equalsIgnoreCase(strCommand))
            return BaseArPay.DISTRIBUTION_SCREEN;
        return super.commandToDocType(strCommand);
    }

}
