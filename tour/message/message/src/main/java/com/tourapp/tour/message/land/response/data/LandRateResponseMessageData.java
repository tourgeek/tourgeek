/**
  * @(#)LandRateResponseMessageData.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.message.land.response.data;

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
import com.tourapp.tour.message.base.response.data.*;
import com.tourapp.tour.message.land.request.*;
import org.jbundle.model.message.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.model.tour.booking.detail.db.*;
import com.tourapp.model.tour.product.land.db.*;
import com.tourapp.model.tour.product.base.db.*;

/**
 *  LandRateResponseMessageData - .
 */
public class LandRateResponseMessageData extends ProductRateResponseMessageData
{
    /**
     * Default constructor.
     */
    public LandRateResponseMessageData()
    {
        super();
    }
    /**
     * LandRateResponseMessageData Method.
     */
    public LandRateResponseMessageData(MessageDataParent messageDataParent, String strKey)
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
     * Move the map values to the correct record fields.
     * If this method is used, is must be overidden to move the correct fields.
     */
    public int getRawRecordData(Rec record)
    {
        int iInfoStatus = super.getRawRecordData(record);
        BookingLandModel recBookingLand = (BookingLandModel)record;
        this.getRawFieldData(recBookingLand.getField(BookingLandModel.PP_COST));
        this.getRawFieldData(recBookingLand.getField(BookingLandModel.PMC_COST));
        this.getRawFieldData(recBookingLand.getField(BookingLandModel.SIC_COST));
        // In case the rate class has changed
        // This is some complicated logic... be careful
        // If the class has changed, the rate key is not correct, so you must change it now.
        if (this.get(NEW_RATE_CLASS_ID_PARAM) != null)
        {
            int iRateClass = ((Integer)this.get(NEW_RATE_CLASS_ID_PARAM)).intValue();
            BaseField fldClass = ((Record)record).getField(BookingLandModel.CLASS_ID);
            if (iRateClass != 0)
                if (iRateClass != fldClass.getValue())
            {   // The rate class changed, recalc the rate key.
                BaseMessageHeader sharedMessageHeader = this.getMessage().getMessageHeader();
                BaseMessage tempMessage = new TreeMessage(sharedMessageHeader, null);   // Not kosher to share, but okay for this short use
                LandRateRequest tempRequest = new LandRateRequest(tempMessage, null);
                boolean[] rgbEnabled = fldClass.setEnableListeners(false); // Just being careful
                tempRequest.handlePutRawRecordData(record);
                String strSentMessageKey = tempRequest.getMessageKey(null).toString();
                String strOrigMessageKey = recBookingLand.getField(BookingDetailModel.COST_REQUEST_KEY).toString();
                if (strSentMessageKey.equals(strOrigMessageKey))
                {   // Good, this response matches the original request
                    recBookingLand.getField(BookingLandModel.CLASS_ID).setData(this.get(NEW_RATE_CLASS_ID_PARAM));   // Set new class
                    recBookingLand.getField(BookingDetailModel.DESCRIPTION).setString(recBookingLand.setupProductDesc());   // The description changes
                    String strNewMessageKey = tempRequest.getMessageKey(null).toString();
                    recBookingLand.getField(BookingDetailModel.COST_REQUEST_KEY).setString(strNewMessageKey);
                }
                //tempRequest.free();
                tempMessage.setMessageHeader(null);
                tempMessage.free();
                fldClass.setEnableListeners(rgbEnabled);
            }
        }
        return iInfoStatus;
    }
    /**
     * SetPMCCost Method.
     */
    public void setPMCCost(double dCost)
    {
        this.put(BookingLandModel.PMC_COST, new Double(dCost));
    }
    /**
     * SetSICCost Method.
     */
    public void setSICCost(double dCost)
    {
        this.put(BookingLandModel.SIC_COST, new Double(dCost));
    }
    /**
     * Get/Create the product record.
     * @param bFindFirst If true, try to lookup the record first.
     * @return The product record.
     */
    public ProductModel getProductRecord(RecordOwner recordOwner, boolean bFindFirst)
    {
        if (bFindFirst)
            if (recordOwner != null)
                if (recordOwner.getRecord(LandModel.LAND_FILE) != null)
                    return (LandModel)recordOwner.getRecord(LandModel.LAND_FILE);
        return (LandModel)Record.makeRecordFromClassName(LandModel.THICK_CLASS, recordOwner);
    }

}
