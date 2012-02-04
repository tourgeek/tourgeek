/**
 * @(#)Ten99Report.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report.ten99;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.acctpay.report.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  Ten99Report - 1099 Printing.
 */
public class Ten99Report extends ReportScreen
{
    /**
     * Default constructor.
     */
    public Ten99Report()
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
    public Ten99Report(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "1099 Printing";
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
        new BankTrx(this);
        new PaymentHistory(this);
        new ApControl(this);
        new TrxDesc(this);
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
        
        // First, set up the default fields
        ((DateTimeField)this.getScreenRecord().getField(ApReportScreenRecord.START_DATE)).setDate(new Date(), false, DBConstants.INIT_MOVE);
        Calendar cal = ((DateTimeField)this.getScreenRecord().getField(ApReportScreenRecord.START_DATE)).getCalendar();
        cal.add(Calendar.MONTH, -6);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        ((DateTimeField)this.getScreenRecord().getField(ApReportScreenRecord.START_DATE)).setCalendar(cal, true, DBConstants.INIT_MOVE);
        cal.add(Calendar.YEAR, 1);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        ((DateTimeField)this.getScreenRecord().getField(ApReportScreenRecord.END_DATE)).setCalendar(cal, true, DBConstants.INIT_MOVE);
        
        this.getScreenRecord().getField(ApReportScreenRecord.EXCLUDE_AMOUNT).addListener(new RegisterValueHandler(null));
        this.getScreenRecord().getField(ApReportScreenRecord.TEMPLATE).addListener(new InitFieldHandler(this.getRecord(ApControl.AP_CONTROL_FILE).getField(ApControl.TEN_99_TEMPLATE)));
        
        this.getMainRecord().addListener(new CompareFileFilter(Vendor.SEND_1099, this.getScreenRecord().getField(ApReportScreenRecord.TRUE_FIELD), "=", null, false));
        // Now add the logic to total the payment to this vendor
        this.getMainRecord().setOpenMode(this.getMainRecord().getOpenMode() | DBConstants.OPEN_READ_ONLY);
        TrxDesc recTrxDesc = (TrxDesc)this.getRecord(TrxDesc.TRX_DESC_FILE);
        recTrxDesc.getTrxDesc(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE);
        Record recBankTrx = this.getRecord(BankTrx.BANK_TRX_FILE);
        recBankTrx.setKeyArea(BankTrx.PAYEE_ID_KEY);
        recBankTrx.addListener(new SubFileFilter(this.getMainRecord().getField(Vendor.ID), BankTrx.PAYEE_ID, recTrxDesc.getField(TrxDesc.ID), BankTrx.PAYEE_TRX_DESC_ID, null, null));
        recBankTrx.addListener(new SubCountHandler(this.getMainRecord().getField(Vendor.VENDOR_BALANCE), BankTrx.AMOUNT, true, true));
    }
    /**
     * Get the next grid record.
     * @param bFirstTime If true, I want the first record.
     * @return the next record (or null if EOF).
     */
    public Record getNextGridRecord(boolean bFirstTime) throws DBException
    {
        Record record = null;
        while (record == null)
        {
            record = super.getNextGridRecord(bFirstTime);
            bFirstTime = false;
            if (record == null)
                return null;    // EOF
            Record recBankTrx = this.getRecord(BankTrx.BANK_TRX_FILE);
            recBankTrx.close();
            while (recBankTrx.hasNext())
            {
                recBankTrx.next();  // Listener will add to total
            }
            if (Math.abs(record.getField(Vendor.VENDOR_BALANCE).getValue()) < this.getScreenRecord().getField(ApReportScreenRecord.EXCLUDE_AMOUNT).getValue())
                record = null;  // Skip this vendor
        }
        return record;
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new Ten99Toolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(ApReportScreenRecord.AP_REPORT_SCREEN_RECORD_FILE).getField(ApReportScreenRecord.START_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApReportScreenRecord.AP_REPORT_SCREEN_RECORD_FILE).getField(ApReportScreenRecord.END_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.VENDOR_NAME).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.ADDRESS_LINE_1).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.ADDRESS_LINE_2).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.CITY_OR_TOWN).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.STATE_OR_REGION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.POSTAL_CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.COUNTRY).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.TAX_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.VENDOR_FILE).getField(Vendor.VENDOR_BALANCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new Ten99Heading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new Ten99Footing(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the path to the target servlet.
     * @param The servlet type (regular html or xhtml)
     * @return the servlet path.
     */
    public String getServletPath(String strServletParam)
    {
        if (!this.getScreenRecord().getField(ApReportScreenRecord.TEMPLATE).isNull())
            strServletParam = DBParams.XHTMLSERVLET; // Use cocoon
        return super.getServletPath(strServletParam);
    }

}
