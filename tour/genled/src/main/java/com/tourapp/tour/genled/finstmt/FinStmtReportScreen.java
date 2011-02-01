/**
 *  @(#)FinStmtReportScreen.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
        this.getMainRecord().setKeyArea(FinStmt.kFinStmtHeaderIDKey);
        this.getMainRecord().addListener(new SubFileFilter(this.getScreenRecord().getField(FinStmtReportScreenRecord.kFinStmtHeaderID), FinStmt.kFinStmtHeaderID, null, -1, null, -1));
        
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).addListener(new SubFileFilter(this.getMainRecord()));
        
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).addListener(new DisplayReadHandler(FinStmtDetail.kAccountID, this.getRecord(Account.kAccountFile), Account.kID));
        
        CalcAcctBalHandler calcListener = null;
        this.getRecord(Account.kAccountFile).addListener(calcListener = new CalcAcctBalHandler(this.getScreenRecord().getField(FinStmtReportScreenRecord.kStartBalance), null, this.getScreenRecord().getField(FinStmtReportScreenRecord.kStartDate), false));
        // Start and end entry date filters
        calcListener.getAcctDetail().addListener(new CompareFileFilter(calcListener.getAcctDetail().getField(AcctDetail.kTrxEntry), this.getScreenRecord().getField(FinStmtReportScreenRecord.kStartEntry), FileFilter.GREATER_THAN_EQUAL));
        calcListener.getAcctDetail().addListener(new CompareFileFilter(calcListener.getAcctDetail().getField(AcctDetail.kTrxEntry), this.getScreenRecord().getField(FinStmtReportScreenRecord.kEndEntry), FileFilter.LESS_THAN_EQUAL));
        // Closing Entry Filter
        calcListener.getAcctDetail().addListener(new ExcludeClosingFilter(this.getScreenRecord().getField(FinStmtReportScreenRecord.kExcludeClosing)));
        
        this.getRecord(Account.kAccountFile).addListener(calcListener = new CalcAcctBalHandler(this.getScreenRecord().getField(FinStmtReportScreenRecord.kBalanceChange), this.getScreenRecord().getField(FinStmtReportScreenRecord.kStartDate), this.getScreenRecord().getField(FinStmtReportScreenRecord.kEndDate), true));
        // Start and end entry date filters
        calcListener.getAcctDetail().addListener(new CompareFileFilter(calcListener.getAcctDetail().getField(AcctDetail.kTrxEntry), this.getScreenRecord().getField(FinStmtReportScreenRecord.kStartEntry), FileFilter.GREATER_THAN_EQUAL));
        calcListener.getAcctDetail().addListener(new CompareFileFilter(calcListener.getAcctDetail().getField(AcctDetail.kTrxEntry), this.getScreenRecord().getField(FinStmtReportScreenRecord.kEndEntry), FileFilter.LESS_THAN_EQUAL));
        // Closing Entry Filter
        calcListener.getAcctDetail().addListener(new ExcludeClosingFilter(this.getScreenRecord().getField(FinStmtReportScreenRecord.kExcludeClosing)));
        
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).addListener(new AddFinStmtDetailTotals(null));        
        
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
        return new FinStmtToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
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
        this.getRecord(FinStmt.kFinStmtFile).getField(FinStmt.kStatementDesc).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmt.kFinStmtFile).getField(FinStmt.kStatementType).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmt.kFinStmtFile).getField(FinStmt.kStatementFormat).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmt.kFinStmtFile).getField(FinStmt.kStatementNumber).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
