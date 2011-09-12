/**
 * @(#)RequestLabelsRestore.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.request.report;

import java.awt.*;
import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.request.db.*;

/**
 *  RequestLabelsRestore - Restore the marked labels from the history file..
 */
public class RequestLabelsRestore extends RequestLabelsUpdate
{
    /**
     * Default constructor.
     */
    public RequestLabelsRestore()
    {
        super();
    }
    /**
     * Constructor.
     */
    public RequestLabelsRestore(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
    {
        this();
        this.init(taskParent, recordMain, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwnerParent taskParent, Record recordMain, Map<String, Object> properties)
    {
        super.init(taskParent, recordMain, properties);
    }
    /**
     * Add the behaviors.
     */
    public void addListeners()
    {
        // DO NOT CALL INHERITED (my listeners are different)
        this.getRecord(RequestHistory.kRequestHistoryFile).setKeyArea(RequestHistory.kHistReprintKey);
        this.getRecord(RequestHistory.kRequestHistoryFile).addListener(new SubFileFilter(this.getScreenRecord().getField(RequestLabelsScreenRecord.kTrueField), RequestHistory.kHistReprint, null, -1, null, -1));
        this.getRecord(RequestDetail.kRequestDetailFile).addListener(new SubFileFilter(this.getRecord(Request.kRequestFile)));
        this.getRecord(RequestHistoryDetail.kRequestHistoryDetailFile).addListener(new SubFileFilter(this.getRecord(RequestHistory.kRequestHistoryFile)));
    }
    /**
     * Restore the marked history.
     */
    public void run()
    {
        Record recRequest = this.getRecord(Request.kRequestFile);
        Record recRequestDetail = this.getRecord(RequestDetail.kRequestDetailFile);
        Record recRequestHistory = this.getRecord(RequestHistory.kRequestHistoryFile);
        Record recRequestHistoryDetail = this.getRecord(RequestHistoryDetail.kRequestHistoryDetailFile);
        
        try {
            recRequestHistory.close();
            while (recRequestHistory.hasNext())
            {
                recRequestHistory.next();
                recRequestHistory.edit();
        
                recRequest.addNew();
                recRequest.moveFields(recRequestHistory, Record.MOVE_BY_NAME, true, DBConstants.SCREEN_MOVE, true, false, false);   // Move all fields to the history record
                recRequest.add();
                Object bookmark = recRequest.getLastModified(DBConstants.BOOKMARK_HANDLE);
                recRequest.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE);
        
                this.updateRequestDetail(recRequestDetail, recRequestHistoryDetail);
        
                recRequestHistory.getField(RequestHistory.kHistReprint).setState(false);
                recRequestHistory.set();
            }
        } catch (DBException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * UpdateRequestDetail Method.
     */
    public void updateRequestDetail(Record recRequestDetail, Record recRequestHistoryDetail)
    {
        try   {
            recRequestHistoryDetail.close();
            while (recRequestHistoryDetail.hasNext())
            {
                recRequestHistoryDetail.next();
        
                recRequestDetail.addNew();
                recRequestDetail.moveFields(recRequestHistoryDetail, Record.MOVE_BY_NAME, true, DBConstants.SCREEN_MOVE, true, false, false);   // Move all fields to the history record
                recRequestDetail.add();  
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
    }

}
