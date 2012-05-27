/**
 * @(#)CheckRequestRequiredHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.main.db.base.*;

/**
 *  CheckRequestRequiredHandler - .
 */
public class CheckRequestRequiredHandler extends FieldListener
{
    protected String fieldTypeToCheck;
    /**
     * Default constructor.
     */
    public CheckRequestRequiredHandler()
    {
        super();
    }
    /**
     * CheckRequestRequiredHandler Method.
     */
    public CheckRequestRequiredHandler(String fieldTypeToCheck)
    {
        this();
        this.init(fieldTypeToCheck);
    }
    /**
     * Initialize class fields.
     */
    public void init(String fieldTypeToCheck)
    {
        fieldTypeToCheck = "";
        super.init(null);
        this.fieldTypeToCheck = fieldTypeToCheck;
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        if (iMoveMode == DBConstants.SCREEN_MOVE)
            if (this.getOwner().getState() == true)
        { // Call on screen moves only
            BookingDetail recBookingDetail = (BookingDetail)this.getOwner().getRecord();
            recBookingDetail.checkRequestRequired(fieldTypeToCheck);
            recBookingDetail.getField(recBookingDetail.getFieldSeq(fieldTypeToCheck) + BookingHotel.MESSAGE_REQUEST_OFFSET).setState(false);    // Reset flag
        }
        return DBConstants.NORMAL_RETURN;
    }

}
