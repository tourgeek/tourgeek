/**
  * @(#)UpdateCreditMemoHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctpay.screen.drcr;

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
import com.tourgeek.tour.base.db.*;
import com.tourgeek.tour.genled.db.*;
import com.tourgeek.tour.acctpay.db.event.*;

/**
 *  UpdateCreditMemoHandler - .
 */
public class UpdateCreditMemoHandler extends UpdateApTrxHandler
{
    /**
     * Default constructor.
     */
    public UpdateCreditMemoHandler()
    {
        super();
    }
    /**
     * UpdateCreditMemoHandler Method.
     */
    public UpdateCreditMemoHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(record);
    }
    /**
     * Get the Credit Account field.
     * @return The credit account field.
     */
    public ReferenceField getCrAccount()
    {
        return (ReferenceField)this.getOwner().getRecordOwner().getScreenRecord().getField(DebitMemoScreenRecord.AP_ACCOUNT_ID);
    }
    /**
     * Get the Debit Account field.
     * @return The debit account field.
     */
    public ReferenceField getDrAccount()
    {
        return (ReferenceField)this.getOwner().getRecordOwner().getScreenRecord().getField(DebitMemoScreenRecord.TOUR_ACCOUNT_ID);
    }
    /**
     * Get the transaction amount for this type of transaction.
     * @param fldTypicalBalance The typical balance field (Debit/Credit/none).
     * @return The transaction amount.
     */
    public double getTrxAmount(BaseField fldTypicalBalance)
    {
        return this.getOwner().getField(ApTrx.INVOICE_LOCAL).getValue();
    }

}
