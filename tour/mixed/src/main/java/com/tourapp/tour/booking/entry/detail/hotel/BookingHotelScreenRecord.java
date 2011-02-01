/**
 *  @(#)BookingHotelScreenRecord.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.detail.hotel;

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
import com.tourapp.tour.booking.entry.detail.base.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.thin.app.booking.entry.*;
import com.tourapp.tour.booking.entry.base.*;

/**
 *  BookingHotelScreenRecord - Screen Fields for BkBooking.
 */
public class BookingHotelScreenRecord extends BookingDetailScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kMarkupFromLast = kMarkupFromLast;
    public static final int kSingleCostLocal = kBookingDetailScreenRecordLastField + 1;
    public static final int kDoubleCostLocal = kSingleCostLocal + 1;
    public static final int kTripleCostLocal = kDoubleCostLocal + 1;
    public static final int kQuadCostLocal = kTripleCostLocal + 1;
    public static final int kBookingHotelScreenRecordLastField = kQuadCostLocal;
    public static final int kBookingHotelScreenRecordFields = kQuadCostLocal - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public BookingHotelScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingHotelScreenRecord(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }

    public static final String kBookingHotelScreenRecordFile = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kSingleCostLocal)
            field = new CurrencyField(this, "SingleCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDoubleCostLocal)
            field = new CurrencyField(this, "DoubleCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTripleCostLocal)
            field = new CurrencyField(this, "TripleCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kQuadCostLocal)
            field = new CurrencyField(this, "QuadCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kMarkupFromLast)
        //  field = new (this, "MarkupFromLast", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBookingHotelScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
