
package com.tourgeek.model.tour.product.base.search.db;

import org.jbundle.model.db.*;

public interface ProductSearchDetailModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String PRODUCT_ID = "ProductID";
    public static final String PRODUCT_TYPE_ID = "ProductTypeID";
    public static final String PRODUCT_SEARCH_TYPE_ID = "ProductSearchTypeID";
    public static final String PRODUCT_SEARCH_CATEGORY_ID = "ProductSearchCategoryID";
    public static final String SEARCH_DATA = "SearchData";

    public static final String PRODUCT_ID_KEY = "ProductID";

    public static final String PRODUCT_SEARCH_CATEGORY_ID_KEY = "ProductSearchCategoryID";
    public static final String PRODUCT_SEARCH_DETAIL_SCREEN_CLASS = "com.tourgeek.tour.product.base.search.screen.ProductSearchDetailScreen";
    public static final String PRODUCT_SEARCH_DETAIL_GRID_SCREEN_CLASS = "com.tourgeek.tour.product.base.search.screen.ProductSearchDetailGridScreen";

    public static final String PRODUCT_SEARCH_DETAIL_FILE = "ProductSearchDetail";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.product.base.search.db.ProductSearchDetail";
    public static final String THICK_CLASS = "com.tourgeek.tour.product.base.search.db.ProductSearchDetail";

}
