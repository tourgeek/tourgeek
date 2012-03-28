/**
 * @(#)CostStatusUpdateHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.event;

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
import com.tourapp.tour.booking.entry.detail.base.*;

/**
 *  CostStatusUpdateHandler - .
 */
public class CostStatusUpdateHandler extends BaseStatusUpdateHandler
{
    /**
     * Default constructor.
     */
    public CostStatusUpdateHandler()
    {
        super();
    }
    /**
     * CostStatusUpdateHandler Method.
     */
    public CostStatusUpdateHandler(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        super.init(field);
    }
    /**
     * Get the message to display in the message box.
     */
    public String getDisplayMessage()
    {
        // todo(don) Add code to describe any errors
        return super.getDisplayMessage();
    }

}
