/**
 * @(#)UpdateInvoiceVendorHandler.
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
 *  UpdateInvoiceVendorHandler - If this departure estimate has the same vendor, use the
same invoice number and date as last time..
 */
public class UpdateInvoiceVendorHandler extends FileListener
{
    protected ScreenRecord m_screenRecord = null;
    /**
     * Default constructor.
     */
    public UpdateInvoiceVendorHandler()
    {
        super();
    }
    /**
     * UpdateInvoiceVendorHandler Method.
     */
    public UpdateInvoiceVendorHandler(ScreenRecord screenRecord)
    {
        this();
        this.init(screenRecord);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenRecord screenRecord)
    {
        m_screenRecord = null;
        m_screenRecord = screenRecord;
        super.init(null);
    }
    /**
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        BaseField fldVendorID = this.getOwner().getField(ApTrx.kVendorID);
        BaseField fldLastVendorID = m_screenRecord.getField(InvoiceScreenRecord.kLastVendorID);
        if (fldVendorID.equals(fldLastVendorID))
            // if (trxStatus = departure est)
        {
            this.getOwner().getField(ApTrx.kInvoiceNo).moveFieldToThis(m_screenRecord.getField(InvoiceScreenRecord.kLastInvoiceNo), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
            this.getOwner().getField(ApTrx.kInvoiceDate).moveFieldToThis(m_screenRecord.getField(InvoiceScreenRecord.kLastInvoiceDate), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        }
        super.doValidRecord(bDisplayOption);
    }

}
