/**
  * @(#)BaseDataStatusModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.product.base.db;

import org.jbundle.model.main.db.base.*;

public interface BaseDataStatusModel extends BaseStatusModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String ICON = ICON;
    public static final String DATA_STATUS = "dataStatus"; // MessageDataDesc.DATA_STATUS;
    public static final String DATA_ERROR_MESSAGE = "dataErrorMessage";

    public static final String BASE_DATA_STATUS_FILE = "BaseDataStatus";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.db.BaseDataStatus";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.db.BaseDataStatus";

}
