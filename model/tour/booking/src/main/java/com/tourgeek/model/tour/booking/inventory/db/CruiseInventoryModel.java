/**
  * @(#)CruiseInventoryModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.booking.inventory.db;

import com.tourgeek.model.tour.booking.inventory.db.*;

public interface CruiseInventoryModel extends InventoryModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String PRODUCT_TYPE_ID = PRODUCT_TYPE_ID;
    //public static final String PRODUCT_ID = PRODUCT_ID;
    //public static final String RATE_ID = RATE_ID;
    //public static final String CLASS_ID = CLASS_ID;
    //public static final String OTHER_ID = OTHER_ID;
    //public static final String INV_DATE = INV_DATE;
    //public static final String BLOCKED = BLOCKED;
    //public static final String USED = USED;
    //public static final String AVAILABLE = AVAILABLE;
    //public static final String OVERSELL = OVERSELL;
    //public static final String CLOSED = CLOSED;
    public static final String CRUISE_INVENTORY_SCREEN_CLASS = "com.tourgeek.tour.product.cruise.screen.CruiseInventoryScreen";
    public static final String CRUISE_INVENTORY_GRID_SCREEN_CLASS = "com.tourgeek.tour.product.cruise.screen.CruiseInventoryGridScreen";
    public static final String CRUISE_INVENTORY_RANGE_ADJUST_CLASS = "com.tourgeek.tour.product.cruise.screen.CruiseInventoryRangeAdjust";

    public static final String CRUISE_INVENTORY_FILE = "Inventory";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.booking.inventory.db.CruiseInventory";
    public static final String THICK_CLASS = "com.tourgeek.tour.booking.inventory.db.CruiseInventory";

}
