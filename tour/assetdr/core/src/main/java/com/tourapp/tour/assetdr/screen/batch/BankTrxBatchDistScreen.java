/**
 * @(#)BankTrxBatchDistScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.screen.batch;

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
import com.tourapp.tour.assetdr.screen.trx.*;
import org.jbundle.main.screen.*;

/**
 *  BankTrxBatchDistScreen - .
 */
public class BankTrxBatchDistScreen extends DetailScreen
{
    /**
     * Default constructor.
     */
    public BankTrxBatchDistScreen()
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
    public BankTrxBatchDistScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * BankTrxBatchDistScreen Method.
     */
    public BankTrxBatchDistScreen(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recHeader, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new BankTrxBatchDist(this);
    }
    /**
     * Open the header record.
     * @return The new header record.
     */
    public Record openHeaderRecord()
    {
        return new BankTrxBatchDetail(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getRecord(BankTrxBatchDist.kBankTrxBatchDistFile).addListener(new SubCountHandler(this.getScreenRecord().getField(BankTrxScreenRecord.kChangeBalance), BankTrxBatchDist.kAmount, false, true));
        this.getScreenRecord().getField(BankTrxScreenRecord.kChangeBalance).addListener(new CalcBalanceHandler(this.getScreenRecord().getField(BankTrxScreenRecord.kEndBalance), this.getScreenRecord().getField(BankTrxScreenRecord.kChangeBalance), this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile).getField(BankTrxBatchDetail.kAmount), true));
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new BankTrxScreenRecord(this);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(BankTrxBatchDist.kBankTrxBatchDistFile).getField(BankTrxBatchDist.kAccountID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxBatchDist.kBankTrxBatchDistFile).getField(BankTrxBatchDist.kAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new BankTrxBatchDetailHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }

}
