/**
 * @(#)BookingDetailBaseSession.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.remote.booking.detail;

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
import org.jbundle.base.remote.db.*;
import com.tourapp.tour.booking.db.*;
import java.rmi.*;
import org.jbundle.base.remote.*;
import com.tourapp.thin.app.booking.entry.search.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;

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
