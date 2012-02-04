/**
 * @(#)CompanyInfoModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.main.db.*;

public interface CompanyInfoModel extends CompanyModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String CODE = CODE;
    public static final String COMPANY_NAME = NAME;
    //public static final String ADDRESS_LINE_1 = ADDRESS_LINE_1;
    //public static final String ADDRESS_LINE_2 = ADDRESS_LINE_2;
    //public static final String CITY_OR_TOWN = CITY_OR_TOWN;
    //public static final String STATE_OR_REGION = STATE_OR_REGION;
    //public static final String POSTAL_CODE = POSTAL_CODE;
    //public static final String COUNTRY = COUNTRY;
    //public static final String TEL = TEL;
    //public static final String FAX = FAX;
    //public static final String EMAIL = EMAIL;
    //public static final String WEB = WEB;
    //public static final String DATE_ENTERED = DATE_ENTERED;
    //public static final String DATE_CHANGED = DATE_CHANGED;
    //public static final String CHANGED_ID = CHANGED_ID;
    //public static final String COMMENTS = COMMENTS;
    //public static final String USER_ID = USER_ID;
    //public static final String PASSWORD = PASSWORD;
    //public static final String NAME_SORT = NAME_SORT;
    //public static final String POSTAL_CODE_SORT = POSTAL_CODE_SORT;
    //public static final String CONTACT = CONTACT;
    public static final String LOGO = "Logo";
    public static final String COMPANY_INFO_SCREEN_CLASS = "com.tourapp.tour.genled.screen.misc.CompanyInfoScreen";
    public static final String COMPANY_INFO_SCREEN_2_CLASS = "com.tourapp.tour.genled.screen.misc.CompanyInfoScreen";

    public static final String COMPANY_INFO_FILE = "CompanyInfo";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.CompanyInfo";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.CompanyInfo";

}
