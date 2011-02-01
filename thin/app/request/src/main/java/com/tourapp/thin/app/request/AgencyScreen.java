package com.tourapp.thin.app.request;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldInfo;

import com.tourapp.thin.tour.request.db.Request;

/**
 * Main Class for applet OrderEntry
 */
public class AgencyScreen extends CompanyScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  Request Constructor.
     */
    public AgencyScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public AgencyScreen(Object parent, Object obj)
    {
        this();
        this.init(parent, obj);
    }
    /**
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     */
    public Converter getFieldForScreen(int iIndex)
    {
        FieldInfo fieldInfo = (FieldInfo)super.getFieldForScreen(iIndex);
        if (iIndex == 1)        // First field is the "Attention" field
            fieldInfo = this.getFieldList().getField(Request.PROFILE_CODE);
        return fieldInfo;
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public void addScreenControls(Container parent)
    {
        super.addScreenControls(parent);
        m_checkboxCompany.setText("Travel Agent");

        if (this.getFieldList().getField(Request.PROFILE_CODE) != null)
        {
            ((JComponent)this.getFieldList().getField(Request.PROFILE_CODE).getComponent(CONTROL)).setVisible(false);
            JLabel label = (JLabel)this.getFieldList().getField(Request.PROFILE_CODE).getComponent(LABEL);
            label.setText(this.getFieldList().getString("IATA"));  // Language Independent
            label.setVisible(false);
        }
    }
    /**
     * Show or hide the "Company" field depending on the checkbox, then redisplay the catalog
     * and focus on the first field.
     */
    public void showCompany(boolean bVisible)
    {
        ((JComponent)this.getFieldList().getField(Request.PROFILE_CODE).getComponent(LABEL)).setVisible(bVisible);
        ((JComponent)this.getFieldList().getField(Request.PROFILE_CODE).getComponent(CONTROL)).setVisible(bVisible);
        JLabel labelName = (JLabel)this.getFieldList().getField(Request.GENERIC_NAME).getComponent(LABEL);
        if (bVisible)
            labelName.setText(this.getFieldList().getString("Company Name"));
        else
            labelName.setText(getFieldList().getField(Request.GENERIC_NAME).getFieldDesc());
        super.showCompany(bVisible);
    }
    /**
     * Add this label to the first column of the grid.
     * @param fieldInfo The field to add a label to.
     * @param gridbag The screen layout.
     * @param c The constraint to use.
     */
    public JComponent addScreenLabel(Container parent, Converter fieldInfo)
    {
        return super.addScreenLabel(parent, fieldInfo);
    }
    /**
     * Add the screen controls to the second column of the grid.
     * Create a default component for this fieldInfo.
     * @param fieldInfo the field to create a control for.
     * @return The component.
     */
    public JComponent createScreenComponent(Converter fieldInfo)
    {
        JComponent component = super.createScreenComponent(fieldInfo);
        if (fieldInfo.getMaxLength() > 35)
            ((JTextField)component).setColumns(35);
        return component;
    }
}
