/**
 * @(#)ItineraryActionHandler.
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
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.db.*;
import java.net.*;
import com.tourapp.tour.booking.report.itinerary.*;
import org.jbundle.thin.base.screen.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.base.message.trx.message.*;
import org.jbundle.base.message.trx.message.internal.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.profile.db.*;
import org.jbundle.base.message.trx.processor.*;
import javax.swing.*;

/**
 *  ItineraryActionHandler - .
 */
public class ItineraryActionHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public ItineraryActionHandler()
    {
        super();
    }
    /**
     * ItineraryActionHandler Method.
     */
    public ItineraryActionHandler(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Init Method.
     */
    public void init(BaseField field)
    {
        super.init(field);
        this.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.setRespondsToMode(DBConstants.READ_MOVE, false); // By default, only respond to screen moves
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        RecordOwner recordOwner = this.getOwner().getRecord().getRecordOwner();
        
        Record recBooking = recordOwner.getRecord(Booking.kBookingFile);
        BaseField fldDestination = recordOwner.getScreenRecord().getField(BookingItineraryScreenRecord.kActionTarget);
        Record recMessageTransport = ((ReferenceField)recordOwner.getScreenRecord().getField(BookingItineraryScreenRecord.kMessageTransportID)).getReference();
        if (recMessageTransport != null)
        {
            String strMessageTransport = recMessageTransport.getField(MessageTransport.kCode).toString();
            if (MessageTransport.FAX.equalsIgnoreCase(strMessageTransport))
            {
                fldDestination.moveFieldToThis(recBooking.getField(Booking.kFax));
            }
            else if (MessageTransport.EMAIL.equalsIgnoreCase(strMessageTransport))
            {
                fldDestination.moveFieldToThis(recBooking.getField(Booking.kEmail));
            }
        }
        
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
