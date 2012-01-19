/**
 * @(#)OverrideGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.air.oride;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.screen.trx.*;

/**
 *  OverrideGridScreen - Enter override information.
 */
public class OverrideGridScreen extends GridScreen
{
    /**
     * Default constructor.
     */
    public OverrideGridScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public OverrideGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Enter override information";
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
        new ApControl(this);
        new TrxStatus(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new OverrideScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getMainRecord().setKeyArea(TicketTrx.kVendorIDKey);
        this.getMainRecord().addListener(new SubFileFilter(this.getScreenRecord().getField(OverrideScreenRecord.kVendorID), TicketTrx.kVendorID, null, -1, null, -1, true));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.kStartServiceDate), this.getScreenRecord().getField(OverrideScreenRecord.kStartDeparture), ">="));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(TicketTrx.kStartServiceDate), this.getScreenRecord().getField(OverrideScreenRecord.kEndDeparture), "<="));
        
        this.getScreenRecord().getField(OverrideScreenRecord.kVendorID).addListener(new FieldReSelectHandler(this));
        this.getScreenRecord().getField(OverrideScreenRecord.kStartDeparture).addListener(new FieldReSelectHandler(this));
        this.getScreenRecord().getField(OverrideScreenRecord.kEndDeparture).addListener(new FieldReSelectHandler(this));
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile);
        int iOverrideStatus = recTrxStatus.getTrxStatusID(TransactionType.AIR, TicketTrx.kTicketTrxFile, TicketTrx.OVERRIDE_PAID);  // Remember, TrxStatus may be used by UpdateOverrideAcctDetailHandler
        this.getMainRecord().getField(TicketTrx.kOverridePaid).addListener(new CopyDataHandler(this.getMainRecord().getField(TicketTrx.kTrxStatusID), Integer.toString(iOverrideStatus), null));
        
        this.getMainRecord().addListener(new DateChangedHandler(TicketTrx.kOverridePaidDate));
        this.getMainRecord().addListener(new UpdateOverrideAcctDetailHandler(null));
        
        this.setEnabled(false);
        this.getMainRecord().getField(TicketTrx.kOverridePaid).setEnabled(true);
        this.setAppending(false);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        this.getRecord(OverrideScreenRecord.kOverrideScreenRecordFile).getField(OverrideScreenRecord.kVendorID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(OverrideScreenRecord.kOverrideScreenRecordFile).getField(OverrideScreenRecord.kStartDeparture).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(OverrideScreenRecord.kOverrideScreenRecordFile).getField(OverrideScreenRecord.kEndDeparture).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(TicketTrx.kTicketTrxFile).getField(TicketTrx.kTicketNumber).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.kTicketTrxFile).getField(TicketTrx.kStartServiceDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.kTicketTrxFile).getField(TicketTrx.kNetFare).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.kTicketTrxFile).getField(TicketTrx.kOverridePercent).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.kTicketTrxFile).getField(TicketTrx.kOverrideAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TicketTrx.kTicketTrxFile).getField(TicketTrx.kOverridePaid).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        String strDesc = "Paid?";
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        strDesc = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(strDesc);
        Converter converter = new OverridePaidCheckbox(this.getMainRecord().getField(ApTrx.kOverridePaid), null, strDesc, false);
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        if ((strCommand.equalsIgnoreCase(MenuConstants.FORM)) || (strCommand.equalsIgnoreCase(MenuConstants.FORMLINK)))
            return (this.onForm(null, TicketTrx.OVERRIDE_SCREEN, true, iCommandOptions, null) != null);
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
