/**
  * @(#)ApCutoffReport.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctpay.report.cutoff;

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
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.acctpay.report.*;
import com.tourgeek.tour.genled.db.*;

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
        recApTrx.addListener(new ApTrxBaseFilter(ApTrx.TRX_STATUS_ID)
        {
            public boolean checkTrxStatus(TrxStatus recTrxStatus)
            {
                if ((recTrxStatus.getField(TrxStatus.STATUS_CODE).toString().indexOf(ApTrx.DEP_ESTIMATE.substring(0, 6)) != -1)
                    || (recTrxStatus.getField(TrxStatus.STATUS_CODE).toString().indexOf(ApTrx.INVOICE) != -1)
                    || (recTrxStatus.getField(TrxStatus.STATUS_CODE).toString().indexOf(ApTrx.CREDIT_MEMO) != -1))
                        return true;    // Include this
                return false; // Don't include this.
            }
        });
        
        Record recCurrencys = ((ReferenceField)this.getScreenRecord().getField(ApCutoffScreenRecord.CURRENCYS_ID)).getReferenceRecord(null);
        this.getRecord(Currencys.CURRENCYS_FILE).addListener(new CompareFileFilter(Currencys.ID, recCurrencys.getField(Currencys.ID), "=", null, true));
        
        Record recPaymentHistory = this.getRecord(PaymentHistory.PAYMENT_HISTORY_FILE);
        recPaymentHistory.addListener(new SubFileFilter(recApTrx));
        recPaymentHistory.addListener(new CompareFileFilter(PaymentHistory.TRX_DATE, this.getScreenRecord().getField(ApCutoffScreenRecord.CUTOFF_DATE), "<=", null, true));
        
        this.getMainRecord().setOpenMode(this.getMainRecord().getOpenMode() | DBConstants.OPEN_READ_ONLY);
        recPaymentHistory.addListener(new SubCountHandler(this.getMainRecord().getField(ApTrx.INVOICE_BALANCE), PaymentHistory.AMOUNT_APPLIED, true, true));
        recPaymentHistory.addListener(new SubCountHandler(this.getMainRecord().getField(ApTrx.INVOICE_BALANCE_LOCAL), PaymentHistory.AMOUNT_LOCAL, true, true));
        
        SubCountHandler listener = null;
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.VEN_EST_TOTAL), ApTrx.DEPARTURE_ESTIMATE, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.ID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.VEN_EST_USD_TOTAL), ApTrx.DEPARTURE_ESTIMATE_LOCAL, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.ID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.VEN_INV_TOTAL), ApTrx.INVOICE_AMOUNT, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.ID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.VEN_INV_USD_TOTAL), ApTrx.INVOICE_LOCAL, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.ID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.VEN_INV_BAL_TOTAL), ApTrx.INVOICE_BALANCE, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.ID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.VEN_INV_BAL_USD_TOTAL), ApTrx.INVOICE_BALANCE_LOCAL, true, true, true));
        listener.setBreakField(this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.ID));
        
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.CURR_EST_TOTAL), ApTrx.DEPARTURE_ESTIMATE, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.ID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.CURR_EST_USD_TOTAL), ApTrx.DEPARTURE_ESTIMATE_LOCAL, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.ID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.CURR_INVOICE_TOTAL), ApTrx.INVOICE_AMOUNT, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.ID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.CURR_INVOICE_USD_TOTAL), ApTrx.INVOICE_LOCAL, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.ID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.CURR_INV_BAL_TOTAL), ApTrx.INVOICE_BALANCE, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.ID));
        this.getMainRecord().addListener(listener = new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.CURR_INV_BAL_USD_TOTAL), ApTrx.INVOICE_BALANCE_LOCAL, true, true, true));
        listener.setBreakField(this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.ID));
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.EST_USD_TOTAL), ApTrx.DEPARTURE_ESTIMATE_LOCAL, true, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.INV_USD_TOTAL), ApTrx.INVOICE_LOCAL, true, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(ApCutoffScreenRecord.INV_BAL_USD_TOTAL), ApTrx.INVOICE_BALANCE_LOCAL, true, true));
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
                Record recTour = ((ReferenceField)recApTrx.getField(ApTrx.TOUR_ID)).getReference();
                if (recTour == null)
                    recApTrx = null;    // Skip this one.
                else
                {
                    if (!this.getScreenRecord().getField(ApCutoffScreenRecord.CUTOFF_DATE).isNull())
                        if (recTour.getField(Tour.DEPARTURE_DATE).compareTo(this.getScreenRecord().getField(ApCutoffScreenRecord.CUTOFF_DATE)) > 0)
                            recApTrx = null;    // Skip this one (past the cutoff date).
                }
            }
        }
        // Now calculate the Invoice balance at the cutoff date
        Record recPaymentHistory = this.getRecord(PaymentHistory.PAYMENT_HISTORY_FILE);
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
        return new ApCutoffToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
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
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.TOUR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.DEPARTURE_ESTIMATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.DEPARTURE_ESTIMATE_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_BALANCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_BALANCE_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
