/**
 * @(#)TourDetailReport.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report.tourdet;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.acctpay.report.*;

/**
 *  TourDetailReport - Tour detail report.
 */
public class TourDetailReport extends ReportScreen
{
    /**
     * Default constructor.
     */
    public TourDetailReport()
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
    public TourDetailReport(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Tour detail report";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Tour(this);
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
        this.getRecord(Tour.kTourFile).setKeyArea(Tour.kTourHeaderIDKey);
        this.getRecord(ApTrx.kApTrxFile).setKeyArea(ApTrx.kTourIDKey);
        
        Record recTour = this.getRecord(Tour.kTourFile);
        //xthis.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kTourID).addListener(new ReadSecondaryHandler(recTour));
        this.getRecord(ApTrx.kApTrxFile).addListener(new SubFileFilter(recTour));
        // Add Filters
        this.getRecord(Tour.kTourFile).addListener(new CompareFileFilter(this.getRecord(Tour.kTourFile).getField(Tour.kID), this.getScreenRecord().getField(ApReportScreenRecord.kTourID), "=", null, true));
        //this.getRecord(Tour.kTourFile).addListener(new CompareFileFilter(this.getRecord(Tour.kTourFile).getField(Tour.kTourTypeID), this.getScreenRecord().getField(ApReportScreenRecord.kTourTypeID), "=", null, true));
        this.getRecord(Tour.kTourFile).addListener(new CompareFileFilter(this.getRecord(Tour.kTourFile).getField(Tour.kTourHeaderID), this.getScreenRecord().getField(ApReportScreenRecord.kTourHeaderID), "=", null, true));
        //this.getRecord(Tour.kTourFile).addListener(new CompareFileFilter(this.getRecord(Tour.kTourFile).getField(Tour.kTourClassID), this.getScreenRecord().getField(ApReportScreenRecord.kTourClassID), "=", null, true));
        //this.getRecord(Tour.kTourFile).addListener(new CompareFileFilter(this.getRecord(Tour.kTourFile).getField(Tour.kProductCategoryID), this.getScreenRecord().getField(ApReportScreenRecord.kProductCategoryID), "=", null, true));
        this.getRecord(Tour.kTourFile).addListener(new CompareFileFilter(this.getRecord(Tour.kTourFile).getField(Tour.kTourStatusID), this.getScreenRecord().getField(ApReportScreenRecord.kTourStatusID), "=", null, true));
        this.getRecord(Tour.kTourFile).addListener(new CompareFileFilter(this.getRecord(Tour.kTourFile).getField(Tour.kDepartureDate), this.getScreenRecord().getField(ApReportScreenRecord.kStartDeparture), ">=", null, true));
        this.getRecord(Tour.kTourFile).addListener(new CompareFileFilter(this.getRecord(Tour.kTourFile).getField(Tour.kDepartureDate), this.getScreenRecord().getField(ApReportScreenRecord.kEndDeparture), "<=", null, true));
        
        this.getRecord(ApTrx.kApTrxFile).addListener(new ApTrxFilter(ApTrx.kTrxStatusID, (ScreenRecord)this.getScreenRecord()));
        
        this.getRecord(ApTrx.kApTrxFile).addListener(new SubCountHandler(this.getScreenRecord().getField(ApReportScreenRecord.kTotalUSDBal), ApTrx.kInvoiceBalanceLocal, true, true, true));    // Sub-count
        this.getRecord(ApTrx.kApTrxFile).addListener(new SubCountHandler(this.getScreenRecord().getField(ApReportScreenRecord.kGrandUSDBal), ApTrx.kInvoiceBalanceLocal, true, true));          // Total count
        
        this.getRecord(ApTrx.kApTrxFile).addListener(new SubCountHandler(this.getScreenRecord().getField(ApReportScreenRecord.kReportCount), -1, true, true, true));    // Sub-count
        this.getRecord(ApTrx.kApTrxFile).addListener(new SubCountHandler(this.getScreenRecord().getField(ApReportScreenRecord.kCount), -1, true, true, false));     // Total count
        
        this.getRecord(ApTrx.kApTrxFile).addListener(new MoveEstimateHandler(null));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new TourDetailToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(Tour.kTourFile).getField(Tour.kCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kDepartureDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kTourHeaderID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kTourStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new TourDetailHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new TourDetailFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the detail screen for this report item (null = none).
     */
    public BaseScreen addReportDetail()
    {
        return new TourDetailDetail(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}
