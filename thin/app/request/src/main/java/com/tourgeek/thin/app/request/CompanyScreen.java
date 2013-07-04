/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.request;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;

import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;

import com.tourapp.thin.tour.request.db.Request;

/**
 * Main Class for applet OrderEntry
 */
public class CompanyScreen extends PersonScreen implements ItemListener
{
	private static final long serialVersionUID = 1L;

	protected JCheckBox m_checkboxCompany = null;
    protected JLabel m_labelBlank = null;
    
    /**
     *  Request Constructor.
     */
    public CompanyScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public CompanyScreen(Object parent, Object obj)
    {
        this();
        this.init(parent, obj);
    }
    /**
     * The init() method is called by the AWT when an applet is first loaded or
     * reloaded.  Override this method to perform whatever initialization your
     * applet needs, such as initializing data structures, loading images or
     * fonts, creating frame windows, setting the layout manager, or adding UI
     * components.
     */
    public void init(Object parent, Object obj)
    {
        super.init(parent, obj);
    }
    /**
     * Build the list of fields that make up the screen.
     */
    public FieldList buildFieldList()
    {
        return super.buildFieldList();  // If overriding class didn't set this
    }
    /**
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     */
    public Converter getFieldForScreen(int iIndex)
    {
        FieldInfo fieldInfo = (FieldInfo)super.getFieldForScreen(iIndex);
        if (iIndex == 0)        // First field is the "Attention" field
            fieldInfo = this.getFieldList().getField(Request.ATTENTION);
        return fieldInfo;
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public JComponent addScreenControl(Container parent, Converter fieldInfo)
    {
        if (m_checkboxCompany == null)
        {       // Add the "Company" Checkbox first.
            m_checkboxCompany = new JCheckBox("Company order");
            m_checkboxCompany.setOpaque(false);
            GridBagConstraints c = this.getGBConstraints();
            GridBagLayout gridbag = (GridBagLayout)this.getScreenLayout();
            gridbag.setConstraints(m_checkboxCompany, c);
            parent.add(m_checkboxCompany);
            m_checkboxCompany.addItemListener(this);    // calls itemStateChanged
        }

        return super.addScreenControl(parent, fieldInfo);
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public void addScreenControls(Container parent)
    {
        super.addScreenControls(parent);

        if (this.getFieldList().getField(Request.ATTENTION) != null)
        {
            ((JComponent)this.getFieldList().getField(Request.ATTENTION).getComponent(LABEL)).setVisible(false);
            ((JComponent)this.getFieldList().getField(Request.ATTENTION).getComponent(CONTROL)).setVisible(false);
        }
    }
    /**
     * Add the description labels to the first column of the grid.
     */
    public JComponent addScreenLabel(Container parent, Converter fieldInfo)
    {
        if (m_labelBlank == null)
        {   // Add a blank row above the first label to balance the "company" checkbox
            m_labelBlank = new JLabel(" ");
            GridBagConstraints c = this.getGBConstraints();
            GridBagLayout gridbag = (GridBagLayout)this.getScreenLayout();
            gridbag.setConstraints(m_labelBlank, c);
            parent.add(m_labelBlank);
        }

        return super.addScreenLabel(parent, fieldInfo);
    }
    /**
     * Respond to a click on the "Company" button.
     */
    public void itemStateChanged(ItemEvent e)
    {
        Component component = (Component)e.getSource();
        if (component == null)
            return;
        boolean bShow = true;
        if (component instanceof JCheckBox)
            bShow = ((JCheckBox)component).isSelected();
        this.showCompany(bShow);
    }
    /**
     * Show or hide the "Company" field depending on the checkbox, then redisplay the catalog
     * and focus on the first field.
     */
    public void showCompany(boolean bShow)
    {
        boolean bVisible = bShow;
        ((JComponent)getFieldList().getField(Request.ATTENTION).getComponent(LABEL)).setVisible(bVisible);
        ((JComponent)getFieldList().getField(Request.ATTENTION).getComponent(CONTROL)).setVisible(bVisible);

        ((RequestMainScreen)this.getParent()).showCompany(bShow); // have applet do any changes associated with showCompany
        this.focusFirstField();
    }
    /**
     * Focus on the first field after the checkbox.
     */
    public void focusFirstField()
    {
        JComponent compFocus = (JComponent)this.getFieldList().getField(Request.ATTENTION).getComponent(CONTROL);
        if (!compFocus.isVisible())
            compFocus = (JComponent)this.getFieldList().getField(Request.GENERIC_NAME).getComponent(CONTROL);
        if (compFocus != null)
            compFocus.requestFocus();
    }
    /**
     * Is the company checkbox clicked?
     */
    public boolean isCompanySelected()
    {
        return m_checkboxCompany.isSelected();
    }
}
