/**
 * @(#)ArTrx.
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
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctrec.screen.misc.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctrec.screen.refund.*;
import com.tourapp.tour.acctrec.db.event.*;
import com.tourapp.tour.booking.entry.acctrec.*;
import com.tourapp.tour.acctrec.screen.display.*;

/**
 *  ArTrx - A/R Open File.
 */
public class ArTrx extends LinkTrx
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kTrxDate = kTrxDate;
    //public static final int kTrxStatusID = kTrxStatusID;
    public static final int kAmount = kAmountLocal;
    //public static final int kTrxUserID = kTrxUserID;
    //public static final int kLinkedTrxID = kLinkedTrxID;
    //public static final int kLinkedTrxDescID = kLinkedTrxDescID;
    //public static final int kTrxEntry = kTrxEntry;
    public static final int kBookingID = kLinkTrxLastField + 1;
    public static final int kComments = kBookingID + 1;
    public static final int kDepartureDate = kComments + 1;
    public static final int kArTrxLastField = kDepartureDate;
    public static final int kArTrxFields = kDepartureDate - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kLinkedTrxIDKey = kIDKey + 1;
    public static final int kBookingIDKey = kLinkedTrxIDKey + 1;
    public static final int kTrxStatusIDKey = kBookingIDKey + 1;
    public static final int kArTrxLastKey = kTrxStatusIDKey;
    public static final int kArTrxKeys = kTrxStatusIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final int CR_DR_SCREEN = ScreenConstants.DISPLAY_MODE | 192;
    public static final int REFUND_SCREEN = ScreenConstants.DISPLAY_MODE | 128;
    public static final int REFUND_PEND_SCREEN = ScreenConstants.DISPLAY_MODE | 256;
    public static final int REFUND_CHECK_POST = ScreenConstants.DISPLAY_MODE | 512;
    public static final int REFUND_CHECK_XML_PRINT = ScreenConstants.DISPLAY_MODE | 2048;
    public static final int REFUND_CHECK_CANNED_PRINT = ScreenConstants.DISPLAY_MODE | 4096;
    public static final int DISTRIBUTION_SCREEN = ScreenConstants.DETAIL_MODE | 8192;
    public static final String PAYMENT = "Payment";
    public static final String CANCELLATION_CHARGE = "CancellationCharge";
    public static final String CREDIT_CARD = "CreditCard";
    public static final String CREDIT_MEMO = "CreditMemo";
    public static final String DEBIT_MEMO = "DebitMemo";
    public static final String ELECTRONIC_PAYMENT = "ElectronicPayment";
    public static final String INVOICE = "Invoice";
    public static final String INVOICE_MODIFICATION = "InvoiceModification";
    public static final String MCO = "Mco";
    public static final String REFUND_SUBMITTED = "RefundSubmitted";
    public static final String REFUND_HELD = "RefundHeld";
    public static final String REFUND_PAY = "RefundPay";
    public static final String REFUND_PAID = "RefundPaid";
    public static final String REFUND_PAID_MANUAL = "RefundPaidManual";
    public static final String REFUND_PENDING = "RefundPending";
    /**
     * Default constructor.
     */
    public ArTrx()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ArTrx(RecordOwner screen)
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

    public static final String kArTrxFile = "ArTrx";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kArTrxFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Open Item";
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
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ArTrx.LINK_DISTRIBUTION_SCREEN) == ArTrx.LINK_DISTRIBUTION_SCREEN)
            screen = new ArTrxLinkTrxGridScreen(null, this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode == ScreenConstants.DETAIL_MODE)
            || (iDocMode == ArTrx.DISTRIBUTION_SCREEN))
                screen = new ArTrxDistGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == ArTrx.CR_DR_SCREEN)
            screen = new CrDrScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == ArTrx.REFUND_SCREEN)
            screen = new RefundScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == ArTrx.REFUND_PEND_SCREEN)
            screen = new RefundPendScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == ArTrx.REFUND_CHECK_POST)
            screen = new RefundCheckPost(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == ArTrx.REFUND_CHECK_XML_PRINT)
            screen = new RefundCheckXMLPrint(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == ArTrx.REFUND_CHECK_CANNED_PRINT)
            screen = new RefundCheckPrint(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new BookingArTrxScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new BookingArTrxGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kBookingID)
            field = new BookingField(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxDate)
            field = new ArTrx_TrxDate(this, "TrxDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxStatusID)
            field = new TrxStatusField(this, "TrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAmount)
            field = new CurrencyField(this, "Amount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTrxUserID)
        //  field = new ArTrx_TrxUserID(this, "TrxUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kComments)
            field = new StringField(this, "Comments", 30, null, null);
        //if (iFieldSeq == kLinkedTrxID)
        //  field = new TrxField(this, "LinkedTrxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kLinkedTrxDescID)
        //  field = new TrxDescField(this, "LinkedTrxDescID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kTrxEntry)
        //  field = new ArTrx_TrxEntry(this, "TrxEntry", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDepartureDate)
            field = new DateField(this, "DepartureDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kArTrxLastField)
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
        if (iKeyArea == kLinkedTrxIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "LinkedTrxID");
            keyArea.addKeyField(kLinkedTrxID, DBConstants.ASCENDING);
            keyArea.addKeyField(kLinkedTrxDescID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kBookingIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BookingID");
            keyArea.addKeyField(kBookingID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxDate, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxStatusID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTrxStatusIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxStatusID");
            keyArea.addKeyField(kTrxStatusID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTrxDate, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kArTrxLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kArTrxLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        this.addListener(new NoDeleteModifyHandler(true, true));
    }
    /**
     * Add the booking detail behaviors.
     */
    public void addDetailBehaviors(Booking recBooking)
    {
        this.addListener(new SubFileFilter(recBooking, true));
        this.addListener(new InitArTrxHandler(recBooking));
        if (this.getRecordOwner() instanceof GridScreen)
            recBooking.getField(Booking.kID).addListener(new FieldReSelectHandler((GridScreen)this.getRecordOwner()));
        if (recBooking != null)
        {   // Sub counts must be first.
            this.addListener(new SubCountHandler(recBooking.getField(Booking.kBalance), ArTrx.kAmount, true, true));
        
            this.addListener(new ArTrxInvoiceSubCountHandler(null, ArTrx.kAmount, true, true));
        }
        this.addListener(new CheckBookingStatusHandler(recBooking));
        this.addListener(new UpdateArTrxAcctDetailHandler(recBooking));
    }
    /**
     * Convert the command to the screen document type.
     * @param strCommand The command text.
     * @param The standard document type (MAINT/DISPLAY/SELECT/MENU/etc).
     */
    public int commandToDocType(String strCommand)
    {
        if (AcctDetailDist.DIST_DISTRIBUTION.equalsIgnoreCase(strCommand))
            return ArTrx.DISTRIBUTION_SCREEN;
        return super.commandToDocType(strCommand);
    }

}
