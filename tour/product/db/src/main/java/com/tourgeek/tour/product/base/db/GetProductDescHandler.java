
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
