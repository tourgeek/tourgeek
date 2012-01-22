/**
 * @(#)AcctDetailLedger.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.report.ledger;

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
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.genled.finstmt.*;
import com.tourapp.tour.genled.report.*;

/**
 *  AcctDetailLedger - General Ledger Printout.
 */
public class AcctDetailLedger extends ReportScreen
{
    /**
     * Default constructor.
     */
    public AcctDetailLedger()
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
    public AcctDetailLedger(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "General Ledger Printout";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Account(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new AcctDetail(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new GenledScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getMainRecord().setKeyArea(Account.kAccountNoKey);
        
        SubFileFilter listener = new SubFileFilter(((AccountField)this.getScreenRecord().getField(GenledScreenRecord.kStartAccountID)).getReferenceRecord().getField(Account.kAccountNo), Account.kAccountNo, null, -1, null, -1);
        listener.setEndKey(false);
        this.getMainRecord().addListener(listener);
        listener = new SubFileFilter(((AccountField)this.getScreenRecord().getField(GenledScreenRecord.kEndAccountID)).getReferenceRecord().getField(Account.kAccountNo), Account.kAccountNo, null, -1, null, -1);
        listener.setInitialKey(false);
        this.getMainRecord().addListener(listener);
        
        this.getRecord(AcctDetail.kAcctDetailFile).addListener(new SubFileFilter(this.getRecord(Account.kAccountFile)));
        
        listener = new SubFileFilter(this.getScreenRecord().getField(GenledScreenRecord.kStartDate), AcctDetail.kTrxDate, null, -1, null, -1);
        listener.setEndKey(false);
        this.getRecord(AcctDetail.kAcctDetailFile).addListener(listener);
        
        listener = new SubFileFilter(this.getScreenRecord().getField(GenledScreenRecord.kEndDate), AcctDetail.kTrxDate, null, -1, null, -1);
        listener.setInitialKey(false);
        this.getRecord(AcctDetail.kAcctDetailFile).addListener(listener);
        
        CalcAcctBalHandler calcListener = null;
        this.getMainRecord().addListener(calcListener = new CalcAcctBalHandler(this.getScreenRecord().getField(GenledScreenRecord.kStartBalance), null, this.getScreenRecord().getField(GenledScreenRecord.kStartDate), false, this.getScreenRecord().getField(GenledScreenRecord.kReportTotal)));
        // Profit Center filter
        this.getMainRecord().addListener(new ProfitCenterFilter(Account.kAccountNo, this.getScreenRecord().getField(GenledScreenRecord.kProfitCenterID)));
        // Start and end entry date filters
        this.getRecord(AcctDetail.kAcctDetailFile).addListener(new CompareFileFilter(this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kTrxEntry), this.getScreenRecord().getField(GenledScreenRecord.kStartEntry), FileFilter.GREATER_THAN_EQUAL));
        calcListener.getAcctDetail().addListener(new CompareFileFilter(this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kTrxEntry), this.getScreenRecord().getField(GenledScreenRecord.kStartEntry), FileFilter.GREATER_THAN_EQUAL));
        this.getRecord(AcctDetail.kAcctDetailFile).addListener(new CompareFileFilter(this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kTrxEntry), this.getScreenRecord().getField(GenledScreenRecord.kEndEntry), FileFilter.LESS_THAN_EQUAL));
        calcListener.getAcctDetail().addListener(new CompareFileFilter(this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kTrxEntry), this.getScreenRecord().getField(GenledScreenRecord.kEndEntry), FileFilter.LESS_THAN_EQUAL));
        
        this.getRecord(AcctDetail.kAcctDetailFile).addListener(new SubCountHandler(this.getScreenRecord().getField(GenledScreenRecord.kChangeBalance), AcctDetail.kAmountLocal, false, true));    // Init this field override for other value
        this.getRecord(AcctDetail.kAcctDetailFile).addListener(new SubCountHandler(this.getScreenRecord().getField(GenledScreenRecord.kReportTotal), AcctDetail.kAmountLocal, false, false));    // Add changes to the report total
        this.getScreenRecord().getField(GenledScreenRecord.kEndBalance).addListener(new FieldListener(null)
        {    // For the ending balance, return the start + change.
            public Object doGetData()
            {
                double dStartBalance = this.getOwner().getRecord().getField(GenledScreenRecord.kStartBalance).getValue();
                double dChangeBalance = this.getOwner().getRecord().getField(GenledScreenRecord.kChangeBalance).getValue();
                this.getOwner().setValue(dStartBalance + dChangeBalance);
                return super.doGetData();
            }
        });
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(GenledScreenRecord.kReportCount), false, true));
        
        this.getRecord(AcctDetail.kAcctDetailFile).addListener(new ExtractRangeFilter(AcctDetail.kTrxEntry, this.getScreenRecord().getField(GenledScreenRecord.kStartEntry), this.getScreenRecord().getField(GenledScreenRecord.kEndEntry), ExtractRangeFilter.PAD_END_FIELD));
        
        this.setProperty(LIMIT_PARAM, LIMIT_UNLIMITED);   // Unlimited detail records
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new AcctDetailLedgerToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new AcctDetailLedgerHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new AcctDetailLedgerFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the detail screen for this report item (null = none).
     */
    public BaseScreen addReportDetail()
    {
        return new AcctDetailLedgerDetail(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(Account.kAccountFile).getField(Account.kAccountNo).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Account.kAccountFile).getField(Account.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
