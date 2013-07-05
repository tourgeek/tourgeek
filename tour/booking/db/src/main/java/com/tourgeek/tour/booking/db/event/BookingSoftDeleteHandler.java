/**
  * @(#)BookingSoftDeleteHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.booking.db.event;

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
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.acctrec.db.*;
import com.tourgeek.tour.booking.history.db.*;
import com.tourgeek.tour.profile.db.*;
import com.tourgeek.tour.product.tour.schedule.db.*;
import com.tourgeek.tour.booking.detail.db.*;
import com.tourgeek.tour.product.tour.detail.db.*;
import com.tourgeek.tour.acctrec.db.event.*;
import com.tourgeek.model.tour.acctrec.db.*;
import com.tourgeek.model.tour.booking.detail.db.*;
import com.tourgeek.model.tour.product.tour.db.*;
import java.util.*;

/**
 *  BookingSoftDeleteHandler - Soft delete this bookingAdd code to make this work..
 */
public class BookingSoftDeleteHandler extends SoftDeleteHandler
{
    /**
     * Default constructor.
     */
    public BookingSoftDeleteHandler()
    {
        super();
    }
    /**
     * BookingSoftDeleteHandler Method.
     */
    public BookingSoftDeleteHandler(BaseField fldDeleteFlag)
    {
        this();
        this.init(fldDeleteFlag);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(record);
    }
    /**
     * Soft delete this record.
     * Set the deleted flag.
     */
    public int softDeleteThisRecord()
    {
        return DBConstants.NORMAL_RETURN; // Fix this
    }
    /**
     * Soft delete this record?
     * Override this to decide whether to soft delete or physically delete the record.
     */
    public boolean isSoftDeleteThisRecord()
    {
        return true;    // Fix this
    }

}
