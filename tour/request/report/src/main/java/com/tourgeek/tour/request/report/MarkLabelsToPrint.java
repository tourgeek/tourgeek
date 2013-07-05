/**
  * @(#)MarkLabelsToPrint.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.request.report;

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
import org.jbundle.base.thread.*;
import com.tourgeek.tour.request.db.*;

/**
 *  MarkLabelsToPrint - Mark all the current labels for printing (concurrency issue)..
 */
public class MarkLabelsToPrint extends BaseProcess
{
    /**
     * Default constructor.
     */
    public MarkLabelsToPrint()
    {
        super();
    }
    /**
     * Constructor.
     */
    public MarkLabelsToPrint(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
    {
        this();
        this.init(taskParent, recordMain, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwnerParent taskParent, Record recordMain, Map<String, Object> properties)
    {
        super.init(taskParent, recordMain, properties);
    }
    /**
     * Add the behaviors.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getScreenRecord().getField(RequestLabelsScreenRecord.SEND_VIA_ID).setData(this.getProperty("sendvia"));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(Request.SEND_VIA_CODE), this.getScreenRecord().getField(RequestLabelsScreenRecord.SEND_VIA_ID), "="));
    }
    /**
     * Add the screen record.
     */
    public Record addScreenRecord()
    {
        return new RequestLabelsScreenRecord(this);
    }
    /**
     * Open the main file.
     */
    public Record openMainRecord()
    {
        return new Request(this);
    }
    /**
     * Run Method.
     */
    public void run()
    {
        Record recRequest = this.getMainRecord();
        try {
            recRequest.close();
            while (recRequest.hasNext())
            {
                recRequest.next();
                recRequest.edit();
                recRequest.getField(Request.PRINT_NOW).setState(true);
                recRequest.set();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
    }

}
