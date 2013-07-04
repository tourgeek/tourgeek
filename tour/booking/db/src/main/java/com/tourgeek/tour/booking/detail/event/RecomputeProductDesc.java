/**
  * @(#)RecomputeProductDesc.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.detail.event;

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
import com.tourapp.tour.booking.detail.db.*;

/**
 *  RecomputeProductDesc - Recompute and set the product description..
 */
public class RecomputeProductDesc extends FieldListener
{
    /**
     * Default constructor.
     */
    public RecomputeProductDesc()
    {
        super();
    }
    /**
     * RecomputeProductDesc Method.
     */
    public RecomputeProductDesc(Object object)
    {
        this();
        this.init(object);
    }
    /**
     * Initialize class fields.
     */
    public void init(Object object)
    {
        super.init(null);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        int iErrorCode = super.fieldChanged(bDisplayOption, iMoveMode);
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        String strDesc = ((BookingDetail)this.getOwner().getRecord()).setupProductDesc();
        iErrorCode = this.getOwner().getRecord().getField(BookingDetail.DESCRIPTION).setString(strDesc);
        return iErrorCode;
    }

}
