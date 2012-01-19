/**
 * @(#)TransportationBookingChangeResponse.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.trans.response;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.thin.base.message.*;

/**
 *  TransportationBookingChangeResponse - .
 */
public class TransportationBookingChangeResponse extends TransportationBookingResponse
{
    /**
     * Default constructor.
     */
    public TransportationBookingChangeResponse()
    {
        super();
    }
    /**
     * TransportationBookingChangeResponse Method.
     */
    public TransportationBookingChangeResponse(MessageDataParent messageDataParent, String strKey)
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
