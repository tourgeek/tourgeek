/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.pax;

import java.util.Date;

import org.jbundle.model.DBException;
import org.jbundle.model.RemoteException;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.remote.RemoteTable;

import com.tourapp.thin.app.booking.entry.TourAppScreen;
import com.tourapp.thin.tour.booking.db.Booking;
import com.tourapp.thin.tour.product.base.db.ProductType;

/**
 * This class simply passes the call on after it synchronizes.
 */
public class PaxCountRemoteTable extends SubFileRemoteTable
{
    protected int iActualPaxCount = 0;
    
    /**
     * Constructor.
     */
    public PaxCountRemoteTable()
    {
        super();
    }
    /**
     * Constructor.
     * @param The remotetable (actually, the ThinTable).
     */
    public PaxCountRemoteTable(RemoteTable tableRemote, FieldList recMain)
    {
        this();
        this.init(tableRemote, recMain);
    }
    /**
     * Constructor.
     * @param The remotetable (actually, the ThinTable).
     */
    public void init(RemoteTable tableRemote, FieldList recMain)
    {
        super.init(tableRemote, recMain);
    }
    /**
     * Free is never called for a CachedTable.
     */
    public void free() throws RemoteException
    {
        super.free();
    }
    /**
     * Add add this data to the file.
     * @param data A vector object containing the raw data for the record.
     * @exception Exception File exception.
     */
    public Object add(Object data, int iOpenMode) throws DBException, RemoteException
    {
        Object bookmark = super.add(data, iOpenMode);
        iActualPaxCount++;
        this.setPax(iActualPaxCount);
        return bookmark;
    }
    public int getPax()
    {
        return (int)m_recMain.getField(Booking.PAX).getValue();
    }
    public int setPax(int iPax)
    {
        return m_recMain.getField(Booking.PAX).setValue(iPax);
    }
    /**
     * Create the new main record.
     * @return
     */
    public int createNewMainRecord()
    {
        TourAppScreen screen = (TourAppScreen)m_recMain.getOwner();
        String strProductType = ProductType.TOUR;
        String strID = Constants.BLANK;
        Date dateTarget = new Date();
        boolean bSuccess = screen.addProductToSession(strProductType, strID, dateTarget);
        if (bSuccess)
            return Constants.NORMAL_RETURN;
        else
            return Constants.ERROR_RETURN;
    }
    /**
     * Update the current record.
     * @param The data to update.
     * @exception DBException File exception.
     */
    public void set(Object data, int iOpenMode) throws DBException, RemoteException
    {
        super.set(data, iOpenMode);
    }
    /**
     * Delete the current record.
     * @param - This is a dummy param, because this call conflicts with a call in EJBHome.
     * @exception DBException File exception.
     */
    public void remove(Object data, int iOpenMode) throws DBException, RemoteException
    {
        super.remove(data, iOpenMode);
    }
    /**
     * Move the current position and read the record (optionally read several records).
     * @param iRelPosition relative Position to read the next record.
     * @param iRecordCount Records to read.
     * @return If I read 1 record, this is the record's data.
     * @return If I read several records, this is a vector of the returned records.
     * @return If at EOF, or error, returns the error code as a Integer.
     * @exception DBException File exception.
     */
    public Object doMove(int iRelPosition, int iRecordCount) throws DBException, RemoteException
    {
        Object data = super.doMove(iRelPosition, iRecordCount);
        if (data instanceof Integer)
        {
        }
        return data;
    }
    /**
     * Retrieve the record that matches this key.
     * @param strSeekSign Which way to seek null/= matches data also &gt;, &lt;, &gt;=, and &lt;=.
     * @param strKeyArea The name of the key area to seek on.
     * @param objKeyData The data for the seek (The raw data if a single field, a BaseBuffer if multiple).
     * @returns The record (as a vector) if successful, The return code (as an Boolean) if not.
     * @exception DBException File exception.
     */
    public Object seek(String strSeekSign, int iOpenMode, String strKeyArea, String strFields, Object objKeyData) throws DBException, RemoteException
    {
        return super.seek(strSeekSign, iOpenMode, strKeyArea, strFields, objKeyData);
    }
    /**
     * Reposition to this record using this bookmark.<p>
     * JiniTables can't access the datasource on the server, so they must use the bookmark.
     */
    public Object doSetHandle(Object bookmark, int iOpenMode, String strFields, int iHandleType) throws DBException, RemoteException
    {
        return super.doSetHandle(bookmark, iOpenMode, strFields, iHandleType);
    }
    /**
     * Receive this relative record in the table.
     * <p>Note: This is usually used only by thin clients, as thick clients have the code to
     * fake absolute access.
     * @param iRowIndex The row to retrieve.
     * @param iRowCount The number of rows to retrieve (Used only by EjbCachedTable).
     * @return The record(s) or an error code as an Integer.
     * @exception Exception File exception.
     */
    public Object get(int iRowIndex, int iRowCount) throws DBException, RemoteException
    {
        return super.get(iRowIndex, iRowCount);
    }
}
