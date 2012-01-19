/**
 * @(#)CurrencyReqHeaderScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.currreq;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.acctpay.report.curreq.*;
import com.tourapp.tour.base.db.*;

/**
 *  CurrencyReqHeaderScreen - .
 */
public class CurrencyReqHeaderScreen extends HeaderScreen
{
    /**
     * Default constructor.
     */
    public CurrencyReqHeaderScreen()
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
    public CurrencyReqHeaderScreen(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
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
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getScreenRecord().getField(CurrencyReqScreenRecord.kCurrencysID).setEnabled(true);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kCurrencysID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kBalanceTotal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CurrencyReqScreenRecord.kCurrencyReqScreenRecordFile).getField(CurrencyReqScreenRecord.kTotalTotal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
