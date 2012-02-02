/**
 * @(#)TourModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.db;

import com.tourapp.model.tour.booking.db.*;

public interface TourModel extends JobModel
{

    //public static final String ID = ID;
    //public static final String DESCRIPTION = DESCRIPTION;
    public static final String CODE = "Code";
    public static final String TOUR_HEADER_ID = "TourHeaderID";
    public static final String DEPARTURE_DATE = "DepartureDate";
    public static final String TOUR_STATUS_SUMMARY = "TourStatusSummary";
    public static final String TOUR_STATUS_ID = "TourStatusID";
    public static final String MANUAL_TOUR_STATUS = "ManualTourStatus";
    public static final String MIN_TO_OP = "MinToOp";
    public static final String AIR_RATE_ID = "AirRateID";
    public static final String AIR_CLASS_ID = "AirClassID";
    public static final String HOTEL_RATE_ID = "HotelRateID";
    public static final String HOTEL_CLASS_ID = "HotelClassID";
    public static final String LAND_RATE_ID = "LandRateID";
    public static final String LAND_CLASS_ID = "LandClassID";
    public static final String PMC_CUTOFF = "PMCCutoff";
    public static final String CAR_RATE_ID = "CarRateID";
    public static final String CAR_CLASS_ID = "CarClassID";
    public static final String TRANSPORTATION_RATE_ID = "TransportationRateID";
    public static final String TRANSPORTATION_CLASS_ID = "TransportationClassID";
    public static final String CRUISE_RATE_ID = "CruiseRateID";
    public static final String CRUISE_CLASS_ID = "CruiseClassID";
    public static final String ITEM_RATE_ID = "ItemRateID";
    public static final String ITEM_CLASS_ID = "ItemClassID";
    public static final String FOCS = "Focs";
    public static final String FILE_NO = "FileNo";
    public static final String FINALIZE_DATE = "FinalizeDate";
    public static final String ORDER_COMP_DATE = "OrderCompDate";
    public static final String CLOSED_DATE = "ClosedDate";
    public static final String FINAL_DOC_DATE = "FinalDocDate";
    public static final String TICKET_DATE = "TicketDate";
    public static final String SP_1_DATE = "Sp1Date";
    public static final String SP_2_DATE = "Sp2Date";
    public static final String FINALIZED = "Finalized";
    public static final String ORDER_COMPONENTS = "OrderComponents";
    public static final String CLOSED = "Closed";
    public static final String FINAL_DOCS = "FinalDocs";
    public static final String TICKETS = "Tickets";
    public static final String SP_1 = "Sp1";
    public static final String SP_2 = "Sp2";
    public static final String SERV_CONF = "ServConf";
    public static final String DEPARTED = "Departed";
    public static final String CANCELLED = "Cancelled";
    public static final String NEXT_EVENT_DATE = "NextEventDate";
    public static final String TOUR_EVENT_ID = "TourEventID";
    public static final String PROPERTIES = "Properties";

    public static final String CODE_KEY = "Code";

    public static final String TOUR_HEADER_ID_KEY = "TourHeaderID";

    public static final String DEPARTURE_DATE_KEY = "DepartureDate";

    public static final String NEXT_EVENT_DATE_KEY = "NextEventDate";

    public static final String DESCRIPTION_KEY = "Description";
    public static final String TOUR_SCREEN_CLASS = "com.tourapp.tour.product.tour.other.screen.TourScreen";
    public static final String TOUR_GRID_SCREEN_CLASS = "com.tourapp.tour.booking.lookup.TourGridScreen";

    public static final String TOUR_FILE = "Tour";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.db.Tour";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.db.Tour";

}
