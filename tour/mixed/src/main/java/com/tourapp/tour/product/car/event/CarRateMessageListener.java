/**
 * @(#)CarRateMessageListener.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.car.event;

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
import com.tourapp.tour.product.base.event.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.message.car.response.*;
import com.tourapp.tour.message.base.response.*;

/**
 *  CarRateMessageListener - Process car rate messages.
 */
public class CarRateMessageListener extends ProductRateMessageListener
{
    /**
     * Default constructor.
     */
    public CarRateMessageListener()
    {
        super();
    }
    /**
     * CarRateMessageListener Method.
     */
    public CarRateMessageListener(BaseMessageReceiver messageHandler, Record record, boolean bUpdateRecord, BaseMessageFilter messageFilter)
    {
        this();
        this.init(messageHandler, record, bUpdateRecord, messageFilter);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseMessageReceiver messageHandler, Record record, boolean bUpdateRecord, BaseMessageFilter messageFilter)
    {
        super.init(messageHandler, record, bUpdateRecord, messageFilter);
    }
    /**
     * Convert this message map to the message map AutoRecordMessageListener
     * is expecting, so the correct fields will be updated.
     */
    public void fixMessageMap(BaseMessage message)
    {
        super.fixMessageMap(message);
    }
    /**
     * Add message description.
     */
    public ProductRateResponse addMessageDesc(BaseMessage message)
    {
        return new CarRateResponse(message, null);
    }

}
