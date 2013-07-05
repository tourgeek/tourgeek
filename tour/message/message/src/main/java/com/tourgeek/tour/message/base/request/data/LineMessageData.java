/**
  * @(#)LineMessageData.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.message.base.request.data;

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
import org.jbundle.thin.base.message.*;
import org.jbundle.model.message.*;
import com.tourgeek.model.tour.booking.detail.db.*;
import com.tourgeek.model.tour.booking.db.*;

/**
 *  LineMessageData - .
 */
public class LineMessageData extends MessageRecordDesc
{
    /**
     * Default constructor.
     */
    public LineMessageData()
    {
        super();
    }
    /**
     * LineMessageData Method.
     */
    public LineMessageData(MessageDataParent messageDataParent, String strKey)
    {
        this();
        this.init(messageDataParent, strKey);
    }
    /**
     * Initialize class fields.
     */
    public void init(MessageDataParent messageDataParent, String strKey)
    {
        super.init(messageDataParent, strKey);
        this.setNodeType(NON_UNIQUE_NODE);
    }
    /**
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(Rec record)
    {
        int iErrorCode = super.getRawRecordData(record);
        Record recBookingLine = (Record)record;
        this.getRawFieldData(recBookingLine.getField(BookingLineModel.DESCRIPTION));
        this.getRawFieldData(recBookingLine.getField(BookingLineModel.PRICE));
        this.getRawFieldData(recBookingLine.getField(BookingLineModel.QUANTITY));
        this.getRawFieldData(recBookingLine.getField(BookingLineModel.GROSS));
        this.getRawFieldData(recBookingLine.getField(BookingLineModel.COMMISSION));
        this.getRawFieldData(recBookingLine.getField(BookingLineModel.NET));
        if (this.get(BookingLineModel.SOURCE_REFERENCE_NO) != null)
            recBookingLine.getField(BookingLineModel.REMOTE_REFERENCE_NO).setString(this.get(BookingLineModel.SOURCE_REFERENCE_NO).toString());
        return iErrorCode;
    }
    /**
     * Move the correct fields from this record to the map.
     * If this method is used, is must be overidden to move the correct fields.
     * @param record The record to get the data from.
     */
    public int putRawRecordData(Rec record)
    {
        int iErrorCode = super.putRawRecordData(record); // Create new node
        Record recBookingLine = (Record)record;
        this.putRawFieldData(recBookingLine.getField(BookingLineModel.DESCRIPTION));
        this.putRawFieldData(recBookingLine.getField(BookingLineModel.PRICE));
        this.putRawFieldData(recBookingLine.getField(BookingLineModel.QUANTITY));
        this.putRawFieldData(recBookingLine.getField(BookingLineModel.GROSS));
        this.putRawFieldData(recBookingLine.getField(BookingLineModel.COMMISSION));
        this.putRawFieldData(recBookingLine.getField(BookingLineModel.NET));
        this.put(BookingLineModel.SOURCE_REFERENCE_NO, recBookingLine.getField(BookingLineModel.ID).toString());  // Reference for remote system
        return iErrorCode;
    }
    /**
     * Does this message only include a single booking detail item?.
     */
    public boolean isSingleDetail(Rec record)
    {
        return false; // Force read thru
    }
    /**
     * Create the sub-detail record.
     * @param record The base record
     * @return The new sub-record (or the base record if there is no new sub-record).
     */
    public Rec createSubDataRecord(Rec record)
    {
        Record recBooking = (Record)((BookingDetailModel)record).getBooking(true);
        Record recBookingLine = Record.makeRecordFromClassName(BookingLineModel.THICK_CLASS, recBooking.findRecordOwner());  // Note I'm safe using this recordowner, since I'll be freeing this in a second.
        recBookingLine.addListener(new SubFileFilter(recBooking));
        return recBookingLine;
    }
    /**
     * Read the record described at the current data location.
     * @param record The record to read from.
     * @return null if error, otherwise return the record.
     */
    public Rec readCurrentRecord(Rec record)
    {
        try {
            Record recBookingLine = (Record)record;
            if (this.get(BookingLineModel.REMOTE_REFERENCE_NO) != null)
            {   // A remote reference is the ID of this item (I am remote)
                recBookingLine.getField(BookingLineModel.ID).setString(this.get(BookingLineModel.REMOTE_REFERENCE_NO).toString());
                recBookingLine.setKeyArea(BookingLineModel.ID_KEY);
                if (recBookingLine.seek(null))
                { // Good
                    recBookingLine.edit();
                }
                else
                {
                    if (record != null)
                        if (record.getTask() != null)
                            record.getTask().setLastError("Remote reference not found");
                    return null;
                }
            }
            else if (this.get(BookingLineModel.SOURCE_REFERENCE_NO) != null)
            {   // A Source reference is the reference of the remote pax.
                recBookingLine.close();
                if (!ADD_RECORD.equalsIgnoreCase((String)this.get(MESSAGE_RECORD_TYPE)))
                {
                    while (recBookingLine.hasNext())
                    {
                        recBookingLine.next();
                        if (this.get(BookingLineModel.SOURCE_REFERENCE_NO).equals(recBookingLine.getField(BookingLineModel.REMOTE_REFERENCE_NO).toString()))
                        {
                            recBookingLine.edit();
                            break;
                        }
                    }
                }
                if (recBookingLine.getEditMode() != DBConstants.EDIT_IN_PROGRESS)
                {
                    if ((CHANGE_RECORD.equalsIgnoreCase((String)this.get(MESSAGE_RECORD_TYPE)))
                            || (DELETE_RECORD.equalsIgnoreCase((String)this.get(MESSAGE_RECORD_TYPE))))
                    {
                        if (record != null)
                            if (record.getTask() != null)
                                record.getTask().setLastError("Remote reference not found");
                        return null;
                    }
                    else
                    {
                        recBookingLine.addNew();
                    }
                }
            }
        } catch (DBException ex) {
            ex.printStackTrace();
            record = null;
        }
        return record;
    }

}
