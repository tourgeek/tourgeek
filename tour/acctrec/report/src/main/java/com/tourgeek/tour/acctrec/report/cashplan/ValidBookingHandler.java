/**
  * @(#)ValidBookingHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctrec.report.cashplan;

import java.util.*;

import org.bson.Document;
import org.jbundle.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.model.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.product.base.db.*;

/**
 *  ValidBookingHandler - Only allow accepted tours through (Status = accepted, or balances non zero).
 */
public class ValidBookingHandler extends FileListener
{
    protected int m_iAcceptedType;
    /**
     * Default constructor.
     */
    public ValidBookingHandler()
    {
        super();
    }
    /**
     * ValidBookingHandler Method.
     */
    public ValidBookingHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        m_iAcceptedType = 0;
        super.init(record);
    }
    /**
     * Set up/do the local criteria.
     * @param strbFilter The SQL query string to add to.
     * @param bIncludeFileName Include the file name with this query?
     * @param vParamList The param list to add the raw data to (for prepared statements).
     * @param doc
     * @return True if you should not skip this record (does a check on the local data).
     */
    public boolean doLocalCriteria(StringBuffer strbFilter, boolean bIncludeFileName, Vector vParamList, Document doc)
    {
        if (m_iAcceptedType == 0)
        {
            BookingStatus recBookingStatus = new BookingStatus(this.getOwner().findRecordOwner());
            recBookingStatus.setKeyArea(BookingStatus.CODE_KEY);
            recBookingStatus.getField(BookingStatus.CODE).setString(BookingStatus.OKAY_CODE);
            m_iAcceptedType = -1; // In case not found
            try
            {
                boolean bSuccess = recBookingStatus.seek("=");
                if (bSuccess)
                    m_iAcceptedType = (int)recBookingStatus.getField(BookingStatus.ID).getValue();
                else
                    System.out.println("Sys error - Status not found (ValidBookingHandler)");
            } catch (DBException ex)    {
                ex.printStackTrace();
            }
            recBookingStatus.free();
            recBookingStatus = null;
        }
        if (this.getOwner().getField(Booking.BALANCE).getValue() >= this.getOwner().getField(Booking.NET).getValue())
            if (this.getOwner().getField(Booking.BOOKING_STATUS_ID).getValue() != m_iAcceptedType)
                return false; // Skip this one; not accepted and no money in yet
        return super.doLocalCriteria(strbFilter, bIncludeFileName, vParamList, doc);   // Valid booking
    }

}
