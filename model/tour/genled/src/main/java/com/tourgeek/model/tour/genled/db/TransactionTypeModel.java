
package com.tourgeek.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface TransactionTypeModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String TYPE_CODE = "TypeCode";
    public static final String TYPE_DESC = "TypeDesc";
    public static final String TRX_GROUP_ID = "TrxGroupID";
    public static final String GROUP_CODE = "GroupCode";
    public static final String GROUP_DESC = "GroupDesc";
    public static final String TRX_DESC_ID = "TrxDescID";
    public static final String DESC_CODE = "DescCode";
    public static final String DESCRIPTION = "Description";
    public static final String TRX_SYSTEM_ID = "TrxSystemID";
    public static final String SYSTEM_CODE = "SystemCode";
    public static final String SYSTEM_DESC = "SystemDesc";
    public static final String TYPICAL_BALANCE = "TypicalBalance";
    public static final String POSTING_TYPE = "PostingType";
    public static final String SOURCE_FILE = "SourceFile";
    public static final String SOURCE_TRX_DESC_ID = "SourceTrxDescID";
    public static final String SOURCE_TRX_STATUS_ID = "SourceTrxStatusID";
    public static final String SOURCE_PREFERRED_SIGN = "SourcePreferredSign";
    public static final String AMOUNT_FIELD = "AmountField";
    public static final String TRX_DATE_FIELD = "TrxDateField";
    public static final String ENTRY_DATE_FIELD = "EntryDateField";
    public static final String USER_ID_FIELD = "UserIDField";
    public static final String TRX_ID_FIELD = "TrxIDField";
    public static final String ACCOUNT_ID_FILE = "AccountIDFile";
    public static final String ACCOUNT_ID_FIELD = "AccountIDField";

    public static final String TRX_GROUP_ID_KEY = "TrxGroupID";

    public static final String TRX_TYPE_CODE_KEY = "TrxTypeCode";

    public static final String SOURCE_TRX_STATUS_ID_KEY = "SourceTrxStatusID";
    public static final String TRANSACTION_TYPE_GRID_SCREEN_CLASS = "com.tourgeek.tour.genled.screen.trx.TrxTypeGridScreen";
    public static final String TRANSACTION_TYPE_SCREEN_CLASS = "com.tourgeek.tour.genled.screen.trx.TrxTypeScreen";
    public static final String TRX_ID_SFIELD_CLASS = "com.tourgeek.tour.genled.screen.trx.TrxIDSField";
    public static final String GENLED = "genled";
    public static final String ASSETDR = "assetdr";
    public static final String ACCTPAY = "acctpay";
    public static final String ACCTREC = "acctrec";
    public static final String PAYROLL = "payroll";
    public static final String AIR = "air";

    public static final String TRANSACTION_TYPE_FILE = "TransactionType";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.genled.db.TransactionType";
    public static final String THICK_CLASS = "com.tourgeek.tour.genled.db.TransactionType";

}
