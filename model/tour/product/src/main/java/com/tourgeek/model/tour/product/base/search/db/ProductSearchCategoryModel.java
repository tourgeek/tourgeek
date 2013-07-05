/**
  * @(#)ProductSearchCategoryModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.product.base.search.db;

import org.jbundle.model.db.*;

public interface ProductSearchCategoryModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String PRODUCT_SEARCH_TYPE_ID = "ProductSearchTypeID";
    public static final String DESCRIPTION = "Description";
    public static final String VALUE = "Value";

    public static final String PRODUCT_SEARCH_TYPE_ID_KEY = "ProductSearchTypeID";
    public static final String PRODUCT_SEARCH_CATEGORY_SCREEN_CLASS = "com.tourgeek.tour.product.base.search.screen.ProductSearchCategoryScreen";
    public static final String PRODUCT_SEARCH_CATEGORY_GRID_CLASS = "com.tourgeek.tour.product.base.search.screen.ProductSearchCategoryGridScreen";

    public static final String PRODUCT_SEARCH_CATEGORY_FILE = "ProductSearchCategory";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.product.base.search.db.ProductSearchCategory";
    public static final String THICK_CLASS = "com.tourgeek.tour.product.base.search.db.ProductSearchCategory";

}
