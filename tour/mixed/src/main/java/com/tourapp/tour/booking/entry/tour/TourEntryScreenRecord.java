/**
 * @(#)TourEntryScreenRecord.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.tour;

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
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.land.db.*;

/**
 *  TourEntryScreenRecord - Screen Fields.
 */
public class TourEntryScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String START_TARGET_DATE = "StartTargetDate";
    public static final String END_TARGET_DATE = "EndTargetDate";
    public static final String TOUR_HEADER_ID = "TourHeaderID";
    public static final String LAND_CLASS_ID = "LandClassID";
    /**
     * Default constructor.
     */
    public TourEntryScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourEntryScreenRecord(RecordOwner screen)
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

    public static final String TOUR_ENTRY_SCREEN_RECORD_FILE = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new DateField(this, START_TARGET_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new DateField(this, END_TARGET_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 2)
            field = new TourHeaderField(this, TOUR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 3)
            field = new LandClassField(this, LAND_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
