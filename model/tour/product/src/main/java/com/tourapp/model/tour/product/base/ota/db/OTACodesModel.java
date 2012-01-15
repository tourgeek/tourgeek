/**
 * @(#)OTACodesModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.base.ota.db;

import org.jbundle.model.db.*;

public interface OTACodesModel extends Rec
{
    public static final String OTA_CODES_SCREEN_CLASS = "com.tourapp.tour.product.base.ota.screen.OTACodesScreen";
    public static final String OTA_CODES_GRID_SCREEN_CLASS = "com.tourapp.tour.product.base.ota.screen.OTACodesGridScreen";

    public static final String OTA_CODES_FILE = "OTACodes";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.ota.db.OTACodes";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.ota.db.OTACodes";

}
