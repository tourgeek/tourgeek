/**
  * @(#)ProductTermsModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.product.base.db;

import org.jbundle.model.db.*;

public interface ProductTermsModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String TAX_RATE = "TaxRate";
    public static final String SERVICE_CHARGE_RATE = "ServiceChargeRate";
    public static final String COMMISSION_RATE = "CommissionRate";

    public static final String DESCRIPTION_KEY = "Description";
    public static final String PRODUCT_TERMS_SCREEN_CLASS = "com.tourgeek.tour.product.base.screen.ProductTermsScreen";
    public static final String PRODUCT_TERMS_GRID_SCREEN_CLASS = "com.tourgeek.tour.product.base.screen.ProductTermsGridScreen";

    public static final String PRODUCT_TERMS_FILE = "ProductTerms";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.product.base.db.ProductTerms";
    public static final String THICK_CLASS = "com.tourgeek.tour.product.base.db.ProductTerms";

}
