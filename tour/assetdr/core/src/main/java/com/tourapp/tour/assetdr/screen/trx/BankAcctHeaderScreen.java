/**
 * @(#)BankAcctHeaderScreen.
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
import java.util.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.assetdr.db.*;

/**
 *  BankAcctHeaderScreen - Header screen for acct detail inquiry.
 */
public class BankAcctHeaderScreen extends HeaderScreen
{
    /**
     * Default constructor.
     */
    public BankAcctHeaderScreen()
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
    public BankAcctHeaderScreen(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Header screen for acct detail inquiry";
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getRecord(BankTrxScreenRecord.kBankTrxScreenRecordFile).getField(BankTrxScreenRecord.kBankAcctID).setEnabled(true);
        this.getScreenRecord().getField(BankTrxScreenRecord.kStartDate).addListener(new RegisterValueHandler(null));
        this.getScreenRecord().getField(BankTrxScreenRecord.kDisplayBalance).addListener(new RegisterValueHandler(null));
        if (this.getScreenRecord().getField(BankTrxScreenRecord.kStartDate).isNull())
        {   // If the starting and ending date haven't been set yet, set them to the current period start and end.
            Date date = new Date();     // Now
            Period recPeriod = new Period((BaseScreen)this.getParentScreen()); // Note: I Use READ_MOVE, because RegisterBehavior doesn't respond to it.
            if (this.getScreenRecord().getField(BankTrxScreenRecord.kStartDate).isNull())
                ((DateTimeField)this.getScreenRecord().getField(BankTrxScreenRecord.kStartDate)).setDate(recPeriod.getPeriodStartDate(date), DBConstants.DISPLAY, DBConstants.READ_MOVE);
            recPeriod.free();
            recPeriod = null;
        }
        this.getScreenRecord().getField(BankTrxScreenRecord.kStartDate).setEnabled(true);
        this.getScreenRecord().getField(BankTrxScreenRecord.kDisplayBalance).setEnabled(true);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        // This will synchronize the screen field and make sure the lookup uses the header record
        ((ReferenceField)this.getRecord(BankTrxScreenRecord.kBankTrxScreenRecordFile).getField(BankTrxScreenRecord.kBankAcctID)).setReference(this.getRecord(BankAcct.kBankAcctFile), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getRecord(BankTrxScreenRecord.kBankTrxScreenRecordFile).getField(BankTrxScreenRecord.kBankAcctID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxScreenRecord.kBankTrxScreenRecordFile).getField(BankTrxScreenRecord.kStartDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxScreenRecord.kBankTrxScreenRecordFile).getField(BankTrxScreenRecord.kDisplayBalance).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxScreenRecord.kBankTrxScreenRecordFile).getField(BankTrxScreenRecord.kEndBalance).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
