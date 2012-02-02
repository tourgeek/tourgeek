/**
 * @(#)CompanyInfoModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.main.db.*;

public interface CompanyInfoModel extends CompanyModel
{

    //public static final String ID = ID;
    //public static final String CODE = CODE;
    public static final String COMPANY_NAME = NAME;
    public static final String LOGO = "Logo";
    public static final String COMPANY_INFO_SCREEN_CLASS = "com.tourapp.tour.genled.screen.misc.CompanyInfoScreen";
    public static final String COMPANY_INFO_SCREEN_2_CLASS = "com.tourapp.tour.genled.screen.misc.CompanyInfoScreen";

    public static final String COMPANY_INFO_FILE = "CompanyInfo";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.CompanyInfo";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.CompanyInfo";

}
