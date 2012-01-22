/**
 * @(#)UpdateApTrxHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.trx;

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
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.tour.db.*;

/**
 *  UpdateApTrxHandler - Base class to do A/P G/L Updates for A/P Transactions.
 */
public class UpdateApTrxHandler extends UpdateAcctDetailHandler
{
    protected int m_iOrigTrxStatusID = 0;
    protected ApControl m_recApControl = null;
    /**
     * Default constructor.
     */
    public UpdateApTrxHandler()
    {
        super();
    }
    /**
     * UpdateApTrxHandler Method.
     */
    public UpdateApTrxHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        m_recApControl = null;
        super.init(record);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recApControl != null)
            m_recApControl.free();
        m_recApControl = null;
        super.free();
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
        Record recApTrx = this.getOwner();
        double dAmount = this.getTrxAmount(recTransactionType.getField(TransactionType.kTypicalBalance));
        if (recTransactionType.getField(TransactionType.kSourcePreferredSign).getString().equals(PreferredSignField.NEGATIVE))
            dAmount = -dAmount;
        ReferenceField fldAccount = null;
        if (recTransactionType.getField(TransactionType.kTypicalBalance).getString().equals(Account.DEBIT))
            fldAccount = (ReferenceField)this.getDrAccount();
        else if (recTransactionType.getField(TransactionType.kTypicalBalance).getString().equals(Account.CREDIT))
        {
            fldAccount = (ReferenceField)this.getCrAccount();
            dAmount = -dAmount;
        }
        else
        {
            fldAccount = (ReferenceField)this.getDiffAccount();
            if (fldAccount == null)
                return;
        }
        dAmount = dAmount - dCurrentBalance;
        DateTimeField fldTrxDate = (DateTimeField)this.getTrxDate();
        DateTimeField fldEntryDate = null;  // Now
        BaseField fldTrxID = recApTrx.getField(ApTrx.kID);
        String strUserID = ((BaseApplication)this.getOwner().getRecordOwner().getTask().getApplication()).getUserID();
        if (strUserID == null)
            strUserID = "0";    // No user/System user
        int iUserID = Integer.parseInt(strUserID);
        recAcctDetailDist.addDetailTrx(fldAccount, fldTrxDate, fldTrxID, recTransactionType, fldEntryDate, dAmount, iUserID, recAcctDetail, recPeriod);
    }
    /**
     * Get the transaction amount for this type of transaction.
     * @param fldTypicalBalance The typical balance field (Debit/Credit/none).
     * @return The transaction amount.
     */
    public double getTrxAmount(BaseField fldTypicalBalance)
    {
        return 0;   // Override this
    }
    /**
     * Get the differential account (Cost Over/Under) for this type of trx.
     * @return The field that contains the differential account.
     */
    public ReferenceField getDiffAccount()
    {
        return null;
    }
    /**
     * Get the transaction date.
     * @return The transaction date for this type of transaction.
     */
    public BaseField getTrxDate()
    {
        return null;    // Override this
    }
    /**
     * Utility method to get the product category record for this trx.
     * (The product category contains many of the G/L accounts).
     * @return The product category record for this trx.
     */
    public ProductCategory getProductCategory()
    {
        // ApTrx->Tour->TourHeader->ProductCat P/P  vs  A/R
        Record recApTrx = this.getOwner();
        if (recApTrx.getField(ApTrx.kTourID).isNull())
            return null;    // No tour
        Tour recTour = (Tour)((ReferenceField)recApTrx.getField(ApTrx.kTourID)).getReference();
        TourHeader recTourHeader = (TourHeader)((ReferenceField)recTour.getField(Tour.kTourHeaderID)).getReference();
        ProductCategory recProductCategory = (ProductCategory)((ReferenceField)recTourHeader.getField(TourHeader.kProductCatID)).getReference();
        return recProductCategory;
    }
    /**
     * Utility method to get the invoice amount and convert it to USD
     * using the last exchange rate in the currency file.
     * @return The invoice amount converted to USD.
     */
    public double getInvoiceAmtUSD()
    {
        Record recApTrx = this.getOwner();
        double dAmountUSD = 0;
        Vendor recVendor = (Vendor)((ReferenceField)recApTrx.getField(ApTrx.kVendorID)).getReference();
        Currencys recCurrencys = null;
        if (recVendor != null)
            recCurrencys = (Currencys)((ReferenceField)recVendor.getField(Vendor.kCurrencysID)).getReference();
        double dExchange = 1;
        if (recCurrencys != null)
            dExchange = recCurrencys.getField(Currencys.kLastRate).getValue();
        double dAmount = recApTrx.getField(ApTrx.kInvoiceAmount).getValue();
        dAmountUSD = (Math.floor(dAmount * dExchange * 100 + 0.5)) / 100;
        return dAmountUSD;
    }
    /**
     * Support method for quickly getting the A/P control file.
     */
    public Record getApControl()
    {
        Record recApControl = null;
        RecordOwner recordOwner = Utility.getRecordOwner(this.getOwner());
        if (recordOwner != null)
            recApControl = recordOwner.getRecord(ApControl.kApControlFile);
        if (recApControl == null)
        {
            recApControl = m_recApControl = new ApControl(recordOwner);
            if (recordOwner != null)
                recordOwner.removeRecord(m_recApControl);
        }
        return recApControl;
    }
    /**
     * New record = unknown trx id.
     */
    public void doNewRecord(boolean bDisplayOption)
    {
        m_iOrigTrxStatusID = 0;
        super.doNewRecord(bDisplayOption);
    }
    /**
     * Read a valid record = this is the orig trx status ID.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        m_iOrigTrxStatusID = (int)this.getOwner().getField(ApTrx.kTrxStatusID).getValue();
        super.doValidRecord(bDisplayOption);
    }
    /**
     * Utility to get the trx status ID for this code.
     */
    public int getTrxStatusID(String strTrxStatus)
    {
        return this.getTrxStatus().getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, strTrxStatus);
    }

}
