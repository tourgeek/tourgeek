/**
 * @(#)RecordMapTrxMessage.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.util.test.hotel;

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
import org.jbundle.thin.base.message.*;

/**
 *  RecordMapTrxMessage - .
 */
public class RecordMapTrxMessage extends MapMessage
{
    /**
     * Default constructor.
     */
    public RecordMapTrxMessage()
    {
        super();
    }
    /**
     * RecordMapTrxMessage Method.
     */
    public RecordMapTrxMessage(BaseMessageHeader messageHeader, Object mapMessage)
    {
        this();
        this.init(messageHeader, mapMessage);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseMessageHeader messageHeader, Object data)
    {
        super.init(messageHeader, data);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        super.free();
    }
    /**
     * Utility method to add the data in this screen field to this key in the property object.
     */
    public void addRecordProperty(String strKey, Record record, int iFieldSeq)
    {
        this.put(strKey, record.getField(iFieldSeq).getData());
    }

}
