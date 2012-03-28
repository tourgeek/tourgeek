/**
 * @(#)GetHotelAvailabilityHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.hotel.event;

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
import com.tourapp.tour.product.base.event.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  GetHotelAvailabilityHandler - .
 */
public class GetHotelAvailabilityHandler extends GetProductAvailabilityHandler
{
    /**
     * Default constructor.
     */
    public GetHotelAvailabilityHandler()
    {
        super();
    }
    /**
     * GetHotelAvailabilityHandler Method.
     */
    public GetHotelAvailabilityHandler(Record recProductVars, Integer intRegistryID)
    {
        this();
        this.init(recProductVars, intRegistryID);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record recProductVars, Integer intRegistryID)
    {
        super.init(recProductVars, intRegistryID);
    }
    /**
     * If all the data in the screen record that is required for a query is there,
     * return true. If not, false.
     */
    public boolean isQueryComplete()
    {
        boolean bQueryComplete = super.isQueryComplete();
        if ((bQueryComplete)
            && (!m_recProductVars.getField(ProductScreenRecord.CLASS_ID).isNull()))
            return true;
        return false;
    }

}
