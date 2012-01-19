/**
 * @(#)TaxRateModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.payroll.db;

import org.jbundle.model.db.*;

public interface TaxRateModel extends Rec
{
    public static final String TAX_RATE_SCREEN_CLASS = "com.tourapp.tour.payroll.screen.tax.TaxRateScreen";
    public static final String TAX_RATE_GRID_SCREEN_CLASS = "com.tourapp.tour.payroll.screen.tax.TaxRateGridScreen";

    public static final String TAX_RATE_FILE = "TaxRate";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.payroll.db.TaxRate";
    public static final String THICK_CLASS = "com.tourapp.tour.payroll.db.TaxRate";

}