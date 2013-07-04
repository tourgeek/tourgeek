
package com.tourgeek.tour.message.tour.response;

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
import com.tourgeek.tour.message.base.response.*;
import org.jbundle.model.message.*;
import com.tourgeek.model.tour.booking.db.*;

/**
 *  TourRateResponse - .
 */
public class TourRateResponse extends ProductRateResponse
{
    /**
     * Default constructor.
     */
    public TourRateResponse()
    {
        super();
    }
    /**
     * TourRateResponse Method.
     */
    public TourRateResponse(MessageDataParent messageDataParent, String strKey)
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
