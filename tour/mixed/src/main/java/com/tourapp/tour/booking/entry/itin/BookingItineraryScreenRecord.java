/**
 * @(#)BookingItineraryScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.entry.detail.base.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.main.db.base.*;

/**
 *  BookingItineraryScreenRecord - .
 */
public class BookingItineraryScreenRecord extends BookingDetailScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final String PP_COST_LOCAL = PP_COST_LOCAL;
    public static final String MESSAGE_PROCESS_INFO_ID = "MessageProcessInfoID";
    public static final String MESSAGE_TRANSPORT_ID = "MessageTransportID";
    public static final String ITINERARY_STYLE_SHEET = "ItineraryStyleSheet";
    public static final String ACTION_TARGET = "ActionTarget";
    public static final String ITINERARY_TEXT = "ItineraryText";
    public static final String ITINERARY_PROPERTIES = "ItineraryProperties";
    public static final String REQUEST_TYPE_ID = "RequestTypeID";
    public static final String CONTACT_TYPE_ID = "ContactTypeID";
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

    public static final String BOOKING_ITINERARY_SCREEN_RECORD_FILE = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //  field = new CurrencyField(this, PP_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new MessageProcessInfoManualField(this, MESSAGE_PROCESS_INFO_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 2)
            field = new MessageTransportManualSelect(this, MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 3)
            field = new StringField(this, ITINERARY_STYLE_SHEET, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new StringField(this, ACTION_TARGET, 40, null, null);
        if (iFieldSeq == 5)
            field = new HtmlField(this, ITINERARY_TEXT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new PropertiesField(this, ITINERARY_PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new RequestTypeField(this, REQUEST_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new ContactTypeField(this, CONTACT_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
