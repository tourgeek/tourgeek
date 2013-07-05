/**
  * @(#)UpdateTourStatusSummaryHandler.
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
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.booking.db.*;

/**
 *  UpdateTourStatusSummaryHandler - Update the TourStatusID when the Tour Status Summary Changes.
 */
public class UpdateTourStatusSummaryHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public UpdateTourStatusSummaryHandler()
    {
        super();
    }
    /**
     * UpdateTourStatusSummaryHandler Method.
     */
    public UpdateTourStatusSummaryHandler(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        super.init(field);
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        Record recTour = this.getOwner().getRecord();
        if (recTour.getField(Tour.MANUAL_TOUR_STATUS).getState() == false)
        {
            int iStatus = BaseDataStatus.NO_STATUS;
            String strKey = ((TourStatusSummaryField)this.getOwner()).getHighStatusKey();
            if (strKey != null)
            {
                String strValue = ((TourStatusSummaryField)this.getOwner()).getProperty(strKey);
                iStatus = ((TourStatusSummaryField)this.getOwner()).getStatusFromProperty(strValue);
            }
            if (iStatus == BaseDataStatus.OKAY)
                if (this.getOwner().getRecord().getField(Tour.ORDER_COMPONENTS).getState() == false)
                    iStatus = BaseDataStatus.DATA_VALID;
            if (this.getOwner().getRecord().getField(Tour.CANCELLED).getState() == true)
            {
                TourStatusSummaryField field = (TourStatusSummaryField)this.getOwner();
                Map<String,Object> map = field.getProperties();
                boolean bAllCancelled = true;
                for (String key : map.keySet())
                {
                    if ("ProductStatusID".equalsIgnoreCase(field.getStatusFromKey(key)))
                    {
                        String strValue = (String)map.get(key);
                        if (field.getStatusFromProperty(strValue) != BaseDataStatus.CANCELED)
                            bAllCancelled = false;
                    }
                }
                if (bAllCancelled)
                    iStatus = BaseDataStatus.CANCELED;
            }
            recTour.getField(Tour.TOUR_STATUS_ID).setValue(iStatus,  bDisplayOption, iMoveMode);
            if (iMoveMode == DBConstants.INIT_MOVE)
                recTour.getField(Tour.TOUR_STATUS_ID).setModified(false);
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
