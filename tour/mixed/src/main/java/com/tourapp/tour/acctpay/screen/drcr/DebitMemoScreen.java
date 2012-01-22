/**
 * @(#)DebitMemoScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.drcr;

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
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.screen.trx.*;

/**
 *  DebitMemoScreen - Debit memos.
 */
public class DebitMemoScreen extends Screen
{
    /**
     * Default constructor.
     */
    public DebitMemoScreen()
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
    public DebitMemoScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Debit memos";
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
        return new DebitMemoScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.DEBIT_MEMO);
        this.getMainRecord().getField(ApTrx.kTrxStatusID).addListener(new InitFieldHandler(recTrxStatus.getField(TrxStatus.kID)));
        
        this.getMainRecord().addListener(new UpdateDebitMemoHandler(null));
        // Invoice balance = invoice amount and select for payment
        this.getMainRecord().getField(ApTrx.kInvoiceAmount).addListener(new CopyFieldHandler(ApTrx.kInvoiceBalance));
        this.getMainRecord().getField(ApTrx.kInvoiceLocal).addListener(new CopyFieldHandler(ApTrx.kInvoiceBalanceLocal));
        
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(ApTrx.kVendorID)).getReferenceRecord(this);
        Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.kCurrencysID)).getReferenceRecord(this);
        this.getMainRecord().getField(ApTrx.kVendorID).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.kDepartureExchange), recCurrencys.getField(Currencys.kLastRate)));
        this.getMainRecord().getField(ApTrx.kInvoiceAmount).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.kDepartureExchange), recCurrencys.getField(Currencys.kLastRate)));
        this.getMainRecord().getField(ApTrx.kInvoiceAmount).addListener(new CalcBalanceHandler(this.getMainRecord().getField(ApTrx.kInvoiceLocal), this.getMainRecord().getField(ApTrx.kDepartureExchange), this.getMainRecord().getField(ApTrx.kInvoiceAmount), CalcBalanceHandler.MULTIPLY, true));
        this.getMainRecord().getField(ApTrx.kDepartureExchange).addListener(new CalcBalanceHandler(this.getMainRecord().getField(ApTrx.kInvoiceLocal), this.getMainRecord().getField(ApTrx.kDepartureExchange), this.getMainRecord().getField(ApTrx.kInvoiceAmount), CalcBalanceHandler.MULTIPLY, true));
        //xthis.getMainRecord().getField(ApTrx.kInvoiceLocal).addListener(new CalcBalanceHandler(this.getMainRecord().getField(ApTrx.kDepartureExchange), this.getMainRecord().getField(ApTrx.kInvoiceUSD), this.getMainRecord().getField(ApTrx.kInvoiceAmount), CalcBalanceHandler.DIVIDE, true));
        
        this.getScreenRecord().getField(DebitMemoScreenRecord.kPpAccountID).addListener(new InitFieldHandler(this.getRecord(ApControl.kApControlFile).getField(ApControl.kNonTourPrepayAccountID)));
        this.getScreenRecord().getField(DebitMemoScreenRecord.kTourAccountID).addListener(new InitFieldHandler(this.getRecord(ApControl.kApControlFile).getField(ApControl.kCostAccountID)));
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
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kVendorID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDepartureExchange).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(DebitMemoScreenRecord.kDebitMemoScreenRecordFile).getField(DebitMemoScreenRecord.kTourAccountID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(DebitMemoScreenRecord.kDebitMemoScreenRecordFile).getField(DebitMemoScreenRecord.kPpAccountID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
