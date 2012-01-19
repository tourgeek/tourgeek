/**
 * @(#)CarRateResponse.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.car.response;

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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.message.base.response.*;
import org.jbundle.thin.base.message.*;

/**
 *  CarRateResponse - .
 */
public class CarRateResponse extends ProductRateResponse
{
    public static final String PER_VEHICLE_COST_PARAM = "perVehicleCost";
    /**
     * Default constructor.
     */
    public CarRateResponse()
    {
        super();
    }
    /**
     * CarRateResponse Method.
     */
    public CarRateResponse(MessageDataParent messageDataParent, String strKey)
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
     * SetPerVehicleCost Method.
     */
    public void setPerVehicleCost(double dPerVehicleCost)
    {
        this.put(PER_VEHICLE_COST_PARAM, new Double(dPerVehicleCost));
    }

}
