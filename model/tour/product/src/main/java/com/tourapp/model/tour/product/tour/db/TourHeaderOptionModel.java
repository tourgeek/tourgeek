/**
 * @(#)TourHeaderOptionModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.tour.db;

import org.jbundle.model.db.*;

public interface TourHeaderOptionModel extends Rec
{
    public static final String OPTION = "O";
    public static final String TOUR_HEADER_OPTION_SCREEN_CLASS = "com.tourapp.tour.product.tour.screen.TourHeaderOptionScreen";
    public static final String TOUR = "T";
    public static final String TOUR_HEADER_OPTION_GRID_SCREEN_CLASS = "com.tourapp.tour.product.tour.screen.TourHeaderOptionGridScreen";

    public static final String TOUR_HEADER_OPTION_FILE = "TourHeaderOption";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.tour.db.TourHeaderOption";
    public static final String THICK_CLASS = "com.tourapp.tour.product.tour.db.TourHeaderOption";

}
