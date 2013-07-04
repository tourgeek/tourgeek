/**
  * @(#)ProfileModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.profile.db;

import org.jbundle.model.main.db.*;

public interface ProfileModel extends CompanyModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String PROFILE_CODE = CODE;
    public static final String GENERIC_NAME = NAME;
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
    public static final String NAME_ORDERED = CONTACT;
    public static final String PROFILE_TYPE_ID = "ProfileTypeID";
    public static final String ENTERED_ID = "EnteredID";
    public static final String AFFILIATION_ID = "AffiliationID";
    public static final String COMMISSION_PLAN_CODE = "CommissionPlanCode";
    public static final String ALT_PHONE = "AltPhone";
    public static final String NAME_PREFIX = "NamePrefix";
    public static final String NAME_FIRST = "NameFirst";
    public static final String NAME_MIDDLE = "NameMiddle";
    public static final String NAME_SUR = "NameSur";
    public static final String NAME_SUFFIX = "NameSuffix";
    public static final String NAME_TITLE = "NameTitle";
    public static final String DATE_OF_BIRTH = "DateOfBirth";
    public static final String GENDER = "Gender";
    public static final String EXPIRATION_DATE = "ExpirationDate";
    public static final String CORPORATE_POSITION = "CorporatePosition";
    public static final String PIN = "PIN";
    public static final String CREDIT_LIMIT = "CreditLimit";
    public static final String COUNTRY_ID = "CountryID";
    public static final String PRIMARY_LANGUAGE_ID = "PrimaryLanguageID";
    public static final String CURRENCYS_ID = "CurrencysID";
    public static final String MESSAGE_TRANSPORT_ID = "MessageTransportID";
    public static final String PROPERTIES = "Properties";
    public static final String PROFILE_STATUS_ID = "ProfileStatusID";
    public static final String PROFILE_CLASS_ID = "ProfileClassID";
    public static final String SMOKER = "Smoker";
    public static final String SEAT_CHOICE_ID = "SeatChoiceID";
    public static final String MAIN_PROFILE_ID = "MainProfileID";

    public static final String PROFILE_CODE_KEY = "ProfileCode";

    public static final String NAME_SORT_KEY = "NameSort";

    public static final String MAIN_PROFILE_ID_KEY = "MainProfileID";

    public static final String POSTAL_CODE_SORT_KEY = "PostalCodeSort";

    public static final String NAME_SUR_KEY = "NameSur";
    public static final String MESSAGE_LOG_SCREEN = "Message Log";
    public static final String MAINT_SCREEN_CLASS = "com.tourapp.tour.profile.screen.ProfileScreen";
    public static final String GRID_SCREEN_CLASS = "com.tourapp.tour.profile.screen.ProfileGridScreen";
    public static final String MESSAGE_LOG_GRID_SCREEN_CLASS = "org.jbundle.main.msg.screen.MessageLogGridScreen";
    public static final String REQUEST_HISTORY_GRID_SCREEN_CLASS = "com.tourapp.tour.request.screen.detail.RequestHistoryGridScreen";
    public static final String PROFILE_SCREEN_CLASS = "com.tourapp.tour.profile.screen.ProfileScreen";
    public static final String PROFILE_GRID_SCREEN_CLASS = "com.tourapp.tour.profile.screen.ProfileGridScreen";

    public static final String PROFILE_FILE = "Profile";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.profile.db.Profile";
    public static final String THICK_CLASS = "com.tourapp.tour.profile.db.Profile";

}
