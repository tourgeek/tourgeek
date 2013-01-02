/**
  * @(#)PrepaymentAcctHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctpay.db.event;

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
import com.tourapp.tour.acctpay.db.*;

/**
 *  PrepaymentAcctHandler - Default to the correct prepayment account depending on tour/non-tour.
 */
public class PrepaymentAcctHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public PrepaymentAcctHandler()
    {
        super();
    }
    /**
     * PrepaymentAcctHandler Method.
     */
    public PrepaymentAcctHandler(BaseField field)
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
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        RecordOwner screen = this.getOwner().getRecord().getRecordOwner();
        Record recApControl = (Record)screen.getRecord(ApControl.AP_CONTROL_FILE);
        BaseField fldApAccountID = this.getOwner().getRecord().getField(ApTrx.ACCOUNT_ID);
        if (this.getOwner().isNull())
        { // No tour, set defaults
            fldApAccountID.moveFieldToThis(recApControl.getField(ApControl.NON_TOUR_PREPAY_ACCOUNT_ID));
        }
        else
        { // Tour set, use tour P/P account
            fldApAccountID.moveFieldToThis(recApControl.getField(ApControl.PREPAY_ACCOUNT_ID));
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
