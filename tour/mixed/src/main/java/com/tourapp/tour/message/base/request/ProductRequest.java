/**
 * @(#)ProductRequest.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.base.request;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.message.base.*;
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.base.response.*;

/**
 *  ProductRequest - Cost/Price request (out) message.
 */
public class ProductRequest extends BaseProductMessageDesc
{
    public static final String PRODUCT_MESSAGE = "productMessage";
    public static final String BOOKING_MESSAGE = "bookingMessage";
    public static final String PASSENGER_MESSAGE = "passengerMessage";
    public static final String PASSENGER_DETAIL = "paxDetail";
    /**
     * Default constructor.
     */
    public ProductRequest()
    {
        super();
    }
    /**
     * ProductRequest Method.
     */
    public ProductRequest(MessageDataParent messageDataParent, String strKey)
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
     * Setup sub-Message Data.
     */
    public void setupMessageDataDesc()
    {
        super.setupMessageDataDesc();
        this.addMessageDataDesc(this.createProductMessageData());
        this.addMessageDataDesc(new BookingMessageData(this, BOOKING_MESSAGE));
        this.addMessageDataDesc(new PassengerMessageData(this, PASSENGER_MESSAGE));
    }
    /**
     * CreateProductMessageData Method.
     */
    public ProductMessageData createProductMessageData()
    {
        return new ProductMessageData(this, PRODUCT_MESSAGE);
    }
    /**
     * Make sure this BookingDetail is linked to an ApTrx.
     */
    public int initBookingApTrx(Rec record)
    {
        BookingDetail recBookingDetail = (BookingDetail)record; 
        if (!recBookingDetail.getField(BookingDetail.AP_TRX_ID).isNull())
        {
            int iOldOrder = recBookingDetail.getDefaultOrder();
            FileListener listener = null;
            Object bookmark = null;
            try {
                bookmark = recBookingDetail.getHandle(DBConstants.BOOKMARK_HANDLE);
                ApTrx recApTrx = (ApTrx)((ReferenceField)recBookingDetail.getField(BookingDetail.AP_TRX_ID)).getReference();
                Vendor recVendor = (Vendor)((ReferenceField)recApTrx.getField(ApTrx.VENDOR_ID)).getReference();
                Tour recTour = (Tour)((ReferenceField)recApTrx.getField(ApTrx.TOUR_ID)).getReference();
                if (recBookingDetail.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
                    recBookingDetail.set();
        
                int iProductTypeID = 0;
                if (recVendor != null)
                {
                    if ((OperationTypeField.LIKE_TOGETHER_CODE.equalsIgnoreCase(recVendor.getField(Vendor.OPERATION_TYPE_CODE).toString()))
                        || (OperationTypeField.INDIVIDUALLY_CODE.equalsIgnoreCase(recVendor.getField(Vendor.OPERATION_TYPE_CODE).toString())))
                            iProductTypeID = (int)recBookingDetail.getField(BookingDetail.PRODUCT_TYPE_ID).getValue();
                }
                // Ouch - This is very expensive, but realistically booking orders are not very frequent
                recBookingDetail = new BookingDetail(recBookingDetail.getRecordOwner());
        
                recBookingDetail.setKeyArea(BookingDetail.TOUR_ID_KEY);
                recBookingDetail.addListener(listener = new SubFileFilter(recTour.getField(Tour.ID), BookingDetail.TOUR_ID, recVendor.getField(Vendor.ID), BookingDetail.VENDOR_ID, null, null));
                BaseTable tblBookingDetail = recBookingDetail.getTable();
                tblBookingDetail.close();
        
                while (tblBookingDetail.next() != null)
                {   // Spin until I get to the right product type.
                    if (iProductTypeID == 0)
                        break;
                    if (iProductTypeID == tblBookingDetail.getCurrentTable().getRecord().getField(BookingDetail.PRODUCT_TYPE_ID).getValue())
                        break;
                }
        
                recApTrx.updateThisApTrx(tblBookingDetail, recTour, recVendor, iProductTypeID);
                
                if (bookmark != null)
                {
                    ((Record)record).setHandle(bookmark, DBConstants.BOOKMARK_HANDLE);
                    ((Record)record).edit();
                }
            } catch (DBException e) {
                e.printStackTrace();
            } finally {
                if (listener != null)
                    recBookingDetail.removeListener(listener, true);
                recBookingDetail.setKeyArea(iOldOrder);
            }
            if (record != recBookingDetail)
                recBookingDetail.free();
            
            return DBConstants.NORMAL_RETURN; // Good already linked to an ApTrx
        }
        ApTrx recApTrx = (ApTrx)((ReferenceField)recBookingDetail.getField(BookingDetail.AP_TRX_ID)).getReferenceRecord();
        return recApTrx.linkBookingDetailToApTrx((BookingDetail)record);
    }
    /**
     * Reduce the internal inventory before sending the booking request.
     * @param record The BookingDetail record.
     * @return The error code.
     */
    public int initBookingInventory(Rec record)
    {
        BookingDetail recBookingDetail = (BookingDetail)record; 
        int iErrorCode = DBConstants.NORMAL_RETURN;
        Product product = recBookingDetail.getProduct();
        // This next line UPDATES the inventory and returns a (unusable) response message with an error code.
        if (!(((MessageTransport)((ReferenceField)recBookingDetail.getField(BookingDetail.INVENTORY_MESSAGE_TRANSPORT_ID)).getReference()).isDirectTransport()))
            return DBConstants.NORMAL_RETURN; // No need to check inventory if this is not taking from the direct inventory
        BaseProductResponse response = (BaseProductResponse)product.processAvailabilityRequestInMessage((BaseMessage)this.getMessage(), null, (BaseField)record.getCounterField()).getMessageDataDesc(null);
        if (response.getMessageDataStatus() != BaseDataStatus.VALID)
        {
            iErrorCode = DBConstants.ERROR_RETURN;
            if (recBookingDetail.getRecordOwner() != null)
                if (recBookingDetail.getRecordOwner().getTask() != null)
                    iErrorCode = recBookingDetail.getRecordOwner().getTask().setLastError((response.getMessageDataError()));
        }
        return iErrorCode;
    }
    /**
     * CheckBookingRequestParams Method.
     */
    public int checkBookingRequestParams(Rec record)
    {
        BookingDetail recBookingDetail = (BookingDetail)record; 
        int iStatus = BaseDataStatus.DATA_VALID;
        if (this.isOrderComponents(recBookingDetail) == false)
        {
            iStatus = BaseDataStatus.PROPOSAL;        // The information must be valid to lookup the price
            String strError = "The Order components event must occur";
            strError = this.getString(recBookingDetail, ResourceConstants.BOOKING_RESOURCE, strError);
            recBookingDetail.setErrorMessage(this, strError);
        }
        else
        {
            String strError = null;
            if (recBookingDetail.getField(BookingDetail.INVENTORY_STATUS_ID).getValue() != BaseDataStatus.OKAY)
                strError = "Inventory Status must be okay";
            if (recBookingDetail.getField(BookingDetail.COST_STATUS_ID).getValue() != BaseDataStatus.OKAY)
                strError = "Cost Status must be okay";
            if (recBookingDetail.getField(BookingDetail.INFO_STATUS_ID).getValue() != BaseDataStatus.OKAY)
                strError = "Info Status must be okay";
            if (strError == null)
            {
                // Double-check to make sure inventory and cost requests are up-to-date
                // NOTE: This is a big performance hit... but required for now.
                // If info is changed, cost status is changed which triggers a booking update
                // The booking update can be called before the inventory update is done (since the inventory status is hasn't been changed from okay)
                if (iStatus == BaseDataStatus.DATA_VALID)
                {
                    String strOldKey = recBookingDetail.getField(BookingDetail.INVENTORY_REQUEST_KEY).toString();
                    boolean bOldIsModified = recBookingDetail.getField(BookingDetail.INVENTORY_REQUEST_KEY).isModified();
                    BaseProductMessageDesc message = recBookingDetail.checkMessageRequired(BookingDetail.INVENTORY_STATUS_ID);
                    if (message != null)
                        if (recBookingDetail.getField(BookingDetail.INVENTORY_REQUEST_KEY).isModified())
                            if (!recBookingDetail.getField(BookingDetail.INVENTORY_REQUEST_KEY).toString().equals(strOldKey))
                    {
                        recBookingDetail.getField(BookingDetail.INVENTORY_REQUEST_KEY).setString(strOldKey);
                        recBookingDetail.getField(BookingDetail.INVENTORY_REQUEST_KEY).setModified(bOldIsModified);                    
                        strError = "Waiting for Inventory Status";
                    }
                    else
                    {
                        strOldKey = recBookingDetail.getField(BookingDetail.COST_REQUEST_KEY).toString();
                        bOldIsModified = recBookingDetail.getField(BookingDetail.COST_REQUEST_KEY).isModified();
                        message = recBookingDetail.checkMessageRequired(BookingDetail.COST_STATUS_ID);
                        if (message != null)
                            if (recBookingDetail.getField(BookingDetail.COST_REQUEST_KEY).isModified())
                                if (!recBookingDetail.getField(BookingDetail.COST_REQUEST_KEY).toString().equals(strOldKey))
                        {
                            recBookingDetail.getField(BookingDetail.COST_REQUEST_KEY).setString(strOldKey);
                            recBookingDetail.getField(BookingDetail.COST_REQUEST_KEY).setModified(bOldIsModified);                    
                            strError = "Waiting for Cost Status";
                        }                        
                    }
                }
            }
            if (strError != null)
            {
                iStatus = BaseDataStatus.DATA_REQUIRED;        // The information must be valid to lookup the price
                strError = this.getString(recBookingDetail, ResourceConstants.BOOKING_RESOURCE, strError);
                recBookingDetail.setErrorMessage(this, strError);
            }
        }
        return iStatus;
    }
    /**
     * IsOrderComponents Method.
     */
    public boolean isOrderComponents(BookingDetail recBookingDetail)
    {
        Record recTour = ((ReferenceField)recBookingDetail.getField(BookingDetail.TOUR_ID)).getReference();
        return recTour.getField(Tour.ORDER_COMPONENTS).getState();
    }
    /**
     * GetRequestType Method.
     */
    public String getRequestType()
    {
        return null;    // Override to change
    }

}
