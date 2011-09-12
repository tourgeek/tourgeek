/**
 * @(#)SetupBrocDetailHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.request.screen;

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
import com.tourapp.tour.request.db.*;
import com.tourapp.tour.base.db.shared.*;

/**
 *  SetupBrocDetailHandler - .
 */
public class SetupBrocDetailHandler extends FileListener
{
    protected BundleDetail m_rAmBrocDetail = null;
    protected Brochure m_rAmBrochure = null;
    protected RequestDetail m_rAmReqDetail = null;
    protected RequestInput m_rAmReqInput = null;
    protected Request m_rAmRequests = null;
    /**
     * Default constructor.
     */
    public SetupBrocDetailHandler()
    {
        super();
    }
    /**
     * SetupBrocDetailHandler Method.
     */
    public SetupBrocDetailHandler(Request recRequests, RequestDetail recReqDetail, BundleDetail recBrocDetail, Brochure recBrochure, RequestInput recReqInput)
    {
        this();
        this.init(recRequests, recReqDetail, recBrocDetail, recBrochure, recReqInput);
    }
    /**
     * Initialize class fields.
     */
    public void init(Request recRequests, RequestDetail recReqDetail, BundleDetail recBrocDetail, Brochure recBrochure, RequestInput recReqInput)
    {
        m_rAmBrocDetail = null;
        m_rAmBrochure = null;
        m_rAmReqDetail = null;
        m_rAmReqInput = null;
        m_rAmRequests = null;
        m_rAmRequests = recRequests;
        m_rAmReqDetail = recReqDetail;
        m_rAmBrocDetail = recBrocDetail;
        m_rAmBrochure = recBrochure;
        m_rAmReqInput = recReqInput;
        super.init(null);
    }
    /**
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        m_rAmReqInput.close();
        try   {
            while (m_rAmReqInput.next() != null)
            {
                m_rAmReqInput.remove();
            }
            m_rAmReqDetail.close();
            while (m_rAmReqDetail.next() != null)
            {
                m_rAmReqInput.addNew();
                m_rAmReqInput.getField(RequestInput.kBrochureID).moveFieldToThis(m_rAmReqDetail.getField(RequestDetail.kBrochureID), DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                m_rAmReqInput.getField(RequestInput.kBrochureQty).moveFieldToThis(m_rAmReqDetail.getField(RequestDetail.kBrochureQty), DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                m_rAmReqInput.add();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        super.doValidRecord(bDisplayOption);
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
        if ((iChangeType == DBConstants.AFTER_UPDATE_TYPE)
            || (iChangeType == DBConstants.AFTER_ADD_TYPE))
        {
            try   {
                m_rAmReqDetail.close();
                while (m_rAmReqDetail.next() != null)
                {
                    m_rAmReqDetail.remove();
                }
                m_rAmReqInput.close();
                while (m_rAmReqInput.next() != null)
                {
                    if (m_rAmReqInput.getField(RequestInput.kBrochureQty).getValue() > 0)
                    {
                        m_rAmReqDetail.addNew();
                        ((ReferenceField)m_rAmReqDetail.getField(RequestDetail.kRequestID)).setReference(m_rAmRequests, DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                        m_rAmReqDetail.getField(RequestDetail.kBrochureID).moveFieldToThis(m_rAmReqInput.getField(RequestInput.kBrochureID), DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                        m_rAmReqDetail.getField(RequestDetail.kBrochureQty).moveFieldToThis(m_rAmReqInput.getField(RequestInput.kBrochureQty), DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                        m_rAmReqDetail.add();
                    }
                }
            } catch (DBException e)   {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        
        return super.doRecordChange(field, iChangeType, bDisplayOption);
    }

}
