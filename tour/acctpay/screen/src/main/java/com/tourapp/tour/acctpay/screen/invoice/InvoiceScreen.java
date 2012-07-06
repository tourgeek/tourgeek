/**
  * @(#)InvoiceScreen.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctpay.screen.invoice;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctpay.screen.findepest.*;
import com.tourapp.tour.acctpay.screen.trx.*;

/**
 *  InvoiceScreen - Invoice entry.
 */
public class InvoiceScreen extends Screen
{
    /**
     * Default constructor.
     */
    public InvoiceScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public InvoiceScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Invoice entry";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new ApTrx(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new TrxStatus(this);
        new ApControl(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new InvoiceScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.addMainKeyBehavior();
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        
        EnableScreenHandler behavior = new EnableScreenHandler(ApTrx.TRX_STATUS_ID);
        this.getMainRecord().addListener(behavior);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.DEPARTURE_EST_MANUAL);
        behavior.addComparison(recTrxStatus.getField(TrxStatus.ID).getData());
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.DEP_ESTIMATE);
        behavior.addComparison(recTrxStatus.getField(TrxStatus.ID).getData());
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.INVOICE_NON_TOUR);
        behavior.addComparison(recTrxStatus.getField(TrxStatus.ID).getData());
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.CREDIT_INVOICE);
        behavior.addComparison(recTrxStatus.getField(TrxStatus.ID).getData());
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.INVOICE);
        behavior.addComparison(recTrxStatus.getField(TrxStatus.ID).getData());
        
        this.getMainRecord().getField(ApTrx.TRX_STATUS_ID).addListener(new InitFieldHandler(recTrxStatus.getField(TrxStatus.ID)));
        
        // If Invoice date, Invoice number, or amount changes, make sure this is an Invoice
        this.getMainRecord().getField(ApTrx.INVOICE_NO).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.TRX_STATUS_ID), recTrxStatus.getField(TrxStatus.ID)));
        this.getMainRecord().getField(ApTrx.INVOICE_DATE).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.TRX_STATUS_ID), recTrxStatus.getField(TrxStatus.ID)));
        this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.TRX_STATUS_ID), recTrxStatus.getField(TrxStatus.ID)));
        
        // Invoice balance = invoice amount and select for payment
        this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.INVOICE_BALANCE), this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT)));
        this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.AMOUNT_SELECTED), this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT)));
        
        this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT).addListener(new CalcBalanceHandler(this.getMainRecord().getField(ApTrx.INVOICE_LOCAL), this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT), this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.LAST_RATE), CalcBalanceHandler.MULTIPLY, false));
        this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.LAST_RATE).addListener(new CalcBalanceHandler(this.getMainRecord().getField(ApTrx.INVOICE_LOCAL), this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT), this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.LAST_RATE), CalcBalanceHandler.MULTIPLY, false));
        FieldListener listener = null;
        this.getMainRecord().getField(ApTrx.INVOICE_LOCAL).addListener(listener = new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.INVOICE_BALANCE_LOCAL), this.getMainRecord().getField(ApTrx.INVOICE_LOCAL)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, true);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, true);
        // Init these to the last value
        this.getMainRecord().getField(ApTrx.VENDOR_ID).addListener(new InitFieldHandler(this.getScreenRecord().getField(InvoiceScreenRecord.LAST_VENDOR_ID)));
        this.getMainRecord().getField(ApTrx.INVOICE_NO).addListener(new InitFieldHandler(this.getScreenRecord().getField(InvoiceScreenRecord.LAST_INVOICE_NO)));
        this.getMainRecord().getField(ApTrx.INVOICE_DATE).addListener(new InitFieldHandler(this.getScreenRecord().getField(InvoiceScreenRecord.LAST_INVOICE_DATE)));
        // If vendor change, set new last vendor, clear all last.
        this.getMainRecord().getField(ApTrx.VENDOR_ID).addListener(listener = new MoveOnChangeHandler(this.getScreenRecord().getField(InvoiceScreenRecord.LAST_VENDOR_ID), this.getMainRecord().getField(ApTrx.VENDOR_ID)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, true);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, true);
        this.getMainRecord().getField(ApTrx.VENDOR_ID).addListener(listener = new InitOnChangeHandler(this.getScreenRecord().getField(InvoiceScreenRecord.LAST_INVOICE_NO)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.getMainRecord().getField(ApTrx.VENDOR_ID).addListener(listener = new InitOnChangeHandler(this.getScreenRecord().getField(InvoiceScreenRecord.LAST_INVOICE_DATE)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        // Set last invoice and date if change by user
        this.getMainRecord().getField(ApTrx.INVOICE_NO).addListener(listener = new MoveOnChangeHandler(this.getScreenRecord().getField(InvoiceScreenRecord.LAST_INVOICE_NO), this.getMainRecord().getField(ApTrx.INVOICE_NO)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, true);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, true);
        this.getMainRecord().getField(ApTrx.INVOICE_DATE).addListener(listener = new MoveOnChangeHandler(this.getScreenRecord().getField(InvoiceScreenRecord.LAST_INVOICE_DATE), this.getMainRecord().getField(ApTrx.INVOICE_DATE), false, false, true));  // Not if null
        listener.setRespondsToMode(DBConstants.READ_MOVE, true);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, true);
        // If user changes vendor no, clear invoice no and date.
        this.getMainRecord().getField(ApTrx.VENDOR_ID).addListener(listener = new InitOnChangeHandler(this.getMainRecord().getField(ApTrx.INVOICE_NO)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.getMainRecord().getField(ApTrx.VENDOR_ID).addListener(listener = new InitOnChangeHandler(this.getMainRecord().getField(ApTrx.INVOICE_DATE)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        
        // If invoice number or amount changes, and the date hasn't been set yet, set it.
        this.getMainRecord().getField(ApTrx.INVOICE_NO).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.INVOICE_DATE), this.getScreenRecord().getField(InvoiceScreenRecord.LAST_INVOICE_DATE), false, true));    // Only if date is null now.
        this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.INVOICE_DATE), this.getScreenRecord().getField(InvoiceScreenRecord.LAST_INVOICE_DATE), false, true));
        
        // If read dep est and vendor is same as last, set invoice no, date and amount in record
        this.getMainRecord().addListener(new UpdateInvoiceVendorHandler((ScreenRecord)this.getScreenRecord()));
        // If entering Invoice number and amount is null, move dep est amount to Invoice amount
        this.getMainRecord().getField(ApTrx.INVOICE_NO).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT), this.getMainRecord().getField(ApTrx.DEPARTURE_ESTIMATE), false, true));
        
        Record recTour = ((ReferenceField)this.getMainRecord().getField(ApTrx.TOUR_ID)).getReferenceRecord(this);
        this.getMainRecord().getField(ApTrx.TOUR_ID).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.START_SERVICE_DATE), recTour.getField(Tour.DEPARTURE_DATE)));
        this.getMainRecord().getField(ApTrx.START_SERVICE_DATE).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.END_SERVICE_DATE), this.getMainRecord().getField(ApTrx.START_SERVICE_DATE)));
        
        this.getMainRecord().getField(ApTrx.TOUR_ID).addListener(new InvoiceAcctHandler(null));
        this.getMainRecord().getField(ApTrx.VENDOR_ID).addListener(new InvoiceAcctHandler(null));
        this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT).addListener(new InvoiceAcctHandler(null));
        
        // Make sure this is a valid Invoice
        this.getMainRecord().addListener(new ValidateInvoiceHandler(null));
        // Update the G/L on Update or Write
        UpdateInvoiceHandler updateInvoiceHandler = new UpdateInvoiceHandler(null);
        updateInvoiceHandler.setEnabledListener(false);
        this.getMainRecord().addListener(updateInvoiceHandler);
        UpdateNonTourInvoiceHandler invoiceNonTourBehavior = new UpdateNonTourInvoiceHandler(null);
        this.getMainRecord().addListener(invoiceNonTourBehavior);
    }
    /**
     * Enable or disable this control.
     * @param bEnable If true, enable this field.
     */
    public void setEnabled(boolean bEnable)
    {
        // Don't call inherrited
        for (int i = 0; i < this.getMainRecord().getFieldCount(); i++)
        {
            this.getMainRecord().getField(i).setEnabled(bEnable);
        }
        this.getMainRecord().getField(ApTrx.TRX_STATUS_ID).setEnabled(false);
        this.getMainRecord().getField(ApTrx.DEPARTURE_ESTIMATE).setEnabled(false);
        this.getMainRecord().getField(ApTrx.ID).setEnabled(true);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(ApTrx.VENDOR_ID)).getReferenceRecord(this);
        if (recVendor != null)
        {    // Make sure currency is read for LocalCurrencyField(s).
            Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReferenceRecord(this);
            recVendor.getField(Vendor.CURRENCYS_ID).addListener(new ReadSecondaryHandler(recCurrencys));
        }
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SButtonBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, null, MenuConstants.LOOKUP, MenuConstants.LOOKUP, null);
        
        ((ReferenceField)this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.TRX_STATUS_ID)).getReferenceRecord(null);   // Don't mix this TrxStatus record with the one I use to keep track of the current trx status (in openOther)
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.TRX_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.VENDOR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.TOUR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.DESCRIPTION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.START_SERVICE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.END_SERVICE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.DEPARTURE_ESTIMATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_NO).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(InvoiceScreenRecord.INVOICE_SCREEN_RECORD_FILE).getField(InvoiceScreenRecord.AP_ACCOUNT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(InvoiceScreenRecord.INVOICE_SCREEN_RECORD_FILE).getField(InvoiceScreenRecord.COST_ACCOUNT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Process the command.
     * <br />Step 1 - Process the command if possible and return true if processed.
     * <br />Step 2 - If I can't process, pass to all children (with me as the source).
     * <br />Step 3 - If children didn't process, pass to parent (with me as the source).
     * <br />Note: Never pass to a parent or child that matches the source (to avoid an endless loop).
     * @param strCommand The command to process.
     * @param sourceSField The source screen field (to avoid echos).
     * @param iCommandOptions If this command creates a new screen, create in a new window?
     * @return true if success.
     */
    public boolean doCommand(String strCommand, ScreenField sourceSField, int iCommandOptions)
    {
        if (MenuConstants.LOOKUP.equalsIgnoreCase(strCommand))
        {
            iCommandOptions = ScreenConstants.USE_NEW_WINDOW | ScreenConstants.DONT_PUSH_TO_BROSWER;
            boolean bReadCurrentRecord = false;
            int iDocMode = ApTrx.VENDOR_AP_SCREEN | ScreenConstants.SELECT_MODE;
            Map<String,Object> properties = new Hashtable<String,Object>();
            properties.put(ApTrxClassField.DISPLAY_TYPE_PARAM, Integer.toString(ApTrxClassField.DEPARTURE_ESTIMATES));
            if (!this.getMainRecord().getField(ApTrx.VENDOR_ID).isNull())
                properties.put(DBParams.HEADER_OBJECT_ID, this.getMainRecord().getField(ApTrx.VENDOR_ID).toString());
        
            return (this.onForm(null, iDocMode, bReadCurrentRecord, iCommandOptions, properties) != null);
        }
        if (MenuConstants.SUBMIT.equalsIgnoreCase(strCommand))
        {   // Special case of submitting a departure estimate with "no" changes
            Record record = this.getMainRecord();
            if (!record.isModified())
            {
                BaseField fldInvoiceAmount = this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_AMOUNT);
                if (!fldInvoiceAmount.isNull())
                    fldInvoiceAmount.setModified(true);
            }
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
