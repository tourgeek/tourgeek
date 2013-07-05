/**
  * @(#)BrocHistButton.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.request.screen;

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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import com.tourgeek.tour.request.db.*;

/**
 *  BrocHistButton - Brochure History.
 */
public class BrocHistButton extends SButtonBox
{
    protected Request m_pAmRequests = null;
    /**
     * Default constructor.
     */
    public BrocHistButton()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BrocHistButton(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Request pAmRequests)
    {
        this();
        this.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, pAmRequests);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Request pAmRequests)
    {
        m_pAmRequests = null;
        m_pAmRequests = pAmRequests;
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, null, "Brochure history", null, null, null);
    }
    /**
     * Move the control's value to the field.
     * @return An error value.
     */
    public int controlToField()
    {
        int iErrorCode = super.controlToField();
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        BasePanel parentScreen = Screen.makeWindow(null);
        
        HistoryDisplay pAmBrocHist = new HistoryDisplay(null);
        ScreenLocation itsLocation = null;
        BrocHistList pBrocHistList = new BrocHistList(pAmBrocHist, itsLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_DESC, null);
        pBrocHistList.setEditing(false);
        m_pAmRequests.addDependentScreen(pBrocHistList);        // When this closes, closes dependent screen
        
        //xFileBehavior pSubFileBeh = new SubFileFilter(m_pAmRequests.getField(Request.USE_AGENCY), RequestHistory.USE_AGENCY, m_pAmRequests.getField(Request.PROFILE_ID), RequestHistory.AGENCY_NO, null, -1);
        //xpAmBrocHist.addListener(pSubFileBeh);
        
        m_pAmRequests.getField(Request.PROFILE_CODE).addListener(new FieldReSelectHandler(pBrocHistList));  // Reselect on file change
        
        return iErrorCode;
    }

}
