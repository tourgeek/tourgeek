/**
 * @(#)TourClassScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.schedule.screen;

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
import com.tourapp.tour.product.tour.db.*;

/**
 *  TourClassScreenRecord - .
 */
public class TourClassScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kTourClassID = kScreenRecordLastField + 1;
    public static final int kTourClassScreenRecordLastField = kTourClassID;
    public static final int kTourClassScreenRecordFields = kTourClassID - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public TourClassScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourClassScreenRecord(RecordOwner screen)
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

    public static final String kTourClassScreenRecordFile = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kTourClassID)
            field = new TourClassField(this, "TourClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTourClassScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
