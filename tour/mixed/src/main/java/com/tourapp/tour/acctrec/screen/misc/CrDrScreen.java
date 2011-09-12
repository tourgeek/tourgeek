/**
 * @(#)CrDrScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.misc;

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
import com.tourapp.tour.booking.entry.acctrec.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctrec.db.event.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  CrDrScreen - Debit/Credit entry.
 */
public class CrDrScreen extends BookingArTrxScreen
{
    /**
     * Default constructor.
     */
    public CrDrScreen()
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
    public CrDrScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Debit/Credit entry";
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new TrxStatus(this);
        new ArControl(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new CrDrScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getMainRecord().getListener(UpdateArTrxAcctDetailHandler.class, true).setEnabledListener(false);    // Since I will be doing the updating
        this.getMainRecord().addListener(new UpdateCrDrAcctDetailHandler(this.getRecord(Booking.kBookingFile)));
        
        try {
            this.getMainRecord().addNew();
        } catch (DBException e) {
        }
        this.setEnabled(true);
        this.getMainRecord().getField(ArTrx.kTrxStatusID).setEnabled(false);
        this.getMainRecord().getField(ArTrx.kAmount).addListener(new SetCrDrTypeHandler(null));
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        super.setupSFields();
        this.getRecord(CrDrScreenRecord.kCrDrScreenRecordFile).getField(CrDrScreenRecord.kCounterAccountID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
