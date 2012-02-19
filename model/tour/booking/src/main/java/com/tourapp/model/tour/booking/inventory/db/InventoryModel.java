/**
 * @(#)InventoryModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.inventory.db;

import org.jbundle.model.db.*;

public interface InventoryModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String PRODUCT_TYPE_ID = "ProductTypeID";
    public static final String PRODUCT_ID = "ProductID";
    public static final String RATE_ID = "RateID";
    public static final String CLASS_ID = "ClassID";
    public static final String OTHER_ID = "OtherID";
    public static final String INV_DATE = "InvDate";
    public static final String BLOCKED = "Blocked";
    public static final String USED = "Used";
    public static final String AVAILABLE = "Available";
    public static final String OVERSELL = "Oversell";
    public static final String CLOSED = "Closed";

    public static final String INV_DATE_KEY = "InvDate";
    public static final int NO_INVENTORY = -1;
    public static final String NO_OTHER = "0";
    public static final String NO_CLASS = "0";
    public static final String NO_RATE = "0";

    public static final String INVENTORY_FILE = "Inventory";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.inventory.db.Inventory";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.inventory.db.Inventory";

}
