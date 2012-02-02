/**
 * @(#)BrokerScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.broker;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.screen.trx.*;
import com.tourapp.tour.base.db.*;
import org.jbundle.main.screen.*;

/**
 *  BrokerScreenRecord - .
 */
public class BrokerScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String VENDOR_ID = "VendorID";
    public static final int kVendorID = kScreenRecordLastField + 1;
    public static final int kBrokerScreenRecordLastField = kVendorID;
    public static final int kBrokerScreenRecordFields = kVendorID - DBConstants.MAIN_FIELD + 1;
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

    public static final String kBrokerScreenRecordFile = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kVendorID)
            field = new VendorField(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBrokerScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}
