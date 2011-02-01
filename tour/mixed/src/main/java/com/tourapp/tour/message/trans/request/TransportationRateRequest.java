/**
 *  @(#)TransportationRateRequest.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.trans.request;

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
import com.tourapp.tour.message.base.request.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.trans.request.data.*;
import org.jbundle.main.msg.db.*;

/**
 *  TransportationRateRequest - .
 */
public class TransportationRateRequest extends ProductRateRequest
{
    /**
     * Default constructor.
     */
    public TransportationRateRequest()
    {
        super();
    }
    /**
     * TransportationRateRequest Method.
     */
    public TransportationRateRequest(MessageDataParent messageDataParent, String strKey)
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
     * CreateProductMessageData Method.
     */
    public ProductMessageData createProductMessageData()
    {
        return new TransportationMessageData(this, PRODUCT_MESSAGE);
    }

}
