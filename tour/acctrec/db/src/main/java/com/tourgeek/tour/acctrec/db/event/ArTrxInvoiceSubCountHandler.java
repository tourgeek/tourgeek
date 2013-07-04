
package com.tourgeek.tour.acctrec.db.event;

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
import com.tourgeek.tour.acctrec.db.*;
import com.tourgeek.tour.genled.db.*;
import com.tourgeek.model.tour.booking.db.*;

/**
 *  ArTrxInvoiceSubCountHandler - .
 */
public class ArTrxInvoiceSubCountHandler extends SubCountHandler
{
    protected int iInvoiceModTrxStatus = -1;
    protected int iInvoiceTrxStatus = -1;
    protected int iTrxStatus = -1;
    /**
     * Default constructor.
     */
    public ArTrxInvoiceSubCountHandler()
    {
        super();
    }
    /**
     * Count a sub-field.
     */
    public ArTrxInvoiceSubCountHandler(BaseField fieldMain, String ifsToCount, boolean bRecountOnSelect, boolean bVerifyOnEOF)
    {
        this();
        this.init(fieldMain, ifsToCount, bRecountOnSelect, bVerifyOnEOF);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fieldMain, String ifsToCount, boolean bRecountOnSelect, boolean bVerifyOnEOF)
    {
        super.init(null, null, null, fieldMain, ifsToCount, bRecountOnSelect, bVerifyOnEOF, false);
    }
    /**
     * Get the value to add (Overidden from SubCountHandler).
     * If there was a field specified, return the value, otherwise just return a count of 1.
     * @return The field value.
     */
    public double getFieldValue()
    {
        if (iInvoiceTrxStatus == -1)
            this.firstTime();
        if ((this.getOwner().getField(ArTrx.TRX_STATUS_ID).getValue() == iInvoiceTrxStatus)
            || (this.getOwner().getField(ArTrx.TRX_STATUS_ID).getValue() == iInvoiceModTrxStatus)) // Amount of old Price
        {
            iTrxStatus = iInvoiceModTrxStatus;      // Invoice Mod
            return super.getFieldValue();
        }
        return 0;   // This is not an invoice or invoice mod
    }
    /**
     * Reset the field count.
     */
    public int resetCount()
    {
        if (iInvoiceTrxStatus == -1)
            this.firstTime();
        iTrxStatus = iInvoiceTrxStatus;
        return super.resetCount();
    }
    /**
     * GetTrxStatus Method.
     */
    public int getTrxStatus()
    {
        if (iInvoiceTrxStatus == -1)
        {
            this.firstTime();
            iTrxStatus = iInvoiceTrxStatus;
        }
        return iTrxStatus;
    }
    /**
     * FirstTime Method.
     */
    public void firstTime()
    {
        try {
            TrxStatus recTrxStatus = (TrxStatus)((ReferenceField)this.getOwner().getField(ArTrx.TRX_STATUS_ID)).getReferenceRecord();
            Object bookmark = null;
            if ((recTrxStatus.getEditMode() == DBConstants.EDIT_CURRENT) || (recTrxStatus.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                bookmark = recTrxStatus.getHandle(DBConstants.BOOKMARK_HANDLE);
            boolean[] brgFieldListeners = recTrxStatus.setEnableListeners(false);
            iInvoiceTrxStatus = recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.AR_TRX_FILE, ArTrx.INVOICE);
            iInvoiceModTrxStatus = recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.AR_TRX_FILE, ArTrx.INVOICE_MODIFICATION);
            if (bookmark == null)
                recTrxStatus.addNew();
            else
                recTrxStatus.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE);
            recTrxStatus.setEnableListeners(brgFieldListeners);
        } catch (DBException e) {
            e.printStackTrace();
        }
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
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        switch (iChangeType)
        {
            case DBConstants.AFTER_ADD_TYPE:
                if (iTrxStatus == iInvoiceTrxStatus)
                    if (iTrxStatus != this.getOwner().getField(ArTrx.TRX_STATUS_ID).getValue())
                {   // The first entry is not an "Invoice" entry, so I need to add the invoice entries first
                    this.addInvoiceEntry();
                }
                break;
            default:        
        }
        return iErrorCode;
    }
    /**
     * Add the A/R trx entry for the total invoice amount.
     */
    public void addInvoiceEntry()
    {
        ArTrx recArTrx = (ArTrx)this.getOwner();
        Calendar calTrxDate = ((DateTimeField)this.getOwner().getField(ArTrx.TRX_DATE)).getCalendar();
        if (calTrxDate != null)
            calTrxDate.add(Calendar.MINUTE, -1);    // Just so it will come before the previous entry
        boolean bOldState = this.setEnabledListener(true);  // This method IS disabled, but must be enabled if I update this record (so count is correct)
        try {
            SubFileFilter filter = (SubFileFilter)this.getOwner().getListener(SubFileFilter.class);
            BookingModel recBooking = (BookingModel)filter.getMainRecord();
            recBooking.addArDetail(recArTrx, null, false);      // Being careful
            recArTrx.addNew();
            double dBalance = recBooking.getField(BookingModel.NET).getValue();
            recArTrx.getField(ArTrx.AMOUNT).setValue(dBalance);
            ((DateTimeField)recArTrx.getField(ArTrx.TRX_DATE)).setCalendar(calTrxDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);   // Just before the previous entry.
            recArTrx.getField(ArTrx.TRX_STATUS_ID).setValue(iInvoiceTrxStatus);
            recArTrx.getField(ArTrx.COMMENTS).moveFieldToThis(((ReferenceField)recArTrx.getField(ArTrx.TRX_STATUS_ID)).getReference().getField(TrxStatus.STATUS_DESC));
            recArTrx.add();
        } catch (DBException ex) {
            ex.printStackTrace();
        } finally {
            this.setEnabledListener(bOldState);
        }
    }

}
