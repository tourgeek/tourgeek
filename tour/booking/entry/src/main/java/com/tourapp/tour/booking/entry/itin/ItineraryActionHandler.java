/**
  * @(#)ItineraryActionHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.db.*;
import java.net.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.thin.base.screen.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.base.message.core.trx.*;
import org.jbundle.base.message.core.trx.internal.*;
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
        
        Record recBooking = (Record)recordOwner.getRecord(Booking.BOOKING_FILE);
        BaseField fldDestination = ((Record)recordOwner.getScreenRecord()).getField(BookingItineraryScreenRecord.ACTION_TARGET);
        Record recMessageTransport = ((ReferenceField)recordOwner.getScreenRecord().getField(BookingItineraryScreenRecord.MESSAGE_TRANSPORT_ID)).getReference();
        if (recMessageTransport != null)
        {
            String strMessageTransport = recMessageTransport.getField(MessageTransport.CODE).toString();
            if (MessageTransport.FAX.equalsIgnoreCase(strMessageTransport))
            {
                fldDestination.moveFieldToThis(recBooking.getField(Booking.FAX));
            }
            else if (MessageTransport.EMAIL.equalsIgnoreCase(strMessageTransport))
            {
                fldDestination.moveFieldToThis(recBooking.getField(Booking.EMAIL));
            }
        }
        
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
