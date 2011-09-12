/**
 * @(#)CarScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.car.screen;

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
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.car.db.*;

/**
 *  CarScreenRecord - .
 */
public class CarScreenRecord extends ProductScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kClassID = kClassID;
    //public static final int kRateID = kRateID;
    public static final int kCarScreenRecordLastField = kProductScreenRecordLastField;
    public static final int kCarScreenRecordFields = kProductScreenRecordLastField - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public CarScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CarScreenRecord(RecordOwner screen)
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

    public static final String kCarScreenRecordFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kClassID)
            field = new CarClassField(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRateID)
            field = new CarRateField(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCarScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
