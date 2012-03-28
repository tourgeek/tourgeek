/**
 * @(#)ScfToHandler.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;

/**
 *  ScfToHandler - Validate the SCF to field.
 */
public class ScfToHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public ScfToHandler()
    {
        super();
    }
    /**
     * ScfToHandler Method.
     */
    public ScfToHandler(BaseField field)
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
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
        this.setRespondsToMode(DBConstants.INIT_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        Record record = this.getOwner().getRecord();
        String strFrom = record.getField(SCF.SCF_FROM).toString();
        String strTo =  record.getField(SCF.SCF_TO).toString();
        if ((strFrom == null) || (strFrom.length() == 0))
            strFrom = Constants.BLANK;
        if ((strTo == null) || (strTo.length() == 0))
            strTo = StringField.HIGH_STRING;
        if (strFrom.compareTo(strTo) > 0) if (record.getRecordOwner() != null)
        {
            String strError = "To SCF must be larger than or equal to From SCF";
            strError = ((BaseApplication)record.getRecordOwner().getTask().getApplication()).getResources(ResourceConstants.PROFILE_RESOURCE, true).getString(strError);
            return record.getRecordOwner().getTask().setLastError(strError);
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
