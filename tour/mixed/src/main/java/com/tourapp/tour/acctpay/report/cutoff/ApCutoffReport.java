/**
 * @(#)ApCutoffReport.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report.cutoff;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctpay.report.*;
import com.tourapp.tour.genled.db.*;

/**
 *  ApCutoffReport - .
 */
public class ApCutoffReport extends ApBaseCutoffReport
{
    protected Object m_objLastCurrencyID = null;
    protected Object m_objLastVendorID = null;
    /**
     * Default constructor.
     */
    public ApCutoffReport()
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
    public ApCutoffReport(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        m_objLastCurrencyID = null;
        m_objLastVendorID = null;
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new PaymentHistory(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new ApCutoffScreenRecord(this);
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
                if ((recTrxStatus.getField(TrxStatus.kStatusCode).toString().indexOf(ApTrx.DEPARTURE_ESTIMATE.substring(0, 6)) != -1)
                    || (recTrxStatus.getField(TrxStatus.kStatusCode).toString().indexOf(ApTrx.INVOICE) != -1)
                    || (recTrxStatus.getField(TrxStatus.kStatusCode).toString().indexOf(ApTrx.CREDIT_MEMO) != -1))
                        return true;    // Include this
                return false; // Don't include this.
            }
        });
        
        Record recCurrencys = ((ReferenceField)this.getScreenRecord().getField(ApCutoffScreenRecord.kCurrencysID)).getReferenceRecord(null);
        this.getRecord(Currencys.kCurrencysFile).addListener(new CompareFileFilter(Currencys.kID, recCurrencys.getField(Currencys.kID), "=", null, true));
        
        Record recPaymentHistory = this.getRecord(PaymentHistory.kPaymentHistoryFile);
        recPaymentHistory.addListener(new SubFileFilter(recApTrx));
        recPaymentHistory.addListener(new CompareFileFilter(PaymentHistory.kTrxDate, this.getScreenRecord().getField(ApCutoffScreenRecord.kCutoffDate), "<=", null, true));
        
        this.getMainRecord().setOpenMode(this.getMainRecord().getOpenMode() | DBConstants.OPEN_READ_ONLY);
        recPaymentHistory.addListener(new SubCountHandler(this.getMainRecord().getField(ApTrx.kInvoiceBalance), PaymentHistory.kAmountApplied, true, true));
        recPaymentHistory.addListener(new SubCountHandler(this.getMainRecord().getField(ApTrx.kInvoiceBalanceLocal), PaymentHistory.kAmountLocal, true, true));
        
        SubCountHandler listener = null;
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.kVenEstTotal), ApTrx.kDepartureEstimate, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.kVendorFile).getField(Vendor.kID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.kVenEstUSDTotal), ApTrx.kDepartureEstimateLocal, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.kVendorFile).getField(Vendor.kID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.kVenInvTotal), ApTrx.kInvoiceAmount, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.kVendorFile).getField(Vendor.kID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.kVenInvUSDTotal), ApTrx.kInvoiceLocal, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.kVendorFile).getField(Vendor.kID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.kVenInvBalTotal), ApTrx.kInvoiceBalance, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.kVendorFile).getField(Vendor.kID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.kVenInvBalUSDTotal), ApTrx.kInvoiceBalanceLocal, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.kVendorFile).getField(Vendor.kID));
        
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.kCurrEstTotal), ApTrx.kDepartureEstimate, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.kCurrEstUSDTotal), ApTrx.kDepartureEstimateLocal, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.kCurrInvoiceTotal), ApTrx.kInvoiceAmount, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.kCurrInvoiceUSDTotal), ApTrx.kInvoiceLocal, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.kCurrInvBalTotal), ApTrx.kInvoiceBalance, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.kCurrInvBalUSDTotal), ApTrx.kInvoiceBalanceLocal, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kID));
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.kEstUSDTotal), ApTrx.kDepartureEstimateLocal, true, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.kInvUSDTotal), ApTrx.kInvoiceLocal, true, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.kInvBalUSDTotal), ApTrx.kInvoiceBalanceLocal, true, true));
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
            { // Exclude those tours after the cutoff date.
                Record recTour = ((ReferenceField)recApTrx.getField(ApTrx.kTourID)).getReference();
                if (recTour == null)
                    recApTrx = null;    // Skip this one.
                else
                {
                    if (!this.getScreenRecord().getField(ApCutoffScreenRecord.kCutoffDate).isNull())
                        if (recTour.getField(Tour.kDepartureDate).compareTo(this.getScreenRecord().getField(ApCutoffScreenRecord.kCutoffDate)) > 0)
                            recApTrx = null;    // Skip this one (past the cutoff date).
                }
            }
        }
        // Now calculate the Invoice balance at the cutoff date
        Record recPaymentHistory = this.getRecord(PaymentHistory.kPaymentHistoryFile);
        recPaymentHistory.close();
        while (recPaymentHistory.hasNext())
        {
            recPaymentHistory.next();   // The listeners do all the work.
        }
        return recApTrx;
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new ApCutoffToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
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
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kTourID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDepartureEstimate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDepartureEstimateLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceBalance).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceBalanceLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new ApCutoffVenFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.FOOTING_SCREEN, null);
        new ApCutoffCurrFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.FOOTING_SCREEN, null);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new ApCutoffHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new ApCutoffFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}
