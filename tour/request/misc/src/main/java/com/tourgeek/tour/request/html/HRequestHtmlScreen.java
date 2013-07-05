/**
  * @(#)HRequestHtmlScreen.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.request.html;

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
import org.jbundle.base.screen.view.html.*;
import java.io.*;
import org.jbundle.base.screen.control.servlet.*;

/**
 *  HRequestHtmlScreen - This is the view for the reqest HTML screen..
 */
public class HRequestHtmlScreen extends HScreen
{
    /**
     * Default constructor.
     */
    public HRequestHtmlScreen()
    {
        super();
    }
    /**
     * HRequestHtmlScreen Method.
     */
    public HRequestHtmlScreen(ScreenField model, boolean bEditableControl)
    {
        this();
        this.init(model, bEditableControl);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenField model, boolean bEditableControl)
    {
        super.init(model, bEditableControl);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "This is the view for the reqest HTML screen.";
    }
    /**
     * Get the print options (view defined).
     * @return The HTML options.
     * @exception DBException File exception.
     */
    public int getPrintOptions() throws DBException
    {
        int iToolbarOptions = super.getPrintOptions();
        if (this.getScreenField().isEnabled() == false)     // For display - no toolbars
            iToolbarOptions = iToolbarOptions & ~(HtmlConstants.PRINT_TOOLBAR_BEFORE | HtmlConstants.PRINT_TOOLBAR_BEFORE);
        return iToolbarOptions;
    }
    /**
     * Display this screen's toolbars in print format.
     * @param out print stream.
     * @param iPrintOptions The view specific options.
     * @exception DBException File exception.
     */
    public boolean printZmlToolbarData(PrintWriter out, int iHtmlOptions)
    {
        boolean bFieldsFound = super.printZmlToolbarData(out, iHtmlOptions);
        
        String strAgentParam = this.getProperty("agent");
        if (strAgentParam == null)
            strAgentParam = "no";
        BasePanel screen = (BasePanel)this.getScreenField();
        String strURL = HtmlConstants.SERVLET_LINK + screen.getScreenURL();
        if (strAgentParam.equalsIgnoreCase("yes"))
            strURL = screen.addURLParam(strURL, "agent", "no");
        else
            strURL = screen.addURLParam(strURL, "agent", "yes");
        if (strAgentParam.equalsIgnoreCase("yes"))
            out.println("<br/><a href=\"" + strURL + "\">If you are <b>not</b> a travel agent, click here</a>");
        else
            out.println("<br/><a href=\"" + strURL + "\">If you are a travel agent, click here</a>");
        bFieldsFound = true;
        
        return bFieldsFound;
    }
    /**
     * ProcessServletCommand Method.
     */
    public boolean processServletCommand() throws DBException
    {
        boolean bDefaultParamsFound = super.processServletCommand();
        String strError = this.getTask().getStatusText(DBConstants.INFORMATION);
        if (bDefaultParamsFound)
            if ((strError == null) || (strError.length() == 0))
        {
            String strStatus = HtmlConstants.SERVLET_LINK + "?menu=";
            strStatus = "<a href=" + strStatus + ">Click here to return to the main menu</a>.";
            this.getTask().setStatusText(strStatus, DBConstants.INFORMATION);
            this.getScreenField().setEnabled(false);        // Display screen!
        }
        return bDefaultParamsFound;
    }
    /**
     * Get this screen's hidden params.
     * @returns a map of the hidden params.
     */
    public Map<String,Object> getHiddenParams()
    {
        Map<String,Object> mapParams = super.getHiddenParams();
        String key = "agent";
        String value = this.getProperty(key);
        if (value == null)
            value = "no";
        mapParams.put(key, value);
        return mapParams;
    }

}
