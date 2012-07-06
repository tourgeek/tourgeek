/**
  * @(#)DebitMemoScreen.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import com.tourapp.tour.acctpay.db.event.*;

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
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.DEBIT_MEMO);
        this.getMainRecord().getField(ApTrx.TRX_STATUS_ID).addListener(new InitFieldHandler(recTrxStatus.getField(TrxStatus.ID)));
        
        this.getMainRecord().addListener(new UpdateDebitMemoHandler(null));
        // Invoice balance = invoice amount and select for payment
        this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT).addListener(new CopyFieldHandler(ApTrx.INVOICE_BALANCE));
        this.getMainRecord().getField(ApTrx.INVOICE_LOCAL).addListener(new CopyFieldHandler(ApTrx.INVOICE_BALANCE_LOCAL));
        
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(ApTrx.VENDOR_ID)).getReferenceRecord(this);
        Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReferenceRecord(this);
        this.getMainRecord().getField(ApTrx.VENDOR_ID).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.DEPARTURE_EXCHANGE), recCurrencys.getField(Currencys.LAST_RATE)));
        this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.DEPARTURE_EXCHANGE), recCurrencys.getField(Currencys.LAST_RATE)));
        this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT).addListener(new CalcBalanceHandler(this.getMainRecord().getField(ApTrx.INVOICE_LOCAL), this.getMainRecord().getField(ApTrx.DEPARTURE_EXCHANGE), this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT), CalcBalanceHandler.MULTIPLY, true));
        this.getMainRecord().getField(ApTrx.DEPARTURE_EXCHANGE).addListener(new CalcBalanceHandler(this.getMainRecord().getField(ApTrx.INVOICE_LOCAL), this.getMainRecord().getField(ApTrx.DEPARTURE_EXCHANGE), this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT), CalcBalanceHandler.MULTIPLY, true));
        //xthis.getMainRecord().getField(ApTrx.INVOICE_LOCAL).addListener(new CalcBalanceHandler(this.getMainRecord().getField(ApTrx.DEPARTURE_EXCHANGE), this.getMainRecord().getField(ApTrx.INVOICE_USD), this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT), CalcBalanceHandler.DIVIDE, true));
        
        this.getScreenRecord().getField(DebitMemoScreenRecord.PP_ACCOUNT_ID).addListener(new InitFieldHandler(this.getRecord(ApControl.AP_CONTROL_FILE).getField(ApControl.NON_TOUR_PREPAY_ACCOUNT_ID)));
        this.getScreenRecord().getField(DebitMemoScreenRecord.TOUR_ACCOUNT_ID).addListener(new InitFieldHandler(this.getRecord(ApControl.AP_CONTROL_FILE).getField(ApControl.COST_ACCOUNT_ID)));
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
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.VENDOR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.DESCRIPTION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.DEPARTURE_EXCHANGE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(DebitMemoScreenRecord.DEBIT_MEMO_SCREEN_RECORD_FILE).getField(DebitMemoScreenRecord.TOUR_ACCOUNT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(DebitMemoScreenRecord.DEBIT_MEMO_SCREEN_RECORD_FILE).getField(DebitMemoScreenRecord.PP_ACCOUNT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
