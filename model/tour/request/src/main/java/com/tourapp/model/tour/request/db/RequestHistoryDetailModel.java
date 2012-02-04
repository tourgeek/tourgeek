/**
 * @(#)RequestHistoryDetailModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.request.db;

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

    public static final String REQUEST_HISTORY_DETAIL_FILE = "RequestHistoryDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.request.db.RequestHistoryDetail";
    public static final String THICK_CLASS = "com.tourapp.tour.request.db.RequestHistoryDetail";

}
