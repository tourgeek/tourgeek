/**
  * @(#)TourOrderHandler.
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
import com.tourapp.tour.booking.db.*;

/**
 *  TourOrderHandler - .
 */
public class TourOrderHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public TourOrderHandler()
    {
        super();
    }
    /**
     * TourOrderHandler Method.
     */
    public TourOrderHandler(BaseField field)
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
        if (this.getOwner().getRecord().getField(Tour.ORDER_COMPONENTS).getState() == true)
        {
            // Note: Must be finalized to order components (setting this flag will set up the A/P detail)
            Record recTour = this.getOwner().getRecord();
            int iOldOpenMode = recTour.setOpenMode(recTour.getOpenMode() & ~DBConstants.OPEN_READ_ONLY);
            recTour.getField(Tour.FINALIZED).setState(true);
            try {
                recTour.writeAndRefresh();
            } catch (DBException ex) {
                ex.printStackTrace();
            } finally {
                recTour.setOpenMode(iOldOpenMode);
            }
        
            ((Tour)this.getOwner().getRecord()).orderAllComponents();
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
