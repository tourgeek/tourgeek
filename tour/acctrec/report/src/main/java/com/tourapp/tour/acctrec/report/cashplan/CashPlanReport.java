/**
  * @(#)CashPlanReport.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
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
        this.getMainRecord().setKeyArea(Booking.BOOKING_DATE_KEY);
        this.getMainRecord().addListener(new ValidBookingHandler(null));
        
        Record recBooking = this.getRecord(Booking.BOOKING_FILE);
        Record recArTrx = this.getRecord(ArTrx.AR_TRX_FILE);
        Record recBookingLine = this.getRecord(BookingLine.BOOKING_LINE_FILE);
        
        recArTrx.addListener(new SubFileFilter(recBooking));
        recBooking.addListener(new RecountOnValidHandler(recArTrx));
        recArTrx.addListener(new SubCountHandler(recBooking.getField(Booking.BALANCE), ArTrx.AMOUNT, true, true));
        
        recBookingLine.addListener(new SubFileFilter(recBooking));
        recBooking.addListener(new RecountOnValidHandler(recBookingLine));
        recBookingLine.addListener(new SubCountHandler(recBooking.getField(Booking.GROSS), BookingLine.GROSS, true, true));
        recBookingLine.addListener(new SubCountHandler(recBooking.getField(Booking.NET), BookingLine.NET, true, true));
        
        recBooking.addListener(new CalcPlanTotals((ScreenRecord)this.getScreenRecord()));
        recBooking.addListener(new CalcStartDateHandler(this.getScreenRecord().getField(CashPlanScreenRecord.DEPOSIT_PERIOD_DATE), recBooking.getField(Booking.DEPOSIT_DUE_DATE), this.getScreenRecord().getField(CashPlanScreenRecord.START_DATE), this.getScreenRecord().getField(CashPlanScreenRecord.PERIOD_TYPE), this.getScreenRecord().getField(CashPlanScreenRecord.PERIOD_LENGTH)));
        recBooking.addListener(new CalcStartDateHandler(this.getScreenRecord().getField(CashPlanScreenRecord.FINAL_PERIOD_DATE), recBooking.getField(Booking.FINAL_PAYMENT_DUE_DATE), this.getScreenRecord().getField(CashPlanScreenRecord.START_DATE), this.getScreenRecord().getField(CashPlanScreenRecord.PERIOD_TYPE), this.getScreenRecord().getField(CashPlanScreenRecord.PERIOD_LENGTH)));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new CashPlanToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(CashPlanScreenRecord.CASH_PLAN_SCREEN_RECORD_FILE).getField(CashPlanScreenRecord.FROM_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashPlanScreenRecord.CASH_PLAN_SCREEN_RECORD_FILE).getField(CashPlanScreenRecord.DEPOSITS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashPlanScreenRecord.CASH_PLAN_SCREEN_RECORD_FILE).getField(CashPlanScreenRecord.RECEIPTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashPlanScreenRecord.CASH_PLAN_SCREEN_RECORD_FILE).getField(CashPlanScreenRecord.FINAL_PAYMENTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashPlanScreenRecord.CASH_PLAN_SCREEN_RECORD_FILE).getField(CashPlanScreenRecord.FINAL_RECEIPTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashPlanScreenRecord.CASH_PLAN_SCREEN_RECORD_FILE).getField(CashPlanScreenRecord.BALANCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        this.getScreenRecord().getField(CashPlanScreenRecord.FROM_DATE).moveFieldToThis(this.getScreenRecord().getField(CashPlanScreenRecord.DEPOSIT_PERIOD_DATE));
        this.getScreenRecord().getField(CashPlanScreenRecord.DEPOSITS).moveFieldToThis(this.getRecord(Booking.BOOKING_FILE).getField(Booking.DEPOSIT));
        this.getScreenRecord().getField(CashPlanScreenRecord.RECEIPTS).moveFieldToThis(this.getScreenRecord().getField(CashPlanScreenRecord.DEPOSIT_DUE_BALANCE));
        this.getScreenRecord().getField(CashPlanScreenRecord.FINAL_PAYMENTS).initField(true);
        this.getScreenRecord().getField(CashPlanScreenRecord.FINAL_RECEIPTS).initField(true);
        this.getScreenRecord().getField(CashPlanScreenRecord.BALANCE).initField(true);
        
        super.addSummary(recSummary, mxKeyFields, mxDataFields);
        
        this.getScreenRecord().getField(CashPlanScreenRecord.FROM_DATE).moveFieldToThis(this.getScreenRecord().getField(CashPlanScreenRecord.FINAL_PERIOD_DATE));
        this.getScreenRecord().getField(CashPlanScreenRecord.DEPOSITS).initField(true);
        this.getScreenRecord().getField(CashPlanScreenRecord.RECEIPTS).initField(true);
        this.getScreenRecord().getField(CashPlanScreenRecord.FINAL_PAYMENTS).moveFieldToThis(this.getRecord(Booking.BOOKING_FILE).getField(Booking.FINAL_PAYMENT_DUE_DATE));
        this.getScreenRecord().getField(CashPlanScreenRecord.FINAL_RECEIPTS).moveFieldToThis(this.getScreenRecord().getField(CashPlanScreenRecord.FINAL_DUE_LESS_DEPOSIT));
        this.getScreenRecord().getField(CashPlanScreenRecord.BALANCE).moveFieldToThis(this.getScreenRecord().getField(CashPlanScreenRecord.FINAL_DUE_LESS_DEP_PYMT));
        
        super.addSummary(recSummary, mxKeyFields, mxDataFields);
    }

}
