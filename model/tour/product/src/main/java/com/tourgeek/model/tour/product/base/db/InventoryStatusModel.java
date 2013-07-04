
package com.tourgeek.model.tour.product.base.db;

import com.tourgeek.model.tour.product.base.db.*;

public interface InventoryStatusModel extends BaseDataStatusModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String ICON = ICON;
    public static final String LOOKUP_ERROR_MESSAGE = "Inventory lookup error";
    public static final String NO_INVENTORY_ERROR_MESSAGE = "Not sufficient inventory";

    public static final String INVENTORY_STATUS_FILE = "InventoryStatus";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.product.base.db.InventoryStatus";
    public static final String THICK_CLASS = "com.tourgeek.tour.product.base.db.InventoryStatus";

}
