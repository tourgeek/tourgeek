/**
 *  @(#)ProductDesc.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.base.db.*;
import java.util.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.product.land.db.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.thin.app.booking.entry.search.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.base.message.trx.message.*;
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
import org.jbundle.main.msg.db.base.*;
import com.tourapp.tour.booking.inventory.db.*;

/**
 *  ProductDesc - Product Description.
 */
public class ProductDesc extends StringField
{
    /**
     * Default constructor.
     */
    public ProductDesc()
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
    public ProductDesc(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
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

}
