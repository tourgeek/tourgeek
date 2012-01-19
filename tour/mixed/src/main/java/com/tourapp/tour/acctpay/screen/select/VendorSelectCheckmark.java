/**
 * @(#)VendorSelectCheckmark.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.select;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.acctpay.screen.trx.*;
import com.tourapp.tour.acctpay.db.*;

/**
 *  VendorSelectCheckmark - If any amount selected, display check; if checked, move amount to selected.
 */
public class VendorSelectCheckmark extends CheckConverter
{
    /**
     * Default constructor.
     */
    public VendorSelectCheckmark()
    {
        super();
    }
    /**
     * Constructor.
     */
    public VendorSelectCheckmark(Converter field, String strTargetValue, String strAltFieldDesc, boolean bTrueIfMatch)
    {
        this();
        this.init(field, strTargetValue, strAltFieldDesc, bTrueIfMatch);
    }
    /**
     * Initialize class fields.
     */
    public void init(Converter field, String strTargetValue, String strAltFieldDesc, boolean bTrueIfMatch)
    {
        super.init(field, strTargetValue, null, strAltFieldDesc, bTrueIfMatch, null);
    }
    /**
     * Set the state of this field for binary fields (don't override this).
     * Usually overidden.
     * @param bState the state to set the data to.
     * @param bDisplayOption Display the data on the screen if true.
     * @param iMoveMode INIT, SCREEN, or READ move mode.
     * @return The error code.
     */
    public int setState(boolean bState, boolean bDisplayOption, int iMoveMode)
    {
        int iErrorCode = super.setState(bState, bDisplayOption, iMoveMode);
        if (iErrorCode == DBConstants.NORMAL_RETURN)
            if (bState == true) if (this.getState() == false)
                ((BaseField)this.getField()).moveFieldToThis(((BaseField)this.getField()).getRecord().getField(ApTrx.kInvoiceBalance));
        return iErrorCode;
    }

}
