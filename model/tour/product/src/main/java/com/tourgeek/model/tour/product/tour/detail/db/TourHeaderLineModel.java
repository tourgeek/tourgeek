/**
  * @(#)TourHeaderLineModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.product.tour.detail.db;

import com.tourapp.model.tour.product.tour.detail.db.*;

public interface TourHeaderLineModel extends TourSubModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String TOUR_HEADER_OPTION_ID = TOUR_HEADER_OPTION_ID;
    //public static final String MODIFY_CODE = MODIFY_CODE;
    //public static final String MODIFY_ID = MODIFY_ID;
    public static final String SEQUENCE = "Sequence";
    public static final String PAX_CATEGORY_ID = "PaxCategoryID";
    public static final String DESCRIPTION = "Description";
    public static final String PRICE = "Price";
    public static final String COMMISSIONABLE = "Commissionable";
    public static final String COMMISSION_RATE = "CommissionRate";
    public static final String PAY_AT = "PayAt";
    public static final String COST = "Cost";
    public static final String PRODUCT_TERMS_ID = "ProductTermsID";

    public static final String TOUR_HEADER_OPTION_ID_KEY = "TourHeaderOptionID";
    public static final String TOUR_HEADER_LINE_SCREEN_CLASS = "com.tourapp.tour.product.tour.detail.screen.TourHeaderLineScreen";
    public static final String TOUR_HEADER_LINE_GRID_SCREEN_CLASS = "com.tourapp.tour.product.tour.detail.screen.TourHeaderLineGridScreen";

    public static final String TOUR_HEADER_LINE_FILE = "TourHeaderLine";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.tour.detail.db.TourHeaderLine";
    public static final String THICK_CLASS = "com.tourapp.tour.product.tour.detail.db.TourHeaderLine";

}
