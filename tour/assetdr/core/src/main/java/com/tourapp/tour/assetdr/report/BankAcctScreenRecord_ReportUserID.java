/**
 * @(#)BankAcctScreenRecord_ReportUserID.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.report;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.main.user.db.*;

/**
 *  BankAcctScreenRecord_ReportUserID - .
 */
public class BankAcctScreenRecord_ReportUserID extends UserField
{
    /**
     * Default constructor.
     */
    public BankAcctScreenRecord_ReportUserID()
    {
        super();
    }
    /**
     * BankAcctScreenRecord_ReportUserID Method.
     */
    public BankAcctScreenRecord_ReportUserID(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
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
