/**
 * @(#)CardModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.profile.db;

import org.jbundle.model.db.*;

public interface CardModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String SERVICE_CHARGE = "ServiceCharge";
    public static final String CREDIT_CARD_REC_ACCOUNT_ID = "CreditCardRecAccountID";
    public static final String CREDIT_CARD_VAR_ACCOUNT_ID = "CreditCardVarAccountID";
    public static final String CARD_CODE = "CardCode";

    public static final String CARD_FILE = "Card";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.profile.db.Card";
    public static final String THICK_CLASS = "com.tourapp.tour.profile.db.Card";

}
