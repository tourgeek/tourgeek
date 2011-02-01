/**
 *  @(#)UpdateCancelationAcctDetailHandler.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.refund;

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
import com.tourapp.tour.acctrec.db.event.*;
import com.tourapp.tour.product.base.db.*;

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
        return (ReferenceField)this.getProductCategory().getField(ProductCategory.kXLChgAccountID);
    }

}
