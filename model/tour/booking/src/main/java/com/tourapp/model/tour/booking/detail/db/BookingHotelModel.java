/**
 * @(#)BookingHotelModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.detail.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingHotelModel extends BookingDetailModel
{
    public static final String NIGHTS = "Nights";
    public static final String MEAL_PLAN_1ID = "MealPlan1ID";
    public static final String MEAL_PLAN_1_DAYS = "MealPlan1Days";
    public static final String MEAL_PLAN_2ID = "MealPlan2ID";
    public static final String MEAL_PLAN_2_DAYS = "MealPlan2Days";
    public static final String MEAL_PLAN_3ID = "MealPlan3ID";
    public static final String MEAL_PLAN_3_DAYS = "MealPlan3Days";
    public static final String MEAL_PLAN_4ID = "MealPlan4ID";
    public static final String MEAL_PLAN_4_DAYS = "MealPlan4Days";
    public static final String SINGLE_COST = "SingleCost";
    public static final String DOUBLE_COST = "DoubleCost";
    public static final String TRIPLE_COST = "TripleCost";
    public static final String QUAD_COST = "QuadCost";
    public static final String ROOM_COST = "RoomCost";
    public static final String MEAL_COST = "MealCost";
    public static final String BOOKING_HOTEL_SCREEN_CLASS = "com.tourapp.tour.booking.entry.detail.hotel.BookingHotelScreen";
    public static final String BOOKING_HOTEL_GRID_SCREEN_CLASS = "com.tourapp.tour.booking.entry.detail.hotel.BookingHotelGridScreen";

    public static final String BOOKING_HOTEL_FILE = "BookingDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.detail.db.BookingHotel";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.detail.db.BookingHotel";

}
