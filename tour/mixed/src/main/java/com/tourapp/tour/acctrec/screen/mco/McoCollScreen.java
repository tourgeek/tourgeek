/**
 *  @(#)McoCollScreen.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctrec.screen.cash.*;
import com.tourapp.tour.product.air.db.*;

/**
 *  McoCollScreen - Mco carrier collection entry screen.
 */
public class McoCollScreen extends Screen
{
    /**
     * Default constructor.
     */
    public McoCollScreen()
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
    public McoCollScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Mco carrier collection entry screen";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Mco(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ArControl(this);
        new TrxStatus(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getScreenRecord().getField(McoScreenRecord.kAirlineID).addListener(new InitFieldHandler(this.getRecord(ArControl.kArControlFile).getField(ArControl.kAirlineID)));
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, Mco.kMcoFile, Mco.SUBMITTED);
        this.getMainRecord().addListener(new SubFileFilter(recTrxStatus));
        
        this.getMainRecord().addListener(new McoCollCalcNetBeh(null));
        
        this.getMainRecord().addListener(new CompareFileFilter(Mco.kAirlineID, this.getScreenRecord().getField(McoScreenRecord.kAirlineID), "=", null, false));
        
        this.getMainRecord().getField(Mco.kPaid).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(Mco.kAmountPaid), this.getScreenRecord().getField(McoScreenRecord.kNet)));
        FieldListener fieldBehavior = new MoveOnChangeHandler(this.getMainRecord().getField(Mco.kDatePaid), this.getScreenRecord().getField(McoScreenRecord.kToday), false, true);
        fieldBehavior.setRespondsToMode(DBConstants.INIT_MOVE, false);
        fieldBehavior.setRespondsToMode(DBConstants.READ_MOVE, false);
        this.getMainRecord().getField(Mco.kAmountPaid).addListener(fieldBehavior);
        
        this.setEnabled(false);
        this.getScreenRecord().getField(McoScreenRecord.kAirlineID).setEnabled(true);
        this.getMainRecord().getField(Mco.kDatePaid).setEnabled(true);
        this.getMainRecord().getField(Mco.kAmountPaid).setEnabled(true);
        this.getMainRecord().getField(Mco.kPaid).setEnabled(true);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new McoScreenRecord(this);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(Mco.kMcoFile).getField(Mco.kMcoNo).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Mco.kMcoFile).getField(Mco.kGross).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Mco.kMcoFile).getField(Mco.kCommAmt).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(McoScreenRecord.kMcoScreenRecordFile).getField(McoScreenRecord.kServiceCharge).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(McoScreenRecord.kMcoScreenRecordFile).getField(McoScreenRecord.kNet).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Mco.kMcoFile).getField(Mco.kDatePaid).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Mco.kMcoFile).getField(Mco.kAmountPaid).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SButtonBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, this.getRecord(Mco.kMcoFile).getField(Mco.kPaid), ScreenConstants.DISPLAY_FIELD_DESC);
    }

}
