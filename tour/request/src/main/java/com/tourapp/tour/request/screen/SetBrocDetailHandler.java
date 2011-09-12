/**
 * @(#)SetBrocDetailHandler.
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
 *  SetBrocDetailHandler - .
 */
public class SetBrocDetailHandler extends FieldReSelectHandler
{
    protected RequestScreen m_mMAmRequests = null;
    protected BundleDetail m_rAmBrocDetail = null;
    protected Brochure m_rAmBrochure = null;
    protected RequestDetail m_rAmReqDetail = null;
    protected RequestInput m_rAmReqInput = null;
    protected Request m_rAmRequests = null;
    /**
     * Default constructor.
     */
    public SetBrocDetailHandler()
    {
        super();
    }
    /**
     * SetBrocDetailHandler Method.
     */
    public SetBrocDetailHandler(RequestScreen mAmRequests, GridScreen gridScreen, Request rAmRequests, RequestDetail rAmReqDetail, BundleDetail rAmBrocDetail, Brochure recBrochure, RequestInput
     rAmReqInput)
    {
        this();
        this.init(mAmRequests, gridScreen, rAmRequests, rAmReqDetail, rAmBrocDetail, recBrochure, rAmReqInput);
    }
    /**
     * Initialize class fields.
     */
    public void init(RequestScreen mAmRequests, GridScreen gridScreen, Request rAmRequests, RequestDetail rAmReqDetail, BundleDetail rAmBrocDetail, Brochure recBrochure, RequestInput
     rAmReqInput)
    {
        m_mMAmRequests = null;
        m_rAmBrocDetail = null;
        m_rAmBrochure = null;
        m_rAmReqDetail = null;
        m_rAmReqInput = null;
        m_rAmRequests = null;
        m_rAmRequests = rAmRequests;
        m_rAmReqDetail = rAmReqDetail;
        m_rAmBrocDetail = rAmBrocDetail;
        m_rAmBrochure = recBrochure;
        m_rAmReqInput = rAmReqInput;
        m_mMAmRequests = mAmRequests;
        super.init(null, gridScreen, null);
        // Only if the use changes the qty or bundle
        this.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        m_mMAmRequests.setupBrocDetail(m_rAmRequests, m_rAmReqDetail, m_rAmBrocDetail, m_rAmBrochure, m_rAmReqInput);
        
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}
