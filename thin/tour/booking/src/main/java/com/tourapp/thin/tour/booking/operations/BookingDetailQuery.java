/**
 * @(#)BookingDetailQuery.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.operations;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.booking.operations.*;

public class BookingDetailQuery extends FieldList
    implements BookingDetailQueryModel
{

    public BookingDetailQuery()
    {
        super();
    }
    public BookingDetailQuery(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}
