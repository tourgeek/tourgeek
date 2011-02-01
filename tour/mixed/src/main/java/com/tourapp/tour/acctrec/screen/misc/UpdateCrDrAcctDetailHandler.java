/**
 *  @(#)UpdateCrDrAcctDetailHandler.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.misc;

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
import com.tourapp.tour.booking.entry.acctrec.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctrec.db.event.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  UpdateCrDrAcctDetailHandler - Update the G/L detail when a Debit/Credit or Refund is written.
 */
public class UpdateCrDrAcctDetailHandler extends UpdateArTrxAcctDetailHandler
{
    /**
     * Default constructor.
     */
    public UpdateCrDrAcctDetailHandler()
    {
        super();
    }
    /**
     * Constructor.
     */
    public UpdateCrDrAcctDetailHandler(Record recBooking)
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
        ReferenceField fldCrAccount = (ReferenceField)this.getOwner().getRecordOwner().getScreenRecord().getField(CrDrScreenRecord.kCounterAccountID);
        if (fldCrAccount.isNull())
            fldCrAccount = (ReferenceField)this.getOwner().getRecordOwner().getRecord(ArControl.kArControlFile).getField(ArControl.kCreditDebitAccountID);
        return fldCrAccount;
    }
    /**
     * Get the Debit Account field.
     * @return The debit account field.
     */
    public ReferenceField getDrAccount()
    {
        return (ReferenceField)this.getProductCategory().getField(ProductCategory.kArAccountID);
    }

}
