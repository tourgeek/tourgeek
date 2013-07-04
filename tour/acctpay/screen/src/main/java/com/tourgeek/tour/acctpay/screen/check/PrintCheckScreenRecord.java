
package com.tourgeek.tour.acctpay.screen.check;

import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourgeek.tour.acctrec.screen.refund.*;
import org.jbundle.base.screen.model.report.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.assetdr.db.*;
import com.tourgeek.tour.genled.db.*;
import org.jbundle.base.screen.model.util.*;

/**
 *  PrintCheckScreenRecord - .
 */
public class PrintCheckScreenRecord extends CheckScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final String REPORT_DATE = REPORT_DATE;
    //public static final String REPORT_TIME = REPORT_TIME;
    //public static final String REPORT_USER_ID = REPORT_USER_ID;
    //public static final String REPORT_PAGE = REPORT_PAGE;
    //public static final String REPORT_COUNT = REPORT_COUNT;
    //public static final String REPORT_TOTAL = REPORT_TOTAL;
    //public static final String REPORT_KEY_AREA = REPORT_KEY_AREA;
    //public static final String CHECK_DATE = CHECK_DATE;
    //public static final String CHECK_NO = CHECK_NO;
    //public static final String PAYEE = PAYEE;
    //public static final String CHECK_AMOUNT = CHECK_AMOUNT;
    //public static final String CHECK_AMOUNT_TEXT = CHECK_AMOUNT_TEXT;
    public static final String BANK_ACCT_ID = "BankAcctID";
    public static final String NEXT_CHECK_NO = "NextCheckNo";
    public static final String CHECKS_TO_PRINT = "ChecksToPrint";
    public static final String TEMPLATE = "template";
    /**
     * Default constructor.
     */
    public PrintCheckScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public PrintCheckScreenRecord(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }

    public static final String PRINT_CHECK_SCREEN_RECORD_FILE = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //  field = new PrintCheckScreenRecord_ReportDate(this, REPORT_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 1)
        //  field = new PrintCheckScreenRecord_ReportTime(this, REPORT_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 2)
        //  field = new PrintCheckScreenRecord_ReportUserID(this, REPORT_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 3)
        //  field = new ShortField(this, REPORT_PAGE, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        //if (iFieldSeq == 4)
        //  field = new IntegerField(this, REPORT_COUNT, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        //if (iFieldSeq == 5)
        //  field = new CurrencyField(this, REPORT_TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        //if (iFieldSeq == 6)
        //  field = new IntegerField(this, REPORT_KEY_AREA, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 7)
        //  field = new PrintCheckScreenRecord_CheckDate(this, CHECK_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 8)
        //  field = new IntegerField(this, CHECK_NO, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 9)
        //  field = new StringField(this, PAYEE, 60, null, null);
        //if (iFieldSeq == 10)
        //  field = new CurrencyField(this, CHECK_AMOUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 11)
        //  field = new AmountTextField(this, CHECK_AMOUNT_TEXT, 128, null, null);
        if (iFieldSeq == 12)
            field = new BankAcctField(this, BANK_ACCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 13)
            field = new IntegerField(this, NEXT_CHECK_NO, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 14)
            field = new ChecksToPrintField(this, CHECKS_TO_PRINT, 1, null, null);
        if (iFieldSeq == 15)
            field = new StringField(this, TEMPLATE, 60, null, "check");
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
