/**
 * @(#)TimeTrxScreenRecord_PayEndingDate.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.payroll.report.payroll;

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

/**
 *  TimeTrxScreenRecord_PayEndingDate - .
 */
public class TimeTrxScreenRecord_PayEndingDate extends DateField
{
    /**
     * Default constructor.
     */
    public TimeTrxScreenRecord_PayEndingDate()
    {
        super();
    }
    /**
     * TimeTrxScreenRecord_PayEndingDate Method.
     */
    public TimeTrxScreenRecord_PayEndingDate(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize the field.
     */
    public int initField(boolean displayOption)
    {
        return this.setValue(todaysDate(), displayOption, DBConstants.INIT_MOVE);
    }

}
