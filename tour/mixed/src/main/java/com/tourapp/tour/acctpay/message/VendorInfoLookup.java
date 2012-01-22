/**
 * @(#)VendorInfoLookup.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.message;

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
import org.jbundle.base.message.trx.processor.*;
import com.tourapp.tour.acctpay.db.*;
import org.jbundle.thin.base.message.*;

/**
 *  VendorInfoLookup - .
 */
public class VendorInfoLookup extends BaseMessageProcessor
{
    /**
     * Default constructor.
     */
    public VendorInfoLookup()
    {
        super();
    }
    /**
     * Constructor.
     */
    public VendorInfoLookup(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
     * Open the main file.
     */
    public Record openMainRecord()
    {
        return new Vendor(this);
    }
    /**
     * Process this internal message.
     * @param internalMessage The message to process.
     * @return (optional) The return message if applicable.
     */
    public BaseMessage processMessage(BaseMessage internalMessage)
    {
        String strRoomType = (String)internalMessage.get("rateType");
        String strRoomClass = (String)internalMessage.get("roomClass");
        // Add code to lookup hotel rate here
        try   {
        java.lang.Thread.currentThread().sleep(5000);   // Simulate time for message turnaround
        } catch (InterruptedException ex) {
        }
        BaseMessage replyMessage = new MapMessage();
        replyMessage.put(DBParams.FIELD, "multipleFields");
        replyMessage.put("CityOrTown", "Los Angeles");
        replyMessage.put("Contact", "Fred");
        
        //?        this.sendMessage(new BaseMessage(null, replyMessage));
        
        return null;    // No reply expected
    }

}
