
package com.tourgeek.tour.message.base.response;

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
import org.jbundle.thin.base.message.*;
import org.jbundle.model.message.*;

/**
 *  CancelResponse - Cancellation message response.
 */
public class CancelResponse extends ProductBookingResponse
{
    /**
     * Default constructor.
     */
    public CancelResponse()
    {
        super();
    }
    /**
     * CancelResponse Method.
     */
    public CancelResponse(MessageDataParent messageDataParent, String strKey)
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
     * Get the key prefix for this type of message.
     */
    public String getKeyPrefix()
    {
        return "message.cancel.";
    }

}
