/*
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.detail;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jbundle.model.DBException;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.screen.AbstractThinTableModel;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBasePanel;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.JBaseToolbar;
import org.jbundle.thin.base.screen.action.ActionManager;
import org.jbundle.thin.base.screen.cal.grid.CalendarThinTableModel;
import org.jbundle.thin.base.screen.cal.popup.ProductConstants;
import org.jbundle.util.calendarpanel.event.MyListSelectionEvent;
import org.jbundle.util.calendarpanel.event.MyListSelectionListener;
import org.jbundle.util.calendarpanel.model.CalendarConstants;
import org.jbundle.util.calendarpanel.model.CalendarModel;
import org.jbundle.thin.base.screen.grid.JCellButton;
import org.jbundle.thin.base.screen.grid.JCellCalendarButton;
import org.jbundle.thin.base.screen.util.JBlinkLabel;
import org.jbundle.thin.base.util.ThinMenuConstants;

import com.tourapp.thin.app.booking.entry.BookingConstants;
import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.app.booking.entry.model.BookingDetailCalendarItem;
import com.tourapp.thin.app.booking.entry.model.CustSaleDetailThinTableModel;
import com.tourapp.thin.tour.booking.detail.db.BookingDetail;

/**
 * The line item (product) detail screen.
 * This screen is made of a panel with a GridBagLayout. Labels in the first column, aligned right.
 * Data fields in the second column aligned left.
 */
public class JBookingDetailGridScreen extends JBaseScreen
    implements MyListSelectionListener
{
	private static final long serialVersionUID = 1L;

	protected AbstractThinTableModel m_model = null;
    protected int m_iSelected = -1;

    /**
     *  JBookingDetailGridScreen Class Constructor.
     */
    public JBookingDetailGridScreen()
    {
        super();
    }
    /**
     *  JBookingDetailGridScreen Class Constructor.
     * Typically, you pass the BaseApplet as the parent, and the record or GridTableModel as the parent.
     */
    public JBookingDetailGridScreen(Object parent, Object record)
    {
        this();
        this.init(parent, record);
    }
    /**
     * Initialize this class.
     */
    public void init(Object parent, Object record)
    {
        super.init(parent, record);
        JCellButton button = null;
        BaseApplet applet = this.getBaseApplet();
        
        AbstractThinTableModel model = (AbstractThinTableModel)((TourAppScreen)this.getTargetScreen(TourAppScreen.class)).getCalendarModel();
        m_model = model;
        JTable screenTable = new JTable(model);
        screenTable.setCellSelectionEnabled(false);
        screenTable.setColumnSelectionAllowed(false);
        screenTable.setRowSelectionAllowed(true);

        TableColumnModel columnModel = screenTable.getColumnModel();
        TableColumn tableColumn = columnModel.getColumn(CustSaleDetailThinTableModel.STATUS_COLUMN);

        JBlinkLabel label = new JBlinkLabel(null)
        {   // Special override to display the correct product type icon.
            private static final long serialVersionUID = 1L;

            public ImageIcon getImageIcon(Object value)
            {
                ImageIcon icon = super.getImageIcon(value);
                if (icon == m_rgIcons[0])
                    if (m_table != null)
                        if (m_iThisRow != -1)
                {
                    BookingDetailCalendarItem item =
                        (BookingDetailCalendarItem)((CustSaleDetailThinTableModel)m_table.getModel()).getItem(m_iThisRow);
                    if (item != null)
                        return item.getIcon(CalendarConstants.START_ICON);
                }
                return icon;
            }
        };
        label.addIcon(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + ProductConstants.ITEM), 0);
        label.addIcon(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.INFO), BookingConstants.INFO_LOOKUP);
        label.addIcon(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.PRICE), BookingConstants.COST_LOOKUP);
        label.addIcon(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.INVENTORY), BookingConstants.INVENTORY_LOOKUP);
        label.addIcon(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.PRODUCT), BookingConstants.PRODUCT_LOOKUP);
        tableColumn.setCellRenderer(label);
        tableColumn.setMaxWidth(20);
        button = new JCellButton(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + "Item"));
        button.setName(Constants.FORM);
        button.addActionListener(this);
        tableColumn.setCellEditor(button);

        tableColumn = columnModel.getColumn(CustSaleDetailThinTableModel.START_DATE_BUTTON_COLUMN);
        Converter fi = model.getFieldInfo(CustSaleDetailThinTableModel.START_DATE_COLUMN);
        JCellCalendarButton button2 = new JCellCalendarButton(fi);
        tableColumn.setCellRenderer(button2);
        tableColumn.setMaxWidth(20);
        button2 = new JCellCalendarButton(fi);
        tableColumn.setCellEditor(button2);

        tableColumn = columnModel.getColumn(CustSaleDetailThinTableModel.END_DATE_BUTTON_COLUMN);
        fi = model.getFieldInfo(CustSaleDetailThinTableModel.END_DATE_COLUMN);
        button2 = new JCellCalendarButton(fi);
        tableColumn.setCellRenderer(button2);
        tableColumn.setMaxWidth(20);
        button2 = new JCellCalendarButton(fi);
        tableColumn.setCellEditor(button2);

        tableColumn = columnModel.getColumn(CustSaleDetailThinTableModel.LOCAL_PRICE_COLUMN);
        tableColumn.setCellRenderer(model.createColumnCellRenderer(CustSaleDetailThinTableModel.LOCAL_PRICE_COLUMN));
        tableColumn.setCellEditor(model.createColumnCellEditor(CustSaleDetailThinTableModel.LOCAL_PRICE_COLUMN));

        tableColumn = columnModel.getColumn(CustSaleDetailThinTableModel.START_DATE_COLUMN);
        tableColumn.setCellRenderer(model.createColumnCellRenderer(CustSaleDetailThinTableModel.START_DATE_COLUMN));
        tableColumn.setCellEditor(model.createColumnCellEditor(CustSaleDetailThinTableModel.START_DATE_COLUMN));

        tableColumn = columnModel.getColumn(CustSaleDetailThinTableModel.END_DATE_COLUMN);
        tableColumn.setCellRenderer(model.createColumnCellRenderer(CustSaleDetailThinTableModel.END_DATE_COLUMN));
        tableColumn.setCellEditor(model.createColumnCellEditor(CustSaleDetailThinTableModel.END_DATE_COLUMN));

        tableColumn = columnModel.getColumn(CustSaleDetailThinTableModel.DESCRIPTION_COLUMN);
        tableColumn.setCellRenderer(model.createColumnCellRenderer(CustSaleDetailThinTableModel.DESCRIPTION_COLUMN));
        tableColumn.setCellEditor(model.createColumnCellEditor(CustSaleDetailThinTableModel.DESCRIPTION_COLUMN));
        tableColumn.setPreferredWidth(300);

        model.setGridScreen(screenTable, false);
        ((CalendarThinTableModel)model).addMySelectionListener(this);

        screenTable.moveColumn(CustSaleDetailThinTableModel.START_DATE_BUTTON_COLUMN, CustSaleDetailThinTableModel.END_DATE_COLUMN);
        screenTable.moveColumn(CustSaleDetailThinTableModel.END_DATE_BUTTON_COLUMN, CustSaleDetailThinTableModel.DESCRIPTION_COLUMN + 1);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JScrollPane scrollpane = new JScrollPane(screenTable);
        scrollpane.setOpaque(false);
        scrollpane.getViewport().setOpaque(false);
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.setAlignmentY(TOP_ALIGNMENT);
        this.add(scrollpane);
    }
    /**
     *  For the action listener (menu commands).
     */
    public void actionPerformed(ActionEvent evt)
    {
        super.actionPerformed(evt);
    }
    /**
     * The table changed... If the selection was changed, change it in the table!
     */
    public void selectionChanged(final MyListSelectionEvent evt)
    {
        JTable screenTable = (JTable)JBasePanel.getSubScreen(this, JTable.class);
        if (evt.getSource() != screenTable)
        {   // Make sure it isn't me doing the selection.
            if (evt.getType() == MyListSelectionEvent.SELECT)
            {
                screenTable.setRowSelectionInterval(evt.getRow(), evt.getRow());
            }
            else if (evt.getType() == MyListSelectionEvent.DESELECT)
            {
                screenTable.clearSelection();
            }
        }
    }
    /**
     * Add the JMenubar
     * @return The JMenubar.
     */
    public JMenuBar createMenubar()
    {
        JMenuBar menuBar = ActionManager.getActionManager().setupStandardMenu(this);
        JMenu menu = ActionManager.getActionManager().addMenu(menuBar,ThinMenuConstants.RECORD, JBaseToolbar.BEFORE_HELP);
        ActionManager.getActionManager().addMenuItem(menu, Constants.FORM, this, KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, ActionEvent.CTRL_MASK));
        ActionManager.getActionManager().addMenuItem(menu, Constants.DELETE, this, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.CTRL_MASK));
        return menuBar;
    }
    /**
     * Add the toolbars?
     */
    public JComponent createToolbar()
    {
        return new JBaseToolbar(this, null)
        {
        	private static final long serialVersionUID = 1L;

        	public void addButtons()
            {
                this.addButton(Constants.BACK);
                this.addButton(Constants.FORM);
                this.addButton(Constants.DELETE);
                for (String type : gProductTypes)
                {
                    this.addTourButton(type);
                }
                this.addButton(Constants.HELP);
            }
            protected final String strImageDir = BookingConstants.BUTTON_LOCATION;
            public void addTourButton(String strTourType)
            {
                this.addButton(this.getBaseApplet().getString(strTourType), strImageDir + strTourType, strTourType);
            }
        };
    }
    public static final String[] gProductTypes = {
        ProductConstants.TOUR,
        ProductConstants.AIR,
        ProductConstants.HOTEL,
        ProductConstants.LAND,
        ProductConstants.CAR,
        ProductConstants.TRANSPORTATION,
        ProductConstants.CRUISE,
        ProductConstants.ITEM
    };
    /**
     * Process this action.
     * @param strAction The command to process.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
        JTable table = (JTable)JBasePanel.getSubScreen(this, JTable.class);
        int iIndex = table.getSelectedRow();
        if (Constants.BACK.equalsIgnoreCase(strAction))
        {
            return tourAppScreen.doAction(BookingConstants.SEARCH, iOptions);
        }
        else if (Constants.FORM.equalsIgnoreCase(strAction))
        {
            if (iIndex != -1)
                ((CalendarModel)m_model).fireTableRowSelected(this, iIndex, MyListSelectionEvent.CONTENT_SELECT | MyListSelectionEvent.CONTENT_CLICK);
            return true;    // Handled
        }
        else if (Constants.DELETE.equalsIgnoreCase(strAction))
        {
            FieldList record = null;
            if (iIndex != -1)
                record = m_model.makeRowCurrent(iIndex, false);
            try {
                if (record != null)
                    record.getTable().remove();
            } catch (DBException ex) {
                ex.printStackTrace();
            }
            return true;    // Always Handled
        }
        else
        {
            for (String type : gProductTypes)
            {
                if (type.equalsIgnoreCase(strAction));
                {   // If you want a product screen, display it!
                    if (iIndex == -1)
                    {
                        try {
                            m_model.getFieldTable().addNew();   // Make sure it is clear
                            m_model.getFieldTable().getRecord().getField(BookingDetail.PRODUCT_TYPE).setString(strAction);
                            JBookingDetailMainScreen mainScreen = (JBookingDetailMainScreen)this.getTargetScreen(JBookingDetailMainScreen.class);
                            mainScreen.setSource(this);
                            boolean bReturn = mainScreen.handleAction(Constants.FORM, null, iOptions);
                            return bReturn;
                        } catch (DBException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
        return super.doAction(strAction, iOptions);
    }
}
