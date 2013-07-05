/**
  * @(#)PaymentRequestScreen.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctpay.screen.pymtreq;

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
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.assetdr.db.*;
import com.tourgeek.tour.acctpay.screen.genpymt.*;
import com.tourgeek.tour.acctpay.screen.check.*;
import org.jbundle.main.screen.*;

/**
 *  PaymentRequestScreen - Payment Request.
 */
public class PaymentRequestScreen extends DetailScreen
{
    /**
     * Default constructor.
     */
    public PaymentRequestScreen()
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
    public PaymentRequestScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Payment Request";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new PaymentRequest(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ApControl(this);
        new ApTrx(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new PaymentRequestScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        ((ReferenceField)this.getScreenRecord().getField(PaymentRequestScreenRecord.BANK_ACCT_ID)).makeReferenceRecord(this);  // Make sure this record is referenced
        this.getScreenRecord().getField(PaymentRequestScreenRecord.BANK_ACCT_ID).addListener(new InitFieldHandler(this.getRecord(ApControl.AP_CONTROL_FILE).getField(ApControl.AP_BANK_ACCT_ID)));
        
        this.getMainRecord().addListener(new SubFileFilter(this.getHeaderRecord()));
        FieldListener listener = new ReadSecondaryHandler(this.getHeaderRecord());
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.getScreenRecord().getField(PaymentRequestScreenRecord.BANK_ACCT_ID).addListener(listener);
        
        ((ReferenceField)this.getMainRecord().getField(PaymentRequest.VENDOR_ID)).makeReferenceRecord(this);  // Make sure this record is referenced
        
        // This code does the selection count to determine the default value.
        Record recVendor = new Vendor(this);
        listener = new ReadSecondaryHandler(recVendor);
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.getMainRecord().getField(PaymentRequest.VENDOR_ID).addListener(listener);
        
        this.getMainRecord().getField(PaymentRequest.VENDOR_ID).addListener(new CheckVendorCurrency(Vendor.CURRENCYS_ID, this.getHeaderRecord().getField(BankAcct.CURRENCY_ID)));
        
        recVendor.addListener(new RecountOnValidHandler(this.getRecord(ApTrx.AP_TRX_FILE)));
        this.getRecord(ApTrx.AP_TRX_FILE).addListener(new SubFileFilter(recVendor));
        this.getRecord(ApTrx.AP_TRX_FILE).addListener(new SubCountHandler(this.getRecord(PaymentRequest.PAYMENT_REQUEST_FILE).getField(PaymentRequest.AMOUNT), ApTrx.AMOUNT_SELECTED, false, true));
        
        this.getRecord(PaymentRequest.PAYMENT_REQUEST_FILE).addListener(new SubCountHandler(this.getScreenRecord().getField(PaymentRequestScreenRecord.REQUEST_TOTAL), PaymentRequest.AMOUNT, false, true));
        
        this.getScreenRecord().getField(PaymentRequestScreenRecord.MANUAL_CHECKS).setState(true); // Allow manual check entry
        this.getScreenRecord().getField(PaymentRequestScreenRecord.MANUAL_CHECKS).addListener(new DisableOnFieldHandler(this.getMainRecord().getField(PaymentRequest.CHECK_NO), BooleanField.NO, true));
    }
    /**
     * Open the header record.
     * @return The new header record.
     */
    public Record openHeaderRecord()
    {
        return new BankAcct(this);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(PaymentRequest.PAYMENT_REQUEST_FILE).getField(PaymentRequest.VENDOR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PaymentRequest.PAYMENT_REQUEST_FILE).getField(PaymentRequest.AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PaymentRequest.PAYMENT_REQUEST_FILE).getField(PaymentRequest.COMMENTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PaymentRequest.PAYMENT_REQUEST_FILE).getField(PaymentRequest.CHECK_NO).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        BaseApplication application = (BaseApplication)getTask().getApplication();
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString("Print checks"), MenuConstants.PRINT, MenuConstants.PRINT, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString("Generalized selection"), MenuConstants.SELECT, MenuConstants.SELECT, null);
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
        BaseField fldBankAcctID = this.getScreenRecord().getField(PaymentRequestScreenRecord.BANK_ACCT_ID);
        if (strCommand.equalsIgnoreCase(MenuConstants.PRINT))
        {
            strCommand = Utility.addURLParam(null, DBParams.SCREEN, PrintCheckJournal.class.getName());
            strCommand = Utility.addURLParam(strCommand, fldBankAcctID.getFieldName(), fldBankAcctID.getData().toString());
        }
        else if (strCommand.equalsIgnoreCase(MenuConstants.SELECT))
        {
            strCommand = Utility.addURLParam(null, DBParams.SCREEN, GenPaymentSelect.class.getName());
            strCommand = Utility.addURLParam(strCommand, fldBankAcctID.getFieldName(), fldBankAcctID.toString());
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new PaymentRequestHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}
