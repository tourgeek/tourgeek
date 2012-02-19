/**
 * @(#)TourEventScreenRecord.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.main.msg.db.*;

/**
 *  TourEventScreenRecord - .
 */
public class TourEventScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String ACTION_CUTOFF_DATE = "ActionCutoffDate";
    public static final String BOOKING_UPDATE = "BookingUpdate";
    public static final String TOUR_UPDATE = "TourUpdate";
    public static final String RUN_PROCESS_IN = "RunProcessIn";
    /**
     * Default constructor.
     */
    public TourEventScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourEventScreenRecord(RecordOwner screen)
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

    public static final String TOUR_EVENT_SCREEN_RECORD_FILE = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new TourEventScreenRecord_ActionCutoffDate(this, ACTION_CUTOFF_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new BooleanField(this, BOOKING_UPDATE, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == 2)
            field = new BooleanField(this, TOUR_UPDATE, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == 3)
            field = new RunProcessInField(this, RUN_PROCESS_IN, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
