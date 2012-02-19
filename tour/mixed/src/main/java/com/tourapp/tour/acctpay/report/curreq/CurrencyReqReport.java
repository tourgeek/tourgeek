/**
 * @(#)CurrencyReqReport.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report.curreq;

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
import com.tourapp.tour.base.db.event.*;
import com.tourapp.tour.acctpay.report.*;

/**
 *  CurrencyReqReport - Currency Requirements Report.
 */
public class CurrencyReqReport extends AnalysisScreen
{
    /**
     * Default constructor.
     */
    public CurrencyReqReport()
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
    public CurrencyReqReport(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Currency Requirements Report";
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
        new Currencys(this);
        new Vendor(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new CurrencyReqScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        Record recApTrx = this.getRecord(ApTrx.AP_TRX_FILE);
        Record recVendor = this.getRecord(Vendor.VENDOR_FILE);
        Record recCurrencys = this.getRecord(Currencys.CURRENCYS_FILE);
        
        recApTrx.getField(ApTrx.VENDOR_ID).addListener(new ReadSecondaryHandler(recVendor));
        recVendor.getField(Vendor.CURRENCYS_ID).addListener(new ReadSecondaryHandler(recCurrencys));
        
        recApTrx.addListener(new CalcStartDateHandler(this.getScreenRecord().getField(CurrencyReqScreenRecord.FROM_DATE), recApTrx.getField(ApTrx.START_SERVICE_DATE), this.getScreenRecord().getField(CurrencyReqScreenRecord.START_DATE), this.getScreenRecord().getField(CurrencyReqScreenRecord.PERIOD_TYPE), this.getScreenRecord().getField(CurrencyReqScreenRecord.PERIOD_LENGTH)));
        
        this.getRecord(ApTrx.AP_TRX_FILE).addListener(new ApTrxFilter(ApTrx.TRX_STATUS_ID, (ScreenRecord)this.getScreenRecord()));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new CurrencyReqToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new CurrencyReqHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(CurrencyReqScreenRecord.CURRENCY_REQ_SCREEN_RECORD_FILE).getField(CurrencyReqScreenRecord.SUMMARY_CURRENCY_DESC).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CurrencyReqScreenRecord.CURRENCY_REQ_SCREEN_RECORD_FILE).getField(CurrencyReqScreenRecord.FROM_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CurrencyReqScreenRecord.CURRENCY_REQ_SCREEN_RECORD_FILE).getField(CurrencyReqScreenRecord.DEPARTURE_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CurrencyReqScreenRecord.CURRENCY_REQ_SCREEN_RECORD_FILE).getField(CurrencyReqScreenRecord.BALANCE_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CurrencyReqScreenRecord.CURRENCY_REQ_SCREEN_RECORD_FILE).getField(CurrencyReqScreenRecord.TOTAL_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CurrencyReqScreenRecord.CURRENCY_REQ_SCREEN_RECORD_FILE).getField(CurrencyReqScreenRecord.TOTAL_USD).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new CurrencyReqFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Add/update this summary record.
     * @param recSummary The destination summary record.
     * @param mxKeyFields The key fields map.
     * @param mxDataFields The data fields map.
     */
    public void addSummary(Record recSummary, BaseField[][] mxKeyFields, BaseField[][] mxDataFields)
    {
        this.getScreenRecord().getField(CurrencyReqScreenRecord.SUMMARY_CURRENCY_DESC).moveFieldToThis(this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.DESCRIPTION));
        this.getScreenRecord().getField(CurrencyReqScreenRecord.DEPARTURE_TOTAL).moveFieldToThis(this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.DEPARTURE_ESTIMATE));
        this.getScreenRecord().getField(CurrencyReqScreenRecord.BALANCE_TOTAL).moveFieldToThis(this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_BALANCE));
        double dEstimate = this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.DEPARTURE_ESTIMATE).getValue();
        double dBalance = this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_BALANCE).getValue();
        double dTotal = dBalance;
        if (dTotal == 0.00)
            dTotal = dEstimate;
        this.getScreenRecord().getField(CurrencyReqScreenRecord.TOTAL_TOTAL).setValue(dTotal);
        double dTotalUSD = dTotal * this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.LAST_RATE).getValue();
        this.getScreenRecord().getField(CurrencyReqScreenRecord.TOTAL_USD).setValue(dTotalUSD);
        
        // Add totals:
        this.getScreenRecord().getField(CurrencyReqScreenRecord.USD_TOTAL).setValue(this.getScreenRecord().getField(CurrencyReqScreenRecord.USD_TOTAL).getValue() + this.getScreenRecord().getField(CurrencyReqScreenRecord.TOTAL_USD).getValue());
        
        super.addSummary(recSummary, mxKeyFields, mxDataFields);
    }

}
