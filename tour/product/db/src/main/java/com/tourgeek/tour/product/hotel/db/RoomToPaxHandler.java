
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
 *  RoomToPaxHandler - Convert this room amount to the pax amount.
 */
public class RoomToPaxHandler extends PaxToRoomHandler
{
    /**
     * Default constructor.
     */
    public RoomToPaxHandler()
    {
        super();
    }
    /**
     * Constructor.
     * @param iTargetFieldSeq The target field sequence to recompute on field change.
     */
    public RoomToPaxHandler(String iTargetFieldSeq)
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
    }
    /**
     * Compute the target value.
     * @param srcValue The value of this listener's owner.
     * @return The value to set the target field to.
     */
    public double computeValue(double dSrcValue)
    {
        return Math.floor(dSrcValue / this.getPaxInRoom() * 100 + 0.5) / 100;
    }

}
