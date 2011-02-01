/**
 *  @(#)InvoiceAmountSubCountHandler.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report;

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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.db.*;

/**
 *  InvoiceAmountSubCountHandler - Total the invoice amounts for invoices only.
 */
public class InvoiceAmountSubCountHandler extends SubCountHandler
{
    /**
     * Default constructor.
     */
    public InvoiceAmountSubCountHandler()
    {
        super();
    }
    /**
     * Count a sub-field.
     */
    public InvoiceAmountSubCountHandler(BaseField fieldMain, int ifsToCount, boolean bRecountOnSelect, boolean bVerifyOnEOF, boolean bResetOnBreak)
    {
        this();
        this.init(fieldMain, ifsToCount, bRecountOnSelect, bVerifyOnEOF, bResetOnBreak);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fieldMain, int ifsToCount, boolean bRecountOnSelect, boolean bVerifyOnEOF, boolean bResetOnBreak)
    {
        super.init(null, null, -1, fieldMain, ifsToCount, bRecountOnSelect, bVerifyOnEOF, bResetOnBreak);
    }
    /**
     * Get the value to add (Overidden from SubCountHandler).
     * If there was a field specified, return the value, otherwise just return a count of 1.
     * @return The field value.
     */
    public double getFieldValue()
    {
        double dMainValue = super.getFieldValue();
        Record recApTrx = this.getOwner();
        Record recTrxStatus = ((ReferenceField)recApTrx.getField(ApTrx.kTrxStatusID)).getReference();
        if (recTrxStatus != null)
            if (recTrxStatus.getField(TrxStatus.kStatusCode).toString().indexOf(ApTrx.INVOICE) == -1)
                dMainValue = 0; // Don't total non-invoice types
        return dMainValue;
    }

}
