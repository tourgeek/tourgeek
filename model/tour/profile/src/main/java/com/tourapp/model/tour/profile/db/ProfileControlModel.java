/**
 * @(#)ProfileControlModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.profile.db;

import org.jbundle.model.db.*;

public interface ProfileControlModel extends Rec
{

    //public static final String ID = ID;
    public static final String DEFAULT_PROFILE_STATUS_ID = "DefaultProfileStatusID";
    public static final String DEFAULT_PROFILE_CLASS_ID = "DefaultProfileClassID";
    public static final String DEFAULT_PROFILE_TYPE_ID = "DefaultProfileTypeID";
    public static final String COUNTRY_ID = "CountryID";
    public static final String LANGUAGE_ID = "LanguageID";
    public static final String CURRENCYS_ID = "CurrencysID";
    public static final String MESSAGE_TRANSPORT_ID = "MessageTransportID";

    public static final String PROFILE_CONTROL_FILE = "ProfileControl";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.profile.db.ProfileControl";
    public static final String THICK_CLASS = "com.tourapp.tour.profile.db.ProfileControl";

}
