/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourgeek.thin.app.booking.entry.search.hotel;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.screen.BaseApplet;

import com.tourgeek.thin.app.booking.entry.search.SearchConstants;
import com.tourgeek.thin.app.booking.entry.search.base.JProductContextScreen;
import com.tourgeek.thin.tour.product.base.db.Product;

/**
 * Main Class for Product Context screen.
 */
public class JHotelContextScreen extends JProductContextScreen
{
	private static final long serialVersionUID = 1L;

	/**
     *  OrderEntry Class Constructor.
     */
    public JHotelContextScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JHotelContextScreen(Object parent, Object obj)
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
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     */
    public Converter getFieldForScreen(int iIndex)
    {
        return super.getFieldForScreen(iIndex);
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public JComponent createScreenComponent(Converter fieldInfo)
    {
        JComponent component = null;
        BaseApplet applet = this.getBaseApplet();

        if (component == null)
            component = super.createScreenComponent(fieldInfo);
        
        if (Product.PRODUCT_TYPE.equalsIgnoreCase(fieldInfo.getFieldName()))
        {
            JPanel panel = (JPanel)component;
            
            String strNights = applet.getString(SearchConstants.NIGHTS);
            if (strNights.length() > 0)
                strNights = Character.toUpperCase(strNights.charAt(0)) + strNights.substring(1);
            JComponent label = new JLabel(strNights);
            label.setAlignmentX(LEFT_ALIGNMENT);
            label.setAlignmentY(TOP_ALIGNMENT);
            panel.add(label);
            JComponent text = this.buildTextEdit(applet, null, applet.getString(SearchConstants.NIGHTS), SearchConstants.NIGHTS);
            panel.add(text);
            text.setName(SearchConstants.NIGHTS);

            text.addFocusListener(new FocusAdapter()
            { // Make sure a tab with a changed field triggers date field validation.
                String m_strOldValue;
                public void focusGained(FocusEvent evt)
                {
                    m_strOldValue = ((JTextField)evt.getSource()).getText();
                    super.focusGained(evt);
                }
                public void focusLost(FocusEvent evt)
                {
                    super.focusLost(evt);
                    if (!m_strOldValue.equalsIgnoreCase(((JTextField)evt.getSource()).getText()))
                        actionPerformed(new ActionEvent(evt.getSource(), evt.getID(), null));
                }
            });
        }
        
        return component;
    }
    /**
     *  For the action listener (menu commands).
     */
    public void actionPerformed(ActionEvent evt)
    {
        JComponent button = (JComponent)evt.getSource();
        String strButtonName = button.getName();
        if (SearchConstants.NIGHTS.equals(strButtonName))
        {
            String strString = ((JTextField)button).getText();
            Integer intNights = null;
            try   {
                intNights = new Integer(strString);
            } catch (NumberFormatException ex)   {
                    intNights = new Integer(1);
            }
            PropertyChangeEvent propChangeEvent = new PropertyChangeEvent(button, SearchConstants.NIGHTS, null, intNights);
            this.getTourGeekScreen().getParams().propertyChange(propChangeEvent);
        }
        else
            super.actionPerformed(evt);
    }
    /** This method gets called when a bound property is changed.
     * @param evt A PropertyChangeEvent object describing the event source
     *    and the property that has changed.
     */
    public void propertyChange(PropertyChangeEvent evt)
    {
        if (SearchConstants.NIGHTS.equalsIgnoreCase(evt.getPropertyName()))
        {   // Somewhere, the user entered/clicked a date
            String strNights = Constants.BLANK;
            Integer intNights = (Integer)evt.getNewValue();
            Converter.initGlobals();
            if (intNights != null)
                strNights = intNights.toString();
            JTextField tfNights = (JTextField)this.getComponentByName(SearchConstants.NIGHTS);
            if (tfNights != null)
                tfNights.setText(strNights);
        }
        super.propertyChange(evt);
    }
}
