
package com.tourgeek.model.tour.profile.detail;

import org.jbundle.model.db.*;

public interface DocumentModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String PROFILE_ID = "ProfileID";
    public static final String DOC_NAME = "DocName";
    public static final String DOC_NUMBER = "DocNumber";
    public static final String COUNTRY_CODE_ID = "CountryCodeID";
    public static final String NATIONALITY_ID = "NationalityID";
    public static final String EFFECTIVE_DATE = "EffectiveDate";
    public static final String EXPIRATION_DATE = "ExpirationDate";
    public static final String DOCUMENT_CODE = "DocumentCode";
    public static final String PLACE_OF_ISSUE = "PlaceOfIssue";

    public static final String PROFILE_ID_KEY = "ProfileID";

    public static final String DOCUMENT_FILE = "Document";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.profile.detail.Document";
    public static final String THICK_CLASS = "com.tourgeek.tour.profile.detail.Document";

}
