/**
 * @(#)CruisePricingModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.cruise.db;

import com.tourapp.model.tour.product.base.db.*;

public interface CruisePricingModel extends ProductPricingModel
{
    public static final String CRUISE_PRICING_SCREEN_CLASS = "com.tourapp.tour.product.cruise.screen.CruisePricingScreen";
    public static final String CRUISE_PRICING_GRID_SCREEN_CLASS = "com.tourapp.tour.product.cruise.screen.CruisePricingGridScreen";

    public static final String CRUISE_PRICING_FILE = "CruisePricing";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.cruise.db.CruisePricing";
    public static final String THICK_CLASS = "com.tourapp.tour.product.cruise.db.CruisePricing";

}
