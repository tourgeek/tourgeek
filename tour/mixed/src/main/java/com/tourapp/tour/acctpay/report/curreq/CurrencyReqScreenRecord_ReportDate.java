/**
 * @(#)CurrencyReqScreenRecord_ReportDate.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report.curreq;

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

/**
 *  CurrencyReqScreenRecord_ReportDate - .
 */
public class CurrencyReqScreenRecord_ReportDate extends DateField
{
    /**
     * Default constructor.
     */
    public CurrencyReqScreenRecord_ReportDate()
    {
        super();
    }
    /**
     * CurrencyReqScreenRecord_ReportDate Method.
     */
    public CurrencyReqScreenRecord_ReportDate(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
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
