/**
 * @(#)ProductSearchDetailModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.base.search.db;

import org.jbundle.model.db.*;

public interface ProductSearchDetailModel extends Rec
{
    public static final String PRODUCT_SEARCH_DETAIL_SCREEN_CLASS = "com.tourapp.tour.product.base.search.screen.ProductSearchDetailScreen";
    public static final String PRODUCT_SEARCH_DETAIL_GRID_SCREEN_CLASS = "com.tourapp.tour.product.base.search.screen.ProductSearchDetailGridScreen";

    public static final String PRODUCT_SEARCH_DETAIL_FILE = "ProductSearchDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.search.db.ProductSearchDetail";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.search.db.ProductSearchDetail";

}
