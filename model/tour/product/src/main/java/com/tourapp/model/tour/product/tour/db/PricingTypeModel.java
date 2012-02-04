/**
 * @(#)PricingTypeModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.tour.db;

import org.jbundle.model.db.*;

public interface PricingTypeModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String PRICING_CODES = "PricingCodes";

    public static final String DESCRIPTION_KEY = "Description";
    public static final int OPTION_PRICING = 1;
    public static final int COMPONENT_PRICING = 2;
    public static final int COMPONENT_COST_PRICING = 4;

    public static final String PRICING_TYPE_FILE = "PricingType";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.tour.db.PricingType";
    public static final String THICK_CLASS = "com.tourapp.tour.product.tour.db.PricingType";

}
