/**
 * @(#)TourModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.db;

import com.tourapp.model.tour.booking.db.*;

public interface TourModel extends JobModel
{
    public static final String DESCRIPTION = "Description";
    public static final String TOUR_HEADER_ID = "TourHeaderID";
    public static final String DEPARTURE_DATE = "DepartureDate";
    public static final String TOUR_STATUS_ID = "TourStatusID";
    public static final String TOUR_SCREEN_CLASS = "com.tourapp.tour.product.tour.other.screen.TourScreen";
    public static final String TOUR_GRID_SCREEN_CLASS = "com.tourapp.tour.booking.lookup.TourGridScreen";

    public static final String TOUR_FILE = "Tour";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.db.Tour";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.db.Tour";

}
