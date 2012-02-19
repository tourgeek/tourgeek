/**
 * @(#)ForceSignHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctpay.screen.findepest.*;
import com.tourapp.tour.acctpay.screen.trx.*;
import com.tourapp.tour.base.db.*;

/**
 *  ForceSignHandler - Force this field to be positive or negative..
 */
public class ForceSignHandler extends ReComputeFieldHandler
{
    protected int m_iTargetSign = 1;
    /**
     * Default constructor.
     */
    public ForceSignHandler()
    {
        super();
    }
    /**
     * Constructor.
     * 1 = Sign forced to positive
     * -1 = Sign is forced to negative.
     */
    public ForceSignHandler(int iTargetSign)
    {
        this();
        this.init(iTargetSign);
    }
    /**
     * Initialize class fields.
     */
    public void init(int iTargetSign)
    {
        super.init(null, -1, null, null);
        if (iTargetSign != -1)
            iTargetSign = 1;
        m_iTargetSign = iTargetSign;
    }
    /**
     * Compute the target value.
     * @param srcValue The value of this listener's owner.
     * @return The value to set the target field to.
     */
    public double computeValue(double dSrcValue)
    {
        return Math.abs(dSrcValue) * m_iTargetSign;   // Force sign
    }

}
