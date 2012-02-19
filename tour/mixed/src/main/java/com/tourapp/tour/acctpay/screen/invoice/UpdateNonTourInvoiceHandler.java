/**
 * @(#)UpdateNonTourInvoiceHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import com.tourapp.tour.acctpay.screen.trx.*;
import com.tourapp.tour.acctpay.db.*;

/**
 *  UpdateNonTourInvoiceHandler - Update the Invoice for Non-tour Invoices.
 */
public class UpdateNonTourInvoiceHandler extends UpdateApTrxHandler
{
    /**
     * Default constructor.
     */
    public UpdateNonTourInvoiceHandler()
    {
        super();
    }
    /**
     * UpdateNonTourInvoiceHandler Method.
     */
    public UpdateNonTourInvoiceHandler(Record record)
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
     * Called when a change is the record status is about to happen/has happened.
     * @param field If this file change is due to a field, this is the field.
     * @param iChangeType The type of change that occurred.
     * @param bDisplayOption If true, display any changes.
     * @return an error code.
     * ADD_TYPE - Before a write.
     * UPDATE_TYPE - Before an update.
     * DELETE_TYPE - Before a delete.
     * AFTER_UPDATE_TYPE - After a write or update.
     * LOCK_TYPE - Before a lock.
     * SELECT_TYPE - After a select.
     * DESELECT_TYPE - After a deselect.
     * MOVE_NEXT_TYPE - After a move.
     * AFTER_REQUERY_TYPE - Record opened.
     * SELECT_EOF_TYPE - EOF Hit.
     */
    public int doRecordChange(FieldInfo field, int iChangeType, boolean bDisplayOption)
    {
        int iErrorCode = super.doRecordChange(field, iChangeType, bDisplayOption);
        if (iErrorCode == DBConstants.NORMAL_RETURN)
            if ((iChangeType == DBConstants.AFTER_UPDATE_TYPE)
                || (iChangeType == DBConstants.AFTER_ADD_TYPE))
        {
            try {
                this.getOwner().setHandle(this.getOwner().getHandle(DBConstants.DATA_SOURCE_HANDLE), DBConstants.DATA_SOURCE_HANDLE);
                this.getOwner().edit();
                this.getOwner().getField(ApTrx.ACCOUNT_ID).moveFieldToThis(this.getCrAccount());
                this.getOwner().set();
            } catch (DBException ex) {
                ex.printStackTrace();
            }
        }
        return iErrorCode;
    }
    /**
     * Get the transaction date.
     * @return The transaction date for this type of transaction.
     */
    public BaseField getTrxDate()
    {
        return this.getOwner().getField(ApTrx.INVOICE_DATE);
    }
    /**
     * Get the transaction amount for this type of transaction.
     * @param fldTypicalBalance The typical balance field (Debit/Credit/none).
     * @return The transaction amount.
     */
    public double getTrxAmount(BaseField fldTypicalBalance)
    {
        return this.getInvoiceAmtUSD();
    }
    /**
     * Get the Credit Account field.
     * @return The credit account field.
     */
    public ReferenceField getCrAccount()
    {
        return (ReferenceField)this.getOwner().getRecordOwner().getScreenRecord().getField(InvoiceScreenRecord.AP_ACCOUNT_ID);
    }
    /**
     * Get the Debit Account field.
     * @return The debit account field.
     */
    public ReferenceField getDrAccount()
    {
        return (ReferenceField)this.getOwner().getRecordOwner().getScreenRecord().getField(InvoiceScreenRecord.COST_ACCOUNT_ID);
    }

}
