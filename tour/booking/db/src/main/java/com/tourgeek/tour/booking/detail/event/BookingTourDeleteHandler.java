
package com.tourgeek.tour.booking.detail.event;

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
import com.tourgeek.tour.booking.detail.db.*;

/**
 *  BookingTourDeleteHandler - Delete the tour detail on tour detail delete.
 */
public class BookingTourDeleteHandler extends FileListener
{
    /**
     * Default constructor.
     */
    public BookingTourDeleteHandler()
    {
        super();
    }
    /**
     * BookingTourDeleteHandler Method.
     */
    public BookingTourDeleteHandler(Record record)
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
        int iErrorCode = DBConstants.NORMAL_RETURN;
        if (iChangeType == DBConstants.AFTER_DELETE_TYPE)
        {   // Note: Soft delete is handled in messaging
            BookingDetail recBookingDetail = (BookingDetail)this.getOwner();
            iErrorCode = recBookingDetail.setupDirectDetail(iChangeType);
        }
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        return super.doRecordChange(field, iChangeType, bDisplayOption);
    }

}
