/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.pax;

import org.jbundle.model.DBException;
import org.jbundle.model.RemoteException;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.db.client.SyncRemoteTable;
import org.jbundle.thin.base.remote.RemoteTable;


/**
 * This class simply makes sure the main record is written if one
 * of these sub-records are added.
 */
public class SubFileRemoteTable extends SyncRemoteTable
{
    /**
     * The main record to write and refresh before writing a new record here.
     */
    protected FieldList m_recMain = null;
    
    /**
     * Constructor.
     */
    public SubFileRemoteTable()
    {
        super();
    }
    /**
     * Constructor.
     * @param The remotetable (actually, the ThinTable).
     */
    public SubFileRemoteTable(RemoteTable tableRemote, FieldList recMain)
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
        Object objSync = this;
        super.init(tableRemote, objSync);
        m_recMain = recMain;
    }
    /**
     * Free is never called for a CachedTable.
     */
    public void free() throws RemoteException
    {
        m_recMain = null;
        super.free();
    }
    /**
     * Add add this data to the file.
     * @param data A vector object containing the raw data for the record.
     * @exception Exception File exception.
     */
    public Object add(Object data, int iOpenMode) throws DBException, RemoteException
    {
        if ((m_recMain.getEditMode() == Constants.EDIT_ADD) ||
                (m_recMain.getEditMode() == Constants.EDIT_NONE))
        {
            this.createNewMainRecord();
        }
        return super.add(data, iOpenMode);
    }
    /**
     * Create the new main record.
     * @return
     */
    public int createNewMainRecord()
    {
        try {
            FieldTable tableMain = m_recMain.getTable();
            tableMain.add(m_recMain);
            Object bookmark = tableMain.getLastModified(0);
            tableMain.setHandle(bookmark, 0);
            return Constants.NORMAL_RETURN;
        } catch (DBException ex) {
            return Constants.ERROR_RETURN;
        }
    }
}
