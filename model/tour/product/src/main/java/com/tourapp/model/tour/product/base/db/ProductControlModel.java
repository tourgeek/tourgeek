/**
 * @(#)ProductControlModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.base.db;

import org.jbundle.model.db.*;

public interface ProductControlModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String AIR_RATE_ID = "AirRateID";
    public static final String AIR_CLASS_ID = "AirClassID";
    public static final String HOTEL_RATE_ID = "HotelRateID";
    public static final String HOTEL_CLASS_ID = "HotelClassID";
    public static final String LAND_RATE_ID = "LandRateID";
    public static final String LAND_CLASS_ID = "LandClassID";
    public static final String VARIES_ON = "VariesOn";
    public static final String PMC_CUTOFF = "PMCCutoff";
    public static final String TRANSPORTATION_RATE_ID = "TransportationRateID";
    public static final String TRANSPORTATION_CLASS_ID = "TransportationClassID";
    public static final String CAR_RATE_ID = "CarRateID";
    public static final String CAR_CLASS_ID = "CarClassID";
    public static final String CRUISE_RATE_ID = "CruiseRateID";
    public static final String CRUISE_CLASS_ID = "CruiseClassID";
    public static final String ITEM_RATE_ID = "ItemRateID";
    public static final String ITEM_CLASS_ID = "ItemClassID";
    public static final String PRODUCT_TERMS_ID = "ProductTermsID";

    public static final String PRODUCT_CONTROL_FILE = "ProductControl";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.db.ProductControl";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.db.ProductControl";

}
