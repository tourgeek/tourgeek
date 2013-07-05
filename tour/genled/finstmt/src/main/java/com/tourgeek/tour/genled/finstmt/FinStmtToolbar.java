/**
  * @(#)FinStmtToolbar.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.genled.finstmt;

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
    public FinStmtToolbar(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
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
        this.getScreenRecord().getField(FinStmtReportScreenRecord.FIN_STMT_HEADER_ID).addListener(new RegisterValueHandler(null));
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        super.setupSFields();
        //if (this.isPrintReport())
        //  return;   // Don't display params if entered
        this.getRecord(FinStmtReportScreenRecord.FIN_STMT_REPORT_SCREEN_RECORD_FILE).getField(FinStmtReportScreenRecord.FIN_STMT_HEADER_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.FIN_STMT_REPORT_SCREEN_RECORD_FILE).getField(FinStmtReportScreenRecord.FIN_STMT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.FIN_STMT_REPORT_SCREEN_RECORD_FILE).getField(FinStmtReportScreenRecord.BUDGET_COMP).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.FIN_STMT_REPORT_SCREEN_RECORD_FILE).getField(FinStmtReportScreenRecord.START_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.FIN_STMT_REPORT_SCREEN_RECORD_FILE).getField(FinStmtReportScreenRecord.END_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.FIN_STMT_REPORT_SCREEN_RECORD_FILE).getField(FinStmtReportScreenRecord.START_ENTRY).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.FIN_STMT_REPORT_SCREEN_RECORD_FILE).getField(FinStmtReportScreenRecord.END_ENTRY).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.FIN_STMT_REPORT_SCREEN_RECORD_FILE).getField(FinStmtReportScreenRecord.EXCLUDE_CLOSING).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.FIN_STMT_REPORT_SCREEN_RECORD_FILE).getField(FinStmtReportScreenRecord.PROFIT_CENTER_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.FIN_STMT_REPORT_SCREEN_RECORD_FILE).getField(FinStmtReportScreenRecord.TEMPLATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
