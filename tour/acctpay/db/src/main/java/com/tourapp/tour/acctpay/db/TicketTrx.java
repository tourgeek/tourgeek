/**
  * @(#)TicketTrx.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctpay.db;

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
import org.jbundle.base.db.shared.*;
import com.tourapp.tour.base.field.*;
import com.tourapp.model.tour.acctpay.db.*;

/**
 *  TicketTrx - Airline Tickets.
 */
public class TicketTrx extends ApTrx
     implements TicketTrxModel
{
    private static final long serialVersionUID = 1L;

    public static final int ARC_REPORT_POST = ScreenConstants.DETAIL_MODE;
    public static final int OVERRIDE_SCREEN = ScreenConstants.DETAIL_MODE | 128;
    public static final int OVERRIDE_GRID_SCREEN = ScreenConstants.DETAIL_MODE | 256;
    /**
     * Default constructor.
     */
    public TicketTrx()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TicketTrx(RecordOwner screen)
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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(TICKET_TRX_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Ticket transaction";
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
        return DBConstants.REMOTE | DBConstants.SHARED_TABLE | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == TicketTrx.ARC_REPORT_POST)
            screen = Record.makeNewScreen(ARC_REPORT_POST_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if (iDocMode == TicketTrx.OVERRIDE_SCREEN)
            screen = Record.makeNewScreen(OVERRIDE_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if (iDocMode == TicketTrx.OVERRIDE_GRID_SCREEN)
            screen = Record.makeNewScreen(OVERRIDE_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(TICKET_TRX_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = Record.makeNewScreen(TICKET_TRX_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
        //if (iFieldSeq == 3)
        //  field = new TrxStatusField(this, TRX_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 4)
        //  field = new TicketTrx_TrxUserID(this, TRX_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 5)
        //  field = new StringField(this, CODE, 28, null, null);
        //if (iFieldSeq == 6)
        //  field = new StringField(this, DESCRIPTION, 50, null, null);
        //if (iFieldSeq == 7)
        //{
        //  field = new IntegerField(this, AP_TRX_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(ApTrx.AP_TRX_TYPE));
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 8)
        //  field = new BooleanField(this, ACTIVE_TRX, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        //if (iFieldSeq == 9)
        //  field = new VendorField(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 10)
        //  field = new TourField(this, TOUR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 11)
        //  field = new ReferenceField(this, PRODUCT_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 12)
        //{
        //  field = new DateField(this, START_SERVICE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 13)
        //{
        //  field = new DateField(this, END_SERVICE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 14)
        //{
        //  field = new DateField(this, FINALIZATION_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 15)
        //  field = new DateField(this, ORDER_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 16)
        //{
        //  field = new DateField(this, ACKNOWLEDGE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 17)
        //{
        //  field = new DateField(this, ACKNOWLEDGED_ON, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 18)
        //  field = new ReferenceField(this, ACKNOWLEDGED_BY, 16, null, null);
        //if (iFieldSeq == 19)
        //{
        //  field = new StringField(this, VENDOR_CONFIRMATION_NO, 20, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 20)
        //  field = new StringField(this, VENDOR_CONF_STATUS, 2, null, null);
        //if (iFieldSeq == 21)
        //  field = new FullCurrencyField(this, DEPARTURE_ESTIMATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 22)
        //{
        //  field = new RealField(this, DEPARTURE_EXCHANGE, 10, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 23)
        //  field = new CurrencyField(this, DEPARTURE_ESTIMATE_LOCAL, 10, null, null);
        if (iFieldSeq == 24)
        {
            field = new DateField(this, DEPARTURE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == 25)
        //  field = new StringField(this, INVOICE_NO, 28, null, null);
        //if (iFieldSeq == 26)
        //  field = new DateField(this, INVOICE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 27)
        //  field = new FullCurrencyField(this, INVOICE_AMOUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 28)
        //  field = new CurrencyField(this, INVOICE_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 29)
        //  field = new FullCurrencyField(this, INVOICE_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 30)
        //  field = new CurrencyField(this, INVOICE_BALANCE_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 31)
        //  field = new FullCurrencyField(this, AMOUNT_SELECTED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 32)
        //  field = new VendorField(this, DRAFT_VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 33)
        //  field = new ApTrxField(this, PREPAYMENT_AP_TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 34)
        //  field = new DateField(this, VOUCHER_BASED_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 35)
        //  field = new DateTimeField(this, TRX_ENTRY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 36)
        //  field = new AccountField(this, ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 37)
        {
            field = new StringField(this, TICKET_NUMBER, 28, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 38)
        {
            field = new DateField(this, ISSUE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 39)
            field = new AirlineField(this, AIRLINE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 40)
            field = new DateField(this, ARC_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 41)
            field = new DateField(this, ARC_PAY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 42)
            field = new StringField(this, INTL, 1, null, null);
        if (iFieldSeq == 43)
        {
            field = new BooleanField(this, CREDIT_CARD, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 44)
            field = new CurrencyField(this, TOTAL, 10, null, null);
        if (iFieldSeq == 45)
            field = new CurrencyField(this, FARE, 10, null, null);
        if (iFieldSeq == 46)
            field = new CurrencyField(this, COMM_AMOUNT, 9, null, null);
        if (iFieldSeq == 47)
            field = new PercentField(this, COMM_PERCENT, 5, null, null);
        if (iFieldSeq == 48)
            field = new CurrencyField(this, TAX_AMOUNT, 9, null, null);
        if (iFieldSeq == 49)
        {
            field = new PercentField(this, TAX_PERCENT, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 50)
            field = new CurrencyField(this, NET_FARE, 10, null, null);
        if (iFieldSeq == 51)
            field = new CurrencyField(this, COST_AMOUNT, 10, null, null);
        if (iFieldSeq == 52)
            field = new PercentField(this, OVERRIDE_PERCENT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 53)
            field = new CurrencyField(this, OVERRIDE_AMOUNT, 10, null, null);
        if (iFieldSeq == 54)
            field = new DateField(this, OVERRIDE_PAID_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 55)
            field = new CurrencyField(this, OVERRIDE_PAID, 10, null, null);
        if (iFieldSeq == 56)
            field = new DateField(this, VOID_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, CODE_KEY);
            keyArea.addKeyField(CODE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, VENDOR_ID_KEY);
            keyArea.addKeyField(VENDOR_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(ACTIVE_TRX, DBConstants.ASCENDING);
            keyArea.addKeyField(START_SERVICE_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, TOUR_ID_KEY);
            keyArea.addKeyField(TOUR_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(VENDOR_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(PRODUCT_TYPE_ID, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Add all standard file & field behaviors.
     * Override this to add record listeners and filters.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.addListener(new SharedFileHandler(ApTrx.AP_TRX_TYPE_ID, ApTrx.TICKET_TRX_TYPE));
    }

}
