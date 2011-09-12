/**
 * @(#)BaseProductMessageData.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.base;

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
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.message.base.request.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.model.db.*;

/**
 *  BaseProductMessageData - .
 */
public class BaseProductMessageData extends MessageRecordDesc
{
    /**
     * Default constructor.
     */
    public BaseProductMessageData()
    {
        super();
    }
    /**
     * BaseProductMessageData Method.
     */
    public BaseProductMessageData(MessageDataParent messageDataParent, String strKey)
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
    }
    /**
     * Does this message only include a single booking detail item?.
     */
    public boolean isSingleDetail(Rec record)
    {
        boolean bSingleDetail = super.isSingleDetail(record);
        if (bSingleDetail == false)   // Default is true
            return bSingleDetail;
        ProductRequest productRequest = (ProductRequest)this.getMessageDataParent();
        String requestType = productRequest.getRequestType();
        if ((RequestType.BOOKING_CANCEL.equalsIgnoreCase(requestType))
            || (RequestType.BOOKING_ADD.equalsIgnoreCase(requestType))
            || (RequestType.BOOKING_CHANGE.equalsIgnoreCase(requestType)))  // For now - Add rate and avail later (probably set up a finalization est)
                if (!record.getField(BookingDetail.kApTrxID).isNull())
                    bSingleDetail = false;  // Multiple detail
        return bSingleDetail;
    }
    /**
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(Rec record)
    {
        int iErrorCode = super.getRawRecordData(record);
        Record recBookingDetail = (BookingDetail)record;
        if (this.get(BookingDetail.SOURCE_REFERENCE_NO) != null)
            recBookingDetail.getField(BookingDetail.kRemoteReferenceNo).setString(this.get(BookingDetail.SOURCE_REFERENCE_NO).toString());
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
        
        Record recBookingDetail =  (Record)record;
        this.put(BookingDetail.SOURCE_REFERENCE_NO, recBookingDetail.getField(BookingDetail.kID).toString());  // Reference for remote system
        
        return iErrorCode;
    }
    /**
     * Create the sub-detail record.
     * @param record The base record
     * @return The new sub-record (or the base record if there is no new sub-record).
     */
    public Rec createSubDataRecord(Rec record)
    {
        BookingDetail recBookingDetail = new BookingDetail(Utility.getRecordOwner((Record)record));  // Note I'm safe using this recordowner, since I'll be freeing this in a second.
        String strApTrxID = record.getField(BookingDetail.kApTrxID).getString();
        
        recBookingDetail.setKeyArea(BookingDetail.kApTrxIDKey);
        recBookingDetail.addListener(new StringSubFileFilter(strApTrxID, BookingDetail.kApTrxID, null, -1, null, -1));
        return recBookingDetail;
    }
    /**
     * Create the sub-detail record.
     * @param record The base record
     * @return The new sub-record (or the base record if there is no new sub-record).
     */
    public Rec createSubNodeRecord(Rec record)
    {
        BookingDetail recBookingDetail = (BookingDetail)record;
        if (recBookingDetail.getListener(SubFileFilter.class) == null)
        {
            Booking recBooking = recBookingDetail.getBooking(false);    // Booking should already be current
            Tour recTour = (Tour)((ReferenceField)recBooking.getField(Booking.kTourID)).getReferenceRecord();
            recBookingDetail.addDetailBehaviors(recBooking, recTour);
        }
        return recBookingDetail;
    }
    /**
     * Read the record described at the current data location.
     * @param record The record to read from.
     * @return null if error, otherwise return the record.
     */
    public Rec readCurrentRecord(Rec record)
    {
        try {
            BookingDetail recBookingDetail = (BookingDetail)record;
            if (this.get(BookingDetail.REMOTE_REFERENCE_NO) != null)
            {   // A remote reference is the ID of this item (I am remote)
                recBookingDetail.getField(BookingDetail.kID).setString(this.get(BookingDetail.REMOTE_REFERENCE_NO).toString());
                recBookingDetail.setKeyArea(BookingDetail.kIDKey);
                if (recBookingDetail.seek(null))
                { // Good
                    recBookingDetail.edit();
                }
                else
                {
                    if (record != null)
                        if (record.getTask() != null)
                            record.getTask().setLastError("Remote reference not found");
                    return null;
                }
            }
            else if (this.get(BookingDetail.SOURCE_REFERENCE_NO) != null)
            {   // A Source reference is the reference of the remote pax.
                recBookingDetail.close();
                if (!ADD_RECORD.equalsIgnoreCase((String)this.get(MESSAGE_RECORD_TYPE)))
                {
                    while (recBookingDetail.hasNext())
                    {
                        recBookingDetail.next();
                        if (this.get(BookingDetail.SOURCE_REFERENCE_NO).equals(recBookingDetail.getField(BookingDetail.kRemoteReferenceNo).toString()))
                        {
                            recBookingDetail.edit();
                            break;
                        }
                    }
                }
                if (recBookingDetail.getEditMode() != DBConstants.EDIT_IN_PROGRESS)
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
                        recBookingDetail.addNew();
                    }
                }
            }
            if (recBookingDetail.getEditMode() == DBConstants.EDIT_ADD)
            {
                Booking recBooking = recBookingDetail.getBooking(false);    // Booking should already be current
                Tour recTour = (Tour)((ReferenceField)recBooking.getField(Booking.kTourID)).getReferenceRecord();
                recBookingDetail.initBookingDetailFields(recBooking, recTour, false);
            }
            return recBookingDetail;
        } catch (DBException ex) {
            ex.printStackTrace();
            if (record != null)
                if (record.getTask() != null)
                    record.getTask().setLastError(ex.getMessage());
            return null;
        }
    }
    /**
     * Free the sub-detail record.
     * @param record The record to free
     * @return True if successful.
     */
    public boolean freeSubNodeRecord(Rec record)
    {
        m_recTargetFieldList = null;
        // Do NOT call inherited (Do NOT free the sub node record!)
        return true;
    }
    /**
     * Am I using the current record as the data record?
     * @return true if I am.
     */
    public boolean isCurrentNodeRecord(Rec record)
    {
        return false;    // Even though I use the current record, I want the record to be updated/written like a separate record
    }
    /**
     * Get/Create the product record.
     * @param bFindFirst If true, try to lookup the record first.
     * @return The product record.
     */
    public Product getProductRecord(RecordOwner recordOwner, boolean bFindFirst)
    {
        return null;    // Override!
    }
    /**
     * Get the product from the product's operator code and chain code.
     */
    public String getProductID(RecordOwner recordOwner, String strChainCode, String strProductCode)
    {
        Product recProduct = this.getProductRecord(recordOwner, true);
        recProduct.setKeyArea(Product.kOperatorsCodeKey);
        recProduct.getField(Product.kOperatorsCode).setString(strProductCode);
        String strProductID = null;
        try {
            while (recProduct.seek(">="))
            {
                if (!strProductCode.equalsIgnoreCase(recProduct.getField(Product.kOperatorsCode).toString()))
                    break;
                if ((strChainCode != null) && (strChainCode.length() > 0))
                {
                    Record recProductChain = ((ReferenceField)recProduct.getField(Product.kProductChainID)).getReference();
                    if (recProductChain != null)
                        if ((recProductChain.getEditMode() == DBConstants.EDIT_CURRENT) || (recProductChain.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                    {
                        if (strChainCode.equalsIgnoreCase(recProductChain.getField(ProductChain.kCode).toString()))
                            return recProduct.getField(Product.kIDKey).toString();  // Found!
                    }
                }
                strProductID = recProduct.getField(Product.kIDKey).toString();  // Probably it.
            }
            if (strProductID != null)
                return strProductID;
            recProduct.setKeyArea(Product.kCodeKey);
            recProduct.getField(Product.kCode).setString(strProductCode);
            if (recProduct.seek(DBConstants.EQUALS))
                return recProduct.getField(Product.kIDKey).toString();  // Found!
            recProduct.setKeyArea(Product.kDescSortKey);
            recProduct.getField(Product.kDescription).setString(strProductCode);
            if (recProduct.seek(DBConstants.EQUALS))
                return recProduct.getField(Product.kIDKey).toString();  // Found!
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;    // Not found
    }
    /**
     * Using the productID in this message, read the product record.
     */
    public boolean getProduct(Product recProduct)
    {
        int iOldOrder = recProduct.getDefaultOrder();
        try {
            if ((this.get(Product.CODE) != null) && (!DBConstants.BLANK.equals(this.get(Product.CODE))))
            {   // Use code if it is available
                recProduct.addNew();
                recProduct.getField(Product.kCode).setString((String)this.get(Product.CODE));
                recProduct.setKeyArea(Product.kCodeKey);
                boolean bSuccess = recProduct.seek(null);
                if (bSuccess)
                    return bSuccess;
                
                recProduct.getField(Product.kDescription).setString((String)this.get(Product.CODE));
                recProduct.setKeyArea(Product.kDescSortKey);
                bSuccess = recProduct.seek(null);
                if (bSuccess)
                    return bSuccess;
        
                if (Utility.isNumeric(this.get(Product.CODE).toString()))
                    if (this.get(BookingDetail.PRODUCT_ID) == null)
                        this.put(BookingDetail.PRODUCT_ID, Integer.parseInt(this.get(Product.CODE).toString()));
            }
            if (this.get(BookingDetail.PRODUCT_ID) != null)
            {
                Integer intProductID = (Integer)this.get(BookingDetail.PRODUCT_ID);
                int iProductID = 0;
                if (intProductID != null)
                    iProductID = intProductID.intValue();
                if (iProductID == 0)
                    return false;
                recProduct.getField(Product.kID).setValue(iProductID);
                recProduct.setKeyArea(Product.kIDKey);
                return recProduct.seek(null);
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally {
            recProduct.setKeyArea(iOldOrder);
        }
        return false;
    }

}
