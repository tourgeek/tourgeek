/**
  * @(#)ProductSearchTypeModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.product.base.search.db;

import org.jbundle.model.db.*;

public interface ProductSearchTypeModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String AIR = "Air";
    public static final String CAR = "Car";
    public static final String HOTEL = "Hotel";
    public static final String ITEM = "Item";
    public static final String TOUR = "Tour";
    public static final String TRANSPORTATION = "Transportation";
    public static final String CRUISE = "Cruise";
    public static final String LAND = "Land";

    public static final String DESCRIPTION_KEY = "Description";
    public static final String PRODUCT_SEARCH_TYPE_SCREEN_CLASS = "com.tourapp.tour.product.base.search.screen.ProductSearchTypeScreen";
    public static final String PRODUCT_SEARCH_TYPE_GRID_SCREEN_CLASS = "com.tourapp.tour.product.base.search.screen.ProductSearchTypeGridScreen";

    public static final String PRODUCT_SEARCH_TYPE_FILE = "ProductSearchType";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.search.db.ProductSearchType";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.search.db.ProductSearchType";

}
