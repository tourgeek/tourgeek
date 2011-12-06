/**
 * @(#)BookingDetailBaseSession.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.remote.booking.detail;

import org.jbundle.base.db.Record;
import org.jbundle.base.db.filter.SubFileFilter;
import org.jbundle.base.field.ReferenceField;
import org.jbundle.base.field.event.MoveOnChangeHandler;
import org.jbundle.base.field.event.ReadSecondaryHandler;
import org.jbundle.base.remote.BaseSession;
import org.jbundle.base.remote.db.TableSession;
import org.jbundle.base.util.DBConstants;
import org.jbundle.thin.base.remote.RemoteException;

import com.tourapp.tour.acctpay.db.Vendor;
import com.tourapp.tour.base.db.Currencys;
import com.tourapp.tour.booking.db.Booking;
import com.tourapp.tour.booking.detail.db.BookingDetail;
import com.tourapp.tour.product.base.db.Product;

/**
 *  BookingDetailBaseSession - .
 */
public class BookingDetailBaseSession extends TableSession
{
    /**
     * Default constructor.
     */
    public BookingDetailBaseSession() throws RemoteException
    {
        super();
    }
    /**
     * BookingDetailBaseSession Method.
     */
    public BookingDetailBaseSession(BaseSession parentSessionObject, Record record, Object objectID) throws RemoteException
    {
        this();
        this.init(parentSessionObject, record, objectID);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseSession parentSessionObject, Record record, Object objectID)
    {
        super.init(parentSessionObject, record, objectID);
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
        Record recBooking = this.getRecord(Booking.kBookingFile);
        this.getMainRecord().addListener(new SubFileFilter(recBooking));
        
        BookingDetail recBookingDetail = (BookingDetail)this.getMainRecord();
        Record recProduct = recBookingDetail.getProduct();
        recBookingDetail.getField(BookingDetail.kProductID).addListener(new ReadSecondaryHandler(recProduct));
        
        Record recVendor = ((ReferenceField)recProduct.getField(Product.kVendorID)).getReferenceRecord(this);
        recProduct.getField(Product.kVendorID).addListener(new ReadSecondaryHandler(recVendor));
        
        Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.kCurrencysID)).getReferenceRecord(this);
        recVendor.getField(Vendor.kCurrencysID).addListener(new ReadSecondaryHandler(recCurrencys));
        
        // This code read the currency CODE into a virtual field for use in displays 
        MoveOnChangeHandler moveListener = new MoveOnChangeHandler(recBookingDetail.getField(BookingDetail.kCurrencyCode), recCurrencys.getField(Currencys.kCurrencyCode));
        moveListener.setRespondsToMode(DBConstants.INIT_MOVE, true);
        moveListener.setRespondsToMode(DBConstants.READ_MOVE, true);
        recBookingDetail.getField(BookingDetail.kProductID).addListener(moveListener);
    }

}
