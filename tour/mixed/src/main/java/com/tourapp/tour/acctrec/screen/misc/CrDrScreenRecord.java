/**
 * @(#)CrDrScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.misc;

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
import com.tourapp.tour.booking.entry.acctrec.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctrec.db.event.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  CrDrScreenRecord - Screen Fields for Screen Record entry.
 */
public class CrDrScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kCounterAccountID = kScreenRecordLastField + 1;
    public static final int kCrDrScreenRecordLastField = kCounterAccountID;
    public static final int kCrDrScreenRecordFields = kCounterAccountID - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public CrDrScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CrDrScreenRecord(RecordOwner screen)
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

    public static final String kCrDrScreenRecordFile = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kCounterAccountID)
            field = new AccountField(this, "CounterAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCrDrScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
