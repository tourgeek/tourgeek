/**
 * @(#)LandInfoScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.land.screen;

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
import com.tourapp.tour.product.land.db.*;

/**
 *  LandInfoScreenRecord - .
 */
public class LandInfoScreenRecord extends ProductInfoScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kProductID = kProductID;
    //public static final int kRateID = kRateID;
    //public static final int kClassID = kClassID;
    public static final int kLandInfoScreenRecordLastField = kProductInfoScreenRecordLastField;
    public static final int kLandInfoScreenRecordFields = kProductInfoScreenRecordLastField - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public LandInfoScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public LandInfoScreenRecord(RecordOwner screen)
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

    public static final String kLandInfoScreenRecordFile = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kProductID)
            field = new LandField(this, "ProductID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRateID)
            field = new LandRateField(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kClassID)
            field = new LandClassField(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kLandInfoScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
