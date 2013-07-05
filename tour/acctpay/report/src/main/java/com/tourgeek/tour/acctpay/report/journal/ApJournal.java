/**
  * @(#)ApJournal.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctpay.report.journal;

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
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.acctpay.report.*;
import com.tourgeek.tour.booking.db.*;

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
        
        this.getRecord(Vendor.VENDOR_FILE).setKeyArea(Vendor.CURRENCYS_ID_KEY);
        this.getRecord(Vendor.VENDOR_FILE).addListener(new CompareFileFilter(this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.ID), this.getScreenRecord().getField(ApReportScreenRecord.VENDOR_ID), "=", null, true));
        this.getRecord(Vendor.VENDOR_FILE).addListener(new CompareFileFilter(this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.CURRENCYS_ID), this.getScreenRecord().getField(ApReportScreenRecord.CURRENCYS_ID), "=", null, true));
        
        this.getRecord(ApTrx.AP_TRX_FILE).addListener(new SubFileFilter(this.getRecord(Vendor.VENDOR_FILE)));
        this.getRecord(ApTrx.AP_TRX_FILE).addListener(new ApTrxFilter(ApTrx.TRX_STATUS_ID, (ScreenRecord)this.getScreenRecord()));
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(ApReportScreenRecord.TOTAL_VENDORS), false, true));
        
        this.getRecord(ApTrx.AP_TRX_FILE).addListener(new SubCountHandler(this.getScreenRecord().getField(ApReportScreenRecord.TOTAL_ESTIMATE), ApTrx.DEPARTURE_ESTIMATE, true, true, true));
        this.getRecord(ApTrx.AP_TRX_FILE).addListener(new SubCountHandler(this.getScreenRecord().getField(ApReportScreenRecord.TOTAL_INVOICE), ApTrx.INVOICE_AMOUNT, true, true, true));
        this.getRecord(ApTrx.AP_TRX_FILE).addListener(new SubCountHandler(this.getScreenRecord().getField(ApReportScreenRecord.TOTAL_BALANCE), ApTrx.INVOICE_BALANCE, true, true, true));
        this.getRecord(ApTrx.AP_TRX_FILE).addListener(new SubCountHandler(this.getScreenRecord().getField(ApReportScreenRecord.TOTAL_USD_BAL), ApTrx.INVOICE_BALANCE_LOCAL, true, true, false));
        this.getRecord(ApTrx.AP_TRX_FILE).addListener(new SubCountHandler(this.getScreenRecord().getField(ApReportScreenRecord.GRAND_USD_BAL), ApTrx.INVOICE_BALANCE_LOCAL, false, false, false));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new ApJournalToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.VENDOR_NAME).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.CURRENCYS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
