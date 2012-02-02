/**
 * @(#)ItemModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.item.db;

import com.tourapp.model.tour.product.base.db.*;

public interface ItemModel extends ProductModel
{

    //public static final String ID = ID;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String CODE = CODE;
    //public static final String PRODUCT_CHAIN_ID = PRODUCT_CHAIN_ID;

    public static final String DESCRIPTION_KEY = "Description";
    public static final String ITEM_SCREEN_CLASS = "com.tourapp.tour.product.item.screen.ItemScreen";
    public static final String ITEM_GRID_SCREEN_CLASS = "com.tourapp.tour.product.item.screen.ItemGridScreen";

    public static final String ITEM_FILE = "Item";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.item.db.Item";
    public static final String THICK_CLASS = "com.tourapp.tour.product.item.db.Item";

}
