/**
 * @(#)AccountHeaderScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.screen.detail;

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

/**
 *  AccountHeaderScreen - Header screen for acct detail inquiry.
 */
public class AccountHeaderScreen extends HeaderScreen
{
    /**
     * Default constructor.
     */
    public AccountHeaderScreen()
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
    public AccountHeaderScreen(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        this.getRecord(AcctDetailScreenRecord.kAcctDetailScreenRecordFile).getField(AcctDetailScreenRecord.kAccountID).setEnabled(true);
        this.getRecord(Account.kAccountFile).getField(Account.kAccountNo).setEnabled(true);   // Enable Key Field
        this.getScreenRecord().getField(AcctDetailScreenRecord.kStartDate).addListener(new RegisterValueHandler(null));
        this.getScreenRecord().getField(AcctDetailScreenRecord.kEndDate).addListener(new RegisterValueHandler(null));
        this.getScreenRecord().getField(AcctDetailScreenRecord.kCalcStart).addListener(new RegisterValueHandler(null));
        if ((this.getScreenRecord().getField(AcctDetailScreenRecord.kStartDate).isNull()) ||
            (this.getScreenRecord().getField(AcctDetailScreenRecord.kEndDate).isNull()))
        {   // If the starting and ending date haven't been set yet, set them to the current period start and end.
            Date date = new Date();     // Now
            Period recPeriod = new Period((RecordOwner)this.getParentScreen()); // Note: I Use READ_MOVE, because RegisterBehavior doesn't respond to it.
            if (this.getScreenRecord().getField(AcctDetailScreenRecord.kStartDate).isNull())
                ((DateTimeField)this.getScreenRecord().getField(AcctDetailScreenRecord.kStartDate)).setDate(recPeriod.getPeriodStartDate(date), DBConstants.DISPLAY, DBConstants.READ_MOVE);
            if (this.getScreenRecord().getField(AcctDetailScreenRecord.kEndDate).isNull())
                ((DateTimeField)this.getScreenRecord().getField(AcctDetailScreenRecord.kEndDate)).setDate(recPeriod.getPeriodEndDate(date), DBConstants.DISPLAY, DBConstants.READ_MOVE);
            recPeriod.free();
            recPeriod = null;
        }
        this.getScreenRecord().getField(AcctDetailScreenRecord.kStartDate).setEnabled(true);
        this.getScreenRecord().getField(AcctDetailScreenRecord.kEndDate).setEnabled(true);
        this.getScreenRecord().getField(AcctDetailScreenRecord.kCalcStart).setEnabled(true);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        // This will synchronize the screen field and make sure the lookup uses the header record
        ((ReferenceField)this.getRecord(AcctDetailScreenRecord.kAcctDetailScreenRecordFile).getField(AcctDetailScreenRecord.kAccountID)).setReference(this.getRecord(Account.kAccountFile), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getRecord(AcctDetailScreenRecord.kAcctDetailScreenRecordFile).getField(AcctDetailScreenRecord.kAccountID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetailScreenRecord.kAcctDetailScreenRecordFile).getField(AcctDetailScreenRecord.kStartDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetailScreenRecord.kAcctDetailScreenRecordFile).getField(AcctDetailScreenRecord.kStartBalance).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetailScreenRecord.kAcctDetailScreenRecordFile).getField(AcctDetailScreenRecord.kCalcStart).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetailScreenRecord.kAcctDetailScreenRecordFile).getField(AcctDetailScreenRecord.kEndDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetailScreenRecord.kAcctDetailScreenRecordFile).getField(AcctDetailScreenRecord.kEndBalance).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetailScreenRecord.kAcctDetailScreenRecordFile).getField(AcctDetailScreenRecord.kChangeBalance).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
