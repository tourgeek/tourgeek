/**
 * @(#)BaseRate.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.product.base.db.*;

public class BaseRate extends FieldList
    implements BaseRateModel
{

    //public static final String ID = ID;

    public BaseRate()
    {
        super();
    }
    public BaseRate(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}
