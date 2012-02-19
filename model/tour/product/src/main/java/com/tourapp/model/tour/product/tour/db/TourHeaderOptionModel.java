/**
 * @(#)TourHeaderOptionModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.tour.db;

import org.jbundle.model.db.*;

public interface TourHeaderOptionModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String TOUR_OR_OPTION = "TourOrOption";
    public static final String TOUR_OR_OPTION_ID = "TourOrOptionID";
    public static final String SEQUENCE = "Sequence";
    public static final String DESCRIPTION = "Description";
    public static final String COMMENTS = "Comments";
    public static final String ASK_FOR_ANSWER = "AskForAnswer";
    public static final String ALWAYS_RESOLVE = "AlwaysResolve";
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String DAYS_OF_WEEK = "DaysOfWeek";
    public static final String GATEWAY = "Gateway";
    public static final String USE_TOUR_HEADER_OPTION_ID = "UseTourHeaderOptionID";
    public static final String PAX_CATEGORY_ID = "PaxCategoryID";
    public static final String DETAIL_OPTION_COUNT = "DetailOptionCount";
    public static final String DETAIL_PRICE_COUNT = "DetailPriceCount";
    public static final String DETAIL_AIR_HEADER_COUNT = "DetailAirHeaderCount";
    public static final String DETAIL_TOUR_DETAIL_COUNT = "DetailTourDetailCount";
    public static final String TOUR_CLASS_ID = "TourClassID";
    public static final String INVOICE_TEXT = "InvoiceText";
    public static final String ITINERARY_DESC = "ItineraryDesc";

    public static final String TOUR_OR_OPTION_KEY = "TourOrOption";
    public static final String OPTION = "O";
    public static final String TOUR_HEADER_OPTION_SCREEN_CLASS = "com.tourapp.tour.product.tour.screen.TourHeaderOptionScreen";
    public static final String TOUR = "T";
    public static final String TOUR_HEADER_OPTION_GRID_SCREEN_CLASS = "com.tourapp.tour.product.tour.screen.TourHeaderOptionGridScreen";

    public static final String TOUR_HEADER_OPTION_FILE = "TourHeaderOption";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.tour.db.TourHeaderOption";
    public static final String THICK_CLASS = "com.tourapp.tour.product.tour.db.TourHeaderOption";

}
