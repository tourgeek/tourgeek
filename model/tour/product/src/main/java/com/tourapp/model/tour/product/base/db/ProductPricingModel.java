/**
 * @(#)ProductPricingModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.base.db;

import org.jbundle.model.db.*;

public interface ProductPricingModel extends Rec
{

    //public static final String ID = ID;
    public static final String PRODUCT_ID = "ProductID";
    public static final String PAX_CATEGORY_ID = "PaxCategoryID";
    public static final String RATE_ID = "RateID";
    public static final String CLASS_ID = "ClassID";
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String COST = "Cost";
    public static final String PRODUCT_TERMS_ID = "ProductTermsID";
    public static final String PRICE = "Price";
    public static final String COMMISSIONABLE = "Commissionable";
    public static final String COMMISSION_RATE = "CommissionRate";
    public static final String PAY_AT = "PayAt";

    public static final String PRODUCT_ID_KEY = "ProductID";

    public static final String PRODUCT_PRICING_FILE = "ProductPricing";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.db.ProductPricing";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.db.ProductPricing";

}
