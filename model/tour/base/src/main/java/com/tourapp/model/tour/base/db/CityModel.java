/**
 * @(#)CityModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.base.db;

import com.tourapp.model.tour.base.db.*;

public interface CityModel extends LocationModel
{

    //public static final String ID = ID;
    public static final String DESCRIPTION = NAME;
    public static final String CITY_CODE = CODE;
    public static final String TICKET_CITY_DESC = "TicketCityDesc";
    public static final String ICAO_CODE = "ICAOCode";
    public static final String MAIN_CITY_ID = "MainCityID";
    public static final String STATE_ID = "StateID";
    public static final String COUNTRY_ID = "CountryID";
    public static final String DOMESTIC_TAX = "DomesticTax";
    public static final String INTERNATIONAL_TAX = "InternationalTax";
    public static final String ARRIVAL_TAX = "ArrivalTax";
    public static final String FACILITIES_TAX = "FacilitiesTax";
    public static final String GMT_OFFSET = "GMTOffset";
    public static final String LATITUDE = "Latitude";
    public static final String LONGITUDE = "Longitude";
    public static final String ALTITUDE = "Altitude";
    public static final String CITY_TYPE = "CityType";
    public static final String MAP = "Map";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String CITY_CODE_KEY = "CityCode";

    public static final String COUNTRY_ID_KEY = "CountryID";

    public static final String TICKET_CITY_DESC_KEY = "TicketCityDesc";
    public static final String CITY_SCREEN_CLASS = "com.tourapp.tour.base.screen.CityScreen";
    public static final String CITY_GRID_SCREEN_CLASS = "com.tourapp.tour.base.screen.CityGridScreen";

    public static final String CITY_FILE = "City";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.base.db.City";
    public static final String THICK_CLASS = "com.tourapp.tour.base.db.City";

}
