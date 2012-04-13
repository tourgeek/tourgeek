/**
 * @(#)PrepaymentScreen.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.prepymt;

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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctpay.screen.findepest.*;
import com.tourapp.tour.acctpay.screen.trx.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.db.event.*;

/**
 *  PrepaymentScreen - Prepayments.
 */
public class PrepaymentScreen extends Screen
{
    /**
     * Default constructor.
     */
    public PrepaymentScreen()
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
    public PrepaymentScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Prepayments";
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
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new TrxStatus(this);
        
        new ApControl(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.addMainKeyBehavior();
        
        EnableScreenHandler behavior = new EnableScreenHandler(ApTrx.TRX_STATUS_ID);
        this.getMainRecord().addListener(behavior);
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.PREPAYMENT_REQUEST);
        this.getMainRecord().getField(ApTrx.TRX_STATUS_ID).addListener(new InitFieldHandler(recTrxStatus.getField(TrxStatus.ID)));
        behavior.addComparison(recTrxStatus.getField(TrxStatus.ID).getData());
        
        Record recTour = ((ReferenceField)this.getMainRecord().getField(ApTrx.TOUR_ID)).getReferenceRecord(this);
        this.getMainRecord().getField(ApTrx.TOUR_ID).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.START_SERVICE_DATE), recTour.getField(Tour.DEPARTURE_DATE)));
        this.getMainRecord().getField(ApTrx.TOUR_ID).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.DESCRIPTION), recTour.getField(Tour.DESCRIPTION))
        {
            public int fieldChanged(boolean bDisplayOption, int iMoveMode)
            {
                int iErrorCode = super.fieldChanged(bDisplayOption, iMoveMode);
                if (iErrorCode == DBConstants.NORMAL_RETURN)
                {
                    BaseApplication application = (BaseApplication)getTask().getApplication();
                    String strPrepaymentFor = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString("Prepayment for");
                    String strValue = strPrepaymentFor + ' ' + m_fldDest.toString();
                    m_fldDest.setString(strValue, bDisplayOption, iMoveMode);
                }
                return iErrorCode;
            }
        });
        // Make sure prepayment is fully selected for payment
        this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT).addListener(new ForceSignHandler(+1));
        this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.AMOUNT_SELECTED), this.getMainRecord().getField(ApTrx.INVOICE_AMOUNT)));
        
        // Remember on prepayments, the USD amounts are caclulated based on the exchange of the funding source (the bank trx).
        
        // Default prepayment account to Account in control file
        this.getMainRecord().getField(ApTrx.TOUR_ID).addListener(new PrepaymentAcctHandler(null));
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(ApTrx.VENDOR_ID)).getReferenceRecord(this);
        if (recVendor != null)
        {    // Make sure currency is read for LocalCurrencyField(s).
            Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReferenceRecord(this);
            recVendor.getField(Vendor.CURRENCYS_ID).addListener(new ReadSecondaryHandler(recCurrencys));
        }
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.VENDOR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.TOUR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.START_SERVICE_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.DESCRIPTION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        Converter converter = this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_AMOUNT);
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        String strPrepaymentAmt = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString("Prepayment amt");
        converter = new FieldDescConverter(converter, strPrepaymentAmt);
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.ACCOUNT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.PREPAYMENT_AP_TRX_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        String strVendor = Vendor.VENDOR_FILE + ' ' + MenuConstants.LOOKUP;
        strVendor = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(strVendor);
        String strTour = Tour.TOUR_FILE + ' ' + MenuConstants.LOOKUP;
        strTour = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(strTour);
        new SButtonBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, strVendor, MenuConstants.LOOKUP, Vendor.VENDOR_FILE, null);
        new SButtonBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, strTour, MenuConstants.LOOKUP, Tour.TOUR_FILE, null);
    }
    /**
     * Enable or disable this control.
     * @param bEnable If true, enable this field.
     */
    public void setEnabled(boolean bEnable)
    {
        // Don't call inherrited
        for (int i = 0; i < this.getMainRecord().getFieldCount(); i++)
        {
            this.getMainRecord().getField(i).setEnabled(bEnable);
        }
        this.getMainRecord().getField(ApTrx.ID).setEnabled(true);
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
        if ((MenuConstants.LOOKUP.equalsIgnoreCase(strCommand))
            || (Vendor.VENDOR_FILE.equalsIgnoreCase(strCommand))
            || (Tour.TOUR_FILE.equalsIgnoreCase(strCommand)))
        {
            Record recordMain = this.getMainRecord();
        
            ScreenLocation itsLocation = null;
            BasePanel parentScreen = Screen.makeWindow(this.getTask().getApplication());
            Task task = parentScreen.getTask();//getAppletScreen().getScreenFieldView().getControl();
            task.setProperty("DisplayType", Integer.toString(ApTrxClassField.PREPAYMENTS));
            int iDocMode = ApTrx.TOUR_AP_SCREEN;
            if (strCommand.equalsIgnoreCase(Vendor.VENDOR_FILE))
                iDocMode = ApTrx.VENDOR_AP_SCREEN;
            iDocMode = iDocMode | ScreenConstants.SELECT_MODE;
        
            boolean bCloneThisQuery = true;
            boolean bReadCurrentRecord = false;
            boolean bUseBaseTable = true;
            boolean bLinkGridToQuery = true;
            this.finalizeThisScreen();  // Validate current control, update record, get ready to close screen.
            ScreenParent screen = recordMain.makeScreen(itsLocation, parentScreen, iDocMode, bCloneThisQuery, bReadCurrentRecord, bUseBaseTable, bLinkGridToQuery, null);
            return true;    // Success
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
