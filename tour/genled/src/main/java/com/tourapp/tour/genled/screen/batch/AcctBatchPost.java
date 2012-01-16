/**
 * @(#)AcctBatchPost.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.screen.batch;

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
import com.tourapp.tour.genled.db.*;
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
        this.getMainRecord().getField(AcctBatch.kRecurring).removeListener((FieldListener)this.getMainRecord().getField(AcctBatch.kRecurring).getListener(DisableOnFieldHandler.class.getName()), true);
        this.getMainRecord().getField(AcctBatch.kTrxDate).removeListener((FieldListener)this.getMainRecord().getField(AcctBatch.kTrxDate).getListener(CopyStringHandler.class.getName()), true);
        if (this.getMainRecord().getField(AcctBatch.kRecurring).getState())
            this.getMainRecord().getField(AcctBatch.kTrxDate).initField(true);
        
        this.getRecord(AcctBatchDetail.kAcctBatchDetailFile).addListener(new SubFileFilter(this.getRecord(AcctBatch.kAcctBatchFile)));
        this.getRecord(AcctBatchDetail.kAcctBatchDetailFile).addListener(new SubCountHandler(this.getRecord(AcctBatch.kAcctBatchFile).getField(AcctBatch.kBalance), AcctBatchDetail.kAmount, false, true));
        this.getRecord(AcctBatchDetail.kAcctBatchDetailFile).addListener(new BatchSequenceHandler(this.getRecord(AcctBatchDetail.kAcctBatchDetailFile).getField(AcctBatchDetail.kSequence), this.getRecord(AcctBatch.kAcctBatchFile).getField(AcctBatch.kNextSequence), this.getRecord(AcctBatch.kAcctBatchFile).getField(AcctBatch.kBalance)));
        this.getRecord(AcctBatchDetail.kAcctBatchDetailFile).addListener(new AcctPostCheckHandler(this.getRecord(AcctBatch.kAcctBatchFile)));
        this.getRecord(AcctBatch.kAcctBatchFile).getField(AcctBatch.kTrxDate).addListener(new AcctBatchSetRecurringBeh(this.getRecord(AcctBatch.kAcctBatchFile)));
        this.getRecord(AcctBatch.kAcctBatchFile).getField(AcctBatch.kAutoReversal).addListener(new AcctBatchSetRecurringBeh(this.getRecord(AcctBatch.kAcctBatchFile)));
        Record recAcctBatchDetail = this.getRecord(AcctBatchDetail.kAcctBatchDetailFile);
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
        this.getRecord(AcctBatch.kAcctBatchFile).getField(AcctBatch.kBalance).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctBatch.kAcctBatchFile).getField(AcctBatch.kAutoReversalDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        AcctBatch recAcctBatch = (AcctBatch)this.getRecord(AcctBatch.kAcctBatchFile);
        AcctBatchDetail recAcctBatchDetail = (AcctBatchDetail)this.getRecord(AcctBatchDetail.kAcctBatchDetailFile);
        AcctDetail recAcctDetail = (AcctDetail)this.getRecord(AcctDetail.kAcctDetailFile);
        AcctDetailDist recAcctDetailDist = (AcctDetailDist)this.getRecord(AcctDetailDist.kAcctDetailDistFile);
        TransactionType recTransactionType = (TransactionType)this.getRecord(TransactionType.kTransactionTypeFile);
        Period recPeriod = (Period)this.getRecord(Period.kPeriodFile);
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
        if (recAcctBatch.getField(AcctBatch.kTrxDate).isNull())
        {
            this.displayError(application.getResources(POSTING_RESOURCE, true).getString("NEED_TRX_DATE"));
            return false;
        }
        if (recAcctBatch.getField(AcctBatch.kBalance).getValue() != 0)
        {
            this.displayError(application.getResources(POSTING_RESOURCE, true).getString("DOESNT_BALANCE"));
            return false;
        }
        if (recAcctBatch.getField(AcctBatch.kAutoReversal).getState() == true)
            if (recAcctBatch.getField(AcctBatch.kAutoReversalDate).isNull())
        {
            this.displayError(application.getResources(POSTING_RESOURCE, true).getString("NEED_AUTO_REVERSE"));
            return false;
        }
        // Step 2 - Post it the the G/L
        recAcctBatchDetail.close();
        recAcctDetailDist.startDistTrx();
        try   {
            int iTypeManual = recTransactionType.getTrxTypeID(TransactionType.GENLED, AcctDetail.kAcctDetailFile, AcctDetail.MANUALENTRY, AcctDetail.MANUALENTRY);
            int iTypeRecurring = recTransactionType.getTrxTypeID(TransactionType.GENLED, AcctDetail.kAcctDetailFile, AcctDetail.RECURRINGTRX, AcctDetail.RECURRINGTRX);
            int iTypeAccrual = recTransactionType.getTrxTypeID(TransactionType.GENLED, AcctDetail.kAcctDetailFile, AcctDetail.ACCRUAL, AcctDetail.ACCRUAL);
            int iTypeReversal = recTransactionType.getTrxTypeID(TransactionType.GENLED, AcctDetail.kAcctDetailFile, AcctDetail.ACCRUALREVERSAL, AcctDetail.ACCRUALREVERSAL);
            int iTypeClosing = recTransactionType.getTrxTypeID(TransactionType.GENLED, AcctDetail.kAcctDetailFile, AcctDetail.CLOSINGENTRY, AcctDetail.CLOSINGENTRY);
            recAcctBatch.getField(AcctBatch.kTrxEntry).initField(DBConstants.DONT_DISPLAY);     // Trx entry date/time
        
            double dBatchSeq = -1;
            while (recAcctBatchDetail.hasNext())
            {
                recAcctBatchDetail.next();
        
                // Add the acct detail for this transaction (note: I do not use the methods in acctDetail, because they summarize trx at eom. I can't because there is no detail audit trail.
                recAcctDetail.addNew();
                if (recAcctBatchDetail.getField(AcctBatchDetail.kSequence).getValue() != dBatchSeq)
                    recAcctDetailDist.getField(AcctDetailDist.kAcctDetailDistGroupID).setValue(0);  // Start of trx
                dBatchSeq = recAcctBatchDetail.getField(AcctBatchDetail.kSequence).getValue();
                recAcctDetail.getField(AcctDetail.kAccountID).moveFieldToThis(recAcctBatchDetail.getField(AcctBatchDetail.kAccountID));
                recAcctDetail.getField(AcctDetail.kTrxDate).moveFieldToThis(recAcctBatch.getField(AcctBatch.kTrxDate));
                if (recAcctBatchDetail.getField(AcctBatchDetail.kAutoReversal).getState())
                    recAcctDetail.getField(AcctDetail.kTrxDate).moveFieldToThis(recAcctBatch.getField(AcctBatch.kAutoReversalDate));
                recAcctDetail.getField(AcctDetail.kTrxTypeID).setValue(iTypeManual);
                if (recAcctBatch.getField(AcctBatch.kRecurring).getState())
                    recAcctDetail.getField(AcctDetail.kTrxTypeID).setValue(iTypeRecurring);
                if (recAcctBatchDetail.getField(AcctBatchDetail.kAutoAccrual).getState())
                    recAcctDetail.getField(AcctDetail.kTrxTypeID).setValue(iTypeAccrual);
                if (recAcctBatchDetail.getField(AcctBatchDetail.kAutoReversal).getState())
                    recAcctDetail.getField(AcctDetail.kTrxTypeID).setValue(iTypeReversal);
                if (recAcctBatch.getField(AcctBatch.kAutoClosing).getState() == true)
                    recAcctDetail.getField(AcctDetail.kTrxTypeID).setValue(iTypeClosing);
                recAcctDetail.getField(AcctDetail.kTrxEntry).moveFieldToThis(recAcctBatch.getField(AcctBatch.kTrxEntry));
                recAcctDetail.getField(AcctDetail.kAmountLocal).moveFieldToThis(recAcctBatchDetail.getField(AcctBatchDetail.kAmount));
                recAcctDetail.getField(AcctDetail.kSource).moveFieldToThis(recAcctBatch.getField(AcctBatch.kSource));
                recAcctDetail.getField(AcctDetail.kComments).moveFieldToThis(recAcctBatch.getField(AcctBatch.kComments));
        
                boolean bSuccess = recAcctDetail.onPostTrx();
                if (!bSuccess)
                    return bSuccess;
                BaseField fldCrAccountID = recAcctDetail.getField(AcctDetail.kAccountID);
                bSuccess = recAcctDetail.onPostManualDist(recAcctDetailDist);
                if (!bSuccess)
                    return bSuccess;
                if (recAcctDetailDist.getField(AcctDetailDist.kAcctDetailDistGroupID).getValue() == 0)
                {   // First transaction in a group... group = this trx number
                    Object objectID = recAcctDetailDist.getLastModified(DBConstants.DATA_SOURCE_HANDLE);
                    recAcctDetailDist.setHandle(objectID, DBConstants.DATA_SOURCE_HANDLE);
                    recAcctDetailDist.edit();
                    recAcctDetailDist.getField(AcctDetailDist.kAcctDetailDistGroupID).moveFieldToThis(recAcctDetailDist.getField(AcctDetailDist.kID));
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
        if (recAcctBatch.getField(AcctBatch.kRecurring).getState() == false)
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
            this.getMainRecord().getField(AcctBatch.kTrxDate).setData(null);
            this.getMainRecord().getField(AcctBatch.kAutoReversalDate).setData(null);
        }
        return true;
    }

}
