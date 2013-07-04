
package com.tourgeek.tour.product.tour.detail.screen;

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
import com.tourgeek.tour.product.tour.detail.db.*;

/**
 *  TourHeaderAirHeaderScreen - Tour Ticket Header Detail.
 */
public class TourHeaderAirHeaderScreen extends TourDetailScreen
{
    /**
     * Default constructor.
     */
    public TourHeaderAirHeaderScreen()
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
    public TourHeaderAirHeaderScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Tour Ticket Header Detail";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new TourHeaderAirHeader(this);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.AIRLINE_CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.AIRLINE_IATA).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.AIRLINE_DESC).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.CONJUNCTION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.ENDORSEMENTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.ORIGIN_DEST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.BOOKING_REFERENCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.TOUR_CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.TOTAL_FARE_BASIS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.FARE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.EQUIVALENT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.CURRENCY_CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.TAX_PERCENT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.TAX_1).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.TAX_1_DESC).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.TAX_2).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.TAX_2_DESC).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.COMMISSION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.COMMISSION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.TAX).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.COMMISSION_RATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.AGENT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.INTERNATIONAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.COMM_PERCENT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.COMM_AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.TICKET_BY).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.NET_FARE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.OVERRIDE_PERCENT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.OVERRIDE_AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.NET_COST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.TK_OR_COLL).setupDefaultView(this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.ARC_COST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.PNR).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.VOID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.VOID_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.EXCH_TICKET).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.DEP_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.DEP_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.CREDIT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.COMMENT_1).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.COMMENT_2).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.COMMENT_3).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.CRS_CONF).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.CRS_STATUS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.FREQ_FLIER).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.FARE_1).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.FARE_2).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.FARE_3).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.MODIFY_CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE).getField(TourHeaderAirHeader.MODIFY_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
