/**
 * @(#)DebitMemoScreenRecord.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.drcr;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.screen.trx.*;

/**
 *  DebitMemoScreenRecord - .
 */
public class DebitMemoScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String TOUR_ACCOUNT_ID = "TourAccountID";
    public static final String PP_ACCOUNT_ID = "PpAccountID";
    public static final String AP_ACCOUNT_ID = "ApAccountID";
    /**
     * Default constructor.
     */
    public DebitMemoScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public DebitMemoScreenRecord(RecordOwner screen)
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

    public static final String DEBIT_MEMO_SCREEN_RECORD_FILE = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new AccountField(this, TOUR_ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new AccountField(this, PP_ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 2)
            field = new AccountField(this, AP_ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
