/**
 * @(#)RequestModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.request.db;

import org.jbundle.model.db.*;

public interface RequestModel extends Rec
{

    //public static final String ID = ID;
    public static final String PROFILE_ID = "ProfileID";
    public static final String PROFILE_CODE = "ProfileCode";
    public static final String GENERIC_NAME = "GenericName";
    public static final String ADDRESS_LINE_1 = "AddressLine1";
    public static final String ADDRESS_LINE_2 = "AddressLine2";
    public static final String CITY_OR_TOWN = "CityOrTown";
    public static final String STATE_OR_REGION = "StateOrRegion";
    public static final String POSTAL_CODE = "PostalCode";
    public static final String COUNTRY = "Country";
    public static final String ATTENTION = "Attention";
    public static final String EMAIL = "Email";
    public static final String SEND_VIA_CODE = "SendViaCode";
    public static final String BUNDLE_ID = "BundleID";
    public static final String BUNDLE_QTY = "BundleQty";
    public static final String BROCHURE_TEXT = "BrochureText";
    public static final String PRINT_NOW = "PrintNow";
    public static final String HIST_REPRINT = "HistReprint";

    public static final String POSTAL_CODE_KEY = "PostalCode";

    public static final String PROFILE_CODE_KEY = "ProfileCode";
    public static final String REQUEST_SCREEN_CLASS = "com.tourapp.tour.request.screen.RequestScreen";
    public static final String REQUEST_GRID_SCREEN_CLASS = "com.tourapp.tour.request.screen.RequestGridScreen";

    public static final String REQUEST_FILE = "Request";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.request.db.Request";
    public static final String THICK_CLASS = "com.tourapp.tour.request.db.Request";

}
