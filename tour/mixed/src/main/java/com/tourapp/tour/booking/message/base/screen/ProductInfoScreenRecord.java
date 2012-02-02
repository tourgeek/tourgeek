/**
 * @(#)ProductInfoScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.message.base.screen;

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
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.base.db.*;

/**
 *  ProductInfoScreenRecord - .
 */
public class ProductInfoScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String PRODUCT_ID = "ProductID";
    public static final int kProductID = kScreenRecordLastField + 1;
    public static final String RATE_ID = "RateID";
    public static final int kRateID = kProductID + 1;
    public static final String CLASS_ID = "ClassID";
    public static final int kClassID = kRateID + 1;
    public static final String DETAIL_DATE = "DetailDate";
    public static final int kDetailDate = kClassID + 1;
    public static final String TOTAL_COST = "TotalCost";
    public static final int kTotalCost = kDetailDate + 1;
    public static final String AVAILABILITY = "Availability";
    public static final int kAvailability = kTotalCost + 1;
    public static final String CONFIRMED_BY = "ConfirmedBy";
    public static final int kConfirmedBy = kAvailability + 1;
    public static final String CONFIRMATION_NO = "ConfirmationNo";
    public static final int kConfirmationNo = kConfirmedBy + 1;
    public static final int kProductInfoScreenRecordLastField = kConfirmationNo;
    public static final int kProductInfoScreenRecordFields = kConfirmationNo - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public ProductInfoScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductInfoScreenRecord(RecordOwner screen)
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

    public static final String kProductInfoScreenRecordFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kProductID)
            field = new ProductField(this, "ProductID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRateID)
            field = new BaseRateField(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kClassID)
            field = new BaseClassField(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDetailDate)
            field = new DateField(this, "DetailDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalCost)
            field = new FullCurrencyField(this, "TotalCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAvailability)
            field = new IntegerField(this, "Availability", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kConfirmedBy)
            field = new StringField(this, "ConfirmedBy", 50, null, null);
        if (iFieldSeq == kConfirmationNo)
            field = new StringField(this, "ConfirmationNo", 60, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kProductInfoScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
