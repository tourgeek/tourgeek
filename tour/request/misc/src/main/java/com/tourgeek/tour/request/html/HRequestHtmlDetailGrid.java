/**
  * @(#)HRequestHtmlDetailGrid.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.screen.view.html.*;
import org.jbundle.base.screen.model.*;
import com.tourgeek.tour.request.db.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import org.jbundle.base.screen.view.*;
import com.tourgeek.tour.request.screen.*;
import com.tourgeek.tour.base.db.shared.*;

/**
 *  HRequestHtmlDetailGrid - .
 */
public class HRequestHtmlDetailGrid extends HGridScreen
{
    /**
     * Default constructor.
     */
    public HRequestHtmlDetailGrid()
    {
        super();
    }
    /**
     * HRequestHtmlDetailGrid Method.
     */
    public HRequestHtmlDetailGrid(ScreenField model, boolean bEditableControl)
    {
        this();
        this.init(model, bEditableControl);
    }
    /**
     * Display this sub-control in html input format?
     * @param iPrintOptions The view specific print options.
     * @return True if this sub-control is printable.
     */
    public boolean isPrintableControl(int iHtmlAttributes)
    {
        return true;    // Typically input sub-screens are not displayed, this IS
    }

}
