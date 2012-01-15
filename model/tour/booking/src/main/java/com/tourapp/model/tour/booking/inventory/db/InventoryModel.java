/**
 * @(#)InventoryModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.inventory.db;

import org.jbundle.model.db.*;

public interface InventoryModel extends Rec
{
    public static final int NO_INVENTORY = -1;
    public static final String NO_OTHER = "0";
    public static final String NO_CLASS = "0";
    public static final String NO_RATE = "0";

    public static final String INVENTORY_FILE = "Inventory";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.inventory.db.Inventory";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.inventory.db.Inventory";

}
