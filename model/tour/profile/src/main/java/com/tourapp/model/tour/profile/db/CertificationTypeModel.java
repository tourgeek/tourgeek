/**
  * @(#)CertificationTypeModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.profile.db;

import org.jbundle.model.db.*;

public interface CertificationTypeModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String CERTIFICATION_TYPE_FILE = "CertificationType";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.profile.db.CertificationType";
    public static final String THICK_CLASS = "com.tourapp.tour.profile.db.CertificationType";

}
