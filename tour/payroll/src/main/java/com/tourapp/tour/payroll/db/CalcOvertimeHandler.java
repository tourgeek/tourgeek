/**
 * @(#)CalcOvertimeHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.payroll.db;

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
import org.jbundle.main.db.*;
import com.tourapp.tour.payroll.screen.emp.*;

/**
 *  CalcOvertimeHandler - Calc overtime field.
 */
public class CalcOvertimeHandler extends ReComputeFieldHandler
{
    /**
     * Default constructor.
     */
    public CalcOvertimeHandler()
    {
        super();
    }
    /**
     * Constructor.
     * @param iTargetFieldSeq The target field sequence to recompute on field change.
     */
    public CalcOvertimeHandler(BaseField field)
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
        
        m_bInitMove = false;        // Only respond to screen
        m_bReadMove = false;
    }
    /**
     * Compute the target value.
     * @param srcValue The value of this listener's owner.
     * @return The value to set the target field to.
     */
    public double computeValue(double dSrcValue)
    {
        return dSrcValue * 1.5;
    }

}
