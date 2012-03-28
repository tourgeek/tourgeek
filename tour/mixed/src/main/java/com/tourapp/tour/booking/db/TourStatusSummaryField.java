/**
 * @(#)TourStatusSummaryField.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.db;

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
import com.tourapp.tour.product.tour.db.*;
import java.util.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.booking.lookup.*;
import com.tourapp.tour.booking.detail.event.*;
import com.tourapp.tour.product.tour.other.screen.*;
import com.tourapp.tour.product.tour.schedule.db.*;
import com.tourapp.tour.booking.db.event.*;
import com.tourapp.tour.acctpay.screen.findepest.*;
import org.jbundle.main.db.base.*;

/**
 *  TourStatusSummaryField - Special field for the tour status summary.
 */
public class TourStatusSummaryField extends PropertiesField
{
    public static final String DELETED_VALUE = "99";
    /**
     * Default constructor.
     */
    public TourStatusSummaryField()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The parent record.
     * @param strName The field name.
     * @param iDataLength The maximum string length (pass -1 for default).
     * @param strDesc The string description (usually pass null, to use the resource file desc).
     * @param strDefault The default value (if object, this value is the default value, if string, the string is the default).
     */
    public TourStatusSummaryField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        super.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Set the (fieldseq) property for this BookingDetail item.
     */
    public void setDetailProperty(Record recBookingDetail, String iFieldSeq, int iChangeType)
    {
        String strKey = this.getKeyFromRecord(recBookingDetail, iFieldSeq);
        String strValue = this.getValueFromRecord(recBookingDetail, iFieldSeq, iChangeType);
        this.setProperty(strKey, strValue);
    }
    /**
     * GetKeyFromRecord Method.
     */
    public String getKeyFromRecord(Record recBookingDetail, String iFieldSeq)
    {
        String bookmark = recBookingDetail.getCounterField().toString();
        BaseField fieldTarget = recBookingDetail.getField(iFieldSeq);
        String strFieldType = fieldTarget.getFieldName();
        if ((bookmark == null) || (bookmark.length() == 0))
            bookmark = recBookingDetail.getLastModified(DBConstants.BOOKMARK_HANDLE).toString();
        String strKey = strFieldType + '.' + bookmark;
        return strKey;
    }
    /**
     * GetValueFromRecord Method.
     */
    public String getValueFromRecord(Record recBookingDetail, String iFieldSeq, int iChangeType)
    {
        BaseField fieldTarget = recBookingDetail.getField(iFieldSeq);
        String strValue = fieldTarget.toString();
        if (iChangeType == DBConstants.AFTER_DELETE_TYPE)
            strValue = DELETED_VALUE;
        String strLastChanged = null;
        if (!recBookingDetail.getField(BookingDetail.LAST_CHANGED).isNull())
            strLastChanged = Long.toString((long)recBookingDetail.getField(BookingDetail.LAST_CHANGED).getValue());
        if ((strValue != null) && (strLastChanged != null))
            strValue = strValue + "," + strLastChanged;
        return strValue;
    }
    /**
     * Go through all the items and get the highest status key.
     */
    public String getHighStatusKey()
    {
        String strStatusKey = null;
        Map<String,Object> properties = this.getProperties();
        if (properties != null)
        {
            int iHighStatus = BaseStatus.NO_STATUS;
            for (String strKey : properties.keySet())
            {
                int iDot = strKey.indexOf('.');
                if (iDot != -1)
                {
                    int iValue = this.getStatusFromProperty((String)properties.get(strKey));
                    if (iValue > iHighStatus)
                    {
                        iHighStatus = iValue;
                        strStatusKey = strKey;
                    }
                }
            }
        }
        return strStatusKey;
    }
    /**
     * Merge my changed data back into field that I just restored from disk.
     * @param objData The value this field held before I refreshed from disk.
     * @return The setData error code.
     */
    public String mergeKey(String strKey, String strReadValue, String strCurrentValue)
    {
        if (this.getTimestampFromProperty(strCurrentValue) > this.getTimestampFromProperty(strReadValue))
            return strCurrentValue;
        // Okay, it isn't clear what the key value should be, so I just look it up. This is expensive, but it shouldn't happen often
        BookingDetail recBookingDetail = new BookingDetail(this.getRecord().getRecordOwner());
        String strID = this.getIDFromKey(strKey);
        recBookingDetail.setKeyArea(BookingDetail.ID_KEY);
        recBookingDetail.getField(BookingDetail.ID).setString(strID);
        try {
            if (recBookingDetail.seek(DBConstants.EQUALS))
            {
                String strStatus = this.getStatusFromKey(strKey);
                for (int iFieldSeq = 0; iFieldSeq < recBookingDetail.getFieldCount(); iFieldSeq++)
                {
                    if (recBookingDetail.getField(iFieldSeq).getFieldName().equalsIgnoreCase(strStatus))
                    {
                        return this.getValueFromRecord(recBookingDetail, recBookingDetail.getField(iFieldSeq).getFieldName(), DBConstants.AFTER_UPDATE_TYPE);
                    }
                }
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
        recBookingDetail.free();
        return super.mergeKey(strKey, strReadValue, strCurrentValue);
    }
    /**
     * GetIDFromKey Method.
     */
    public String getIDFromKey(String strKey)
    {
        if (strKey.indexOf('.') != -1)
            return strKey.substring(strKey.indexOf('.') + 1);
        return strKey;  // Never
    }
    /**
     * GetStatusFromKey Method.
     */
    public String getStatusFromKey(String strKey)
    {
        if (strKey.indexOf('.') != -1)
            return strKey.substring(0, strKey.indexOf('.'));
        return strKey;  // Never
    }
    /**
     * Given this retrieved property, extract the status value.
     */
    public int getStatusFromProperty(String strValue)
    {
        if (strValue != null)
            if (strValue.indexOf(',') != -1)
                strValue = strValue.substring(0, strValue.indexOf(','));
        if (DELETED_VALUE.equals(strValue))
            return 0;
        try {
            return ((Integer)Converter.convertObjectToDatatype(strValue, Integer.class, IntegerField.ZERO)).intValue();
        } catch (Exception ex) {
        }
        return 0;
    }
    /**
     * Given this property value, get the timestamp value.
     */
    public long getTimestampFromProperty(String strValue)
    {
        if (strValue != null)
            if (strValue.indexOf(',') != -1)
        {
            strValue = strValue.substring(strValue.indexOf(',') + 1);
            try {
                return Long.parseLong(strValue);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

}
