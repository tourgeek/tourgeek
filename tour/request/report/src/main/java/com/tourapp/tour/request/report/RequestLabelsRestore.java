/**
  * @(#)RequestLabelsRestore.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
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
        this.getRecord(RequestHistory.REQUEST_HISTORY_FILE).setKeyArea(RequestHistory.HIST_REPRINT_KEY);
        this.getRecord(RequestHistory.REQUEST_HISTORY_FILE).addListener(new SubFileFilter(this.getScreenRecord().getField(RequestLabelsScreenRecord.TRUE_FIELD), RequestHistory.HIST_REPRINT, null, null, null, null));
        this.getRecord(RequestDetail.REQUEST_DETAIL_FILE).addListener(new SubFileFilter(this.getRecord(Request.REQUEST_FILE)));
        this.getRecord(RequestHistoryDetail.REQUEST_HISTORY_DETAIL_FILE).addListener(new SubFileFilter(this.getRecord(RequestHistory.REQUEST_HISTORY_FILE)));
    }
    /**
     * Restore the marked history.
     */
    public void run()
    {
        Record recRequest = this.getRecord(Request.REQUEST_FILE);
        Record recRequestDetail = this.getRecord(RequestDetail.REQUEST_DETAIL_FILE);
        Record recRequestHistory = this.getRecord(RequestHistory.REQUEST_HISTORY_FILE);
        Record recRequestHistoryDetail = this.getRecord(RequestHistoryDetail.REQUEST_HISTORY_DETAIL_FILE);
        
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
        
                recRequestHistory.getField(RequestHistory.HIST_REPRINT).setState(false);
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
