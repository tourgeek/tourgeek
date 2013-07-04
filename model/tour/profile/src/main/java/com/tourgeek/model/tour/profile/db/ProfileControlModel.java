
package com.tourgeek.model.tour.profile.db;

import org.jbundle.model.db.*;

public interface ProfileControlModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DEFAULT_PROFILE_STATUS_ID = "DefaultProfileStatusID";
    public static final String DEFAULT_PROFILE_CLASS_ID = "DefaultProfileClassID";
    public static final String DEFAULT_PROFILE_TYPE_ID = "DefaultProfileTypeID";
    public static final String COUNTRY_ID = "CountryID";
    public static final String LANGUAGE_ID = "LanguageID";
    public static final String CURRENCYS_ID = "CurrencysID";
    public static final String MESSAGE_TRANSPORT_ID = "MessageTransportID";

    public static final String PROFILE_CONTROL_FILE = "ProfileControl";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.profile.db.ProfileControl";
    public static final String THICK_CLASS = "com.tourgeek.tour.profile.db.ProfileControl";

}
