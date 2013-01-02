/**
  * @(#)InvoiceAcctHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctpay.screen.invoice;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctpay.screen.findepest.*;
import com.tourapp.tour.acctpay.screen.trx.*;

/**
 *  InvoiceAcctHandler - If there is no tour, set the default A/P Account and Cost account.
 */
public class InvoiceAcctHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public InvoiceAcctHandler()
    {
        super();
    }
    /**
     * InvoiceAcctHandler Method.
     */
    public InvoiceAcctHandler(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        super.init(field);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        Record recApTrx = this.getOwner().getRecord();
        RecordOwner screen = recApTrx.getRecordOwner();
        BaseField fldApAccountID = ((Record)screen.getScreenRecord()).getField(InvoiceScreenRecord.AP_ACCOUNT_ID);
        BaseField fldCostAccountID = ((Record)screen.getScreenRecord()).getField(InvoiceScreenRecord.COST_ACCOUNT_ID);
        TrxStatus recTrxStatus = (TrxStatus)screen.getRecord(TrxStatus.TRX_STATUS_FILE);
        Record recApControl = (Record)screen.getRecord(ApControl.AP_CONTROL_FILE);
        FileListener invoiceBehavior = ((Record)screen.getRecord(ApTrx.AP_TRX_FILE)).getListener(UpdateInvoiceHandler.class.getName());
        FileListener invoiceNonTourBehavior = ((Record)screen.getRecord(ApTrx.AP_TRX_FILE)).getListener(UpdateNonTourInvoiceHandler.class.getName());
        BaseField fldTourID = recApTrx.getField(ApTrx.TOUR_ID);
        if (fldTourID.isNull())
        { // No tour, set defaults
            fldApAccountID.moveFieldToThis(recApControl.getField(ApControl.NON_TOUR_AP_ACCOUNT_ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
            Record recVendor = ((ReferenceField)recApTrx.getField(ApTrx.VENDOR_ID)).getReference();
            if ((recVendor != null) && (!recVendor.getField(Vendor.DEFAULT_ACCOUNT_ID).isNull()))
                fldCostAccountID.moveFieldToThis(recVendor.getField(Vendor.DEFAULT_ACCOUNT_ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
            else
                fldCostAccountID.moveFieldToThis(recApControl.getField(ApControl.COST_ACCOUNT_ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        
            if (recApTrx.getField(ApTrx.INVOICE_AMOUNT).getValue() >= 0)
                recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.INVOICE_NON_TOUR);
            else
                recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.CREDIT_INVOICE_NON_TOUR);
        
            fldApAccountID.setEnabled(true);
            fldCostAccountID.setEnabled(true);
            if ((invoiceBehavior.isEnabled()) && (!invoiceNonTourBehavior.isEnabled()))
            {
                invoiceBehavior.setEnabledListener(false);
                invoiceNonTourBehavior.setEnabledListener(true);
            }
        }
        else
        { // Tour set, Don't allow account changes
            fldApAccountID.setString(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
            fldCostAccountID.setString(null, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        
            if (recApTrx.getField(ApTrx.INVOICE_AMOUNT).getValue() >= 0)
                recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.INVOICE);
            else
                recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.CREDIT_INVOICE);
        
            fldApAccountID.setEnabled(false);
            fldCostAccountID.setEnabled(false);
            if ((invoiceNonTourBehavior.isEnabled()) && (!invoiceBehavior.isEnabled()))
            {
                invoiceBehavior.setEnabledListener(true);
                invoiceNonTourBehavior.setEnabledListener(false);
            }
        }
        if (iMoveMode != DBConstants.READ_MOVE)
        {
            recApTrx.getField(ApTrx.TRX_STATUS_ID).initField(bDisplayOption);
            recApTrx.getField(ApTrx.TRX_STATUS_ID).setModified(false);
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
