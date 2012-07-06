/**
  * @(#)ApTrxStatusHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctpay.db.event;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.model.tour.booking.db.*;

/**
 *  ApTrxStatusHandler - .
 */
public class ApTrxStatusHandler extends FileListener
{
    /**
     * Default constructor.
     */
    public ApTrxStatusHandler()
    {
        super();
    }
    /**
     * ApTrxStatusHandler Method.
     */
    public ApTrxStatusHandler(Record record)
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
        
        if ((iChangeType == DBConstants.AFTER_ADD_TYPE)
            || (iChangeType == DBConstants.AFTER_UPDATE_TYPE)
            || (iChangeType == DBConstants.AFTER_DELETE_TYPE))
        {
            Record recApTrx = this.getOwner();
            if (ApTrx.DEP_ESTIMATE.equalsIgnoreCase(((ReferenceField)recApTrx.getField(ApTrx.TRX_STATUS_ID)).getReference().getField(TrxStatus.STATUS_CODE).toString()))
            {
                Record recVendor = ((ReferenceField)recApTrx.getField(ApTrx.VENDOR_ID)).getReference();
                if (recVendor != null)
                    if ((recVendor.getEditMode() == DBConstants.EDIT_CURRENT) || (recVendor.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                {
                    Record recDeposit = ((ReferenceField)recVendor.getField(Vendor.DEPOSIT_ID)).getReference();
                    if (recDeposit != null)
                        if ((recDeposit.getEditMode() == DBConstants.EDIT_CURRENT) || (recDeposit.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                    {
                        String strDepositType = recDeposit.getField(Deposit.DEPOSIT_TYPE).toString();
                        if ((DepositTypeField.AMOUNT.equalsIgnoreCase(strDepositType))
                            || (DepositTypeField.PERCENTAGE.equalsIgnoreCase(strDepositType))
                            || (DepositTypeField.IN_FULL.equalsIgnoreCase(strDepositType)))
                        {
                            double dAmount = recApTrx.getField(ApTrx.DEPARTURE_ESTIMATE).getValue();
                            if (dAmount != 0)
                            {
                                if (DepositTypeField.AMOUNT.equalsIgnoreCase(strDepositType))
                                    dAmount = recDeposit.getField(Deposit.QUANTITY).getValue();
                                if (DepositTypeField.PERCENTAGE.equalsIgnoreCase(strDepositType))
                                    dAmount = Math.floor(dAmount * recDeposit.getField(Deposit.QUANTITY).getValue() * 100) / 100;
                                ApTrx recApTrxNew = null;
                                try {
                                    Object bookmark = recApTrx.getLastModified(DBConstants.BOOKMARK_HANDLE);
                                    int iVendorID = (int)recApTrx.getField(ApTrx.VENDOR_ID).getValue();
                                    int iTourID = (int)recApTrx.getField(ApTrx.TOUR_ID).getValue();
                                    String strDesc = DBConstants.BLANK;
                                    if (((ReferenceField)recApTrx.getField(ApTrx.TOUR_ID)).getReference() != null)
                                        strDesc = ((ReferenceField)recApTrx.getField(ApTrx.TOUR_ID)).getReference().getField(TourModel.DESCRIPTION).toString();
                                    Date dateStartService = ((DateField)recApTrx.getField(ApTrx.START_SERVICE_DATE)).getDateTime();
                                    recApTrxNew = new ApTrx(this.getOwner().findRecordOwner());
                                    Object bookmarkPP = null;
                                    if (!recApTrx.getField(ApTrx.PREPAYMENT_AP_TRX_ID).isNull())
                                    {
                                        bookmarkPP = recApTrx.getField(ApTrx.PREPAYMENT_AP_TRX_ID).getData();
                                        if (recApTrxNew.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE) == null)
                                            return DBConstants.ERROR_RETURN;
                                        recApTrxNew.edit();
                                        if (iChangeType == DBConstants.AFTER_DELETE_TYPE)
                                        {
                                            recApTrxNew.remove();
                                            return DBConstants.NORMAL_RETURN;
                                        }
                                    }
                                    else
                                        recApTrxNew.addNew();
                                    if (iChangeType == DBConstants.AFTER_DELETE_TYPE)
                                        return DBConstants.NORMAL_RETURN;
                                    
                                    TrxStatus recTrxStatus = (TrxStatus)((ReferenceField)recApTrxNew.getField(ApTrx.TRX_STATUS_ID)).getReferenceRecord();
                                    int iTrxStatus = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.PREPAYMENT_REQUEST);
                                    if (recApTrxNew.getEditMode() != DBConstants.EDIT_ADD)
                                        if (recApTrxNew.getField(ApTrx.TRX_STATUS_ID).getValue() != iTrxStatus)
                                            return DBConstants.ERROR_RETURN;    // Prepayment request probably already paid!
                                    recApTrxNew.getField(ApTrx.TRX_STATUS_ID).setValue(iTrxStatus);
                                    recApTrxNew.getField(ApTrx.VENDOR_ID).setValue(iVendorID);
                                    PrepaymentAcctHandler listener = null;
                                    recApTrxNew.getField(ApTrx.TOUR_ID).addListener(listener = new PrepaymentAcctHandler(null));
                                    recApTrxNew.getField(ApTrx.TOUR_ID).setValue(iTourID);
                                    recApTrxNew.getField(ApTrx.TOUR_ID).removeListener(listener, true);
                                    ((DateField)recApTrxNew.getField(ApTrx.START_SERVICE_DATE)).setDate(dateStartService, bDisplayOption, DBConstants.SCREEN_MOVE);
                                    BaseApplication application = (BaseApplication)recApTrx.getTask().getApplication();
                                    String strPrepaymentFor = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString("Prepayment for");
                                    recApTrxNew.getField(ApTrx.DESCRIPTION).setString(strPrepaymentFor + ' ' + strDesc);
        
                                    recApTrxNew.getField(ApTrx.INVOICE_AMOUNT).setValue(dAmount);
                                    recApTrxNew.getField(ApTrx.AMOUNT_SELECTED).setValue(dAmount);
                                    recApTrxNew.getField(ApTrx.PREPAYMENT_AP_TRX_ID).setString(bookmark.toString());
                                    
                                    if (recApTrxNew.getEditMode() == DBConstants.EDIT_ADD)
                                    {
                                        recApTrxNew.add();
                                        bookmarkPP = recApTrxNew.getLastModified(DBConstants.BOOKMARK_HANDLE);
        
                                        recApTrx.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE);
                                        recApTrx.edit();
                                        recApTrx.getField(ApTrx.PREPAYMENT_AP_TRX_ID).setString(bookmarkPP.toString());
                                        recApTrx.set();
                                    }
                                    else
                                        recApTrxNew.set();
                                } catch (DBException ex) {
                                    ex.printStackTrace();
                                } finally {
                                    if (recApTrxNew != null)
                                        recApTrxNew.free();
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return iErrorCode;
    }

}
