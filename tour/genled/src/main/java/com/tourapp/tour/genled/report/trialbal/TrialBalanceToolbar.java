/**
 * @(#)TrialBalanceToolbar.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.report.trialbal;

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
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.genled.finstmt.*;
import com.tourapp.tour.genled.report.*;

/**
 *  TrialBalanceToolbar - .
 */
public class TrialBalanceToolbar extends ReportToolbar
{
    /**
     * Default constructor.
     */
    public TrialBalanceToolbar()
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
    public TrialBalanceToolbar(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
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
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        super.setupSFields();
        //if (this.isPrintReport())
        //  return;   // Don't display params if entered
        this.getRecord(GenledScreenRecord.kGenledScreenRecordFile).getField(GenledScreenRecord.kStartAccountID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(GenledScreenRecord.kGenledScreenRecordFile).getField(GenledScreenRecord.kEndAccountID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(GenledScreenRecord.kGenledScreenRecordFile).getField(GenledScreenRecord.kCutoffDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(GenledScreenRecord.kGenledScreenRecordFile).getField(GenledScreenRecord.kProfitCenterID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(GenledScreenRecord.kGenledScreenRecordFile).getField(GenledScreenRecord.kSubTotals).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
