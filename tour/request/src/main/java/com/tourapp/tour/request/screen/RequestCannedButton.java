/**
 * @(#)RequestCannedButton.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;

/**
 *  RequestCannedButton - A canned button that does the action on the agency or pax file depending on the A/P field   .
 */
public class RequestCannedButton extends SCannedBox
{
    protected BaseField m_fldAgencyOrPax = null;
    protected Record m_PaPax = null;
    /**
     * Default constructor.
     */
    public RequestCannedButton()
    {
        super();
    }
    /**
     * Constructor.
     */
    public RequestCannedButton(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, String strDesc, int iDisplayFieldDesc, Record amAgcy, Record paPax, BaseField
     fldAgencyOrPax)
    {
        this();
        this.init(itsLocation, parentScreen, fieldConverter, strDesc, iDisplayFieldDesc, amAgcy, paPax, fldAgencyOrPax);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, String strDesc, int iDisplayFieldDesc, Record amAgcy, Record paPax, BaseField
     fldAgencyOrPax)
    {
        m_fldAgencyOrPax = null;
        m_PaPax = null;
        m_fldAgencyOrPax = fldAgencyOrPax;
        m_PaPax = paPax;
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, null, null, strDesc, strDesc, null, amAgcy, null);
    }
    /**
     * Process the command.
     */
    public boolean doCommand(String strCommand, ScreenField sourceSField, int iCommandOptions)
    {
        Record record = null;
        if (m_fldAgencyOrPax != null) if (m_fldAgencyOrPax.getString().equalsIgnoreCase("P"))
        {
            record = m_record;
            m_record = m_PaPax;
        }
        boolean bFlag = super.doCommand(strCommand, sourceSField, iCommandOptions);
        if (record != null)
            m_record = record;
        return bFlag;
    }

}
