/**
 *  @(#)TourAvailabilityResponse.
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
import org.jbundle.model.db.*;

/**
 *  TourAvailabilityResponse - .
 */
public class TourAvailabilityResponse extends ProductAvailabilityResponse
{
    /**
     * Default constructor.
     */
    public TourAvailabilityResponse()
    {
        super();
    }
    /**
     * TourAvailabilityResponse Method.
     */
    public TourAvailabilityResponse(MessageDataParent messageDataParent, String strKey)
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
