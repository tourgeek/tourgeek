/**
 * @(#)BrocBundleButton.
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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.request.db.*;

/**
 *  BrocBundleButton - Bundle button.
 */
public class BrocBundleButton extends SButtonBox
{
    protected Request m_pAmRequests = null;
    /**
     * Default constructor.
     */
    public BrocBundleButton()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BrocBundleButton(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Request pAmRequests)
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
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, "", "Bundles", null, null, null);
    }
    /**
     * Move the control's value to the field.
     * @return An error value.
     */
    public int controlToField()
    {
        super.controlToField();
        Bundle pAmBrocHist = new Bundle(null);
        BasePanel parentScreen = Screen.makeWindow(null);
        BundleList pBundleList = new BundleList(pAmBrocHist, null, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
        pBundleList.setEditing(false);
        m_pAmRequests.addDependentScreen(pBundleList);      // When this closes, closes dependent screen
        return DBConstants.NORMAL_RETURN;
    }

}
