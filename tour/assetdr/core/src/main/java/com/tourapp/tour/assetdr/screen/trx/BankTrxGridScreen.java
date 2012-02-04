/**
 * @(#)BankTrxGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.screen.trx;

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
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  BankTrxGridScreen - Checking account desc..
 */
public class BankTrxGridScreen extends DetailGridScreen
{
    /**
     * Default constructor.
     */
    public BankTrxGridScreen()
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
    public BankTrxGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Checking account desc.";
    }
    /**
     * Constructor.
     */
    public BankTrxGridScreen(Record recBankAcct, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recBankAcct, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new BankTrx(this);
    }
    /**
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        return new BankAcct(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        SubFileFilter listener = new SubFileFilter(this.getScreenRecord().getField(BankTrxScreenRecord.START_DATE), BankTrx.TRX_DATE, null, null, null, null);
        listener.setEndKey(false);
        this.getMainRecord().addListener(listener);
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(BankTrxScreenRecord.CHANGE_BALANCE), BankTrx.AMOUNT, false, true));  // Init this field override for other value
        
        this.getScreenRecord().getField(BankTrxScreenRecord.BANK_ACCT_ID).moveFieldToThis(this.getHeaderRecord().getField(BankAcct.ID));
        ((ReferenceField)this.getScreenRecord().getField(BankTrxScreenRecord.BANK_ACCT_ID)).setReferenceRecord(this.getHeaderRecord());
        this.getScreenRecord().getField(BankTrxScreenRecord.BANK_ACCT_ID).addListener(new ReadSecondaryHandler(((ReferenceField)this.getScreenRecord().getField(BankTrxScreenRecord.BANK_ACCT_ID)).getReferenceRecord()));
        
        this.getScreenRecord().getField(BankTrxScreenRecord.BANK_ACCT_ID).addListener(new FieldReSelectHandler(this));
        this.getScreenRecord().getField(BankTrxScreenRecord.START_DATE).addListener(new FieldReSelectHandler(this));
        
        this.getScreenRecord().getField(BankTrxScreenRecord.DISPLAY_BALANCE).addListener(new BankTrxCalcBalance(this.getRecord(BankAcct.BANK_ACCT_FILE), this.getScreenRecord().getField(BankTrxScreenRecord.END_BALANCE), this.getScreenRecord().getField(BankTrxScreenRecord.DISPLAY_BALANCE)));
        this.getScreenRecord().getField(BankTrxScreenRecord.BANK_ACCT_ID).addListener(new ChangeOnChangeHandler(this.getScreenRecord().getField(BankTrxScreenRecord.DISPLAY_BALANCE)));
        
        this.setEditing(false);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new BankTrxScreenRecord(this);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.TRX_NUMBER).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.TRX_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.PAYEE_NAME).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.TRX_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.COMMENTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Add the navigation button(s) to the left of the grid row.
     */
    public void addNavButtons()
    {
        if ((m_iDisplayFieldDesc & ScreenConstants.SELECT_MODE) != ScreenConstants.SELECT_MODE)
        {
            BaseApplication application = (BaseApplication)this.getTask().getApplication();
        
            String strPaymentHistory = BankTrx.PAYMENT_DISTRIBUTION;
            strPaymentHistory = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(strPaymentHistory);
            new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, LinkTrx.PAYMENT_HISTORY_ICON, BankTrx.PAYMENT_DISTRIBUTION, strPaymentHistory);
        
            new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, AcctDetailDist.DIST_DISTRIBUTION, AcctDetailDist.DIST_DISTRIBUTION, application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(AcctDetailDist.DIST_DISTRIBUTION));
        }
        super.addNavButtons();  // Next buttons will be "First!"
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(AcctDetailDist.DIST_DISTRIBUTION), AcctDetailDist.DIST_DISTRIBUTION, AcctDetailDist.DIST_DISTRIBUTION, null);
        
        String strPaymentHistory = BankTrx.PAYMENT_DISTRIBUTION;
        strPaymentHistory = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(strPaymentHistory);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, strPaymentHistory, LinkTrx.PAYMENT_HISTORY_ICON, BankTrx.PAYMENT_DISTRIBUTION, null);
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
        if (strCommand.equalsIgnoreCase(AcctDetailDist.DIST_DISTRIBUTION))
            return (this.onForm(null, BankTrx.DISTRIBUTION_SCREEN, true, iCommandOptions, null) != null);
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new BankAcctHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}
