/**
 * @(#)CarAvailRequestMessageInProcessor.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.car.request.in;

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
import com.tourapp.tour.message.base.request.in.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.car.db.*;

/**
 *  CarAvailRequestMessageInProcessor - .
 */
public class CarAvailRequestMessageInProcessor extends ProductAvailRequestMessageInProcessor
{
    /**
     * Default constructor.
     */
    public CarAvailRequestMessageInProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CarAvailRequestMessageInProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
     * Create the product record.
     * Override in the concrete classes.
     */
    public Product getProductRecord()
    {
        return new Car(this);
    }

}
