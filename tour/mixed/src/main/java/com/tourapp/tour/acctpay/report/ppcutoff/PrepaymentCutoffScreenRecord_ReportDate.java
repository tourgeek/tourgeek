/**
 *  @(#)PrepaymentCutoffScreenRecord_ReportDate.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report.ppcutoff;

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

/**
 *  PrepaymentCutoffScreenRecord_ReportDate - .
 */
public class PrepaymentCutoffScreenRecord_ReportDate extends DateField
{
    /**
     * Default constructor.
     */
    public PrepaymentCutoffScreenRecord_ReportDate()
    {
        super();
    }
    /**
     * PrepaymentCutoffScreenRecord_ReportDate Method.
     */
    public PrepaymentCutoffScreenRecord_ReportDate(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
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
