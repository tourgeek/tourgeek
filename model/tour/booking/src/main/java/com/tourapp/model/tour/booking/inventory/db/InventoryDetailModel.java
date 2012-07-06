/**
  * @(#)InventoryDetailModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.booking.inventory.db;

import org.jbundle.model.db.*;

public interface InventoryDetailModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String INVENTORY_ID = "InventoryID";
    public static final String BOOKING_DETAIL_ID = "BookingDetailID";
    public static final String TYPE = "Type";
    public static final String AMOUNT = "Amount";

    public static final String INVENTORY_ID_KEY = "InventoryID";

    public static final String BOOKING_DETAIL_ID_KEY = "BookingDetailID";
    public static final String INVENTORY_DETAIL_SCREEN_CLASS = "com.tourapp.tour.product.base.screen.InventoryDetailScreen";
    public static final String INVENTORY_DETAIL_GRID_SCREEN_CLASS = "com.tourapp.tour.product.base.screen.InventoryDetailGridScreen";

    public static final String INVENTORY_DETAIL_FILE = "InventoryDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.inventory.db.InventoryDetail";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.inventory.db.InventoryDetail";

}
