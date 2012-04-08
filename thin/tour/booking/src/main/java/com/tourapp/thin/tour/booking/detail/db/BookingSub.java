/**
 * @(#)BookingSub.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.detail.db;

import com.tourapp.model.tour.booking.db.*;
import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.booking.detail.db.*;

public class BookingSub extends FieldList
    implements BookingSubModel
{
    private static final long serialVersionUID = 1L;


    public BookingSub()
    {
        super();
    }
    public BookingSub(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    /**
     * AddDetailBehaviors Method.
     */
    public void addDetailBehaviors(BookingModel recBooking, TourModel recTour)
    {
        // Empty implementation
    }
    /**
     * Get the main (Booking) record for this detail record.
     * Note: This will only return the main record if it already exists.
     */
    public BookingModel getBooking(boolean bCreateAndReadCurrent)
    {
        return null; // Empty implementation
    }

}
