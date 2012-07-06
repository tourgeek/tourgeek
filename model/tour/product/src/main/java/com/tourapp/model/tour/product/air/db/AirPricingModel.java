/**
  * @(#)AirPricingModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.product.air.db;

import com.tourapp.model.tour.product.base.db.*;

public interface AirPricingModel extends ProductPricingModel
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
    public static final String AIR_PRICING_SCREEN_CLASS = "com.tourapp.tour.product.air.screen.AirPricingScreen";
    public static final String AIR_PRICING_GRID_SCREEN_CLASS = "com.tourapp.tour.product.air.screen.AirPricingGridScreen";

    public static final String AIR_PRICING_FILE = "AirPricing";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.air.db.AirPricing";
    public static final String THICK_CLASS = "com.tourapp.tour.product.air.db.AirPricing";

}
