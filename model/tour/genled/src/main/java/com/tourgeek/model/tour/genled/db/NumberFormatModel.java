/**
  * @(#)NumberFormatModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface NumberFormatModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String NAME = "Name";
    public static final String DESCRIPTION = "Description";

    public static final String NAME_KEY = "Name";

    public static final String NUMBER_FORMAT_FILE = "NumberFormat";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.genled.db.NumberFormat";
    public static final String THICK_CLASS = "com.tourgeek.tour.genled.db.NumberFormat";

}
