/**
 *  @(#)BookingItineraryScreenRecord.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.itin;

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
import com.tourapp.tour.booking.entry.detail.base.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.main.msg.db.base.*;

/**
 *  BookingItineraryScreenRecord - .
 */
public class BookingItineraryScreenRecord extends BookingDetailScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kMessageProcessInfoID = kBookingDetailScreenRecordLastField + 1;
    public static final int kMessageTransportID = kMessageProcessInfoID + 1;
    public static final int kItineraryStyleSheet = kMessageTransportID + 1;
    public static final int kActionTarget = kItineraryStyleSheet + 1;
    public static final int kItineraryText = kActionTarget + 1;
    public static final int kItineraryProperties = kItineraryText + 1;
    public static final int kRequestTypeID = kItineraryProperties + 1;
    public static final int kContactTypeID = kRequestTypeID + 1;
    public static final int kBookingItineraryScreenRecordLastField = kContactTypeID;
    public static final int kBookingItineraryScreenRecordFields = kContactTypeID - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public BookingItineraryScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingItineraryScreenRecord(RecordOwner screen)
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

    public static final String kBookingItineraryScreenRecordFile = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kMessageProcessInfoID)
            field = new MessageProcessInfoManualField(this, "MessageProcessInfoID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMessageTransportID)
            field = new MessageTransportManualSelect(this, "MessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kItineraryStyleSheet)
            field = new StringField(this, "ItineraryStyleSheet", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kActionTarget)
            field = new StringField(this, "ActionTarget", 40, null, null);
        if (iFieldSeq == kItineraryText)
            field = new HtmlField(this, "ItineraryText", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kItineraryProperties)
            field = new PropertiesField(this, "ItineraryProperties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRequestTypeID)
            field = new RequestTypeField(this, "RequestTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kContactTypeID)
            field = new ContactTypeField(this, "ContactTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBookingItineraryScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
