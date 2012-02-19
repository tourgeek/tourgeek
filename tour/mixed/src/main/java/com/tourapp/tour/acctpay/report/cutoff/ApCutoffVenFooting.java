/**
 * @(#)ApCutoffVenFooting.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report.cutoff;

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
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctpay.report.*;
import com.tourapp.tour.genled.db.*;

/**
 *  ApCutoffVenFooting - Currency break totals.
 */
public class ApCutoffVenFooting extends ReportBreakScreen
{
    /**
     * Default constructor.
     */
    public ApCutoffVenFooting()
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
    public ApCutoffVenFooting(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Currency break totals";
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(ApCutoffScreenRecord.AP_CUTOFF_SCREEN_RECORD_FILE).getField(ApCutoffScreenRecord.VEN_EST_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApCutoffScreenRecord.AP_CUTOFF_SCREEN_RECORD_FILE).getField(ApCutoffScreenRecord.VEN_EST_USD_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApCutoffScreenRecord.AP_CUTOFF_SCREEN_RECORD_FILE).getField(ApCutoffScreenRecord.VEN_INV_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApCutoffScreenRecord.AP_CUTOFF_SCREEN_RECORD_FILE).getField(ApCutoffScreenRecord.VEN_INV_USD_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApCutoffScreenRecord.AP_CUTOFF_SCREEN_RECORD_FILE).getField(ApCutoffScreenRecord.VEN_INV_BAL_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApCutoffScreenRecord.AP_CUTOFF_SCREEN_RECORD_FILE).getField(ApCutoffScreenRecord.VEN_INV_BAL_USD_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Get the value to break on.
     * By default, use the first field of the current key (as long as it isn't the counter).
     * @return The break value.
     */
    public Object getBreakValue()
    {
        return this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.ID).getData();
    }

}
