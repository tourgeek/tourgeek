/**
  * @(#)ManualVariesDefaultCost.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.product.land.event;

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
import com.tourapp.tour.product.land.db.*;

/**
 *  ManualVariesDefaultCost - Default the land cost to 1 when there is a manual varies code set.
 */
public class ManualVariesDefaultCost extends FieldListener
{
    /**
     * Default constructor.
     */
    public ManualVariesDefaultCost()
    {
        super();
    }
    /**
     * ManualVariesDefaultCost Method.
     */
    public ManualVariesDefaultCost(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Init Method.
     */
    public void init(BaseField field)
    {
        super.init(field);
        this.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        int iErrorCode = super.fieldChanged(bDisplayOption, iMoveMode);
        
        if (!this.getOwner().isNull())
        {
            Record recLandVaries = ((ReferenceField)this.getOwner()).getReference();
            if (recLandVaries != null)
            {
                String strVariesBy = recLandVaries.getField(LandVaries.VARIES_BY).toString();
                if ((VariesByField.MANUAL_FIXED.equals(strVariesBy))
                    || (VariesByField.MANUAL_PER_PERSON.equals(strVariesBy))
                    || (VariesByField.MANUAL_PER_ROOM.equals(strVariesBy)))
                {
                    Record recLandPricing = this.getOwner().getRecord();
                    if (recLandPricing.getField(LandPricing.COST).isNull())
                        recLandPricing.getField(LandPricing.COST).setValue(1);
                }
            }
        }
        
        return iErrorCode;
    }

}
