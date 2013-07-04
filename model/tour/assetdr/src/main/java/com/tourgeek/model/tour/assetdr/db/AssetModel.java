/**
  * @(#)AssetModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.assetdr.db;

import org.jbundle.model.db.*;

public interface AssetModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String MANUFACTURER = "Manufacturer";
    public static final String SERIAL_NUMBER = "SerialNumber";
    public static final String INVENTORY_NO = "InventoryNo";
    public static final String LOCATION = "Location";
    public static final String PURCHASE_DATE = "PurchaseDate";
    public static final String SALE_DATE = "SaleDate";
    public static final String FS_DEPR_METHOD = "FSDeprMethod";
    public static final String FS_LIFE = "FSLife";
    public static final String FED_DEPR_METHOD = "FedDeprMethod";
    public static final String FED_LIFE = "FedLife";
    public static final String STATE_DEPR_METHOD = "StateDeprMethod";
    public static final String STATE_LIFE = "StateLife";
    public static final String ASSET_ACCOUNT_ID = "AssetAccountID";
    public static final String DEPR_ACCOUNT_ID = "DeprAccountID";
    public static final String EXPENSE_ACCOUNT_ID = "ExpenseAccountID";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String INVENTORY_NO_KEY = "InventoryNo";
    public static final String ASSET_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.asset.AssetScreen";
    public static final String ASSET_GRID_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.asset.AssetGridScreen";

    public static final String ASSET_FILE = "Asset";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.assetdr.db.Asset";
    public static final String THICK_CLASS = "com.tourapp.tour.assetdr.db.Asset";

}
