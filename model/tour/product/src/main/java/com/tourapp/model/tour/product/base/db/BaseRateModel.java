/**
 * @(#)BaseRateModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.base.db;

import org.jbundle.model.db.*;

public interface BaseRateModel extends Rec
{

    //public static final String ID = ID;
    public static final String DESCRIPTION = "Description";
    public static final String CODE = "Code";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String CODE_KEY = "Code";

    public static final String BASE_RATE_FILE = "BaseRate";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.db.BaseRate";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.db.BaseRate";

}
