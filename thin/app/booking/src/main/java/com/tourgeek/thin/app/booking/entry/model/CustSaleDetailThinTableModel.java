/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourgeek.thin.app.booking.entry.model;

import java.util.Date;

import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.jbundle.model.db.Convert;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.screen.cal.grid.CalendarThinTableModel;
import org.jbundle.thin.base.screen.grid.JCellTextField;

import com.tourgeek.thin.tour.booking.detail.db.BookingDetail;

public class CustSaleDetailThinTableModel extends CalendarThinTableModel
{
	private static final long serialVersionUID = 1L;

    public static final int STATUS_COLUMN = 0;
    public static final int START_DATE_COLUMN = STATUS_COLUMN + 1;
    public static final int END_DATE_COLUMN = START_DATE_COLUMN + 1;
    public static final int DESCRIPTION_COLUMN = END_DATE_COLUMN + 1;
//    public static final int COST_COLUMN = DESCRIPTION_COLUMN + 1;
    public static final int LOCAL_PRICE_COLUMN = DESCRIPTION_COLUMN + 1;
    public static final int START_DATE_BUTTON_COLUMN = LOCAL_PRICE_COLUMN + 1;
    public static final int END_DATE_BUTTON_COLUMN = START_DATE_BUTTON_COLUMN + 1;
    public static final int COLUMNS = END_DATE_BUTTON_COLUMN + 1;

    /**
     * Called to start the applet.  You never need to call this directly; it
     * is called when the applet's document is visited.
     */
    public CustSaleDetailThinTableModel()
    {
        super();
    }
    /**
     * Called to start the applet.  You never need to call this directly; it
     * is called when the applet's document is visited.
     */
    public CustSaleDetailThinTableModel(FieldTable table)
    {
        this();
        this.init(table);
    }
    /**
     * Constructor.
     */
    public CustSaleDetailThinTableModel(FieldTable table, String strStartDateTimeField, String strEndDateTimeField, String strDescriptionField, String strStatusField)
    {
        this();
        this.init(table, strStartDateTimeField, strEndDateTimeField, strDescriptionField, strStatusField);
    }
    /**
     * Constructor.
     */
    public void init(FieldTable table, String strStartDateTimeField, String strEndDateTimeField, String strDescriptionField, String strStatusField)
    {
        super.init(table, strStartDateTimeField, strEndDateTimeField, strDescriptionField, strStatusField);
    }
    /**
     * Get the row count.
     */
    public int getColumnCount()
    {
        return COLUMNS;
    }
    /**
     * Returns the field at columnIndex.
     */
    public Convert getFieldInfo(int iColumnIndex)
    {
        if (iColumnIndex == STATUS_COLUMN)
            return m_table.getRecord().getField(BookingDetail.STATUS_SUMMARY);
        else if (iColumnIndex == START_DATE_COLUMN)
            return m_table.getRecord().getField(BookingDetail.DETAIL_DATE);
        else if (iColumnIndex == END_DATE_COLUMN)
            return m_table.getRecord().getField(BookingDetail.DETAIL_END_DATE);
        else if (iColumnIndex == DESCRIPTION_COLUMN)
            return m_table.getRecord().getField(BookingDetail.DESCRIPTION);
//        else if (iColumnIndex == COST_COLUMN)
//            return m_table.getFieldList().getFieldInfo(BookingDetail.TOTAL_COST);
        else if (iColumnIndex == LOCAL_PRICE_COLUMN)
            return m_table.getRecord().getField(BookingDetail.TOTAL_PRICE_LOCAL);
        else if (iColumnIndex == START_DATE_BUTTON_COLUMN)
            return m_table.getRecord().getField(BookingDetail.DETAIL_DATE);
        else if (iColumnIndex == END_DATE_BUTTON_COLUMN)
            return m_table.getRecord().getField(BookingDetail.DETAIL_END_DATE);
        return super.getFieldInfo(iColumnIndex);
    }
    /**
     * Change the start time of this service.
     * Note: pend(don) There may be some synchronization issues to deal with here.
     */
    public Date setNewDates(BookingDetailCalendarItem item, Date timeStart, Date timeEnd)
    {
        int iIndex = this.getCurrentRow();
        FieldList fieldList = this.makeRowCurrent(iIndex, true);    // This is only necessary for the GridModel, because screenFields directly update the record.
        Date timeStartOld = (Date)fieldList.getField(BookingDetail.DETAIL_DATE).getData();
        if (timeStart != null)
            fieldList.getField(BookingDetail.DETAIL_DATE).setData(timeStart);
        if (timeEnd == null)
        {
            Date timeEndOld = (Date)fieldList.getField(BookingDetail.DETAIL_END_DATE).getData();
            long lTimeDifference = timeEndOld.getTime() - timeStartOld.getTime();
            timeEnd = new Date(timeStart.getTime() + lTimeDifference);
        }
        fieldList.getField(BookingDetail.DETAIL_END_DATE).setData(timeEnd);
        try   {
            if ((fieldList.getEditMode() == Constants.EDIT_CURRENT) || (fieldList.getEditMode() == Constants.EDIT_IN_PROGRESS))
                m_table.set(fieldList);   // Current record = set (always)
        } catch (Exception ex)  {
            ex.printStackTrace();
        }
        m_iCurrentRowIndex = -1;    // Can't use current record anymore
        m_iCurrentLockedRowIndex = -1;
//        m_rgCurrentLockedData = null;
        item = (BookingDetailCalendarItem)this.makeRowCurrent(iIndex, false);  // return to original status
        if (timeStart != null)
            return item.getStartDate();
        else
            return item.getEndDate();
    }
    /**
     * Change the start time of this service.
     */
    public int getColumnIndex(String strFieldName)
    {
        for (int i = 0; i < this.getColumnCount(); i++)
        {
            Convert fieldInfo = this.getFieldInfo(i);
            if (strFieldName.equalsIgnoreCase(fieldInfo.getFieldName()))
                return i;
        }
        return -1;
    }
    /**
     * Get the cell editor for this column.
     * @param The column to get the cell editor for.
     * @return The cell editor or null to use the default.
     */
    public TableCellEditor createColumnCellEditor(int iColumnIndex)
    {
        switch (iColumnIndex)
        {
        case STATUS_COLUMN:
        case START_DATE_BUTTON_COLUMN:
        case END_DATE_BUTTON_COLUMN:
            break;
        case START_DATE_COLUMN:
        case END_DATE_COLUMN:
        case DESCRIPTION_COLUMN:
        case LOCAL_PRICE_COLUMN:
        default:
            Convert fieldInfo2 = this.getFieldInfo(iColumnIndex);
            JCellTextField component2 = new JCellTextField(fieldInfo2.getMaxLength(), false);
            component2.setOpaque(false);
            if (fieldInfo2.getField() != null)
                if (Number.class.isAssignableFrom(fieldInfo2.getField().getDataClass()))
                    component2.setHorizontalAlignment(JTextField.RIGHT);
            fieldInfo2.getField().addComponent(component2);
            if ((DESCRIPTION_COLUMN == iColumnIndex)
                    || (LOCAL_PRICE_COLUMN == iColumnIndex))
                component2.getCellEditorHelper().setEnabled(false);
            return component2;
        }
        return super.createColumnCellEditor(iColumnIndex);
    }
    /**
     * Get the cell renderer for this column.
     * @param The column to get the cell renderer for.
     * @return The cell renderer or null to use the default.
     */
    public TableCellRenderer createColumnCellRenderer(int iColumnIndex)
    {
        switch (iColumnIndex)
        {
        case STATUS_COLUMN:
        case START_DATE_BUTTON_COLUMN:
        case END_DATE_BUTTON_COLUMN:
            break;
        case LOCAL_PRICE_COLUMN:
        case START_DATE_COLUMN:
        case END_DATE_COLUMN:
        case DESCRIPTION_COLUMN:
        default:
            Convert fieldInfo2 = this.getFieldInfo(iColumnIndex);
            JCellTextField component2 = new JCellTextField(fieldInfo2.getMaxLength(), false);
            component2.setOpaque(false);
            if (fieldInfo2.getField() != null)
                if (Number.class.isAssignableFrom(fieldInfo2.getField().getDataClass()))
                    component2.setHorizontalAlignment(JTextField.RIGHT);
            fieldInfo2.getField().addComponent(component2);
            if ((DESCRIPTION_COLUMN == iColumnIndex)
                    || (LOCAL_PRICE_COLUMN == iColumnIndex))
                component2.getCellEditorHelper().setEnabled(false);
            return component2;
        }
        return super.createColumnCellRenderer(iColumnIndex);
    }
    /**
     * Add one extra blank record for appending?
     * @return true if I should add a record at the end for insertion.
     */
    public boolean isAppending()
    {
        return false;   // For the grid screen
    }
}
