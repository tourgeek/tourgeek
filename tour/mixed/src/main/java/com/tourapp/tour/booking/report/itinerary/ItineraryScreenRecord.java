/**
 * @(#)ItineraryScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.report.itinerary;

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
import org.jbundle.main.db.*;
import com.tourapp.tour.booking.db.*;

/**
 *  ItineraryScreenRecord - .
 */
public class ItineraryScreenRecord extends ReportScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kTourID = kReportScreenRecordLastField + 1;
    public static final int kBookingID = kTourID + 1;
    public static final int ktemplate = kBookingID + 1;
    public static final int kfileout = ktemplate + 1;
    public static final int ksendMessageBy = kfileout + 1;
    public static final int kdestinationAddress = ksendMessageBy + 1;
    public static final int kItineraryScreenRecordLastField = kdestinationAddress;
    public static final int kItineraryScreenRecordFields = kdestinationAddress - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public ItineraryScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ItineraryScreenRecord(RecordOwner screen)
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

    public static final String kItineraryScreenRecordFile = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kTourID)
            field = new ReferenceField(this, "TourID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBookingID)
            field = new BookingField(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == ktemplate)
            field = new StringField(this, "template", 40, null, "tour/itinerary");
        if (iFieldSeq == kfileout)
            field = new StringField(this, "fileout", 128, null, null);
        if (iFieldSeq == ksendMessageBy)
            field = new StringField(this, "sendMessageBy", 30, null, null);
        if (iFieldSeq == kdestinationAddress)
            field = new StringField(this, "destinationAddress", 128, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kItineraryScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
