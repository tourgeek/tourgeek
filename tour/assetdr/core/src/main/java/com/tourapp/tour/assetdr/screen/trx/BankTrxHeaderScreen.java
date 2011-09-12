/**
 * @(#)BankTrxHeaderScreen.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.genled.screen.detail.*;
import com.tourapp.tour.assetdr.db.*;

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
    public BankTrxHeaderScreen(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
    {
        this();
        this.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
    {
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc);
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
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kBankAcctID).setEnabled(true);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kTrxNumber).setEnabled(true);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kID).setEnabled(true);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kTrxNumber).addListener(new MainFieldHandler(BankTrx.kTrxNumberKey));
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kBankAcctID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kTrxNumber).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        ScreenField screenField = new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kID), ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, null, MenuConstants.LOOKUP, MenuConstants.LOOKUP, null);
        screenField.setRequestFocusEnabled(false);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kTrxStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kTrxDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kPayeeName).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
            int iDocMode = ScreenConstants.DISPLAY_MODE | ScreenConstants.SELECT_MODE;
            Map<String,Object> properties = new Hashtable<String,Object>();
            if (!this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kBankAcctID).isNull())
                properties.put(DBParams.HEADER_OBJECT_ID, this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kBankAcctID).toString());
        
            return (this.onForm(this.getRecord(BankTrx.kBankTrxFile), iDocMode, bReadCurrentRecord, iCommandOptions, properties) != null);
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
