/**
 * @(#)AssetDepreciationModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.assetdr.db;

import org.jbundle.model.db.*;

public interface AssetDepreciationModel extends Rec
{

    //public static final String ID = ID;
    public static final String ASSET_ID = "AssetID";
    public static final String DEPR_DATE = "DeprDate";
    public static final String DEPR_POST = "DeprPost";
    public static final String VERSION_ID = "VersionID";
    public static final String DEPR_AMOUNT = "DeprAmount";
    public static final String DEPR_DR_ID = "DeprDrID";
    public static final String DEPR_CR_ID = "DeprCrID";

    public static final String ASSET_ID_KEY = "AssetID";
    public static final String ASSET_DEPRECIATION_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.asset.AssetDepreciationScreen";
    public static final String ASSET_DEPRECIATION_GRID_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.asset.AssetDepreciationGridScreen";

    public static final String ASSET_DEPRECIATION_FILE = "AssetDepreciation";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.assetdr.db.AssetDepreciation";
    public static final String THICK_CLASS = "com.tourapp.tour.assetdr.db.AssetDepreciation";

}
