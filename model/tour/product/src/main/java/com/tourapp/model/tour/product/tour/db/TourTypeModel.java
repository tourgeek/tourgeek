/**
 * @(#)TourTypeModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.tour.db;

import org.jbundle.model.db.*;

public interface TourTypeModel extends Rec
{
    public static final String DESCRIPTION = "Description";
    public static final int SERIES = 1 << 1;

    public static final String TOUR_TYPE_FILE = "TourType";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.tour.db.TourType";
    public static final String THICK_CLASS = "com.tourapp.tour.product.tour.db.TourType";

}
