/**
 * @(#)CarPricingModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.car.db;

import com.tourapp.model.tour.product.base.db.*;

public interface CarPricingModel extends ProductPricingModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String PRODUCT_ID = PRODUCT_ID;
    //public static final String PAX_CATEGORY_ID = PAX_CATEGORY_ID;
    //public static final String RATE_ID = RATE_ID;
    //public static final String CLASS_ID = CLASS_ID;
    //public static final String START_DATE = START_DATE;
    //public static final String END_DATE = END_DATE;
    //public static final String COST = COST;
    //public static final String PRODUCT_TERMS_ID = PRODUCT_TERMS_ID;
    //public static final String PRICE = PRICE;
    //public static final String COMMISSIONABLE = COMMISSIONABLE;
    //public static final String COMMISSION_RATE = COMMISSION_RATE;
    //public static final String PAY_AT = PAY_AT;
    public static final String MAX_PAX = "MaxPax";
    public static final String CAR_PRICING_SCREEN_CLASS = "com.tourapp.tour.product.car.screen.CarPricingScreen";
    public static final String CAR_PRICING_GRID_SCREEN_CLASS = "com.tourapp.tour.product.car.screen.CarPricingGridScreen";
    public static final String CAR_INVENTORY_RANGE_ADJUST_CLASS = "com.tourapp.tour.product.car.screen.CarInventoryRangeAdjust";

    public static final String CAR_PRICING_FILE = "CarPricing";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.car.db.CarPricing";
    public static final String THICK_CLASS = "com.tourapp.tour.product.car.db.CarPricing";

}
