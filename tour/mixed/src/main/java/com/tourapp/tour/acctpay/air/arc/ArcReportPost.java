/**
 * @(#)ArcReportPost.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.air.arc;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.screen.trx.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  ArcReportPost - Post the ARC ticketing report.
 */
public class ArcReportPost extends Screen
{
    /**
     * Default constructor.
     */
    public ArcReportPost()
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
    public ArcReportPost(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Post the ARC ticketing report";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new TicketTrx(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ApTrx(this);
        new TrxStatus(this);
        new ApControl(this);
        new AcctDetail(this);
        new Period(this);
        new AcctDetailDist(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new ArcReportScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getScreenRecord().getField(ArcReportScreenRecord.LAST_ARC_DATE).setSFieldToProperty();
        this.getScreenRecord().getField(ArcReportScreenRecord.SUMMARY_ACCOUNT_ID).setSFieldToProperty();
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        int iNewTrxType = recTrxStatus.getTrxStatusID(TransactionType.AIR, TicketTrx.TICKET_TRX_FILE, TicketTrx.ARC_SUBMITTED);
        recTrxStatus.getTrxStatusID(TransactionType.AIR, TicketTrx.TICKET_TRX_FILE, TicketTrx.TICKETED);
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.TRX_STATUS_ID), recTrxStatus.getField(TrxStatus.ID), "="));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.ISSUE_DATE), this.getScreenRecord().getField(ArcReportScreenRecord.LAST_ARC_DATE), "<="));
        
        this.getMainRecord().addListener(new UpdateArcHandler(null));
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(ArcReportScreenRecord.ARC_REPORT_SCREEN_RECORD_FILE).getField(ArcReportScreenRecord.LAST_ARC_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ArcReportScreenRecord.ARC_REPORT_SCREEN_RECORD_FILE).getField(ArcReportScreenRecord.SUMMARY_ACCOUNT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
     * OnPost Method.
     */
    public boolean onPost()
    {
        try   {
            TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
            Object bookmark = recTrxStatus.getHandle(DBConstants.DATA_SOURCE_HANDLE);
            int iNewTrxType = recTrxStatus.getTrxStatusID(TransactionType.AIR, TicketTrx.TICKET_TRX_FILE, TicketTrx.ARC_SUBMITTED);
            recTrxStatus.setHandle(bookmark, DBConstants.DATA_SOURCE_HANDLE);
            Record recTicketTrx = this.getMainRecord();
        
            recTicketTrx.close();
            while (recTicketTrx.hasNext())
            {
                recTicketTrx.next();
                recTicketTrx.edit();
                recTicketTrx.getField(TicketTrx.TRX_STATUS_ID).setValue(iNewTrxType);
                recTicketTrx.getField(TicketTrx.ARC_DATE).setValue(DateField.todaysDate());
                recTicketTrx.set();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
            return false;
        }
        
        return true;
    }

}
