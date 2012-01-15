/**
 * @(#)TourHeaderModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.tour.db;

import com.tourapp.model.tour.product.base.db.*;

public interface TourHeaderModel extends ProductModel
{
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String TOUR_TYPE = "TourType";
    public static final String DAYS = "Days";
    public static final String NIGHTS = "Nights";
    public static final String TOUR_DETAIL = "Tour detail";

    public static final String TOUR_HEADER_FILE = "TourHeader";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.tour.db.TourHeader";
    public static final String THICK_CLASS = "com.tourapp.tour.product.tour.db.TourHeader";

}
