/**
  * @(#)RequestInputModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.request.db;

import org.jbundle.model.db.*;

public interface RequestInputModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String BROCHURE_QTY = "BrochureQty";
    public static final String BROCHURE_ID = "BrochureID";
    public static final String BROCHURE_DESC = "BrochureDesc";

    public static final String REQUEST_INPUT_FILE = "RequestInput";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.request.db.RequestInput";
    public static final String THICK_CLASS = "com.tourgeek.tour.request.db.RequestInput";

}
