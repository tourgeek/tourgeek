/**
 * @(#)PaymentHistoryGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.hist;

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
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.screen.trx.*;
import com.tourapp.tour.acctpay.db.*;

/**
 *  PaymentHistoryGridScreen - Payment History File.
 */
public class PaymentHistoryGridScreen extends DetailGridScreen
{
    /**
     * Default constructor.
     */
    public PaymentHistoryGridScreen()
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
    public PaymentHistoryGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Payment History File";
    }
    /**
     * PaymentHistoryGridScreen Method.
     */
    public PaymentHistoryGridScreen(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recHeader, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new PaymentHistory(this);
    }
    /**
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        return new ApTrx(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getMainRecord().setKeyArea(PaymentHistory.kApTrxIDKey);
        
        this.getHeaderRecord().getField(ApTrx.kID).addListener(new FieldReSelectHandler(this));
        
        this.setEditing(false);
    }
    /**
     * Add the navigation button(s) to the left of the grid row.
     */
    public void addNavButtons()
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, AcctDetailDist.DIST_DISTRIBUTION, AcctDetailDist.DIST_DISTRIBUTION, application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(AcctDetailDist.DIST_DISTRIBUTION));
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, LinkTrx.SOURCE, LinkTrx.SOURCE, application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(LinkTrx.SOURCE));
        super.addNavButtons();
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(LinkTrx.SOURCE), LinkTrx.SOURCE, LinkTrx.SOURCE, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(AcctDetailDist.DIST_DISTRIBUTION), AcctDetailDist.DIST_DISTRIBUTION, AcctDetailDist.DIST_DISTRIBUTION, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record recVendor = ((ReferenceField)this.getHeaderRecord().getField(ApTrx.kVendorID)).getReferenceRecord(this);
        if (recVendor != null)
        { // This is display the correct currency on local currency fields.
            Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.kCurrencysID)).getReferenceRecord(this);
            recVendor.getField(Vendor.kCurrencysID).addListener(new ReadSecondaryHandler(recCurrencys));
        }
        this.getRecord(PaymentHistory.kPaymentHistoryFile).getField(PaymentHistory.kTrxDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PaymentHistory.kPaymentHistoryFile).getField(PaymentHistory.kTrxStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PaymentHistory.kPaymentHistoryFile).getField(PaymentHistory.kAmountApplied).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PaymentHistory.kPaymentHistoryFile).getField(PaymentHistory.kLinkedTrxID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PaymentHistory.kPaymentHistoryFile).getField(PaymentHistory.kLinkedTrxDescID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new ApTrxHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
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
        if (LinkTrx.SOURCE.equalsIgnoreCase(strCommand))
        {
            LinkTrx recLinkTrx = (LinkTrx)this.getMainRecord();
            strCommand = recLinkTrx.getSourceCommand();
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
