/**
 * @(#)CurrencysModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.base.db;

import org.jbundle.model.db.*;

public interface CurrencysModel extends Rec
{
    public static final String DESCRIPTION = "Description";
    public static final String CURRENCY_CODE = "CurrencyCode";
    public static final String CURRENCYS_SCREEN_CLASS = "com.tourapp.tour.base.screen.CurrencysScreen";
    public static final String CURRENCYS_GRID_SCREEN_CLASS = "com.tourapp.tour.base.screen.CurrencysGridScreen";

    public static final String CURRENCYS_FILE = "Currencys";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.base.db.Currencys";
    public static final String THICK_CLASS = "com.tourapp.tour.base.db.Currencys";

}
