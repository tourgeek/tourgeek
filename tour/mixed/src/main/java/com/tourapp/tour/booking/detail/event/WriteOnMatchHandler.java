/**
 * @(#)WriteOnMatchHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.detail.event;

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

/**
 *  WriteOnMatchHandler - .
 */
public class WriteOnMatchHandler extends WriteOnChangeHandler
{
    protected Object m_objToMatch = null;
    /**
     * Default constructor.
     */
    public WriteOnMatchHandler()
    {
        super();
    }
    /**
     * Write the record if this field is changed to match this data.
     */
    public WriteOnMatchHandler(Object objToMatch, boolean bRefreshAfterWrite)
    {
        this();
        this.init(objToMatch, bRefreshAfterWrite);
    }
    /**
     * Initialize class fields.
     */
    public void init(Object objToMatch, boolean bRefreshAfterWrite)
    {
        m_objToMatch = null;
        m_objToMatch = objToMatch;
        super.init(null, bRefreshAfterWrite);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        boolean bMatch = false;
        if (this.getOwner().getData() == null)
        {
            if (m_objToMatch == null)
                bMatch = true;
        }
        else if (this.getOwner().getData().equals(m_objToMatch))
            bMatch = true;
        if (bMatch)
            return super.fieldChanged(bDisplayOption, iMoveMode);
        return DBConstants.NORMAL_RETURN;
    }

}
