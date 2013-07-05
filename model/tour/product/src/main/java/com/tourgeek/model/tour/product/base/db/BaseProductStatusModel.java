/**
  * @(#)BaseProductStatusModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.product.base.db;

import org.jbundle.model.main.db.base.*;

public interface BaseProductStatusModel extends BaseStatusModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String ICON = ICON;
    public static final String CODE = "Code";
    public static final String NO_STATUS_CODE = "NS";
    public static final String PROPOSAL_CODE = "PR";
    public static final String ACCEPTED_CODE = "RQ";
    public static final String OKAY_CODE = "OK";
    public static final String CANCELLED_CODE = "XL";

    public static final String BASE_PRODUCT_STATUS_FILE = "BaseProductStatus";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.product.base.db.BaseProductStatus";
    public static final String THICK_CLASS = "com.tourgeek.tour.product.base.db.BaseProductStatus";

}
