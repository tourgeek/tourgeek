/**
 * @(#)BookingLookupQuery.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.lookup;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.booking.lookup.*;

public class BookingLookupQuery extends FieldList
    implements BookingLookupQueryModel
{

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
