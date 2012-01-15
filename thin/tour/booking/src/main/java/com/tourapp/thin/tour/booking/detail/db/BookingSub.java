/**
 * @(#)BookingSub.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.detail.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.booking.detail.db.*;

public class BookingSub extends FieldList
    implements BookingSubModel
{

    public BookingSub()
    {
        super();
    }
    public BookingSub(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}
