/**
  * @(#)ApCutoffCurrFooting.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctpay.report.cutoff;

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
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.acctpay.report.*;
import com.tourgeek.tour.genled.db.*;

/**
 *  ApCutoffCurrFooting - Currency break totals.
 */
public class ApCutoffCurrFooting extends ReportBreakScreen
{
    /**
     * Default constructor.
     */
    public ApCutoffCurrFooting()
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
    public ApCutoffCurrFooting(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        this.getRecord(ApCutoffScreenRecord.AP_CUTOFF_SCREEN_RECORD_FILE).getField(ApCutoffScreenRecord.CURR_EST_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApCutoffScreenRecord.AP_CUTOFF_SCREEN_RECORD_FILE).getField(ApCutoffScreenRecord.CURR_EST_USD_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApCutoffScreenRecord.AP_CUTOFF_SCREEN_RECORD_FILE).getField(ApCutoffScreenRecord.CURR_INVOICE_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApCutoffScreenRecord.AP_CUTOFF_SCREEN_RECORD_FILE).getField(ApCutoffScreenRecord.CURR_INVOICE_USD_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApCutoffScreenRecord.AP_CUTOFF_SCREEN_RECORD_FILE).getField(ApCutoffScreenRecord.CURR_INV_BAL_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApCutoffScreenRecord.AP_CUTOFF_SCREEN_RECORD_FILE).getField(ApCutoffScreenRecord.CURR_INV_BAL_USD_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Get the value to break on.
     * By default, use the first field of the current key (as long as it isn't the counter).
     * @return The break value.
     */
    public Object getBreakValue()
    {
        return this.getRecord(Currencys.CURRENCYS_FILE).getField(Currencys.ID).getData();
    }

}
