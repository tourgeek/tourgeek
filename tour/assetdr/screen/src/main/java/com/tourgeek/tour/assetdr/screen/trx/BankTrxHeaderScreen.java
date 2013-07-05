/**
  * @(#)BankTrxHeaderScreen.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.assetdr.screen.trx;

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
import com.tourgeek.tour.genled.screen.detail.*;
import com.tourgeek.tour.assetdr.db.*;

/**
 *  BankTrxHeaderScreen - Header Screen for Bank Trx dist..
 */
public class BankTrxHeaderScreen extends HeaderScreen
{
    /**
     * Default constructor.
     */
    public BankTrxHeaderScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public BankTrxHeaderScreen(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Header Screen for Bank Trx dist.";
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.BANK_ACCT_ID).setEnabled(true);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.TRX_NUMBER).setEnabled(true);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.ID).setEnabled(true);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.TRX_NUMBER).addListener(new MainFieldHandler(BankTrx.TRX_NUMBER_KEY));
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.BANK_ACCT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.TRX_NUMBER).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        ScreenField screenField = new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.ID), ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, null, MenuConstants.LOOKUP, MenuConstants.LOOKUP, null);
        screenField.setRequestFocusEnabled(false);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.TRX_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.TRX_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.PAYEE_NAME).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
            iCommandOptions = ScreenConstants.USE_NEW_WINDOW | ScreenConstants.DONT_PUSH_TO_BROWSER;
            boolean bReadCurrentRecord = false;
            int iDocMode = ScreenConstants.DISPLAY_MODE | ScreenConstants.SELECT_MODE;
            Map<String,Object> properties = new Hashtable<String,Object>();
            if (!this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.BANK_ACCT_ID).isNull())
                properties.put(DBParams.HEADER_OBJECT_ID, this.getRecord(BankTrx.BANK_TRX_FILE).getField(BankTrx.BANK_ACCT_ID).toString());
        
            return (this.onForm(this.getRecord(BankTrx.BANK_TRX_FILE), iDocMode, bReadCurrentRecord, iCommandOptions, properties) != null);
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
