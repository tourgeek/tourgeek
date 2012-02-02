/**
 * @(#)ProductTermsModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.base.db;

import org.jbundle.model.db.*;

public interface ProductTermsModel extends Rec
{

    //public static final String ID = ID;
    public static final String DESCRIPTION = "Description";
    public static final String TAX_RATE = "TaxRate";
    public static final String SERVICE_CHARGE_RATE = "ServiceChargeRate";
    public static final String COMMISSION_RATE = "CommissionRate";

    public static final String DESCRIPTION_KEY = "Description";
    public static final String PRODUCT_TERMS_SCREEN_CLASS = "com.tourapp.tour.product.base.screen.ProductTermsScreen";
    public static final String PRODUCT_TERMS_GRID_SCREEN_CLASS = "com.tourapp.tour.product.base.screen.ProductTermsGridScreen";

    public static final String PRODUCT_TERMS_FILE = "ProductTerms";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.db.ProductTerms";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.db.ProductTerms";

}
