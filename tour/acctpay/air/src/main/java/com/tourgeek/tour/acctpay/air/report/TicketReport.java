
package com.tourgeek.tour.acctpay.air.report;

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
import com.tourgeek.tour.booking.detail.db.*;
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
        
        //xthis.getMainRecord().setKeyArea(TicketTrx.START_SERVICE_DATE_KEY);
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.AIRLINE_ID), this.getScreenRecord().getField(TicketScreenRecord.AIRLINE_1ID), FileListener.EQUALS));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.AIRLINE_ID), this.getScreenRecord().getField(TicketScreenRecord.AIRLINE_2ID), FileListener.EQUALS));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.AIRLINE_ID), this.getScreenRecord().getField(TicketScreenRecord.AIRLINE_3ID), FileListener.EQUALS));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.AIRLINE_ID), this.getScreenRecord().getField(TicketScreenRecord.AIRLINE_4ID), FileListener.EQUALS));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.START_SERVICE_DATE), this.getScreenRecord().getField(TicketScreenRecord.START_DEPARTURE), FileListener.GREATER_THAN_EQUAL));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.START_SERVICE_DATE), this.getScreenRecord().getField(TicketScreenRecord.END_DEPARTURE), FileListener.LESS_THAN_EQUAL));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.ISSUE_DATE), this.getScreenRecord().getField(TicketScreenRecord.START_ISSUE), FileListener.GREATER_THAN_EQUAL));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.ISSUE_DATE), this.getScreenRecord().getField(TicketScreenRecord.END_ISSUE), FileListener.LESS_THAN_EQUAL));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.TICKET_NUMBER), this.getScreenRecord().getField(TicketScreenRecord.START_TICKET), FileListener.GREATER_THAN_EQUAL));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.TICKET_NUMBER), this.getScreenRecord().getField(TicketScreenRecord.END_TICKET), FileListener.LESS_THAN_EQUAL));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.VOID_DATE), (BaseField)null, FileListener.NOT_EQUAL, this.getScreenRecord().getField(TicketScreenRecord.INCLUDE_VOID), true));
        
        SortOrderHandler keyBehavior = new SortOrderHandler(this.getMainRecord());
        this.getScreenRecord().getField(TicketScreenRecord.REPORT_ORDER).setValue(0); // No necessary
        //xkeyBehavior.setGridTable(TicketTrx.START_SERVICE_DATE_KEY, this.getMainRecord(), 0);
        //xkeyBehavior.setGridTable(TicketTrx.ISSUE_DATE_KEY, this.getMainRecord(), 1);
        //xkeyBehavior.setGridTable(TicketTrx.TICKET_NUMBER_KEY, this.getMainRecord(), 2);
        this.getScreenRecord().getField(TicketScreenRecord.REPORT_ORDER).addListener(keyBehavior);
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new TicketToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(TicketTrx.TICKET_TRX_FILE).getField(TicketTrx.TICKET_NUMBER).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.TICKET_TRX_FILE).getField(TicketTrx.ISSUE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingAirHeader.BOOKING_AIR_HEADER_FILE).getField(BookingAirHeader.PAX_NAME).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.TICKET_TRX_FILE).getField(TicketTrx.START_SERVICE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.TICKET_TRX_FILE).getField(TicketTrx.FARE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.TICKET_TRX_FILE).getField(TicketTrx.NET_FARE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.TICKET_TRX_FILE).getField(TicketTrx.VOID_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
