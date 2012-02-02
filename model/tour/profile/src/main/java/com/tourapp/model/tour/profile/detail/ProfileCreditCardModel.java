/**
 * @(#)ProfileCreditCardModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.profile.detail;

import org.jbundle.model.db.*;

public interface ProfileCreditCardModel extends Rec
{

    //public static final String ID = ID;
    public static final String PROFILE_ID = "ProfileID";
    public static final String CC_CODE = "CCCode";
    public static final String CC_HOLDER_NAME = "CCHolderName";
    public static final String CC_NUMBER = "CCNumber";
    public static final String CC_BEGIN_DATE = "CCBeginDate";
    public static final String CC_EXPIRE = "CCExpire";

    public static final String PROFILE_ID_KEY = "ProfileID";

    public static final String CC_NUMBER_KEY = "CCNumber";

    public static final String PROFILE_CREDIT_CARD_FILE = "ProfileCreditCard";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.profile.detail.ProfileCreditCard";
    public static final String THICK_CLASS = "com.tourapp.tour.profile.detail.ProfileCreditCard";

}
