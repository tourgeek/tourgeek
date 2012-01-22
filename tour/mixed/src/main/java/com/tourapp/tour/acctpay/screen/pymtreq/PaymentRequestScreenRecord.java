/**
 * @(#)PaymentRequestScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.pymtreq;

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
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.acctpay.screen.genpymt.*;
import com.tourapp.tour.acctpay.screen.check.*;
import org.jbundle.main.screen.*;
import com.tourapp.tour.base.db.*;

/**
 *  PaymentRequestScreenRecord - Screen Fields.
 */
public class PaymentRequestScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kBankAcctID = kScreenRecordLastField + 1;
    public static final int kRequestTotal = kBankAcctID + 1;
    public static final int kManualChecks = kRequestTotal + 1;
    public static final int kPaymentRequestScreenRecordLastField = kManualChecks;
    public static final int kPaymentRequestScreenRecordFields = kManualChecks - DBConstants.MAIN_FIELD + 1;
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

    public static final String kPaymentRequestScreenRecordFile = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kBankAcctID)
            field = new BankAcctField(this, "BankAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRequestTotal)
            field = new FullCurrencyField(this, "RequestTotal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kManualChecks)
            field = new BooleanField(this, "ManualChecks", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kPaymentRequestScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
