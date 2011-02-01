package com.tourapp.thin.app.booking.entry.pax;

/**
 * OrderEntry.java:   Applet
 *  Copyright (c) 2005 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import javax.swing.JMenuBar;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.db.Params;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.grid.JCellRemoteComboBox;
import org.jbundle.thin.base.screen.grid.JGridScreen;
import org.jbundle.thin.base.screen.grid.ThinTableModel;

import com.tourapp.thin.app.booking.entry.BookingConstants;
import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.tour.booking.db.BookingPax;
import com.tourapp.thin.tour.product.base.db.PaxCategory;

/**
 * Main Class for applet OrderEntry
 */
public class JBookingPaxGridScreen extends JGridScreen
{
	private static final long serialVersionUID = 1L;

	/**
     *  OrderEntry Class Constructor.
     */
    public JBookingPaxGridScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JBookingPaxGridScreen(Object parent,Object obj)
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

        BaseApplet baseApplet = ((JBaseScreen)parent).getBaseApplet();
        JTable jTable = this.getJTable();
        TableColumnModel columnModel = jTable.getColumnModel();
        TableColumn tableColumn = columnModel.getColumn(BookingPaxGridModel.ROOM_COLUMN);

        JBookingPaxMainScreen mainScreen = (JBookingPaxMainScreen)this.getTargetScreen(this, JBookingPaxMainScreen.class);
        RemoteSession remoteSession = null; // Not needed here.
        FieldList recPaxCategory = mainScreen.getPaxCategory();
        JCellRemoteComboBox box = new JCellRemoteComboBox(baseApplet, remoteSession, recPaxCategory, baseApplet.getString(BookingPaxGridModel.PAX_CATEGORY), PaxCategory.DESCRIPTION, BookingPaxGridModel.PAX_CATEGORY, false, Params.ID, null);
        tableColumn.setCellRenderer(box);
        this.getGridModel().getFieldInfo(BookingPaxGridModel.ROOM_COLUMN).addComponent(box);    // Make sure this gets freed
        
        box = new JCellRemoteComboBox(baseApplet, remoteSession, recPaxCategory, baseApplet.getString(BookingPaxGridModel.PAX_CATEGORY), PaxCategory.DESCRIPTION, BookingPaxGridModel.PAX_CATEGORY, false, Params.ID, null);
        tableColumn.setCellEditor(box);
        this.getGridModel().getFieldInfo(BookingPaxGridModel.ROOM_COLUMN).addComponent(box);    // Make sure this gets freed

        jTable.setRowHeight(Math.max(jTable.getRowHeight(), box.getPreferredSize().height));    // Make sure a row can hold a popup.
        tableColumn.setPreferredWidth(Math.max(tableColumn.getPreferredWidth(), box.getPreferredSize().width)); // Make sure the column is wide enough.
    }
    /**
     * Free.
     */
    public void free()
    {
        JCellRemoteComboBox box = (JCellRemoteComboBox)this.getFieldList().getField(BookingPax.PAX_CATEGORY_ID).getComponent(0);
        box.setRecord(null);    // Don't free this record.
        box = (JCellRemoteComboBox)this.getFieldList().getField(BookingPax.PAX_CATEGORY_ID).getComponent(1);
        box.setRecord(null);    // Don't free this record.
        super.free();
    }
    /**
     * Build the list of fields that make up the screen.
     * Override this to create a new record.
     * @return The fieldlist for this screen.
     */
    public FieldList buildFieldList()
    {
        return new BookingPax(null);    // If overriding class didn't set this
    }
    /**
     * Build the list of fields that make up the screen.
     * Override this to create a new record.
     * @return The fieldlist for this screen.
     */
    public ThinTableModel createGridModel(FieldList record)
    {
        return new BookingPaxGridModel(record.getTable());
    }
    /**
     * Process this action.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        boolean bAction = false;
        
        if (Constants.BACK.equalsIgnoreCase(strAction))
        {
            TourAppScreen tourAppScreen = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
            return tourAppScreen.doAction(BookingConstants.SEARCH, iOptions);
        }
        else if (Constants.RESET.equalsIgnoreCase(strAction))
        {
            ThinTableModel model = (ThinTableModel)this.getGridModel();
            FieldTable table = model.getFieldTable();
            table.close();
            model.resetTheModel();
            return true;
        }
        bAction = super.doAction(strAction, iOptions);
        
        return bAction;
    }
    /**
     * Add the menubars?
     * @return The newly created menubar or null.
     */
    public JMenuBar createMenubar()
    {
        return null;    // No menu bar on this sub-screen.
    }
}
