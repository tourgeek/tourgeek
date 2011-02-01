package com.tourapp.thin.app.booking.entry.context;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.border.BevelBorder;

import org.jbundle.model.DBException;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldInfo;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.screen.JBasePanel;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.cal.popup.ProductConstants;
import org.jbundle.util.calendarpanel.event.MyListSelectionEvent;
import org.jbundle.util.calendarpanel.event.MyListSelectionListener;
import org.jbundle.thin.base.screen.grid.JGridScreen;
import org.jbundle.thin.base.screen.grid.ThinTableModel;

import com.tourapp.thin.app.booking.entry.BookingConstants;
import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.app.booking.entry.model.BookingDetailCalendarItem;
import com.tourapp.thin.app.booking.entry.model.CustSaleDetailThinTableModel;
import com.tourapp.thin.app.booking.entry.search.base.JProductContextScreen;
import com.tourapp.thin.app.booking.entry.search.base.ProductGridModel;
import com.tourapp.thin.tour.product.tour.db.TourHeader;

/**
 * The ContextPanel displays a sub-panel for the current screen context.
 * ie., If there is nothing selected, a legend panel displays. If a hotel
 * is selected a Hotel panel displays.
 * <p>NOTE: Be sure to add this as a selection listener:
 * <br><pre>
 * if (object instanceof TableModel)
 *          ((TableModel)object).addMySelectionListener(this);      // Listen for (selection) changes.
 * </pre>
 */
public class JContextPanel extends JBasePanel
    implements MyListSelectionListener
{
	private static final long serialVersionUID = 1L;

	public static final Dimension BANNER_SIZE = new Dimension(640, 100);
    public static final int DISCONNECT = -2;
    public static final Dimension MAX_PANEL_SIZE = new Dimension(2000, 100);

    /**
     * Is the record for the current sub-panel shared with another panel?
     * (If so, don't free the record on sub-panel free).
     */
    protected boolean m_bRecordShared = false;
    /**
     * Current screen type (product, detail, or ?).
     */
    protected String m_strScreenType = null;
    /**
     * Current record type (Hotel, Land, etc).
     */
    protected String m_strProductType = null;
    /**
     * Current sub-panel.
     */
    protected JBasePanel m_subPanel = null;

    /**
     * Constructor.
     */
    public JContextPanel()
    {
        super();
    }
    /**
     * Constructor.
     */
    public JContextPanel(Object obj)
    {
        this();
        this.init(obj);
    }
    /**
     * Constructor.
     */
    public void init(Object object)
    {
        super.init(object, null);

        this.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.setForeground(Color.black);

        Dimension size = BANNER_SIZE; // Typical banner size.
        this.setMinimumSize(size);
        this.setMaximumSize(MAX_PANEL_SIZE);
        this.setPreferredSize(size);
        this.setOpaque(false);
        this.setLayout(null);   // I want panel contents left justified

        this.selectPanel(null, null, null, true);     // Initial panel
    }
    /**
     * Free this screen.
     */
    public void free()
    {
        super.free();
    }
    /**
     * These items have changed, update them on the screen.
     */
    public void selectionChanged(MyListSelectionEvent event)
    {
        int iCurrentSelection = event.getRow();
        if ((event.getType() == MyListSelectionEvent.SELECT)
            || (event.getType() == MyListSelectionEvent.DESELECT))
                this.selectPanel(((ThinTableModel)event.getModel()), iCurrentSelection);
    }
    /**
     * These items have changed, update them on the screen.
     */
    public void selectPanel(ThinTableModel tableModel, int iCurrentSelection)
    {
        FieldList fieldList = tableModel.getFieldTable().getRecord();
        if (iCurrentSelection >= 0)
            fieldList = tableModel.makeRowCurrent(iCurrentSelection, false);
        String strScreenType = Constants.BLANK;
        String strProductType = fieldList.getTableNames(false);
        if (tableModel instanceof CustSaleDetailThinTableModel)
        {
            strScreenType = BookingConstants.BOOKING;
            strProductType = ((BookingDetailCalendarItem)fieldList).getLocalProductType();
        }
        else if (tableModel instanceof ProductGridModel)
        {
            strScreenType = BookingConstants.PRODUCT;
            strProductType = ((ProductGridModel)tableModel).getProductType();
        }
        if (TourHeader.TOUR_HEADER_FILE.equalsIgnoreCase(strProductType))
            strProductType = ProductConstants.TOUR;
        boolean bScreenChange = false;
        if ((!(strScreenType.equalsIgnoreCase(m_strScreenType)))
            || (!(strProductType.equalsIgnoreCase(m_strProductType)))
            || (m_bRecordShared != false))
                bScreenChange = true;   // Do change if the product type is changing.
        
        if (iCurrentSelection == DISCONNECT)
        {
            if (bScreenChange)
                return;     // Already disconnected!
            TourAppScreen screenMain = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
            if (screenMain == null)
                return;
            if (screenMain.getTourRecord() == null)
                return;     // This is a hack (The screen is closing, so don't go any further).
            bScreenChange = true;
        }
        if (bScreenChange)
        {   // Change the context screen, since the table is different than last time.
            FieldList fieldListNew = null;
            if (iCurrentSelection >= 0)
            {
                fieldListNew = this.cloneFieldList(fieldList);
                if (fieldListNew != null)
                {
                    fieldListNew.setTable(fieldList.getTable());  // This fakes JScreen into not linking to the remote DB
                    fieldListNew.setEditMode(Constants.EDIT_ADD);
                }
                this.selectPanel(strScreenType, strProductType, fieldListNew, false);
                if (fieldListNew != null)
                {
                    fieldListNew.setTable(null);  // This fakes JScreen into not linking to the remote DB
                    fieldListNew.setEditMode(Constants.EDIT_NONE);
                }
            } else if (iCurrentSelection == DISCONNECT)
                this.selectPanel(null, null, null, true);     // Initial panel
            else if (iCurrentSelection == -1)
            {
                if (BookingConstants.BOOKING.equals(this.getScreenType()))
                    this.selectPanel(null, null, null, true);     // Switch to Initial panel (only if booking detail is currently displayed)
            }
        }
        if (m_subPanel instanceof JBaseScreen)
        {
            FieldList fieldListNew = ((JBaseScreen)m_subPanel).getFieldList();
            if (fieldListNew != null)
            {
                Object recordOwner = fieldListNew.getOwner();
                if (!(recordOwner instanceof JGridScreen))
                {
                    if (iCurrentSelection == -1)
                    {
                        // Don't do anything.
                    }
                    else if (iCurrentSelection != DISCONNECT)
                    {
                        try {
                            tableModel.updateIfNewRow(-1);      // Clear
                            tableModel.setCurrentRow(-1);  // the cache
                        } catch (DBException ex) {
                            ex.printStackTrace();
                        }
                        fieldList = tableModel.makeRowCurrent(iCurrentSelection, false);
                        this.copyFieldList(fieldListNew, fieldList);  // Copy the data over
                    }
                }
            }
        }
    }
    /**
     * Display the correct panel for this type.
     */
    public void selectPanel(String strScreenType, String strProductType, FieldList record, boolean bRecordShared)
    {
        boolean bSame = false;
        if (m_bRecordShared == bRecordShared)
            if (m_strScreenType == strScreenType)
                if (m_strProductType == strProductType)
                    bSame = true;   // Same screen
        if (record == null)
        {
            if (bRecordShared != true)
                if (strScreenType != null)
                    if (strProductType != null)
                        if (!bSame)
                            return;     // Don't free someone else screen (mine has already been freed).
            if (m_bRecordShared == true)
                if (m_strScreenType == null)
                    if (m_strProductType == null)
                        return;     // Summary screen already displayed!
            strScreenType = null;   // Display summary screen.
            strProductType = null;
            bRecordShared = true;
        }
        else
        {
            if (bSame)
                return;     // Already displayed!
        }

        if (m_subPanel != null)
        {
            if (m_bRecordShared)
                if (m_subPanel instanceof JBaseScreen)
                {
                    ((JBaseScreen)m_subPanel).disconnectControls(null);
                    ((JBaseScreen)m_subPanel).removeFieldList(null);
                }
            m_subPanel.free();
        }
        m_subPanel = null;
        this.removeAll();
        
        JBasePanel subPanel = null;
        if (BookingConstants.PRODUCT.equalsIgnoreCase(strScreenType))
        {
            subPanel = this.makeProductScreen(this, record, strProductType);
            if (subPanel == null)
                subPanel = new JProductContextScreen(this, record);
        }
        else if (BookingConstants.BOOKING.equalsIgnoreCase(strScreenType))
        {
            subPanel = this.makeDetailScreen(this, record, strProductType);
        }
        if (subPanel == null)
            subPanel = new JSummaryScreen(this, null);
        
        // Since this panel's parent doesn't have a layout manager, set the size manually.
        Dimension dim = subPanel.getPreferredSize();
        dim.height += 5;
        dim.width += 20;
        subPanel.setSize(dim);
        subPanel.setLocation(0, 0);
        this.add(subPanel);

        m_bRecordShared = bRecordShared;
        m_strScreenType = strScreenType;
        m_strProductType = strProductType;

        this.invalidate();
        this.validate();
        this.repaint();
        
        m_subPanel = subPanel;
    }
    /**
     *
     */
    private FieldList cloneFieldList(FieldList item)
    {
        FieldList fieldList = new FieldList(null);
        for (int i = 0; i < item.getFieldCount(); i++)
        {
            FieldInfo fldSource = item.getField(i);
            FieldInfo fldDest = new FieldInfo(fieldList, fldSource.getFieldName(), fldSource.getMaxLength(), fldSource.getFieldDesc(), fldSource.getDefault());
            fldDest.setDataClass(fldSource.getDataClass());
        }
        return fieldList;
    }
    /**
     *
     */
    private void copyFieldList(FieldList fieldList, FieldList item)
    {
        for (int i = 0; i < item.getFieldCount(); i++)
        {
            FieldInfo fldSource = item.getField(i);
            FieldInfo fldDest = fieldList.getField(i);
            fldDest.setData(fldSource.getData(), Constants.DISPLAY, Constants.READ_MOVE);
        }
    }
    /**
     * Make the product context screen for this type of product.
     * @param parent The screen's parent.
     * @param fieldListNew The field list.
     * @param strItemType The item type.
     * @return The product screen (or null if none).
     */
    public JBaseScreen makeProductScreen(Container parent, FieldList fieldListNew, String strItemType)
    {
        JBaseScreen subPanel = null;
        if ((strItemType != null) && (strItemType.startsWith(ProductConstants.TOUR)))
            subPanel = new com.tourapp.thin.app.booking.entry.search.tour.JTourHeaderContextScreen(parent, fieldListNew);
        else if (ProductConstants.AIR.equalsIgnoreCase(strItemType))
            subPanel = new com.tourapp.thin.app.booking.entry.search.air.JAirContextScreen(parent, fieldListNew);
        else if (ProductConstants.HOTEL.equalsIgnoreCase(strItemType))
            subPanel = new com.tourapp.thin.app.booking.entry.search.hotel.JHotelContextScreen(parent, fieldListNew);
        else if (ProductConstants.LAND.equalsIgnoreCase(strItemType))
            subPanel = new com.tourapp.thin.app.booking.entry.search.land.JLandContextScreen(parent, fieldListNew);
        else if (ProductConstants.CAR.equalsIgnoreCase(strItemType))
            subPanel = new com.tourapp.thin.app.booking.entry.search.car.JCarContextScreen(parent, fieldListNew);
        else if (ProductConstants.TRANSPORTATION.equalsIgnoreCase(strItemType))
            subPanel = new com.tourapp.thin.app.booking.entry.search.trans.JTransportationContextScreen(parent, fieldListNew);
        else if (ProductConstants.CRUISE.equalsIgnoreCase(strItemType))
            subPanel = new com.tourapp.thin.app.booking.entry.search.cruise.JCruiseContextScreen(parent, fieldListNew);
        else if (ProductConstants.ITEM.equalsIgnoreCase(strItemType))
            subPanel = new com.tourapp.thin.app.booking.entry.search.item.JItemContextScreen(parent, fieldListNew);
        return subPanel;
    }
    /**
     * Make the product context screen for this type of product.
     * @param parent The screen's parent.
     * @param fieldListNew The field list.
     * @param strItemType The item type.
     * @return The product screen (or null if none).
     */
    public JBaseScreen makeDetailScreen(Container parent, FieldList fieldListNew, String strItemType)
    {
        JBaseScreen subPanel = null;
        if (ProductConstants.TOUR.equalsIgnoreCase(strItemType))
            subPanel = new com.tourapp.thin.app.booking.entry.detail.tour.JBookingTourContextScreen(parent, fieldListNew);
        else if (ProductConstants.AIR.equalsIgnoreCase(strItemType))
            subPanel = new com.tourapp.thin.app.booking.entry.detail.air.JBookingAirContextScreen(parent, fieldListNew);
        else if (ProductConstants.HOTEL.equalsIgnoreCase(strItemType))
            subPanel = new com.tourapp.thin.app.booking.entry.detail.hotel.JBookingHotelContextScreen(parent, fieldListNew);
        else if (ProductConstants.LAND.equalsIgnoreCase(strItemType))
            subPanel = new com.tourapp.thin.app.booking.entry.detail.land.JBookingLandContextScreen(parent, fieldListNew);
        else if (ProductConstants.CAR.equalsIgnoreCase(strItemType))
            subPanel = new com.tourapp.thin.app.booking.entry.detail.car.JBookingCarContextScreen(parent, fieldListNew);
        else if (ProductConstants.TRANSPORTATION.equalsIgnoreCase(strItemType))
            subPanel = new com.tourapp.thin.app.booking.entry.detail.trans.JBookingTransportationContextScreen(parent, fieldListNew);
        else if (ProductConstants.CRUISE.equalsIgnoreCase(strItemType))
            subPanel = new com.tourapp.thin.app.booking.entry.detail.cruise.JBookingCruiseContextScreen(parent, fieldListNew);
        else if (ProductConstants.ITEM.equalsIgnoreCase(strItemType))
            subPanel = new com.tourapp.thin.app.booking.entry.detail.item.JBookingItemContextScreen(parent, fieldListNew);
        return subPanel;
    }
    /**
     * Current screen type (product, detail, or ?).
     */
    public String getScreenType()
    {
        return m_strScreenType;
    }
    /**
     * Current record type (Hotel, Land, etc).
     */
    public String getProductType()
    {
        return m_strProductType;
    }

}
