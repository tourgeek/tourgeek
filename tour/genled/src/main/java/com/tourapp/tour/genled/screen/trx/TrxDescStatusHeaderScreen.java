/**
 * @(#)TrxDescStatusHeaderScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.main.screen.*;
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
    public TrxDescStatusHeaderScreen(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
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
        this.getScreenRecord().getField(TrxStatusScreenRecord.kTrxSystemID).setEnabled(true);
        this.getScreenRecord().getField(TrxStatusScreenRecord.kTrxDescID).setEnabled(true);
        
        if (this.getRecord(TrxStatusScreenRecord.kTrxStatusScreenRecordFile).getField(TrxStatusScreenRecord.kTrxDescID).getComponent(0) instanceof SPopupBox)   // Yes
            this.getScreenRecord().getField(TrxStatusScreenRecord.kTrxSystemID).addListener(new FieldReSelectHandler((SPopupBox)this.getRecord(TrxStatusScreenRecord.kTrxStatusScreenRecordFile).getField(TrxStatusScreenRecord.kTrxDescID).getComponent(0)));
        Record rec = this.getRecord(TrxDesc.kTrxDescFile);
        this.getRecord(TrxDesc.kTrxDescFile).setKeyArea(TrxDesc.kTrxSystemIDKey);
        this.getRecord(TrxDesc.kTrxDescFile).addListener(new SubFileFilter(this.getScreenRecord().getField(TrxStatusScreenRecord.kTrxSystemID), TrxDesc.kTrxSystemID, null, -1, null, -1, false));
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(TrxStatusScreenRecord.kTrxStatusScreenRecordFile).getField(TrxStatusScreenRecord.kTrxSystemID).setupTablePopup(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY, this.getRecord(TrxSystem.kTrxSystemFile), true);
        this.getRecord(TrxStatusScreenRecord.kTrxStatusScreenRecordFile).getField(TrxStatusScreenRecord.kTrxDescID).setupTablePopup(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY, this.getRecord(TrxDesc.kTrxDescFile), true);
    }

}
