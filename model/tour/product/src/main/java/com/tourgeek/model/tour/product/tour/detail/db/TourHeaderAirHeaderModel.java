
package com.tourgeek.model.tour.product.tour.detail.db;

import com.tourgeek.model.tour.product.tour.detail.db.*;

public interface TourHeaderAirHeaderModel extends TourSubModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String TOUR_HEADER_OPTION_ID = TOUR_HEADER_OPTION_ID;
    //public static final String MODIFY_CODE = MODIFY_CODE;
    //public static final String MODIFY_ID = MODIFY_ID;
    public static final String AIRLINE_CODE = "AirlineCode";
    public static final String AIRLINE_IATA = "AirlineIATA";
    public static final String AIRLINE_DESC = "AirlineDesc";
    public static final String CONJUNCTION = "Conjunction";
    public static final String ENDORSEMENTS = "Endorsements";
    public static final String ORIGIN_DEST = "OriginDest";
    public static final String BOOKING_REFERENCE = "BookingReference";
    public static final String TOUR_CODE = "TourCode";
    public static final String TOTAL_FARE_BASIS = "TotalFareBasis";
    public static final String FARE = "Fare";
    public static final String EQUIVALENT = "Equivalent";
    public static final String CURRENCY_CODE = "CurrencyCode";
    public static final String TAX_PERCENT = "TaxPercent";
    public static final String TAX_1 = "Tax1";
    public static final String TAX_1_DESC = "Tax1Desc";
    public static final String TAX_2 = "Tax2";
    public static final String TAX_2_DESC = "Tax2Desc";
    public static final String TOTAL = "Total";
    public static final String COMMISSION = "Commission";
    public static final String TAX = "Tax";
    public static final String COMMISSION_RATE = "CommissionRate";
    public static final String AGENT = "Agent";
    public static final String INTERNATIONAL = "International";
    public static final String COMM_PERCENT = "CommPercent";
    public static final String COMM_AMOUNT = "CommAmount";
    public static final String TICKET_BY = "TicketBy";
    public static final String NET_FARE = "NetFare";
    public static final String OVERRIDE_PERCENT = "OverridePercent";
    public static final String OVERRIDE_AMOUNT = "OverrideAmount";
    public static final String NET_COST = "NetCost";
    public static final String TK_OR_COLL = "TkOrColl";
    public static final String ARC_COST = "ARCCost";
    public static final String PNR = "PNR";
    public static final String VOID = "Void";
    public static final String VOID_DATE = "VoidDate";
    public static final String EXCH_TICKET = "ExchTicket";
    public static final String DEP_DATE = "DepDate";
    public static final String CREDIT = "Credit";
    public static final String COMMENT_1 = "Comment1";
    public static final String COMMENT_2 = "Comment2";
    public static final String COMMENT_3 = "Comment3";
    public static final String CRS_CONF = "CRSConf";
    public static final String CRS_STATUS = "CRSStatus";
    public static final String FREQ_FLIER = "FreqFlier";
    public static final String FARE_1 = "Fare1";
    public static final String FARE_2 = "Fare2";
    public static final String FARE_3 = "Fare3";

    public static final String TOUR_HEADER_OPTION_ID_KEY = "TourHeaderOptionID";
    public static final String TOUR_HEADER_AIR_HEADER_SCREEN_CLASS = "com.tourgeek.tour.product.tour.detail.screen.TourHeaderAirHeaderScreen";
    public static final String TOUR_HEADER_AIR_HEADER_GRID_SCREEN_CLASS = "com.tourgeek.tour.product.tour.detail.screen.TourHeaderAirHeaderGridScreen";
    public static final String TOUR_HEADER_AIR_HEADER_SFIELD_CLASS = "com.tourgeek.tour.product.tour.detail.screen.TourHeaderAirHeaderScreenField";

    public static final String TOUR_HEADER_AIR_HEADER_FILE = "TourHeaderAirHeader";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.product.tour.detail.db.TourHeaderAirHeader";
    public static final String THICK_CLASS = "com.tourgeek.tour.product.tour.detail.db.TourHeaderAirHeader";

}
