/**
 * @(#)BookingLine_CommissionRate.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.detail.db;

import java.awt.*;
import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;

/**
 *  BookingLine_CommissionRate - .
 */
public class BookingLine_CommissionRate extends FloatField
{
    /**
     * Default constructor.
     */
    public BookingLine_CommissionRate()
    {
        super();
    }
    /**
     * BookingLine_CommissionRate Method.
     */
    public BookingLine_CommissionRate(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize the field.
     */
    public int initField(boolean displayOption)
    {
        return this.setString(""/**BK_STD_COMM*/, displayOption, DBConstants.INIT_MOVE);
    }

}
