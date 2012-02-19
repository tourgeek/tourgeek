/**
 * @(#)PastDueReport.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.report.pastdue;

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
import com.tourapp.tour.genled.db.*;
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.main.db.*;

/**
 *  PastDueReport - Print past due reports and notices.
 */
public class PastDueReport extends ReportScreen
{
    /**
     * Default constructor.
     */
    public PastDueReport()
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
    public PastDueReport(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Print past due reports and notices";
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
        new Tour(this);
        new TourHeader(this);
        new ArTrx(this);
        new BookingLine(this);
        new ArControl(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getScreenRecord().getField(PastDueScreenRecord.MCO_PER).addListener(new InitFieldHandler(this.getRecord(ArControl.AR_CONTROL_FILE).getField(ArControl.MCO_COMM_PER)));
        
        this.getMainRecord().setKeyArea(Booking.BOOKING_DATE_KEY);
        SubFileFilter listener = new SubFileFilter(this.getScreenRecord().getField(PastDueScreenRecord.AS_OF_DATE), Booking.BOOKING_DATE, null, null, null, null);
        listener.setInitialKey(false);      // Search all bookings up to this booking date
        this.getMainRecord().addListener(listener);
        this.getMainRecord().addListener(new PastDueHandler(this.getScreenRecord()));
        
        this.getMainRecord().getField(Booking.TOUR_ID).addListener(new ReadSecondaryHandler(this.getRecord(Tour.TOUR_FILE)));
        this.getRecord(Tour.TOUR_FILE).getField(Tour.TOUR_HEADER_ID).addListener(new ReadSecondaryHandler(this.getRecord(TourHeader.TOUR_HEADER_FILE)));
        
        Record recArTrx = this.getRecord(ArTrx.AR_TRX_FILE);
        Record recBooking = this.getRecord(Booking.BOOKING_FILE);
        Record recBookingLine = this.getRecord(BookingLine.BOOKING_LINE_FILE);
        
        recArTrx.addListener(new SubFileFilter(recBooking));
        recBooking.addListener(new RecountOnValidHandler(recArTrx));
        recArTrx.addListener(new SubCountHandler(recBooking.getField(Booking.BALANCE), ArTrx.AMOUNT, true, true));
        recArTrx.addListener(new CountMcoAmountHandler(this.getScreenRecord().getField(PastDueScreenRecord.MCO_AMOUNT_PAID), ArTrx.AMOUNT, true, true));
        
        recBookingLine.addListener(new SubFileFilter(recBooking));
        recBooking.addListener(new RecountOnValidHandler(recBookingLine));
        recBookingLine.addListener(new SubCountHandler(recBooking.getField(Booking.GROSS), BookingLine.GROSS, true, true));
        recBookingLine.addListener(new SubCountHandler(recBooking.getField(Booking.NET), BookingLine.NET, true, true));
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new PastDueScreenRecord(this);
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new PastDueToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.BOOKING_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.EMPLOYEE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.DESCRIPTION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.PROFILE_CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.GENERIC_NAME).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.ADDRESS_LINE_1).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.ADDRESS_LINE_2).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.CITY_OR_TOWN).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.STATE_OR_REGION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.POSTAL_CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.TEL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.CONTACT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PastDueScreenRecord.PAST_DUE_SCREEN_RECORD_FILE).getField(PastDueScreenRecord.DUE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PastDueScreenRecord.PAST_DUE_SCREEN_RECORD_FILE).getField(PastDueScreenRecord.AMT_DUE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PastDueScreenRecord.PAST_DUE_SCREEN_RECORD_FILE).getField(PastDueScreenRecord.DEP_OR_PYMT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.DEPOSIT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.BALANCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.DEPOSIT_DUE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.FINAL_PAYMENT_DUE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeader.TOUR_HEADER_FILE).getField(TourHeader.CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.TOUR_FILE).getField(Tour.DEPARTURE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.GROSS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Booking.BOOKING_FILE).getField(Booking.NET).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new PastDueHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new PastDueFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the path to the target servlet.
     * @param The servlet type (regular html or xhtml)
     * @return the servlet path.
     */
    public String getServletPath(String strServletParam)
    {
        return super.getServletPath(DBParams.XHTMLSERVLET); // Use cocoon
    }

}
