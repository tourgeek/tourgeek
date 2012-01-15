/**
 * @(#)AssetModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.assetdr.db;

import org.jbundle.model.db.*;

public interface AssetModel extends Rec
{
    public static final String ASSET_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.asset.AssetScreen";
    public static final String ASSET_GRID_SCREEN_CLASS = "com.tourapp.tour.assetdr.screen.asset.AssetGridScreen";

    public static final String ASSET_FILE = "Asset";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.assetdr.db.Asset";
    public static final String THICK_CLASS = "com.tourapp.tour.assetdr.db.Asset";

}
