/**
  * @(#)VendorCostAnalysis.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctpay.report.vendorcost;

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
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.report.*;
import com.tourapp.tour.acctpay.report.tourcost.*;

/**
 *  VendorCostAnalysis - Vendor cost analysis.
 */
public class VendorCostAnalysis extends ReportScreen
{
    /**
     * Default constructor.
     */
    public VendorCostAnalysis()
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
    public VendorCostAnalysis(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Vendor cost analysis";
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
        new Vendor(this);
        new PaymentHistory(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new CostAnalysisScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getRecord(ApTrx.AP_TRX_FILE).setKeyArea(ApTrx.VENDOR_ID_KEY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.VENDOR_ID).addListener(new ReadSecondaryHandler(this.getRecord(Vendor.VENDOR_FILE)));
        
        this.getRecord(ApTrx.AP_TRX_FILE).addListener(new CompareFileFilter(this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.VENDOR_ID), this.getScreenRecord().getField(ApReportScreenRecord.VENDOR_ID), "=", null, true));
        this.getRecord(ApTrx.AP_TRX_FILE).addListener(new FilterApTrxDateRange(ApTrx.DEPARTURE_DATE, this.getScreenRecord().getField(ApReportScreenRecord.START_DEPARTURE), this.getScreenRecord().getField(ApReportScreenRecord.END_DEPARTURE)));
        
        Record recPaymentHistory = this.getRecord(PaymentHistory.PAYMENT_HISTORY_FILE);
        Record recApTrx = this.getRecord(ApTrx.AP_TRX_FILE);
        recPaymentHistory.addListener(new SubFileFilter(recApTrx));
        recApTrx.addListener(new RecountOnValidHandler(recPaymentHistory));
        recPaymentHistory.addListener(new SubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.PAYMENTS), PaymentHistory.AMOUNT_LOCAL, false, true));
        
        recApTrx.addListener(new SubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.TOTAL_ESTIMATE), ApTrx.DEPARTURE_ESTIMATE, true, true, true));
        recApTrx.addListener(new InvoiceAmountSubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.TOTAL_INVOICE), ApTrx.INVOICE_AMOUNT, true, true, true));
        recApTrx.addListener(new SubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.SUB_TOTAL_DEP_EST_USD), ApTrx.DEPARTURE_ESTIMATE_LOCAL, true, true, true));
        recApTrx.addListener(new InvoiceAmountSubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.SUB_TOTAL_INVOICE_USD), ApTrx.INVOICE_LOCAL, true, true, true));
        recPaymentHistory.addListener(new SubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.SUB_TOTAL_PAYMENTS_USD), PaymentHistory.AMOUNT_LOCAL, true, true, true));
        recApTrx.addListener(new SubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.SUB_TOTAL_BAL_USD), ApTrx.INVOICE_BALANCE_LOCAL, true, true, true));
        
        recApTrx.addListener(new SubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.TOTAL_DEP_EST_USD), ApTrx.DEPARTURE_ESTIMATE_LOCAL, true, true));
        recApTrx.addListener(new InvoiceAmountSubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.TOTAL_INVOICE_USD), ApTrx.INVOICE_LOCAL, true, true, false));
        recPaymentHistory.addListener(new SubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.TOTAL_PAYMENTS_USD), PaymentHistory.AMOUNT_LOCAL, true, true));
        recApTrx.addListener(new InvoiceAmountSubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.TOTAL_USD_BAL), ApTrx.INVOICE_BALANCE_LOCAL, true, true, false));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new VendorCostAnalysisToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        new VendorCostAnalysisVenHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.HEADING_SCREEN, null);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.TRX_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.TOUR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.START_SERVICE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.END_SERVICE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_NO).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.DESCRIPTION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.DEPARTURE_ESTIMATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_BALANCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.DEPARTURE_ESTIMATE_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CostAnalysisScreenRecord.COST_ANALYSIS_SCREEN_RECORD_FILE).getField(CostAnalysisScreenRecord.PAYMENTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_BALANCE_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new VendorCostAnalysisVenFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.FOOTING_SCREEN, null);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new VendorCostAnalysisFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new VendorCostAnalysisHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}
