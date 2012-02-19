/**
 * @(#)ProductMessageInErrorProcessor.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.base.error;

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
import org.jbundle.thin.base.message.*;

/**
 *  ProductMessageInErrorProcessor - .
 */
public class ProductMessageInErrorProcessor extends ProductMessageErrorProcessor
{
    /**
     * Default constructor.
     */
    public ProductMessageInErrorProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductMessageInErrorProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
     * Process this internal message.
     * @param internalMessage The message to process.
     * @return (optional) The return message if applicable.
     */
    public BaseMessage processMessage(BaseMessage internalMessage)
    {
        return super.processMessage(internalMessage);
    }

}
