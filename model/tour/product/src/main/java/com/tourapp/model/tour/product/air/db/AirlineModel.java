/**
 * @(#)AirlineModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.air.db;

import org.jbundle.model.db.*;

public interface AirlineModel extends Rec
{
    public static final String DESCRIPTION = "Description";
    public static final String AIRLINE_CODE = "AirlineCode";
    public static final String LOGO = "Logo";

    public static final String AIRLINE_FILE = "Airline";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.air.db.Airline";
    public static final String THICK_CLASS = "com.tourapp.tour.product.air.db.Airline";

}
