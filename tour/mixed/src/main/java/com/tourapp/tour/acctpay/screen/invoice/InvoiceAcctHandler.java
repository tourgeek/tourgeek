/**
 * @(#)InvoiceAcctHandler.
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
        BaseField fldApAccountID = screen.getScreenRecord().getField(InvoiceScreenRecord.kApAccountID);
        BaseField fldCostAccountID = screen.getScreenRecord().getField(InvoiceScreenRecord.kCostAccountID);
        TrxStatus recTrxStatus = (TrxStatus)screen.getRecord(TrxStatus.kTrxStatusFile);
        Record recApControl = screen.getRecord(ApControl.kApControlFile);
        FileListener invoiceBehavior = screen.getRecord(ApTrx.kApTrxFile).getListener(UpdateInvoiceHandler.class.getName());
        FileListener invoiceNonTourBehavior = screen.getRecord(ApTrx.kApTrxFile).getListener(UpdateNonTourInvoiceHandler.class.getName());
        BaseField fldTourID = recApTrx.getField(ApTrx.kTourID);
        if (fldTourID.isNull())
        { // No tour, set defaults
            fldApAccountID.moveFieldToThis(recApControl.getField(ApControl.kNonTourApAccountID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
            Record recVendor = ((ReferenceField)recApTrx.getField(ApTrx.kVendorID)).getReference();
            if ((recVendor != null) && (!recVendor.getField(Vendor.kDefaultAccountID).isNull()))
                fldCostAccountID.moveFieldToThis(recVendor.getField(Vendor.kDefaultAccountID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
            else
                fldCostAccountID.moveFieldToThis(recApControl.getField(ApControl.kCostAccountID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        
            if (recApTrx.getField(ApTrx.kInvoiceAmount).getValue() >= 0)
                recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.INVOICE_NON_TOUR);
            else
                recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.CREDIT_INVOICE_NON_TOUR);
        
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
        
            if (recApTrx.getField(ApTrx.kInvoiceAmount).getValue() >= 0)
                recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.INVOICE);
            else
                recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.CREDIT_INVOICE);
        
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
            recApTrx.getField(ApTrx.kTrxStatusID).initField(bDisplayOption);
            recApTrx.getField(ApTrx.kTrxStatusID).setModified(false);
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
