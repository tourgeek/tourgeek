/**
  * @(#)AssetDepreciationModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.assetdr.db;

import org.jbundle.model.db.*;

public interface AssetDepreciationModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String ASSET_ID = "AssetID";
    public static final String DEPR_DATE = "DeprDate";
    public static final String DEPR_POST = "DeprPost";
    public static final String VERSION_ID = "VersionID";
    public static final String DEPR_AMOUNT = "DeprAmount";
    public static final String DEPR_DR_ID = "DeprDrID";
    public static final String DEPR_CR_ID = "DeprCrID";

    public static final String ASSET_ID_KEY = "AssetID";
    public static final String ASSET_DEPRECIATION_SCREEN_CLASS = "com.tourgeek.tour.assetdr.screen.asset.AssetDepreciationScreen";
    public static final String ASSET_DEPRECIATION_GRID_SCREEN_CLASS = "com.tourgeek.tour.assetdr.screen.asset.AssetDepreciationGridScreen";

    public static final String ASSET_DEPRECIATION_FILE = "AssetDepreciation";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.assetdr.db.AssetDepreciation";
    public static final String THICK_CLASS = "com.tourgeek.tour.assetdr.db.AssetDepreciation";

}
