/**
  * @(#)RequestHtmlDetailGrid.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.request.html;

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
import com.tourapp.tour.request.db.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import org.jbundle.base.screen.view.*;
import com.tourapp.tour.request.screen.*;
import com.tourapp.tour.base.db.shared.*;

/**
 *  RequestHtmlDetailGrid - Screen to enter the brochures requested.
 */
public class RequestHtmlDetailGrid extends GridScreen
{
    /**
     * Default constructor.
     */
    public RequestHtmlDetailGrid()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public RequestHtmlDetailGrid(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Screen to enter the brochures requested";
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.setEditing(true);
        this.getRecord(RequestInput.REQUEST_INPUT_FILE).getField(RequestInput.BROCHURE_ID).setEnabled(false);
    }
    /**
     * Add the navigation button(s) to the left of the grid row.
     */
    public void addNavButtons()
    {
        // No nav buttons
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        String strAgentProperty = this.getProperty("agent");
        if (strAgentProperty == null)
            strAgentProperty = "no";
        if (strAgentProperty.equalsIgnoreCase("yes"))
            this.getRecord(RequestInput.REQUEST_INPUT_FILE).getField(RequestInput.BROCHURE_QTY).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        else
            new SCheckBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, this.getRecord(RequestInput.REQUEST_INPUT_FILE).getField(RequestInput.BROCHURE_QTY), ScreenConstants.DEFAULT_DISPLAY, "1", null);
        this.getRecord(RequestInput.REQUEST_INPUT_FILE).getField(RequestInput.BROCHURE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Set up the physical control (that implements Component).
     * @param bEditableControl If false, set up a read-only control.
     * @return The new view.
     */
    public ScreenFieldView setupScreenFieldView(boolean bEditableControl)
    {
        if (ScreenModel.HTML_TYPE.equalsIgnoreCase(this.getViewFactory().getViewSubpackage()))
            return new HRequestHtmlDetailGrid(this, bEditableControl);
        else
            return null;    // Not supported!
    }

}
