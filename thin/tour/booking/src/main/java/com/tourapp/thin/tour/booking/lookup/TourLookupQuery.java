/**
 * @(#)TourLookupQuery.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.lookup;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.booking.lookup.*;

public class TourLookupQuery extends FieldList
    implements TourLookupQueryModel
{
    private static final long serialVersionUID = 1L;


    public TourLookupQuery()
    {
        super();
    }
    public TourLookupQuery(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}
