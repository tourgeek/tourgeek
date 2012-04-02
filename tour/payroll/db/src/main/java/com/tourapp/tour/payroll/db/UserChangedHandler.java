/**
 * @(#)UserChangedHandler.
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
import org.jbundle.main.db.*;

/**
 *  UserChangedHandler - Set user ID on record change.
 */
public class UserChangedHandler extends FileListener
{
    protected long m_CurrentUserID;
    protected int m_UserIDFieldSeq;
    /**
     * Default constructor.
     */
    public UserChangedHandler()
    {
        super();
    }
    /**
     * UserChangedHandler Method.
     */
    public UserChangedHandler(int fsUserID)
    {
        this();
        this.init(fsUserID);
    }
    /**
     * Initialize class fields.
     */
    public void init(int fsUserID)
    {
        m_CurrentUserID = -1;
        m_UserIDFieldSeq = 0;
        m_CurrentUserID = fsUserID;
        super.init(null);
    }
    /**
     * Called when a change is the record status is about to happen/has happened.
     * @param field If this file change is due to a field, this is the field.
     * @param iChangeType The type of change that occurred.
     * @param bDisplayOption If true, display any changes.
     * @return an error code.
     * ADD_TYPE - Before a write.
     * UPDATE_TYPE - Before an update.
     * DELETE_TYPE - Before a delete.
     * AFTER_UPDATE_TYPE - After a write or update.
     * LOCK_TYPE - Before a lock.
     * SELECT_TYPE - After a select.
     * DESELECT_TYPE - After a deselect.
     * MOVE_NEXT_TYPE - After a move.
     * AFTER_REQUERY_TYPE - Record opened.
     * SELECT_EOF_TYPE - EOF Hit.
     */
    public int doRecordChange(FieldInfo field, int iChangeType, boolean bDisplayOption)
    {
        if (m_CurrentUserID == -1)
        { // First time through
            CurrentUserHandler cCurrentUserBehavior = new CurrentUserHandler();
            m_CurrentUserID = cCurrentUserBehavior.getCurrentUserID();
            cCurrentUserBehavior.free();
            cCurrentUserBehavior = null;
        }
        switch (iChangeType)
        {
            case DBConstants.ADD_TYPE:
            case DBConstants.UPDATE_TYPE:
                IntegerField thisField = (IntegerField)this.getOwner().getField(m_UserIDFieldSeq);
                thisField.setValue(m_CurrentUserID, bDisplayOption, DBConstants.SCREEN_MOVE);   // File written or updated, set the update date
                break;
        }
        return super.doRecordChange(field, iChangeType, bDisplayOption);
    }

}
