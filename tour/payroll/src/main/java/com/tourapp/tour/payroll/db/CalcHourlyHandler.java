/**
 * @(#)CalcHourlyHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.main.db.*;
import com.tourapp.tour.payroll.screen.emp.*;

/**
 *  CalcHourlyHandler - Calc hourly rate from salary.
 */
public class CalcHourlyHandler extends ReComputeFieldHandler
{
    /**
     * Default constructor.
     */
    public CalcHourlyHandler()
    {
        super();
    }
    /**
     * Constructor.
     * @param iTargetFieldSeq The target field sequence to recompute on field change.
     */
    public CalcHourlyHandler(BaseField field)
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
        String tempString = this.getOwner().getRecord().getField(Employee.PAY_FREQUENCY).getString();
        int multiplier = 0;
        if (tempString.equalsIgnoreCase("D"))
            multiplier = 365;
        if (tempString.equalsIgnoreCase("W"))
            multiplier = 52;
        if (tempString.equalsIgnoreCase("B"))
            multiplier = 26;
        if (tempString.equalsIgnoreCase("S"))
            multiplier = 24;
        if (tempString.equalsIgnoreCase("M"))
            multiplier = 12;
        if (tempString.equalsIgnoreCase("Q"))
            multiplier = 4;
        if (tempString.equalsIgnoreCase("A"))
            multiplier = 1;
        double hourly = dSrcValue * multiplier / 52 / 5 / 8;
        return hourly;
    }

}
