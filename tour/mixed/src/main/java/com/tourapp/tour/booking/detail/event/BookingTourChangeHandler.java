/**
 *  @(#)BookingTourChangeHandler.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.detail.event;

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
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  BookingTourChangeHandler - .
 */
public class BookingTourChangeHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public BookingTourChangeHandler()
    {
        super();
    }
    /**
     * BookingTourChangeHandler Method.
     */
    public BookingTourChangeHandler(BaseField field)
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
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        if ((this.getOwner().getValue() == BaseDataStatus.OKAY) || (this.getOwner().getValue() == BaseDataStatus.CANCELED))
        {
            BookingDetail recBookingDetail = (BookingDetail)this.getOwner().getRecord();
            iErrorCode = recBookingDetail.setupDirectDetail(DBConstants.FIELD_CHANGED_TYPE);
        }
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
