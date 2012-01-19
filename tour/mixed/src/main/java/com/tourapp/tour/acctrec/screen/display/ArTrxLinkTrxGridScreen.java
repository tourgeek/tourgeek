/**
 * @(#)ArTrxLinkTrxGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.display;

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
import com.tourapp.tour.genled.screen.detail.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.assetdr.screen.trx.*;
import com.tourapp.tour.acctrec.screen.credit.trx.*;
import com.tourapp.tour.acctrec.screen.mco.trx.*;

/**
 *  ArTrxLinkTrxGridScreen - A/R Open File.
 */
public class ArTrxLinkTrxGridScreen extends LinkTrxGridScreen
{
    /**
     * Default constructor.
     */
    public ArTrxLinkTrxGridScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public ArTrxLinkTrxGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "A/R Open File";
    }
    /**
     * ArTrxLinkTrxGridScreen Method.
     */
    public ArTrxLinkTrxGridScreen(Record recTrx, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(recTrx, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new ArTrx(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        if (m_recHeader != null)
            this.addRecord(m_recHeader, false);
        else
        {
            if (CreditCard.kCreditCardFile.equalsIgnoreCase(this.getProperty(DBParams.HEADER_RECORD)))
                m_recHeader = new CreditCard(this);
            else if (Mco.kMcoFile.equalsIgnoreCase(this.getProperty(DBParams.HEADER_RECORD)))
                m_recHeader = new Mco(this);
            else
                m_recHeader = new BankTrx(this);
        }
        super.openOtherRecords();
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(ArTrx.kArTrxFile).getField(ArTrx.kTrxStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ArTrx.kArTrxFile).getField(ArTrx.kTrxDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ArTrx.kArTrxFile).getField(ArTrx.kAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ArTrx.kArTrxFile).getField(ArTrx.kComments).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        if (LinkTrx.SOURCE.equalsIgnoreCase(strCommand))
        {
            Record recBooking = ((ReferenceField)this.getMainRecord().getField(ArTrx.kBookingID)).getReference();
            if (recBooking != null)
                return (this.onForm(recBooking, ScreenConstants.MAINT_MODE, true, iCommandOptions, null) != null);
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        if (this.getHeaderRecord() instanceof CreditCard)
            return new CreditCardHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        else if (this.getHeaderRecord() instanceof Mco)
            return new McoHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        else
            return new BankTrxHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }

}
