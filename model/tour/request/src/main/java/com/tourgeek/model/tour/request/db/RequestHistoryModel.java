
package com.tourgeek.model.tour.request.db;

import com.tourgeek.model.tour.request.db.*;

public interface RequestHistoryModel extends RequestModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String PROFILE_ID = PROFILE_ID;
    //public static final String PROFILE_CODE = PROFILE_CODE;
    //public static final String GENERIC_NAME = GENERIC_NAME;
    //public static final String ADDRESS_LINE_1 = ADDRESS_LINE_1;
    //public static final String ADDRESS_LINE_2 = ADDRESS_LINE_2;
    //public static final String CITY_OR_TOWN = CITY_OR_TOWN;
    //public static final String STATE_OR_REGION = STATE_OR_REGION;
    //public static final String POSTAL_CODE = POSTAL_CODE;
    //public static final String COUNTRY = COUNTRY;
    //public static final String ATTENTION = ATTENTION;
    //public static final String EMAIL = EMAIL;
    //public static final String SEND_VIA_CODE = SEND_VIA_CODE;
    //public static final String BUNDLE_ID = BUNDLE_ID;
    //public static final String BUNDLE_QTY = BUNDLE_QTY;
    //public static final String BROCHURE_TEXT = BROCHURE_TEXT;
    //public static final String PRINT_NOW = PRINT_NOW;
    //public static final String HIST_REPRINT = HIST_REPRINT;
    public static final String HIST_TIME_PRINTED = "HistTimePrinted";

    public static final String PROFILE_ID_KEY = "ProfileID";

    public static final String HIST_REPRINT_KEY = "HistReprint";
    public static final String REQUEST_HISTORY_SCREEN_CLASS = "com.tourgeek.tour.request.screen.detail.RequestHistoryScreen";
    public static final String REQUEST_HISTORY_GRID_SCREEN_CLASS = "com.tourgeek.tour.request.screen.detail.RequestHistoryGridScreen";

    public static final String REQUEST_HISTORY_FILE = "RequestHistory";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.request.db.RequestHistory";
    public static final String THICK_CLASS = "com.tourgeek.tour.request.db.RequestHistory";

}
