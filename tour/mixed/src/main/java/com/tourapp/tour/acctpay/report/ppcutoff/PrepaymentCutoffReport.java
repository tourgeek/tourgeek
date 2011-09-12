/**
 * @(#)PrepaymentCutoffReport.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report.ppcutoff;

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
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctpay.report.cutoff.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.report.*;

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
        recApTrx.addListener(new ApTrxBaseFilter(ApTrx.kTrxStatusID)
        {
            public boolean checkTrxStatus(TrxStatus recTrxStatus)
            {
                if ((recTrxStatus.getField(TrxStatus.kStatusCode).toString().indexOf(ApTrx.PREPAYMENT_REQUEST) != -1)
                    || (recTrxStatus.getField(TrxStatus.kStatusCode).toString().indexOf(ApTrx.BROKER_PAYMENT_HEADER) != -1)
                    || (recTrxStatus.getField(TrxStatus.kStatusCode).toString().indexOf(ApTrx.DEBIT_MEMO) != -1))
                        return true;    // Include this
                return false; // Don't include this.
            }
        });
        
        Record recCurrencys = ((ReferenceField)this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.kCurrencysID)).getReferenceRecord(null);
        this.getRecord(Currencys.kCurrencysFile).addListener(new CompareFileFilter(Currencys.kID, recCurrencys.getField(Currencys.kID), "=", null, true));
        
        Record recPaymentHistory = this.getRecord(PaymentHistory.kPaymentHistoryFile);
        recPaymentHistory.setKeyArea(PaymentHistory.kLinkedTrxIDKey);
        TrxDesc recTrxDesc = (TrxDesc)this.getRecord(TrxDesc.kTrxDescFile);
        recTrxDesc.getTrxDesc(TransactionType.ACCTPAY, ApTrx.kApTrxFile);
        recPaymentHistory.addListener(new SubFileFilter(recApTrx.getField(Trx.kID), LinkTrx.kLinkedTrxID, recTrxDesc.getField(TrxDesc.kID), LinkTrx.kLinkedTrxDescID, null, -1, true));
        recPaymentHistory.addListener(new CompareFileFilter(PaymentHistory.kTrxDate, this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.kCutoffDate), "<=", null, true));
        
        this.getMainRecord().setOpenMode(this.getMainRecord().getOpenMode() | DBConstants.OPEN_READ_ONLY);
        
        SubCountHandler listener = null;
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.kVendorTotal), ApTrx.kInvoiceAmount, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.kVendorFile).getField(Vendor.kID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.kVendorTotalUSD), ApTrx.kInvoiceLocal, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.kVendorFile).getField(Vendor.kID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.kVendorBalanceTotal), ApTrx.kInvoiceBalance, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.kVendorFile).getField(Vendor.kID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.kVendorBalanceTotalUSD), ApTrx.kInvoiceBalanceLocal, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.kVendorFile).getField(Vendor.kID));
        
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.kCurrTotal), ApTrx.kInvoiceAmount, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.kCurrTotalUSD), ApTrx.kInvoiceLocal, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.kCurrBalanceTotal), ApTrx.kInvoiceBalance, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.kCurrBalanceTotalUSD), ApTrx.kInvoiceBalanceLocal, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kID));
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.kTotalUSD), ApTrx.kInvoiceLocal, true, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.kBalanceTotalUSD), ApTrx.kInvoiceBalanceLocal, true, true));
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
                if (!this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.kCutoffDate).isNull())
                    if (recApTrx.getField(ApTrx.kInvoiceDate).compareTo(this.getScreenRecord().getField(PrepaymentCutoffScreenRecord.kCutoffDate)) > 0)
                        recApTrx = null;    // Skip this one (past the cutoff date).
            }
        }
        // Now calculate the Invoice balance at the cutoff date
        double dInvoiceBalance = Math.abs(recApTrx.getField(ApTrx.kInvoiceAmount).getValue());
        double dInvoiceBalanceLocal = Math.abs(recApTrx.getField(ApTrx.kInvoiceLocal).getValue());
        recApTrx.getField(ApTrx.kInvoiceAmount).setValue(dInvoiceBalance);  // Make sure these are positive
        recApTrx.getField(ApTrx.kInvoiceLocal).setValue(dInvoiceBalanceLocal);
        
        Record recPaymentHistory = this.getRecord(PaymentHistory.kPaymentHistoryFile);
        recPaymentHistory.close();
        while (recPaymentHistory.hasNext())
        {
            recPaymentHistory.next();
            dInvoiceBalance = dInvoiceBalance - recPaymentHistory.getField(PaymentHistory.kAmountApplied).getValue();   // I manually zero these
            dInvoiceBalanceLocal = dInvoiceBalanceLocal - recPaymentHistory.getField(PaymentHistory.kAmountLocal).getValue();
        }
        recApTrx.getField(ApTrx.kInvoiceBalance).setValue(dInvoiceBalance);
        recApTrx.getField(ApTrx.kInvoiceBalanceLocal).setValue(dInvoiceBalanceLocal);
        return recApTrx;
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new PrepaymentCutoffToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        new ApCutoffCurrHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.HEADING_SCREEN, null);
        new ApCutoffVendorHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.HEADING_SCREEN, null);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kTrxStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceBalance).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceBalanceLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
