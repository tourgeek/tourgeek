/**
  * @(#)BatchSequenceHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.genled.screen.batch;

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
import com.tourapp.tour.genled.db.*;

/**
 *  BatchSequenceHandler - .
 */
public class BatchSequenceHandler extends SequenceHandler
{
    protected BaseField m_fldBalance = null;
    /**
     * Default constructor.
     */
    public BatchSequenceHandler()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BatchSequenceHandler(BaseField fldDest, BaseField fldSource, BaseField fldBalance)
    {
        this();
        this.init(fldDest, fldSource, fldBalance);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fldDest, BaseField fldSource, BaseField fldBalance)
    {
        m_fldBalance = null;
        m_fldBalance = fldBalance;
        super.init(null, fldDest, fldSource);
    }
    /**
     * Clone Method.
     */
    public Object clone()
    {
        return new BatchSequenceHandler(m_fldDest, m_fldSource, m_fldBalance);
    }
    /**
     * Get the increment value.
     */
    public double getBumpValue()
    {
        double dIncAmount = 0;
        double dAmount = m_fldBalance.getValue();
        if (dAmount == 0)
            dIncAmount = 1;   // Bump sequence no when balance is 0.
        return dIncAmount;
    }

}
