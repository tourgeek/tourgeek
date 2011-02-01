/**
 *  @(#)UpdateTourStatusSummaryHandler.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.db.*;

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
        if (recTour.getField(Tour.kManualTourStatus).getState() == false)
        {
            int iStatus = BaseDataStatus.NO_STATUS;
            String strKey = ((TourStatusSummaryField)this.getOwner()).getHighStatusKey();
            if (strKey != null)
            {
                String strValue = ((TourStatusSummaryField)this.getOwner()).getProperty(strKey);
                iStatus = ((TourStatusSummaryField)this.getOwner()).getStatusFromProperty(strValue);
            }
            if (iStatus == BaseDataStatus.OKAY)
                if (this.getOwner().getRecord().getField(Tour.kOrderComponents).getState() == false)
                    iStatus = BaseDataStatus.DATA_VALID;
            if (this.getOwner().getRecord().getField(Tour.kCancelled).getState() == true)
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
            recTour.getField(Tour.kTourStatusID).setValue(iStatus,  bDisplayOption, iMoveMode);
            if (iMoveMode == DBConstants.INIT_MOVE)
                recTour.getField(Tour.kTourStatusID).setModified(false);
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
