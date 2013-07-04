
package com.tourgeek.tour.product.base.db;

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
import com.tourgeek.tour.base.db.*;
import java.util.*;
import com.tourgeek.tour.product.hotel.db.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.product.land.db.*;
import org.jbundle.thin.base.message.*;
import com.tourgeek.thin.app.booking.entry.search.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.base.message.core.trx.*;
import com.tourgeek.tour.message.base.response.*;
import java.text.*;
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.tour.profile.db.*;
import com.tourgeek.tour.product.trans.db.*;
import com.tourgeek.tour.product.car.db.*;
import com.tourgeek.tour.product.cruise.db.*;
import com.tourgeek.tour.product.item.db.*;
import com.tourgeek.tour.message.base.request.*;
import com.tourgeek.tour.message.base.request.data.*;
import com.tourgeek.tour.message.base.response.data.*;
import com.tourgeek.tour.product.air.db.*;
import org.jbundle.main.db.base.*;
import com.tourgeek.tour.product.base.event.*;
import com.tourgeek.model.tour.booking.db.*;
import com.tourgeek.model.tour.booking.detail.db.*;
import com.tourgeek.model.tour.booking.inventory.db.*;
import org.jbundle.model.message.*;

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
