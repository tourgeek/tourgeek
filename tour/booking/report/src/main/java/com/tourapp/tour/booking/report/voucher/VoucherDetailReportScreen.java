/**
  * @(#)VoucherDetailReportScreen.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.report.voucher;

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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.booking.report.itinerary.*;

/**
 *  VoucherDetailReportScreen - .
 */
public class VoucherDetailReportScreen extends ReportScreen
{
    /**
     * Default constructor.
     */
    public VoucherDetailReportScreen()
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
    public VoucherDetailReportScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        
        new BookingDetail(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getMainRecord().addListener(new SubFileFilter(this.getRecord(Tour.TOUR_FILE)));
        
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(ApTrx.VENDOR_ID)).getReferenceRecord(this);
        this.getMainRecord().getField(ApTrx.VENDOR_ID).addListener(new ReadSecondaryHandler(recVendor));
        
        this.getRecord(BookingDetail.BOOKING_DETAIL_FILE).addListener(new SubFileFilter(this.getRecord(ApTrx.AP_TRX_FILE)));
        this.getRecord(BookingDetail.BOOKING_DETAIL_FILE).setKeyArea(BookingDetail.AP_TRX_ID_KEY);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record recApTrx = this.getMainRecord();
        ((ReferenceField)recApTrx.getField(ApTrx.TOUR_ID)).setReferenceRecord(this.getRecord(Tour.TOUR_FILE));
        for (int iFieldSeq = 0; iFieldSeq < recApTrx.getFieldCount(); iFieldSeq++)
        {
            BaseField field = recApTrx.getField(iFieldSeq);
            this.addColumn(field);
        }
        
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(ApTrx.VENDOR_ID)).getReferenceRecord(this);
        for (int iFieldSeq = 0; iFieldSeq < recVendor.getFieldCount(); iFieldSeq++)
        {
            BaseField field = recVendor.getField(iFieldSeq);
            this.addColumn(field);
        }
    }
    /**
     * Get the detail screen for this report item (null = none).
     */
    public BaseScreen addReportDetail()
    {
        //      Booking detail record
        return new BookingDetailReportScreen(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.HEADING_SCREEN, null);
    }

}
