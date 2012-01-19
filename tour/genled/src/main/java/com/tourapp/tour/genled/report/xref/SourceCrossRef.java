/**
 * @(#)SourceCrossRef.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
        this.getMainRecord().setKeyArea(AcctDetail.kSourceKey);
        
        SubFileFilter listener = new SubFileFilter(this.getScreenRecord().getField(GenledScreenRecord.kStartSource), AcctDetail.kSource, null, -1, null, -1);
        listener.setEndKey(false);
        this.getMainRecord().addListener(listener);
        listener = new SubFileFilter(this.getScreenRecord().getField(GenledScreenRecord.kEndSource), AcctDetail.kSource, null, -1, null, -1);
        listener.setInitialKey(false);
        this.getMainRecord().addListener(listener);
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(GenledScreenRecord.kReportCount), false, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(GenledScreenRecord.kReportTotal), AcctDetail.kAmountLocal, false, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(GenledScreenRecord.kSubTotal), AcctDetail.kAmountLocal, false, true, true));
        
        this.getRecord(AcctDetail.kAcctDetailFile).addListener(new ExtractRangeFilter(AcctDetail.kTrxDate, this.getScreenRecord().getField(GenledScreenRecord.kStartDate), this.getScreenRecord().getField(GenledScreenRecord.kEndDate), ExtractRangeFilter.PAD_END_FIELD));
        this.getRecord(AcctDetail.kAcctDetailFile).addListener(new ExtractRangeFilter(AcctDetail.kTrxEntry, this.getScreenRecord().getField(GenledScreenRecord.kStartEntry), this.getScreenRecord().getField(GenledScreenRecord.kEndEntry), ExtractRangeFilter.PAD_END_FIELD));        
        
        this.setProperty(LIMIT_PARAM, LIMIT_UNLIMITED);   // Unlimited detail records
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new SourceCrossRefToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
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
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kSource).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kTrxDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kAccountID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kAmountLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kComments).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kTrxTypeID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctDetail.kAcctDetailFile).getField(AcctDetail.kTrxEntry).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SourceCrossRefSubTotal(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.FOOTING_SCREEN, null);
    }

}
