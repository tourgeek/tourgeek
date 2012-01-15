/**
 * @(#)LandPricingModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.land.db;

import com.tourapp.model.tour.product.base.db.*;

public interface LandPricingModel extends ProductPricingModel
{
    public static final String LAND_PRICING_SCREEN_CLASS = "com.tourapp.tour.product.land.screen.LandPricingScreen";
    public static final String LAND_PRICING_GRID_SCREEN_CLASS = "com.tourapp.tour.product.land.screen.LandPricingGridScreen";

    public static final String LAND_PRICING_FILE = "LandPricing";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.land.db.LandPricing";
    public static final String THICK_CLASS = "com.tourapp.tour.product.land.db.LandPricing";

}
