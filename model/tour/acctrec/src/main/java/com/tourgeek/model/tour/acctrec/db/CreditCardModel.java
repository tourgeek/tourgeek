
package com.tourgeek.model.tour.acctrec.db;

import com.tourgeek.model.tour.acctrec.db.*;

public interface CreditCardModel extends BaseArPayModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String TRX_STATUS_ID = TRX_STATUS_ID;
    //public static final String TRX_USER_ID = TRX_USER_ID;
    //public static final String TRX_DATE = TRX_DATE;
    //public static final String AMT_APPLY = AMT_APPLY;
    //public static final String TRX_ENTRY = TRX_ENTRY;
    //public static final String BOOKING_ID = BOOKING_ID;
    //public static final String GROSS = GROSS;
    //public static final String SVC_PER = SVC_PER;
    //public static final String SVC_AMT = SVC_AMT;
    //public static final String NET = NET;
    //public static final String COMMENTS = COMMENTS;
    //public static final String DATE_SUBMITTED = DATE_SUBMITTED;
    //public static final String DATE_PAID = DATE_PAID;
    //public static final String AMOUNT_PAID = AMOUNT_PAID;
    //public static final String PAID = PAID;
    //public static final String PAYMENT_ENTERED = PAYMENT_ENTERED;
    public static final String CARD_ID = "CardID";
    public static final String CARD_NO = "CardNo";
    public static final String EXPIRATION = "Expiration";
    public static final String DATE_APPROVED = "DateApproved";

    public static final String TRX_STATUS_ID_KEY = "TrxStatusID";

    public static final String TRX_DATE_KEY = "TrxDate";
    public static final String APPROVED = "Approved";

    public static final String CREDIT_CARD_FILE = "CreditCard";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.acctrec.db.CreditCard";
    public static final String THICK_CLASS = "com.tourgeek.tour.acctrec.db.CreditCard";

}
