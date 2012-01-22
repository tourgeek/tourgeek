/**
 * @(#)GetDepartureDateHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.db.event;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.booking.db.*;

/**
 *  GetDepartureDateHandler - .
 */
public class GetDepartureDateHandler extends FieldListener
{
    protected TourField m_fldTourID = null;
    /**
     * Default constructor.
     */
    public GetDepartureDateHandler()
    {
        super();
    }
    /**
     * GetDepartureDateHandler Method.
     */
    public GetDepartureDateHandler(TourField fldTourID)
    {
        this();
        this.init(fldTourID);
    }
    /**
     * Initialize class fields.
     */
    public void init(TourField fldTourID)
    {
        m_fldTourID = null;
        super.init(null);
        m_fldTourID = fldTourID;
    }
    /**
     * Get the raw data from this behavior's owner's field.
     */
    public Object doGetData()
    {
        Object data = super.doGetData();
        if (m_fldTourID != null)
        {
            Record recTour = m_fldTourID.getReferenceRecord();
            if ((this.getOwner().getRecord().getDefaultOrder() == ApTrx.kTourIDKey)
                && ((this.getOwner().getRecord().getListener(SubFileFilter.class) != null))
                    && (recTour != null)
                        && (!m_fldTourID.equals(recTour.getCounterField())))
                            recTour = null;
                        else
                            m_fldTourID.getReference();
            if (recTour != null)
                if ((recTour.getEditMode() == DBConstants.EDIT_CURRENT) || (recTour.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                    data = recTour.getField(Tour.kDepartureDate).getData();
        }
        return data;
    }

}
