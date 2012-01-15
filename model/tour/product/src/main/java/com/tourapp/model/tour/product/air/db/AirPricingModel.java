/**
 * @(#)AirPricingModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.air.db;

import com.tourapp.model.tour.product.base.db.*;

public interface AirPricingModel extends ProductPricingModel
{
    public static final String AIR_PRICING_SCREEN_CLASS = "com.tourapp.tour.product.air.screen.AirPricingScreen";
    public static final String AIR_PRICING_GRID_SCREEN_CLASS = "com.tourapp.tour.product.air.screen.AirPricingGridScreen";

    public static final String AIR_PRICING_FILE = "AirPricing";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.air.db.AirPricing";
    public static final String THICK_CLASS = "com.tourapp.tour.product.air.db.AirPricing";

}
