/**
 * @(#)PricingStatusModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.base.db;

import org.jbundle.model.main.db.base.*;

public interface PricingStatusModel extends BaseStatusModel
{
    public static final String DESCRIPTION = "Description";
    public static final String ICON = "Icon";
    public static final int MANUAL = ACCEPTED;

    public static final String PRICING_STATUS_FILE = "PricingStatus";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.db.PricingStatus";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.db.PricingStatus";

}
