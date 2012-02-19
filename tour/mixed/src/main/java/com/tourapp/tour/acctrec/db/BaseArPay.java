/**
 * @(#)BaseArPay.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.db;

import java.awt.*;
import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.model.tour.acctrec.db.*;

/**
 *  BaseArPay - Base payment instrument for A/R (Mcos, Credit Cards, etc.).
 */
public class BaseArPay extends BaseTrx
     implements BaseArPayModel
{
    private static final long serialVersionUID = 1L;

    public static final int POST = ScreenConstants.POST_MODE;
    public static final int ENTRY_SCREEN = ScreenConstants.DISPLAY_MODE | ScreenConstants.LAST_MODE;
    public static final int ENTRY_GRID_SCREEN = ScreenConstants.DISPLAY_MODE | ScreenConstants.LAST_MODE * 2;
    public static final int COLL_POST = ScreenConstants.DISPLAY_MODE | ScreenConstants.LAST_MODE * 4;
    public static final int COLL_SCREEN = ScreenConstants.DISPLAY_MODE | ScreenConstants.LAST_MODE * 8;
    public static final int APPROVE_SCREEN = ScreenConstants.DISPLAY_MODE | ScreenConstants.LAST_MODE * 16;
    public static final int SUBMIT_SCREEN = ScreenConstants.DISPLAY_MODE | ScreenConstants.LAST_MODE * 32;
    public static final int DISTRIBUTION_SCREEN = ScreenConstants.DETAIL_MODE | ScreenConstants.LAST_MODE * 64;
    /**
     * Default constructor.
     */
    public BaseArPay()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BaseArPay(RecordOwner screen)
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

    public static final String BASE_AR_PAY_FILE = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 3)
        //  field = new TrxStatusField(this, TRX_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 4)
        //  field = new BaseArPay_TrxUserID(this, TRX_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new BaseArPay_TrxDate(this, TRX_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new CurrencyField(this, AMT_APPLY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 7)
        //  field = new BaseArPay_TrxEntry(this, TRX_ENTRY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new BookingField(this, BOOKING_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new CurrencyField(this, GROSS, 10, null, null);
        if (iFieldSeq == 10)
        {
            field = new PercentField(this, SVC_PER, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 11)
            field = new CurrencyField(this, SVC_AMT, 8, null, null);
        if (iFieldSeq == 12)
            field = new CurrencyField(this, NET, 10, null, null);
        if (iFieldSeq == 13)
            field = new StringField(this, COMMENTS, 30, null, null);
        if (iFieldSeq == 14)
            field = new DateTimeField(this, DATE_SUBMITTED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 15)
            field = new DateTimeField(this, DATE_PAID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
            field = new CurrencyField(this, AMOUNT_PAID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
        {
            field = new BooleanField(this, PAID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 18)
            field = new DateTimeField(this, PAYMENT_ENTERED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Convert the command to the screen document type.
     * @param strCommand The command text.
     * @param The standard document type (MAINT/DISPLAY/SELECT/MENU/etc).
     */
    public int commandToDocType(String strCommand)
    {
        if (AcctDetailDist.DIST_DISTRIBUTION.equalsIgnoreCase(strCommand))
            return BaseArPay.DISTRIBUTION_SCREEN;
        return super.commandToDocType(strCommand);
    }

}
