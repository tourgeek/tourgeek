/**
 * @(#)ElectronicAddressModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.profile.detail;

import org.jbundle.model.db.*;

public interface ElectronicAddressModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String PROFILE_ID = "ProfileID";
    public static final String E_ADDRESS_TYPE_ID = "EAddressTypeID";
    public static final String E_ADDRESS = "EAddress";

    public static final String PROFILE_ID_KEY = "ProfileID";

    public static final String ELECTRONIC_ADDRESS_FILE = "ElectronicAddress";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.profile.detail.ElectronicAddress";
    public static final String THICK_CLASS = "com.tourapp.tour.profile.detail.ElectronicAddress";

}
