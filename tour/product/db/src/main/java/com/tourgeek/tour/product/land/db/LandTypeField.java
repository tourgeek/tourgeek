
package com.tourgeek.tour.product.land.db;

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
import com.tourgeek.tour.product.base.db.*;
import java.util.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.thin.app.booking.entry.search.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.core.trx.*;
import com.tourgeek.tour.message.land.request.*;
import com.tourgeek.tour.message.base.response.*;
import com.tourgeek.tour.product.tour.db.*;
import org.jbundle.main.msg.db.*;
import com.tourgeek.tour.message.base.request.*;
import com.tourgeek.tour.message.land.request.data.*;
import com.tourgeek.tour.message.land.response.*;
import com.tourgeek.tour.message.land.response.data.*;
import com.tourgeek.tour.message.base.request.data.*;
import org.jbundle.main.db.base.*;
import org.jbundle.model.message.*;
import com.tourgeek.model.tour.booking.detail.db.*;
import com.tourgeek.model.tour.booking.inventory.db.*;
import com.tourgeek.tour.acctpay.db.*;

/**
 *  LandTypeField - Type of Land Record.
 */
public class LandTypeField extends StringPopupField
{
    public static final String ENTERTAINMENT = "E";
    public final static String ITINERARY = "I";
    public static final String SIGHTSEEING = "S";
    public static final String TRANSFER = "T";
    /**
     * Default constructor.
     */
    public LandTypeField()
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
    public LandTypeField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
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
            objDefault = SIGHTSEEING;
        return objDefault;
    }
    /**
     * Get the conversion Map.
     */
    public String[][] getPopupMap()
    {
        String[][] string = {
            {DBConstants.BLANK, DBConstants.BLANK},
            {SIGHTSEEING, "Sightseeing"},
            {TRANSFER, "Transfer"},
            {ENTERTAINMENT, "Entertainment"},
            {ITINERARY, "Itinerary only"}
        };
        return string;
    }

}
