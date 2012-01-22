/**
 * @(#)BookingDetailStatusUpdateHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.detail.base;

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
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.main.db.base.*;
import org.jbundle.main.properties.db.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.thin.base.message.*;

/**
 *  BookingDetailStatusUpdateHandler - .
 */
public class BookingDetailStatusUpdateHandler extends BaseStatusUpdateHandler
{
    /**
     * Default constructor.
     */
    public BookingDetailStatusUpdateHandler()
    {
        super();
    }
    /**
     * BookingDetailStatusUpdateHandler Method.
     */
    public BookingDetailStatusUpdateHandler(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        super.init(field);
    }
    /**
     * Get the message to display in the message box.
     */
    public String getDisplayMessage()
    {
        Record recBaseStatus = this.getOwner().getRecord();
        BaseField fldStatusID = recBaseStatus.getReferringField();
        Record recBookingDetail = fldStatusID.getRecord(); 
        int iStatusID = (int)fldStatusID.getValue();
        String strProperty = this.getFieldParam(fldStatusID);
        int iFieldSeq = BookingDetail.kInfoStatusID;
        if (strProperty.indexOf(BookingDetail.COST_PARAM) != -1)
            iFieldSeq = BookingDetail.kCostStatusID;
        else if (strProperty.indexOf(BookingDetail.INVENTORY_PARAM) != -1)
            iFieldSeq = BookingDetail.kInventoryStatusID;
        else if (strProperty.indexOf(BookingDetail.PRODUCT_PARAM) != -1)
            iFieldSeq = BookingDetail.kProductStatusID;
        
        String strMessage = fldStatusID.getFieldTip();   // Default display = field help text
        if (iStatusID != BaseStatus.MANUAL_REQUEST_REQUIRED)
        {
            Map<String,Object> properties = ((PropertiesField)recBookingDetail.getField(BookingDetail.kErrorProperties)).getProperties();
            if (properties != null)
            {
                String strErrorKey = this.getFieldParam(fldStatusID);
                strErrorKey = strErrorKey + '.' + BookingDetail.MESSAGE_PARAM + '.' + BookingDetail.ERROR_PARAM;
                strMessage = (String)properties.get(strErrorKey);
                
                RecordOwner recordOwner = Utility.getRecordOwner(recBookingDetail);
                PropertiesInput recProperties = new PropertiesInput(recordOwner);
                if (recordOwner != null)
                    recordOwner.removeRecord(recProperties);
                recBookingDetail.addListener(new FreeOnFreeHandler(recProperties));
                Map<String,Object> mapKeyDescriptions = new Hashtable<String,Object>();
                Map<String,Object> propErrors = ((PropertiesField)recBookingDetail.getField(BookingDetail.kErrorProperties)).getProperties();
                if (propErrors != null)
                {
                    String strStartOfParam = this.getFieldParam(fldStatusID);
                    strStartOfParam = strStartOfParam + '.' + BookingDetail.MESSAGE_PARAM + '.';
                    for (String strKey : propErrors.keySet())
                    {
                        if (strKey.startsWith(strStartOfParam))
                            if (!strKey.equals(strErrorKey))
                        {
                            String DESCRIPTION = ".Description";
                            if (strKey.endsWith(DESCRIPTION))
                            {
                                String strValue = (String)propErrors.get(strKey);
                                strKey = strKey.substring(0, strKey.length() - DESCRIPTION.length());
                                if (propErrors.get(strKey + ".VariesDesc") != null)
                                    strValue = strValue + " (" + propErrors.get(strKey + ".VariesDesc") + ")";
                                mapKeyDescriptions.put(strKey, strValue);
                            }
                        }                        
                    }
                }
                if (mapKeyDescriptions.size() > 0)
                {
                    // The following code says, try to recalculate the cost if the user changes the properties.
                    FieldListener listener = null;
                    recBookingDetail.getField(BookingDetail.kProperties).addListener(listener = new InitOnChangeHandler(recBookingDetail.getField(iFieldSeq + BookingDetail.MESSAGE_KEY_OFFSET)));
                    listener.setRespondsToMode(DBConstants.READ_MOVE, false);   // Usually, you only want to move a string on screen change
                    listener.setRespondsToMode(DBConstants.INIT_MOVE, false);   // Usually, you only want to move a string on screen change
                    recProperties.getField(PropertiesInput.kKey).addListener(new FieldRemoveBOnCloseHandler(listener));
                    recBookingDetail.getField(BookingDetail.kProperties).addListener(listener = new CopyDataHandler(recBookingDetail.getField(iFieldSeq + BookingDetail.MESSAGE_REQUEST_OFFSET), Boolean.TRUE, null));
                    recProperties.getField(PropertiesInput.kKey).addListener(new FieldRemoveBOnCloseHandler(listener));
                    // Display the properties editor
                    recProperties.startEditor((PropertiesField)recBookingDetail.getField(BookingDetail.kProperties), false, mapKeyDescriptions);
                }
            }
        }
        if (iStatusID == BaseStatus.MANUAL_REQUEST_REQUIRED)
        { // Force the manual request to be sent by setting the Manual=True param in the message header.
            strProperty += '.' + BookingDetail.MESSAGE_PARAM + '.' + BaseMessage.HEADER_TAG + '.' + MessageTransport.MANUAL_RESPONSE_PARAM;
            recBookingDetail.getField(iFieldSeq + BookingDetail.MESSAGE_KEY_OFFSET).setString(DBConstants.BLANK);     // Force message change
            String strValue = DBConstants.TRUE;
            ((PropertiesField)recBookingDetail.getField(BookingDetail.kProperties)).setProperty(strProperty, strValue);
            recBookingDetail.getField(iFieldSeq + BookingDetail.MESSAGE_REQUEST_OFFSET).setData(Boolean.TRUE); // Force the request to go!
        }
        if (strMessage != null)
            return strMessage;
        return super.getDisplayMessage();
    }
    /**
     * GetFieldParam Method.
     */
    public String getFieldParam(BaseField fldStatusID)
    {
        return ((BookingDetail)fldStatusID.getRecord()).getFieldParam(fldStatusID);
    }

}
