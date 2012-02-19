/**
 * @(#)UpdateRefundAcctDetailHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.entry.acctrec.*;
import com.tourapp.tour.acctrec.screen.misc.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctrec.db.event.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  UpdateRefundAcctDetailHandler - Update the G/L for a refund transaction.
 */
public class UpdateRefundAcctDetailHandler extends UpdateArTrxAcctDetailHandler
{
    /**
     * Default constructor.
     */
    public UpdateRefundAcctDetailHandler()
    {
        super();
    }
    /**
     * Constructor.
     */
    public UpdateRefundAcctDetailHandler(Record recBooking)
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
     * Get the Debit Account field.
     * @return The debit account field.
     */
    public ReferenceField getDrAccount()
    {
        return (ReferenceField)this.getProductCategory().getField(ProductCategory.AR_ACCOUNT_ID);
    }
    /**
     * Get the Credit Account field.
     * @return The credit account field.
     */
    public ReferenceField getCrAccount()
    {
        boolean bTempControl = false;
        Record recArControl = (Record)this.getOwner().getRecordOwner().getRecord(ArControl.AR_CONTROL_FILE);
        if (recArControl == null)
        {
            bTempControl = true;
            recArControl = new ArControl(this.getOwner().getRecordOwner());
        }
        ReferenceField field = (ReferenceField)recArControl.getField(ArControl.REFUND_SUSPENSE_ACCOUNT_ID);
        if (bTempControl)
            recArControl.free();
        return field;
    }

}
