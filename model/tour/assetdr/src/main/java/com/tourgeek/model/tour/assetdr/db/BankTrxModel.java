
package com.tourgeek.model.tour.assetdr.db;

import com.tourgeek.model.tour.genled.db.*;

public interface BankTrxModel extends BaseTrxModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String TRX_STATUS_ID = TRX_STATUS_ID;
    //public static final String TRX_USER_ID = TRX_USER_ID;
    //public static final String TRX_DATE = TRX_DATE;
    //public static final String AMOUNT_LOCAL = AMOUNT_LOCAL;
    //public static final String TRX_ENTRY = TRX_ENTRY;
    public static final String BANK_ACCT_ID = "BankAcctID";
    public static final String TRX_NUMBER = "TrxNumber";
    public static final String EFT_TRX_NO = "EFTTrxNo";
    public static final String PAYEE_TRX_DESC_ID = "PayeeTrxDescID";
    public static final String PAYEE_ID = "PayeeID";
    public static final String PAYEE_NAME = "PayeeName";
    public static final String AMOUNT = "Amount";
    public static final String EXCHANGE = "Exchange";
    public static final String INV_SIGN = "InvSign";
    public static final String INV_BALANCE = "InvBalance";
    public static final String INV_BALANCE_LOCAL = "InvBalanceLocal";
    public static final String COMMENTS = "Comments";
    public static final String DATE_RECONCILED = "DateReconciled";
    public static final String MANUAL = "Manual";

    public static final String TRX_DATE_KEY = "TrxDate";

    public static final String TRX_CLASS_KEY = "TrxClass";

    public static final String INV_BALANCE_KEY = "InvBalance";

    public static final String TRX_NUMBER_KEY = "TrxNumber";

    public static final String PAYEE_ID_KEY = "PayeeID";
    public static final String PAYMENT_DISTRIBUTION = "Payment Distribution";
    public static final String BANK_TRX_BATCH_SCREEN_CLASS = "com.tourgeek.tour.assetdr.screen.batch.BankTrxBatchScreen";
    public static final String BANK_TRX_BATCH_GRID_SCREEN_CLASS = "com.tourgeek.tour.assetdr.screen.batch.BankTrxBatchGridScreen";
    public static final String BANK_TRX_GRID_SCREEN_CLASS = "com.tourgeek.tour.assetdr.screen.trx.BankTrxGridScreen";
    public static final String BANK_TRX_SCREEN_CLASS = "com.tourgeek.tour.assetdr.screen.trx.BankTrxScreen";
    public static final String BANK_RECON_SCREEN_CLASS = "com.tourgeek.tour.assetdr.report.recon.BankReconScreen";
    public static final String BANK_TRX_DIST_GRID_SCREEN_CLASS = "com.tourgeek.tour.assetdr.screen.trx.BankTrxDistGridScreen";

    public static final String BANK_TRX_FILE = "BankTrx";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.assetdr.db.BankTrx";
    public static final String THICK_CLASS = "com.tourgeek.tour.assetdr.db.BankTrx";

}
