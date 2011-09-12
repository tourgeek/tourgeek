/**
 * @(#)CurrencyReqReport.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
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
        
        Record recApTrx = this.getRecord(ApTrx.kApTrxFile);
        Record recVendor = this.getRecord(Vendor.kVendorFile);
        Record recCurrencys = this.getRecord(Currencys.kCurrencysFile);
        
        recApTrx.getField(ApTrx.kVendorID).addListener(new ReadSecondaryHandler(recVendor));
        recVendor.getField(Vendor.kCurrencysID).addListener(new ReadSecondaryHandler(recCurrencys));
        
        recApTrx.addListener(new CalcStartDateHandler(this.getScreenRecord().getField(CurrencyReqScreenRecord.kFromDate), recApTrx.getField(ApTrx.kStartServiceDate), this.getScreenRecord().getField(CurrencyReqScreenRecord.kStartDate), this.getScreenRecord().getField(CurrencyReqScreenRecord.kPeriodType), this.getScreenRecord().getField(CurrencyReqScreenRecord.kPeriodLength)));
        
        this.getRecord(ApTrx.kApTrxFile).addListener(new ApTrxFilter(ApTrx.kTrxStatusID, (ScreenRecord)this.getScreenRecord()));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new CurrencyReqToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
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
        this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kSummaryCurrencyDesc).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kFromDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kDepartureTotal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kBalanceTotal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kTotalTotal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kTotalUSD).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kSummaryCurrencyDesc).moveFieldToThis(this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kDescription));
        this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kDepartureTotal).moveFieldToThis(this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDepartureEstimate));
        this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kBalanceTotal).moveFieldToThis(this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceBalance));
        double dEstimate = this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDepartureEstimate).getValue();
        double dBalance = this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceBalance).getValue();
        double dTotal = dBalance;
        if (dTotal == 0.00)
            dTotal = dEstimate;
        this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kTotalTotal).setValue(dTotal);
        double dTotalUSD = dTotal * this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kLastRate).getValue();
        this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kTotalUSD).setValue(dTotalUSD);
        
        // Add totals:
        this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kUSDTotal).setValue(this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kUSDTotal).getValue() + this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kTotalUSD).getValue());
        
        super.addSummary(recSummary, mxKeyFields, mxDataFields);
    }

}
