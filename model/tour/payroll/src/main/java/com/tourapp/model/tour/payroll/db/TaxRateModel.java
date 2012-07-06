/**
  * @(#)TaxRateModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.payroll.db;

import org.jbundle.model.db.*;

public interface TaxRateModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String TAX_CODE = "TaxCode";
    public static final String MARITAL_STATUS = "MaritalStatus";
    public static final String CUT_OFF_AMOUNT = "CutOffAmount";
    public static final String TAX_RATE = "TaxRate";

    public static final String TAX_CODE_KEY = "TaxCode";
    public static final String TAX_RATE_SCREEN_CLASS = "com.tourapp.tour.payroll.screen.tax.TaxRateScreen";
    public static final String TAX_RATE_GRID_SCREEN_CLASS = "com.tourapp.tour.payroll.screen.tax.TaxRateGridScreen";

    public static final String TAX_RATE_FILE = "TaxRate";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.payroll.db.TaxRate";
    public static final String THICK_CLASS = "com.tourapp.tour.payroll.db.TaxRate";

}
