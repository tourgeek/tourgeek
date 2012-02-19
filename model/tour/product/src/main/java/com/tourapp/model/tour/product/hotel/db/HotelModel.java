/**
 * @(#)HotelModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.hotel.db;

import com.tourapp.model.tour.product.base.db.*;

public interface HotelModel extends ProductModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String CODE = CODE;
    //public static final String VENDOR_ID = VENDOR_ID;
    //public static final String OPERATORS_CODE = OPERATORS_CODE;
    //public static final String PRODUCT_CHAIN_ID = PRODUCT_CHAIN_ID;
    //public static final String CITY_ID = CITY_ID;
    //public static final String ETD = ETD;
    //public static final String ACK_DAYS = ACK_DAYS;
    //public static final String COMMENTS = COMMENTS;
    //public static final String PROPERTIES = PROPERTIES;
    //public static final String ITINERARY_DESC = ITINERARY_DESC;
    //public static final String DESC_SORT = DESC_SORT;
    //public static final String PRODUCT_TYPE = PRODUCT_TYPE;
    //public static final String PRODUCT_COST = PRODUCT_COST;
    //public static final String PRODUCT_COST_LOCAL = PRODUCT_COST_LOCAL;
    //public static final String PRODUCT_MESSAGE_TRANSPORT_ID = PRODUCT_MESSAGE_TRANSPORT_ID;
    //public static final String DISPLAY_INVENTORY_STATUS_ID = DISPLAY_INVENTORY_STATUS_ID;
    //public static final String INVENTORY_AVAILABILITY = INVENTORY_AVAILABILITY;
    //public static final String CURRENCY_CODE = CURRENCY_CODE;
    //public static final String CURRENCY_CODE_LOCAL = CURRENCY_CODE_LOCAL;
    //public static final String VENDOR_NAME = VENDOR_NAME;
    //public static final String DISPLAY_COST_STATUS_ID = DISPLAY_COST_STATUS_ID;
    //public static final String PP_COST = PP_COST;
    //public static final String PP_COST_LOCAL = PP_COST_LOCAL;
    //public static final String RATE_ID = RATE_ID;
    //public static final String CLASS_ID = CLASS_ID;
    //public static final String PRODUCT_PRICE_LOCAL = PRODUCT_PRICE_LOCAL;
    //public static final String PP_PRICE_LOCAL = PP_PRICE_LOCAL;
    public static final String CHECK_OUT = "CheckOut";
    public static final String SAME_AS_VENDOR = "SameAsVendor";
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
    public static final String HOTEL_SCREEN_CLASS = "com.tourapp.tour.product.hotel.screen.HotelScreen";
    public static final String HOTEL_GRID_SCREEN_CLASS = "com.tourapp.tour.product.hotel.screen.HotelGridScreen";

    public static final String HOTEL_FILE = "Hotel";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.hotel.db.Hotel";
    public static final String THICK_CLASS = "com.tourapp.tour.product.hotel.db.Hotel";

}
