/**
  * @(#)PassengerDetailMessageData.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.message.base.request.data;

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
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.message.base.request.*;
import org.jbundle.model.message.*;
import com.tourapp.model.tour.booking.db.*;
import com.tourapp.model.tour.booking.detail.db.*;
import com.tourapp.model.tour.product.base.db.*;

/**
 *  PassengerDetailMessageData - .
 */
public class PassengerDetailMessageData extends MessageRecordDesc
{
    public static final String PASSENGER_DETAIL = "paxDetail";
    /**
     * Default constructor.
     */
    public PassengerDetailMessageData()
    {
        super();
    }
    /**
     * PassengerDetailMessageData Method.
     */
    public PassengerDetailMessageData(MessageDataParent messageDataParent, String strKey)
    {
        this();
        this.init(messageDataParent, strKey);
    }
    /**
     * Initialize class fields.
     */
    public void init(MessageDataParent messageDataParent, String strKey)
    {
        if (strKey == null)
            strKey = PassengerDetailMessageData.PASSENGER_DETAIL;
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
        Record recBookingPax = (Record)record;
        if (this.get(BookingPaxModel.SOURCE_REFERENCE_NO) != null)
            recBookingPax.getField(BookingPaxModel.REMOTE_REFERENCE_NO).setString(this.get(BookingPaxModel.SOURCE_REFERENCE_NO).toString());
        FirstMLastConverter converter = new FirstMLastConverter(recBookingPax, BookingPaxModel.NAME_PREFIX, BookingPaxModel.FIRST_NAME, BookingPaxModel.MIDDLE_NAME, BookingPaxModel.SUR_NAME);
        String strFullName = (String)this.get(converter.getFieldDesc());
        converter.setString(strFullName);
        converter.free();
        this.getRawFieldData(recBookingPax.getField(BookingPaxModel.SMOKER));
        PaxCategoryModel recPaxCategory = (PaxCategoryModel)((ReferenceField)recBookingPax.getField(BookingPaxModel.PAX_CATEGORY_ID)).getReferenceRecord();
        String strParam = recPaxCategory.getField(PaxCategoryModel.DESCRIPTION).getFieldName();
        String strPaxCategory = (String)this.get(strParam);
        String strPaxCategoryID = recPaxCategory.convertNameToID(strPaxCategory);
        if (strPaxCategoryID != null)
            recBookingPax.getField(BookingPaxModel.PAX_CATEGORY_ID).setString(strPaxCategoryID);
        //    for (int iFieldSeq = BookingPaxModel.NAME_PREFIX; iFieldSeq <= BookingPaxModel.SUR_NAME; iFieldSeq++)
        {
        //        this.getRawFieldData(cat.getFieldInfo(iFieldSeq));
        }
        return iErrorCode;
    }
    /**
     * Move the correct fields from this record to the map.
     * If this method is used, is must be overidden to move the correct fields.
     * @param record The record to get the data from.
     */
    public int putRawRecordData(Rec record)
    {
        int iErrorCode = super.putRawRecordData(record);
        
        Record recBookingPax =  (Record)record;
        FirstMLastConverter converter = new FirstMLastConverter(recBookingPax, BookingPaxModel.NAME_PREFIX, BookingPaxModel.FIRST_NAME, BookingPaxModel.MIDDLE_NAME, BookingPaxModel.SUR_NAME);
        this.put(converter.getFieldDesc(), converter.toString());   // Full Name
        for (int iFieldSeq = recBookingPax.getFieldSeq(BookingPaxModel.NAME_PREFIX); iFieldSeq <= recBookingPax.getFieldSeq(BookingPaxModel.SUR_NAME); iFieldSeq++)
        {
            this.putRawFieldData(recBookingPax.getField(iFieldSeq));
        }
        this.putRawFieldData(recBookingPax.getField(BookingPaxModel.SMOKER));
        this.putRawFieldData(recBookingPax.getField(BookingPaxModel.PAX_CATEGORY_ID));
        this.putRawFieldData(((ReferenceField)recBookingPax.getField(BookingPaxModel.PAX_CATEGORY_ID)).getReference().getField(PaxCategoryModel.DESCRIPTION));
        this.putRawFieldData(recBookingPax.getField(BookingPaxModel.PAX_CATEGORY_ID));
        this.put(BookingPaxModel.SOURCE_REFERENCE_NO, recBookingPax.getField(BookingPaxModel.ID).toString());  // Reference for remote system
        converter.free();
        
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
        BookingDetailModel recBookingDetail = (BookingDetailModel)record;
        Record recBooking = (Record)recBookingDetail.getBooking(!record.getField(BookingDetailModel.BOOKING_ID).isNull());
        Record recBookingPax = Record.makeRecordFromClassName(BookingPaxModel.THICK_CLASS, recBooking.findRecordOwner());  // Note I'm safe using this recordowner, since I'll be freeing this in a second.
        recBookingPax.addListener(new SubFileFilter(recBooking));
        return recBookingPax;
    }
    /**
     * Read the record described at the current data location.
     * @param record The record to read from.
     * @return null if error, otherwise return the record.
     */
    public Rec readCurrentRecord(Rec record)
    {
        try {
            Record recBookingPax = (Record)record;
            recBookingPax.addNew();
            if (this.get(BookingPaxModel.REMOTE_REFERENCE_NO) != null)
            {   // A remote reference is the ID of this item (I am remote)
                recBookingPax.getField(BookingPaxModel.ID).setString(this.get(BookingPaxModel.REMOTE_REFERENCE_NO).toString());
                recBookingPax.setKeyArea(BookingPaxModel.ID_KEY);
                if (recBookingPax.seek(null))
                { // Good
                    recBookingPax.edit();
                }
                else
                {
                    if (record != null)
                        if (record.getTask() != null)
                            record.getTask().setLastError("Remote reference not found");
                    return null;
                }
            }
            else if (this.get(BookingPaxModel.SOURCE_REFERENCE_NO) != null)
            {   // A Source reference is the reference of the remote pax.
                recBookingPax.close();
                if (!ADD_RECORD.equalsIgnoreCase((String)this.get(MESSAGE_RECORD_TYPE)))
                {
                    while (recBookingPax.hasNext())
                    {
                        recBookingPax.next();
                        if (this.get(BookingPaxModel.SOURCE_REFERENCE_NO).equals(recBookingPax.getField(BookingPaxModel.REMOTE_REFERENCE_NO).toString()))
                        {
                            recBookingPax.edit();
                            break;
                        }
                    }
                }
                if (recBookingPax.getEditMode() != DBConstants.EDIT_IN_PROGRESS)
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
                        recBookingPax.addNew();
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
