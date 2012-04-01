/**
 * @(#)PastDueHeading.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.report.pastdue;

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
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.main.db.*;

/**
 *  PastDueHeading - Heading.
 */
public class PastDueHeading extends HeadingScreen
{
    /**
     * Default constructor.
     */
    public PastDueHeading()
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
    public PastDueHeading(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Heading";
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(PastDueScreenRecord.PAST_DUE_SCREEN_RECORD_FILE).getField(PastDueScreenRecord.REPORT_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PastDueScreenRecord.PAST_DUE_SCREEN_RECORD_FILE).getField(PastDueScreenRecord.REPORT_TIME).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PastDueScreenRecord.PAST_DUE_SCREEN_RECORD_FILE).getField(PastDueScreenRecord.REPORT_USER_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PastDueScreenRecord.PAST_DUE_SCREEN_RECORD_FILE).getField(PastDueScreenRecord.AS_OF_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PastDueScreenRecord.PAST_DUE_SCREEN_RECORD_FILE).getField(PastDueScreenRecord.CHECK_DEP).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PastDueScreenRecord.PAST_DUE_SCREEN_RECORD_FILE).getField(PastDueScreenRecord.CHECK_FINAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PastDueScreenRecord.PAST_DUE_SCREEN_RECORD_FILE).getField(PastDueScreenRecord.MCO_PER).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PastDueScreenRecord.PAST_DUE_SCREEN_RECORD_FILE).getField(PastDueScreenRecord.REPORT_PAGE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
