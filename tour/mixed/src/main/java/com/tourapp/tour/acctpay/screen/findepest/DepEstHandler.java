/**
 * @(#)DepEstHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.findepest;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.screen.trx.*;

/**
 *  DepEstHandler - Make sure this is a Fin Est or Manual Dep Est, and change to Dep Est.
 */
public class DepEstHandler extends FileListener
{
    /**
     * Default constructor.
     */
    public DepEstHandler()
    {
        super();
    }
    /**
     * DepEstHandler Method.
     */
    public DepEstHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(record);
    }

}
