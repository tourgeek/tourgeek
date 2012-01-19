/**
 * @(#)BankReconHeaderScreen.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.assetdr.db.*;

/**
 *  BankReconHeaderScreen - Header Screen.
 */
public class BankReconHeaderScreen extends HeaderScreen
{
    /**
     * Default constructor.
     */
    public BankReconHeaderScreen()
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
    public BankReconHeaderScreen(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
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
        return "Header Screen";
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.setEditing(false);
        this.getScreenRecord().getField(BankReconScreenRecord.kBankAcctID).setEnabled(true);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(BankReconScreenRecord.kBankReconScreenRecordFile).getField(BankReconScreenRecord.kBankAcctID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankReconScreenRecord.kBankReconScreenRecordFile).getField(BankReconScreenRecord.kStartCleared).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankReconScreenRecord.kBankReconScreenRecordFile).getField(BankReconScreenRecord.kDepositsCleared).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankReconScreenRecord.kBankReconScreenRecordFile).getField(BankReconScreenRecord.kChecksCleared).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankReconScreenRecord.kBankReconScreenRecordFile).getField(BankReconScreenRecord.kNewCleared).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
