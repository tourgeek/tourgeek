/**
 * @(#)UpdateCancelationAcctDetailHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.db.event;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.model.tour.product.base.db.*;

/**
 *  UpdateCancelationAcctDetailHandler - Post the cancellation charge.
 */
public class UpdateCancelationAcctDetailHandler extends UpdateArTrxAcctDetailHandler
{
    /**
     * Default constructor.
     */
    public UpdateCancelationAcctDetailHandler()
    {
        super();
    }
    /**
     * Constructor.
     */
    public UpdateCancelationAcctDetailHandler(Record recBooking)
    {
        this();
        this.init(recBooking);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record recBooking)
    {
        super.init(recBooking);
    }
    /**
     * Get the Credit Account field.
     * @return The credit account field.
     */
    public ReferenceField getCrAccount()
    {
        return (ReferenceField)this.getProductCategory().getField(ProductCategoryModel.XL_CHG_ACCOUNT_ID);
    }

}
