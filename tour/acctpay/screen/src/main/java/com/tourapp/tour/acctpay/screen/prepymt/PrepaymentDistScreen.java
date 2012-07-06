/**
  * @(#)PrepaymentDistScreen.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import com.tourapp.tour.acctpay.screen.findepest.*;
import com.tourapp.tour.acctpay.screen.trx.*;

/**
 *  PrepaymentDistScreen - Prepayment distribution.
 */
public class PrepaymentDistScreen extends Screen
{
    public static final String DISTRIBUTE = "Distribute";
    protected ApTrx m_recSelectApTrx = null;
    /**
     * Default constructor.
     */
    public PrepaymentDistScreen()
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
    public PrepaymentDistScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        m_recSelectApTrx = null;
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Prepayment distribution";
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
        new TransactionType(this);
        
        new PaymentHistory(this);
        new AcctDetail(this);
        new AcctDetailDist(this);
        new Period(this);
        new ApControl(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.addMainKeyBehavior();
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        
        EnableScreenHandler behavior = new EnableScreenHandler(ApTrx.TRX_STATUS_ID);
        this.getMainRecord().addListener(behavior);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.PREPAYMENT);
        behavior.addComparison(recTrxStatus.getField(TrxStatus.ID).getData());
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.DEBIT_MEMO);
        behavior.addComparison(recTrxStatus.getField(TrxStatus.ID).getData());
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.BROKER_PAYMENT);
        behavior.addComparison(recTrxStatus.getField(TrxStatus.ID).getData());
        
        m_recSelectApTrx = ((Vendor)this.getRecord(Vendor.VENDOR_FILE)).addSelectBehaviors();
        FilterApTrxHandler filter = new FilterApTrxHandler(null);
        m_recSelectApTrx.addListener(filter);
        filter.addTrxStatusID(ApTrx.INVOICE);
        filter.addTrxStatusID(ApTrx.INVOICE_NON_TOUR);
        filter.addTrxStatusID(ApTrx.CREDIT_MEMO);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        String strDistribute = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(PrepaymentDistScreen.DISTRIBUTE);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, strDistribute, MenuConstants.POST, PrepaymentDistScreen.DISTRIBUTE, null);
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
        this.getMainRecord().getField(ApTrx.TRX_STATUS_ID).setEnabled(false);
        this.getMainRecord().getField(ApTrx.ID).setEnabled(true);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(ApTrx.VENDOR_ID)).getReferenceRecord(this);
        if (recVendor != null)
        {
            Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReferenceRecord(this);
            recVendor.getField(Vendor.CURRENCYS_ID).addListener(new ReadSecondaryHandler(recCurrencys));
        }
        ScreenField screenField = null;
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        screenField = new SButtonBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, null, MenuConstants.LOOKUP, MenuConstants.LOOKUP, null);
        screenField.setRequestFocusEnabled(false);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.TRX_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.VENDOR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_BALANCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.AMOUNT_SELECTED).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        String strDistribute = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(PrepaymentDistScreen.DISTRIBUTE);
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, strDistribute, MenuConstants.POST, PrepaymentDistScreen.DISTRIBUTE, null);
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
        if (MenuConstants.LOOKUP.equalsIgnoreCase(strCommand))
        {
            iCommandOptions = ScreenConstants.USE_NEW_WINDOW | ScreenConstants.DONT_PUSH_TO_BROSWER;
            boolean bReadCurrentRecord = false;
            int iDocMode = ApTrx.VENDOR_AP_SCREEN | ScreenConstants.SELECT_MODE;
            Map<String,Object> properties = new Hashtable<String,Object>();
            properties.put(ApTrxClassField.DISPLAY_TYPE_PARAM, Integer.toString(ApTrxClassField.PREPAYMENTS));
            if (!this.getMainRecord().getField(ApTrx.VENDOR_ID).isNull())
                properties.put(DBParams.HEADER_OBJECT_ID, this.getMainRecord().getField(ApTrx.VENDOR_ID).toString());
        
            return (this.onForm(null, iDocMode, bReadCurrentRecord, iCommandOptions, properties) != null);
        }
        else if (strCommand.equalsIgnoreCase(PrepaymentDistScreen.DISTRIBUTE))
            return this.distribute();
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Distribute this prepayment to the selected amounts for this vendor.
     */
    public boolean distribute()
    {
        ApTrx recApTrx = (ApTrx)this.getMainRecord();
        PaymentHistory recPaymentHistory = (PaymentHistory)this.getRecord(PaymentHistory.PAYMENT_HISTORY_FILE);
        return recPaymentHistory.distribute(this, recApTrx, m_recSelectApTrx);
    }

}
