/**
 * @(#)CashPlanReport.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.report.cashplan;

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
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.base.db.event.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  CashPlanReport - Cash Receipts Planning Report.
 */
public class CashPlanReport extends AnalysisScreen
{
    /**
     * Default constructor.
     */
    public CashPlanReport()
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
    public CashPlanReport(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Cash Receipts Planning Report";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Booking(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ArTrx(this);
        new BookingLine(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new CashPlanScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getMainRecord().setKeyArea(Booking.kBookingDateKey);
        this.getMainRecord().addListener(new ValidBookingHandler(null));
        
        Record recBooking = this.getRecord(Booking.kBookingFile);
        Record recArTrx = this.getRecord(ArTrx.kArTrxFile);
        Record recBookingLine = this.getRecord(BookingLine.kBookingLineFile);
        
        recArTrx.addListener(new SubFileFilter(recBooking));
        recBooking.addListener(new RecountOnValidHandler(recArTrx));
        recArTrx.addListener(new SubCountHandler(recBooking.getField(Booking.kBalance), ArTrx.kAmount, true, true));
        
        recBookingLine.addListener(new SubFileFilter(recBooking));
        recBooking.addListener(new RecountOnValidHandler(recBookingLine));
        recBookingLine.addListener(new SubCountHandler(recBooking.getField(Booking.kGross), BookingLine.kGross, true, true));
        recBookingLine.addListener(new SubCountHandler(recBooking.getField(Booking.kNet), BookingLine.kNet, true, true));
        
        
        recBooking.addListener(new CalcPlanTotals((ScreenRecord)this.getScreenRecord()));
        recBooking.addListener(new CalcStartDateHandler(this.getScreenRecord().getField(CashPlanScreenRecord.kDepositPeriodDate), recBooking.getField(Booking.kDepositDueDate), this.getScreenRecord().getField(CashPlanScreenRecord.kStartDate), this.getScreenRecord().getField(CashPlanScreenRecord.kPeriodType), this.getScreenRecord().getField(CashPlanScreenRecord.kPeriodLength)));
        recBooking.addListener(new CalcStartDateHandler(this.getScreenRecord().getField(CashPlanScreenRecord.kFinalPeriodDate), recBooking.getField(Booking.kFinalPaymentDueDate), this.getScreenRecord().getField(CashPlanScreenRecord.kStartDate), this.getScreenRecord().getField(CashPlanScreenRecord.kPeriodType), this.getScreenRecord().getField(CashPlanScreenRecord.kPeriodLength)));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new CashPlanToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kFromDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kDeposits).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kReceipts).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kFinalPayments).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kFinalReceipts).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kBalance).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new CashPlanHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Add/update this summary record.
     * @param recSummary The destination summary record.
     * @param mxKeyFields The key fields map.
     * @param mxDataFields The data fields map.
     */
    public void addSummary(Record recSummary, BaseField[][] mxKeyFields, BaseField[][] mxDataFields)
    {
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kFromDate).moveFieldToThis(this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kDepositPeriodDate));
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kDeposits).moveFieldToThis(this.getRecord(Booking.kBookingFile).getField(Booking.kDeposit));
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kReceipts).moveFieldToThis(this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kDepositDueBalance));
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kFinalPayments).initField(true);
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kFinalReceipts).initField(true);
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kBalance).initField(true);
        
        super.addSummary(recSummary, mxKeyFields, mxDataFields);
        
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kFromDate).moveFieldToThis(this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kFinalPeriodDate));
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kDeposits).initField(true);
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kReceipts).initField(true);
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kFinalPayments).moveFieldToThis(this.getRecord(Booking.kBookingFile).getField(Booking.kFinalPaymentDueDate));
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kFinalReceipts).moveFieldToThis(this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kFinalDueLessDeposit));
        this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kBalance).moveFieldToThis(this.getRecord(CashPlanScreenRecord.kCashPlanScreenRecordFile).getField(CashPlanScreenRecord.kFinalDueLessDepPymt));
        
        super.addSummary(recSummary, mxKeyFields, mxDataFields);
    }

}
