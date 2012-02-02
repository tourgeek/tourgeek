/**
 * @(#)CurrentUserHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.main.db.*;
import com.tourapp.tour.payroll.screen.emp.*;

/**
 *  CurrentUserHandler - Current Employee.
 */
public class CurrentUserHandler extends FieldListener
{
    protected int m_CurrentUserID;
    /**
     * Default constructor.
     */
    public CurrentUserHandler()
    {
        super();
    }
    /**
     * CurrentUserHandler Method.
     */
    public CurrentUserHandler(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        m_CurrentUserID = -1;
        super.init(field);
    }
    /**
     * GetCurrentUserID Method.
     */
    public int getCurrentUserID()
    {
        if (m_CurrentUserID == -1)
        {   // Lookup the current user (first time only)
            Employee employee = new Employee(null);
            int errorCode = employee.getCurrentUser();
            if (errorCode == DBConstants.NORMAL_RETURN)
                m_CurrentUserID = (int)employee.getField(Employee.ID).getValue();
            else
                m_CurrentUserID = 0;    // Not found in list
            employee.free();
            employee = null;
        }
        return m_CurrentUserID;
    }

}
