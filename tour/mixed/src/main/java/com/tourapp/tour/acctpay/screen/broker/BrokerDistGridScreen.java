/**
 * @(#)BrokerDistGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.broker;

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
import org.jbundle.main.screen.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.screen.trx.*;
import com.tourapp.tour.base.db.*;

/**
 *  BrokerDistGridScreen - Broker distribution.
 */
public class BrokerDistGridScreen extends DetailGridScreen
{
    /**
     * Default constructor.
     */
    public BrokerDistGridScreen()
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
    public BrokerDistGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * Constructor.
     */
    public BrokerDistGridScreen(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
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
        return new BrokerScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        ((ReferenceField)this.getScreenRecord().getField(BrokerScreenRecord.kVendorID)).setReference(this.getRecord(Vendor.kVendorFile));
        this.getScreenRecord().getField(BrokerScreenRecord.kVendorID).addListener(new ReadSecondaryHandler(this.getRecord(Vendor.kVendorFile)));
        if (this.getScreenRecord().getField(BrokerScreenRecord.kVendorID).isNull())
            this.getScreenRecord().getField(BrokerScreenRecord.kVendorID).moveFieldToThis(this.getRecord(ApControl.kApControlFile).getField(ApControl.kBrokerVendorID));
        this.getScreenRecord().getField(BrokerScreenRecord.kVendorID).addListener(new FieldReSelectHandler(this));
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.BROKER_PAYMENT_HEADER);
        this.getMainRecord().getField(ApTrx.kTrxStatusID).addListener(new InitFieldHandler(recTrxStatus.getField(TrxStatus.kID)));
        
        // Make sure draft is fully selected for payment
        this.getMainRecord().getField(ApTrx.kInvoiceLocal).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.kAmountSelected), this.getMainRecord().getField(ApTrx.kInvoiceLocal)));
        
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
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDraftVendorID).addListener(listener);
        recVendor2.addListener(new RecountOnValidHandler(recApTrx2));
        recApTrx2.addListener(new SubFileFilter(recVendor2));
        recApTrx2.addListener(new SubCountHandler(this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceAmount), ApTrx.kAmountSelected, false, true));
        
        this.getRecord(ApTrx.kApTrxFile).removeListener(this.getRecord(ApTrx.kApTrxFile).getListener(NoDeleteModifyHandler.class.getName()), true);
        
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(ApTrx.kDraftVendorID)).getReferenceRecord(this);
        if (recVendor != null)
        {
            Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.kCurrencysID)).getReferenceRecord(this);
            this.getMainRecord().getField(ApTrx.kDraftVendorID).addListener(new MoveOnChangeHandler(recApTrx.getField(ApTrx.kDepartureExchange), recCurrencys.getField(Currencys.kLastRate)));
        }
        recApTrx.getField(ApTrx.kDepartureExchange).addListener(new CalcBalanceHandler(recApTrx.getField(ApTrx.kInvoiceLocal), recApTrx.getField(ApTrx.kDepartureExchange), recApTrx.getField(ApTrx.kInvoiceAmount), CalcBalanceHandler.MULTIPLY, true));
        recApTrx.getField(ApTrx.kInvoiceAmount).addListener(new CalcBalanceHandler(recApTrx.getField(ApTrx.kInvoiceLocal), recApTrx.getField(ApTrx.kDepartureExchange), recApTrx.getField(ApTrx.kInvoiceAmount), CalcBalanceHandler.MULTIPLY, true));
        //?recApTrx.getField(ApTrx.kInvoiceUSD).addListener(new CalcBalanceHandler(recApTrx.getField(ApTrx.kDepartureExchange), recApTrx.getField(ApTrx.kInvoiceLocal), recApTrx.getField(ApTrx.kInvoiceAmount), CalcBalanceHandler.DIVIDE, true));
        
        recApTrx.getField(ApTrx.kAccountID).addListener(new InitFieldHandler(this.getRecord(ApControl.kApControlFile).getField(ApControl.kPrepayAccountID)));
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(ApTrx.kDraftVendorID)).getReferenceRecord(this);
        if (recVendor != null)
        {
            Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.kCurrencysID)).getReferenceRecord(this);
            recVendor.getField(Vendor.kCurrencysID).addListener(new ReadSecondaryHandler(recCurrencys));
        }
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDraftVendorID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDepartureExchange).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * If there is a header record, return it, otherwise, return the main record.
     * The header record is the (optional) main record on gridscreens and is sometimes used
     * to enter data in a sub-record when a header is required.
     * @return The header record.
     */
    public Record getHeaderRecord()
    {
        return this.getRecord(Vendor.kVendorFile);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new BrokerHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
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
