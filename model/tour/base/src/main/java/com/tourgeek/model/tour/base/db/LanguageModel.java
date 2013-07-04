/**
  * @(#)LanguageModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.base.db;

import org.jbundle.model.db.*;

public interface LanguageModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String CODE = "Code";

    public static final String CODE_KEY = "Code";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String LANGUAGE_FILE = "Language";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.base.db.Language";
    public static final String THICK_CLASS = "com.tourapp.tour.base.db.Language";

}
