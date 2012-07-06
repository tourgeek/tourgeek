/**
  * @(#)TourHeaderSelectHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.entry.tour;

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
import com.tourapp.tour.product.tour.db.*;

/**
 *  TourHeaderSelectHandler - On selection, fake tour cd chg.
 */
public class TourHeaderSelectHandler extends FileListener
{
    /**
     * Default constructor.
     */
    public TourHeaderSelectHandler()
    {
        super();
    }
    /**
     * TourHeaderSelectHandler Method.
     */
    public TourHeaderSelectHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(record);
    }
    /**
     * Called when a change is the record status is about to happen/has happened.
     * @param field If this file change is due to a field, this is the field.
     * @param iChangeType The type of change that occurred.
     * @param bDisplayOption If true, display any changes.
     * @return an error code.
     * ADD_TYPE - Before a write.
     * UPDATE_TYPE - Before an update.
     * DELETE_TYPE - Before a delete.
     * AFTER_UPDATE_TYPE - After a write or update.
     * LOCK_TYPE - Before a lock.
     * SELECT_TYPE - After a select.
     * DESELECT_TYPE - After a deselect.
     * MOVE_NEXT_TYPE - After a move.
     * AFTER_REQUERY_TYPE - Record opened.
     * SELECT_EOF_TYPE - EOF Hit.
     */
    public int doRecordChange(FieldInfo field, int iChangeType, boolean bDisplayOption)
    {
        int errorCode = super.doRecordChange(field, iChangeType, bDisplayOption);
        if (errorCode != DBConstants.NORMAL_RETURN)
            return errorCode;
        if (iChangeType == DBConstants.SELECT_TYPE)
        {   // Process the "Change field listener!)
            errorCode = this.getOwner().getField(TourHeader.ID).handleFieldChanged(bDisplayOption, DBConstants.SCREEN_MOVE);
        }
        return errorCode;
    }

}
