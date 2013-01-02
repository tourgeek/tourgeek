/**
  * @(#)McoSubmitReport.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctrec.screen.mco;

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
import com.tourapp.tour.genled.db.*;

/**
 *  McoSubmitReport - MCO carrier submission report.
 */
public class McoSubmitReport extends ReportScreen
{
    /**
     * Default constructor.
     */
    public McoSubmitReport()
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
    public McoSubmitReport(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "MCO carrier submission report";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Mco(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ArControl(this);
        new TrxStatus(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new McoScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getScreenRecord().getField(McoScreenRecord.AIRLINE_ID).addListener(new InitFieldHandler(this.getRecord(ArControl.AR_CONTROL_FILE).getField(ArControl.AIRLINE_ID)));
        this.getScreenRecord().getField(McoScreenRecord.SERVICE_CHARGE).addListener(new InitFieldHandler(this.getRecord(ArControl.AR_CONTROL_FILE).getField(ArControl.MCO_SVC_PER)));
        
        this.getMainRecord().setKeyArea(Mco.TRX_STATUS_ID_KEY);
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, Mco.MCO_FILE, Mco.ENTERED);
        this.getMainRecord().addListener(new SubFileFilter(recTrxStatus));
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(McoScreenRecord.TOTAL_GROSS), Mco.GROSS, false, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(McoScreenRecord.TOTAL_NET), Mco.NET, false, true));
        this.getMainRecord().addListener(new McoSubmitCalcNetBeh(null));
        
        this.getMainRecord().addListener(new CompareFileFilter(Mco.AIRLINE_ID, this.getScreenRecord().getField(McoScreenRecord.AIRLINE_ID), "=", null, false));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new McoSubmitToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new McoSubmitHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new McoSubmitFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(Mco.MCO_FILE).getField(Mco.MCO_NO).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Mco.MCO_FILE).getField(Mco.GROSS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Mco.MCO_FILE).getField(Mco.TAX_AMT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Mco.MCO_FILE).getField(Mco.COMM_PER).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Mco.MCO_FILE).getField(Mco.COMM_AMT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(McoScreenRecord.MCO_SCREEN_RECORD_FILE).getField(McoScreenRecord.NET).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        if (McoSubmitToolbar.UPDATE_COMMAND.equalsIgnoreCase(strCommand))
            return this.onMcoSubmit();
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Update the Mco Status to "Submitted".
     */
    public boolean onMcoSubmit()
    {
        try   {
            TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
            Object bookmark = recTrxStatus.getHandle(DBConstants.DATA_SOURCE_HANDLE);
            int iTrxClassID = recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, Mco.MCO_FILE, Mco.SUBMITTED);
            recTrxStatus.setHandle(bookmark, DBConstants.DATA_SOURCE_HANDLE);
            double dToday = DateTimeField.todaysDate();
            Record recMco = this.getMainRecord();
        
            // Make sure the A/R trx's status is in sync
            if (this.getMainRecord().getListener(SyncArTrxStatusHandler.class.getName()) == null)
                this.getMainRecord().addListener(new SyncArTrxStatusHandler(null));
                    
            recMco.close();
            while (recMco.hasNext())
            {
                recMco.next();
                recMco.edit();
                recMco.getField(Mco.TRX_STATUS_ID).setValue(iTrxClassID);
                recMco.getField(Mco.CARRIER_SVC_PER).moveFieldToThis(this.getScreenRecord().getField(McoScreenRecord.SERVICE_CHARGE));
                recMco.getField(Mco.DATE_SUBMITTED).setValue(dToday);
                recMco.set();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

}
