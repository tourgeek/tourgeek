/**
 * @(#)TicketReport.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.air.report;

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
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.main.db.*;

/**
 *  TicketReport - Ticket analysis.
 */
public class TicketReport extends ReportScreen
{
    /**
     * Default constructor.
     */
    public TicketReport()
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
    public TicketReport(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Ticket analysis";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new TicketTrx(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new BookingAirHeader(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new TicketScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        //xthis.getMainRecord().setKeyArea(TicketTrx.kStartServiceDateKey);
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.kAirlineID), this.getScreenRecord().getField(TicketScreenRecord.kAirline1ID), FileListener.EQUALS));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.kAirlineID), this.getScreenRecord().getField(TicketScreenRecord.kAirline2ID), FileListener.EQUALS));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.kAirlineID), this.getScreenRecord().getField(TicketScreenRecord.kAirline3ID), FileListener.EQUALS));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.kAirlineID), this.getScreenRecord().getField(TicketScreenRecord.kAirline4ID), FileListener.EQUALS));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.kStartServiceDate), this.getScreenRecord().getField(TicketScreenRecord.kStartDeparture), FileListener.GREATER_THAN_EQUAL));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.kStartServiceDate), this.getScreenRecord().getField(TicketScreenRecord.kEndDeparture), FileListener.LESS_THAN_EQUAL));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.kIssueDate), this.getScreenRecord().getField(TicketScreenRecord.kStartIssue), FileListener.GREATER_THAN_EQUAL));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.kIssueDate), this.getScreenRecord().getField(TicketScreenRecord.kEndIssue), FileListener.LESS_THAN_EQUAL));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.kTicketNumber), this.getScreenRecord().getField(TicketScreenRecord.kStartTicket), FileListener.GREATER_THAN_EQUAL));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.kTicketNumber), this.getScreenRecord().getField(TicketScreenRecord.kEndTicket), FileListener.LESS_THAN_EQUAL));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.kVoidDate), null, FileListener.NOT_EQUAL, this.getScreenRecord().getField(TicketScreenRecord.kIncludeVoid), true));
        
        SortOrderHandler keyBehavior = new SortOrderHandler(this.getMainRecord());
        this.getScreenRecord().getField(TicketScreenRecord.kReportOrder).setValue(0); // No necessary
        //xkeyBehavior.setGridTable(TicketTrx.kStartServiceDateKey, this.getMainRecord(), 0);
        //xkeyBehavior.setGridTable(TicketTrx.kIssueDateKey, this.getMainRecord(), 1);
        //xkeyBehavior.setGridTable(TicketTrx.kTicketNumberKey, this.getMainRecord(), 2);
        this.getScreenRecord().getField(TicketScreenRecord.kReportOrder).addListener(keyBehavior);
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new TicketToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(TicketTrx.kTicketTrxFile).getField(TicketTrx.kTicketNumber).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.kTicketTrxFile).getField(TicketTrx.kIssueDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingAirHeader.kBookingAirHeaderFile).getField(BookingAirHeader.kPaxName).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.kTicketTrxFile).getField(TicketTrx.kStartServiceDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.kTicketTrxFile).getField(TicketTrx.kFare).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.kTicketTrxFile).getField(TicketTrx.kNetFare).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.kTicketTrxFile).getField(TicketTrx.kVoidDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new TicketHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new TicketFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}
