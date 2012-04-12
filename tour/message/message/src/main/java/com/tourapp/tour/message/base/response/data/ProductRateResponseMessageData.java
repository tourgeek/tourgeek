/**
 * @(#)ProductRateResponseMessageData.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.base.response.data;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import org.jbundle.model.message.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.model.tour.product.base.db.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  ProductRateResponseMessageData - .
 */
public class ProductRateResponseMessageData extends ProductResponseMessageData
{
    public static final String NEW_RATE_CLASS_ID_PARAM = BookingDetailModel.RATE_ID + "New";
    /**
     * Default constructor.
     */
    public ProductRateResponseMessageData()
    {
        super();
    }
    /**
     * ProductRateResponseMessageData Method.
     */
    public ProductRateResponseMessageData(MessageDataParent messageDataParent, String strKey)
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
            strKey = BaseProductResponse.PRODUCT_RESPONSE_MESSAGE;
        super.init(messageDataParent, strKey);
    }
    /**
     * Setup sub-Message Data.
     */
    public void setupMessageDataDesc()
    {
        super.setupMessageDataDesc();
        this.addMessageFieldDesc(BookingDetailModel.TOTAL_COST, Double.class, MessageFieldDesc.REQUIRED, null);
        this.addMessageFieldDesc(BookingDetailModel.PP_COST, Double.class, MessageFieldDesc.REQUIRED, null);
        this.addMessageFieldDesc(NEW_RATE_CLASS_ID_PARAM, Integer.class, MessageFieldDesc.REQUIRED, null);
        //?this.addMessageFieldDesc(BookingDetailModel.CLASS_ID, Integer.class, MessageFieldDesc.REQUIRED, null);
    }
    /**
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(Rec record)
    {
        int iInfoStatus = super.getRawRecordData(record);
        BookingDetailModel recBookingDetail = (BookingDetailModel)record;
        if (recBookingDetail.getField(BookingDetailModel.EXCHANGE).getValue() == 0)
        {
            ProductModel recProduct = recBookingDetail.getProduct();
            if (recProduct != null)
            {
                Vendor recVendor = (Vendor)((ReferenceField)recProduct.getField(ProductModel.VENDOR_ID)).getReference();
                if (recVendor != null)
                {
                    Currencys recCurrencys = (Currencys)((ReferenceField)recVendor.getField(recVendor.CURRENCYS_ID)).getReference();
                    if (recCurrencys != null)
                    {
                        if (!recCurrencys.getField(Currencys.COSTING_RATE).isNull())
                            ((Record)recBookingDetail).getField(BookingDetailModel.EXCHANGE).moveFieldToThis((BaseField)recCurrencys.getField(Currencys.COSTING_RATE));
                        else
                            ((Record)recBookingDetail).getField(BookingDetailModel.EXCHANGE).moveFieldToThis((BaseField)recCurrencys.getField(Currencys.COSTING_RATE));
                    }
                }
            }
            if (recBookingDetail.getField(BookingDetailModel.EXCHANGE).getValue() == 0)
                recBookingDetail.getField(BookingDetailModel.EXCHANGE).setValue(1.0);
        }
        this.getRawFieldData(recBookingDetail.getField(BookingDetailModel.TOTAL_COST));
        if (recBookingDetail.getField(BookingDetailModel.MARKUP_FROM_LAST).getValue() > 0)
            recBookingDetail.getField(BookingDetailModel.TOTAL_COST).setValue(Math.floor(recBookingDetail.getField(BookingDetailModel.TOTAL_COST).getValue() * (1 + recBookingDetail.getField(BookingDetailModel.MARKUP_FROM_LAST).getValue()) * 100 + 0.5) / 100);
        return iInfoStatus;
    }
    /**
     * SetPPCost Method.
     */
    public void setPPCost(double dCost)
    {
        this.put(BookingDetailModel.PP_COST, new Double(dCost));
    }
    /**
     * SetNewRateClassID Method.
     */
    public void setNewRateClassID(int iClassID)
    {
        this.put(NEW_RATE_CLASS_ID_PARAM, new Integer(iClassID));
    }
    /**
     * SetRateClassID Method.
     */
    public void setRateClassID(int iClassID)
    {
        this.put(BookingDetailModel.CLASS_ID, new Integer(iClassID));
    }

}
