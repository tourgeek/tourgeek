/**
 * @(#)HotelModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.hotel.db;

import com.tourapp.model.tour.product.base.db.*;

public interface HotelModel extends ProductModel
{
    public static final String CHECK_OUT = "CheckOut";
    public static final String CONTACT = "Contact";
    public static final String CONTACT_TITLE = "ContactTitle";
    public static final String ADDRESS_LINE_1 = "AddressLine1";
    public static final String ADDRESS_LINE_2 = "AddressLine2";
    public static final String CITY_OR_TOWN = "CityOrTown";
    public static final String STATE_OR_REGION = "StateOrRegion";
    public static final String POSTAL_CODE = "PostalCode";
    public static final String COUNTRY = "Country";
    public static final String TEL = "Tel";
    public static final String FAX = "Fax";
    public static final String EMAIL = "Email";
    public static final String ROOMS = "Rooms";
    public static final String GENERAL_MANAGER = "GeneralManager";
    public static final String SALES_MANAGER = "SalesManager";
    public static final String LOCAL_CONTACT = "LocalContact";
    public static final String LOCAL_PHONE = "LocalPhone";
    public static final String TOLL_FREE_PHONE = "TollFreePhone";
    public static final String ALT_PHONE = "AltPhone";
    public static final String ONE_FREE = "OneFree";
    public static final String FREE_TYPE = "FreeType";
    public static final String CHILD_AGE = "ChildAge";
    public static final String SINGLE_COST = "SingleCost";
    public static final String DOUBLE_COST = "DoubleCost";
    public static final String TRIPLE_COST = "TripleCost";
    public static final String QUAD_COST = "QuadCost";
    public static final String ROOM_COST = "RoomCost";
    public static final String MEAL_COST = "MealCost";
    public static final String SINGLE_COST_LOCAL = "SingleCostLocal";
    public static final String DOUBLE_COST_LOCAL = "DoubleCostLocal";
    public static final String TRIPLE_COST_LOCAL = "TripleCostLocal";
    public static final String QUAD_COST_LOCAL = "QuadCostLocal";
    public static final String ROOM_COST_LOCAL = "RoomCostLocal";
    public static final String MEAL_COST_LOCAL = "MealCostLocal";
    public static final String SINGLE_PRICE_LOCAL = "SinglePriceLocal";
    public static final String DOUBLE_PRICE_LOCAL = "DoublePriceLocal";
    public static final String TRIPLE_PRICE_LOCAL = "TriplePriceLocal";
    public static final String QUAD_PRICE_LOCAL = "QuadPriceLocal";
    public static final String ROOM_PRICE_LOCAL = "RoomPriceLocal";
    public static final String MEAL_PRICE_LOCAL = "MealPriceLocal";
    public static final String MEAL_DETAIL = "Meal";
    public static final String MEAL_PLAN_DAYS_PARAM = "mealDays";

    public static final String HOTEL_FILE = "Hotel";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.hotel.db.Hotel";
    public static final String THICK_CLASS = "com.tourapp.tour.product.hotel.db.Hotel";

}
