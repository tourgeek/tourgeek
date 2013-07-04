
package com.tourgeek.tour.message.air.request;

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
import com.tourgeek.tour.message.base.request.*;
import com.tourgeek.tour.message.air.request.data.*;
import com.tourgeek.tour.message.base.request.data.*;
import org.jbundle.model.message.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.main.msg.db.*;
import com.tourgeek.model.tour.booking.detail.db.*;
import com.tourgeek.model.tour.product.base.db.*;

/**
 *  AirInformationRequest - .
 */
public class AirInformationRequest extends ProductInformationRequest
{
    /**
     * Default constructor.
     */
    public AirInformationRequest()
    {
        super();
    }
    /**
     * AirInformationRequest Method.
     */
    public AirInformationRequest(MessageDataParent messageDataParent, String strKey)
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
     * CreateProductMessageData Method.
     */
    public ProductMessageData createProductMessageData()
    {
        return new AirMessageData(this, PRODUCT_MESSAGE);
    }

}
