/**
  * @(#)BookingHotelScreenRecord.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.booking.entry.detail.hotel;

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
import com.tourgeek.tour.booking.entry.detail.base.*;
import com.tourgeek.tour.booking.detail.db.*;
import com.tourgeek.tour.product.hotel.db.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.tour.product.land.db.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.thin.app.booking.entry.*;
import com.tourgeek.tour.booking.entry.base.*;

/**
 *  BookingHotelScreenRecord - Screen Fields for BkBooking.
 */
public class BookingHotelScreenRecord extends BookingDetailScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final String PP_COST_LOCAL = PP_COST_LOCAL;
    //public static final String MARKUP_FROM_LAST = MARKUP_FROM_LAST;
    public static final String SINGLE_COST_LOCAL = "SingleCostLocal";
    public static final String DOUBLE_COST_LOCAL = "DoubleCostLocal";
    public static final String TRIPLE_COST_LOCAL = "TripleCostLocal";
    public static final String QUAD_COST_LOCAL = "QuadCostLocal";
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

    public static final String BOOKING_HOTEL_SCREEN_RECORD_FILE = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //  field = new CurrencyField(this, PP_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new CurrencyField(this, SINGLE_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 2)
            field = new CurrencyField(this, DOUBLE_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 3)
            field = new CurrencyField(this, TRIPLE_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new CurrencyField(this, QUAD_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 5)
        //  field = new StringField(this, MARKUP_FROM_LAST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
