/**
 * @(#)BookingHotelAvailMsgReplyInProcessor.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.hotel.response.in;

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
import com.tourapp.tour.message.base.response.in.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.hotel.db.*;
import org.jbundle.thin.base.message.*;

/**
 *  BookingHotelAvailMsgReplyInProcessor - .
 */
public class BookingHotelAvailMsgReplyInProcessor extends BookingDetailAvailMsgReplyInProcessor
{
    /**
     * Default constructor.
     */
    public BookingHotelAvailMsgReplyInProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingHotelAvailMsgReplyInProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
    {
        this();
        this.init(taskParent, recordMain, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwnerParent taskParent, Record recordMain, Map<String, Object> properties)
    {
        super.init(taskParent, recordMain, properties);
    }
    /**
     * Open the main file.
     */
    public Record openMainRecord()
    {
        return new BookingHotel(this);
    }

}
