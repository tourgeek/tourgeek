/**
 * @(#)ProductPricing.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.product.base.db.*;

public class ProductPricing extends FieldList
    implements ProductPricingModel
{

    //public static final String ID = ID;

    public ProductPricing()
    {
        super();
    }
    public ProductPricing(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}
