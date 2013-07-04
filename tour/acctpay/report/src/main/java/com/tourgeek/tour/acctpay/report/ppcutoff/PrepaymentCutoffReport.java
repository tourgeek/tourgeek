
package com.tourgeek.tour.acctpay.report.ppcutoff;

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
import org.jbundle.base.screen.model.report.*;
import com.tourgeek.tour.acctpay.report.cutoff.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.tour.genled.db.*;
import com.tourgeek.tour.acctpay.report.*;

/**
 *  PrepaymentCutoffReport - .
 */
public class PrepaymentCutoffReport extends ApBaseCutoffReport
{
    /**
     * Default constructor.
     */
    public PrepaymentCutoffReport()
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
    public PrepaymentCutoffReport(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new PaymentHistory(this);
        new TrxDesc(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new PrepaymentCutoffScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        Record recApTrx = this.getMainRecord();
        recApTrx.addListener(new ApTrxBaseFilter(ApTrx.TRX_STATUS_ID)
        {
            public boolean checkTrxStatus(TrxStatus recTrxStatus)
            {
                if ((recTrxStatus.getField(TrxStatus.STATUS_CODE).toString().indexOf(ApTrx.PREPAYMENT_REQUEST) != -1)
                    || (recTrxStatus.getField(TrxStatus.STATUS_CODE).toString().indexOf(ApTrx.BROKER_PAYMENT_HEADER) != -1)
                    || (recTrxStatus.getField(TrxStatus.STATUS_CODE).toString().indexOf(ApTrx.DEBIT_MEMO) != -1))
                        return true;    // Include this
                return false; // Don't include this.
            }
        });
        
        Record recCurrencys = ((ReferenceField)this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.CURRENCYS_ID)).getReferenceRecord(null);
        this.getRecord(Currencys.CURRENCYS_FILE).addListener(new CompareFileFilter(Currencys.ID, recCurrencys.getField(Currencys.ID), "=", null, true));
        
        Record recPaymentHistory = this.getRecord(PaymentHistory.PAYMENT_HISTORY_FILE);
        recPaymentHistory.setKeyArea(PaymentHistory.LINKED_TRX_ID_KEY);
        TrxDesc recTrxDesc = (TrxDesc)this.getRecord(TrxDesc.TRX_DESC_FILE);
        recTrxDesc.getTrxDesc(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE);
        recPaymentHistory.addListener(new SubFileFilter(recApTrx.getField(Trx.ID), LinkTrx.LINKED_TRX_ID, recTrxDesc.getField(TrxDesc.ID), LinkTrx.LINKED_TRX_DESC_ID, null, null, true));
        recPaymentHistory.addListener(new CompareFileFilter(PaymentHistory.TRX_DATE, this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.CUTOFF_DATE), "<=", null, true));
        
        this.getMainRecord().setOpenMode(this.getMainRecord().getOpenMode() | DBConstants.OPEN_READ_ONLY);
        
        SubCountHandler listener = null;
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.VENDOR_TOTAL), ApTrx.INVOICE_AMOUNT, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.ID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.VENDOR_TOTAL_USD), ApTrx.INVOICE_LOCAL, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.ID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.VENDOR_BALANCE_TOTAL), ApTrx.INVOICE_BALANCE, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.ID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.VENDOR_BALANCE_TOTAL_USD), ApTrx.INVOICE_BALANCE_LOCAL, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.ID));
        
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.CURR_TOTAL), ApTrx.INVOICE_AMOUNT, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.ID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.CURR_TOTAL_USD), ApTrx.INVOICE_LOCAL, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.ID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.CURR_BALANCE_TOTAL), ApTrx.INVOICE_BALANCE, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.ID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.CURR_BALANCE_TOTAL_USD), ApTrx.INVOICE_BALANCE_LOCAL, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.ID));
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.TOTAL_USD), ApTrx.INVOICE_LOCAL, true, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.BALANCE_TOTAL_USD), ApTrx.INVOICE_BALANCE_LOCAL, true, true));
    }
    /**
     * Get the next grid record.
     * @param bFirstTime If true, I want the first record.
     * @return the next record (or null if EOF).
     */
    public Record getNextGridRecord(boolean bFirstTime) throws DBException
    {
        Record recApTrx = null;
        while (recApTrx == null)
        {
            recApTrx = super.getNextGridRecord(bFirstTime);
            bFirstTime = false;
            if (recApTrx == null)
                return recApTrx;    // EOF
            else
            { // Exclude those prepayments after the cutoff date.
                if (!this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.CUTOFF_DATE).isNull())
                    if (recApTrx.getField(ApTrx.INVOICE_DATE).compareTo(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.CUTOFF_DATE)) > 0)
                        recApTrx = null;    // Skip this one (past the cutoff date).
            }
        }
        // Now calculate the Invoice balance at the cutoff date
        double dInvoiceBalance = Math.abs(recApTrx.getField(ApTrx.INVOICE_AMOUNT).getValue());
        double dInvoiceBalanceLocal = Math.abs(recApTrx.getField(ApTrx.INVOICE_LOCAL).getValue());
        recApTrx.getField(ApTrx.INVOICE_AMOUNT).setValue(dInvoiceBalance);  // Make sure these are positive
        recApTrx.getField(ApTrx.INVOICE_LOCAL).setValue(dInvoiceBalanceLocal);
        
        Record recPaymentHistory = this.getRecord(PaymentHistory.PAYMENT_HISTORY_FILE);
        recPaymentHistory.close();
        while (recPaymentHistory.hasNext())
        {
            recPaymentHistory.next();
            dInvoiceBalance = dInvoiceBalance - recPaymentHistory.getField(PaymentHistory.AMOUNT_APPLIED).getValue();   // I manually zero these
            dInvoiceBalanceLocal = dInvoiceBalanceLocal - recPaymentHistory.getField(PaymentHistory.AMOUNT_LOCAL).getValue();
        }
        recApTrx.getField(ApTrx.INVOICE_BALANCE).setValue(dInvoiceBalance);
        recApTrx.getField(ApTrx.INVOICE_BALANCE_LOCAL).setValue(dInvoiceBalanceLocal);
        return recApTrx;
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new PrepaymentCutoffToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        new ApCutoffCurrHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.HEADING_SCREEN, null);
        new ApCutoffVendorHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.HEADING_SCREEN, null);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.TRX_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_BALANCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_BALANCE_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new PrepaymentCutoffVenFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.FOOTING_SCREEN, null);
        new PrepaymentCutoffCurrFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.FOOTING_SCREEN, null);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new PrepaymentCutoffHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new PrepaymentCutoffFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}
