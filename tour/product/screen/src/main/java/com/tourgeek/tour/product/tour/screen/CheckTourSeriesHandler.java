/**
  * @(#)CheckTourSeriesHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.product.tour.screen;

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
import com.tourapp.tour.product.tour.db.*;

/**
 *  CheckTourSeriesHandler - .
 */
public class CheckTourSeriesHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public CheckTourSeriesHandler()
    {
        super();
    }
    /**
     * CheckTourSeriesHandler Method.
     */
    public CheckTourSeriesHandler(BaseField field)
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
        BaseField fldTourTypeID = this.getOwner();
        if (!fldTourTypeID.isNull())
        {
            if ((TourType.SERIES & (int)this.getOwner().getValue()) != 0)
                this.getOwner().getRecord().getField(TourHeader.TOUR_SERIES).setState(true);
            else
                this.getOwner().getRecord().getField(TourHeader.TOUR_SERIES).setState(false);
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
