/**
 * @(#)TransportProductModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.base.db;

import com.tourapp.model.tour.product.base.db.*;

public interface TransportProductModel extends ProductModel
{

    //public static final String ID = ID;
    //public static final String CITY_ID = CITY_ID;
    public static final String CITY_CODE = "CityCode";
    public static final String TO_CITY_ID = "ToCityID";
    public static final String TO_CITY_CODE = "ToCityCode";

    public static final String TRANSPORT_PRODUCT_FILE = "TransportProduct";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.db.TransportProduct";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.db.TransportProduct";

}
