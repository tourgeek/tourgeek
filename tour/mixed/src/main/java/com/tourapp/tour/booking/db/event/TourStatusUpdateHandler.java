/**
 * @(#)TourStatusUpdateHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import com.tourapp.tour.booking.entry.detail.base.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;

/**
 *  TourStatusUpdateHandler - Add capablity to display the source of the tour status in the display box..
 */
public class TourStatusUpdateHandler extends BaseStatusUpdateHandler
{
    /**
     * Default constructor.
     */
    public TourStatusUpdateHandler()
    {
        super();
    }
    /**
     * TourStatusUpdateHandler Method.
     */
    public TourStatusUpdateHandler(BaseField field)
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
    }
    /**
     * Get the message to display in the message box.
     */
    public String getDisplayMessage()
    {
        String strMessage = null;
        
        Record recBaseStatus = this.getOwner().getRecord();
        BaseField fldStatusID = recBaseStatus.getReferringField();
        Record recTour = fldStatusID.getRecord(); 
        TourStatusSummaryField fldTourStatusSummary = (TourStatusSummaryField)recTour.getField(Tour.TOUR_STATUS_SUMMARY);
        String strSource = null;
        String strKey = fldTourStatusSummary.getHighStatusKey();
        if (strKey != null)
        {
            int iDot = strKey.indexOf('.');
            if (iDot != 0)
            {
                String strType = strKey.substring(0, iDot);
                String strDetailID = strKey.substring(iDot+1);
                BookingDetail recBookingDetail = new BookingDetail(recTour.findRecordOwner());
                try {
                    recBookingDetail.getField(BookingDetail.ID).setString(strDetailID);
                    if (recBookingDetail.seek(null))
                    {
                        String strErrorKey = recBookingDetail.getFieldParam(recBookingDetail.getField(strType));
                        strErrorKey = strErrorKey + '.' + BookingDetail.MESSAGE_PARAM + '.' + BookingDetail.ERROR_PARAM;
                        strMessage = ((PropertiesField)recBookingDetail.getField(BookingDetail.ERROR_PROPERTIES)).getProperty(strErrorKey);
                        strSource = recBookingDetail.getField(BookingDetail.PRODUCT_TYPE) + " - " + recBookingDetail.getField(BookingDetail.DESCRIPTION);
                    }
                } catch (DBException ex) {
                    ex.printStackTrace();
                } finally {
                    recBookingDetail.free();
                }
            }
        }
        if (strMessage != null)
            strMessage = strMessage + " (" + strSource + ")";
        else
            strMessage = this.getOwner().getRecord().getField(TourStatus.DESCRIPTION).toString();
        if (strMessage == null)
            strMessage = super.getDisplayMessage();
        return strMessage;
    }

}
