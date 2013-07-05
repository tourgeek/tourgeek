/**
  * @(#)BrokerDistScreen.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctpay.screen.broker;

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
import com.tourgeek.tour.genled.db.*;
import com.tourgeek.tour.acctpay.screen.trx.*;
import com.tourgeek.tour.base.db.*;
import org.jbundle.main.screen.*;

/**
 *  BrokerDistScreen - Broker distribution.
 */
public class BrokerDistScreen extends DetailScreen
{
    /**
     * Default constructor.
     */
    public BrokerDistScreen()
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
    public BrokerDistScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Broker distribution";
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
        new Vendor(this);
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
        return new BrokerScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        ((ReferenceField)this.getScreenRecord().getField(BrokerScreenRecord.VENDOR_ID)).setReference(this.getRecord(Vendor.VENDOR_FILE));
        this.getScreenRecord().getField(BrokerScreenRecord.VENDOR_ID).addListener(new ReadSecondaryHandler(this.getRecord(Vendor.VENDOR_FILE)));
        if (this.getScreenRecord().getField(BrokerScreenRecord.VENDOR_ID).isNull())
            this.getScreenRecord().getField(BrokerScreenRecord.VENDOR_ID).moveFieldToThis(this.getRecord(ApControl.AP_CONTROL_FILE).getField(ApControl.BROKER_VENDOR_ID));
        //xthis.getScreenRecord().getField(BrokerScreenRecord.VENDOR_ID).addListener(new FieldReSelectHandler(this));
        
        this.getMainRecord().addListener(new SubFileFilter(this.getHeaderRecord()));
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.BROKER_PAYMENT_HEADER);
        this.getMainRecord().getField(ApTrx.TRX_STATUS_ID).addListener(new InitFieldHandler(recTrxStatus.getField(TrxStatus.ID)));
        
        // Make sure draft is fully selected for payment
        this.getMainRecord().getField(ApTrx.INVOICE_LOCAL).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.AMOUNT_SELECTED), this.getMainRecord().getField(ApTrx.INVOICE_LOCAL)));
        
        FilterApTrxHandler filter = new FilterApTrxHandler(null);
        this.getMainRecord().addListener(filter);
        filter.addTrxStatusID(ApTrx.BROKER_PAYMENT_HEADER);
        
        // This code does the selection count to determine the default value.
        Record recApTrx = this.getMainRecord();
        Record recVendor2 = new Vendor(this);
        this.removeRecord(recVendor2);
        this.getMainRecord().addListener(new FreeOnFreeHandler(recVendor2));
        Record recApTrx2 = new ApTrx(this);
        this.removeRecord(recApTrx2);
        this.getMainRecord().addListener(new FreeOnFreeHandler(recApTrx2));
        ReadSecondaryHandler listener = new ReadSecondaryHandler(recVendor2);
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.DRAFT_VENDOR_ID).addListener(listener);
        recVendor2.addListener(new RecountOnValidHandler(recApTrx2));
        recApTrx2.addListener(new SubFileFilter(recVendor2));
        recApTrx2.addListener(new SubCountHandler(this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_AMOUNT), ApTrx.AMOUNT_SELECTED, false, true));
        
        this.getRecord(ApTrx.AP_TRX_FILE).removeListener(this.getRecord(ApTrx.AP_TRX_FILE).getListener(NoDeleteModifyHandler.class.getName()), true);
        
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(ApTrx.DRAFT_VENDOR_ID)).getReferenceRecord(this);
        if (recVendor != null)
        {
            Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReferenceRecord(this);
            this.getMainRecord().getField(ApTrx.DRAFT_VENDOR_ID).addListener(new MoveOnChangeHandler(recApTrx.getField(ApTrx.DEPARTURE_EXCHANGE), recCurrencys.getField(Currencys.LAST_RATE)));
        }
        recApTrx.getField(ApTrx.DEPARTURE_EXCHANGE).addListener(new CalcBalanceHandler(recApTrx.getField(ApTrx.INVOICE_LOCAL), recApTrx.getField(ApTrx.DEPARTURE_EXCHANGE), recApTrx.getField(ApTrx.INVOICE_AMOUNT), CalcBalanceHandler.MULTIPLY, true));
        recApTrx.getField(ApTrx.INVOICE_LOCAL).addListener(new CalcBalanceHandler(recApTrx.getField(ApTrx.DEPARTURE_EXCHANGE), recApTrx.getField(ApTrx.INVOICE_LOCAL), recApTrx.getField(ApTrx.INVOICE_AMOUNT), CalcBalanceHandler.DIVIDE, true));
        //?recApTrx.getField(ApTrx.INVOICE_AMOUNT).addListener(new CalcBalanceHandler(recApTrx.getField(ApTrx.INVOICE_LOCAL), recApTrx.getField(ApTrx.DEPARTURE_EXCHANGE), recApTrx.getField(ApTrx.INVOICE_AMOUNT), CalcBalanceHandler.MULTIPLY, true));
        
        recApTrx.getField(ApTrx.ACCOUNT_ID).addListener(new InitFieldHandler(this.getRecord(ApControl.AP_CONTROL_FILE).getField(ApControl.PREPAY_ACCOUNT_ID)));
    }
    /**
     * Open the header record.
     * @return The new header record.
     */
    public Record openHeaderRecord()
    {
        return new Vendor(this);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(ApTrx.DRAFT_VENDOR_ID)).getReferenceRecord(this);
        if (recVendor != null)
        {
            Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReferenceRecord(this);
            recVendor.getField(Vendor.CURRENCYS_ID).addListener(new ReadSecondaryHandler(recCurrencys));
        }
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.DRAFT_VENDOR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.DEPARTURE_EXCHANGE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * If there is a header record, return it, otherwise, return the main record.
     * The header record is the (optional) main record on gridscreens and is sometimes used
     * to enter data in a sub-record when a header is required.
     * @return The header record.
     */
    public Record getHeaderRecord()
    {
        return this.getRecord(Vendor.VENDOR_FILE);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new BrokerHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
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
            return (this.onForm(null, ApTrx.BROKER_DIST_SCREEN, true, iCommandOptions, null) != null);
        else if (strCommand.equalsIgnoreCase(MenuConstants.LOOKUP))
            return (this.onForm(null, ApTrx.BROKER_DIST_GRID_SCREEN, true, iCommandOptions, null) != null);
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
