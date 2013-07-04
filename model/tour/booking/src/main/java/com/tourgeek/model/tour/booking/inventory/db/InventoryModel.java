
package com.tourgeek.model.tour.booking.inventory.db;

import java.util.*;
import com.tourgeek.model.tour.product.base.db.*;
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
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.booking.inventory.db.Inventory";
    public static final String THICK_CLASS = "com.tourgeek.tour.booking.inventory.db.Inventory";
    /**
     * GetAvailability Method.
     */
    public InventoryModel getAvailability(ProductModel recProduct, Date dateTarget, int iRateID, int iClassID, int iOtherID);
    /**
     * Get all the transaction IDs for this BookingDetail trx ID.
     */
    public Set<Integer> surveyInventory(Field fldTrxID);
    /**
     * For the current record, update the availability using this amount
     * @param iTargetAmount Amount to reduce the inventory by.
     * @param fldTrxID The (BookingDetail) trx to tie this inventory to
     * @param iType Option type (ie., room type)
     * @param bDelete If true, delete this inventory
     * @param mapSurvey If a changed transaction is contained in this map, remove it
     * @return Error code.
     */
    public int updateAvailability(int iTargetAmount, Field fldTrxID, int iType, boolean bDelete, Set<Integer> setSurvey);
    /**
     * Remove the transactions contained in this map.
     */
    public int removeTrxs(Field fldTrxID, Set<Integer> setSurvey);
    /**
     * Remove the inventory for this BookingDetail trxID.
     * (Deletes the links, and adds the inventory back).
     */
    public int removeInventory(Field fldTrxID);

}
