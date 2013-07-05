/**
  * @(#)PaxToRoomHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.hotel.db;

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
import com.tourgeek.tour.product.base.db.*;

/**
 *  PaxToRoomHandler - Convert this amount to the room amount and move to the room field.
 */
public class PaxToRoomHandler extends ReComputeFieldHandler
{
    /**
     * Default constructor.
     */
    public PaxToRoomHandler()
    {
        super();
    }
    /**
     * Constructor.
     * @param iTargetFieldSeq The target field sequence to recompute on field change.
     */
    public PaxToRoomHandler(String iTargetFieldSeq)
    {
        this();
        this.init(iTargetFieldSeq);
    }
    /**
     * Initialize class fields.
     */
    public void init(String iTargetFieldSeq)
    {
        super.init(null, iTargetFieldSeq, null);
        this.setDisableTarget(true);    // Eliminate the echo back
    }
    /**
     * Compute the target value.
     * @param srcValue The value of this listener's owner.
     * @return The value to set the target field to.
     */
    public double computeValue(double dSrcValue)
    {
        return Math.floor(dSrcValue * this.getPaxInRoom() * 100 + 0.5) / 100;
    }
    /**
     * Get the number of passengers in this hotel pricing category.
     */
    public int getPaxInRoom()
    {
        Record recHotelPricing = ((BaseField)this.getOwner()).getRecord();
        PaxCategory recPaxCategory = (PaxCategory)((ReferenceField)recHotelPricing.getField(HotelPricing.PAX_CATEGORY_ID)).getReferenceRecord();
        if (recPaxCategory != null)
            return recPaxCategory.getPaxInRoom();
        return 1;
    }

}
