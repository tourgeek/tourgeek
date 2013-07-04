/**
  * @(#)HotelAvailProcess.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.util.test.hotel.screen;

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
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.core.trx.*;

/**
 *  HotelAvailProcess - .
 */
public class HotelAvailProcess extends BaseProcess
{
    /**
     * Default constructor.
     */
    public HotelAvailProcess()
    {
        super();
    }
    /**
     * Constructor.
     */
    public HotelAvailProcess(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
     * Run Method.
     */
    public void run()
    {
        int MULTIPLIER = 250;
        for (int i = 10; i > 0; i--)
        {
            this.startProcess(HotelAvailAddProcess.class.getName(), i);
            synchronized(this)
            {
                try {
                    this.wait(i * MULTIPLIER); // Start with 2.5 seconds, and drop
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * StartProcess Method.
     */
    public void startProcess(String strProcessName, int iCount)
    {
        Map<String,Object> properties = new HashMap<String,Object>();
        properties.put(DBParams.PROCESS, strProcessName);
        properties.put(DBParams.TABLE, this.getProperty(DBParams.TABLE));
        properties.put("count", iCount);
        Application app = (Application)this.getTask().getApplication();
        String strQueueName = MessageConstants.TRX_SEND_QUEUE;
        String strQueueType = MessageConstants.INTRANET_QUEUE;
        BaseMessage message = new MapMessage(new TrxMessageHeader(strQueueName, strQueueType, properties), properties);
        
        app.getMessageManager().sendMessage(message);
    }

}
