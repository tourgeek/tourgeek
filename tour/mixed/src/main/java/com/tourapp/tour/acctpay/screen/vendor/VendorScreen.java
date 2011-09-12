/**
 * @(#)VendorScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.vendor;

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
import com.tourapp.tour.acctpay.screen.trx.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import org.jbundle.main.msg.wsdl.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.thin.base.screen.*;
import org.jbundle.base.message.trx.message.*;

/**
 *  VendorScreen - Vendors.
 */
public class VendorScreen extends Screen
{
    protected Integer m_intRegistryID = null;
    /**
     * Default constructor.
     */
    public VendorScreen()
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
    public VendorScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Vendors";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Vendor(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ApControl(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        ((Vendor)this.getMainRecord()).addPropertyListeners();
        
        this.addMainKeyBehavior();
        this.getMainRecord().getField(Vendor.kCountryID).addListener(new InitFieldHandler(this.getRecord(ApControl.kApControlFile).getField(ApControl.kCountryID)));
        this.getMainRecord().getField(Vendor.kCurrencysID).addListener(new InitFieldHandler(this.getRecord(ApControl.kApControlFile).getField(ApControl.kCurrencysID)));
        this.getMainRecord().getField(Vendor.kMessageTransportID).addListener(new InitFieldHandler(this.getRecord(ApControl.kApControlFile).getField(ApControl.kMessageTransportID)));
        this.getMainRecord().getField(Vendor.kVendorStatusID).addListener(new InitFieldHandler(this.getRecord(ApControl.kApControlFile).getField(ApControl.kVendorStatusID)));
        this.getMainRecord().getField(Vendor.kPaymentCycleID).addListener(new InitFieldHandler(this.getRecord(ApControl.kApControlFile).getField(ApControl.kPaymentCycleID)));
        this.getMainRecord().getField(Vendor.kPaymentCodeID).addListener(new InitFieldHandler(this.getRecord(ApControl.kApControlFile).getField(ApControl.kPaymentCodeID)));
        this.getMainRecord().getField(Vendor.kPrepayTypeID).addListener(new InitFieldHandler(this.getRecord(ApControl.kApControlFile).getField(ApControl.kPrepayTypeID)));
        this.getMainRecord().getField(Vendor.kDefaultAccountID).addListener(new InitFieldHandler(this.getRecord(ApControl.kApControlFile).getField(ApControl.kCostAccountID)));
        
        Record recCountry = ((ReferenceField)this.getMainRecord().getField(Vendor.kCountryID)).getReferenceRecord();
        this.getMainRecord().getField(Vendor.kCountryID).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(Vendor.kCountry), recCountry.getField(Country.kName), false,  true));
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, Vendor.MESSAGE_DETAIL_SCREEN, MenuConstants.FORMDETAIL, Vendor.MESSAGE_DETAIL_SCREEN, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, Vendor.MESSAGE_LOG_SCREEN, MenuConstants.FORMDETAIL, Vendor.MESSAGE_LOG_SCREEN, null);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kVendorName).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kAddressLine1).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kAddressLine2).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kCityOrTown).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kStateOrRegion).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kPostalCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kCountryID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kCountry).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kTel).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kFax).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kEmail).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kWeb).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        FieldLengthConverter converter = new FieldLengthConverter(this.getRecord(Vendor.kVendorFile).getField(Vendor.kMessageServer), 60);
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        converter = new FieldLengthConverter(this.getRecord(Vendor.kVendorFile).getField(Vendor.kWSDLPath), 60);
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        BaseApplication application = (BaseApplication)this.getTask().getApplication();        
        String strRefresh = application.getResources(ResourceConstants.DEFAULT_RESOURCE, true).getString(MenuConstants.REFRESH);
        new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, null, ScreenConstants.DONT_DISPLAY_DESC, null, null, MenuConstants.REFRESH, MenuConstants.REFRESH, strRefresh);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kMessageTransportID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kContact).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kCurrencysID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        ScreenLocation secondColumn = this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.DONT_SET_ANCHOR);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kVendorStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kPaymentCycleID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kPaymentCodeID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kPrepayTypeID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kDepositID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kDefaultAccountID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kSend1099).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kTaxId).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kVendorsCustNo).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kOperationTypeCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kUserID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kComments).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        //?this.setAnchor(secondColumn);
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
        if (MenuConstants.REFRESH.equalsIgnoreCase(strCommand))
        {
            if ((this.getMainRecord().getEditMode() == DBConstants.EDIT_CURRENT) || (this.getMainRecord().getEditMode() == DBConstants.EDIT_IN_PROGRESS))
            {
                if ((this.getMainRecord().getField(Vendor.kWeb).isModified())
                    || (this.getMainRecord().getField(Vendor.kProperties).isModified()))
                {
                    try {
                        this.getMainRecord().writeAndRefresh();
                    } catch (DBException e) {
                        e.printStackTrace();
                    }
                }
                Map<String,Object> map = new Hashtable<String,Object>();
                map.put(MenuConstants.RECORD, this.getMainRecord().getClass().getName());
                try {
                    map.put(DBConstants.OBJECT_ID, this.getMainRecord().getHandle(DBConstants.OBJECT_ID_HANDLE).toString());
                } catch (DBException e) {
                    e.printStackTrace();
                }
                
                map.put(DBParams.PROCESS, GetWSDL.class.getName());   // Default
                Application app = (Application)this.getTask().getApplication();
                String strQueueName = MessageConstants.TRX_SEND_QUEUE;
                String strQueueType = MessageConstants.INTRANET_QUEUE;
            
                if (m_intRegistryID == null)
                {
                    BaseMessageManager messageManager = app.getMessageManager();
                    if (messageManager != null)
                    {
                        Object source = this;
                        BaseMessageFilter filter = new BaseMessageFilter(MessageConstants.TRX_RETURN_QUEUE, MessageConstants.INTERNET_QUEUE, source, null);
                        filter.addMessageListener(this);
                        messageManager.addMessageFilter(filter);
                        m_intRegistryID = filter.getRegistryID();
                    }
                }
                if (m_intRegistryID != null)
                    map.put(TrxMessageHeader.REGISTRY_ID, m_intRegistryID);    // The return Queue ID
                BaseMessage message = new MapMessage(new TrxMessageHeader(strQueueName, strQueueType, map), map);
                app.getMessageManager().sendMessage(message);
                
                String strMessage = app.getResources(ResourceConstants.DEFAULT_RESOURCE, true).getString("Remote action queued");
                this.getTask().setStatusText(strMessage, Cursor.WAIT_CURSOR);
            }
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * A record with this datasource handle changed, notify any behaviors that are checking.
     * NOTE: Be very careful as this code is running in an independent thread
     * (synchronize to the task before calling record calls).
     * NOTE: For now, you are only notified of the main record changes.
     * @param message The message to handle.
     * @return The error code.
     */
    public int handleMessage(BaseMessage message)
    {
        if (message != null)
            if (message.getMessageHeader().getRegistryIDMatch() != null)    // My private message
                if (message.getMessageHeader().getRegistryIDMatch().equals(m_intRegistryID))    // My private message
        {
            Application app = (Application)this.getTask().getApplication();
            String strMessage = app.getResources(ResourceConstants.DEFAULT_RESOURCE, true).getString("Remote action completed");
            this.getTask().setStatusText(strMessage, Constants.INFORMATION);
            message.consume();
        }
        return super.handleMessage(message);
    }

}
