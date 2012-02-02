/**
 * @(#)TourHeaderDetailModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.tour.detail.db;

import com.tourapp.model.tour.product.tour.detail.db.*;

public interface TourHeaderDetailModel extends TourSubModel
{

    //public static final String ID = ID;
    //public static final String TOUR_HEADER_OPTION_ID = TOUR_HEADER_OPTION_ID;
    //public static final String MODIFY_CODE = MODIFY_CODE;
    //public static final String MODIFY_ID = MODIFY_ID;
    public static final String DAY = "Day";
    public static final String ETD = "Etd";
    public static final String PRODUCT_TYPE_ID = "ProductTypeID";
    public static final String PRODUCT_ID = "ProductID";
    public static final String RATE_ID = "RateID";
    public static final String CLASS_ID = "ClassID";
    public static final String INFO_MESSAGE_TRANSPORT_ID = "InfoMessageTransportID";
    public static final String INFO_STATUS_ID = "InfoStatusID";
    public static final String COST_MESSAGE_TRANSPORT_ID = "CostMessageTransportID";
    public static final String COST_STATUS_ID = "CostStatusID";
    public static final String INVENTORY_MESSAGE_TRANSPORT_ID = "InventoryMessageTransportID";
    public static final String INVENTORY_STATUS_ID = "InventoryStatusID";
    public static final String PRODUCT_MESSAGE_TRANSPORT_ID = "ProductMessageTransportID";
    public static final String PRODUCT_STATUS_ID = "ProductStatusID";
    public static final String ACK_DAYS = "AckDays";
    public static final String COMMENTS = "Comments";
    public static final String ITINERARY_DESC = "ItineraryDesc";
    public static final String NIGHTS = "Nights";
    public static final String MEAL_1 = "Meal1";
    public static final String MEAL_QTY_1 = "MealQty1";
    public static final String MEAL_DAYS_1 = "MealDays1";
    public static final String MEAL_2 = "Meal2";
    public static final String MEAL_QTY_2 = "MealQty2";
    public static final String MEAL_DAYS_2 = "MealDays2";
    public static final String MEAL_3 = "Meal3";
    public static final String MEAL_QTY_3 = "MealQty3";
    public static final String MEAL_DAYS_3 = "MealDays3";
    public static final String MEAL_4 = "Meal4";
    public static final String MEAL_QTY_4 = "MealQty4";
    public static final String MEAL_DAYS_4 = "MealDays4";
    public static final String PMC_CUTOFF = "PMCCutoff";
    public static final String CITY_ID = "CityID";
    public static final String CITY_CODE = "CityCode";
    public static final String TO_CITY_ID = "ToCityID";
    public static final String TO_CITY_CODE = "ToCityCode";
    public static final String TOUR_HEADER_AIR_HEADER_ID = "TourHeaderAirHeaderID";
    public static final String GMT_TIME = "GMTTime";
    public static final String CITY_DESC = "CityDesc";
    public static final String TO_CITY_DESC = "ToCityDesc";
    public static final String TICKET_ID = "TicketID";
    public static final String XO = "XO";
    public static final String CARRIER = "Carrier";
    public static final String AIRLINE_ID = "AirlineID";
    public static final String FLIGHT_NO = "FlightNo";
    public static final String FLIGHT_CLASS = "FlightClass";
    public static final String ARRIVE_TIME = "ArriveTime";
    public static final String ADD_DAYS = "AddDays";
    public static final String ARC_STATUS = "ARCStatus";
    public static final String FARE_BASIS = "FareBasis";
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String ALLOW = "Allow";
    public static final String DET_FARE = "DetFare";
    public static final String CONFIRMED_BY = "ConfirmedBy";
    public static final String CONFIRMATION_NO = "ConfirmationNo";
    public static final String COUPON = "Coupon";
    public static final String SEAT = "Seat";
    public static final String GATE = "Gate";
    public static final String SEAT_PERF = "SeatPerf";
    public static final String SMOKING = "Smoking";
    public static final String MEALS = "Meals";
    public static final String DAYS = "Days";
    public static final String PRICING_TYPE_ID = "PricingTypeID";

    public static final String TOUR_HEADER_OPTION_ID_KEY = "TourHeaderOptionID";

    public static final String PRODUCT_ID_KEY = "ProductID";

    public static final String TOUR_HEADER_DETAIL_FILE = "TourHeaderDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.tour.detail.db.TourHeaderDetail";
    public static final String THICK_CLASS = "com.tourapp.tour.product.tour.detail.db.TourHeaderDetail";

}
