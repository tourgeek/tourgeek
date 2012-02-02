/**
 * @(#)ProfileCertificationModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.profile.detail;

import org.jbundle.model.db.*;

public interface ProfileCertificationModel extends Rec
{

    //public static final String ID = ID;
    public static final String PROFILE_ID = "ProfileID";
    public static final String CERTIFICATION_TYPE_ID = "CertificationTypeID";
    public static final String CERTIFICATION_CODE = "CertificationCode";

    public static final String PROFILE_ID_KEY = "ProfileID";

    public static final String CERTIFICATION_CODE_KEY = "CertificationCode";

    public static final String PROFILE_CERTIFICATION_FILE = "ProfileCertification";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.profile.detail.ProfileCertification";
    public static final String THICK_CLASS = "com.tourapp.tour.profile.detail.ProfileCertification";

}
