/**
 * @(#)TourApTrxGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.acctpay;

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
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctpay.screen.trx.*;
import com.tourapp.tour.genled.db.*;

/**
 *  TourApTrxGridScreen - Tour A/P detail.
 */
public class TourApTrxGridScreen extends BookingSubGridScreen
{
    /**
     * Default constructor.
     */
    public TourApTrxGridScreen()
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
    public TourApTrxGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        parentScreen.setProperty(BookingScreenHandler.SUB_SCREEN_PARAM, Integer.toString(BookingScreenHandler.ITIN_SCREEN));
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Tour A/P detail";
    }
    /**
     * TourApTrxGridScreen Method.
     */
    public TourApTrxGridScreen(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recHeader, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new ApTrx(this);
    }
    /**
     * If there is a header record, return it, otherwise, return the main record.
     * The header record is the (optional) main record on gridscreens and is sometimes used
     * to enter data in a sub-record when a header is required.
     * @return The header record.
     */
    public Record getHeaderRecord()
    {
        return this.getRecord(Tour.kTourFile);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getHeaderRecord().getField(Tour.kID).addListener(new FieldReSelectHandler(this));
        this.getMainRecord().addListener(new SubFileFilter(this.getHeaderRecord(), true));
        /*
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(VendorScreenRecord.kTotal), ApTrx.kInvoiceAmount, false, true)); // Init this field override for other value
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(VendorScreenRecord.kBalance), ApTrx.kInvoiceBalance, false, true));    // Init this field override for other value
        this.getScreenRecord().getField(VendorScreenRecord.kTourID).addListener(new FieldReSelectHandler(this));
        
        String strDisplayType = this.getProperty(this.getScreenRecord().getField(VendorScreenRecord.kDisplayType).getFieldName());
        if (strDisplayType != null)
            this.getScreenRecord().getField(VendorScreenRecord.kDisplayType).setString(strDisplayType, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        this.getMainRecord().addListener(new FilterApTrxHandler(this.getScreenRecord().getField(VendorScreenRecord.kDisplayType)));
        this.getScreenRecord().getField(VendorScreenRecord.kDisplayType).addListener(new FieldReSelectHandler(this));
        */
        this.setEditing(false);
        this.setAppending(false);
    }
    /**
     * Add the navigation button(s) to the left of the grid row.
     */
    public void addNavButtons()
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, AcctDetailDist.DIST_DISTRIBUTION, AcctDetailDist.DIST_DISTRIBUTION, application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(AcctDetailDist.DIST_DISTRIBUTION));
        
        String strPaymentHistory = PaymentHistory.PAYMENT_HISTORY;
        strPaymentHistory = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(strPaymentHistory);
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, PaymentHistory.PAYMENT_HISTORY_ICON, PaymentHistory.kPaymentHistoryFile, strPaymentHistory);
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
        super.addNavButtons();  // Next buttons will be "First!"
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        super.addToolbarButtons(toolScreen);
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        String strPaymentHistory = PaymentHistory.PAYMENT_HISTORY;
        strPaymentHistory = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(strPaymentHistory);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, strPaymentHistory, PaymentHistory.PAYMENT_HISTORY_ICON, PaymentHistory.kPaymentHistoryFile, null);
        
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(AcctDetailDist.DIST_DISTRIBUTION), AcctDetailDist.DIST_DISTRIBUTION, AcctDetailDist.DIST_DISTRIBUTION, null);
        
        String strVendor = Vendor.kVendorFile + ' ' + MenuConstants.DISPLAY;
        strVendor = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(strVendor);
        new SButtonBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, strVendor, MenuConstants.DISPLAY, Vendor.kVendorFile, null);
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        ToolScreen toolbar = super.addToolbars();
        /*
        ToolScreen toolbar2 = new EmptyToolbar(this.getNextLocation(ScreenConstants.LAST_LOCATION, ScreenConstants.DONT_SET_ANCHOR), this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        Tour recTour = (Tour)this.getRecord(Tour.kTourFile);
        
        Converter converter = null;
        converter = recTour.getField(Tour.kTotalCost);
        ScreenField sField = (ScreenField)converter.setupDefaultView(toolbar.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolbar, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
        */
        return toolbar;
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kStartServiceDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kVendorID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kTrxStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceBalance).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceBalanceLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        if (Vendor.kVendorFile.equalsIgnoreCase(strCommand))
        {
            iCommandOptions = ScreenConstants.USE_SAME_WINDOW | DBConstants.PUSH_TO_BROSWER;
            boolean bReadCurrentRecord = false;
            boolean bLinkGridToQuery = false;
            int iDocMode = ApTrx.VENDOR_AP_SCREEN;
            Map<String,Object> properties = new Hashtable<String,Object>();
        //?    properties.put(this.getScreenRecord().getField(VendorScreenRecord.kDisplayType).getFieldName(), this.getScreenRecord().getField(VendorScreenRecord.kDisplayType).toString());
            if (!this.getMainRecord().getField(ApTrx.kVendorID).isNull())
                properties.put(DBParams.HEADER_OBJECT_ID, this.getMainRecord().getField(ApTrx.kVendorID).toString());
        
            Record recordMain = this.getMainRecord();
            if (recordMain.getListener(OnSelectHandler.class.getName()) != null)
            {
                OnSelectHandler listener = (OnSelectHandler)recordMain.getListener(OnSelectHandler.class.getName());
                recordMain = listener.getRecordToSync();
                bLinkGridToQuery = true;
                iDocMode = iDocMode | ScreenConstants.SELECT_MODE;
            }
            boolean bSuccess = (this.onForm(recordMain, iDocMode, bReadCurrentRecord, iCommandOptions, bLinkGridToQuery, properties) != null);
            if (bLinkGridToQuery)
                this.free();  // Note: I will get a warning that the remote listener was not removed since the server thinks this is the same listener (ignore)
            return bSuccess;
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
