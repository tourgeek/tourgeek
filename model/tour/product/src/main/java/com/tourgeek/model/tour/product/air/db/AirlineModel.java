/**
  * @(#)AirlineModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.product.air.db;

import org.jbundle.model.db.*;

public interface AirlineModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String SHORT_DESC = "ShortDesc";
    public static final String AIRLINE_CODE = "AirlineCode";
    public static final String ICAO_CODE = "ICAOCode";
    public static final String COUNTRY_ID = "CountryID";
    public static final String AIRLINE_NO = "AirlineNo";
    public static final String VENDOR_ID = "VendorID";
    public static final String CONTACT = "Contact";
    public static final String CONTACT_TITLE = "ContactTitle";
    public static final String ACCEPT_MC_OS = "AcceptMCOs";
    public static final String MCO_SVC_CHG = "McoSvcChg";
    public static final String MCO_REC_ACCOUNT_ID = "McoRecAccountID";
    public static final String MCO_VAR_ACCOUNT_ID = "McoVarAccountID";
    public static final String OVERRIDE_ACCOUNT_ID = "OverrideAccountID";
    public static final String OR_VAR_ACCOUNT_ID = "ORVarAccountID";
    public static final String LOGO = "Logo";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String AIRLINE_CODE_KEY = "AirlineCode";

    public static final String AIRLINE_NO_KEY = "AirlineNo";

    public static final String VENDOR_ID_KEY = "VendorID";
    public static final String AIRLINE_SCREEN_CLASS = "com.tourgeek.tour.product.air.screen.AirlineScreen";
    public static final String AIRLINE_GRID_SCREEN_CLASS = "com.tourgeek.tour.product.air.screen.AirlineGridScreen";

    public static final String AIRLINE_FILE = "Airline";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.product.air.db.Airline";
    public static final String THICK_CLASS = "com.tourgeek.tour.product.air.db.Airline";

}
