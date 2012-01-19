/**
 * @(#)BaseStatusUpdateHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.detail.base;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.main.properties.db.*;
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.main.db.base.*;

/**
 *  BaseStatusUpdateHandler - .
 */
public class BaseStatusUpdateHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public BaseStatusUpdateHandler()
    {
        super();
    }
    /**
     * BaseStatusUpdateHandler Method.
     */
    public BaseStatusUpdateHandler(BaseField field)
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
     * DoSetData Method.
     */
    public int doSetData(Object objData, boolean bDisplayOption, int iMoveMode)
    {
        if (objData instanceof String)
        { // The user pressed the button.
            Record recBaseStatus = this.getOwner().getRecord();
            BaseField fldStatusID = recBaseStatus.getReferringField();
            Record recBookingDetail = fldStatusID.getRecord(); 
            Task task = null;
            if (recBookingDetail.getRecordOwner() != null)
                task = recBookingDetail.getRecordOwner().getTask();
            else if (recBaseStatus.getRecordOwner() != null)
                task = recBaseStatus.getRecordOwner().getTask();
            String strMessage = this.getDisplayMessage();
            if (task != null)
                task.setStatusText(strMessage);
            return DBConstants.NORMAL_RETURN;
        }
        return super.doSetData(objData, bDisplayOption, iMoveMode);
    }
    /**
     * Get the message to display in the message box.
     */
    public String getDisplayMessage()
    {
        String strMessage = this.getOwner().getRecord().getField(BaseStatus.kDescription).toString();
        return strMessage;
    }

}
