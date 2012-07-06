/**
  * @(#)GetHotelCostHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.acctpay.db.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  GetHotelCostHandler - This handler looks up the hotel cost, given the rate, class, and date
and places the hotel cost in this field..
 */
public class GetHotelCostHandler extends GetProductCostHandler
{
    /**
     * Default constructor.
     */
    public GetHotelCostHandler()
    {
        super();
    }
    /**
     * GetHotelCostHandler Method.
     */
    public GetHotelCostHandler(Record recProductVars, Integer intRegistryID)
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
    /**
     * Move this product cost from to virtual fields to the ProductCost
     * field in recProduct. Also move the status to the product cost status.
     * (Override this to set the correct fields!).
     */
    public void setupScreenStatus(Record recProduct, int iStatus)
    {
        if (iStatus == CostStatus.VALID)
        {   // The returned cost is the double cost
            double dProductCost = recProduct.getField(Product.PRODUCT_COST).getValue();
            recProduct.getField(Hotel.DOUBLE_COST).setValue(dProductCost);
        }
        else
            recProduct.getField(Hotel.DOUBLE_COST).setData(null);
        super.setupScreenStatus(recProduct, iStatus);
    }

}
