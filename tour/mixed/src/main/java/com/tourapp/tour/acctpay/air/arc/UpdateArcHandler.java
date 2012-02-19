/**
 * @(#)UpdateArcHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.air.arc;

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
import com.tourapp.tour.acctpay.screen.trx.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  UpdateArcHandler - .
 */
public class UpdateArcHandler extends UpdateApTrxHandler
{
    /**
     * Default constructor.
     */
    public UpdateArcHandler()
    {
        super();
    }
    /**
     * UpdateArcHandler Method.
     */
    public UpdateArcHandler(Record record)
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
        return (ReferenceField)this.getOwner().getRecordOwner().getScreenRecord().getField(ArcReportScreenRecord.SUMMARY_ACCOUNT_ID);
    }
    /**
     * Get the Debit Account field.
     * @return The debit account field.
     */
    public ReferenceField getDrAccount()
    {
        ReferenceField fldAccount = null;
        if (this.getProductCategory() != null)
            fldAccount = (ReferenceField)this.getProductCategory().getField(ProductCategory.PP_TIC_ACCOUNT_ID);
        if (fldAccount == null)
            fldAccount = (ReferenceField)this.getOwner().getRecordOwner().getRecord(ApControl.AP_CONTROL_FILE).getField(ApControl.PP_TIC_ACCOUNT_ID);
        return fldAccount;
    }
    /**
     * Get the transaction amount for this type of transaction.
     * @param fldTypicalBalance The typical balance field (Debit/Credit/none).
     * @return The transaction amount.
     */
    public double getTrxAmount(BaseField fldTypicalBalance)
    {
        return this.getOwner().getField(TicketTrx.NET_FARE).getValue();
    }

}
