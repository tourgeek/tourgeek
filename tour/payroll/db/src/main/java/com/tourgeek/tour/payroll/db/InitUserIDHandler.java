/**
  * @(#)InitUserIDHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.payroll.db;

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
import org.jbundle.main.db.*;

/**
 *  InitUserIDHandler - Init this field to the user ID.
 */
public class InitUserIDHandler extends CurrentUserHandler
{
    /**
     * Default constructor.
     */
    public InitUserIDHandler()
    {
        super();
    }
    /**
     * InitUserIDHandler Method.
     */
    public InitUserIDHandler(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        super.init(field);
        
        m_bScreenMove = false;
        m_bInitMove = true;     // Only respond to init
        m_bReadMove = false;
    }
    /**
     * DoSetData Method.
     */
    public int doSetData(Object objData, boolean bDisplayOption, int iMoveMode)
    {
        int userID = this.getCurrentUserID();   // Only move the userID!!!
        return super.doSetData(objData, bDisplayOption, iMoveMode);
    }

}
