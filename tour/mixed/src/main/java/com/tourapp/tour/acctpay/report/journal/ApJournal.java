/**
 * @(#)ApJournal.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report.journal;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.acctpay.report.*;
import com.tourapp.tour.booking.db.*;

/**
 *  ApJournal - A/P Journal.
 */
public class ApJournal extends ReportScreen
{
    /**
     * Default constructor.
     */
    public ApJournal()
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
    public ApJournal(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "A/P Journal";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Vendor(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ApTrx(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new ApReportScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getRecord(Vendor.kVendorFile).setKeyArea(Vendor.kCurrencysIDKey);
        this.getRecord(Vendor.kVendorFile).addListener(new CompareFileFilter(this.getRecord(Vendor.kVendorFile).getField(Vendor.kID), this.getScreenRecord().getField(ApReportScreenRecord.kVendorID), "=", null, true));
        this.getRecord(Vendor.kVendorFile).addListener(new CompareFileFilter(this.getRecord(Vendor.kVendorFile).getField(Vendor.kCurrencysID), this.getScreenRecord().getField(ApReportScreenRecord.kCurrencysID), "=", null, true));
        
        this.getRecord(ApTrx.kApTrxFile).addListener(new SubFileFilter(this.getRecord(Vendor.kVendorFile)));
        this.getRecord(ApTrx.kApTrxFile).addListener(new ApTrxFilter(ApTrx.kTrxStatusID, (ScreenRecord)this.getScreenRecord()));
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(ApReportScreenRecord.kTotalVendors), false, true));
        
        this.getRecord(ApTrx.kApTrxFile).addListener(new SubCountHandler(this.getScreenRecord().getField(ApReportScreenRecord.kTotalEstimate), ApTrx.kDepartureEstimate, true, true, true));
        this.getRecord(ApTrx.kApTrxFile).addListener(new SubCountHandler(this.getScreenRecord().getField(ApReportScreenRecord.kTotalInvoice), ApTrx.kInvoiceAmount, true, true, true));
        this.getRecord(ApTrx.kApTrxFile).addListener(new SubCountHandler(this.getScreenRecord().getField(ApReportScreenRecord.kTotalBalance), ApTrx.kInvoiceBalance, true, true, true));
        this.getRecord(ApTrx.kApTrxFile).addListener(new SubCountHandler(this.getScreenRecord().getField(ApReportScreenRecord.kTotalUSDBal), ApTrx.kInvoiceBalanceLocal, true, true, false));
        this.getRecord(ApTrx.kApTrxFile).addListener(new SubCountHandler(this.getScreenRecord().getField(ApReportScreenRecord.kGrandUSDBal), ApTrx.kInvoiceBalanceLocal, false, false, false));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new ApJournalToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kVendorName).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kCurrencysID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new ApJournalVendorTotals(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.FOOTING_SCREEN, null);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new ApJournalHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new ApJournalFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the detail screen for this report item (null = none).
     */
    public BaseScreen addReportDetail()
    {
        return new ApJournalDetail(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}
