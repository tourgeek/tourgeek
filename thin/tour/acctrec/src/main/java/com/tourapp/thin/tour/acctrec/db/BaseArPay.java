/**
 * @(#)BaseArPay.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.acctrec.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.genled.db.*;
import com.tourapp.model.tour.acctrec.db.*;

public class BaseArPay extends BaseTrx
    implements BaseArPayModel
{

    //public static final String ID = ID;
    //public static final String TRX_STATUS_ID = TRX_STATUS_ID;
    //public static final String TRX_DATE = TRX_DATE;
    public static final String AMT_APPLY = AMOUNT_LOCAL;
    //public static final String TRX_ENTRY = TRX_ENTRY;
    //public static final String TRX_USER_ID = TRX_USER_ID;

    public BaseArPay()
    {
        super();
    }
    public BaseArPay(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}
