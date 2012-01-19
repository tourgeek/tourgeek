/**
 * @(#)FinStmtToolbar.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.finstmt;

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

/**
 *  FinStmtToolbar - Params for the Financial Statement reports.
 */
public class FinStmtToolbar extends ReportToolbar
{
    /**
     * Default constructor.
     */
    public FinStmtToolbar()
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
    public FinStmtToolbar(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
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
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Params for the Financial Statement reports";
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getRecord(FinStmtReportScreenRecord.kFinStmtReportScreenRecordFile).getField(FinStmtReportScreenRecord.kFinStmtHeaderID).addListener(new RegisterValueHandler(null));
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        super.setupSFields();
        //if (this.isPrintReport())
        //  return;   // Don't display params if entered
        this.getRecord(FinStmtReportScreenRecord.kFinStmtReportScreenRecordFile).getField(FinStmtReportScreenRecord.kFinStmtHeaderID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.kFinStmtReportScreenRecordFile).getField(FinStmtReportScreenRecord.kFinStmtID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.kFinStmtReportScreenRecordFile).getField(FinStmtReportScreenRecord.kBudgetComp).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.kFinStmtReportScreenRecordFile).getField(FinStmtReportScreenRecord.kStartDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.kFinStmtReportScreenRecordFile).getField(FinStmtReportScreenRecord.kEndDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.kFinStmtReportScreenRecordFile).getField(FinStmtReportScreenRecord.kStartEntry).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.kFinStmtReportScreenRecordFile).getField(FinStmtReportScreenRecord.kEndEntry).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.kFinStmtReportScreenRecordFile).getField(FinStmtReportScreenRecord.kExcludeClosing).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.kFinStmtReportScreenRecordFile).getField(FinStmtReportScreenRecord.kProfitCenterID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.kFinStmtReportScreenRecordFile).getField(FinStmtReportScreenRecord.ktemplate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
