/**
  * @(#)McoBatchDistScreen.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctrec.screen.mco;

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
import com.tourgeek.tour.acctrec.screen.cash.*;
import com.tourgeek.tour.acctrec.db.*;

/**
 *  McoBatchDistScreen - MCOs.
 */
public class McoBatchDistScreen extends Screen
{
    /**
     * Default constructor.
     */
    public McoBatchDistScreen()
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
    public McoBatchDistScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "MCOs";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new McoBatchDist(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new Mco(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new CashBatchScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getMainRecord().addListener(new SubFileFilter(this.getHeaderRecord()));
        
        this.getRecord(McoBatchDist.MCO_BATCH_DIST_FILE).addListener(new SubCountHandler(this.getScreenRecord().getField(CashBatchScreenRecord.CHANGE_BALANCE), Mco.NET, false, true));
        this.getScreenRecord().getField(CashBatchScreenRecord.CHANGE_BALANCE).addListener(new CalcBalanceHandler(this.getScreenRecord().getField(CashBatchScreenRecord.END_BALANCE), this.getScreenRecord().getField(CashBatchScreenRecord.CHANGE_BALANCE), this.getRecord(Mco.MCO_FILE).getField(Mco.NET), true));
    }
    /**
     * If there is a header record, return it, otherwise, return the main record.
     * The header record is the (optional) main record on gridscreens and is sometimes used
     * to enter data in a sub-record when a header is required.
     * @return The header record.
     */
    public Record getHeaderRecord()
    {
        return this.getRecord(Mco.MCO_FILE);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new BaseArPayDistHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(McoBatchDist.MCO_BATCH_DIST_FILE).getField(McoBatchDist.BOOKING_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(McoBatchDist.MCO_BATCH_DIST_FILE).getField(McoBatchDist.AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(McoBatchDist.MCO_BATCH_DIST_FILE).getField(McoBatchDist.ACCOUNT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
