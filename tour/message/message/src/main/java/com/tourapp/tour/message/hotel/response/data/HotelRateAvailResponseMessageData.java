/**
  * @(#)HotelRateAvailResponseMessageData.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.message.hotel.response.data;

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
import com.tourapp.tour.message.hotel.response.*;
import org.jbundle.main.db.base.*;
import com.tourapp.model.tour.product.hotel.db.*;
import com.tourapp.model.tour.product.base.db.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  HotelRateAvailResponseMessageData - .
 */
public class HotelRateAvailResponseMessageData extends HotelRateResponseMessageData
{
    /**
     * Default constructor.
     */
    public HotelRateAvailResponseMessageData()
    {
        super();
    }
    /**
     * HotelRateAvailResponseMessageData Method.
     */
    public HotelRateAvailResponseMessageData(MessageDataParent messageDataParent, String strKey)
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
        this.addMessageFieldDesc(ProductModel.AVAILABILITY_PARAM, Integer.class, MessageFieldDesc.REQUIRED, null);
    }
    /**
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(Rec record)
    {
        int iInfoStatus = super.getRawRecordData(record);
        BookingHotelModel recBookingHotel = (BookingHotelModel)record;
        
        if (iInfoStatus == BaseStatus.VALID)
        {
            HotelAvailabilityResponse response = new HotelAvailabilityResponse(this.getMessage(), null);
            iInfoStatus = response.getRawRecordData(record);        
            String iFieldSeq = BookingDetailModel.INVENTORY_STATUS_ID;
            recBookingHotel.getField(iFieldSeq).setValue(iInfoStatus);   // Usually VALID
        }
        
        return iInfoStatus;
    }

}
