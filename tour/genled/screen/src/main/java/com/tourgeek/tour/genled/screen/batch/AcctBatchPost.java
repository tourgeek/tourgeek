
package com.tourgeek.tour.genled.screen.batch;

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
import com.tourgeek.tour.genled.db.*;
import org.jbundle.main.screen.*;
import java.util.*;
import org.jbundle.thin.base.screen.*;

/**
 *  AcctBatchPost - Post the account detail.
 */
public class AcctBatchPost extends AcctBatchScreen
{
    /**
     * Default constructor.
     */
    public AcctBatchPost()
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
    public AcctBatchPost(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Post the account detail";
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new AcctBatchDetail(this);
        new AcctDetail(this);
        new AcctDetailDist(this);
        new TransactionType(this);
        new Period(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getMainRecord().getField(AcctBatch.RECURRING).removeListener((FieldListener)this.getMainRecord().getField(AcctBatch.RECURRING).getListener(DisableOnFieldHandler.class.getName()), true);
        this.getMainRecord().getField(AcctBatch.TRX_DATE).removeListener((FieldListener)this.getMainRecord().getField(AcctBatch.TRX_DATE).getListener(CopyStringHandler.class.getName()), true);
        if (this.getMainRecord().getField(AcctBatch.RECURRING).getState())
            this.getMainRecord().getField(AcctBatch.TRX_DATE).initField(true);
        
        this.getRecord(AcctBatchDetail.ACCT_BATCH_DETAIL_FILE).addListener(new SubFileFilter(this.getRecord(AcctBatch.ACCT_BATCH_FILE)));
        this.getRecord(AcctBatchDetail.ACCT_BATCH_DETAIL_FILE).addListener(new SubCountHandler(this.getRecord(AcctBatch.ACCT_BATCH_FILE).getField(AcctBatch.BALANCE), AcctBatchDetail.AMOUNT, false, true));
        this.getRecord(AcctBatchDetail.ACCT_BATCH_DETAIL_FILE).addListener(new BatchSequenceHandler(this.getRecord(AcctBatchDetail.ACCT_BATCH_DETAIL_FILE).getField(AcctBatchDetail.SEQUENCE), this.getRecord(AcctBatch.ACCT_BATCH_FILE).getField(AcctBatch.NEXT_SEQUENCE), this.getRecord(AcctBatch.ACCT_BATCH_FILE).getField(AcctBatch.BALANCE)));
        this.getRecord(AcctBatchDetail.ACCT_BATCH_DETAIL_FILE).addListener(new AcctPostCheckHandler(this.getRecord(AcctBatch.ACCT_BATCH_FILE)));
        this.getRecord(AcctBatch.ACCT_BATCH_FILE).getField(AcctBatch.TRX_DATE).addListener(new AcctBatchSetRecurringBeh(this.getRecord(AcctBatch.ACCT_BATCH_FILE)));
        this.getRecord(AcctBatch.ACCT_BATCH_FILE).getField(AcctBatch.AUTO_REVERSAL).addListener(new AcctBatchSetRecurringBeh(this.getRecord(AcctBatch.ACCT_BATCH_FILE)));
        Record recAcctBatchDetail = this.getRecord(AcctBatchDetail.ACCT_BATCH_DETAIL_FILE);
        recAcctBatchDetail.close();
        try   {
            while (recAcctBatchDetail.hasNext())
            {
                recAcctBatchDetail.next();
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
        super.setupSFields(); // Set up inherited
        this.getRecord(AcctBatch.ACCT_BATCH_FILE).getField(AcctBatch.BALANCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctBatch.ACCT_BATCH_FILE).getField(AcctBatch.AUTO_REVERSAL_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, "Post", "Post", "Post", null);
    }
    /**
     * Process the command.
     * <br />Step 1 - Process the command if possible and return true if processed.
     * <br />Step 2 - If I can't process, pass to all children (with me as the source).
     * <br />Step 3 - If children didn't process, pass to parent (with me as the source).
     * <br />Note: Never pass to a parent or child that matches the source (to avoid an endless loop).
     * @param strCommand The command to process.
     * @param sourceSField The source screen field (to avoid echos).
     * @param iCommandOptions If this command creates a new screen, create in a new window?
     * @return true if success.
     */
    public boolean doCommand(String strCommand, ScreenField sourceSField, int iCommandOptions)
    {
        if (strCommand.equalsIgnoreCase(MenuConstants.POST))
            return this.onPost();
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Post the detail transaction to the G/L.
     */
    public boolean onPost()
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        String POSTING_RESOURCE = application.getResourceClassName(this.getClass().getName(), "AcctBatchPostingResources");
        // Step 1 - make sure batch is valid
        AcctBatch recAcctBatch = (AcctBatch)this.getRecord(AcctBatch.ACCT_BATCH_FILE);
        AcctBatchDetail recAcctBatchDetail = (AcctBatchDetail)this.getRecord(AcctBatchDetail.ACCT_BATCH_DETAIL_FILE);
        AcctDetail recAcctDetail = (AcctDetail)this.getRecord(AcctDetail.ACCT_DETAIL_FILE);
        AcctDetailDist recAcctDetailDist = (AcctDetailDist)this.getRecord(AcctDetailDist.ACCT_DETAIL_DIST_FILE);
        TransactionType recTransactionType = (TransactionType)this.getRecord(TransactionType.TRANSACTION_TYPE_FILE);
        Period recPeriod = (Period)this.getRecord(Period.PERIOD_FILE);
        try   {
            if (recAcctBatch.getEditMode() == Constants.EDIT_CURRENT)
            {
                if (recAcctBatch.edit() != DBConstants.NORMAL_RETURN)
                {
                    this.displayError(application.getResources(POSTING_RESOURCE, true).getString("BATCH_IN_USE"));
                    return false;
                }
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
            return false;
        }
        if (recAcctBatch.getEditMode() != Constants.EDIT_IN_PROGRESS)
        {   // Error - I need exclusive use of this record
            this.displayError(application.getResources(POSTING_RESOURCE, true).getString("BATCH_NOT_VALID"));
            return false;
        }
        if (recAcctBatch.getField(AcctBatch.TRX_DATE).isNull())
        {
            this.displayError(application.getResources(POSTING_RESOURCE, true).getString("NEED_TRX_DATE"));
            return false;
        }
        if (recAcctBatch.getField(AcctBatch.BALANCE).getValue() != 0)
        {
            this.displayError(application.getResources(POSTING_RESOURCE, true).getString("DOESNT_BALANCE"));
            return false;
        }
        if (recAcctBatch.getField(AcctBatch.AUTO_REVERSAL).getState() == true)
            if (recAcctBatch.getField(AcctBatch.AUTO_REVERSAL_DATE).isNull())
        {
            this.displayError(application.getResources(POSTING_RESOURCE, true).getString("NEED_AUTO_REVERSE"));
            return false;
        }
        // Step 2 - Post it the the G/L
        recAcctBatchDetail.close();
        recAcctDetailDist.startDistTrx();
        try   {
            int iTypeManual = recTransactionType.getTrxTypeID(TransactionType.GENLED, AcctDetail.ACCT_DETAIL_FILE, AcctDetail.MANUALENTRY, AcctDetail.MANUALENTRY);
            int iTypeRecurring = recTransactionType.getTrxTypeID(TransactionType.GENLED, AcctDetail.ACCT_DETAIL_FILE, AcctDetail.RECURRINGTRX, AcctDetail.RECURRINGTRX);
            int iTypeAccrual = recTransactionType.getTrxTypeID(TransactionType.GENLED, AcctDetail.ACCT_DETAIL_FILE, AcctDetail.ACCRUAL, AcctDetail.ACCRUAL);
            int iTypeReversal = recTransactionType.getTrxTypeID(TransactionType.GENLED, AcctDetail.ACCT_DETAIL_FILE, AcctDetail.ACCRUALREVERSAL, AcctDetail.ACCRUALREVERSAL);
            int iTypeClosing = recTransactionType.getTrxTypeID(TransactionType.GENLED, AcctDetail.ACCT_DETAIL_FILE, AcctDetail.CLOSINGENTRY, AcctDetail.CLOSINGENTRY);
            recAcctBatch.getField(AcctBatch.TRX_ENTRY).initField(DBConstants.DONT_DISPLAY);     // Trx entry date/time
        
            double dBatchSeq = -1;
            while (recAcctBatchDetail.hasNext())
            {
                recAcctBatchDetail.next();
        
                // Add the acct detail for this transaction (note: I do not use the methods in acctDetail, because they summarize trx at eom. I can't because there is no detail audit trail.
                recAcctDetail.addNew();
                if (recAcctBatchDetail.getField(AcctBatchDetail.SEQUENCE).getValue() != dBatchSeq)
                    recAcctDetailDist.getField(AcctDetailDist.ACCT_DETAIL_DIST_GROUP_ID).setValue(0);  // Start of trx
                dBatchSeq = recAcctBatchDetail.getField(AcctBatchDetail.SEQUENCE).getValue();
                recAcctDetail.getField(AcctDetail.ACCOUNT_ID).moveFieldToThis(recAcctBatchDetail.getField(AcctBatchDetail.ACCOUNT_ID));
                recAcctDetail.getField(AcctDetail.TRX_DATE).moveFieldToThis(recAcctBatch.getField(AcctBatch.TRX_DATE));
                if (recAcctBatchDetail.getField(AcctBatchDetail.AUTO_REVERSAL).getState())
                    recAcctDetail.getField(AcctDetail.TRX_DATE).moveFieldToThis(recAcctBatch.getField(AcctBatch.AUTO_REVERSAL_DATE));
                recAcctDetail.getField(AcctDetail.TRX_TYPE_ID).setValue(iTypeManual);
                if (recAcctBatch.getField(AcctBatch.RECURRING).getState())
                    recAcctDetail.getField(AcctDetail.TRX_TYPE_ID).setValue(iTypeRecurring);
                if (recAcctBatchDetail.getField(AcctBatchDetail.AUTO_ACCRUAL).getState())
                    recAcctDetail.getField(AcctDetail.TRX_TYPE_ID).setValue(iTypeAccrual);
                if (recAcctBatchDetail.getField(AcctBatchDetail.AUTO_REVERSAL).getState())
                    recAcctDetail.getField(AcctDetail.TRX_TYPE_ID).setValue(iTypeReversal);
                if (recAcctBatch.getField(AcctBatch.AUTO_CLOSING).getState() == true)
                    recAcctDetail.getField(AcctDetail.TRX_TYPE_ID).setValue(iTypeClosing);
                recAcctDetail.getField(AcctDetail.TRX_ENTRY).moveFieldToThis(recAcctBatch.getField(AcctBatch.TRX_ENTRY));
                recAcctDetail.getField(AcctDetail.AMOUNT_LOCAL).moveFieldToThis(recAcctBatchDetail.getField(AcctBatchDetail.AMOUNT));
                recAcctDetail.getField(AcctDetail.SOURCE).moveFieldToThis(recAcctBatch.getField(AcctBatch.SOURCE));
                recAcctDetail.getField(AcctDetail.COMMENTS).moveFieldToThis(recAcctBatch.getField(AcctBatch.COMMENTS));
        
                boolean bSuccess = recAcctDetail.onPostTrx();
                if (!bSuccess)
                    return bSuccess;
                BaseField fldCrAccountID = recAcctDetail.getField(AcctDetail.ACCOUNT_ID);
                bSuccess = recAcctDetail.onPostManualDist(recAcctDetailDist);
                if (!bSuccess)
                    return bSuccess;
                if (recAcctDetailDist.getField(AcctDetailDist.ACCT_DETAIL_DIST_GROUP_ID).getValue() == 0)
                {   // First transaction in a group... group = this trx number
                    Object objectID = recAcctDetailDist.getLastModified(DBConstants.DATA_SOURCE_HANDLE);
                    recAcctDetailDist.setHandle(objectID, DBConstants.DATA_SOURCE_HANDLE);
                    recAcctDetailDist.edit();
                    recAcctDetailDist.getField(AcctDetailDist.ACCT_DETAIL_DIST_GROUP_ID).moveFieldToThis(recAcctDetailDist.getField(AcctDetailDist.ID));
                    recAcctDetailDist.set();
                }
            }
            recAcctDetailDist.endDistTrx();
        } catch (DBException ex)    {
            ex.printStackTrace();
            //+recAcctDetail.getDatabase().rollback();
            return false;
        }
        // Step 3 - Delete the batch (if not recurring)
        if (recAcctBatch.getField(AcctBatch.RECURRING).getState() == false)
        {
            recAcctBatchDetail.close();
            try   {
                while (recAcctBatchDetail.hasNext())
                {
                    recAcctBatchDetail.next();
                    recAcctBatchDetail.edit();
                    recAcctBatchDetail.remove();
                }
                recAcctBatch.remove();
                recAcctBatch.addNew();
            } catch (DBException ex)    {
                ex.printStackTrace();
            }
        }
        else
        {
            this.getMainRecord().getField(AcctBatch.TRX_DATE).setData(null);
            this.getMainRecord().getField(AcctBatch.AUTO_REVERSAL_DATE).setData(null);
        }
        return true;
    }

}
