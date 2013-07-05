/**
  * @(#)BookingLookupQuery.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.booking.lookup;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.booking.lookup.*;

public class BookingLookupQuery extends FieldList
    implements BookingLookupQueryModel
{
    private static final long serialVersionUID = 1L;


    public BookingLookupQuery()
    {
        super();
    }
    public BookingLookupQuery(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}
