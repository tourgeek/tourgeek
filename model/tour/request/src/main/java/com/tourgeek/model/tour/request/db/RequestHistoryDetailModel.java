/**
  * @(#)RequestHistoryDetailModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.request.db;

import org.jbundle.model.db.*;

public interface RequestHistoryDetailModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String REQUEST_HISTORY_ID = "RequestHistoryID";
    public static final String PROFILE_ID = "ProfileID";
    public static final String BROCHURE_ID = "BrochureID";
    public static final String BROCHURE_QTY = "BrochureQty";
    public static final String BROCHURE_DESC = "BrochureDesc";
    public static final String MAILED_ON = "MailedOn";

    public static final String REQUEST_HISTORY_ID_KEY = "RequestHistoryID";

    public static final String PROFILE_ID_KEY = "ProfileID";
    public static final String REQUEST_HISTORY_DETAIL_GRID_SCREEN_CLASS = "com.tourgeek.tour.request.screen.detail.RequestHistoryDetailGridScreen";

    public static final String REQUEST_HISTORY_DETAIL_FILE = "RequestHistoryDetail";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.request.db.RequestHistoryDetail";
    public static final String THICK_CLASS = "com.tourgeek.tour.request.db.RequestHistoryDetail";

}
