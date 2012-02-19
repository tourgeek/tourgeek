/**
 * @(#)TourSubModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.tour.detail.db;

import org.jbundle.model.db.*;

public interface TourSubModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String TOUR_HEADER_OPTION_ID = "TourHeaderOptionID";
    public static final String MODIFY_CODE = "ModifyCode";
    public static final String MODIFY_ID = "ModifyID";

    public static final String TOUR_SUB_FILE = "TourSub";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.tour.detail.db.TourSub";
    public static final String THICK_CLASS = "com.tourapp.tour.product.tour.detail.db.TourSub";

}
