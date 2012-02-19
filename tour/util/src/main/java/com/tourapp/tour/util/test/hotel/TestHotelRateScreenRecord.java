/**
 * @(#)TestHotelRateScreenRecord.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.util.test.hotel;

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
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  TestHotelRateScreenRecord - Test screen record for hotel rate retrieval..
 */
public class TestHotelRateScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String PRODUCT_ID = "ProductID";
    public static final String RATE_ID = "RateID";
    public static final String CLASS_ID = "ClassID";
    public static final String DETAIL_DATE = "DetailDate";
    public static final String TOTAL_COST = "TotalCost";
    public static final String DISPLAY_COST_STATUS_ID = "DisplayCostStatusID";
    /**
     * Default constructor.
     */
    public TestHotelRateScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TestHotelRateScreenRecord(RecordOwner screen)
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

    public static final String TEST_HOTEL_RATE_SCREEN_RECORD_FILE = null; // Screen field
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
        if (iFieldSeq == 3)
            field = new DateField(this, DETAIL_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new CurrencyField(this, TOTAL_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new CostStatusField(this, DISPLAY_COST_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
