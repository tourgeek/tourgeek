/**
  * @(#)ProductAvailabilityRequest.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.message.base.request;

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
import org.jbundle.model.message.*;
import org.jbundle.main.msg.db.*;

/**
 *  ProductAvailabilityRequest - .
 */
public class ProductAvailabilityRequest extends ProductRequest
{
    /**
     * Default constructor.
     */
    public ProductAvailabilityRequest()
    {
        super();
    }
    /**
     * ProductAvailabilityRequest Method.
     */
    public ProductAvailabilityRequest(MessageDataParent messageDataParent, String strKey)
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
     * GetRequestType Method.
     */
    public String getRequestType()
    {
        return RequestType.AVAILABILITY;
    }

}
