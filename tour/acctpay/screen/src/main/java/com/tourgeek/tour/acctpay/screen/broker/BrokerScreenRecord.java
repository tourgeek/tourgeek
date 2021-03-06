/**
  * @(#)BrokerScreenRecord.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctpay.screen.broker;

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
import org.jbundle.base.screen.model.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.genled.db.*;
import com.tourgeek.tour.acctpay.screen.trx.*;
import com.tourgeek.tour.base.db.*;
import org.jbundle.main.screen.*;
import org.jbundle.base.screen.model.util.*;

/**
 *  BrokerScreenRecord - .
 */
public class BrokerScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String VENDOR_ID = "VendorID";
    /**
     * Default constructor.
     */
    public BrokerScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BrokerScreenRecord(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }

    public static final String BROKER_SCREEN_RECORD_FILE = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new VendorField(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}
