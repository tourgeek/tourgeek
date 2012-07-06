/**
  * @(#)TourStatusEventHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.db.event;

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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;

/**
 *  TourStatusEventHandler - Monitor the tour status to trigger the following events:
- Services Confirmed (All ordered services okay)
- Others?.
 */
public class TourStatusEventHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public TourStatusEventHandler()
    {
        super();
    }
    /**
     * TourStatusEventHandler Method.
     */
    public TourStatusEventHandler(BaseField field)
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
        int iTourStatus = (int)this.getOwner().getValue();
        if (iTourStatus == TourStatus.OKAY)
            this.getOwner().getRecord().getField(Tour.SERV_CONF).setState(true, bDisplayOption, iMoveMode);    // Trigger the services confirmed event
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
