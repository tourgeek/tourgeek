/**
 * @(#)CurrencysModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.base.db;

import org.jbundle.model.db.*;

public interface CurrencysModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String CURRENCY_CODE = "CurrencyCode";
    public static final String LAST_RATE = "LastRate";
    public static final String RATE_CHANGED_DATE = "RateChangedDate";
    public static final String RATE_CHANGED_BY = "RateChangedBy";
    public static final String COSTING_RATE = "CostingRate";
    public static final String COSTING_CHANGED_DATE = "CostingChangedDate";
    public static final String COSTING_CHANGED_BY = "CostingChangedBy";
    public static final String ROUND_AT = "RoundAt";
    public static final String INTEGER_DESC = "IntegerDesc";
    public static final String FRACTION_DESC = "FractionDesc";
    public static final String FRACTION_AMOUNT = "FractionAmount";
    public static final String SIGN = "Sign";
    public static final String LANGUAGE_ID = "LanguageID";
    public static final String NATURAL_INTEGER = "NaturalInteger";
    public static final String NATURAL_FRACTION = "NaturalFraction";

    public static final String CURRENCY_CODE_KEY = "CurrencyCode";

    public static final String DESCRIPTION_KEY = "Description";
    public static final String CURRENCYS_SCREEN_CLASS = "com.tourapp.tour.base.screen.CurrencysScreen";
    public static final String CURRENCYS_GRID_SCREEN_CLASS = "com.tourapp.tour.base.screen.CurrencysGridScreen";

    public static final String CURRENCYS_FILE = "Currencys";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.base.db.Currencys";
    public static final String THICK_CLASS = "com.tourapp.tour.base.db.Currencys";

}
