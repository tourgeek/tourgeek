
package com.tourgeek.model.tour.product.hotel.db;

import com.tourgeek.model.tour.product.base.db.*;

public interface HotelMealPricingModel extends ProductPricingModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String HOTEL_ID = PRODUCT_ID;
    //public static final String PAX_CATEGORY_ID = PAX_CATEGORY_ID;
    //public static final String RATE_ID = RATE_ID;
    public static final String MEAL_PLAN_ID = CLASS_ID;
    //public static final String START_DATE = START_DATE;
    //public static final String END_DATE = END_DATE;
    //public static final String COST = COST;
    //public static final String PRODUCT_TERMS_ID = PRODUCT_TERMS_ID;
    //public static final String PRICE = PRICE;
    //public static final String COMMISSIONABLE = COMMISSIONABLE;
    //public static final String COMMISSION_RATE = COMMISSION_RATE;
    //public static final String PAY_AT = PAY_AT;

    public static final String HOTEL_ID_KEY = "HotelID";
    public static final String HOTEL_MEAL_PRICING_SCREEN_CLASS = "com.tourgeek.tour.product.hotel.screen.HotelMealPricingScreen";
    public static final String HOTEL_MEAL_PRICING_GRID_SCREEN_CLASS = "com.tourgeek.tour.product.hotel.screen.HotelMealPricingGridScreen";

    public static final String HOTEL_MEAL_PRICING_FILE = "HotelMealPricing";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.product.hotel.db.HotelMealPricing";
    public static final String THICK_CLASS = "com.tourgeek.tour.product.hotel.db.HotelMealPricing";

}
