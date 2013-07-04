
package com.tourgeek.tour.acctpay.screen.invoice;

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
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.tour.genled.db.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.acctpay.screen.findepest.*;
import com.tourgeek.tour.acctpay.screen.trx.*;

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
        BaseField fldVendorID = this.getOwner().getField(ApTrx.VENDOR_ID);
        BaseField fldLastVendorID = m_screenRecord.getField(InvoiceScreenRecord.LAST_VENDOR_ID);
        if (fldVendorID.equals(fldLastVendorID))
            // if (trxStatus = departure est)
        {
            this.getOwner().getField(ApTrx.INVOICE_NO).moveFieldToThis(m_screenRecord.getField(InvoiceScreenRecord.LAST_INVOICE_NO), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
            this.getOwner().getField(ApTrx.INVOICE_DATE).moveFieldToThis(m_screenRecord.getField(InvoiceScreenRecord.LAST_INVOICE_DATE), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        }
        super.doValidRecord(bDisplayOption);
    }

}
