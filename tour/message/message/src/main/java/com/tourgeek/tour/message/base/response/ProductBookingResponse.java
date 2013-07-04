
package com.tourgeek.tour.message.base.response;

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
import com.tourgeek.tour.message.base.response.data.*;
import org.jbundle.model.message.*;
import com.tourgeek.model.tour.product.base.db.*;
import com.tourgeek.model.tour.booking.detail.db.*;

/**
 *  ProductBookingResponse - .
 */
public class ProductBookingResponse extends BaseProductResponse
{
    /**
     * Default constructor.
     */
    public ProductBookingResponse()
    {
        super();
    }
    /**
     * ProductBookingResponse Method.
     */
    public ProductBookingResponse(MessageDataParent messageDataParent, String strKey)
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
        ((ProductResponseMessageData)this.getMessageDataDesc(PRODUCT_RESPONSE_MESSAGE)).addMessageFieldDesc(BookingDetailModel.REMOTE_BOOKING_NO, String.class, MessageFieldDesc.REQUIRED, MessageFieldDesc.NOT_UNIQUE | MessageFieldDesc.DONT_INIT, null);
    }
    /**
     * Get the key prefix for this type of message.
     */
    public String getKeyPrefix()
    {
        return "message.booking.";
    }

}
