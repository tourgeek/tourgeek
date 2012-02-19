/**
 * @(#)SourceCrossRef.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.report.xref;

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
import com.tourapp.tour.genled.report.*;

/**
 *  SourceCrossRef - Source Cross-Reference Report.
 */
public class SourceCrossRef extends ReportScreen
{
    /**
     * Default constructor.
     */
    public SourceCrossRef()
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
    public SourceCrossRef(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Source Cross-Reference Report";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new AcctDetail(this);
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
        this.getMainRecord().setKeyArea(AcctDetail.SOURCE_KEY);
        
        SubFileFilter listener = new SubFileFilter(this.getScreenRecord().getField(GenledScreenRecord.START_SOURCE), AcctDetail.SOURCE, null, null, null, null);
        listener.setEndKey(false);
        this.getMainRecord().addListener(listener);
        listener = new SubFileFilter(this.getScreenRecord().getField(GenledScreenRecord.END_SOURCE), AcctDetail.SOURCE, null, null, null, null);
        listener.setInitialKey(false);
        this.getMainRecord().addListener(listener);
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(GenledScreenRecord.REPORT_COUNT), false, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(GenledScreenRecord.REPORT_TOTAL), AcctDetail.AMOUNT_LOCAL, false, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(GenledScreenRecord.SUB_TOTAL), AcctDetail.AMOUNT_LOCAL, false, true, true));
        
        this.getRecord(AcctDetail.ACCT_DETAIL_FILE).addListener(new ExtractRangeFilter(AcctDetail.TRX_DATE, this.getScreenRecord().getField(GenledScreenRecord.START_DATE), this.getScreenRecord().getField(GenledScreenRecord.END_DATE), ExtractRangeFilter.PAD_END_FIELD));
        this.getRecord(AcctDetail.ACCT_DETAIL_FILE).addListener(new ExtractRangeFilter(AcctDetail.TRX_ENTRY, this.getScreenRecord().getField(GenledScreenRecord.START_ENTRY), this.getScreenRecord().getField(GenledScreenRecord.END_ENTRY), ExtractRangeFilter.PAD_END_FIELD));        
        
        this.setProperty(LIMIT_PARAM, LIMIT_UNLIMITED);   // Unlimited detail records
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new SourceCrossRefToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new SourceCrossRefHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new SourceCrossRefFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(AcctDetail.ACCT_DETAIL_FILE).getField(AcctDetail.SOURCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.ACCT_DETAIL_FILE).getField(AcctDetail.TRX_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.ACCT_DETAIL_FILE).getField(AcctDetail.ACCOUNT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.ACCT_DETAIL_FILE).getField(AcctDetail.AMOUNT_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.ACCT_DETAIL_FILE).getField(AcctDetail.COMMENTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.ACCT_DETAIL_FILE).getField(AcctDetail.TRX_TYPE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.ACCT_DETAIL_FILE).getField(AcctDetail.TRX_ENTRY).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SourceCrossRefSubTotal(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.FOOTING_SCREEN, null);
    }

}
