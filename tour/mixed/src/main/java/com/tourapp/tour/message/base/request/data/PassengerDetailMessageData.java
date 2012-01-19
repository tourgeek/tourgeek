/**
 * @(#)PassengerDetailMessageData.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.message.base.request.*;

/**
 *  PassengerDetailMessageData - .
 */
public class PassengerDetailMessageData extends MessageRecordDesc
{
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
            strKey = ProductRequest.PASSENGER_DETAIL;
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
        Record recBookingPax = (BookingPax)record;
        if (this.get(BookingPax.SOURCE_REFERENCE_NO) != null)
            recBookingPax.getField(BookingPax.kRemoteReferenceNo).setString(this.get(BookingPax.SOURCE_REFERENCE_NO).toString());
        FirstMLastConverter converter = new FirstMLastConverter(recBookingPax, BookingPax.kNamePrefix, BookingPax.kFirstName, BookingPax.kMiddleName, BookingPax.kSurName);
        String strFullName = (String)this.get(converter.getFieldDesc());
        converter.setString(strFullName);
        converter.free();
        this.getRawFieldData(recBookingPax.getField(BookingPax.kSmoker));
        PaxCategory recPaxCategory = (PaxCategory)((ReferenceField)recBookingPax.getField(BookingPax.kPaxCategoryID)).getReferenceRecord();
        String strParam = recPaxCategory.getField(PaxCategory.kDescription).getFieldName();
        String strPaxCategory = (String)this.get(strParam);
        String strPaxCategoryID = recPaxCategory.convertNameToID(strPaxCategory);
        if (strPaxCategoryID != null)
            recBookingPax.getField(BookingPax.kPaxCategoryID).setString(strPaxCategoryID);
        //    for (int iFieldSeq = BookingPax.kNamePrefix; iFieldSeq <= BookingPax.kSurName; iFieldSeq++)
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
        FirstMLastConverter converter = new FirstMLastConverter(recBookingPax, BookingPax.kNamePrefix, BookingPax.kFirstName, BookingPax.kMiddleName, BookingPax.kSurName);
        this.put(converter.getFieldDesc(), converter.toString());   // Full Name
        for (int iFieldSeq = BookingPax.kNamePrefix; iFieldSeq <= BookingPax.kSurName; iFieldSeq++)
        {
            this.putRawFieldData(recBookingPax.getField(iFieldSeq));
        }
        this.putRawFieldData(recBookingPax.getField(BookingPax.kSmoker));
        this.putRawFieldData(recBookingPax.getField(BookingPax.kPaxCategoryID));
        this.putRawFieldData(((ReferenceField)recBookingPax.getField(BookingPax.kPaxCategoryID)).getReference().getField(PaxCategory.kDescription));
        this.putRawFieldData(recBookingPax.getField(BookingPax.kPaxCategoryID));
        this.put(BookingPax.SOURCE_REFERENCE_NO, recBookingPax.getField(BookingPax.kID).toString());  // Reference for remote system
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
        BookingDetail recBookingDetail = (BookingDetail)record;
        Booking recBooking = recBookingDetail.getBooking(!record.getField(BookingDetail.kBookingID).isNull());
        BookingPax recBookingPax = new BookingPax(Utility.getRecordOwner(recBooking));  // Note I'm safe using this recordowner, since I'll be freeing this in a second.
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
            BookingPax recBookingPax = (BookingPax)record;
            recBookingPax.addNew();
            if (this.get(BookingPax.REMOTE_REFERENCE_NO) != null)
            {   // A remote reference is the ID of this item (I am remote)
                recBookingPax.getField(BookingPax.kID).setString(this.get(BookingPax.REMOTE_REFERENCE_NO).toString());
                recBookingPax.setKeyArea(BookingPax.kIDKey);
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
            else if (this.get(BookingPax.SOURCE_REFERENCE_NO) != null)
            {   // A Source reference is the reference of the remote pax.
                recBookingPax.close();
                if (!ADD_RECORD.equalsIgnoreCase((String)this.get(MESSAGE_RECORD_TYPE)))
                {
                    while (recBookingPax.hasNext())
                    {
                        recBookingPax.next();
                        if (this.get(BookingPax.SOURCE_REFERENCE_NO).equals(recBookingPax.getField(BookingPax.kRemoteReferenceNo).toString()))
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
