/**
 *  @(#)BankTrxBatchPost.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.screen.batch;

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
import org.jbundle.main.screen.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.assetdr.screen.trx.*;
import com.tourapp.tour.assetdr.screen.batch.dist.*;
import com.tourapp.tour.genled.db.*;

/**
 *  BankTrxBatchPost - .
 */
public class BankTrxBatchPost extends BaseTrxPostScreen
{
    /**
     * Default constructor.
     */
    public BankTrxBatchPost()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public BankTrxBatchPost(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        if (this.getMainRecord().getTableNames(false).equalsIgnoreCase(BankTrxBatchDetail.kBankTrxBatchDetailFile))
            this.addRecord(((ReferenceField)this.getMainRecord().getField(BankTrxBatchDetail.kBankTrxBatchID)).getReference(), true);
        else
            new BankTrxBatchDetail(this);
        new AssetDrControl(this);
        
        new BankTrx(this);
        
        new BankTrxBatchDist(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new BankTrxScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        BankTrxBatchDetail recBankTrxBatchDetail = (BankTrxBatchDetail)this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile);
        recBankTrxBatchDetail.addListener(new SubFileFilter(this.getMainRecord()));
        recBankTrxBatchDetail.addListener(new SubCountHandler(this.getScreenRecord().getField(BankTrxScreenRecord.kEndBalance), BankTrxBatchDetail.kAmount, false, true));
        recBankTrxBatchDetail.addListener(new SubCountHandler(this.getScreenRecord().getField(BankTrxScreenRecord.kTrxCount), false, true));
        
        BankTrxBatchDist recBankTrxBatchDist = (BankTrxBatchDist)this.getRecord(BankTrxBatchDist.kBankTrxBatchDistFile);
        recBankTrxBatchDist.addListener(new SubFileFilter(this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile)));
        recBankTrxBatchDist.addListener(new SubCountHandler(this.getScreenRecord().getField(BankTrxScreenRecord.kChangeBalance), BankTrxBatchDist.kAmount, false, true));
        
        try {
            recBankTrxBatchDetail.close();
            while (recBankTrxBatchDetail.hasNext())
            {
                recBankTrxBatchDetail.next();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(BankTrxBatch.kBankTrxBatchFile).getField(BankTrxBatch.kBankAcctID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxScreenRecord.kBankTrxScreenRecordFile).getField(BankTrxScreenRecord.kEndBalance).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxScreenRecord.kBankTrxScreenRecordFile).getField(BankTrxScreenRecord.kTrxCount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.setEnabled(false);
        SCannedBox box = new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
        box.setEnabled(true);
    }
    /**
     * Make sure this batch detail trx is valid.
     * @return True if batch is okay.
     */
    public boolean checkValidDetail()
    {
        boolean bSuccess = super.checkValidDetail();
        Record recBankTrxBatchDetail = this.getDetailRecord();
        if (bSuccess)
            if (recBankTrxBatchDetail.getField(BankTrxBatchDetail.kTrxDate).isNull())
        {
            BaseApplication application = (BaseApplication)this.getTask().getApplication();
            this.displayError(application.getResources(ResourceConstants.ASSETDR_RESOURCE, true).getString("You must have a transaction date, can't post"));
            return false;
        }
        return bSuccess;
    }
    /**
     * Is the batch header record valid?
     * @return true if valid (if false, set the last error).
     */
    public boolean checkValidHeader()
    {
        boolean bSuccess = super.checkValidHeader();
        if (!bSuccess)
            return bSuccess;
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        BankAcct recBankAcct = (BankAcct)((ReferenceField)this.getMainRecord().getField(BankTrxBatch.kBankAcctID)).getReference();
        if (recBankAcct == null)
        {
            this.displayError(application.getResources(ResourceConstants.ASSETDR_RESOURCE, true).getString("Invalid Bank account, can't post"));
            return false;
        }
        BaseField fldDefAccountID = this.getRecord(AssetDrControl.kAssetDrControlFile).getField(AssetDrControl.kAccountID);
        if (fldDefAccountID.isNull())
        {
            this.displayError(application.getResources(ResourceConstants.ASSETDR_RESOURCE, true).getString("No default account set in control file"));
            return false;
        }
        return true;    // Success
    }
    /**
     * Get the base trx record.
     * @return The record.
     */
    public BaseTrx getBaseTrx()
    {
        return (BaseTrx)this.getRecord(BankTrx.kBankTrxFile);
    }
    /**
     * Get the batch detail record.
     */
    public Record getDetailRecord()
    {
        return this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile);
    }
    /**
     * Return the distribution detail record.
     * @return The dist record.
     */
    public Record getDistRecord()
    {
        return this.getRecord(BankTrxBatchDist.kBankTrxBatchDistFile);
    }
    /**
     * Setup and post this base transaction.
     * @param recBaseTrx The base transaction to post.
     * @param recTransactionType The transaction type for the TRX posting.
     * @return true If successful.
     */
    public boolean postBaseTrx(BaseTrx recBaseTrx, TransactionType recTransactionType)
    {
        BankAcct recBankAcct = (BankAcct)((ReferenceField)this.getMainRecord().getField(BankTrxBatch.kBankAcctID)).getReference();
        Record recBankTrxBatchDetail = this.getDetailRecord();
        BaseField fldDrAccountID = recBankAcct.getField(BankAcct.kAccountID);
        try   {
        // Step 2a - Create and write the bank transaction (in BankTrx).
            recBaseTrx.addNew();
            for (int iFieldSeq = BankTrx.kID + 1; iFieldSeq < BankTrx.kBankTrxLastField; iFieldSeq++)
            {
                recBaseTrx.getField(iFieldSeq).moveFieldToThis(recBankTrxBatchDetail.getField(iFieldSeq));
            }
            recBaseTrx.getField(BankTrx.kBankAcctID).moveFieldToThis(this.getRecord(BankTrxBatch.kBankTrxBatchFile).getField(BankTrxBatch.kBankAcctID));
            ((BankTrx)recBaseTrx).calcUSDAmounts(true);
            boolean bSuccess = recBaseTrx.onPost(fldDrAccountID, null);
            return bSuccess;
        } catch (DBException ex)    {
            ex.printStackTrace();
            return false;
        }
    }
    /**
     * Post the distribution detail.
     * @param recBaseTrx The base transaction to post.
     * @param recTransactionType The transaction type for the DIST posting.
     * @return true If successful.
     */
    public boolean postDistTrx(BaseTrx recBaseTrx, TransactionType recTransactionType)
    {
        boolean bSuccess = true;
        Record recBankTrxBatchDist = this.getDistRecord();
        BankAcct recBankAcct = (BankAcct)((ReferenceField)this.getMainRecord().getField(BankTrxBatch.kBankAcctID)).getReference();
        BaseField fldDefAccountID = this.getRecord(AssetDrControl.kAssetDrControlFile).getField(AssetDrControl.kAccountID);
        try {
            TransactionType recTrxType = recBaseTrx.getTrxType(PostingType.DIST_POST);
            double dTotalLocal = recBaseTrx.getField(BankTrx.kAmount).getValue();
            int iSign = +1;
            if (dTotalLocal > 0)
                iSign = -iSign; // Should have the opposite sign
            dTotalLocal = Math.abs(dTotalLocal);
            double dTotalUSD = Math.abs(recBaseTrx.getField(BankTrx.kAmountLocal).getValue());
            double dExchange = 1.0;
            if (dTotalLocal != 0)
                dExchange = dTotalUSD / dTotalLocal;
            if (dExchange <= 0)
                dExchange = 1.0;
            double dBalanceLocal = dTotalLocal;
            double dBalanceUSD = dTotalUSD;
            recBankTrxBatchDist.close();
            while (recBankTrxBatchDist.hasNext())
            {
                recBankTrxBatchDist.next();
                BaseField fldDistAccountID = recBankTrxBatchDist.getField(BankTrxBatchDist.kAccountID);
                double dAmountLocal = recBankTrxBatchDist.getField(BankTrxBatchDist.kAmount).getValue();
                dAmountLocal = Math.abs(dAmountLocal);      // Should have same sign as the total
                dBalanceLocal = dBalanceLocal - dAmountLocal;
        
                double dNewBalanceUSD = Math.floor(dBalanceLocal * dExchange * 100.00 + 0.5) / 100.00;
                double dAmountUSD = dBalanceUSD - dNewBalanceUSD;
                dBalanceUSD = dBalanceUSD - dAmountUSD;
                bSuccess = recBaseTrx.onPostTrxDist(fldDistAccountID, dAmountUSD * iSign, PostingType.DIST_POST);
                if (!bSuccess)
                    return bSuccess;
            }
            if (Math.round((dBalanceLocal + 0.001) * 100) != 0)
            {   // Send the rest of this dist to the default account
                bSuccess = recBaseTrx.onPostTrxDist(fldDefAccountID, dBalanceUSD * iSign, PostingType.DIST_POST);
                if (!bSuccess)
                    return bSuccess;
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

}
