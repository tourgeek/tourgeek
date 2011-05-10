package com.tourapp.thin.app.booking.entry.context;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jbundle.model.util.Util;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.screen.JScreen;

import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.tour.booking.db.Booking;
import com.tourapp.thin.tour.booking.db.Tour;
import com.tourapp.thin.tour.product.base.db.BookingStatus;
import com.tourapp.thin.tour.product.base.db.PricingStatus;
import com.tourapp.thin.tour.product.tour.db.TourStatus;

/**
 * This panel displays the Legend (instructions) for the Calendar screen.
 * It is usually displayed when there is nothing selected.
 */
public class JSummaryScreen extends JScreen
{
	private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     */
    public JSummaryScreen()
    {
        super();
    }
    /**
     *  JScreen Class Constructor.
     */
    public JSummaryScreen(Object parent, Object record)
    {
        this();
        this.init(parent, record);
    }
    /**
     * Initialize this class.
     */
    public void init(Object parent, Object record)
    {
        m_parent = parent;  // Now I can do the next call.
        if (record == null)
            record = this.getTourAppScreen().getFieldList();
        this.addFieldList(this.getTourAppScreen().getTourRecord());
        super.init(parent, record);
        
        JPanel button = new JLegendScreen(null);
        m_gbconstraints.gridwidth = GridBagConstraints.REMAINDER;

        m_gbconstraints.weightx = 1.0;                        // Grow edit and scroll pane but not label
        m_gbconstraints.anchor = GridBagConstraints.NORTHWEST;    // Edit boxes left justified
        m_gbconstraints.gridx = 0;                            // Column 1
        m_gbconstraints.gridy = GridBagConstraints.RELATIVE;  // Bump Row each time

        GridBagLayout gridbag = (GridBagLayout)this.getScreenLayout();
        gridbag.setConstraints(button, m_gbconstraints);
        this.add(button);
    }
    /**
     * Free the resources held by this object.
     * NOTE NOTE NOTE: This method does not remove this panel from the parent, or free() sub-panels; you have to do that.
     */
    public void free()
    {
        if (this.getTourRecord() != null)
            this.disconnectControls(this.getTourRecord());
        if (this.getFieldList() != null)
            this.disconnectControls(this.getFieldList());
        this.removeFieldList(null);    // Another screen will free Booking.
        super.free();
    }
    /**
     * Utility to get the main tour studio screen.
     * @return the TourAppScreen.
     */
    public TourAppScreen getTourAppScreen()
    {
        return (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
    }
    /**
     * Utility to get the main tour studio screen's tour record.
     * @return the TourRecord.
     */
    public FieldList getTourRecord()
    {
        return this.getTourAppScreen().getTourRecord();
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
                return this.getFieldList().getField(Booking.CODE);
            case 1:
                return this.getTourRecord().getField(Tour.DEPARTURE_DATE);
            case 2:
                return this.getFieldList().getField(Booking.GROSS);
            case 3:
                return this.getTourRecord().getField(Tour.DESCRIPTION);
            case 4:
                return this.getFieldList().getField(Booking.BOOKING_STATUS_ID);
            case 5:
                return this.getTourRecord().getField(Tour.TOUR_STATUS_ID);
            default:
        }
        return null;
    }
    /**
     * Add the description labels to the first column of the grid.
     */
    public JComponent addScreenLabel(Container parent, Converter fieldInfo)
    {
        GridBagConstraints c = this.getGBConstraints();
        c.gridy = GridBagConstraints.RELATIVE;  // Bump Row each time
        if (fieldInfo.getFieldName().equals(Booking.BOOKING_STATUS_ID))
        {
            c.gridx = 4;
            c.gridy = 1;
        }
        else if (fieldInfo.getFieldName().equals(Tour.TOUR_STATUS_ID))
        {
            c.gridx = 4;
            c.gridy = 2;
        }
        else if (fieldInfo.getFieldName().equals(Tour.DESCRIPTION))
        {
            c.gridx = 4;
            c.gridy = 0;
        }
        return super.addScreenLabel(parent, fieldInfo);
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public JComponent addScreenControl(Container parent, Converter fieldInfo)
    {
        GridBagConstraints c = this.getGBConstraints();
        c.gridy = GridBagConstraints.RELATIVE;  // Bump Row each time
        if (fieldInfo.getFieldName().equals(Booking.BOOKING_STATUS_ID))
        {
            c.gridx = 5;
            c.gridy = 1;
        }
        else if (fieldInfo.getFieldName().equals(Tour.TOUR_STATUS_ID))
        {
            c.gridx = 5;
            c.gridy = 2;
        }
        else if (fieldInfo.getFieldName().equals(Tour.DESCRIPTION))
        {
            c.gridx = 5;
            c.gridy = 0;
        }
        return super.addScreenControl(parent, fieldInfo);
    }
    /**
     * Add the screen controls to the second column of the grid.
     * Create a default component for this fieldInfo.
     * @param fieldInfo the field to create a control for.
     * @return The component.
     */
    public JComponent createScreenComponent(Converter fieldInfo)
    {
        JComponent component = null;
        if (fieldInfo.getFieldName().equals(Booking.BOOKING_STATUS_ID))
        {
            FieldList record = new BookingStatus(this);
            component = this.getTourAppScreen().getMainSearchPane().addSecondaryIconComponent(record, fieldInfo, BookingStatus.ID, BookingStatus.ICON, BookingStatus.DESCRIPTION);
        }
        else if (fieldInfo.getFieldName().equals(Tour.DESCRIPTION))
        {
            String strDefault = fieldInfo.toString();
            if (strDefault == null)
                strDefault = Constants.BLANK;
            component = new JTextField(strDefault, 40);
        }
        else if (fieldInfo.getFieldName().equals(Tour.TOUR_STATUS_ID))
        {
            FieldList record = new TourStatus(this);
            component = this.getTourAppScreen().getMainSearchPane().addSecondaryIconComponent(record, fieldInfo, TourStatus.ID, TourStatus.ICON, TourStatus.DESCRIPTION);
        }
        else if (fieldInfo.getFieldName().equals(Booking.GROSS))
        {
            JPanel panelTotal = new JPanel();
            panelTotal.setOpaque(false);
            panelTotal.setLayout(new BoxLayout(panelTotal, BoxLayout.X_AXIS));

            FieldList recPricingStatus = new PricingStatus(this);
            FieldList recBooking = fieldInfo.getField().getRecord();
            Converter fldPricingStatus = recBooking.getField(Booking.PRICING_STATUS_ID);
            JComponent compPricingStatus = this.getTourAppScreen().getMainSearchPane().addSecondaryIconComponent(recPricingStatus, fldPricingStatus, PricingStatus.ID, PricingStatus.ICON, null);        
            this.addScreenComponent(panelTotal, fldPricingStatus, compPricingStatus);
            panelTotal.add(Box.createHorizontalStrut(3));

            Converter fldPrice = recBooking.getField(Booking.GROSS); 
            component = this.getTourAppScreen().getMainSearchPane().addCurrencyAmount(fldPrice, this.getFieldList().getField(Booking.CURRENCY_CODE), panelTotal);
            component = panelTotal;
        }

        if (component == null)
            component = super.createScreenComponent(fieldInfo);
        if (fieldInfo.getFieldName().equals(Tour.DEPARTURE_DATE))
            Util.setEnabled(component, false);
        if (fieldInfo.getFieldName().equals(Booking.GROSS))
            Util.setEnabled(component, false);
        return component;
    }
    /**
     * When a control loses focus, move the field to the data area.
     * @param e The focus event.
     */
    public void focusLost(FocusEvent e)
    {
        boolean bFirstChange = false;
        if (this.getFieldList() != null)
            if (this.getFieldList().getEditMode() == Constants.EDIT_ADD)
                if (this.getFieldList().isModified() == false)
                    bFirstChange = true;
        super.focusLost(e);
        if (bFirstChange)
        {
            m_componentNextFocus = null;
            Component component = (Component)e.getSource();
            String string = component.getName();
            FieldInfo field = this.getFieldList(0).getField(string);        // Get the fieldInfo for this component
            if (field != null)
                if (Booking.CODE.equals(string))
                {
                    String strBookingID = field.toString();
                    this.getTourAppScreen().readThisBooking(strBookingID, Booking.CODE, true);
                }
        }
    }
}
