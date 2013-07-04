
package com.tourgeek.tour.message.car.request;

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
import org.jbundle.thin.base.message.*;
import com.tourgeek.tour.message.base.request.data.*;
import com.tourgeek.tour.message.car.request.data.*;
import org.jbundle.model.message.*;

/**
 *  CarRateRequest - .
 */
public class CarRateRequest extends ProductRateRequest
{
    /**
     * Default constructor.
     */
    public CarRateRequest()
    {
        super();
    }
    /**
     * CarRateRequest Method.
     */
    public CarRateRequest(MessageDataParent messageDataParent, String strKey)
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
        return new CarMessageData(this, PRODUCT_MESSAGE);
    }

}
