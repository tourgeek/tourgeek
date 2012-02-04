/**
 * @(#)CountryModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.base.db;

import com.tourapp.model.tour.base.db.*;

public interface CountryModel extends LocationModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String NAME = NAME;
    //public static final String CODE = CODE;
    public static final String CURRENCYS_ID = "CurrencysID";
    public static final String LANGUAGE_ID = "LanguageID";
    public static final String ICAO_COUNTRY_CODE = "ICAOCountryCode";
    public static final String FAX_PREFIX = "FaxPrefix";
    public static final String INTERNATIONAL_TAX = "InternationalTax";
    public static final String DOMESTIC_TAX = "DomesticTax";
    public static final String ARRIVAL_TAX = "ArrivalTax";
    public static final String GMT_OFFSET = "GMTOffset";
    public static final String REGION_ID = "RegionID";
    public static final String DESCRIPTION = "Description";
    public static final String PICTURE = "Picture";

    public static final String REGION_ID_KEY = "RegionID";
    public static final String COUNTRY_SCREEN_CLASS = "com.tourapp.tour.base.screen.CountryScreen";
    public static final String COUNTRY_GRID_SCREEN_CLASS = "com.tourapp.tour.base.screen.CountryGridScreen";

    public static final String COUNTRY_FILE = "Country";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.base.db.Country";
    public static final String THICK_CLASS = "com.tourapp.tour.base.db.Country";

}
