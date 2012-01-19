/**
 * @(#)TestHotelRateScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
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

    public static final int kProductID = kScreenRecordLastField + 1;
    public static final int kRateID = kProductID + 1;
    public static final int kClassID = kRateID + 1;
    public static final int kDetailDate = kClassID + 1;
    public static final int kTotalCost = kDetailDate + 1;
    public static final int kDisplayCostStatusID = kTotalCost + 1;
    public static final int kTestHotelRateScreenRecordLastField = kDisplayCostStatusID;
    public static final int kTestHotelRateScreenRecordFields = kDisplayCostStatusID - DBConstants.MAIN_FIELD + 1;
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

    public static final String kTestHotelRateScreenRecordFile = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kProductID)
            field = new HotelField(this, "ProductID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRateID)
            field = new HotelRateField(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kClassID)
            field = new HotelClassField(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDetailDate)
            field = new DateField(this, "DetailDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalCost)
            field = new CurrencyField(this, "TotalCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDisplayCostStatusID)
            field = new CostStatusField(this, "DisplayCostStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTestHotelRateScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
