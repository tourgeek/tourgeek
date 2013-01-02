/**
  * @(#)GetLandCostHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.product.land.event;

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
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  GetLandCostHandler - Lookup the land cost, given fields for the rate type and date..
 */
public class GetLandCostHandler extends GetProductCostHandler
{
    /**
     * Default constructor.
     */
    public GetLandCostHandler()
    {
        super();
    }
    /**
     * GetLandCostHandler Method.
     */
    public GetLandCostHandler(Record recProductVars, Integer intRegistryID)
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
            && (m_recProductVars.getField(ProductScreenRecord.PAX).getValue() > 0))
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
        if (iStatus == CostStatus.VALID)
        {   // The returned cost is the double cost
            // All data is in the correct field
        }
        else
        {
            recProduct.getField(Land.PMC_COST_HOME).setData(null);
            recProduct.getField(Land.SIC_COST_HOME).setData(null);
        }
        super.setupScreenStatus(recProduct, iStatus);
    }

}
