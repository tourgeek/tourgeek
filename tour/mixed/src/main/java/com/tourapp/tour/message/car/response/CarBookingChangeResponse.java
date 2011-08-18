/**
 *  @(#)CarBookingChangeResponse.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.car.response;

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
import com.tourapp.tour.message.car.request.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.base.request.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.car.request.data.*;
import org.jbundle.model.db.*;

/**
 *  CarBookingChangeResponse - .
 */
public class CarBookingChangeResponse extends CarBookingResponse
{
    /**
     * Default constructor.
     */
    public CarBookingChangeResponse()
    {
        super();
    }
    /**
     * CarBookingChangeResponse Method.
     */
    public CarBookingChangeResponse(MessageDataParent messageDataParent, String strKey)
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

}
