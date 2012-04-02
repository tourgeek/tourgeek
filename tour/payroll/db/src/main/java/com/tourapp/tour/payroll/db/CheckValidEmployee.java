/**
 * @(#)CheckValidEmployee.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.payroll.db;

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

/**
 *  CheckValidEmployee - Check to see this emp is valid.
 */
public class CheckValidEmployee extends FileListener
{
    /**
     * Default constructor.
     */
    public CheckValidEmployee()
    {
        super();
    }
    /**
     * CheckValidEmployee Method.
     */
    public CheckValidEmployee(BaseField empTypes)
    {
        this();
        this.init(empTypes);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField empTypes)
    {
        //?m_EmpTypes = empTypes;
        super.init(empTypes);
    }
    /**
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        super.doValidRecord(bDisplayOption);
        /* HACK
        String recordString = this.getOwner().getField(kPayFrequency).getString();
        String compareString = m_EmpTypes.getString();
        if (compareString.Find(recordString) == -1)
        {
            String errorString = "Incorrect employee period";
            ErrorDlg box(null, errorString);
            box.DoModal();
        }
        if (this.getOwner().getField(kTerminationDate).getLength() != 0)
        {
            String errorString = "Terminated employee";
            ErrorDlg box(null, errorString);
            box.DoModal();
        }
        */
    }

}
