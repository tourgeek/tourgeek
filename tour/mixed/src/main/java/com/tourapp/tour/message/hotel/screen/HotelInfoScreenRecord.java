/**
 * @(#)HotelInfoScreenRecord.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.hotel.screen;

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
import com.tourapp.tour.booking.message.base.screen.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.hotel.db.*;

/**
 *  HotelInfoScreenRecord - .
 */
public class HotelInfoScreenRecord extends ProductInfoScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final String PRODUCT_ID = PRODUCT_ID;
    //public static final String RATE_ID = RATE_ID;
    //public static final String CLASS_ID = CLASS_ID;
    //public static final String DETAIL_DATE = DETAIL_DATE;
    //public static final String TOTAL_COST = TOTAL_COST;
    //public static final String AVAILABILITY = AVAILABILITY;
    //public static final String CONFIRMED_BY = CONFIRMED_BY;
    //public static final String CONFIRMATION_NO = CONFIRMATION_NO;
    public static final String NIGHTS = "Nights";
    /**
     * Default constructor.
     */
    public HotelInfoScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public HotelInfoScreenRecord(RecordOwner screen)
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

    public static final String HOTEL_INFO_SCREEN_RECORD_FILE = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new HotelField(this, PRODUCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new HotelRateField(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 2)
            field = new HotelClassField(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 3)
        //  field = new DateField(this, DETAIL_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 4)
        //  field = new FullCurrencyField(this, TOTAL_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 5)
        //  field = new IntegerField(this, AVAILABILITY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 6)
        //  field = new StringField(this, CONFIRMED_BY, 50, null, null);
        //if (iFieldSeq == 7)
        //  field = new StringField(this, CONFIRMATION_NO, 60, null, null);
        if (iFieldSeq == 8)
            field = new ShortField(this, NIGHTS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
