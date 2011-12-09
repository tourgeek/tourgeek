/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search.air;

/**
 * JScreen.java:    Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.screen.AbstractThinTableModel;
import org.jbundle.thin.base.screen.JBaseToolbar;
import org.jbundle.thin.base.screen.grid.JGridScreen;
import org.jbundle.thin.base.util.ThinMenuConstants;

import com.tourapp.thin.tour.product.air.db.Airline;

/**
 * A Basic data entry screen.
 * This screen is made of a panel with a GridBagLayout. Labels in the first column, aligned right.
 * Data fields in the second column aligned left.
 */
public class AirlineGridScreen extends JGridScreen
{
	private static final long serialVersionUID = 1L;

	protected String m_strParam = null;

    /**
     *  JScreen Class Constructor.
     */
    public AirlineGridScreen()
    {
        super();
    }
    /**
     *  JScreen Class Constructor.
     * @param parent Typically, you pass the BaseApplet as the parent.
     * @param record and the record or GridTableModel as the parent.
     */
    public AirlineGridScreen(Object parent, Object record)
    {
        this();
        this.init(parent, record);
    }
    /**
     * Initialize this class.
     * @param parent Typically, you pass the BaseApplet as the parent.
     * @param record and the record or GridTableModel as the parent.
     */
    public void init(Object parent, Object record)
    {
        super.init(parent, record);
        m_strParam = (String)record;
    }
    /**
     * Build the list of fields that make up the screen.
     * Override this to create a new record.
     * @return The fieldlist for this screen.
     */
    public FieldList buildFieldList()
    {
        FieldList fieldList = new Airline(null);   // If overriding class didn't set this
        fieldList.setKeyName(Airline.DESCRIPTION);
        return fieldList;
    }
    /**
     * Build the list of fields that make up the screen.
     * Override this to create a new record.
     * @return The fieldlist for this screen.
     */
    public AbstractThinTableModel createGridModel(FieldList record)
    {
        return new AirlineGridModel(record.getTable());
    }
    /**
     * Process this action.
     * @param strAction The action to process.
     * By default, this method handles RESET, SUBMIT, and DELETE.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        FieldList record = this.getFieldList();
        boolean bFlag = false;
        try   {
            bFlag = super.doAction(strAction, iOptions);
            if (bFlag)
                return bFlag;
            if (ThinMenuConstants.SELECT.equalsIgnoreCase(strAction))
            {
                record = this.makeSelectedRowCurrent();
                if (record != null)
                {
                    String strAirline = record.getField(Airline.ID).toString();
                    this.firePropertyChange(m_strParam + Airline.ID, null, strAirline); // This won't trigger a change.
                    strAirline = record.getField(Airline.DESCRIPTION).toString();
                    this.firePropertyChange(m_strParam, null, strAirline);      // This will trigger a change.
                    return super.doAction(Constants.CLOSE, iOptions);     // Close this window
                }
            }
        } catch (Exception ex)  {
            this.getBaseApplet().setStatusText(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
        return bFlag;
    }
    /**
     * Add the toolbars?
     * @return The newly created toolbar or null.
     */
    public JComponent createToolbar()
    {
        return new JBaseToolbar(this, null)
        {
            private static final long serialVersionUID = 1L;
            /**
             * Add the buttons to this window.
             * Override this to include buttons other than the default buttons.
             */
            public void addButtons()
            {
                super.addButtons();
                addButton(ThinMenuConstants.SELECT);
                addButton(Constants.HELP);
            }
        };
    }
}
