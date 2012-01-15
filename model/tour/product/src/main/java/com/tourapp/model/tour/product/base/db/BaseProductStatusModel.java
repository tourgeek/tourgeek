/**
 * @(#)BaseProductStatusModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.base.db;

import org.jbundle.model.main.msg.db.base.*;

public interface BaseProductStatusModel extends BaseStatusModel
{
    public static final String NO_STATUS_CODE = "NS";
    public static final String PROPOSAL_CODE = "PR";
    public static final String ACCEPTED_CODE = "RQ";
    public static final String OKAY_CODE = "OK";
    public static final String CANCELLED_CODE = "XL";

    public static final String BASE_PRODUCT_STATUS_FILE = "BaseProductStatus";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.db.BaseProductStatus";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.db.BaseProductStatus";

}
