/**
 * @(#)PrepaymentAcctHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.prepymt;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctpay.screen.findepest.*;
import com.tourapp.tour.acctpay.screen.trx.*;
import com.tourapp.tour.base.db.*;

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
        Record recApControl = screen.getRecord(ApControl.kApControlFile);
        BaseField fldApAccountID = this.getOwner().getRecord().getField(ApTrx.kAccountID);
        if (this.getOwner().isNull())
        { // No tour, set defaults
            fldApAccountID.moveFieldToThis(recApControl.getField(ApControl.kNonTourPrepayAccountID));
        }
        else
        { // Tour set, use tour P/P account
            fldApAccountID.moveFieldToThis(recApControl.getField(ApControl.kPrepayAccountID));
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
