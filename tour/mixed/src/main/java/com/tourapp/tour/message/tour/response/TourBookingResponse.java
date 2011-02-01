/**
 *  @(#)TourBookingResponse.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.tour.response;

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
import com.tourapp.tour.message.base.response.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.booking.db.*;

/**
 *  TourBookingResponse - .
 */
public class TourBookingResponse extends ProductBookingResponse
{
    /**
     * Default constructor.
     */
    public TourBookingResponse()
    {
        super();
    }
    /**
     * TourBookingResponse Method.
     */
    public TourBookingResponse(MessageDataParent messageDataParent, String strKey)
    {
        this();
        this.init(messageDataParent, strKey);
    }
    /**
     * Initialize class fields.
     */
    public void init(MessageDataParent messageDataParent, String strKey)
    {
        super.init(messageDataParent, strKey);
    }
    /**
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(FieldList record)
    {
        // For now, don't extract the detail pricing. It was already add on the costing lookup
        // todo(don) - May want to confirm pricing?
        //Booking recBooking = ((BookingDetail)record).getBooking(true);
        //this.addLineItemsToBooking(recBooking);
        return super.getRawRecordData(record);
    }

}
