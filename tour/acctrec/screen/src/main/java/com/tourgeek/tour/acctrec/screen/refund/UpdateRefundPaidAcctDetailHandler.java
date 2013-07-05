/**
  * @(#)UpdateRefundPaidAcctDetailHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctrec.screen.refund;

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
import org.jbundle.base.screen.model.report.*;
import com.tourgeek.tour.acctrec.db.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.genled.db.*;
import com.tourgeek.tour.booking.detail.db.*;
import com.tourgeek.tour.acctrec.screen.cash.*;
import com.tourgeek.tour.assetdr.db.*;
import org.jbundle.base.screen.model.util.*;

/**
 *  UpdateRefundPaidAcctDetailHandler - After printing the Refund checks, post the ArTrx to BankTrx and G/L.
 */
public class UpdateRefundPaidAcctDetailHandler extends UpdateAcctDetailHandler
{
    /**
     * Default constructor.
     */
    public UpdateRefundPaidAcctDetailHandler()
    {
        super();
    }
    /**
     * UpdateRefundPaidAcctDetailHandler Method.
     */
    public UpdateRefundPaidAcctDetailHandler(Record record)
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
     * Override this method and call recAcctDetailDist.addDetailTrx(...) to
     * add the G/L transaction.
     * @param recTransactionType TransactionType record
     * @param recAcctDetailDist AcctDetailDist record
     * @param recAcctDetail AcctDetail record
     * @param recPeriod Period record
     * @param dCurrentBalance The current balance for this type of transaction.
     */
    public void addDetailTrx(TransactionType recTransactionType, AcctDetailDist recAcctDetailDist, AcctDetail recAcctDetail, Period recPeriod, double dCurrentBalance)
    {
        Record recArTrx = this.getOwner();
        RecordOwner recordOwner = recArTrx.getRecordOwner();
        Record recScreenRecord = (Record)recordOwner.getScreenRecord();
        
        double dAmount = recArTrx.getField(ArTrx.AMOUNT).getValue();
        ReferenceField fldAccount = null;
        DateTimeField fldTrxDate = (DateTimeField)recScreenRecord.getField(RefundScreenRecord.CHECK_DATE);
        DateTimeField fldEntryDate = null;  // Now
        BaseField fldTrxID = recArTrx.getField(ArTrx.ID);
        int iUserID = Integer.parseInt(((MainApplication)this.getOwner().getRecordOwner().getTask().getApplication()).getUserID());
        if (recTransactionType.getField(recTransactionType.TYPICAL_BALANCE).getString().equals(Account.DEBIT))
        {
            fldAccount = (ReferenceField)this.getDrAccount();
            recAcctDetailDist.addDetailTrx(fldAccount, fldTrxDate, fldTrxID, recTransactionType, fldEntryDate, dAmount, iUserID, recAcctDetail, recPeriod);
        }
        else
        {
        // Step 2a - Create and write the bank transaction (in BankTrx).
            BankAcct recBankAcct = (BankAcct)((ReferenceField)recScreenRecord.getField(RefundScreenRecord.BANK_ACCT_ID)).getReference();
            BankTrx recBankTrx = (BankTrx)recordOwner.getRecord(BankTrx.BANK_TRX_FILE);
            Record recBooking = ((ReferenceField)recArTrx.getField(ArTrx.BOOKING_ID)).getReference();
            fldAccount = (ReferenceField)recBankAcct.getField(BankAcct.ACCOUNT_ID);
            try   {
                recBankTrx.addNew();
                recBankTrx.getField(BankTrx.TRX_STATUS_ID).moveFieldToThis(recArTrx.getField(ArTrx.TRX_STATUS_ID));
                recBankTrx.getField(BankTrx.TRX_DATE).moveFieldToThis(fldTrxDate);
                recBankTrx.getField(BankTrx.AMOUNT_LOCAL).setValue(-dAmount);
                recBankTrx.getField(BankTrx.TRX_ENTRY).initField(DBConstants.DONT_DISPLAY);
                recBankTrx.getField(BankTrx.BANK_ACCT_ID).moveFieldToThis(recScreenRecord.getField(RefundScreenRecord.BANK_ACCT_ID));
                recBankTrx.getField(BankTrx.AMOUNT).setValue(-dAmount);
                recBankTrx.getField(BankTrx.EXCHANGE).initField(DBConstants.DONT_DISPLAY);
                recBankTrx.getField(BankTrx.COMMENTS).moveFieldToThis(recArTrx.getField(ArTrx.COMMENTS));
                if (recBankTrx.getField(BankTrx.COMMENTS).isNull())
                    recBankTrx.getField(BankTrx.COMMENTS).moveFieldToThis(recTransactionType.getField(TransactionType.GROUP_DESC));
                recBankTrx.getField(BankTrx.TRX_NUMBER).moveFieldToThis(recScreenRecord.getField(RefundScreenRecord.CHECK_NO));
                recBankTrx.getField(BankTrx.PAYEE_TRX_DESC_ID).moveFieldToThis(recTransactionType.getField(TransactionType.TRX_DESC_ID));
                recBankTrx.getField(BankTrx.PAYEE_ID).moveFieldToThis(recBooking.getField(Booking.PROFILE_ID));
                recBankTrx.getField(BankTrx.PAYEE_NAME).moveFieldToThis(recBooking.getField(Booking.GENERIC_NAME));
                // Step 2 - Post it to the G/L
                //+recAcctDetail.getDatabase().startTrx();
                // Step 2a - Create and write the bank transaction (in BankTrx).
                boolean bSuccess = recBankTrx.onPostTrx();
                //?if (!bSuccess)
                    //?return bSuccess;
                // Step 2b - Post the transaction side of the distribution.
                bSuccess = recBankTrx.onPostTrxDist(fldAccount, -dAmount, PostingType.DIST_POST, recAcctDetail, recAcctDetailDist, recPeriod);
                //?if (!bSuccess)
                    //?return bSuccess;
                //?recAcctDetailDist.addDetailTrx(fldAccount, fldTrxDate, fldTrxTypeID, recTransactionType, fldEntryDate, dAmount, iUserID, recAcctDetail, recPeriod);
            } catch (DBException ex)    {
                ex.printStackTrace();
            }
        }
    }
    /**
     * Get the Debit Account field.
     * @return The debit account field.
     */
    public ReferenceField getDrAccount()
    {
        return (ReferenceField)this.getOwner().getRecordOwner().getRecord(ArControl.AR_CONTROL_FILE).getField(ArControl.REFUND_SUSPENSE_ACCOUNT_ID);
    }

}
