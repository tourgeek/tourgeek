/**
 * @(#)ItineraryReportScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.report.itinerary;

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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.detail.db.*;

/**
 *  ItineraryReportScreen - Itinerary report.
 */
public class ItineraryReportScreen extends ReportScreen
{
    /**
     * Default constructor.
     */
    public ItineraryReportScreen()
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
    public ItineraryReportScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Itinerary report";
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
        
        new BookingDetail(this);
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
        
        Record recBooking = ((ReferenceField)this.getScreenRecord().getField(ItineraryScreenRecord.kBookingID)).getReferenceRecord();
        MoveOnValidHandler moveHandler = new MoveOnValidHandler(this.getScreenRecord().getField(ItineraryScreenRecord.kTourID), recBooking.getField(Booking.kTourID));
        recBooking.addListener(moveHandler);
        
        ((ReferenceField)this.getScreenRecord().getField(ItineraryScreenRecord.kTourID)).addListener(new ReadSecondaryHandler(this.getRecord(Tour.kTourFile)));
        
        this.getRecord(Booking.kBookingFile).addListener(new SubFileFilter(this.getRecord(Tour.kTourFile)));
        this.getRecord(Booking.kBookingFile).addListener(new CompareFileFilter(this.getRecord(Booking.kBookingFile).getField(Booking.kID), this.getScreenRecord().getField(ItineraryScreenRecord.kBookingID), DBConstants.EQUALS));
        
        this.getRecord(BookingDetail.kBookingDetailFile).addListener(new SubFileFilter(this.getRecord(Booking.kBookingFile)));
        this.getRecord(BookingDetail.kBookingDetailFile).setKeyArea(BookingDetail.kBookingIDKey);
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new ItineraryToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record recBooking = this.getRecord(Booking.kBookingFile);
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
        // Tour record
        return new TourReportScreen(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.HEADING_SCREEN, null);
    }
    /**
     * Get the detail screen for this report item (null = none).
     */
    public BaseScreen addReportDetail()
    {
        //      Booking detail record
        return new OtherItineraryReportDetail(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.HEADING_SCREEN, null);
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
