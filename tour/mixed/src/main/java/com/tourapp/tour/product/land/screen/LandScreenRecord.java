/**
 * @(#)LandScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.land.screen;

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
import com.tourapp.tour.product.base.screen.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.land.db.*;

/**
 *  LandScreenRecord - Screen Fields.
 */
public class LandScreenRecord extends ProductScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kProductID = kProductID;
    //public static final int kStartDate = kStartDate;
    //public static final int kEndDate = kEndDate;
    //public static final int kBlocked = kBlocked;
    //public static final int kOversell = kOversell;
    public static final int kLandClassID = kProductScreenRecordLastField + 1;
    public static final int kLandScreenRecordLastField = kLandClassID;
    public static final int kLandScreenRecordFields = kLandClassID - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public LandScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public LandScreenRecord(RecordOwner screen)
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

    public static final String kLandScreenRecordFile = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kProductID)
            field = new LandField(this, "ProductID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLandClassID)
            field = new LandClassField(this, "LandClassID", 1, null, null);
        //if (iFieldSeq == kStartDate)
        //  field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kEndDate)
        //  field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kBlocked)
        //  field = new ShortField(this, "Blocked", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kOversell)
        //  field = new ShortField(this, "Oversell", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kLandScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
