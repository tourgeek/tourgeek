/**
  * @(#)PaymentRequestScreenRecord.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctpay.screen.pymtreq;

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
import org.jbundle.base.screen.model.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.assetdr.db.*;
import com.tourgeek.tour.acctpay.screen.genpymt.*;
import com.tourgeek.tour.acctpay.screen.check.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.main.screen.*;
import com.tourgeek.tour.base.db.*;

/**
 *  PaymentRequestScreenRecord - Screen Fields.
 */
public class PaymentRequestScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String BANK_ACCT_ID = "BankAcctID";
    public static final String REQUEST_TOTAL = "RequestTotal";
    public static final String MANUAL_CHECKS = "ManualChecks";
    /**
     * Default constructor.
     */
    public PaymentRequestScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public PaymentRequestScreenRecord(RecordOwner screen)
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

    public static final String PAYMENT_REQUEST_SCREEN_RECORD_FILE = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new BankAcctField(this, BANK_ACCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new FullCurrencyField(this, REQUEST_TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 2)
            field = new BooleanField(this, MANUAL_CHECKS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
