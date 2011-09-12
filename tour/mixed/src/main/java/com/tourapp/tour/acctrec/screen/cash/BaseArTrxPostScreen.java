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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
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
        Record recTrxStatus = this.getRecord(TrxStatus.kTrxStatusFile);
        ArTrx recArTrx = (ArTrx)this.getRecord(ArTrx.kArTrxFile);
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
                if (recCashBatchDist.getField(CashBatchDist.kBookingID).isNull())
                {   // Non-booking distribution
                    BaseField fldDistAccountID = recCashBatchDist.getField(CashBatchDist.kAccountID);
                    if (fldDistAccountID == null)
                        fldDistAccountID = fldDefAccountID;
                    double dAmount = Math.abs(recCashBatchDist.getField(CashBatchDist.kAmount).getValue());
                    if (iCurrCount == iDistCount)
                        dAmount = dLocalBalance;
                    double dAmountUSD = dAmount;
                    if (Math.round((dLocalBalance - dAmount + 0.001) * 100) <= 0)
                        dAmountUSD = dLocalBalance;
                    dLocalBalance = dLocalBalance - dAmount;
                    dAmountUSD = -dAmountUSD;   // Negative = Credit
        
                    DateTimeField trxDate = (DateTimeField)recBaseTrx.getField(BaseTrx.kTrxDate);
                    BaseField fldTrxID = null;  // No reference
                    TransactionType recTrxType = recBaseTrx.getTrxType(PostingType.OPTIONAL_POST);
                    DateTimeField trxEntryDate = (DateTimeField)recBaseTrx.getField(BaseTrx.kTrxEntry);
                    int iUserID = Integer.parseInt(((BaseApplication)this.getTask().getApplication()).getUserID());
                    AcctDetailDist recAcctDetailDist = (AcctDetailDist)recBaseTrx.getCachedRecord(AcctDetailDist.kAcctDetailDistFile);
                    AcctDetail recAcctDetail = (AcctDetail)recBaseTrx.getCachedRecord(AcctDetail.kAcctDetailFile);
                    Period recPeriod = (Period)recBaseTrx.getCachedRecord(Period.kPeriodFile);
                    bSuccess = recAcctDetailDist.addDetailTrx(fldDistAccountID, trxDate, fldTrxID, recTrxType, trxEntryDate, dAmountUSD, iUserID, recAcctDetail, recPeriod);
        
                    if (!bSuccess)
                        return bSuccess;
                }
                else
                {   // Booking distribution
                    recArTrx.addNew();
                    recArTrx.getField(ArTrx.kTrxDate).moveFieldToThis(recBaseTrx.getField(BaseTrx.kTrxDate));
                    recArTrx.getField(ArTrx.kTrxStatusID).moveFieldToThis(recBaseTrx.getField(BaseTrx.kTrxStatusID));
                    recArTrx.getField(ArTrx.kTrxEntry).initField(DBConstants.DONT_DISPLAY);
                    if ((strComment != null) && (strComment.length() > 0))
                        recArTrx.getField(ArTrx.kComments).setString(strComment);
                    else
                        recArTrx.getField(ArTrx.kComments).moveFieldToThis(recTrxStatus.getField(TrxStatus.kStatusDesc));
                    ((ReferenceField)recArTrx.getField(ArTrx.kLinkedTrxID)).setReference(recBaseTrx, DBConstants.DONT_DISPLAY, DBConstants.INIT_MOVE);
                    // LinkedTrxDescID must be the source file's desc
                    Record recTrxDesc = recBaseTrx.getTrxDesc(null);    // Typically this is it.
                    Record recTrxType = recBaseTrx.getTrxType(PostingType.TRX_POST);
                    if (!recTrxType.getField(TransactionType.kSourceFile).equals(recTrxDesc.getField(TrxDesc.kSourceFile)))
                    {
                        Object bookmark = recTrxDesc.getHandle(DBConstants.BOOKMARK_HANDLE);
                        int iOldKey = recTrxDesc.getDefaultOrder();
                        recTrxDesc.setKeyArea(TrxDesc.kSourceFileKey);
                        recTrxDesc.getField(TrxDesc.kSourceFile).moveFieldToThis(recTrxType.getField(TransactionType.kSourceFile));
                        if (!recTrxDesc.seek(DBConstants.EQUALS))    // This is the correct source desc
                        {   // If not, use the orig
                            recTrxDesc.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE);
                        }
                        recTrxDesc.setKeyArea(iOldKey);
                    }
                    recArTrx.getField(ArTrx.kLinkedTrxDescID).moveFieldToThis(recTrxDesc.getField(TrxDesc.kID));
        
                    recArTrx.getField(ArTrx.kBookingID).moveFieldToThis(recCashBatchDist.getField(CashBatchDist.kBookingID));
                    double dAmount = Math.abs(recCashBatchDist.getField(CashBatchDist.kAmount).getValue());
                    if (iCurrCount == iDistCount)
                        dAmount = dLocalBalance;
                    double dAmountUSD = dAmount;
                    if (Math.round((dLocalBalance - dAmount + 0.001) * 100) <= 0)
                        dAmountUSD = dLocalBalance;
                    dLocalBalance = dLocalBalance - dAmount;
                    dAmountUSD = -dAmountUSD;   // Credit
                    recArTrx.getField(ArTrx.kAmount).setValue(dAmountUSD); // Subtract from booking balance
                    bSuccess = recArTrx.onPostTrx();
                    if (!bSuccess)
                        return bSuccess;
                    BaseField fldCrAccountID = recCashBatchDist.getField(CashBatchDist.kAccountID);   // Default
                    Record recBooking = ((ReferenceField)recCashBatchDist.getField(CashBatchDist.kBookingID)).getReference();
                    if (recBooking != null)
                    {
                        Record recTour = ((ReferenceField)recBooking.getField(Booking.kTourID)).getReference();
                        if (recTour != null)
                        {
                            Record recTourHeader = ((ReferenceField)recTour.getField(Tour.kTourHeaderID)).getReference();
                            if (recTourHeader != null)
                            {
                                Record recProductCat = ((ReferenceField)recTourHeader.getField(TourHeader.kProductCatID)).getReference();
                                if (recProductCat != null)
                                    fldCrAccountID = recProductCat.getField(ProductCategory.kArAccountID);
                            }
                        }
                    }
                    if ((fldCrAccountID == null) || (fldCrAccountID.isNull()))
                        fldCrAccountID = fldDefAccountID;
                    AcctDetailDist recAcctDetailDist = (AcctDetailDist)recBaseTrx.getCachedRecord(AcctDetailDist.kAcctDetailDistFile);
                    AcctDetail recAcctDetail = (AcctDetail)recBaseTrx.getCachedRecord(AcctDetail.kAcctDetailFile);
                    Period recPeriod = (Period)recBaseTrx.getCachedRecord(Period.kPeriodFile);
                    bSuccess = recArTrx.onPostTrxDist(fldCrAccountID, dAmountUSD, PostingType.DIST_POST, recAcctDetail, recAcctDetailDist, recPeriod);
                    if (!bSuccess)
                        return bSuccess;
                }
            }
            if (Math.round((dLocalBalance + 0.001) * 100) != 0)
            {   // Send the rest of this dist to the default account
                double dAmountUSD = -dLocalBalance;   // Credit
        
                DateTimeField trxDate = (DateTimeField)recBaseTrx.getField(BaseTrx.kTrxDate);
                BaseField fldTrxID = null;  // No reference
                TransactionType recTrxType = recBaseTrx.getTrxType(PostingType.OPTIONAL_POST);
                DateTimeField trxEntryDate = (DateTimeField)recBaseTrx.getField(BaseTrx.kTrxEntry);
                int iUserID = Integer.parseInt(((BaseApplication)this.getTask().getApplication()).getUserID());
                AcctDetailDist recAcctDetailDist = (AcctDetailDist)recBaseTrx.getCachedRecord(AcctDetailDist.kAcctDetailDistFile);
                AcctDetail recAcctDetail = (AcctDetail)recBaseTrx.getCachedRecord(AcctDetail.kAcctDetailFile);
                Period recPeriod = (Period)recBaseTrx.getCachedRecord(Period.kPeriodFile);
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
