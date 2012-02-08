/**
 * @(#)GetProductDescHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.db;

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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.base.db.*;
import java.util.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.product.land.db.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.thin.app.booking.entry.search.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.base.message.core.trx.*;
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.main.msg.screen.*;
import com.tourapp.tour.message.base.response.*;
import java.text.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.product.trans.db.*;
import com.tourapp.tour.product.car.db.*;
import com.tourapp.tour.product.cruise.db.*;
import com.tourapp.tour.product.item.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.base.response.data.*;
import com.tourapp.tour.product.base.screen.*;
import com.tourapp.tour.product.base.search.screen.*;
import com.tourapp.tour.booking.entry.event.*;
import com.tourapp.tour.product.air.db.*;
import org.jbundle.main.db.base.*;
import com.tourapp.tour.booking.inventory.db.*;

/**
 *  GetProductDescHandler - Get the product description of this Product object.
 */
public class GetProductDescHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public GetProductDescHandler()
    {
        super();
    }
    /**
     * GetProductDescHandler Method.
     */
    public GetProductDescHandler(BaseField field)
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
     * Set the object that owns this listener.
     * @owner The object that this listener is being added to (if null, this listener is being removed).
     */
    public void setOwner(ListenerOwner owner)
    {
        if (owner != null)
        {
            Record recProduct = ((BaseField)owner).getRecord();
            String string = recProduct.getTableNames(false);
            if (string.equals("TourHeader"))
                string = "Tour";
            ((BaseField)owner).setDefault(string);
        }
        super.setOwner(owner);
    }

}
