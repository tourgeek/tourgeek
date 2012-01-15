/**
 * @(#)HotelPricingModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.hotel.db;

import com.tourapp.model.tour.product.base.db.*;

public interface HotelPricingModel extends ProductPricingModel
{
    public static final String HOTEL_PRICING_SCREEN_CLASS = "com.tourapp.tour.product.hotel.screen.HotelPricingScreen";
    public static final String HOTEL_PRICING_GRID_SCREEN_CLASS = "com.tourapp.tour.product.hotel.screen.HotelPricingGridScreen";

    public static final String HOTEL_PRICING_FILE = "HotelPricing";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.hotel.db.HotelPricing";
    public static final String THICK_CLASS = "com.tourapp.tour.product.hotel.db.HotelPricing";

}
