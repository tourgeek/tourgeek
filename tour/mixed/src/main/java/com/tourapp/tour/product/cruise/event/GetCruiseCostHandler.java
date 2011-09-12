/**
 * @(#)GetCruiseCostHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.cruise.event;

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
import com.tourapp.tour.product.base.event.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  GetCruiseCostHandler - Get the cruise cost..
 */
public class GetCruiseCostHandler extends GetProductCostHandler
{
    /**
     * Default constructor.
     */
    public GetCruiseCostHandler()
    {
        super();
    }
    /**
     * GetCruiseCostHandler Method.
     */
    public GetCruiseCostHandler(Record recProductVars, Integer intRegistryID)
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
            && (!m_recProductVars.getField(ProductScreenRecord.kClassID).isNull()))
            return true;
        return false;
    }
    /**
     * Move this product cost from to virtual fields to the ProductCost
     * field in recProduct. Also move the status to the product cost status.
     * (Override this to set the correct fields!).
     */
    public void setupScreenStatus(Record recProduct, int iStatus)
    {
        super.setupScreenStatus(recProduct, iStatus);
    }

}
