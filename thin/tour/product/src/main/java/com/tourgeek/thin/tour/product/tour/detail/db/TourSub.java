/**
  * @(#)TourSub.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.product.tour.detail.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.product.tour.detail.db.*;

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
