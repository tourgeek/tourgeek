/**
 * @(#)HotelFreeField.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.hotel.db;

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
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.hotel.screen.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.base.db.*;
import java.util.*;
import com.tourapp.tour.acctpay.db.*;
import java.text.*;
import com.tourapp.thin.app.booking.entry.search.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.message.hotel.request.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.core.trx.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.product.tour.db.*;
import org.jbundle.thin.base.screen.*;
import com.tourapp.tour.booking.inventory.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.hotel.request.data.*;
import com.tourapp.tour.message.hotel.response.data.*;
import com.tourapp.tour.message.hotel.response.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.base.response.data.*;
import org.jbundle.main.db.base.*;

/**
 *  HotelFreeField - .
 */
public class HotelFreeField extends StringPopupField
{
    public static final String SINGLE_ROOM = "S";
    public static final String TWIN_SHARE = "T";
    /**
     * Default constructor.
     */
    public HotelFreeField()
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
    public HotelFreeField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Constructor.
     * @param record The parent record.
     * @param strName The field name.
     * @param iDataLength The maximum string length (pass -1 for default).
     * @param strDesc The string description (usually pass null, to use the resource file desc).
     * @param strDefault The default value (if object, this value is the default value, if string, the string is the default).
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        super.init(record, strName, iDataLength, strDesc, strDefault);
        if (iDataLength == DBConstants.DEFAULT_FIELD_LENGTH)
            m_iMaxLength = 1;
    }
    /**
     * Get the default value.
     * @return The default value.
     */
    public Object getDefault()
    {
        Object objDefault = super.getDefault();
        if (objDefault == null)
            objDefault = SINGLE_ROOM;
        return objDefault;
    }
    /**
     * Get the conversion Map.
     */
    public String[][] getPopupMap()
    {
        String[][] string = {
            {SINGLE_ROOM, "Single room"},
            {TWIN_SHARE, "Twin share"}
        };
        return string;
    }

}
