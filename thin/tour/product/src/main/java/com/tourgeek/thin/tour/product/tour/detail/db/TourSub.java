/**
  * @(#)TourSub.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.product.tour.detail.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.product.tour.detail.db.*;

public class TourSub extends FieldList
    implements TourSubModel
{
    private static final long serialVersionUID = 1L;


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
