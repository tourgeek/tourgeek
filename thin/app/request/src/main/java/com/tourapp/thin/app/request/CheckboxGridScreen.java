package com.tourapp.thin.app.request;

import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.JCheckBox;
import javax.swing.table.TableModel;

import org.jbundle.thin.base.screen.grid.opt.JAltGridScreen;


/**
 * JGridScreen is a screen which tries to resemble a JTable with a GridLayout.
 * Most of the time, you should use JTable, except where the table is very simple
 * or you need to have different sized rows.
 */
public class CheckboxGridScreen extends JAltGridScreen
{
	private static final long serialVersionUID = 1L;

	public CheckboxGridScreen()
    {
        super();
    }
    /**
     *  Class Constructor.
     */
    public CheckboxGridScreen(Object parent, TableModel model)
    {
        this();
        this.init(parent, model);
    }
    /**
     * Add the catalog <quantity> <description> headings to the catalog panel.
     */
    public void addGridHeading()
    {
        super.addGridHeading();
        if (m_rgcompHeadings[RequestGridModel.QTY_COLUMN] != null)
            m_rgcompHeadings[RequestGridModel.QTY_COLUMN].setVisible(false);
    }
    /**
     * read through the catalog object and add add the items to the catalog panel.
     */
    public Component addDetailComponent(TableModel model, Object aValue, int iRowIndex, int iColumnIndex, GridBagConstraints c)
    {
        boolean bIsCompanySelected = false;
        if (this.getBaseApplet() != null)
            bIsCompanySelected = ((RequestMainScreen)this.getTargetScreen(this, RequestMainScreen.class)).isCompanySelected();
        if (bIsCompanySelected)
            return super.addDetailComponent(model, aValue, iRowIndex, iColumnIndex, c);
        else
        {
            if (iColumnIndex != RequestGridModel.QTY_COLUMN)
                return null;
            aValue = model.getValueAt(iRowIndex, RequestGridModel.DESC_COLUMN);   // Need the item description for the checkbox
            JCheckBox checkbox = new JCheckBox((String)aValue);
            checkbox.setOpaque(false);
            c.weightx = 1.0;
            c.gridwidth = GridBagConstraints.REMAINDER; //end row
            c.anchor = GridBagConstraints.WEST;     // Edit boxes left justified
            c.insets.right = 5;
            return checkbox;
        }
    }
}
