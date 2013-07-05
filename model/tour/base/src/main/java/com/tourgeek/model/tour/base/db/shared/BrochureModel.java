/**
  * @(#)BrochureModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.base.db.shared;

import org.jbundle.model.db.*;

public interface BrochureModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String BROCHURE_CLASS_ID = "BrochureClassID";
    public static final String DISCONTINUED = "Discontinued";
    public static final String REQUEST = "Request";
    public static final String INVENTORY = "Inventory";
    public static final String LINK = "Link";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String BROCHURE_CLASS_ID_KEY = "BrochureClassID";

    public static final String BROCHURE_FILE = "Brochure";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.base.db.shared.Brochure";
    public static final String THICK_CLASS = "com.tourgeek.tour.base.db.shared.Brochure";

}
