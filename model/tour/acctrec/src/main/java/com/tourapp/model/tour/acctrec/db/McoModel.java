/**
 * @(#)McoModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctrec.db;

import com.tourapp.model.tour.acctrec.db.*;

public interface McoModel extends BaseArPayModel
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
    public static final String MCO_NO = "McoNo";
    public static final String AIRLINE_ID = "AirlineID";
    public static final String COMM_PER = "CommPer";
    public static final String COMM_AMT = "CommAmt";
    public static final String TAX_PER = "TaxPer";
    public static final String TAX_AMT = "TaxAmt";
    public static final String CARRIER_SVC_PER = "CarrierSvcPer";

    public static final String TRX_STATUS_ID_KEY = "TrxStatusID";

    public static final String MCO_NO_KEY = "McoNo";
    public static final String MCO_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.mco.McoScreen";
    public static final String MCO_GRID_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.mco.McoGridScreen";
    public static final String MCO_DIST_GRID_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.mco.trx.McoDistGridScreen";
    public static final String MCO_POST_CLASS = "com.tourapp.tour.acctrec.screen.mco.McoPost";
    public static final String MCO_ENTRY_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.mco.McoEntryScreen";
    public static final String MCO_ENTRY_GRID_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.mco.McoEntryGridScreen";
    public static final String MCO_COLL_POST_CLASS = "com.tourapp.tour.acctrec.screen.mco.McoCollPost";
    public static final String MCO_COLL_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.mco.McoCollScreen";
    public static final String MCO_BATCH_DIST_GRID_SCREEN_CLASS = "com.tourapp.tour.acctrec.screen.mco.McoBatchDistGridScreen";

    public static final String MCO_FILE = "Mco";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctrec.db.Mco";
    public static final String THICK_CLASS = "com.tourapp.tour.acctrec.db.Mco";

}
