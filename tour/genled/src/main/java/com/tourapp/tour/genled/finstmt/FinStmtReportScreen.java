/**
 * @(#)FinStmtReportScreen.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.genled.db.*;
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.genled.finstmt.screen.*;
import org.jbundle.main.db.*;

/**
 *  FinStmtReportScreen - Financial Statements.
 */
public class FinStmtReportScreen extends ReportScreen
{
    /**
     * Default constructor.
     */
    public FinStmtReportScreen()
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
    public FinStmtReportScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Financial Statements";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new FinStmt(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new Account(this);
        new FinStmtDetail(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getMainRecord().setKeyArea(FinStmt.FIN_STMT_HEADER_ID_KEY);
        this.getMainRecord().addListener(new SubFileFilter(this.getScreenRecord().getField(FinStmtReportScreenRecord.FIN_STMT_HEADER_ID), FinStmt.FIN_STMT_HEADER_ID, null, null, null, null));
        
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).addListener(new SubFileFilter(this.getMainRecord()));
        
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).addListener(new DisplayReadHandler(FinStmtDetail.ACCOUNT_ID, this.getRecord(Account.ACCOUNT_FILE), Account.ID));
        
        CalcAcctBalHandler calcListener = null;
        this.getRecord(Account.ACCOUNT_FILE).addListener(calcListener = new CalcAcctBalHandler(this.getScreenRecord().getField(FinStmtReportScreenRecord.START_BALANCE), null, this.getScreenRecord().getField(FinStmtReportScreenRecord.START_DATE), false));
        // Start and end entry date filters
        calcListener.getAcctDetail().addListener(new CompareFileFilter(calcListener.getAcctDetail().getField(AcctDetail.TRX_ENTRY), this.getScreenRecord().getField(FinStmtReportScreenRecord.START_ENTRY), FileFilter.GREATER_THAN_EQUAL));
        calcListener.getAcctDetail().addListener(new CompareFileFilter(calcListener.getAcctDetail().getField(AcctDetail.TRX_ENTRY), this.getScreenRecord().getField(FinStmtReportScreenRecord.END_ENTRY), FileFilter.LESS_THAN_EQUAL));
        // Closing Entry Filter
        calcListener.getAcctDetail().addListener(new ExcludeClosingFilter(this.getScreenRecord().getField(FinStmtReportScreenRecord.EXCLUDE_CLOSING)));
        
        this.getRecord(Account.ACCOUNT_FILE).addListener(calcListener = new CalcAcctBalHandler(this.getScreenRecord().getField(FinStmtReportScreenRecord.BALANCE_CHANGE), this.getScreenRecord().getField(FinStmtReportScreenRecord.START_DATE), this.getScreenRecord().getField(FinStmtReportScreenRecord.END_DATE), true));
        // Start and end entry date filters
        calcListener.getAcctDetail().addListener(new CompareFileFilter(calcListener.getAcctDetail().getField(AcctDetail.TRX_ENTRY), this.getScreenRecord().getField(FinStmtReportScreenRecord.START_ENTRY), FileFilter.GREATER_THAN_EQUAL));
        calcListener.getAcctDetail().addListener(new CompareFileFilter(calcListener.getAcctDetail().getField(AcctDetail.TRX_ENTRY), this.getScreenRecord().getField(FinStmtReportScreenRecord.END_ENTRY), FileFilter.LESS_THAN_EQUAL));
        // Closing Entry Filter
        calcListener.getAcctDetail().addListener(new ExcludeClosingFilter(this.getScreenRecord().getField(FinStmtReportScreenRecord.EXCLUDE_CLOSING)));
        
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).addListener(new AddFinStmtDetailTotals(null));        
        
        this.setProperty(LIMIT_PARAM, LIMIT_UNLIMITED);   // Unlimited detail records
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new FinStmtReportScreenRecord(this);
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new FinStmtToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new FinStmtReportHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the detail screen for this report item (null = none).
     */
    public BaseScreen addReportDetail()
    {
        return new FinStmtDetailReportScreen(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(FinStmt.FIN_STMT_FILE).getField(FinStmt.STATEMENT_DESC).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmt.FIN_STMT_FILE).getField(FinStmt.STATEMENT_TYPE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmt.FIN_STMT_FILE).getField(FinStmt.STATEMENT_FORMAT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmt.FIN_STMT_FILE).getField(FinStmt.STATEMENT_NUMBER).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Get the path to the target servlet.
     * @param The servlet type (regular html or xhtml)
     * @return the servlet path.
     */
    public String getServletPath(String strServletParam)
    {
        return super.getServletPath(DBParams.XHTMLSERVLET); // Use cocoon
    }

}
