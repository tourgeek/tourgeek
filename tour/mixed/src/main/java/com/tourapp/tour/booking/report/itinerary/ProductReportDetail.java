/**
 * @(#)ProductReportDetail.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.hotel.db.*;

/**
 *  ProductReportDetail - .
 */
public class ProductReportDetail extends RecordReportDetail
{
    /**
     * Default constructor.
     */
    public ProductReportDetail()
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
    public ProductReportDetail(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new Vendor(this);
        new City(this);
        City record = new City(this);
        try {
            record.hasNext();   // Make sure this is open before I change the name.
        } catch (DBException e) {
            e.printStackTrace();
        }
        record.setTableNames("To" + record.getTableNames(false));
    }
    /**
     * Number of Screen Fields in this screen.
     * @return screen field count.
     */
    public int getSFieldCount()
    {
        int iFieldCount = super.getSFieldCount();
        Record recBookingDetail = this.getRecord(BookingDetail.kBookingDetailFile);
        if (recBookingDetail != null)
            if ((recBookingDetail.getEditMode() == DBConstants.EDIT_CURRENT) || (recBookingDetail.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
        {
            BaseTable currentTable = recBookingDetail.getTable().getCurrentTable();
            if (currentTable == null)
                currentTable = recBookingDetail.getTable();    // First time only
            recBookingDetail = currentTable.getRecord();
            Record recProduct = ((ReferenceField)recBookingDetail.getField(BookingDetail.kProductID)).getReferenceRecord();
            if (recProduct != null)
            {
                iFieldCount = iFieldCount - Hotel.kHotelFields + recProduct.getFieldCount();
                if ((DBConstants.TRUE.equalsIgnoreCase(this.getProperty(City.kCityFile))) || (DBConstants.YES.equalsIgnoreCase(this.getProperty(City.kCityFile))))
                {
                    Record recCity = this.getRecord(City.kCityFile);
                    ((ReferenceField)recProduct.getField(Product.kCityID)).setReferenceRecord(recCity);
                    ((ReferenceField)recProduct.getField(Product.kCityID)).getReference();
                    ((ReferenceField)recProduct.getField(Product.kCityID)).setReferenceRecord(null);
                    Record recCity2 = this.getRecord("To" + City.kCityFile);
                    try {
                        recCity2.addNew();
                    } catch (DBException e) {
                        e.printStackTrace();
                    }
                    if (recProduct instanceof TransportProduct)
                    {
                        recCity2.setTableNames(City.kCityFile);
                        ((ReferenceField)recProduct.getField(TransportProduct.kToCityID)).setReferenceRecord(recCity2);
                        ((ReferenceField)recProduct.getField(TransportProduct.kToCityID)).getReference();
                        ((ReferenceField)recProduct.getField(TransportProduct.kToCityID)).setReferenceRecord(null);                   
                        recCity2.setTableNames("To" + recCity2.getTableNames(false));
                    }
                }
                if ((DBConstants.TRUE.equalsIgnoreCase(this.getProperty(Vendor.kVendorFile))) || (DBConstants.YES.equalsIgnoreCase(this.getProperty(Vendor.kVendorFile))))
                {
                    Record recVendor = this.getRecord(Vendor.kVendorFile);
                    ((ReferenceField)recProduct.getField(Product.kVendorID)).setReferenceRecord(recVendor);
                    ((ReferenceField)recProduct.getField(Product.kVendorID)).getReference();
                    ((ReferenceField)recProduct.getField(Product.kVendorID)).setReferenceRecord(null);
                }
            }
        }
        return iFieldCount;
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        if ((DBConstants.TRUE.equalsIgnoreCase(this.getProperty(City.kCityFile))) || (DBConstants.YES.equalsIgnoreCase(this.getProperty(City.kCityFile))))
        {
            Record recCity = this.getRecord(City.kCityFile);
            for (int iFieldSeq = 0; iFieldSeq < City.kCityFields; iFieldSeq++)
            {
                this.addDetailXMLColumn(recCity, iFieldSeq);
            }
        
            Record recCity2 = this.getRecord("To" + City.kCityFile);
            for (int iFieldSeq = 0; iFieldSeq < City.kCityFields; iFieldSeq++)
            {
                this.addDetailXMLColumn(recCity2, iFieldSeq);
            }
        }
        if ((DBConstants.TRUE.equalsIgnoreCase(this.getProperty(Vendor.kVendorFile))) || (DBConstants.YES.equalsIgnoreCase(this.getProperty(Vendor.kVendorFile))))
        {
            Record recVendor = this.getRecord(Vendor.kVendorFile);
            for (int iFieldSeq = 0; iFieldSeq < Vendor.kVendorFields; iFieldSeq++)
            {
                this.addDetailXMLColumn(recVendor, iFieldSeq);
            }
        }
        
        Record recBookingDetail = this.getRecord(BookingDetail.kBookingDetailFile);
        for (int iFieldSeq = 0; iFieldSeq < Hotel.kHotelFields; iFieldSeq++)    // Hotel.kHotelFields is the largest possible value
        {
            this.addDetailXMLColumn(recBookingDetail, BookingDetail.kProductID, iFieldSeq);
        }
    }

}
