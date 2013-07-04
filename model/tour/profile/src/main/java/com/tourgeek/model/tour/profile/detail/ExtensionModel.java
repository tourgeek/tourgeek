
package com.tourgeek.model.tour.profile.detail;

import org.jbundle.model.db.*;

public interface ExtensionModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String PROFILE_ID = "ProfileID";
    public static final String CONTENT_CODE = "ContentCode";
    public static final String CONTENT_DATA = "ContentData";
    public static final String DESCRIPTION = "Description";

    public static final String PROFILE_ID_KEY = "ProfileID";

    public static final String EXTENSION_FILE = "Extension";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.profile.detail.Extension";
    public static final String THICK_CLASS = "com.tourgeek.tour.profile.detail.Extension";

}
