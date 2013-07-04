
package com.tourgeek.tour.booking.remote.booking.detail;

import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.remote.db.*;
import com.tourgeek.tour.booking.db.*;
import org.jbundle.thin.base.remote.*;
import org.jbundle.base.remote.*;
import com.tourgeek.thin.app.booking.entry.search.*;
import com.tourgeek.tour.booking.detail.db.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.base.db.*;

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
    public BookingDetailBaseSession(BaseSession parentSessionObject, Record record, Map<String, Object> objectID) throws RemoteException
    {
        this();
        this.init(parentSessionObject, record, objectID);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseSession parentSessionObject, Record record, Map<String, Object> objectID)
    {
        super.init(parentSessionObject, record, objectID);
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
        Record recBooking = this.getRecord(Booking.BOOKING_FILE);
        this.getMainRecord().addListener(new SubFileFilter(recBooking));
        
        BookingDetail recBookingDetail = (BookingDetail)this.getMainRecord();
        Record recProduct = (Record)recBookingDetail.getProduct();
        recBookingDetail.getField(BookingDetail.PRODUCT_ID).addListener(new ReadSecondaryHandler(recProduct));
        
        Record recVendor = ((ReferenceField)recProduct.getField(Product.VENDOR_ID)).getReferenceRecord(this);
        recProduct.getField(Product.VENDOR_ID).addListener(new ReadSecondaryHandler(recVendor));
        
        Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReferenceRecord(this);
        recVendor.getField(Vendor.CURRENCYS_ID).addListener(new ReadSecondaryHandler(recCurrencys));
        
        // This code read the currency CODE into a virtual field for use in displays 
        MoveOnChangeHandler moveListener = new MoveOnChangeHandler(recBookingDetail.getField(BookingDetail.CURRENCY_CODE), recCurrencys.getField(Currencys.CURRENCY_CODE));
        moveListener.setRespondsToMode(DBConstants.INIT_MOVE, true);
        moveListener.setRespondsToMode(DBConstants.READ_MOVE, true);
        recBookingDetail.getField(BookingDetail.PRODUCT_ID).addListener(moveListener);
    }

}
