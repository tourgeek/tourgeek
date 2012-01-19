/**
 * @(#)BaseArPayDistHeaderScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.mco;

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
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.acctrec.screen.cash.*;

/**
 *  BaseArPayDistHeaderScreen - Mco/Credit Card dist header screen.
 */
public class BaseArPayDistHeaderScreen extends HeaderScreen
{
    /**
     * Default constructor.
     */
    public BaseArPayDistHeaderScreen()
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
    public BaseArPayDistHeaderScreen(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
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
        return "Mco/Credit Card dist header screen";
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.setEditing(false);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record recHeader = this.getRecord(Mco.kMcoFile);
        if (recHeader == null)
            recHeader = this.getRecord(CreditCard.kCreditCardFile);
        recHeader.getField(BaseArPay.kNet).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashBatchScreenRecord.kCashBatchScreenRecordFile).getField(CashBatchScreenRecord.kChangeBalance).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashBatchScreenRecord.kCashBatchScreenRecordFile).getField(CashBatchScreenRecord.kEndBalance).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
