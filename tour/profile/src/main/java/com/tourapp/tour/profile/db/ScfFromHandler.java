/**
 * @(#)ScfFromHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.profile.db;

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

/**
 *  ScfFromHandler - Validate the SCF from field.
 */
public class ScfFromHandler extends MainFieldHandler
{
    /**
     * Default constructor.
     */
    public ScfFromHandler()
    {
        super();
    }
    /**
     * ScfFromHandler Method.
     */
    public ScfFromHandler(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        super.init(field, SCF.SCF_TO, -1);
    }
    /**
     * Find the record.
     */
    public boolean seek(Record record) throws DBException
    {
        String strFromTarget = record.getField(SCF.SCF_FROM).toString();
        record.getField(SCF.SCF_TO).moveFieldToThis(record.getField(SCF.SCF_FROM), DBConstants.DONT_DISPLAY, DBConstants.READ_MOVE);
        record.getField(SCF.SCF_FROM).setString(Constants.BLANK, DBConstants.DONT_DISPLAY, DBConstants.READ_MOVE);
        boolean bSuccess = record.seek(">=");
        if (bSuccess)
        {
            String strFrom = record.getField(SCF.SCF_FROM).toString();
            String strTo = record.getField(SCF.SCF_TO).toString();
            if ((strFrom == null) || (strFrom.length() == 0))
                strFrom = Constants.BLANK;
            if ((strTo == null) || (strTo.length() == 0))
                strTo = Constants.BLANK;
            if ((strFromTarget.compareTo(strFrom) >= 0) && (strFromTarget.compareTo(strTo) <= 0))
                bSuccess = true;    // Success
            else
                bSuccess = false;
        }
        return bSuccess;
    }

}
