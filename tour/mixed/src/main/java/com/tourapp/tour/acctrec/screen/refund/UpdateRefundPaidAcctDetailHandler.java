/**
 * @(#)UpdateRefundPaidAcctDetailHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.refund;

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
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.acctrec.screen.cash.*;
import com.tourapp.tour.assetdr.db.*;

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
        Record recScreenRecord = recordOwner.getScreenRecord();
        
        double dAmount = recArTrx.getField(ArTrx.kAmount).getValue();
        ReferenceField fldAccount = null;
        DateTimeField fldTrxDate = (DateTimeField)recScreenRecord.getField(RefundScreenRecord.kCheckDate);
        DateTimeField fldEntryDate = null;  // Now
        BaseField fldTrxID = recArTrx.getField(ArTrx.kID);
        int iUserID = Integer.parseInt(((MainApplication)this.getOwner().getRecordOwner().getTask().getApplication()).getUserID());
        if (recTransactionType.getField(recTransactionType.kTypicalBalance).getString().equals(Account.DEBIT))
        {
            fldAccount = (ReferenceField)this.getDrAccount();
            recAcctDetailDist.addDetailTrx(fldAccount, fldTrxDate, fldTrxID, recTransactionType, fldEntryDate, dAmount, iUserID, recAcctDetail, recPeriod);
        }
        else
        {
        // Step 2a - Create and write the bank transaction (in BankTrx).
            BankAcct recBankAcct = (BankAcct)((ReferenceField)recScreenRecord.getField(RefundScreenRecord.kBankAcctID)).getReference();
            BankTrx recBankTrx = (BankTrx)recordOwner.getRecord(BankTrx.kBankTrxFile);
            Record recBooking = ((ReferenceField)recArTrx.getField(ArTrx.kBookingID)).getReference();
            fldAccount = (ReferenceField)recBankAcct.getField(BankAcct.kAccountID);
            try   {
                recBankTrx.addNew();
                recBankTrx.getField(BankTrx.kTrxStatusID).moveFieldToThis(recArTrx.getField(ArTrx.kTrxStatusID));
                recBankTrx.getField(BankTrx.kTrxDate).moveFieldToThis(fldTrxDate);
                recBankTrx.getField(BankTrx.kAmountLocal).setValue(-dAmount);
                recBankTrx.getField(BankTrx.kTrxEntry).initField(DBConstants.DONT_DISPLAY);
                recBankTrx.getField(BankTrx.kBankAcctID).moveFieldToThis(recScreenRecord.getField(RefundScreenRecord.kBankAcctID));
                recBankTrx.getField(BankTrx.kAmount).setValue(-dAmount);
                recBankTrx.getField(BankTrx.kExchange).initField(DBConstants.DONT_DISPLAY);
                recBankTrx.getField(BankTrx.kComments).moveFieldToThis(recArTrx.getField(ArTrx.kComments));
                if (recBankTrx.getField(BankTrx.kComments).isNull())
                    recBankTrx.getField(BankTrx.kComments).moveFieldToThis(recTransactionType.getField(TransactionType.kGroupDesc));
                recBankTrx.getField(BankTrx.kTrxNumber).moveFieldToThis(recScreenRecord.getField(RefundScreenRecord.kCheckNo));
                recBankTrx.getField(BankTrx.kPayeeTrxDescID).moveFieldToThis(recTransactionType.getField(TransactionType.kTrxDescID));
                recBankTrx.getField(BankTrx.kPayeeID).moveFieldToThis(recBooking.getField(Booking.kProfileID));
                recBankTrx.getField(BankTrx.kPayeeName).moveFieldToThis(recBooking.getField(Booking.kGenericName));
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
        return (ReferenceField)this.getOwner().getRecordOwner().getRecord(ArControl.kArControlFile).getField(ArControl.kRefundSuspenseAccountID);
    }

}
