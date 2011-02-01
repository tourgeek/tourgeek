package com.tourapp.thin.app.booking.entry.pax;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.Container;
import java.awt.LayoutManager;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.screen.JScreen;

import com.tourapp.thin.tour.booking.db.Booking;

/**
 * Main Class for applet OrderEntry
 */
public class JBookingPaxSummaryScreen extends JScreen
{
	private static final long serialVersionUID = 1L;
    
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingPaxSummaryScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingPaxSummaryScreen(Object parent,Object obj)
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
     * Free.
     */
    public void free()
    {
        this.disconnectControls(null);
        this.removeFieldList(null);    // Another screen will free Booking.
        super.free();
    }
    /**
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     */
    public Converter getFieldForScreen(int iIndex)
    {
        switch (iIndex)
        {
            case 0:
                return this.getFieldList().getField(Booking.PAX);
            case 1:
                return this.getFieldList().getField(Booking.SINGLE_PAX);
            case 2:
                return this.getFieldList().getField(Booking.DOUBLE_PAX);
            case 3:
                return this.getFieldList().getField(Booking.TRIPLE_PAX);
            case 4:
                return this.getFieldList().getField(Booking.QUAD_PAX);
            case 5:
                return this.getFieldList().getField(Booking.CHILDREN);
            default:
        }
        return null;
    }
    /**
     * Get the GridBagConstraints.
     * @return The gridbag constraints object.
     */
    public LayoutManager getScreenLayout(Container parent)
    {
        if (m_layout == null)
            m_layout = new BoxLayout(parent, BoxLayout.X_AXIS);
        return m_layout;
    }
    /**
     * Add the description labels to the first column of the grid.
     * Not used for menus.
     */
    public void addScreenLabels(Container parent)
    {
    }
    /**
     * Set the constraints for this component.
     * If you aren't using gridbag, override this.
     * @param component The component to set the constraints for.
     */
    public void setComponentConstraints(JComponent component)
    {
    }
    /**
     * Add the screen controls to the second column of the grid.
     * @param parent The container to add the control(s) to.
     * @param fieldInfo the field to add a control for.
     * @param gridbag The screen layout.
     * @param c The constraint to use.
     */
    public JComponent addScreenControl(Container parent, Converter fieldInfo)
    {
        this.addScreenLabel(parent, fieldInfo);
        parent.add(Box.createHorizontalStrut(5));
        JComponent component = super.addScreenControl(parent, fieldInfo);
        component.setEnabled(false);
        parent.add(Box.createHorizontalStrut(20));
        return component;
    }
}
