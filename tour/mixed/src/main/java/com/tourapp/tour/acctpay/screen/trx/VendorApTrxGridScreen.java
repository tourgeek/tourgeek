/**
 * @(#)VendorApTrxGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.trx;

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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  VendorApTrxGridScreen - Vendor A/P detail.
 */
public class VendorApTrxGridScreen extends DetailGridScreen
{
    protected Record m_recVendor = null;
    /**
     * Default constructor.
     */
    public VendorApTrxGridScreen()
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
    public VendorApTrxGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        m_recVendor = null;
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Vendor A/P detail";
    }
    /**
     * Constructor.
     */
    public VendorApTrxGridScreen(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
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
        return new ApTrx(this);
    }
    /**
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        return new Vendor(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        ScreenRecord screenRecord = new VendorScreenRecord(this);
        ((ReferenceField)screenRecord.getField(VendorScreenRecord.kVendorID)).setReferenceRecord(this.getRecord(Vendor.kVendorFile));
        ((ReferenceField)screenRecord.getField(VendorScreenRecord.kVendorID)).setReference(this.getRecord(Vendor.kVendorFile), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        return screenRecord;
    }
    /**
     * Does the current user have permission to access this screen.
     * @return NORMAL_RETURN if access is allowed, ACCESS_DENIED or LOGIN_REQUIRED otherwise.
     */
    public int checkSecurity()
    {
        int iErrorCode = super.checkSecurity();
        if (iErrorCode == DBConstants.NORMAL_RETURN)
        {   // Okay, their group can access this screen, but can this user access this data?
            String strUserContactType = this.getProperty(DBParams.CONTACT_TYPE);
            String strUserContactID = this.getProperty(DBParams.CONTACT_ID);
        
            String strContactID = this.getHeaderRecord().getField(Vendor.kID).toString();
            if (Vendor.kVendorFile.equalsIgnoreCase(strUserContactType))
            {
                if ((strContactID == null) || (strContactID.length() == 0))
                    if ((strUserContactID != null) && (strUserContactID.length() > 0))
                        this.getHeaderRecord().getField(Vendor.kID).setString(strContactID = strUserContactID);
                iErrorCode = this.checkContactSecurity(Vendor.kVendorFile, strContactID);
            }
        }
        return iErrorCode;
    }
    /**
     * IsContactDisplay Method.
     */
    public boolean isContactDisplay()
    {
        String strUserContactType = this.getProperty(DBParams.CONTACT_TYPE);
        String strUserContactID = this.getProperty(DBParams.CONTACT_ID);
        
        String strContactID = this.getHeaderRecord().getField(Vendor.kID).toString();
        
        if ((strUserContactID != null) && (strUserContactID.equals(strContactID)))
            if (Vendor.kVendorFile.equalsIgnoreCase(strUserContactType))
                return true;
        return false;
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        //x I want to see them all
        //xSubFileFilter filter = (SubFileFilter)this.getMainRecord().getListener(SubFileFilter.class);
        //xif (filter != null)
        //x    filter.setFilterIfNull(true);
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(VendorScreenRecord.kBalance), ApTrx.kInvoiceBalance, false, true)); // Init this field override for other value
        this.getScreenRecord().getField(VendorScreenRecord.kVendorID).addListener(new FieldReSelectHandler(this));
        
        String strDisplayType = this.getProperty(this.getScreenRecord().getField(VendorScreenRecord.kDisplayType).getFieldName());
        if (strDisplayType != null)
            this.getScreenRecord().getField(VendorScreenRecord.kDisplayType).setString(strDisplayType, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        this.getMainRecord().addListener(new FilterApTrxHandler(this.getScreenRecord().getField(VendorScreenRecord.kDisplayType)));
        this.getScreenRecord().getField(VendorScreenRecord.kDisplayType).addListener(new FieldReSelectHandler(this));
        
        this.getMainRecord().addListener(new CompareFileFilter(ApTrx.kActiveTrx, this.getScreenRecord().getField(VendorScreenRecord.kDisplayActive), "=", this.getScreenRecord().getField(VendorScreenRecord.kDisplayActive), true));
        this.getScreenRecord().getField(VendorScreenRecord.kDisplayActive).addListener(new FieldReSelectHandler(this));
        
        this.setEditing(false);
    }
    /**
     * Add the navigation button(s) to the left of the grid row.
     */
    public void addNavButtons()
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        
        if (!this.isContactDisplay())
        {
            String strTour = Tour.kTourFile + ' ' + MenuConstants.DISPLAY;
            strTour = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(strTour);
            new SButtonBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, MenuConstants.DISPLAY, Tour.kTourFile, strTour);
            
            new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, AcctDetailDist.DIST_DISTRIBUTION, AcctDetailDist.DIST_DISTRIBUTION, application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(AcctDetailDist.DIST_DISTRIBUTION));
        }
        
        String strPaymentHistory = PaymentHistory.PAYMENT_HISTORY;
        strPaymentHistory = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(strPaymentHistory);
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, PaymentHistory.PAYMENT_HISTORY_ICON, PaymentHistory.kPaymentHistoryFile, strPaymentHistory);
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(ApTrx.PRODUCT_DETAIL));
        super.addNavButtons();  // Next buttons will be "First!"
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        String strPaymentHistory = PaymentHistory.PAYMENT_HISTORY;
        strPaymentHistory = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(strPaymentHistory);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(ApTrx.PRODUCT_DETAIL), MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, strPaymentHistory, PaymentHistory.PAYMENT_HISTORY_ICON, PaymentHistory.kPaymentHistoryFile, null);
        
        if (!this.isContactDisplay())
        {
            new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(AcctDetailDist.DIST_DISTRIBUTION), AcctDetailDist.DIST_DISTRIBUTION, AcctDetailDist.DIST_DISTRIBUTION, null);
            
            String strTour = Tour.kTourFile + ' ' + MenuConstants.DISPLAY;
            strTour = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(strTour);
            new SButtonBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, strTour, MenuConstants.DISPLAY, Tour.kTourFile, null);
        }
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kTrxStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kStartServiceDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDepartureEstimate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceBalance).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceNo).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kTourID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        if (Tour.kTourFile.equalsIgnoreCase(strCommand))
        {
            iCommandOptions = ScreenConstants.USE_SAME_WINDOW | DBConstants.PUSH_TO_BROSWER;
            boolean bReadCurrentRecord = false;
            boolean bLinkGridToQuery = false;
            int iDocMode = ApTrx.TOUR_AP_SCREEN;
            Map<String,Object> properties = new Hashtable<String,Object>();
            properties.put(this.getScreenRecord().getField(VendorScreenRecord.kDisplayType).getFieldName(), this.getScreenRecord().getField(VendorScreenRecord.kDisplayType).toString());
            if (!this.getMainRecord().getField(ApTrx.kTourID).isNull())
                properties.put(DBParams.HEADER_OBJECT_ID, this.getMainRecord().getField(ApTrx.kTourID).toString());
        
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
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        if (this.isContactDisplay())
            return null;
        return new VendorHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}
