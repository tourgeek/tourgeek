/**
 * @(#)BaseArTrxPostScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.cash;

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
import com.tourapp.tour.assetdr.screen.batch.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctrec.db.event.*;

/**
 *  BaseArTrxPostScreen - Post A/R transactions.
 */
public class BaseArTrxPostScreen extends BaseTrxPostScreen
{
    /**
     * Default constructor.
     */
    public BaseArTrxPostScreen()
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
    public BaseArTrxPostScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Post A/R transactions";
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.addTrxSpecificListeners();
    }
    /**
     * AddTrxSpecificListeners Method.
     */
    public void addTrxSpecificListeners()
    {
        // Override this to add listeners for this type
    }
    /**
     * Post this CashBatchDist detail to the ArTrx file.
     */
    public boolean postDistTrx(BaseTrx recBaseTrx, double dLocalTotal, String strComment, BaseField fldDefAccountID)
    {
        boolean bSuccess = true;
        
        Record recCashBatchDist = this.getDistRecord();
        Record recTrxStatus = this.getRecord(TrxStatus.TRX_STATUS_FILE);
        ArTrx recArTrx = (ArTrx)this.getRecord(ArTrx.AR_TRX_FILE);
        recArTrx.getListener(UpdateArTrxAcctDetailHandler.class).setEnabledListener(false);
        
        try {
            double dLocalBalance = dLocalTotal;
            int iDistCount = 0;
            recCashBatchDist.close();
            while (recCashBatchDist.hasNext())
            {
                recCashBatchDist.next();
                iDistCount++;
            }
            int iCurrCount = 0;
            recCashBatchDist.close();
            while (recCashBatchDist.hasNext())
            {
                recCashBatchDist.next();
                iCurrCount++;
                if (recCashBatchDist.getField(CashBatchDist.BOOKING_ID).isNull())
                {   // Non-booking distribution
                    BaseField fldDistAccountID = recCashBatchDist.getField(CashBatchDist.ACCOUNT_ID);
                    if (fldDistAccountID == null)
                        fldDistAccountID = fldDefAccountID;
                    double dAmount = Math.abs(recCashBatchDist.getField(CashBatchDist.AMOUNT).getValue());
                    if (iCurrCount == iDistCount)
                        dAmount = dLocalBalance;
                    double dAmountUSD = dAmount;
                    if (Math.round((dLocalBalance - dAmount + 0.001) * 100) <= 0)
                        dAmountUSD = dLocalBalance;
                    dLocalBalance = dLocalBalance - dAmount;
                    dAmountUSD = -dAmountUSD;   // Negative = Credit
        
                    DateTimeField trxDate = (DateTimeField)recBaseTrx.getField(BaseTrx.TRX_DATE);
                    BaseField fldTrxID = null;  // No reference
                    TransactionType recTrxType = recBaseTrx.getTrxType(PostingType.OPTIONAL_POST);
                    DateTimeField trxEntryDate = (DateTimeField)recBaseTrx.getField(BaseTrx.TRX_ENTRY);
                    int iUserID = Integer.parseInt(((BaseApplication)this.getTask().getApplication()).getUserID());
                    AcctDetailDist recAcctDetailDist = (AcctDetailDist)recBaseTrx.getCachedRecord(AcctDetailDist.ACCT_DETAIL_DIST_FILE);
                    AcctDetail recAcctDetail = (AcctDetail)recBaseTrx.getCachedRecord(AcctDetail.ACCT_DETAIL_FILE);
                    Period recPeriod = (Period)recBaseTrx.getCachedRecord(Period.PERIOD_FILE);
                    bSuccess = recAcctDetailDist.addDetailTrx(fldDistAccountID, trxDate, fldTrxID, recTrxType, trxEntryDate, dAmountUSD, iUserID, recAcctDetail, recPeriod);
        
                    if (!bSuccess)
                        return bSuccess;
                }
                else
                {   // Booking distribution
                    recArTrx.addNew();
                    recArTrx.getField(ArTrx.TRX_DATE).moveFieldToThis(recBaseTrx.getField(BaseTrx.TRX_DATE));
                    recArTrx.getField(ArTrx.TRX_STATUS_ID).moveFieldToThis(recBaseTrx.getField(BaseTrx.TRX_STATUS_ID));
                    recArTrx.getField(ArTrx.TRX_ENTRY).initField(DBConstants.DONT_DISPLAY);
                    if ((strComment != null) && (strComment.length() > 0))
                        recArTrx.getField(ArTrx.COMMENTS).setString(strComment);
                    else
                        recArTrx.getField(ArTrx.COMMENTS).moveFieldToThis(recTrxStatus.getField(TrxStatus.STATUS_DESC));
                    ((ReferenceField)recArTrx.getField(ArTrx.LINKED_TRX_ID)).setReference(recBaseTrx, DBConstants.DONT_DISPLAY, DBConstants.INIT_MOVE);
                    // LinkedTrxDescID must be the source file's desc
                    Record recTrxDesc = recBaseTrx.getTrxDesc(null);    // Typically this is it.
                    Record recTrxType = recBaseTrx.getTrxType(PostingType.TRX_POST);
                    if (!recTrxType.getField(TransactionType.SOURCE_FILE).equals(recTrxDesc.getField(TrxDesc.SOURCE_FILE)))
                    {
                        Object bookmark = recTrxDesc.getHandle(DBConstants.BOOKMARK_HANDLE);
                        int iOldKey = recTrxDesc.getDefaultOrder();
                        recTrxDesc.setKeyArea(TrxDesc.SOURCE_FILE_KEY);
                        recTrxDesc.getField(TrxDesc.SOURCE_FILE).moveFieldToThis(recTrxType.getField(TransactionType.SOURCE_FILE));
                        if (!recTrxDesc.seek(DBConstants.EQUALS))    // This is the correct source desc
                        {   // If not, use the orig
                            recTrxDesc.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE);
                        }
                        recTrxDesc.setKeyArea(iOldKey);
                    }
                    recArTrx.getField(ArTrx.LINKED_TRX_DESC_ID).moveFieldToThis(recTrxDesc.getField(TrxDesc.ID));
        
                    recArTrx.getField(ArTrx.BOOKING_ID).moveFieldToThis(recCashBatchDist.getField(CashBatchDist.BOOKING_ID));
                    double dAmount = Math.abs(recCashBatchDist.getField(CashBatchDist.AMOUNT).getValue());
                    if (iCurrCount == iDistCount)
                        dAmount = dLocalBalance;
                    double dAmountUSD = dAmount;
                    if (Math.round((dLocalBalance - dAmount + 0.001) * 100) <= 0)
                        dAmountUSD = dLocalBalance;
                    dLocalBalance = dLocalBalance - dAmount;
                    dAmountUSD = -dAmountUSD;   // Credit
                    recArTrx.getField(ArTrx.AMOUNT).setValue(dAmountUSD); // Subtract from booking balance
                    bSuccess = recArTrx.onPostTrx();
                    if (!bSuccess)
                        return bSuccess;
                    BaseField fldCrAccountID = recCashBatchDist.getField(CashBatchDist.ACCOUNT_ID);   // Default
                    Record recBooking = ((ReferenceField)recCashBatchDist.getField(CashBatchDist.BOOKING_ID)).getReference();
                    if (recBooking != null)
                    {
                        Record recTour = ((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReference();
                        if (recTour != null)
                        {
                            Record recTourHeader = ((ReferenceField)recTour.getField(Tour.TOUR_HEADER_ID)).getReference();
                            if (recTourHeader != null)
                            {
                                Record recProductCat = ((ReferenceField)recTourHeader.getField(TourHeader.PRODUCT_CAT_ID)).getReference();
                                if (recProductCat != null)
                                    fldCrAccountID = recProductCat.getField(ProductCategory.AR_ACCOUNT_ID);
                            }
                        }
                    }
                    if ((fldCrAccountID == null) || (fldCrAccountID.isNull()))
                        fldCrAccountID = fldDefAccountID;
                    AcctDetailDist recAcctDetailDist = (AcctDetailDist)recBaseTrx.getCachedRecord(AcctDetailDist.ACCT_DETAIL_DIST_FILE);
                    AcctDetail recAcctDetail = (AcctDetail)recBaseTrx.getCachedRecord(AcctDetail.ACCT_DETAIL_FILE);
                    Period recPeriod = (Period)recBaseTrx.getCachedRecord(Period.PERIOD_FILE);
                    bSuccess = recArTrx.onPostTrxDist(fldCrAccountID, dAmountUSD, PostingType.DIST_POST, recAcctDetail, recAcctDetailDist, recPeriod);
                    if (!bSuccess)
                        return bSuccess;
                }
            }
            if (Math.round((dLocalBalance + 0.001) * 100) != 0)
            {   // Send the rest of this dist to the default account
                double dAmountUSD = -dLocalBalance;   // Credit
        
                DateTimeField trxDate = (DateTimeField)recBaseTrx.getField(BaseTrx.TRX_DATE);
                BaseField fldTrxID = null;  // No reference
                TransactionType recTrxType = recBaseTrx.getTrxType(PostingType.OPTIONAL_POST);
                DateTimeField trxEntryDate = (DateTimeField)recBaseTrx.getField(BaseTrx.TRX_ENTRY);
                int iUserID = Integer.parseInt(((BaseApplication)this.getTask().getApplication()).getUserID());
                AcctDetailDist recAcctDetailDist = (AcctDetailDist)recBaseTrx.getCachedRecord(AcctDetailDist.ACCT_DETAIL_DIST_FILE);
                AcctDetail recAcctDetail = (AcctDetail)recBaseTrx.getCachedRecord(AcctDetail.ACCT_DETAIL_FILE);
                Period recPeriod = (Period)recBaseTrx.getCachedRecord(Period.PERIOD_FILE);
                bSuccess = recAcctDetailDist.addDetailTrx(fldDefAccountID, trxDate, fldTrxID, recTrxType, trxEntryDate, dAmountUSD, iUserID, recAcctDetail, recPeriod);
        
                if (!bSuccess)
                    return bSuccess;
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
            return false;
        } finally {
            recArTrx.getListener(UpdateArTrxAcctDetailHandler.class).setEnabledListener(true);
        }
        return bSuccess;
    }

}
