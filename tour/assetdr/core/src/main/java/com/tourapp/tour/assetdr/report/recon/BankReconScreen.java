/**
 * @(#)BankReconScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.report.recon;

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

/**
 *  BankReconScreen - Bank Reconciliation.
 */
public class BankReconScreen extends Screen
{
    /**
     * Default constructor.
     */
    public BankReconScreen()
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
    public BankReconScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Bank Reconciliation";
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
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.setAppending(false);
        this.setEnabled(false);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.DATE_RECONCILED).setEnabled(true);
        this.getScreenRecord().getField(BankReconScreenRecord.BANK_ACCT_ID).moveFieldToThis(this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.BANK_ACCT_ID));
        //xthis.getScreenRecord().getField(BankReconScreenRecord.BANK_ACCT_ID).addListener(new InitFieldHandler(this.getRecord(AssetDrControl.ASSET_DR_CONTROL_FILE).getField(AssetDrControl.BANK_ACCT_ID)));
        
        this.getMainRecord().setKeyArea(BankTrx.TRX_DATE_KEY);
        this.getMainRecord().addListener(new SubFileFilter(this.getScreenRecord().getField(BankReconScreenRecord.BANK_ACCT_ID), BankTrx.BANK_ACCT_ID, null, null, null, null));
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        Converter field = this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.DATE_RECONCILED);
        BaseField fldTargetValue = this.getScreenRecord().getField(BankReconScreenRecord.RECON_DATE);
        Converter converter = new ReconciledConverter(field, fldTargetValue, "Reconciled?", true);
        new SCheckBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, converter, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.TRX_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.TRX_NUMBER).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.TRX_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.PAYEE_NAME).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.COMMENTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.MANUAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new BankReconScreenRecord(this);
    }

}
