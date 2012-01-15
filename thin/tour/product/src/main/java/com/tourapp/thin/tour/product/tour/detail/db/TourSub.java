/**
 * @(#)TourSub.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.tour.detail.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.product.tour.detail.db.*;

public class TourSub extends FieldList
    implements TourSubModel
{

    public TourSub()
    {
        super();
    }
    public TourSub(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}
