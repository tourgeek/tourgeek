/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.pax;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import javax.swing.JComponent;
import javax.swing.JMenuBar;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.Params;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JScreen;
import org.jbundle.thin.base.screen.util.JRemoteComboBox;
import org.jbundle.thin.base.screen.util.JThreeStateCheckBox;

import com.tourapp.thin.tour.booking.db.BookingPax;
import com.tourapp.thin.tour.product.base.db.PaxCategory;

/**
 * Main Class for applet OrderEntry
 */
public class JBookingPaxScreen extends JScreen
{
	private static final long serialVersionUID = 1L;

    /**
     *  Constructor.
     */
    public JBookingPaxScreen()
    {
        super();
    }
    /**
     *  Constructor.
     */
    public JBookingPaxScreen(Object parent,Object obj)
    {
        this();
        this.init(parent, obj);
    }
    /**
     * Constructor.
     */
    public void init(Object parent, Object obj)
    {
        super.init(parent, obj);
    }
    /**
     * Free.
     */
    public void free()
    {
        JRemoteComboBox box = (JRemoteComboBox)this.getFieldList().getField(BookingPax.PAX_CATEGORY_ID).getComponent(CONTROL);
        box.setRecord(null);    // Don't free this record.
        super.free();
    }
    /**
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     */
    public Converter getFieldForScreen(int iIndex)
    {
        String strFieldName = null;
        switch (iIndex)
        {
            case 0:
                strFieldName = BookingPax.PAX_CATEGORY_ID;break;
            case 1:
                strFieldName = BookingPax.FIRST_NAME;break;
            case 2:
                strFieldName = BookingPax.MIDDLE_NAME;break;
            case 3:
                strFieldName = BookingPax.SUR_NAME;break;
            case 4:
                strFieldName = BookingPax.SMOKER;break;
            case 5:
                strFieldName = BookingPax.COMMENTS;break;
        }
        if (strFieldName != null)
            return this.getFieldList().getField(strFieldName);
        return null;    // End of list
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public JComponent createScreenComponent(Converter fieldInfo)
    {
        JComponent component = null;

        String strDefault = fieldInfo.toString();
        if (strDefault == null)
            strDefault = Constants.BLANK;
        if (fieldInfo.getFieldName().equals(BookingPax.PAX_CATEGORY_ID))
        {
            BaseApplet applet = this.getBaseApplet();
            RemoteSession remoteSession = null; // Not needed since paxcat is already linked to a table
            JBookingPaxMainScreen mainScreen = (JBookingPaxMainScreen)this.getTargetScreen(this, JBookingPaxMainScreen.class);
            FieldList recPaxCategory = mainScreen.getPaxCategory();
            component = new JRemoteComboBox(applet, remoteSession, recPaxCategory, applet.getString(BookingPaxGridModel.PAX_CATEGORY), PaxCategory.DESCRIPTION, BookingPaxGridModel.PAX_CATEGORY, false, Params.ID, null);
        }
        else if (fieldInfo.getFieldName().equals(BookingPax.SMOKER))
        {
            component = new JThreeStateCheckBox(null);
        }
        else
            component = super.createScreenComponent(fieldInfo);
        return component;
    }
    /**
     * Add the menubars?
     * @return The newly created menubar or null.
     */
    public JMenuBar createMenubar()
    {
        return null;    // No menu bar on this sub-screen.
    }
    /**
     * Process this action.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        boolean bAction = false;
        
        if (Constants.BACK.equalsIgnoreCase(strAction))
        {
            strAction = Constants.GRID;
        }
        bAction = super.doAction(strAction, iOptions);
        
        return bAction;
    }
}
