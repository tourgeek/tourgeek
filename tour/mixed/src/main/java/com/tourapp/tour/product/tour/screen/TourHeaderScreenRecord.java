/**
 * @(#)TourHeaderScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.screen;

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
import com.tourapp.tour.booking.lookup.*;
import com.tourapp.tour.base.db.shared.*;
import com.tourapp.tour.product.air.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.base.db.*;

/**
 *  TourHeaderScreenRecord - .
 */
public class TourHeaderScreenRecord extends ProductScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String DISPLAY_TYPE = "DisplayType";
    public static final int kDisplayType = kProductScreenRecordLastField + 1;
    public static final String BROCHURE_ID = "BrochureID";
    public static final int kBrochureID = kDisplayType + 1;
    public static final String AIRLINE_ID = "AirlineID";
    public static final int kAirlineID = kBrochureID + 1;
    public static final String TOUR_TYPE = "TourType";
    public static final int kTourType = kAirlineID + 1;
    public static final String START_DEPARTURE_DATE = "StartDepartureDate";
    public static final int kStartDepartureDate = kTourType + 1;
    public static final String END_DEPARTURE_DATE = "EndDepartureDate";
    public static final int kEndDepartureDate = kStartDepartureDate + 1;
    public static final String REGION_ID = "RegionID";
    public static final int kRegionID = kEndDepartureDate + 1;
    public static final String KEY_ORDER = "KeyOrder";
    public static final int kKeyOrder = kRegionID + 1;
    public static final int kTourHeaderScreenRecordLastField = kKeyOrder;
    public static final int kTourHeaderScreenRecordFields = kKeyOrder - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public TourHeaderScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourHeaderScreenRecord(RecordOwner screen)
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

    public static final String kTourHeaderScreenRecordFile = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kDisplayType)
            field = new DisplayTypeField(this, "DisplayType", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBrochureID)
            field = new BrochureField(this, "BrochureID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAirlineID)
            field = new AirlineField(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourType)
            field = new TourTypeSelect(this, "TourType", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartDepartureDate)
            field = new DateField(this, "StartDepartureDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndDepartureDate)
            field = new DateField(this, "EndDepartureDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRegionID)
            field = new RegionField(this, "RegionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kKeyOrder)
            field = new ShortField(this, "KeyOrder", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTourHeaderScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
