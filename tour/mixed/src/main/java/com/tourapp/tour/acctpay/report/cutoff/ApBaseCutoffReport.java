/**
 *  @(#)ApBaseCutoffReport.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report.cutoff;

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
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.acctpay.report.*;
import com.tourapp.tour.base.db.*;

/**
 *  ApBaseCutoffReport - Cutoff report.
 */
public class ApBaseCutoffReport extends ReportScreen
{
    /**
     * Default constructor.
     */
    public ApBaseCutoffReport()
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
    public ApBaseCutoffReport(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Cutoff report";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new ApTrx(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new Currencys(this);
        new Vendor(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new ApReportScreenRecord(this);  // Probably override this
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        Record recApTrx = this.getMainRecord();
        Record recVendor = this.getRecord(Vendor.kVendorFile);
        Record recCurrencys = this.getRecord(Currencys.kCurrencysFile);
        recVendor.addListener(new SubFileFilter(recCurrencys));
        recApTrx.addListener(new SubFileFilter(recVendor));
    }
    /**
     * Get the next grid record.
     * @param bFirstTime If true, I want the first record.
     * @return the next record (or null if EOF).
     */
    public Record getNextGridRecord(boolean bFirstTime) throws DBException
    {
        Record recApTrx = null;
        while (recApTrx == null)
        {
            if (!bFirstTime)
                recApTrx = super.getNextGridRecord(bFirstTime);
            if (recApTrx == null)
            {
                Record recVendor = null;
                while (recVendor == null)
                {
                    if (!bFirstTime)
                        recVendor = this.getRecord(Vendor.kVendorFile).next();
                    if (recVendor == null)
                    {
                        if (bFirstTime)
                            this.getRecord(Currencys.kCurrencysFile).close();
                        bFirstTime = false;
                        Record recCurrencys = this.getRecord(Currencys.kCurrencysFile).next();
                        if (recCurrencys == null)
                            return null;    // EOF
                        this.getRecord(Vendor.kVendorFile).close();
                    }
                }
                this.getRecord(ApTrx.kApTrxFile).close();
            }
        }
        return recApTrx;
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return super.addReportFooting();    // Override this!
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return super.addReportHeading();    // Override this!
    }

}
