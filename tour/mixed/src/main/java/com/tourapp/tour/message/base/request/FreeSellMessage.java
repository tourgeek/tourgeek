/**
 * @(#)FreeSellMessage.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.base.request;

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
import com.tourapp.tour.message.base.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.model.db.*;

/**
 *  FreeSellMessage - Free sell this item (status is automatically set to VALID).
 */
public class FreeSellMessage extends BaseProductMessageDesc
{
    /**
     * Default constructor.
     */
    public FreeSellMessage()
    {
        super();
    }
    /**
     * FreeSellMessage Method.
     */
    public FreeSellMessage(MessageDataParent messageDataParent, String strKey)
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
     * Check to make sure all the data is present to attempt a cost lookup.
     * Note: You are NOT returning the status, you are returning the status of the params,
     * The calling program will change the status if required.
     * @return DATA_REQUIRED if all the data is not present, DATA_VALID if the data is OKAY.
     */
    public int checkRequestParams(Rec record)
    {
        return BaseDataStatus.VALID;    // Status is always valid for free-sells
    }

}
