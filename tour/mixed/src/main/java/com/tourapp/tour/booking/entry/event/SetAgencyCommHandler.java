/**
 * @(#)SetAgencyCommHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.event;

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
import com.tourapp.tour.profile.db.*;

/**
 *  SetAgencyCommHandler - Set up the agency commission %.
 */
public class SetAgencyCommHandler extends FileListener
{
    protected Record m_pAmAffil = null;
    protected BaseField m_pBookingComm = null;
    /**
     * Default constructor.
     */
    public SetAgencyCommHandler()
    {
        super();
    }
    /**
     * SetAgencyCommHandler Method.
     */
    public SetAgencyCommHandler(BaseField pBookingComm)
    {
        this();
        this.init(pBookingComm);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField pBookingComm)
    {
        m_pAmAffil = null;
        m_pBookingComm = null;
        m_pBookingComm = pBookingComm;
        super.init(null);
    }
    /**
     * Set the object that owns this listener.
     * @owner The object that this listener is being added to (if null, this listener is being removed).
     */
    public void setOwner(ListenerOwner owner)
    {
        super.setOwner(owner);
        if (owner != null)
        {
            m_pBookingComm.addListener(new FieldRemoveBOnCloseHandler(this));
            m_pAmAffil = new Affiliation(null);   // Make sure close in screen change
            FileListener pSecondary = new DisplayReadHandler(Profile.AFFILIATION_ID, m_pAmAffil, Affiliation.ID);
            Record pAmAgcy = this.getOwner();
            pAmAgcy.addListener(pSecondary);
        }
        else
        {
            if (m_pAmAffil != null)
            {
                m_pAmAffil.free();
                m_pAmAffil = null;
            }
        }
    }
    /**
     * Called when a new blank record is required for the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doNewRecord(boolean bDisplayOption)
    {
        super.doNewRecord(bDisplayOption);
        m_pBookingComm.initField(bDisplayOption);
    }
    /**
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        super.doValidRecord(bDisplayOption);
        m_pBookingComm.moveFieldToThis(m_pAmAffil.getField(Affiliation.AGENT_COMM), bDisplayOption, DBConstants.SCREEN_MOVE);
    }

}
