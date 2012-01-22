/**
 * @(#)InvoiceScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile);
        
        EnableScreenHandler behavior = new EnableScreenHandler(ApTrx.kTrxStatusID);
        this.getMainRecord().addListener(behavior);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.DEPARTURE_EST_MANUAL);
        behavior.addComparison(recTrxStatus.getField(TrxStatus.kID).getData());
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.DEPARTURE_ESTIMATE);
        behavior.addComparison(recTrxStatus.getField(TrxStatus.kID).getData());
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.INVOICE_NON_TOUR);
        behavior.addComparison(recTrxStatus.getField(TrxStatus.kID).getData());
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.CREDIT_INVOICE);
        behavior.addComparison(recTrxStatus.getField(TrxStatus.kID).getData());
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.INVOICE);
        behavior.addComparison(recTrxStatus.getField(TrxStatus.kID).getData());
        
        this.getMainRecord().getField(ApTrx.kTrxStatusID).addListener(new InitFieldHandler(recTrxStatus.getField(TrxStatus.kID)));
        
        // If Invoice date, Invoice number, or amount changes, make sure this is an Invoice
        this.getMainRecord().getField(ApTrx.kInvoiceNo).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.kTrxStatusID), recTrxStatus.getField(TrxStatus.kID)));
        this.getMainRecord().getField(ApTrx.kInvoiceDate).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.kTrxStatusID), recTrxStatus.getField(TrxStatus.kID)));
        this.getMainRecord().getField(ApTrx.kInvoiceAmount).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.kTrxStatusID), recTrxStatus.getField(TrxStatus.kID)));
        
        // Invoice balance = invoice amount and select for payment
        this.getMainRecord().getField(ApTrx.kInvoiceAmount).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.kInvoiceBalance), this.getMainRecord().getField(ApTrx.kInvoiceAmount)));
        this.getMainRecord().getField(ApTrx.kInvoiceAmount).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.kAmountSelected), this.getMainRecord().getField(ApTrx.kInvoiceAmount)));
        
        this.getMainRecord().getField(ApTrx.kInvoiceAmount).addListener(new CalcBalanceHandler(this.getMainRecord().getField(ApTrx.kInvoiceLocal), this.getMainRecord().getField(ApTrx.kInvoiceAmount), this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kLastRate), CalcBalanceHandler.MULTIPLY, false));
        this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kLastRate).addListener(new CalcBalanceHandler(this.getMainRecord().getField(ApTrx.kInvoiceLocal), this.getMainRecord().getField(ApTrx.kInvoiceAmount), this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kLastRate), CalcBalanceHandler.MULTIPLY, false));
        FieldListener listener = null;
        this.getMainRecord().getField(ApTrx.kInvoiceLocal).addListener(listener = new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.kInvoiceBalanceLocal), this.getMainRecord().getField(ApTrx.kInvoiceLocal)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, true);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, true);
        // Init these to the last value
        this.getMainRecord().getField(ApTrx.kVendorID).addListener(new InitFieldHandler(this.getScreenRecord().getField(InvoiceScreenRecord.kLastVendorID)));
        this.getMainRecord().getField(ApTrx.kInvoiceNo).addListener(new InitFieldHandler(this.getScreenRecord().getField(InvoiceScreenRecord.kLastInvoiceNo)));
        this.getMainRecord().getField(ApTrx.kInvoiceDate).addListener(new InitFieldHandler(this.getScreenRecord().getField(InvoiceScreenRecord.kLastInvoiceDate)));
        // If vendor change, set new last vendor, clear all last.
        this.getMainRecord().getField(ApTrx.kVendorID).addListener(listener = new MoveOnChangeHandler(this.getScreenRecord().getField(InvoiceScreenRecord.kLastVendorID), this.getMainRecord().getField(ApTrx.kVendorID)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, true);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, true);
        this.getMainRecord().getField(ApTrx.kVendorID).addListener(listener = new InitOnChangeHandler(this.getScreenRecord().getField(InvoiceScreenRecord.kLastInvoiceNo)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.getMainRecord().getField(ApTrx.kVendorID).addListener(listener = new InitOnChangeHandler(this.getScreenRecord().getField(InvoiceScreenRecord.kLastInvoiceDate)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        // Set last invoice and date if change by user
        this.getMainRecord().getField(ApTrx.kInvoiceNo).addListener(listener = new MoveOnChangeHandler(this.getScreenRecord().getField(InvoiceScreenRecord.kLastInvoiceNo), this.getMainRecord().getField(ApTrx.kInvoiceNo)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, true);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, true);
        this.getMainRecord().getField(ApTrx.kInvoiceDate).addListener(listener = new MoveOnChangeHandler(this.getScreenRecord().getField(InvoiceScreenRecord.kLastInvoiceDate), this.getMainRecord().getField(ApTrx.kInvoiceDate), false, false, true));  // Not if null
        listener.setRespondsToMode(DBConstants.READ_MOVE, true);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, true);
        // If user changes vendor no, clear invoice no and date.
        this.getMainRecord().getField(ApTrx.kVendorID).addListener(listener = new InitOnChangeHandler(this.getMainRecord().getField(ApTrx.kInvoiceNo)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.getMainRecord().getField(ApTrx.kVendorID).addListener(listener = new InitOnChangeHandler(this.getMainRecord().getField(ApTrx.kInvoiceDate)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        
        // If invoice number or amount changes, and the date hasn't been set yet, set it.
        this.getMainRecord().getField(ApTrx.kInvoiceNo).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.kInvoiceDate), this.getScreenRecord().getField(InvoiceScreenRecord.kLastInvoiceDate), false, true));    // Only if date is null now.
        this.getMainRecord().getField(ApTrx.kInvoiceAmount).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.kInvoiceDate), this.getScreenRecord().getField(InvoiceScreenRecord.kLastInvoiceDate), false, true));
        
        // If read dep est and vendor is same as last, set invoice no, date and amount in record
        this.getMainRecord().addListener(new UpdateInvoiceVendorHandler((ScreenRecord)this.getScreenRecord()));
        // If entering Invoice number and amount is null, move dep est amount to Invoice amount
        this.getMainRecord().getField(ApTrx.kInvoiceNo).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.kInvoiceAmount), this.getMainRecord().getField(ApTrx.kDepartureEstimate), false, true));
        
        Record recTour = ((ReferenceField)this.getMainRecord().getField(ApTrx.kTourID)).getReferenceRecord(this);
        this.getMainRecord().getField(ApTrx.kTourID).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.kStartServiceDate), recTour.getField(Tour.kDepartureDate)));
        this.getMainRecord().getField(ApTrx.kStartServiceDate).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.kEndServiceDate), this.getMainRecord().getField(ApTrx.kStartServiceDate)));
        
        this.getMainRecord().getField(ApTrx.kTourID).addListener(new InvoiceAcctHandler(null));
        this.getMainRecord().getField(ApTrx.kVendorID).addListener(new InvoiceAcctHandler(null));
        this.getMainRecord().getField(ApTrx.kInvoiceAmount).addListener(new InvoiceAcctHandler(null));
        
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
        this.getMainRecord().getField(ApTrx.kTrxStatusID).setEnabled(false);
        this.getMainRecord().getField(ApTrx.kDepartureEstimate).setEnabled(false);
        this.getMainRecord().getField(ApTrx.kID).setEnabled(true);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(ApTrx.kVendorID)).getReferenceRecord(this);
        if (recVendor != null)
        {    // Make sure currency is read for LocalCurrencyField(s).
            Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.kCurrencysID)).getReferenceRecord(this);
            recVendor.getField(Vendor.kCurrencysID).addListener(new ReadSecondaryHandler(recCurrencys));
        }
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SButtonBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, null, MenuConstants.LOOKUP, MenuConstants.LOOKUP, null);
        
        ((ReferenceField)this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kTrxStatusID)).getReferenceRecord(null);   // Don't mix this TrxStatus record with the one I use to keep track of the current trx status (in openOther)
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kTrxStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kVendorID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kTourID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kStartServiceDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kEndServiceDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDepartureEstimate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceNo).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(InvoiceScreenRecord.kInvoiceScreenRecordFile).getField(InvoiceScreenRecord.kApAccountID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(InvoiceScreenRecord.kInvoiceScreenRecordFile).getField(InvoiceScreenRecord.kCostAccountID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
            if (!this.getMainRecord().getField(ApTrx.kVendorID).isNull())
                properties.put(DBParams.HEADER_OBJECT_ID, this.getMainRecord().getField(ApTrx.kVendorID).toString());
        
            return (this.onForm(null, iDocMode, bReadCurrentRecord, iCommandOptions, properties) != null);
        }
        if (MenuConstants.SUBMIT.equalsIgnoreCase(strCommand))
        {   // Special case of submitting a departure estimate with "no" changes
            Record record = this.getMainRecord();
            if (!record.isModified())
            {
                BaseField fldInvoiceAmount = this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceAmount);
                if (!fldInvoiceAmount.isNull())
                    fldInvoiceAmount.setModified(true);
            }
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
