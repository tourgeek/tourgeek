/**
 * @(#)TicketTrx_TrxUserID.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.db;

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
import org.jbundle.main.user.db.*;

/**
 *  TicketTrx_TrxUserID - .
 */
public class TicketTrx_TrxUserID extends UserField
{
    /**
     * Default constructor.
     */
    public TicketTrx_TrxUserID()
    {
        super();
    }
    /**
     * TicketTrx_TrxUserID Method.
     */
    public TicketTrx_TrxUserID(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize the field.
     */
    public int initField(boolean displayOption)
    {
        return this.setValue(getUserID(), displayOption, DBConstants.INIT_MOVE);
    }

}
