/**
 * @(#)BookingDetailReportScreen.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.report.itinerary;

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
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  BookingDetailReportScreen - .
 */
public class BookingDetailReportScreen extends ReportScreen
{
    /**
     * Default constructor.
     */
    public BookingDetailReportScreen()
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
    public BookingDetailReportScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * Get the main record for this screen.
     * @return The main record.
     */
    public Record getMainRecord()
    {
        return this.getRecord(BookingDetail.BOOKING_DETAIL_FILE);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record recBookingDetail = this.getRecord(BookingDetail.BOOKING_DETAIL_FILE);
        for (int iFieldSeq = 0; iFieldSeq < recBookingDetail.getFieldCount(); iFieldSeq++)
        {
            this.addDetailXMLColumn(recBookingDetail, iFieldSeq);
        }
        new ProductReportDetail(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.HEADING_SCREEN, null);
    }
    /**
     * AddDetailXMLColumn Method.
     */
    public Object addDetailXMLColumn(Record record, int iFieldSeq)
    {
        return this.addColumn(new MultipleTableFieldConverter(record, iFieldSeq));
    }
    /**
     * Set up the screen fields (default = set them all up for the current record).
     * @param converter The converter to creat a default screen field for.
    .
     */
    public Object addColumn(Converter converter)
    {
        if (converter.getField() instanceof ReferenceField)
            return new SEditText(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.SET_ANCHOR), this, converter, ScreenConstants.DEFAULT_DISPLAY);
        else
            return super.addColumn(converter);
    }
    /**
     * Get the next grid record.
     * @param bFirstTime If true, I want the first record.
     * @return the next record (or null if EOF).
     */
    public Record getNextGridRecord(boolean bFirstTime) throws DBException
    {
        Record record = super.getNextGridRecord(bFirstTime);
        if (record != null)
        {
            ReferenceField fldProductID = (ReferenceField)record.getField(BookingDetail.PRODUCT_ID);
            if (fldProductID.getListener(ReadSecondaryHandler.class.getName()) == null)
            {
                fldProductID.addListener(new ReadSecondaryHandler(fldProductID.getReferenceRecord()));
            }
        }
        return record;
    }

}
