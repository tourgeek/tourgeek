/**
 * @(#)EnableScreenHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.findepest;

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
 *  EnableScreenHandler - Disable the screen if the valid record matches any of these values..
 */
public class EnableScreenHandler extends FileListener
{
    public static final int DEFAULT_SIZE = 16;
    protected String m_fsFieldSeq = null;
    protected int m_iMaxSize = DEFAULT_SIZE;
    protected Object[] m_rgObject = null;
    /**
     * Default constructor.
     */
    public EnableScreenHandler()
    {
        super();
    }
    /**
     * EnableScreenHandler Method.
     */
    public EnableScreenHandler(String fsFieldSeq)
    {
        this();
        this.init(fsFieldSeq);
    }
    /**
     * Initialize class fields.
     */
    public void init(String fsFieldSeq)
    {
        m_fsFieldSeq = fsFieldSeq;
        super.init(null);
    }
    /**
     * EnableScreenHandler Method.
     */
    public EnableScreenHandler(String fsFieldSeq, int iMaxSize)
    {
        this();
        this.init(fsFieldSeq, iMaxSize);
    }
    /**
     * Initialize class fields.
     */
    public void init(String fsFieldSeq, int iMaxSize)
    {
        m_iMaxSize = iMaxSize;
        m_fsFieldSeq = fsFieldSeq;
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
            m_rgObject = new Object[m_iMaxSize];
            for (int i = 0; i < m_rgObject.length; i++)
            {
                m_rgObject[i] = null;
            }
        }
    }
    /**
     * Free Method.
     */
    public void free()
    {
        m_rgObject = null;
        super.free();
    }
    /**
     * AddComparison Method.
     */
    public void addComparison(Object objValue)
    {
        for (int i = 0; i < m_rgObject.length; i++)
        {
            if (m_rgObject[i] == null)
            {
                m_rgObject[i] = objValue;
                return;
            }
        }
    }
    /**
     * Called when a new blank record is required for the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doNewRecord(boolean bDisplayOption)
    {
        this.setEnabled(true);
        super.doNewRecord(bDisplayOption);
    }
    /**
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        // Only allow fin estimates to be changed
        BaseField field = this.getOwner().getField(m_fsFieldSeq);
        boolean bEnabled = false;
        for (int i = 0; i < m_rgObject.length; i++)
        {
            if (m_rgObject[i] != null)
            {
                if (m_rgObject[i].equals(field.getData()))
                {
                    bEnabled = true;
                    break;
                }
            }
        }
        this.setEnabled(bEnabled);
        super.doValidRecord(bDisplayOption);
    }
    /**
     * SetEnabled Method.
     */
    public void setEnabled(boolean bEnable)
    {
        BasePanel screen = (BasePanel)this.getOwner().getRecordOwner();
        screen.setEnabled(bEnable);
    }

}
