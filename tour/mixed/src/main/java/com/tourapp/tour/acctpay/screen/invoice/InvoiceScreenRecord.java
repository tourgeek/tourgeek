/**
 * @(#)InvoiceScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.invoice;

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
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctpay.screen.findepest.*;
import com.tourapp.tour.acctpay.screen.trx.*;

/**
 *  InvoiceScreenRecord - Fields for the Invoice Screen.
 */
public class InvoiceScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String AP_ACCOUNT_ID = "ApAccountID";
    public static final int kApAccountID = kScreenRecordLastField + 1;
    public static final String COST_ACCOUNT_ID = "CostAccountID";
    public static final int kCostAccountID = kApAccountID + 1;
    public static final String LAST_VENDOR_ID = "LastVendorID";
    public static final int kLastVendorID = kCostAccountID + 1;
    public static final String LAST_INVOICE_DATE = "LastInvoiceDate";
    public static final int kLastInvoiceDate = kLastVendorID + 1;
    public static final String LAST_INVOICE_NO = "LastInvoiceNo";
    public static final int kLastInvoiceNo = kLastInvoiceDate + 1;
    public static final int kInvoiceScreenRecordLastField = kLastInvoiceNo;
    public static final int kInvoiceScreenRecordFields = kLastInvoiceNo - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public InvoiceScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public InvoiceScreenRecord(RecordOwner screen)
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

    public static final String kInvoiceScreenRecordFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kApAccountID)
            field = new AccountField(this, "ApAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCostAccountID)
            field = new AccountField(this, "CostAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLastVendorID)
            field = new VendorField(this, "LastVendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLastInvoiceDate)
            field = new InvoiceScreenRecord_LastInvoiceDate(this, "LastInvoiceDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLastInvoiceNo)
            field = new StringField(this, "LastInvoiceNo", 15, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kInvoiceScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
