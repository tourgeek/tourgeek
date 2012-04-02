/**
 * @(#)TrxDescStatusHeaderScreen.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.screen.trx;

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
import com.tourapp.tour.genled.db.*;

/**
 *  TrxDescStatusHeaderScreen - .
 */
public class TrxDescStatusHeaderScreen extends HeaderScreen
{
    /**
     * Default constructor.
     */
    public TrxDescStatusHeaderScreen()
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
    public TrxDescStatusHeaderScreen(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getScreenRecord().getField(TrxStatusScreenRecord.TRX_SYSTEM_ID).setEnabled(true);
        this.getScreenRecord().getField(TrxStatusScreenRecord.TRX_DESC_ID).setEnabled(true);
        
        if (this.getScreenRecord().getField(TrxStatusScreenRecord.TRX_DESC_ID).getComponent(0) instanceof SPopupBox)   // Yes
            this.getScreenRecord().getField(TrxStatusScreenRecord.TRX_SYSTEM_ID).addListener(new FieldReSelectHandler((SPopupBox)this.getScreenRecord().getField(TrxStatusScreenRecord.TRX_DESC_ID).getComponent(0)));
        Record rec = this.getRecord(TrxDesc.TRX_DESC_FILE);
        this.getRecord(TrxDesc.TRX_DESC_FILE).setKeyArea(TrxDesc.TRX_SYSTEM_ID_KEY);
        this.getRecord(TrxDesc.TRX_DESC_FILE).addListener(new SubFileFilter(this.getScreenRecord().getField(TrxStatusScreenRecord.TRX_SYSTEM_ID), TrxDesc.TRX_SYSTEM_ID, null, null, null, null, false));
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getScreenRecord().getField(TrxStatusScreenRecord.TRX_SYSTEM_ID).setupTablePopup(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY, this.getRecord(TrxSystem.TRX_SYSTEM_FILE), true);
        this.getScreenRecord().getField(TrxStatusScreenRecord.TRX_DESC_ID).setupTablePopup(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY, this.getRecord(TrxDesc.TRX_DESC_FILE), true);
    }

}
