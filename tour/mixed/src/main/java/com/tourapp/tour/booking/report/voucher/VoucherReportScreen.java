/**
 * @(#)VoucherReportScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.report.voucher;

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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.report.itinerary.*;
import com.tourapp.tour.acctpay.db.*;

/**
 *  VoucherReportScreen - .
 */
public class VoucherReportScreen extends ReportScreen
{
    /**
     * Default constructor.
     */
    public VoucherReportScreen()
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
    public VoucherReportScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Booking(this); // Since Tour is the header
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new Tour(this);
        
        new ApTrx(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new ItineraryScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        ((ReferenceField)this.getScreenRecord().getField(ItineraryScreenRecord.TOUR_ID)).addListener(new ReadSecondaryHandler(this.getRecord(Tour.TOUR_FILE)));
        
        this.getRecord(Booking.BOOKING_FILE).addListener(new SubFileFilter(this.getRecord(Tour.TOUR_FILE)));
        
        this.getRecord(ApTrx.AP_TRX_FILE).addListener(new SubFileFilter(this.getRecord(Tour.TOUR_FILE)));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new ItineraryToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record recBooking = this.getRecord(Booking.BOOKING_FILE);
        ((ReferenceField)recBooking.getField(Booking.TOUR_ID)).setReferenceRecord(this.getRecord(Tour.TOUR_FILE));
        for (int iFieldSeq = 0; iFieldSeq < recBooking.getFieldCount(); iFieldSeq++)
        {
            BaseField field = recBooking.getField(iFieldSeq);
            this.addColumn(field);
        }
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        TourReportScreen tourReportScreen = new TourReportScreen(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.HEADING_SCREEN, null); 
        // Add this A/P Trx detail screen
        VoucherDetailReportScreen voucherDetailReportScreen = new VoucherDetailReportScreen(null, null, tourReportScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.HEADING_SCREEN, null);
        
        return tourReportScreen;
    }
    /**
     * Get the detail screen for this report item (null = none).
     */
    public BaseScreen addReportDetail()
    {
        // Pax detail
        return new BookingPaxReportDetail(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.HEADING_SCREEN, null);
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
