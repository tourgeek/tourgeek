/**
 * @(#)GenledScreenRecord_ReportTime.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.report;

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
 *  GenledScreenRecord_ReportTime - .
 */
public class GenledScreenRecord_ReportTime extends TimeField
{
    /**
     * Default constructor.
     */
    public GenledScreenRecord_ReportTime()
    {
        super();
    }
    /**
     * GenledScreenRecord_ReportTime Method.
     */
    public GenledScreenRecord_ReportTime(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize the field.
     */
    public int initField(boolean displayOption)
    {
        return this.setValue(currentTime(), displayOption, DBConstants.INIT_MOVE);
    }

}
